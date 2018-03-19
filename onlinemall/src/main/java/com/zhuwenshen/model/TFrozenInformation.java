package com.zhuwenshen.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_frozen_information")
public class TFrozenInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 被冻结用户id
     */
    @Column(name = "frozen_user_id")
    private String frozenUserId;

    /**
     * 操作管理员id
     */
    @Column(name = "operating_user_id")
    private String operatingUserId;

    /**
     * 冻结时间
     */
    @Column(name = "frozen_time")
    private Date frozenTime;

    /**
     * 冻结理由
     */
    @Column(name = "frozen_reason")
    private String frozenReason;

    /**
     * 解封时间
     */
    @Column(name = "unfreezing_time")
    private Date unfreezingTime;

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
     * 获取被冻结用户id
     *
     * @return frozen_user_id - 被冻结用户id
     */
    public String getFrozenUserId() {
        return frozenUserId;
    }

    /**
     * 设置被冻结用户id
     *
     * @param frozenUserId 被冻结用户id
     */
    public void setFrozenUserId(String frozenUserId) {
        this.frozenUserId = frozenUserId == null ? null : frozenUserId.trim();
    }

    /**
     * 获取操作管理员id
     *
     * @return operating_user_id - 操作管理员id
     */
    public String getOperatingUserId() {
        return operatingUserId;
    }

    /**
     * 设置操作管理员id
     *
     * @param operatingUserId 操作管理员id
     */
    public void setOperatingUserId(String operatingUserId) {
        this.operatingUserId = operatingUserId == null ? null : operatingUserId.trim();
    }

    /**
     * 获取冻结时间
     *
     * @return frozen_time - 冻结时间
     */
    public Date getFrozenTime() {
        return frozenTime;
    }

    /**
     * 设置冻结时间
     *
     * @param frozenTime 冻结时间
     */
    public void setFrozenTime(Date frozenTime) {
        this.frozenTime = frozenTime;
    }

    /**
     * 获取冻结理由
     *
     * @return frozen_reason - 冻结理由
     */
    public String getFrozenReason() {
        return frozenReason;
    }

    /**
     * 设置冻结理由
     *
     * @param frozenReason 冻结理由
     */
    public void setFrozenReason(String frozenReason) {
        this.frozenReason = frozenReason == null ? null : frozenReason.trim();
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