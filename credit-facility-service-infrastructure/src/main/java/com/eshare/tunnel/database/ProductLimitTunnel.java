package com.eshare.tunnel.database;

import com.eshare.tunnel.database.dataobject.ProductLimitDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductLimitTunnel {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_product_quota
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_product_quota
     *
     * @mbggenerated
     */
    int insert(ProductLimitDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_product_quota
     *
     * @mbggenerated
     */
    int insertSelective(ProductLimitDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_product_quota
     *
     * @mbggenerated
     */
    ProductLimitDO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_product_quota
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(ProductLimitDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_product_quota
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(ProductLimitDO record);

    /**
     * 此方法返回满足条件的记录数;<br/>
     * 此返回条件判断其值不为 null且不为空'';<br/>
     * 若strict为true则精确匹配所有值,若为false则模糊匹配所有类型为String的值;<br/>
     * This method was generated by MyBatis Generator.<br/>
     * This method corresponds to the database table<br/> t_product_quota
     *
     * @mbg.generated Mon Dec 11 18:23:07 CST 2019
     */
    Integer countByModelSelective(@Param("cond") ProductLimitDO cond, @Param("strict") boolean strict);

    /**
     * 此方法删除满足条件的记录数;<br/>
     * 此条件判断其值不为 null且不为空'';<br/>
     * 若strict为true则精确匹配所有值,若为false则模糊匹配所有类型为String的值;<br/>
     * This method was generated by MyBatis Generator.<br/>
     * This method corresponds to the database table<br/> t_product_quota
     *
     * @mbg.generated Mon Dec 11 18:23:07 CST 2019
     */
    Integer deleteByModelSelective(@Param("cond") ProductLimitDO cond, @Param("strict") boolean strict);

    /**
     * 此方法根据cond设置条件更新model中设置的字段值;<br/>
     * 此条件判断其值不为 null且不为空'';<br/>
     * 若strict为true则精确匹配所有值,若为false则模糊匹配所有类型为String的值;<br/>
     * This method was generated by MyBatis Generator.<br/>
     * This method corresponds to the database table<br/> t_product_quota
     *
     * @mbg.generated Mon Dec 11 18:23:07 CST 2019
     */
    Integer updateByModelSelective(@Param("cond") ProductLimitDO cond, @Param("strict") boolean strict, @Param("model") ProductLimitDO model);

    /**
     * 此方法返回满足条件的单条记录.<br/>
     * 此返回条件判断其值不为 null且不为空'';<br/>
     * 若strict为true则精确匹配所有值,若为false则模糊匹配所有类型为String的值;<br/>
     * 注意：java的Date不能完成匹配Oracle Date类型，即insert date into table 然后使用同一值date select是取不到对应记录的.<br/>
     * <p>
     * This method was generated by MyBatis Generator.<br/>
     * This method corresponds to the database table t_product_quota
     *
     * @mbg.generated Mon Dec 11 18:23:07 CST 2019
     */
    ProductLimitDO selectByModelSelective(@Param("cond") ProductLimitDO cond, @Param("strict") boolean strict);

    /**
     * 计算客户产品额度的总值
     *
     * @param customerId
     * @return
     */
    Integer sumQuota(@Param("customerId") Long customerId);

    /**
     * 根据卡ID查询额度
     *
     * @param cardIds
     * @return
     */
    List<ProductLimitDO> selectByCardIds(List<Long> cardIds);
}
