package com.eshare.executor;

import com.alibaba.cola.command.Command;
import com.alibaba.cola.command.CommandExecutorI;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.BizException;
import com.alibaba.cola.extension.BizScenario;
import com.eshare.common.BizCode;
import com.eshare.domain.creditlimit.RegistrationLimit;
import com.eshare.domain.gateway.CustomerGateway;
import com.eshare.dto.CreditLimitRegisterCmd;
import com.eshare.dto.clientobject.RegistrationLimitCO;
import com.eshare.domain.creditlimit.CustomerLimit;
import com.eshare.dto.domainmodel.ProductLimit;
import com.eshare.repository.CustomerLimitRepository;
import com.eshare.repository.ProductLimitRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author Evan Leung
 * <p>
 * 客户注册命令执行类
 */
@Command
public class CreditLimitRegisterCmdExe implements CommandExecutorI<SingleResponse<ProductLimit>, CreditLimitRegisterCmd> {

    private final ProductLimitRepository productLimitRepository;
    private final CustomerLimitRepository customerLimitRepository;
    private final CustomerGateway customerGateway;

    @Autowired
    public CreditLimitRegisterCmdExe(ProductLimitRepository productLimitRepository, CustomerLimitRepository customerLimitRepository, CustomerGateway customerGateway) {
        this.productLimitRepository = productLimitRepository;
        this.customerLimitRepository = customerLimitRepository;
        this.customerGateway = customerGateway;
    }

    @Override
    public SingleResponse<ProductLimit> execute(CreditLimitRegisterCmd cmd) {
        ProductLimit productLimitRsp= new ProductLimit();
        RegistrationLimitCO registrationLimitCO = cmd.getRegistrationLimitCO();
        RegistrationLimit registrationLimit = new RegistrationLimit();
        BeanUtils.copyProperties(registrationLimitCO, registrationLimit);
        Long customerId = customerGateway.getCustomerId(registrationLimitCO.getUserId());
        registrationLimit.setCustomerId(customerId);
        registrationLimit.setBizScenario(BizScenario.valueOf("eshare", "registerLimit", "jd"));
        CustomerLimit customerLimit = customerLimitRepository.find(customerId);
        if (customerLimit == null) {
            customerLimit = customerLimitRepository.init(customerId);
        }
        int cardQuota = productLimitRepository.sumQuota(customerId);
        if (customerLimit.getQuotaLimit() < cardQuota + registrationLimitCO.getQuotaLimit()) {
            throw new BizException(BizCode.BIZ_ONE);
        }

        // 2. 保存额度
        com.eshare.domain.creditlimit.ProductLimit productLimit = productLimitRepository.save(registrationLimit);
        //3. 转换领域对象到dto
        BeanUtils.copyProperties(productLimit, productLimitRsp);
        return SingleResponse.of(productLimitRsp);
    }
}