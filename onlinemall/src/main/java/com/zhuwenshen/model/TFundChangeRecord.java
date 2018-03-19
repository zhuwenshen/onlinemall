package com.zhuwenshen.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_fund_change_record")
public class TFundChangeRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 商家id
     */
    @Column(name = "merchant_information_id")
    private String merchantInformationId;

    /**
     * 用户类型；1游客 2普通用户 3商家 4商家服务员 5管理员 6超级管理员
     */
    @Column(name = "user_type")
    private Integer userType;

    /**
     * 订单id
     */
    @Column(name = "t_order_id")
    private String tOrderId;

    /**
     * 资金变化数量
     */
    @Column(name = "changing_num")
    private BigDecimal changingNum;

    /**
     * 资金变化类型；1充值 2提现 3售出 4购买
     */
    @Column(name = "fund_change_type")
    private Integer fundChangeType;

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
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 获取商家id
     *
     * @return merchant_information_id - 商家id
     */
    public String getMerchantInformationId() {
        return merchantInformationId;
    }

    /**
     * 设置商家id
     *
     * @param merchantInformationId 商家id
     */
    public void setMerchantInformationId(String merchantInformationId) {
        this.merchantInformationId = merchantInformationId == null ? null : merchantInformationId.trim();
    }

    /**
     * 获取用户类型；1游客 2普通用户 3商家 4商家服务员 5管理员 6超级管理员
     *
     * @return user_type - 用户类型；1游客 2普通用户 3商家 4商家服务员 5管理员 6超级管理员
     */
    public Integer getUserType() {
        return userType;
    }

    /**
     * 设置用户类型；1游客 2普通用户 3商家 4商家服务员 5管理员 6超级管理员
     *
     * @param userType 用户类型；1游客 2普通用户 3商家 4商家服务员 5管理员 6超级管理员
     */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /**
     * 获取订单id
     *
     * @return t_order_id - 订单id
     */
    public String gettOrderId() {
        return tOrderId;
    }

    /**
     * 设置订单id
     *
     * @param tOrderId 订单id
     */
    public void settOrderId(String tOrderId) {
        this.tOrderId = tOrderId == null ? null : tOrderId.trim();
    }

    /**
     * 获取资金变化数量
     *
     * @return changing_num - 资金变化数量
     */
    public BigDecimal getChangingNum() {
        return changingNum;
    }

    /**
     * 设置资金变化数量
     *
     * @param changingNum 资金变化数量
     */
    public void setChangingNum(BigDecimal changingNum) {
        this.changingNum = changingNum;
    }

    /**
     * 获取资金变化类型；1充值 2提现 3售出 4购买
     *
     * @return fund_change_type - 资金变化类型；1充值 2提现 3售出 4购买
     */
    public Integer getFundChangeType() {
        return fundChangeType;
    }

    /**
     * 设置资金变化类型；1充值 2提现 3售出 4购买
     *
     * @param fundChangeType 资金变化类型；1充值 2提现 3售出 4购买
     */
    public void setFundChangeType(Integer fundChangeType) {
        this.fundChangeType = fundChangeType;
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