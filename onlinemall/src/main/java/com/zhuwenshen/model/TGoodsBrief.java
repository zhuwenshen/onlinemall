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

    @Column(name = "goods_id")
    private String goodsId;

    /**
     * 最近统计时间
     */
    @Column(name = "latest_time")
    private Date latestTime;

    /**
     * 成交订单数
     */
    @Column(name = "sold_order_num")
    private Integer soldOrderNum;

    /**
     * 成功订单数
     */
    @Column(name = "success_num")
    private Integer successNum;

    /**
     * 失败订单数量
     */
    @Column(name = "fali_num")
    private Integer faliNum;

    /**
     * 评价评分
     */
    @Column(name = "avg_score")
    private Double avgScore;

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

    @Column(name = "goods_name")
    private String goodsName;

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
     * @return sold_order_num - 成交订单数
     */
    public Integer getSoldOrderNum() {
        return soldOrderNum;
    }

    /**
     * 设置成交订单数
     *
     * @param soldOrderNum 成交订单数
     */
    public void setSoldOrderNum(Integer soldOrderNum) {
        this.soldOrderNum = soldOrderNum;
    }

    /**
     * 获取成功订单数
     *
     * @return success_num - 成功订单数
     */
    public Integer getSuccessNum() {
        return successNum;
    }

    /**
     * 设置成功订单数
     *
     * @param successNum 成功订单数
     */
    public void setSuccessNum(Integer successNum) {
        this.successNum = successNum;
    }

    /**
     * 获取失败订单数量
     *
     * @return fali_num - 失败订单数量
     */
    public Integer getFaliNum() {
        return faliNum;
    }

    /**
     * 设置失败订单数量
     *
     * @param faliNum 失败订单数量
     */
    public void setFaliNum(Integer faliNum) {
        this.faliNum = faliNum;
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
}