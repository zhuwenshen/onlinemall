package com.zhuwenshen.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_goods_sort")
public class TGoodsSort {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 商品id
     */
    @Column(name = "goods_id")
    private String goodsId;

    /**
     * 分类类别
     */
    @Column(name = "sort_name")
    private String sortName;

    /**
     * 分类信息（用空格隔开）
     */
    @Column(name = "sort_detail")
    private String sortDetail;

    /**
     * 分类排序
     */
    @Column(name = "sort_num")
    private Integer sortNum;

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
     * 获取商品id
     *
     * @return goods_id - 商品id
     */
    public String getGoodsId() {
        return goodsId;
    }

    /**
     * 设置商品id
     *
     * @param goodsId 商品id
     */
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    /**
     * 获取分类类别
     *
     * @return sort_name - 分类类别
     */
    public String getSortName() {
        return sortName;
    }

    /**
     * 设置分类类别
     *
     * @param sortName 分类类别
     */
    public void setSortName(String sortName) {
        this.sortName = sortName == null ? null : sortName.trim();
    }

    /**
     * 获取分类信息（用空格隔开）
     *
     * @return sort_detail - 分类信息（用空格隔开）
     */
    public String getSortDetail() {
        return sortDetail;
    }

    /**
     * 设置分类信息（用空格隔开）
     *
     * @param sortDetail 分类信息（用空格隔开）
     */
    public void setSortDetail(String sortDetail) {
        this.sortDetail = sortDetail == null ? null : sortDetail.trim();
    }

    /**
     * 获取分类排序
     *
     * @return sort_num - 分类排序
     */
    public Integer getSortNum() {
        return sortNum;
    }

    /**
     * 设置分类排序
     *
     * @param sortNum 分类排序
     */
    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
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