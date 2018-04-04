package com.zhuwenshen.model.custom;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhuwenshen.annotation.ConstantTranslate;

public class QueryApprovalMerchant {
	
	private String id;
	private String userId;
	private String realName;
	private String merchantInformationId;
	private Long merchantNum;
	private String merchantName;
	private Integer certificationWorkflow;
	//对certificationWorkflow的翻译
	@ConstantTranslate(kind="certification_workflow_of_merchant",valueMethod="getCertificationWorkflow")
	private String workflowTL;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date applyTime;
	private Integer approvalType;
	@ConstantTranslate(kind="approval_type_of_merchant",valueMethod="getApprovalType")
	private String approvalTypeTL;
	//审批结果描述
	private String approvalResult;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date approvalTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMerchantInformationId() {
		return merchantInformationId;
	}

	public void setMerchantInformationId(String merchantInformationId) {
		this.merchantInformationId = merchantInformationId;
	}

	public Long getMerchantNum() {
		return merchantNum;
	}

	public void setMerchantNum(Long merchantNum) {
		this.merchantNum = merchantNum;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public Integer getCertificationWorkflow() {
		return certificationWorkflow;
	}

	public void setCertificationWorkflow(Integer certificationWorkflow) {
		this.certificationWorkflow = certificationWorkflow;
	}
	
	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Integer getApprovalType() {
		return approvalType;
	}

	public void setApprovalType(Integer approvalType) {
		this.approvalType = approvalType;
	}

	public String getApprovalResult() {
		return approvalResult;
	}

	public void setApprovalResult(String approvalResult) {
		this.approvalResult = approvalResult;
	}

	public Date getApprovalTime() {
		return approvalTime;
	}

	public void setApprovalTime(Date approvalTime) {
		this.approvalTime = approvalTime;
	}

	//@JsonSerialize(using = ConstantTranslateSerialize.class)
	public String getWorkflowTL() {
		return workflowTL;
	}

	public void setWorkflowTL(String workflowTL) {
		this.workflowTL = workflowTL;
	}

	public String getApprovalTypeTL() {
		return approvalTypeTL;
	}

	public void setApprovalTypeTL(String approvalTypeTL) {
		this.approvalTypeTL = approvalTypeTL;
	}

	@Override
	public String toString() {
		return "QueryApprovalMerchant [id=" + id + ", userId=" + userId + ", realName=" + realName
				+ ", merchantInformationId=" + merchantInformationId + ", merchantNum=" + merchantNum
				+ ", merchantName=" + merchantName + ", certificationWorkflow=" + certificationWorkflow
				+ ", workflowTL=" + workflowTL + ", applyTime=" + applyTime + ", approvalType=" + approvalType
				+ ", approvalTypeTL=" + approvalTypeTL + ", approvalResult=" + approvalResult + ", approvalTime="
				+ approvalTime + "]";
	}

	

}
