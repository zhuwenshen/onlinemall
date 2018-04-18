package com.zhuwenshen.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_warehouse")
public class TWarehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 进货商品价格id
     */
    @Column(name = "price_id")
    private String priceId;

    /**
     * 定价或退货价
     */
    private BigDecimal price;

    /**
     * 进货数量
     */
    private Integer num;

    /**
     * 货源
     */
    private String supply;

    /**
     * 备注
     */
    private String remake;

    /**
     * 进出仓库类型;【1：进货，-1：退货】
     */
    private Integer type;

    /**
     * 是否删除；0否 1是
     */
    private Boolean deleted;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建人id
     */
    @Column(name = "create_userid")
    private String createUserid;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 更新人id
     */
    @Column(name = "update_userid")
    private String updateUserid;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取进货商品价格id
     *
     * @return price_id - 进货商品价格id
     */
    public String getPriceId() {
        return priceId;
    }

    /**
     * 设置进货商品价格id
     *
     * @param priceId 进货商品价格id
     */
    public void setPriceId(String priceId) {
        this.priceId = priceId == null ? null : priceId.trim();
    }

    /**
     * 获取定价或退货价
     *
     * @return price - 定价或退货价
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置定价或退货价
     *
     * @param price 定价或退货价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取进货数量
     *
     * @return num - 进货数量
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置进货数量
     *
     * @param num 进货数量
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 获取货源
     *
     * @return supply - 货源
     */
    public String getSupply() {
        return supply;
    }

    /**
     * 设置货源
     *
     * @param supply 货源
     */
    public void setSupply(String supply) {
        this.supply = supply == null ? null : supply.trim();
    }

    /**
     * 获取备注
     *
     * @return remake - 备注
     */
    public String getRemake() {
        return remake;
    }

    /**
     * 设置备注
     *
     * @param remake 备注
     */
    public void setRemake(String remake) {
        this.remake = remake == null ? null : remake.trim();
    }

    /**
     * 获取进出仓库类型;【1：进货，-1：退货】
     *
     * @return type - 进出仓库类型;【1：进货，-1：退货】
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置进出仓库类型;【1：进货，-1：退货】
     *
     * @param type 进出仓库类型;【1：进货，-1：退货】
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取是否删除；0否 1是
     *
     * @return deleted - 是否删除；0否 1是
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * 设置是否删除；0否 1是
     *
     * @param deleted 是否删除；0否 1是
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取创建人id
     *
     * @return create_userid - 创建人id
     */
    public String getCreateUserid() {
        return createUserid;
    }

    /**
     * 设置创建人id
     *
     * @param createUserid 创建人id
     */
    public void setCreateUserid(String createUserid) {
        this.createUserid = createUserid == null ? null : createUserid.trim();
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取更新人id
     *
     * @return update_userid - 更新人id
     */
    public String getUpdateUserid() {
        return updateUserid;
    }

    /**
     * 设置更新人id
     *
     * @param updateUserid 更新人id
     */
    public void setUpdateUserid(String updateUserid) {
        this.updateUserid = updateUserid == null ? null : updateUserid.trim();
    }
}