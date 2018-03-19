package com.zhuwenshen.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_merchant_coupon")
public class TMerchantCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 商品id
     */
    @Column(name = "goods_id")
    private String goodsId;

    /**
     * 名称
     */
    private String name;

    /**
     * 优惠金额
     */
    @Column(name = "discount_num")
    private BigDecimal discountNum;

    /**
     * 满额条件
     */
    @Column(name = "condition_num")
    private BigDecimal conditionNum;

    /**
     * 数量
     */
    private Integer num;

    /**
     * 打折;100为不打折
     */
    @Column(name = "discount_amount")
    private Integer discountAmount;

    /**
     * 是否被使用;0否 1是 默认0
     */
    private Boolean used;

    /**
     * 被使用时间
     */
    @Column(name = "used_time")
    private Date usedTime;

    /**
     * 是否过期;0否 1是 默认0
     */
    private Boolean overdue;

    /**
     * 过期时间
     */
    @Column(name = "expiration_time")
    private Date expirationTime;

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
     * 获取商品id
     *
     * @return goods_id - 商品id
     */
    public String getGoodsId() {
        return goodsId;
    }

    /**
     * 设置商品id
     *
     * @param goodsId 商品id
     */
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取优惠金额
     *
     * @return discount_num - 优惠金额
     */
    public BigDecimal getDiscountNum() {
        return discountNum;
    }

    /**
     * 设置优惠金额
     *
     * @param discountNum 优惠金额
     */
    public void setDiscountNum(BigDecimal discountNum) {
        this.discountNum = discountNum;
    }

    /**
     * 获取满额条件
     *
     * @return condition_num - 满额条件
     */
    public BigDecimal getConditionNum() {
        return conditionNum;
    }

    /**
     * 设置满额条件
     *
     * @param conditionNum 满额条件
     */
    public void setConditionNum(BigDecimal conditionNum) {
        this.conditionNum = conditionNum;
    }

    /**
     * 获取数量
     *
     * @return num - 数量
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置数量
     *
     * @param num 数量
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 获取打折;100为不打折
     *
     * @return discount_amount - 打折;100为不打折
     */
    public Integer getDiscountAmount() {
        return discountAmount;
    }

    /**
     * 设置打折;100为不打折
     *
     * @param discountAmount 打折;100为不打折
     */
    public void setDiscountAmount(Integer discountAmount) {
        this.discountAmount = discountAmount;
    }

    /**
     * 获取是否被使用;0否 1是 默认0
     *
     * @return used - 是否被使用;0否 1是 默认0
     */
    public Boolean getUsed() {
        return used;
    }

    /**
     * 设置是否被使用;0否 1是 默认0
     *
     * @param used 是否被使用;0否 1是 默认0
     */
    public void setUsed(Boolean used) {
        this.used = used;
    }

    /**
     * 获取被使用时间
     *
     * @return used_time - 被使用时间
     */
    public Date getUsedTime() {
        return usedTime;
    }

    /**
     * 设置被使用时间
     *
     * @param usedTime 被使用时间
     */
    public void setUsedTime(Date usedTime) {
        this.usedTime = usedTime;
    }

    /**
     * 获取是否过期;0否 1是 默认0
     *
     * @return overdue - 是否过期;0否 1是 默认0
     */
    public Boolean getOverdue() {
        return overdue;
    }

    /**
     * 设置是否过期;0否 1是 默认0
     *
     * @param overdue 是否过期;0否 1是 默认0
     */
    public void setOverdue(Boolean overdue) {
        this.overdue = overdue;
    }

    /**
     * 获取过期时间
     *
     * @return expiration_time - 过期时间
     */
    public Date getExpirationTime() {
        return expirationTime;
    }

    /**
     * 设置过期时间
     *
     * @param expirationTime 过期时间
     */
    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
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