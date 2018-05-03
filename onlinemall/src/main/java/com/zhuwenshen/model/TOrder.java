package com.zhuwenshen.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_order")
public class TOrder {
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
    @Column(name = "merchant_id")
    private String merchantId;

    /**
     * 订单流程状态
     */
    @Column(name = "order_status")
    private Integer orderStatus;

    /**
     * 预计金额
     */
    @Column(name = "original_amount")
    private BigDecimal originalAmount;

    /**
     * 实际金额
     */
    @Column(name = "actual_amount")
    private BigDecimal actualAmount;

    /**
     * 实际支付金额;单位元
     */
    @Column(name = "paid_actual_amount")
    private BigDecimal paidActualAmount;

    /**
     * 商家代金券id
     */
    @Column(name = "merchant_coupon_id")
    private String merchantCouponId;

    /**
     * 用户ip
     */
    @Column(name = "user_ip")
    private String userIp;

    /**
     * 订单支付方式
     */
    @Column(name = "order_paid_type")
    private Integer orderPaidType;

    /**
     * 收货人手机号
     */
    @Column(name = "address_phone")
    private String addressPhone;

    /**
     * 详细地址
     */
    @Column(name = "address_detail")
    private String addressDetail;

    /**
     * 收货人
     */
    @Column(name = "address_consignee")
    private String addressConsignee;

    /**
     * 评价流程状态
     */
    @Column(name = "evaluate_status")
    private Integer evaluateStatus;

    /**
     * 快递单号
     */
    @Column(name = "express_number")
    private String expressNumber;

    /**
     * 快递公司
     */
    @Column(name = "express_company")
    private String expressCompany;

    /**
     * 快递状态码
     */
    @Column(name = "express_code")
    private Integer expressCode;

    /**
     * 快递情况
     */
    @Column(name = "express_status")
    private String expressStatus;

    /**
     * 提交订单时间
     */
    @Column(name = "submit_time")
    private Date submitTime;

    /**
     * 支付时间
     */
    @Column(name = "pay_time")
    private Date payTime;

    /**
     * 确认订单时间
     */
    @Column(name = "confirm_time")
    private Date confirmTime;

    /**
     * 签收时间
     */
    @Column(name = "sign_time")
    private Date signTime;

    /**
     * 发货时间
     */
    @Column(name = "deliver_time")
    private Date deliverTime;

    /**
     * 订单完成时间
     */
    @Column(name = "finish_time")
    private Date finishTime;

    /**
     * 备注
     */
    private String remake;

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
     * @return merchant_id - 商家id
     */
    public String getMerchantId() {
        return merchantId;
    }

