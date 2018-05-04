package com.zhuwenshen.model.custom.merchant;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuwenshen.annotation.PicUrl;

public class QueryEvaluation {
    private String id;   

    /**
     * 评价类型
     */
    private Integer evaluationType;

    /**
     * 评分
     */
    private Integer score;

    /**
     * 用户评价
     */
    private String userEvaluation;

    /**
     * 评价图片1
     */
    private String evaluationImg1Url;

    /**
     * 评价图片2
     */
    private String evaluationImg2Url;

    /**
     * 评价图片3
     */   
    private String evaluationImg3Url;

    /**
     * 用户是否删除
     */    
    private Boolean userDeleted;

    /**
     * 评价时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date userTime;

    /**
     * 商家回复评价
     */
    private String merchantReply;

    /**
     * 商家回复评价图片1
     */
    private String replyImg1Url;

    /**
     * 商家回复评价图片2
     */
    private String replyImg2Url;

    /**
     * 商家回复评价图片3
     */
    private String replyImg3Url;

    /**
     * 商家是否删除
     */
    private Boolean merchantDeleted;

    /**
     * 商家回复评价时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date replyTime;
  
    private String orderInforId;
    /**
     * 商品数量
     */
    private Integer num;

    /**
     * 单价
     */
    private BigDecimal price;    
    
    /**
     * 具体分类信息
     */
    private String classDetails;
    
    /**
     * 订单id
     */
    private String orderId;
    
    private String merchantId;

    /**
     * 商品种类id
     */
    private String priceId;

    private String goodsId;
    private String goodsName;

    @PicUrl
    private String goodsImg;
    

   
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
     * @return order_infor_id
     */
    public String getOrderInforId() {
        return orderInforId;
    }

    /**
     * @param orderInforId
     */
    public void setOrderInforId(String orderInforId) {
        this.orderInforId = orderInforId == null ? null : orderInforId.trim();
    }

    /**
     * 获取评价类型
     *
     * @return evaluation_type - 评价类型
     */
    public Integer getEvaluationType() {
        return evaluationType;
    }

    /**
     * 设置评价类型
     *
     * @param evaluationType 评价类型
     */
    public void setEvaluationType(Integer evaluationType) {
        this.evaluationType = evaluationType;
    }

    /**
     * 获取评分
     *
     * @return score - 评分
     */
    public Integer getScore() {
        return score;
    }

    /**
     * 设置评分
     *
     * @param score 评分
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * 获取用户评价
     *
     * @return user_evaluation - 用户评价
     */
    public String getUserEvaluation() {
        return userEvaluation;
    }

    /**
     * 设置用户评价
     *
     * @param userEvaluation 用户评价
     */
    public void setUserEvaluation(String userEvaluation) {
        this.userEvaluation = userEvaluation == null ? null : userEvaluation.trim();
    }

    /**
     * 获取评价图片1
     *
     * @return evaluation_img1_url - 评价图片1
     */
    public String getEvaluationImg1Url() {
        return evaluationImg1Url;
    }

    /**
     * 设置评价图片1
     *
     * @param evaluationImg1Url 评价图片1
     */
    public void setEvaluationImg1Url(String evaluationImg1Url) {
        this.evaluationImg1Url = evaluationImg1Url == null ? null : evaluationImg1Url.trim();
    }

    /**
     * 获取评价图片2
     *
     * @return evaluation_img2_url - 评价图片2
     */
    public String getEvaluationImg2Url() {
        return evaluationImg2Url;
    }

    /**
     * 设置评价图片2
     *
     * @param evaluationImg2Url 评价图片2
     */
    public void setEvaluationImg2Url(String evaluationImg2Url) {
        this.evaluationImg2Url = evaluationImg2Url == null ? null : evaluationImg2Url.trim();
    }

    /**
     * 获取评价图片3
     *
     * @return evaluation_img3_url - 评价图片3
     */
    public String getEvaluationImg3Url() {
        return evaluationImg3Url;
    }

