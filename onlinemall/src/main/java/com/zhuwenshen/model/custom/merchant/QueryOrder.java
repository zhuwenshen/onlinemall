package com.zhuwenshen.model.custom.merchant;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuwenshen.annotation.ConstantTranslate;

public class QueryOrder {
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 商家id
     */
    private String merchantId;
    
   // private String merchantName;

    /**
     * 订单流程状态
     */
    private Integer orderStatus;
    
    @ConstantTranslate(kind="order_status",valueMethod="getOrderStatus")
    private Integer orderStatusTL;

    /**
     * 预计金额
     */
    private BigDecimal originalAmount;

    /**
     * 实际金额
     */
    private BigDecimal actualAmount;

    /**
     * 实际支付金额;单位元
     */
    private BigDecimal paidActualAmount;

    /**
     * 商家代金券id
     */  
    private String merchantCouponId;

    /**
     * 用户ip
     */
    private String userIp;

    /**
     * 订单支付方式
     */
    private Integer orderPaidType;
    
    /**
     * 收货人手机号
     */
    private String addressPhone;

    /**
     * 详细地址
     */
    private String addressDetail;

    /**
     * 收货人
     */
    private String addressConsignee;

    /**
     * 评价流程状态
     */
    private Integer evaluateStatus;

    /**
     * 快递单号
     */
    private String expressNumber;

    /**
     * 快递公司
     */
    private String expressCompany;

    /**
     * 快递状态码
     */
    private Integer expressCode;

    /**
     * 快递情况
     */
    private String expressStatus;
    
    /**
     * 提交订单时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date submitTime;

    /**
     * 支付时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date payTime;

    /**
     * 确认订单时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date confirmTime;

    /**
     * 签收时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date signTime;

    /**
     * 发货时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date deliverTime;

    /**
     * 订单完成时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
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
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
     private Date createTime;

     private List<QueryOrderInfor> orderInforList;
  

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

	public List<QueryOrderInfor> getOrderInforList() {
		return orderInforList;
	}

	public void setOrderInforList(List<QueryOrderInfor> orderInforList) {
		this.orderInforList = orderInforList;
	}

	public Integer getOrderStatusTL() {
		return orderStatusTL;
	}

	public void setOrderStatusTL(Integer orderStatusTL) {
		this.orderStatusTL = orderStatusTL;
	}

	public String getAddressPhone() {
		return addressPhone;
	}

	public void setAddressPhone(String addressPhone) {
		this.addressPhone = addressPhone;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getAddressConsignee() {
		return addressConsignee;
	}

	public void setAddressConsignee(String addressConsignee) {
		this.addressConsignee = addressConsignee;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	public Date getSignTime() {
		return signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	public Date getDeliverTime() {
		return deliverTime;
	}

	public void setDeliverTime(Date deliverTime) {
		this.deliverTime = deliverTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public String getRemake() {
		return remake;
	}

	public void setRemake(String remake) {
		this.remake = remake;
	}
	
}