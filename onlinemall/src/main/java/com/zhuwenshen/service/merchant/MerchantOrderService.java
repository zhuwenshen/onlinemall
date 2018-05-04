package com.zhuwenshen.service.merchant;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhuwenshen.exception.JsonResultException;
import com.zhuwenshen.mapper.OrderMapperCustom;
import com.zhuwenshen.mapper.TOrderInformationMapper;
import com.zhuwenshen.mapper.TOrderMapper;
import com.zhuwenshen.mapper.TPriceMapper;
import com.zhuwenshen.model.TOrder;
import com.zhuwenshen.model.TOrderInformation;
import com.zhuwenshen.model.TPrice;
import com.zhuwenshen.model.custom.JsonResult;
import com.zhuwenshen.model.custom.merchant.QueryOrderParam;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class MerchantOrderService {

	@Autowired
	private OrderMapperCustom orderMC;
	
	@Autowired
	private TOrderMapper orderMapper;
	
	@Autowired
	private TOrderInformationMapper orderInformationMapper;
	
	@Autowired
	private TPriceMapper priceMapper;

	public String query(String merchantId, QueryOrderParam param) {
		if(param == null) {
			param = new QueryOrderParam();
		}
		
		if(param.getCreateTimeStart() == null && param.getCreateTimeEnd() == null) {
			param.setCreateTimeMon(new Date(System.currentTimeMillis()-(long)30*24*60*60*1000));
		}
		
		param.setMerchantId(merchantId);
		// 开始分页
		Page<?> page = PageHelper.startPage(param.getPageNum(), param.getPageSize());		
		orderMC.selectOrder(param);		
		
		return JsonResult.okToPageList(page);
	}

	public JsonResult getOrderInfor(String merchantId, QueryOrderParam param) throws JsonResultException {
		if(StringUtils.isEmpty(merchantId)) {
			throw new JsonResultException(JsonResult.fail("merchantId不能为空"));
		}
		if(param == null) {
			throw new JsonResultException(JsonResult.fail("orderId不能为空"));
		}
		if(StringUtils.isEmpty(param.getId())) {
			throw new JsonResultException(JsonResult.fail("orderId不能为空"));
		}
		
		param.setMerchantId(merchantId);
		List<?> list = orderMC.selectOrder(param);	
		return JsonResult.ok("查询成功", list);
	}

	/**
	 * 确认订单
	 * @param merchantId
	 * @param orderId
	 * @return
	 * @throws JsonResultException
	 */
	@Transactional
	public JsonResult confirmOrder(String merchantId, String orderId) throws JsonResultException  {
		if(StringUtils.isEmpty(merchantId)) {
			throw new JsonResultException(JsonResult.fail("merchantId不能为空"));
		}
		if(StringUtils.isEmpty(orderId)) {
			throw new JsonResultException(JsonResult.fail("orderId不能为空"));
		}
		//校验订单信息
		TOrder o = orderMapper.selectByPrimaryKey(orderId);
		if(o == null || o.getDeleted()|| !merchantId.equals(o.getMerchantId())) {
			throw new JsonResultException(JsonResult.fail("该订单信息不存在"));
		}
		if(!o.getOrderStatus().equals(2)) {
			throw new JsonResultException(JsonResult.fail("该订单状态不能被商家确认"));
		}
		
		//更新确认状态
		TOrder ou = new TOrder();
		ou.setId(orderId);
		ou.setOrderStatus(3);
		ou.setConfirmTime(new Date());
		
		orderMapper.updateByPrimaryKeySelective(ou);
		
		return JsonResult.ok("确认成功");
	}

	/**
	 * 取消订单
	 * @param merchantId
	 * @param orderId
	 * @param reason
	 * @return
	 * @throws JsonResultException
	 */
	@Transactional
	public JsonResult cancelOrder(String merchantId, String orderId, String reason) throws JsonResultException {
		if(StringUtils.isEmpty(merchantId)) {
			throw new JsonResultException(JsonResult.fail("merchantId不能为空"));
		}
		if(StringUtils.isEmpty(reason)) {
			throw new JsonResultException(JsonResult.fail("取消原因不能为空"));
		}
		
		//检查订单是否已经支付，已经支付的退还支付款
		TOrder o = orderMapper.selectByPrimaryKey(orderId);
		if(o == null || o.getDeleted()|| !merchantId.equals(o.getMerchantId())) {
			throw new JsonResultException(JsonResult.fail("该订单信息不存在"));
		}
		if(!o.getOrderStatus().equals(1) && !o.getOrderStatus().equals(2) && !o.getOrderStatus().equals(3)) {
			throw new JsonResultException(JsonResult.fail("该订单状态不能取消订单"));
		}
		
		//已经支付的退还支付款
		if(o.getPayTime() != null) {
			//TODO 已经支付的退还支付款
		}
		
		//回调商品数量
		Example a = new Example(TOrderInformation.class);
		Criteria c = a.createCriteria();
		c.andEqualTo("orderId", orderId);
		c.andEqualTo("deleted", false);
		
		List<TOrderInformation> list = orderInformationMapper.selectByExample(a);
		for(TOrderInformation oi:list) {
			callbackOrderPriceNum(oi);
		}
		
		//更新状态
		o = new TOrder();
		o.setId(orderId);
		o.setFinishTime(new Date());
		o.setOrderStatus(14);//交易失败
		o.setRemake("商家取消了交易，取消原因： "+reason);
		
		orderMapper.updateByPrimaryKeySelective(o);
		
		return JsonResult.ok("取消成功");
	}

	/**
	 * 回调商品数量
	 * @param oi
	 */
	private void callbackOrderPriceNum(TOrderInformation oi) {
		if(oi == null) {
			return;
		}
		
		TPrice p = priceMapper.selectByPrimaryKey(oi.getPriceId());
		if(p == null) {
			return;
		}
		
		TPrice pu = new TPrice();
		pu.setId(oi.getPriceId());
		pu.setNum(p.getNum()+oi.getNum());
		Integer soldNum = p.getSoldNum() - oi.getNum();		
		pu.setSoldNum(soldNum<0?0:soldNum);
		
		priceMapper.updateByPrimaryKeySelective(pu);
	}

	/**
	 * 发货
	 * @param merchantId
	 * @param orderId
	 * @param expressNumber
	 * @param expressCompany
	 * @return
	 * @throws JsonResultException
	 */
	@Transactional
	public JsonResult deliverOrder(String merchantId, String orderId, String expressNumber, String expressCompany) throws JsonResultException {
		if(StringUtils.isEmpty(merchantId)) {
			throw new JsonResultException(JsonResult.fail("merchantId不能为空"));
		}
		if(StringUtils.isEmpty(orderId)) {
			throw new JsonResultException(JsonResult.fail("orderId不能为空"));
		}
		if(StringUtils.isEmpty(expressNumber)) {
			throw new JsonResultException(JsonResult.fail("快递单号不能为空"));
		}
		if(StringUtils.isEmpty(expressCompany)) {
			throw new JsonResultException(JsonResult.fail("快递公司不能为空"));
		}
		
		//校验订单信息
		TOrder o = orderMapper.selectByPrimaryKey(orderId);
		if(o == null || o.getDeleted()|| !merchantId.equals(o.getMerchantId())) {
			throw new JsonResultException(JsonResult.fail("该订单信息不存在"));
		}
		if(!o.getOrderStatus().equals(3)) {
			throw new JsonResultException(JsonResult.fail("该订单状态不能发货"));
		}
		
		//更新确认状态
		TOrder ou = new TOrder();
		ou.setId(orderId);
		ou.setOrderStatus(4);//已发货
		ou.setDeliverTime(new Date());
		
		orderMapper.updateByPrimaryKeySelective(ou);
		
		return JsonResult.ok("发货成功");
	}
	
	
}