    /**
     * 设置评价图片3
     *
     * @param evaluationImg3Url 评价图片3
     */
    public void setEvaluationImg3Url(String evaluationImg3Url) {
        this.evaluationImg3Url = evaluationImg3Url == null ? null : evaluationImg3Url.trim();
    }

    /**
     * 获取用户是否删除
     *
     * @return user_deleted - 用户是否删除
     */
    public Boolean getUserDeleted() {
        return userDeleted;
    }

    /**
     * 设置用户是否删除
     *
     * @param userDeleted 用户是否删除
     */
    public void setUserDeleted(Boolean userDeleted) {
        this.userDeleted = userDeleted;
    }

    /**
     * 获取评价时间
     *
     * @return user_time - 评价时间
     */
    public Date getUserTime() {
        return userTime;
    }

    /**
     * 设置评价时间
     *
     * @param userTime 评价时间
     */
    public void setUserTime(Date userTime) {
        this.userTime = userTime;
    }

    /**
     * 获取商家回复评价
     *
     * @return merchant_reply - 商家回复评价
     */
    public String getMerchantReply() {
        return merchantReply;
    }

    /**
     * 设置商家回复评价
     *
     * @param merchantReply 商家回复评价
     */
    public void setMerchantReply(String merchantReply) {
        this.merchantReply = merchantReply == null ? null : merchantReply.trim();
    }

    /**
     * 获取商家回复评价图片1
     *
     * @return reply_img1_url - 商家回复评价图片1
     */
    public String getReplyImg1Url() {
        return replyImg1Url;
    }

    /**
     * 设置商家回复评价图片1
     *
     * @param replyImg1Url 商家回复评价图片1
     */
    public void setReplyImg1Url(String replyImg1Url) {
        this.replyImg1Url = replyImg1Url == null ? null : replyImg1Url.trim();
    }

    /**
     * 获取商家回复评价图片2
     *
     * @return reply_img2_url - 商家回复评价图片2
     */
    public String getReplyImg2Url() {
        return replyImg2Url;
    }

    /**
     * 设置商家回复评价图片2
     *
     * @param replyImg2Url 商家回复评价图片2
     */
    public void setReplyImg2Url(String replyImg2Url) {
        this.replyImg2Url = replyImg2Url == null ? null : replyImg2Url.trim();
    }

    /**
     * 获取商家回复评价图片3
     *
     * @return reply_img3_url - 商家回复评价图片3
     */
    public String getReplyImg3Url() {
        return replyImg3Url;
    }

    /**
     * 设置商家回复评价图片3
     *
     * @param replyImg3Url 商家回复评价图片3
     */
    public void setReplyImg3Url(String replyImg3Url) {
        this.replyImg3Url = replyImg3Url == null ? null : replyImg3Url.trim();
    }

    /**
     * 获取商家是否删除
     *
     * @return merchant_deleted - 商家是否删除
     */
    public Boolean getMerchantDeleted() {
        return merchantDeleted;
    }

    /**
     * 设置商家是否删除
     *
     * @param merchantDeleted 商家是否删除
     */
    public void setMerchantDeleted(Boolean merchantDeleted) {
        this.merchantDeleted = merchantDeleted;
    }

    /**
     * 获取商家回复评价时间
     *
     * @return reply_time - 商家回复评价时间
     */
    public Date getReplyTime() {
        return replyTime;
    }

    /**
     * 设置商家回复评价时间
     *
     * @param replyTime 商家回复评价时间
     */
    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getClassDetails() {
		return classDetails;
	}

	public void setClassDetails(String classDetails) {
		this.classDetails = classDetails;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getPriceId() {
		return priceId;
	}

	public void setPriceId(String priceId) {
		this.priceId = priceId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsImg() {
		return goodsImg;
	}

	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}  
    
}