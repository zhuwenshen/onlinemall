package com.zhuwenshen.model.custom.merchant;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class QueryMerchantGoodsParam {
	
	private Integer pageNum = 1;
	private Integer pageSize = 10;
	
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
    private String descriptionImg1Url;

    /**
     * 商品描述图片2
     */
    private String descriptionImg2Url;

    /**
     * 商品描述图片3
     */
    private String descriptionImg3Url;

    /**
     * 商品描述图片4
     */
    private String descriptionImg4url;

    /**
     * 上架时间
     */    
    private Date shelveTime;
    private Date shelveTimeStart;
    private Date shelveTimeEnd;

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
    private Date unshelveTime;
    private Date unshelveTimeStart;
    private Date unshelveTimeEnd;
    
    /**
     * 创建时间
     */
    private Date createTimeStart;
    private Date createTimeEnd;  
    
    private String[] label;
    
    private List<String> labelList;
    
    
    /**
     * 厂家
     */
    private String producer;

    /**
     * 规格
     */
    private String specification;
    
    private String[] detailImgUrl;
    
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
    

	public Date getShelveTimeStart() {
		return shelveTimeStart;
	}

	public void setShelveTimeStart(Date shelveTimeStart) {
		this.shelveTimeStart = shelveTimeStart;
	}

	public Date getShelveTimeEnd() {
		return shelveTimeEnd;
	}

	public void setShelveTimeEnd(Date shelveTimeEnd) {
		this.shelveTimeEnd = shelveTimeEnd;
	}

	public Date getUnshelveTimeStart() {
		return unshelveTimeStart;
	}

	public void setUnshelveTimeStart(Date unshelveTimeStart) {
		this.unshelveTimeStart = unshelveTimeStart;
	}

	public Date getUnshelveTimeEnd() {
		return unshelveTimeEnd;
	}

	public void setUnshelveTimeEnd(Date unshelveTimeEnd) {
		this.unshelveTimeEnd = unshelveTimeEnd;
	}

	public Date getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<String> getLabelList() {
		return labelList;
	}

	public void setLabelList(List<String> labelList) {
		this.labelList = labelList;
	}

	public Date getShelveTime() {
		return shelveTime;
	}

	public void setShelveTime(Date shelveTime) {
		this.shelveTime = shelveTime;
	}

	public Date getUnshelveTime() {
		return unshelveTime;
	}

	public void setUnshelveTime(Date unshelveTime) {
		this.unshelveTime = unshelveTime;
	}

	public String[] getLabel() {
		return label;
	}

	public void setLabel(String[] label) {
		this.label = label;
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

	public String[] getDetailImgUrl() {
		return detailImgUrl;
	}

	public void setDetailImgUrl(String[] detailImgUrl) {
		this.detailImgUrl = detailImgUrl;
	}

	@Override
	public String toString() {
		return "QueryMerchantGoodsParam [pageNum=" + pageNum + ", pageSize=" + pageSize + ", id=" + id
				+ ", merchantInformationId=" + merchantInformationId + ", name=" + name + ", description=" + description
				+ ", descriptionImg1Url=" + descriptionImg1Url + ", descriptionImg2Url=" + descriptionImg2Url
				+ ", descriptionImg3Url=" + descriptionImg3Url + ", descriptionImg4url=" + descriptionImg4url
				+ ", shelveTime=" + shelveTime + ", shelveTimeStart=" + shelveTimeStart + ", shelveTimeEnd="
				+ shelveTimeEnd + ", unshelveByAdmin=" + unshelveByAdmin + ", unshelve=" + unshelve + ", unshelveTime="
				+ unshelveTime + ", unshelveTimeStart=" + unshelveTimeStart + ", unshelveTimeEnd=" + unshelveTimeEnd
				+ ", createTimeStart=" + createTimeStart + ", createTimeEnd=" + createTimeEnd + ", label="
				+ Arrays.toString(label) + ", labelList=" + labelList + ", producer=" + producer + ", specification="
				+ specification + ", detailImgUrl=" + Arrays.toString(detailImgUrl) + "]";
	} 
	
	
    
}