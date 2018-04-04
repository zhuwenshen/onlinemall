package com.zhuwenshen.model.custom;

import java.util.Date;

public class QueryApprovalMerchantParam {

	private Integer pageNum;
	private Integer pageSize;
	
	private String realName;	
	private Long merchantNum;
	private String merchantName;
	private Integer certificationWorkflow;
	//对certificationWorkflow的翻译
	private String workflowTL;
	private Date applyTimeStart;
	private Date applyTimeEnd;
	private Integer approvalType;
	private String approvalTypeTL;
	private Date approvalTimeStart;
	private Date approvalTimeEnd;
	
	private String idU;
	private String approvalTypeU;
	private String approvalResultU;
	//认证等级;默认0(0未通过审核 1实名认证 2实体店认证)
	private String certificationLevelU;
	
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
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
	public String getWorkflowTL() {
		return workflowTL;
	}
	public void setWorkflowTL(String workflowTL) {
		this.workflowTL = workflowTL;
	}
	public Date getApplyTimeStart() {
		return applyTimeStart;
	}
	public void setApplyTimeStart(Date applyTimeStart) {
		this.applyTimeStart = applyTimeStart;
	}
	public Date getApplyTimeEnd() {
		return applyTimeEnd;
	}
	public void setApplyTimeEnd(Date applyTimeEnd) {
		this.applyTimeEnd = applyTimeEnd;
	}
	public Integer getApprovalType() {
		return approvalType;
	}
	public void setApprovalType(Integer approvalType) {
		this.approvalType = approvalType;
	}
	public String getApprovalTypeTL() {
		return approvalTypeTL;
	}
	public void setApprovalTypeTL(String approvalTypeTL) {
		this.approvalTypeTL = approvalTypeTL;
	}
	public Date getApprovalTimeStart() {
		return approvalTimeStart;
	}
	public void setApprovalTimeStart(Date approvalTimeStart) {
		this.approvalTimeStart = approvalTimeStart;
	}
	public Date getApprovalTimeEnd() {
		return approvalTimeEnd;
	}
	public void setApprovalTimeEnd(Date approvalTimeEnd) {
		this.approvalTimeEnd = approvalTimeEnd;
	}
	public String getIdU() {
		return idU;
	}
	public void setIdU(String idU) {
		this.idU = idU;
	}
	public String getApprovalTypeU() {
		return approvalTypeU;
	}
	public void setApprovalTypeU(String approvalTypeU) {
		this.approvalTypeU = approvalTypeU;
	}
	public String getApprovalResultU() {
		return approvalResultU;
	}
	public void setApprovalResultU(String approvalResultU) {
		this.approvalResultU = approvalResultU;
	}
	
	public String getCertificationLevelU() {
		return certificationLevelU;
	}
	public void setCertificationLevelU(String certificationLevelU) {
		this.certificationLevelU = certificationLevelU;
	}
	@Override
	public String toString() {
		return "QueryApprovalMerchantParam [pageNum=" + pageNum + ", pageSize=" + pageSize + ", realName=" + realName
				+ ", merchantNum=" + merchantNum + ", merchantName=" + merchantName + ", certificationWorkflow="
				+ certificationWorkflow + ", workflowTL=" + workflowTL + ", applyTimeStart=" + applyTimeStart
				+ ", applyTimeEnd=" + applyTimeEnd + ", approvalType=" + approvalType + ", approvalTypeTL="
				+ approvalTypeTL + ", approvalTimeStart=" + approvalTimeStart + ", approvalTimeEnd=" + approvalTimeEnd
				+ ", idU=" + idU + ", approvalTypeU=" + approvalTypeU + ", approvalResultU=" + approvalResultU
				+ ", certificationLevelU=" + certificationLevelU + "]";
	}
	

}
