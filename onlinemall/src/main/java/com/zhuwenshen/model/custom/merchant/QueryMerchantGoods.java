package com.zhuwenshen.model.custom.merchant;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuwenshen.annotation.PicUrl;

public class QueryMerchantGoods {
	
    private String id;

    /**
     * 商家id
     */
    private String merchantInformationId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 商品描述图片1
     */
    @PicUrl
    private String descriptionImg1Url;

    /**
     * 商品描述图片2
     */
    @PicUrl
    private String descriptionImg2Url;

    /**
     * 商品描述图片3
     */
    @PicUrl
    private String descriptionImg3Url;

    /**
     * 商品描述图片4
     */
    @PicUrl
    private String descriptionImg4url;

    /**
     * 上架时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date shelveTime;

    /**
     * 是否被管理员下架;0否 1是
     */
    private Boolean unshelveByAdmin;

    /**
     * 是否下架;0否 1是
     */
    private Boolean unshelve;

    /**
     * 下架时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date unshelveTime;

    /**
     * 是否删除；0否 1是
     */
    private Boolean deleted;

    /**
     * 创建时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    
    
    private List<?> labelList;
    
    private String producer;
    
    private String specification;
    
    private List<String> detailImgUrl;
    
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
     * 获取商家id
     *
     * @return merchant_information_id - 商家id
     */
    public String getMerchantInformationId() {
        return merchantInformationId;
    }

    /**
     * 设置商家id
     *
     * @param merchantInformationId 商家id
     */
    public void setMerchantInformationId(String merchantInformationId) {
        this.merchantInformationId = merchantInformationId == null ? null : merchantInformationId.trim();
    }

    /**
     * 获取商品名称
     *
     * @return name - 商品名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置商品名称
     *
     * @param name 商品名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取商品描述
     *
     * @return description - 商品描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置商品描述
     *
     * @param description 商品描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取商品描述图片1
     *
     * @return description_img1_url - 商品描述图片1
     */
    public String getDescriptionImg1Url() {
        return descriptionImg1Url;
    }

    /**
     * 设置商品描述图片1
     *
     * @param descriptionImg1Url 商品描述图片1
     */
    public void setDescriptionImg1Url(String descriptionImg1Url) {
        this.descriptionImg1Url = descriptionImg1Url == null ? null : descriptionImg1Url.trim();
    }

    /**
     * 获取商品描述图片2
     *
     * @return description_img2_url - 商品描述图片2
     */
    public String getDescriptionImg2Url() {
        return descriptionImg2Url;
    }

    /**
     * 设置商品描述图片2
     *
     * @param descriptionImg2Url 商品描述图片2
     */
    public void setDescriptionImg2Url(String descriptionImg2Url) {
        this.descriptionImg2Url = descriptionImg2Url == null ? null : descriptionImg2Url.trim();
    }

    /**
     * 获取商品描述图片3
     *
     * @return description_img3_url - 商品描述图片3
     */
    public String getDescriptionImg3Url() {
        return descriptionImg3Url;
    }

    /**
     * 设置商品描述图片3
     *
     * @param descriptionImg3Url 商品描述图片3
     */
    public void setDescriptionImg3Url(String descriptionImg3Url) {
        this.descriptionImg3Url = descriptionImg3Url == null ? null : descriptionImg3Url.trim();
    }

    /**
     * 获取商品描述图片4
     *
     * @return description_img4url - 商品描述图片4
     */
    public String getDescriptionImg4url() {
        return descriptionImg4url;
    }

    /**
     * 设置商品描述图片4
     *
     * @param descriptionImg4url 商品描述图片4
     */
    public void setDescriptionImg4url(String descriptionImg4url) {
        this.descriptionImg4url = descriptionImg4url == null ? null : descriptionImg4url.trim();
    }

    /**
     * 获取上架时间
     *
     * @return shelve _time - 上架时间
     */
    public Date getShelveTime() {
        return shelveTime;
    }

    /**
     * 设置上架时间
     *
     * @param shelveTime 上架时间
     */
    public void setShelveTime(Date shelveTime) {
        this.shelveTime = shelveTime;
    }

    /**
     * 获取是否被管理员下架;0否 1是
     *
     * @return unshelve_by_admin - 是否被管理员下架;0否 1是
     */
    public Boolean getUnshelveByAdmin() {
        return unshelveByAdmin;
    }

    /**
     * 设置是否被管理员下架;0否 1是
     *
     * @param unshelveByAdmin 是否被管理员下架;0否 1是
     */
    public void setUnshelveByAdmin(Boolean unshelveByAdmin) {
        this.unshelveByAdmin = unshelveByAdmin;
    }

    /**
     * 获取是否下架;0否 1是
     *
     * @return unshelve - 是否下架;0否 1是
     */
    public Boolean getUnshelve() {
        return unshelve;
    }

    /**
     * 设置是否下架;0否 1是
     *
     * @param unshelve 是否下架;0否 1是
     */
    public void setUnshelve(Boolean unshelve) {
        this.unshelve = unshelve;
    }

    /**
     * 获取下架时间
     *
     * @return unshelve_time - 下架时间
     */
    public Date getUnshelveTime() {
        return unshelveTime;
    }

    /**
     * 设置下架时间
     *
     * @param unshelveTime 下架时间
     */
    public void setUnshelveTime(Date unshelveTime) {
        this.unshelveTime = unshelveTime;
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

	public List<?> getLabelList() {
		return labelList;
	}

	public void setLabelList(List<?> labelList) {
		this.labelList = labelList;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public List<String> getDetailImgUrl() {
		return detailImgUrl;
	}

	public void setDetailImgUrl(List<String> detailImgUrl) {
		this.detailImgUrl = detailImgUrl;
	} 
}