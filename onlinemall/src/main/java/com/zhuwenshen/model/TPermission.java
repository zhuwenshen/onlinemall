package com.zhuwenshen.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_permission")
public class TPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 等级；每两位一个等级，最多四个等级(超级管理员 11000000 管理员 11010000 商家掌柜12000000 店员 12020000 用户 12010000)

     */
    private Integer level;

    /**
     * 名称
     */
    private String name;

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
     * 获取等级；每两位一个等级，最多四个等级(超级管理员 11000000 管理员 11010000 商家掌柜12000000 店员 12020000 用户 12010000)

     *
     * @return level - 等级；每两位一个等级，最多四个等级(超级管理员 11000000 管理员 11010000 商家掌柜12000000 店员 12020000 用户 12010000)

     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置等级；每两位一个等级，最多四个等级(超级管理员 11000000 管理员 11010000 商家掌柜12000000 店员 12020000 用户 12010000)

     *
     * @param level 等级；每两位一个等级，最多四个等级(超级管理员 11000000 管理员 11010000 商家掌柜12000000 店员 12020000 用户 12010000)

     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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