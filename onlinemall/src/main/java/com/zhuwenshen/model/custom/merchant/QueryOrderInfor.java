package com.zhuwenshen.model.custom.merchant;

import java.math.BigDecimal;
import java.util.Date;

import com.zhuwenshen.annotation.PicUrl;

public class QueryOrderInfor {
	private String id;

	/**
	 * 订单id
	 */
	private String orderId;

	private String goodsId;
	private String goodsName;
	@PicUrl
	private String goodsImg;

	/**
	 * 商品种类id
	 */
	private String priceId;

	/**
	 * 具体分类信息
	 */
	private String classDetails;

	/**
	 * 商品数量
	 */
	private Integer num;

	/**
	 * 单价
	 */
	private BigDecimal price;

	/**
	 * 总价
	 */
	private BigDecimal totalPrices;
	
	/**
     * 评价流程状态
     */
    private Integer evaluateStatus;


	/**
	 * 是否删除；0否 1是
	 */
	private Boolean deleted;

	/**
	 * 创建时间
	 */
	private Date createTime;

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
	 * 获取订单id
	 *
	 * @return order_id - 订单id
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * 设置订单id
	 *
	 * @param orderId
	 *            订单id
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId == null ? null : orderId.trim();
	}

	/**
	 * 获取商品种类id
	 *
	 * @return price_id - 商品种类id
	 */
	public String getPriceId() {
		return priceId;
	}

	/**
	 * 设置商品种类id
	 *
	 * @param priceId
	 *            商品种类id
	 */
	public void setPriceId(String priceId) {
		this.priceId = priceId == null ? null : priceId.trim();
	}

	/**
	 * 获取具体分类信息
	 *
	 * @return class_details - 具体分类信息
	 */
	public String getClassDetails() {
		return classDetails;
	}

	/**
	 * 设置具体分类信息
	 *
	 * @param classDetails
	 *            具体分类信息
	 */
	public void setClassDetails(String classDetails) {
		this.classDetails = classDetails == null ? null : classDetails.trim();
	}

	/**
	 * 获取商品数量
	 *
	 * @return num - 商品数量
	 */
	public Integer getNum() {
		return num;
	}

	/**
	 * 设置商品数量
	 *
	 * @param num
	 *            商品数量
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
	 * @param price
	 *            单价
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * 获取总价
	 *
	 * @return total_prices - 总价
	 */
	public BigDecimal getTotalPrices() {
		return totalPrices;
	}

	/**
	 * 设置总价
	 *
	 * @param totalPrices
	 *            总价
	 */
	public void setTotalPrices(BigDecimal totalPrices) {
		this.totalPrices = totalPrices;
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
	 * @param deleted
	 *            是否删除；0否 1是
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
	 * @param createTime
	 *            创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public Integer getEvaluateStatus() {
		return evaluateStatus;
	}

	public void setEvaluateStatus(Integer evaluateStatus) {
		this.evaluateStatus = evaluateStatus;
	}
}