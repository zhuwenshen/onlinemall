package com.zhuwenshen.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_goods_sort_detail")
public class TGoodsSortDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 商家分类id
     */
    @Column(name = "goods_sort_id")
    private String goodsSortId;

    /**
     * 分类名称组合
     */
    @Column(name = "sort_detail_name")
    private String sortDetailName;

    /**
     * 数量
     */
    private Integer num;

    /**
     * 单价
     */
    private BigDecimal price;

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
     * 获取商家分类id
     *
     * @return goods_sort_id - 商家分类id
     */
    public String getGoodsSortId() {
        return goodsSortId;
    }

    /**
     * 设置商家分类id
     *
     * @param goodsSortId 商家分类id
     */
    public void setGoodsSortId(String goodsSortId) {
        this.goodsSortId = goodsSortId == null ? null : goodsSortId.trim();
    }

    /**
     * 获取分类名称组合
     *
     * @return sort_detail_name - 分类名称组合
     */
    public String getSortDetailName() {
        return sortDetailName;
    }

    /**
     * 设置分类名称组合
     *
     * @param sortDetailName 分类名称组合
     */
    public void setSortDetailName(String sortDetailName) {
        this.sortDetailName = sortDetailName == null ? null : sortDetailName.trim();
    }

    /**
     * 获取数量
     *
     * @return num - 数量
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置数量
     *
     * @param num 数量
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 获取单价
     *
     * @return price - 单价
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置单价
     *
     * @param price 单价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
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