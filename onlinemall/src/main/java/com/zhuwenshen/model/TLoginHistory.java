package com.zhuwenshen.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_login_history")
public class TLoginHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "user_id")
    private String userId;

    /**
     * 客户端类型；1移动安卓端 2移动IOS端，3网页，4微信小程序
     */
    @Column(name = "client_type")
    private Integer clientType;

    /**
     * 登录时间
     */
    @Column(name = "login_time")
    private Date loginTime;

    /**
     * 登录验证
     */
    private String token;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 定位位置
     */
    private String location;

    /**
     * 有效的，有用的
     */
    private Boolean useful;

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
     * 获取客户端类型；1移动安卓端 2移动IOS端，3网页，4微信小程序
     *
     * @return client_type - 客户端类型；1移动安卓端 2移动IOS端，3网页，4微信小程序
     */
    public Integer getClientType() {
        return clientType;
    }

    /**
     * 设置客户端类型；1移动安卓端 2移动IOS端，3网页，4微信小程序
     *
     * @param clientType 客户端类型；1移动安卓端 2移动IOS端，3网页，4微信小程序
     */
    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

    /**
     * 获取登录时间
     *
     * @return login_time - 登录时间
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 设置登录时间
     *
     * @param loginTime 登录时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * 获取登录验证
     *
     * @return token - 登录验证
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置登录验证
     *
     * @param token 登录验证
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * 获取ip地址
     *
     * @return ip - ip地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置ip地址
     *
     * @param ip ip地址
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * 获取定位位置
     *
     * @return location - 定位位置
     */
    public String getLocation() {
        return location;
    }

    /**
     * 设置定位位置
     *
     * @param location 定位位置
     */
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    /**
     * 获取有效的，有用的
     *
     * @return useful - 有效的，有用的
     */
    public Boolean getUseful() {
        return useful;
    }

    /**
     * 设置有效的，有用的
     *
     * @param useful 有效的，有用的
     */
    public void setUseful(Boolean useful) {
        this.useful = useful;
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