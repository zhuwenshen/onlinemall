package com.zhuwenshen.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_goods_detail")
public class TGoodsDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 商品id
     */
    @Column(name = "goods_id")
    private String goodsId;

    /**
     * 厂家
     */
    private String producer;

    /**
     * 规格
     */
    private String specification;

    /**
     * 详细描述
     */
    private String detail;

    /**
     * 描述图片1
     */
    @Column(name = "description_img1_url")
    private String descriptionImg1Url;

    /**
     * 描述图片2
     */
    @Column(name = "description_img2_url")
    private String descriptionImg2Url;

    /**
     * 描述图片3
     */
    @Column(name = "description_img3_url")
    private String descriptionImg3Url;

    /**
     * 描述图片4
     */
    @Column(name = "description_img4_url")
    private String descriptionImg4Url;

    /**
     * 描述图片5
     */
    @Column(name = "description_img5_url")
    private String descriptionImg5Url;

    /**
     * 描述图片6
     */
    @Column(name = "description_img6_url")
    private String descriptionImg6Url;

    /**
     * 描述图片7
     */
    @Column(name = "description_img7_url")
    private String descriptionImg7Url;

    /**
     * 描述图片8
     */
    @Column(name = "description_img8_url")
    private String descriptionImg8Url;

    /**
     * 描述图片9
     */
    @Column(name = "description_img9_url")
    private String descriptionImg9Url;

    /**
     * 描述图片10
     */
    @Column(name = "description_img10_url")
    private String descriptionImg10Url;

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
     * 获取厂家
     *
     * @return producer - 厂家
     */
    public String getProducer() {
        return producer;
    }

    /**
     * 设置厂家
     *
     * @param producer 厂家
     */
    public void setProducer(String producer) {
        this.producer = producer == null ? null : producer.trim();
    }

    /**
     * 获取规格
     *
     * @return specification - 规格
     */
    public String getSpecification() {
        return specification;
    }

    /**
     * 设置规格
     *
     * @param specification 规格
     */
    public void setSpecification(String specification) {
        this.specification = specification == null ? null : specification.trim();
    }

    /**
     * 获取详细描述
     *
     * @return detail - 详细描述
     */
    public String getDetail() {
        return detail;
    }

    /**
     * 设置详细描述
     *
     * @param detail 详细描述
     */
    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    /**
     * 获取描述图片1
     *
     * @return description_img1_url - 描述图片1
     */
    public String getDescriptionImg1Url() {
        return descriptionImg1Url;
    }

    /**
     * 设置描述图片1
     *
     * @param descriptionImg1Url 描述图片1
     */
    public void setDescriptionImg1Url(String descriptionImg1Url) {
        this.descriptionImg1Url = descriptionImg1Url == null ? null : descriptionImg1Url.trim();
    }

    /**
     * 获取描述图片2
     *
     * @return description_img2_url - 描述图片2
     */
    public String getDescriptionImg2Url() {
        return descriptionImg2Url;
    }

    /**
     * 设置描述图片2
     *
     * @param descriptionImg2Url 描述图片2
     */
    public void setDescriptionImg2Url(String descriptionImg2Url) {
        this.descriptionImg2Url = descriptionImg2Url == null ? null : descriptionImg2Url.trim();
    }

    /**
     * 获取描述图片3
     *
     * @return description_img3_url - 描述图片3
     */
    public String getDescriptionImg3Url() {
        return descriptionImg3Url;
    }

    /**
     * 设置描述图片3
     *
     * @param descriptionImg3Url 描述图片3
     */
    public void setDescriptionImg3Url(String descriptionImg3Url) {
        this.descriptionImg3Url = descriptionImg3Url == null ? null : descriptionImg3Url.trim();
    }

    /**
     * 获取描述图片4
     *
     * @return description_img4_url - 描述图片4
     */
    public String getDescriptionImg4Url() {
        return descriptionImg4Url;
    }

    /**
     * 设置描述图片4
     *
     * @param descriptionImg4Url 描述图片4
     */
    public void setDescriptionImg4Url(String descriptionImg4Url) {
        this.descriptionImg4Url = descriptionImg4Url == null ? null : descriptionImg4Url.trim();
    }

    /**
     * 获取描述图片5
     *
     * @return description_img5_url - 描述图片5
     */
    public String getDescriptionImg5Url() {
        return descriptionImg5Url;
    }

    /**
     * 设置描述图片5
     *
     * @param descriptionImg5Url 描述图片5
     */
    public void setDescriptionImg5Url(String descriptionImg5Url) {
        this.descriptionImg5Url = descriptionImg5Url == null ? null : descriptionImg5Url.trim();
    }

    /**
     * 获取描述图片6
     *
     * @return description_img6_url - 描述图片6
     */
    public String getDescriptionImg6Url() {
        return descriptionImg6Url;
    }

    /**
     * 设置描述图片6
     *
     * @param descriptionImg6Url 描述图片6
     */
    public void setDescriptionImg6Url(String descriptionImg6Url) {
        this.descriptionImg6Url = descriptionImg6Url == null ? null : descriptionImg6Url.trim();
    }

    /**
     * 获取描述图片7
     *
     * @return description_img7_url - 描述图片7
     */
    public String getDescriptionImg7Url() {
        return descriptionImg7Url;
    }

    /**
     * 设置描述图片7
     *
     * @param descriptionImg7Url 描述图片7
     */
    public void setDescriptionImg7Url(String descriptionImg7Url) {
        this.descriptionImg7Url = descriptionImg7Url == null ? null : descriptionImg7Url.trim();
    }

    /**
     * 获取描述图片8
     *
     * @return description_img8_url - 描述图片8
     */
    public String getDescriptionImg8Url() {
        return descriptionImg8Url;
    }

    /**
     * 设置描述图片8
     *
     * @param descriptionImg8Url 描述图片8
     */
    public void setDescriptionImg8Url(String descriptionImg8Url) {
        this.descriptionImg8Url = descriptionImg8Url == null ? null : descriptionImg8Url.trim();
    }

    /**
     * 获取描述图片9
     *
     * @return description_img9_url - 描述图片9
     */
    public String getDescriptionImg9Url() {
        return descriptionImg9Url;
    }

    /**
     * 设置描述图片9
     *
     * @param descriptionImg9Url 描述图片9
     */
    public void setDescriptionImg9Url(String descriptionImg9Url) {
        this.descriptionImg9Url = descriptionImg9Url == null ? null : descriptionImg9Url.trim();
    }

    /**
     * 获取描述图片10
     *
     * @return description_img10_url - 描述图片10
     */
    public String getDescriptionImg10Url() {
        return descriptionImg10Url;
    }

    /**
     * 设置描述图片10
     *
     * @param descriptionImg10Url 描述图片10
     */
    public void setDescriptionImg10Url(String descriptionImg10Url) {
        this.descriptionImg10Url = descriptionImg10Url == null ? null : descriptionImg10Url.trim();
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