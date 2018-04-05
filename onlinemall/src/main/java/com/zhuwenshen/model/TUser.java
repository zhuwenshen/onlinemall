package com.zhuwenshen.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_user")
public class TUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 真实姓名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 身份证号
     */
    @Column(name = "ID_number")
    private String idNumber;

    /**
     * 头像
     */
    @Column(name = "head_portrait_url")
    private String headPortraitUrl;

    /**
     * 积分,单位分
     */
    private BigDecimal integral;

    /**
     * 总积分
     */
    @Column(name = "all_integral")
    private BigDecimal allIntegral;

    /**
     * 资金
     */
    private BigDecimal fund;

    /**
     * 1男 2女 3隐藏
     */
    private Integer sex;

    /**
     * 出生日期 格式YYYY-MM-DD
     */
    private Date birthdate;

    /**
     * 用户类型；1游客 2普通用户 3商家 4商家服务员 5管理员 6超级管理员
     */
    @Column(name = "user_type")
    private Integer userType;

    /**
     * 登录账号；用于登录账号，默认手机号，不可以更改
     */
    @Column(name = "login_ID")
    private String loginId;

    /**
     * 登录密码
     */
    @Column(name = "login_passworld")
    private String loginPassworld;

    /**
     * 支付密码
     */
    @Column(name = "pay_passworld")
    private String payPassworld;

    /**
     * 是否冻结；0否 1是 
     */
    private Boolean frozen;

    /**
     * 解封时间
     */
    @Column(name = "unfreezing_time")
    private Date unfreezingTime;

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
     * 获取用户昵称
     *
     * @return name - 用户昵称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置用户昵称
     *
     * @param name 用户昵称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取手机号
     *
     * @return phone - 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号
     *
     * @param phone 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取真实姓名
     *
     * @return real_name - 真实姓名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置真实姓名
     *
     * @param realName 真实姓名
     */
    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    /**
     * 获取身份证号
     *
     * @return ID_number - 身份证号
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * 设置身份证号
     *
     * @param idNumber 身份证号
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber == null ? null : idNumber.trim();
    }

    /**
     * 获取头像
     *
     * @return head_portrait_url - 头像
     */
    public String getHeadPortraitUrl() {
        return headPortraitUrl;
    }

    /**
     * 设置头像
     *
     * @param headPortraitUrl 头像
     */
    public void setHeadPortraitUrl(String headPortraitUrl) {
        this.headPortraitUrl = headPortraitUrl == null ? null : headPortraitUrl.trim();
    }

    /**
     * 获取积分,单位分
     *
     * @return integral - 积分,单位分
     */
    public BigDecimal getIntegral() {
        return integral;
    }

    /**
     * 设置积分,单位分
     *
     * @param integral 积分,单位分
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
     * 获取1男 2女 3隐藏
     *
     * @return sex - 1男 2女 3隐藏
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置1男 2女 3隐藏
     *
     * @param sex 1男 2女 3隐藏
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取出生日期 格式YYYY-MM-DD
     *
     * @return birthdate - 出生日期 格式YYYY-MM-DD
     */
    public Date getBirthdate() {
        return birthdate;
    }

    /**
     * 设置出生日期 格式YYYY-MM-DD
     *
     * @param birthdate 出生日期 格式YYYY-MM-DD
     */
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
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
     * 获取登录账号；用于登录账号，默认手机号，不可以更改
     *
     * @return login_ID - 登录账号；用于登录账号，默认手机号，不可以更改
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * 设置登录账号；用于登录账号，默认手机号，不可以更改
     *
     * @param loginId 登录账号；用于登录账号，默认手机号，不可以更改
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId == null ? null : loginId.trim();
    }

    /**
     * 获取登录密码
     *
     * @return login_passworld - 登录密码
     */
    public String getLoginPassworld() {
        return loginPassworld;
    }

    /**
     * 设置登录密码
     *
     * @param loginPassworld 登录密码
     */
    public void setLoginPassworld(String loginPassworld) {
        this.loginPassworld = loginPassworld == null ? null : loginPassworld.trim();
    }

    /**
     * 获取支付密码
     *
     * @return pay_passworld - 支付密码
     */
    public String getPayPassworld() {
        return payPassworld;
    }

    /**
     * 设置支付密码
     *
     * @param payPassworld 支付密码
     */
    public void setPayPassworld(String payPassworld) {
        this.payPassworld = payPassworld == null ? null : payPassworld.trim();
    }

    /**
     * 获取是否冻结；0否 1是 
     *
     * @return frozen - 是否冻结；0否 1是 
     */
    public Boolean getFrozen() {
        return frozen;
    }

    /**
     * 设置是否冻结；0否 1是 
     *
     * @param frozen 是否冻结；0否 1是 
     */
    public void setFrozen(Boolean frozen) {
        this.frozen = frozen;
    }

    /**
     * 获取解封时间
     *
     * @return unfreezing_time - 解封时间
     */
    public Date getUnfreezingTime() {
        return unfreezingTime;
    }

    /**
     * 设置解封时间
     *
     * @param unfreezingTime 解封时间
     */
    public void setUnfreezingTime(Date unfreezingTime) {
        this.unfreezingTime = unfreezingTime;
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

	@Override
	public String toString() {
		return "TUser [id=" + id + ", name=" + name + ", realName=" + realName + ", userType=" + userType + ", loginId="
				+ loginId + ", frozen=" + frozen + ", unfreezingTime=" + unfreezingTime + ", deleted=" + deleted + "]";
	}
    
    
}