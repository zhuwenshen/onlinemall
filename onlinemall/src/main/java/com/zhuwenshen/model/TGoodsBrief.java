package com.zhuwenshen.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_goods_brief")
public class TGoodsBrief {
    /**
     * goodsId
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 商家id
     */
    @Column(name = "merchant_id")
    private String merchantId;

    @Column(name = "goods_id")
    private String goodsId;

    /**
     * 商家名
     */
    @Column(name = "merchant_name")
    private String merchantName;

    @Column(name = "goods_name")
    private String goodsName;

    /**
     * 商品标签
     */
    @Column(name = "goods_label")
    private String goodsLabel;

    /**
     * 商品描述
     */
    @Column(name = "goods_des")
    private String goodsDes;

    /**
     * 商品图
     */
    @Column(name = "goods_img")
    private String goodsImg;

    /**
     * 最小商品价格
     */
    private BigDecimal price;

    /**
     * 最近统计时间
     */
    @Column(name = "latest_time")
    private Date latestTime;

    /**
     * 成交订单数
     */
    @Column(name = "order_sold_num")
    private Integer orderSoldNum;

    @Column(name = "order_success_num")
    private Integer orderSuccessNum;

    @Column(name = "order_fail_num")
    private Integer orderFailNum;

    /**
     * 评价评分
     */
    @Column(name = "avg_score")
    private Double avgScore;

    /**
     * 售出数量
     */
    @Column(name = "sold_num")
    private Integer soldNum;

    /**
     * 成功交易数
     */
    @Column(name = "success_num")
    private Integer successNum;

    /**
     * 失败交易数量
     */
    @Column(name = "fail_num")
    private Integer failNum;

    /**
     * 最近一个月的时间（即30天前的时刻）
     */
    @Column(name = "last_mon_time")
    private Date lastMonTime;

    /**
     * 月售数量
     */
    @Column(name = "mon_sold_num")
    private Integer monSoldNum;

    /**
     * 加权评分
     */
    @Column(name = "weight_score")
    private Double weightScore;

    /**
     * 上架时间
     */
    @Column(name = "shelve_time")
    private Date shelveTime;

    /**
     * 获取goodsId
     *
     * @return id - goodsId
     */
    public String getId() {
        return id;
    }

    /**
     * 设置goodsId
     *
     * @param id goodsId
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取商家id
     *
     * @return merchant_id - 商家id
     */
    public String getMerchantId() {
        return merchantId;
    }

