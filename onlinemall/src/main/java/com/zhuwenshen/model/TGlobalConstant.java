package com.zhuwenshen.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_global_constant")
public class TGlobalConstant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 分类
     */
    private String kind;

    /**
     * 分类名称
     */
    @Column(name = "kind_name")
    private String kindName;

    /**
     * 变量（英文）
     */
    private String name;

    /**
     * 变量名称（中文）
     */
    @Column(name = "name_cn")
    private String nameCn;

    /**
     * 值
     */
    @Column(name = "value_1")
    private String value1;

    /**
     * 有效的;0否 1是
     */
    private Boolean useful;

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
     * 获取分类
     *
     * @return kind - 分类
     */
    public String getKind() {
        return kind;
    }

    /**
     * 设置分类
     *
     * @param kind 分类
     */
    public void setKind(String kind) {
        this.kind = kind == null ? null : kind.trim();
    }

    /**
     * 获取分类名称
     *
     * @return kind_name - 分类名称
     */
    public String getKindName() {
        return kindName;
    }

    /**
     * 设置分类名称
     *
     * @param kindName 分类名称
     */
    public void setKindName(String kindName) {
        this.kindName = kindName == null ? null : kindName.trim();
    }

    /**
     * 获取变量（英文）
     *
     * @return name - 变量（英文）
     */
    public String getName() {
        return name;
    }

    /**
     * 设置变量（英文）
     *
     * @param name 变量（英文）
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取变量名称（中文）
     *
     * @return name_cn - 变量名称（中文）
     */
    public String getNameCn() {
        return nameCn;
    }

    /**
     * 设置变量名称（中文）
     *
     * @param nameCn 变量名称（中文）
     */
    public void setNameCn(String nameCn) {
        this.nameCn = nameCn == null ? null : nameCn.trim();
    }

    /**
     * 获取值
     *
     * @return value_1 - 值
     */
    public String getValue1() {
        return value1;
    }

    /**
     * 设置值
     *
     * @param value1 值
     */
    public void setValue1(String value1) {
        this.value1 = value1 == null ? null : value1.trim();
    }

    /**
     * 获取有效的;0否 1是
     *
     * @return useful - 有效的;0否 1是
     */
    public Boolean getUseful() {
        return useful;
    }

    /**
     * 设置有效的;0否 1是
     *
     * @param useful 有效的;0否 1是
     */
    public void setUseful(Boolean useful) {
        this.useful = useful;
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