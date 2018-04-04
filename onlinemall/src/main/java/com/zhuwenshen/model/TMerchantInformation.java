package com.zhuwenshen.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_merchant_information")
public class TMerchantInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "user_id")
    private String userId;

    /**
     * 商家编号
     */
    private Long num;

    /**
     * 店名
     */
    @Column(name = "name_cn")
    private String nameCn;

    /**
     * 店名（英文）;用于url访问和图片存储
     */
    @Column(name = "name_en")
    private String nameEn;

    /**
     * 详细地址
     */
    @Column(name = "detailed_address")
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
    @Column(name = "all_integral")
    private BigDecimal allIntegral;

    /**
     * 商家头像
     */
    @Column(name = "merchant_portrait_url")
    private String merchantPortraitUrl;

    /**
     * 认证等级;默认0(0未通过审核 1实名认证 2实体店认证)
     */
    @Column(name = "certification_level")
    private Integer certificationLevel;

    /**
     * 资金
     */
    private BigDecimal fund;

    /**
     * 商家描述图片1
     */
    @Column(name = "describing_img1_url")
    private String describingImg1Url;

    /**
     * 商家描述图片2
     */
    @Column(name = "describing_img2_url")
    private String describingImg2Url;

    /**
     * 商家描述图片3
     */
    @Column(name = "describing_img3_url")
    private String describingImg3Url;

    /**
     * 商家描述图片4
     */
    @Column(name = "describing_img4_url")
    private String describingImg4Url;

    /**
     * 工商号
     */
    @Column(name = "business_license")
    private String businessLicense;

    /**
     * 营业执照图片
     */
    @Column(name = "business_license_url")
    private String businessLicenseUrl;

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
     * @return user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 获取商家编号
     *
     * @return num - 商家编号
     */
    public Long getNum() {
        return num;
    }

    /**
     * 设置商家编号
     *
     * @param num 商家编号
     */
    public void setNum(Long num) {
        this.num = num;
    }

    /**
     * 获取店名
     *
     * @return name_cn - 店名
     */
    public String getNameCn() {
        return nameCn;
    }

    /**
     * 设置店名
     *
     * @param nameCn 店名
     */
    public void setNameCn(String nameCn) {
        this.nameCn = nameCn == null ? null : nameCn.trim();
    }

    /**
     * 获取店名（英文）;用于url访问和图片存储
     *
     * @return name_en - 店名（英文）;用于url访问和图片存储
     */
    public String getNameEn() {
        return nameEn;
    }

    /**
     * 设置店名（英文）;用于url访问和图片存储
     *
     * @param nameEn 店名（英文）;用于url访问和图片存储
     */
    public void setNameEn(String nameEn) {
        this.nameEn = nameEn == null ? null : nameEn.trim();
    }

    /**
     * 获取详细地址
     *
     * @return detailed_address - 详细地址
     */
    public String getDetailedAddress() {
        return detailedAddress;
    }

    /**
     * 设置详细地址
     *
     * @param detailedAddress 详细地址
     */
    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress == null ? null : detailedAddress.trim();
    }

    /**
     * 获取联系电话
     *
     * @return phone - 联系电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置联系电话
     *
     * @param phone 联系电话
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 获取积分
     *
     * @return integral - 积分
     */
    public BigDecimal getIntegral() {
        return integral;
    }

    /**
     * 设置积分
     *
     * @param integral 积分
     */
    public void setIntegral(BigDecimal integral) {
        this.integral = integral;
    }

    /**
     * 获取总积分
     *
     * @return all_integral - 总积分
     */
    public BigDecimal getAllIntegral() {
        return allIntegral;
    }

    /**
     * 设置总积分
     *
     * @param allIntegral 总积分
     */
    public void setAllIntegral(BigDecimal allIntegral) {
        this.allIntegral = allIntegral;
    }

    /**
     * 获取商家头像
     *
     * @return merchant_portrait_url - 商家头像
     */
    public String getMerchantPortraitUrl() {
        return merchantPortraitUrl;
    }

    /**
     * 设置商家头像
     *
     * @param merchantPortraitUrl 商家头像
     */
    public void setMerchantPortraitUrl(String merchantPortraitUrl) {
        this.merchantPortraitUrl = merchantPortraitUrl == null ? null : merchantPortraitUrl.trim();
    }

    /**
     * 获取认证等级;默认0(0未通过审核 1实名认证 2实体店认证)
     *
     * @return certification_level - 认证等级;默认0(0未通过审核 1实名认证 2实体店认证)
     */
    public Integer getCertificationLevel() {
        return certificationLevel;
    }

    /**
     * 设置认证等级;默认0(0未通过审核 1实名认证 2实体店认证)
     *
     * @param certificationLevel 认证等级;默认0(0未通过审核 1实名认证 2实体店认证)
     */
    public void setCertificationLevel(Integer certificationLevel) {
        this.certificationLevel = certificationLevel;
    }

    /**
     * 获取资金
     *
     * @return fund - 资金
     */
    public BigDecimal getFund() {
        return fund;
    }

    /**
     * 设置资金
     *
     * @param fund 资金
     */
    public void setFund(BigDecimal fund) {
        this.fund = fund;
    }

    /**
     * 获取商家描述图片1
     *
     * @return describing_img1_url - 商家描述图片1
     */
    public String getDescribingImg1Url() {
        return describingImg1Url;
    }

    /**
     * 设置商家描述图片1
     *
     * @param describingImg1Url 商家描述图片1
     */
    public void setDescribingImg1Url(String describingImg1Url) {
        this.describingImg1Url = describingImg1Url == null ? null : describingImg1Url.trim();
    }

    /**
     * 获取商家描述图片2
     *
     * @return describing_img2_url - 商家描述图片2
     */
    public String getDescribingImg2Url() {
        return describingImg2Url;
    }

    /**
     * 设置商家描述图片2
     *
     * @param describingImg2Url 商家描述图片2
     */
    public void setDescribingImg2Url(String describingImg2Url) {
        this.describingImg2Url = describingImg2Url == null ? null : describingImg2Url.trim();
    }

    /**
     * 获取商家描述图片3
     *
     * @return describing_img3_url - 商家描述图片3
     */
    public String getDescribingImg3Url() {
        return describingImg3Url;
    }

    /**
     * 设置商家描述图片3
     *
     * @param describingImg3Url 商家描述图片3
     */
    public void setDescribingImg3Url(String describingImg3Url) {
        this.describingImg3Url = describingImg3Url == null ? null : describingImg3Url.trim();
    }

    /**
     * 获取商家描述图片4
     *
     * @return describing_img4_url - 商家描述图片4
     */
    public String getDescribingImg4Url() {
        return describingImg4Url;
    }

    /**
     * 设置商家描述图片4
     *
     * @param describingImg4Url 商家描述图片4
     */
    public void setDescribingImg4Url(String describingImg4Url) {
        this.describingImg4Url = describingImg4Url == null ? null : describingImg4Url.trim();
    }

    /**
     * 获取工商号
     *
     * @return business_license - 工商号
     */
    public String getBusinessLicense() {
        return businessLicense;
    }

    /**
     * 设置工商号
     *
     * @param businessLicense 工商号
     */
    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense == null ? null : businessLicense.trim();
    }

    /**
     * 获取营业执照图片
     *
     * @return business_license_url - 营业执照图片
     */
    public String getBusinessLicenseUrl() {
        return businessLicenseUrl;
    }

    /**
     * 设置营业执照图片
     *
     * @param businessLicenseUrl 营业执照图片
     */
    public void setBusinessLicenseUrl(String businessLicenseUrl) {
        this.businessLicenseUrl = businessLicenseUrl == null ? null : businessLicenseUrl.trim();
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