    /**
     * 设置商家id
     *
     * @param merchantId 商家id
     */
    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId == null ? null : merchantId.trim();
    }

    /**
     * @return goods_id
     */
    public String getGoodsId() {
        return goodsId;
    }

    /**
     * @param goodsId
     */
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    /**
     * 获取商家名
     *
     * @return merchant_name - 商家名
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * 设置商家名
     *
     * @param merchantName 商家名
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    /**
     * @return goods_name
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * @param goodsName
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    /**
     * 获取商品标签
     *
     * @return goods_label - 商品标签
     */
    public String getGoodsLabel() {
        return goodsLabel;
    }

    /**
     * 设置商品标签
     *
     * @param goodsLabel 商品标签
     */
    public void setGoodsLabel(String goodsLabel) {
        this.goodsLabel = goodsLabel == null ? null : goodsLabel.trim();
    }

    /**
     * 获取商品描述
     *
     * @return goods_des - 商品描述
     */
    public String getGoodsDes() {
        return goodsDes;
    }

    /**
     * 设置商品描述
     *
     * @param goodsDes 商品描述
     */
    public void setGoodsDes(String goodsDes) {
        this.goodsDes = goodsDes == null ? null : goodsDes.trim();
    }

    /**
     * 获取商品图
     *
     * @return goods_img - 商品图
     */
    public String getGoodsImg() {
        return goodsImg;
    }

    /**
     * 设置商品图
     *
     * @param goodsImg 商品图
     */
    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg == null ? null : goodsImg.trim();
    }

    /**
     * 获取最小商品价格
     *
     * @return price - 最小商品价格
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置最小商品价格
     *
     * @param price 最小商品价格
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取最近统计时间
     *
     * @return latest_time - 最近统计时间
     */
    public Date getLatestTime() {
        return latestTime;
    }

    /**
     * 设置最近统计时间
     *
     * @param latestTime 最近统计时间
     */
    public void setLatestTime(Date latestTime) {
        this.latestTime = latestTime;
    }

    /**
     * 获取成交订单数
     *
     * @return order_sold_num - 成交订单数
     */
    public Integer getOrderSoldNum() {
        return orderSoldNum;
    }

    /**
     * 设置成交订单数
     *
     * @param orderSoldNum 成交订单数
     */
    public void setOrderSoldNum(Integer orderSoldNum) {
        this.orderSoldNum = orderSoldNum;
    }

    /**
     * @return order_success_num
     */
    public Integer getOrderSuccessNum() {
        return orderSuccessNum;
    }

    /**
     * @param orderSuccessNum
     */
    public void setOrderSuccessNum(Integer orderSuccessNum) {
        this.orderSuccessNum = orderSuccessNum;
    }

    /**
     * @return order_fail_num
     */
    public Integer getOrderFailNum() {
        return orderFailNum;
    }

    /**
     * @param orderFailNum
     */
    public void setOrderFailNum(Integer orderFailNum) {
        this.orderFailNum = orderFailNum;
    }

    /**
     * 获取评价评分
     *
     * @return avg_score - 评价评分
     */
    public Double getAvgScore() {
        return avgScore;
    }

    /**
     * 设置评价评分
     *
     * @param avgScore 评价评分
     */
    public void setAvgScore(Double avgScore) {
        this.avgScore = avgScore;
    }

    /**
     * 获取售出数量
     *
     * @return sold_num - 售出数量
     */
    public Integer getSoldNum() {
        return soldNum;
    }

    /**
     * 设置售出数量
     *
     * @param soldNum 售出数量
     */
    public void setSoldNum(Integer soldNum) {
        this.soldNum = soldNum;
    }

    /**
     * 获取成功交易数
     *
     * @return success_num - 成功交易数
     */
    public Integer getSuccessNum() {
        return successNum;
    }

    /**
     * 设置成功交易数
     *
     * @param successNum 成功交易数
     */
    public void setSuccessNum(Integer successNum) {
        this.successNum = successNum;
    }

    /**
     * 获取失败交易数量
     *
     * @return fail_num - 失败交易数量
     */
    public Integer getFailNum() {
        return failNum;
    }

    /**
     * 设置失败交易数量
     *
     * @param failNum 失败交易数量
     */
    public void setFailNum(Integer failNum) {
        this.failNum = failNum;
    }

    /**
     * 获取最近一个月的时间（即30天前的时刻）
     *
     * @return last_mon_time - 最近一个月的时间（即30天前的时刻）
     */
    public Date getLastMonTime() {
        return lastMonTime;
    }

    /**
     * 设置最近一个月的时间（即30天前的时刻）
     *
     * @param lastMonTime 最近一个月的时间（即30天前的时刻）
     */
    public void setLastMonTime(Date lastMonTime) {
        this.lastMonTime = lastMonTime;
    }

    /**
     * 获取月售数量
     *
     * @return mon_sold_num - 月售数量
     */
    public Integer getMonSoldNum() {
        return monSoldNum;
    }

    /**
     * 设置月售数量
     *
     * @param monSoldNum 月售数量
     */
    public void setMonSoldNum(Integer monSoldNum) {
        this.monSoldNum = monSoldNum;
    }

    /**
     * 获取加权评分
     *
     * @return weight_score - 加权评分
     */
    public Double getWeightScore() {
        return weightScore;
    }

    /**
     * 设置加权评分
     *
     * @param weightScore 加权评分
     */
    public void setWeightScore(Double weightScore) {
        this.weightScore = weightScore;
    }

    /**
     * 获取上架时间
     *
     * @return shelve_time - 上架时间
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
}