    /**
     * 设置商家id
     *
     * @param merchantId 商家id
     */
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId == null ? null : merchantId.trim();
    }

    /**
     * 获取订单流程状态
     *
     * @return order_status - 订单流程状态
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * 设置订单流程状态
     *
     * @param orderStatus 订单流程状态
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 获取预计金额
     *
     * @return original_amount - 预计金额
     */
    public BigDecimal getOriginalAmount() {
        return originalAmount;
    }

    /**
     * 设置预计金额
     *
     * @param originalAmount 预计金额
     */
    public void setOriginalAmount(BigDecimal originalAmount) {
        this.originalAmount = originalAmount;
    }

    /**
     * 获取实际金额
     *
     * @return actual_amount - 实际金额
     */
    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    /**
     * 设置实际金额
     *
     * @param actualAmount 实际金额
     */
    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    /**
     * 获取实际支付金额;单位元
     *
     * @return paid_actual_amount - 实际支付金额;单位元
     */
    public BigDecimal getPaidActualAmount() {
        return paidActualAmount;
    }

    /**
     * 设置实际支付金额;单位元
     *
     * @param paidActualAmount 实际支付金额;单位元
     */
    public void setPaidActualAmount(BigDecimal paidActualAmount) {
        this.paidActualAmount = paidActualAmount;
    }

    /**
     * 获取商家代金券id
     *
     * @return merchant_coupon_id - 商家代金券id
     */
    public String getMerchantCouponId() {
        return merchantCouponId;
    }

    /**
     * 设置商家代金券id
     *
     * @param merchantCouponId 商家代金券id
     */
    public void setMerchantCouponId(String merchantCouponId) {
        this.merchantCouponId = merchantCouponId == null ? null : merchantCouponId.trim();
    }

    /**
     * 获取用户ip
     *
     * @return user_ip - 用户ip
     */
    public String getUserIp() {
        return userIp;
    }

    /**
     * 设置用户ip
     *
     * @param userIp 用户ip
     */
    public void setUserIp(String userIp) {
        this.userIp = userIp == null ? null : userIp.trim();
    }

    /**
     * 获取订单支付方式
     *
     * @return order_paid_type - 订单支付方式
     */
    public Integer getOrderPaidType() {
        return orderPaidType;
    }

    /**
     * 设置订单支付方式
     *
     * @param orderPaidType 订单支付方式
     */
    public void setOrderPaidType(Integer orderPaidType) {
        this.orderPaidType = orderPaidType;
    }

    /**
     * 获取收货人手机号
     *
     * @return address_phone - 收货人手机号
     */
    public String getAddressPhone() {
        return addressPhone;
    }

    /**
     * 设置收货人手机号
     *
     * @param addressPhone 收货人手机号
     */
    public void setAddressPhone(String addressPhone) {
        this.addressPhone = addressPhone == null ? null : addressPhone.trim();
    }

    /**
     * 获取详细地址
     *
     * @return address_detail - 详细地址
     */
    public String getAddressDetail() {
        return addressDetail;
    }

    /**
     * 设置详细地址
     *
     * @param addressDetail 详细地址
     */
    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail == null ? null : addressDetail.trim();
    }

    /**
     * 获取收货人
     *
     * @return address_consignee - 收货人
     */
    public String getAddressConsignee() {
        return addressConsignee;
    }

    /**
     * 设置收货人
     *
     * @param addressConsignee 收货人
     */
    public void setAddressConsignee(String addressConsignee) {
        this.addressConsignee = addressConsignee == null ? null : addressConsignee.trim();
    }

    /**
     * 获取评价流程状态
     *
     * @return evaluate_status - 评价流程状态
     */
    public Integer getEvaluateStatus() {
        return evaluateStatus;
    }

    /**
     * 设置评价流程状态
     *
     * @param evaluateStatus 评价流程状态
     */
    public void setEvaluateStatus(Integer evaluateStatus) {
        this.evaluateStatus = evaluateStatus;
    }

    /**
     * 获取快递单号
     *
     * @return express_number - 快递单号
     */
    public String getExpressNumber() {
        return expressNumber;
    }

    /**
     * 设置快递单号
     *
     * @param expressNumber 快递单号
     */
    public void setExpressNumber(String expressNumber) {
        this.expressNumber = expressNumber == null ? null : expressNumber.trim();
    }

    /**
     * 获取快递公司
     *
     * @return express_company - 快递公司
     */
    public String getExpressCompany() {
        return expressCompany;
    }

    /**
     * 设置快递公司
     *
     * @param expressCompany 快递公司
     */
    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany == null ? null : expressCompany.trim();
    }

    /**
     * 获取快递状态码
     *
     * @return express_code - 快递状态码
     */
    public Integer getExpressCode() {
        return expressCode;
    }

    /**
     * 设置快递状态码
     *
     * @param expressCode 快递状态码
     */
    public void setExpressCode(Integer expressCode) {
        this.expressCode = expressCode;
    }

    /**
     * 获取快递情况
     *
     * @return express_status - 快递情况
     */
    public String getExpressStatus() {
        return expressStatus;
    }

    /**
     * 设置快递情况
     *
     * @param expressStatus 快递情况
     */
    public void setExpressStatus(String expressStatus) {
        this.expressStatus = expressStatus == null ? null : expressStatus.trim();
    }

    /**
     * 获取提交订单时间
     *
     * @return submit_time - 提交订单时间
     */
    public Date getSubmitTime() {
        return submitTime;
    }

    /**
     * 设置提交订单时间
     *
     * @param submitTime 提交订单时间
     */
    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    /**
     * 获取支付时间
     *
     * @return pay_time - 支付时间
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * 设置支付时间
     *
     * @param payTime 支付时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * 获取确认订单时间
     *
     * @return confirm_time - 确认订单时间
     */
    public Date getConfirmTime() {
        return confirmTime;
    }

    /**
     * 设置确认订单时间
     *
     * @param confirmTime 确认订单时间
     */
    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    /**
     * 获取签收时间
     *
     * @return sign_time - 签收时间
     */
    public Date getSignTime() {
        return signTime;
    }

    /**
     * 设置签收时间
     *
     * @param signTime 签收时间
     */
    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    /**
     * 获取发货时间
     *
     * @return deliver_time - 发货时间
     */
    public Date getDeliverTime() {
        return deliverTime;
    }

    /**
     * 设置发货时间
     *
     * @param deliverTime 发货时间
     */
    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }

    /**
     * 获取订单完成时间
     *
     * @return finish_time - 订单完成时间
     */
    public Date getFinishTime() {
        return finishTime;
    }

    /**
     * 设置订单完成时间
     *
     * @param finishTime 订单完成时间
     */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
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