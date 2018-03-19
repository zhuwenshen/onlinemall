package com.zhuwenshen.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`t_ evaluation`")
public class TEvaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "order_id")
    private String orderId;

    /**
     * 评价类型
     */
    @Column(name = "evaluation_type")
    private Integer evaluationType;

    /**
     * 用户评价
     */
    @Column(name = "user_evaluation")
    private String userEvaluation;

    /**
     * 评价图片1
     */
    @Column(name = "`evaluation _img1_url`")
    private String evaluationImg1Url;

    /**
     * 评价图片2
     */
    @Column(name = "`evaluation _img2_url`")
    private String evaluationImg2Url;

    /**
     * 评价图片3
     */
    @Column(name = "`evaluation _img3_url`")
    private String evaluationImg3Url;

    /**
     * 用户是否删除
     */
    @Column(name = "user_deleted")
    private Boolean userDeleted;

    /**
     * 评价时间
     */
    @Column(name = "user_time")
    private Date userTime;

    /**
     * 商家回复评价
     */
    @Column(name = "merchant_reply")
    private String merchantReply;

    /**
     * 商家回复评价图片1
     */
    @Column(name = "reply_img1_url")
    private String replyImg1Url;

    /**
     * 商家回复评价图片2
     */
    @Column(name = "reply_img2_url")
    private String replyImg2Url;

    /**
     * 商家回复评价图片3
     */
    @Column(name = "reply_img3_url")
    private String replyImg3Url;

    /**
     * 商家是否删除
     */
    @Column(name = "merchant_deleted")
    private Boolean merchantDeleted;

    /**
     * 商家回复评价时间
     */
    @Column(name = "reply_time")
    private Date replyTime;

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
     * @return order_id
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
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
     * @return evaluation _img1_url - 评价图片1
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
     * @return evaluation _img2_url - 评价图片2
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
     * @return evaluation _img3_url - 评价图片3
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