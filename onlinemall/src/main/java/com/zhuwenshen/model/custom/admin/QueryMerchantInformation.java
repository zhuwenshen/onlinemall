package com.zhuwenshen.model.custom.admin;

import java.math.BigDecimal;

import com.zhuwenshen.annotation.ConstantTranslate;
import com.zhuwenshen.annotation.PicUrl;

public class QueryMerchantInformation {

	private String id;
	private String userId;
	private String userName;
	/**
	 * 商家编号
	 */
	private Long num;

	/**
	 * 店名
	 */
	private String nameCn;

	/**
	 * 店名（英文）
	 */
	private String nameEn;

	/**
	 * 详细地址
	 */
	private String detailedAddress;

	/**
	 * 联系电话
	 */
	private String phone;

	/**
	 * 积分
	 */
	private BigDecimal integral;

	/**
	 * 总积分
	 */
	private BigDecimal allIntegral;

	/**
	 * 商家头像
	 */
	@PicUrl
	private String merchantPortraitUrl;

	/**
	 * 认证等级;默认0(0未通过审核 1实名认证 2实体店认证)
	 */
	private Integer certificationLevel;

	@ConstantTranslate(kind = "certification_level_of_merchant", valueMethod = "getCertificationLevel")
	private String certificationLevelTL;

	/**
	 * 资金
	 */
	private BigDecimal fund;

	/**
	 * 商家描述图片1
	 */
	@PicUrl
	private String describingImg1Url;

	/**
	 * 商家描述图片2
	 */
	@PicUrl
	private String describingImg2Url;

	/**
	 * 商家描述图片3
	 */
	@PicUrl
	private String describingImg3Url;

	/**
	 * 商家描述图片4
	 */
	@PicUrl
	private String describingImg4Url;

	/**
	 * 工商号
	 */
	private String businessLicense;

	/**
	 * 营业执照图片
	 */
	@PicUrl
	private String businessLicenseUrl;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public String getNameCn() {
		return nameCn;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getDetailedAddress() {
		return detailedAddress;
	}

	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public BigDecimal getIntegral() {
		return integral;
	}

	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}

	public BigDecimal getAllIntegral() {
		return allIntegral;
	}

	public void setAllIntegral(BigDecimal allIntegral) {
		this.allIntegral = allIntegral;
	}

	public String getMerchantPortraitUrl() {
		return merchantPortraitUrl;
	}

	public void setMerchantPortraitUrl(String merchantPortraitUrl) {
		this.merchantPortraitUrl = merchantPortraitUrl;
	}

	public Integer getCertificationLevel() {
		return certificationLevel;
	}

	public void setCertificationLevel(Integer certificationLevel) {
		this.certificationLevel = certificationLevel;
	}

	public String getCertificationLevelTL() {
		return certificationLevelTL;
	}

	public void setCertificationLevelTL(String certificationLevelTL) {
		this.certificationLevelTL = certificationLevelTL;
	}

	public BigDecimal getFund() {
		return fund;
	}

	public void setFund(BigDecimal fund) {
		this.fund = fund;
	}

	public String getDescribingImg1Url() {
		return describingImg1Url;
	}

	public void setDescribingImg1Url(String describingImg1Url) {
		this.describingImg1Url = describingImg1Url;
	}

	public String getDescribingImg2Url() {
		return describingImg2Url;
	}

	public void setDescribingImg2Url(String describingImg2Url) {
		this.describingImg2Url = describingImg2Url;
	}

	public String getDescribingImg3Url() {
		return describingImg3Url;
	}

	public void setDescribingImg3Url(String describingImg3Url) {
		this.describingImg3Url = describingImg3Url;
	}

	public String getDescribingImg4Url() {
		return describingImg4Url;
	}

	public void setDescribingImg4Url(String describingImg4Url) {
		this.describingImg4Url = describingImg4Url;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	public String getBusinessLicenseUrl() {
		return businessLicenseUrl;
	}

	public void setBusinessLicenseUrl(String businessLicenseUrl) {
		this.businessLicenseUrl = businessLicenseUrl;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
