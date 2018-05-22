package com.zhuwenshen.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zhuwenshen.mapper.BriefGoodsMapperCustom;
import com.zhuwenshen.mapper.TGoodsBriefMapper;
import com.zhuwenshen.mapper.TGoodsMapper;
import com.zhuwenshen.mapper.TMerchantInformationMapper;
import com.zhuwenshen.mapper.TPriceMapper;
import com.zhuwenshen.mapper.TTaskGoodsIndexMapper;
import com.zhuwenshen.model.TGoods;
import com.zhuwenshen.model.TGoodsBrief;
import com.zhuwenshen.model.TMerchantInformation;
import com.zhuwenshen.model.TPrice;
import com.zhuwenshen.model.TTaskGoodsIndex;
import com.zhuwenshen.util.DateFormatUtils;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class BriefGoodsService {
	
	private final static long  MON_TIME = (long)30*24*60*60*1000;

	@Autowired
	private TMerchantInformationMapper merchantMapper;
	
	@Autowired
	private TTaskGoodsIndexMapper indexMapper;
	
	@Autowired
	private TGoodsBriefMapper goodsBriefMapper;
	
	@Autowired
	private BriefGoodsMapperCustom goodsBriefMC;
	
	@Autowired
	private TGoodsMapper goodsMapper;
	
	@Autowired
	private TPriceMapper priceMapper;
	
	/**
	 * 提取goods简要信息
	 * @param isAll 是否全部更新
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public synchronized void brief(boolean isAll) {		
		if(isAll) {
			insertAllIndex();
		}		
		doBrief();
	}
	
	/**
	 * 全部提取
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public synchronized void briefAll() {
		insertAllIndex();
		doBrief();
	}
	
	/**
	 * 获取需要更新的goodis数组
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void queryIndex() {
		//如果简要表为空，即更新全部
		Example example = new Example(TGoodsBrief.class);
		Integer num = goodsBriefMapper.selectCountByExample(example);
		if(num == 0) {
			
		}
	}
	
	/**
	 * 插入所有的index
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void insertAllIndex() {
		goodsBriefMC.deleteAll();		
		goodsBriefMC.insertAll();
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	private void doBrief() {
		//获取需要更新的商品id
		List<TTaskGoodsIndex> list = getIndex();
		for(TTaskGoodsIndex i :list) {
			try {
				//统计一个商品信息
				briefOneGoods(i.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 统计一个商品简要信息
	 * @param goodsId
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	private void briefOneGoods(String goodsId) {
		Date today = DateFormatUtils.getToday();
		
		TGoodsBrief brief = new TGoodsBrief();
		brief.setGoodsId(goodsId);
		brief.setLatestTime(today);
		brief.setLastMonTime(new Date(today.getTime() - MON_TIME));		
		
		TGoods goods = goodsMapper.selectByPrimaryKey(goodsId);
		if(goods == null || goods.getDeleted() || goods.getUnshelve()) {			
			deleted(goodsId);
			return;
		}
		
		//设置商家信息
		TMerchantInformation merchant = merchantMapper.selectByPrimaryKey(goods.getMerchantInformationId());
		if(merchant == null || merchant.getDeleted()) {
			deleted(goodsId);
			return;
		}
		
		brief.setMerchantId(merchant.getId());
		brief.setMerchantName(merchant.getNameCn());
		
		brief.setGoodsDes(goods.getDescription());
		brief.setGoodsName(goods.getName());
		brief.setGoodsImg(goods.getDescriptionImg1Url());
		brief.setShelveTime(goods.getShelveTime());
		
		//找到所有price
		Example aPrice = new Example(TPrice.class);
		Criteria cPrice = aPrice.createCriteria();
		cPrice.andEqualTo("goodsId", goodsId);
		List<TPrice> priceList = priceMapper.selectByExample(aPrice);
		if(priceList.isEmpty()) {
			deleted(goodsId);
			return;
		}
		
		Boolean hasPrice = false;
		List<String> priceIdList = new ArrayList<String>();
		TPrice p = priceList.get(0);
		BigDecimal temp = p.getPrice();
		priceIdList.add(p.getId());
		if(!p.getDeleted()) {
			hasPrice = true;
		}
		
		for(int i=1;i<priceList.size();i++) {			
			p = priceList.get(i);
			priceIdList.add(p.getId());
			if(temp.compareTo(p.getPrice())<0) {
				temp = p.getPrice();
			}
			if(!p.getDeleted()) {
				hasPrice = true;
			}
		}
		
		brief.setPrice(temp);
		if(!hasPrice) {
			deleted(goodsId);
			return;
		}
		
		//设置商品标签
		brief.setGoodsLabel(goodsBriefMC.selectGoodsLabel(goodsId));
		
		//设置订单数量
		brief.setOrderSoldNum(goodsBriefMC.selectOrderNum(priceIdList, 1));
		brief.setOrderSuccessNum(goodsBriefMC.selectOrderNum(priceIdList, 2));
		brief.setOrderFailNum(goodsBriefMC.selectOrderNum(priceIdList, 3));
		
		//设置商品数量
		Integer num = null;
		num = goodsBriefMC.selectGoodsNum(priceIdList, 1);
		if(num == null) {
			num = 0;
		}
		brief.setSoldNum(num);
		
		num = goodsBriefMC.selectGoodsNum(priceIdList, 2);
		if(num == null) {
			num = 0;
		}
		brief.setSuccessNum(num);
		
		num = goodsBriefMC.selectGoodsNum(priceIdList, 3);
		if(num == null) {
			num = 0;
		}
		brief.setFailNum(num);
		
		//设置评分
		brief.setAvgScore(goodsBriefMC.selectAvgScore(priceIdList));
		
		//设置月售	
		num = goodsBriefMC.selectMonSoldNum(priceIdList, brief.getLatestTime(), brief.getLastMonTime());
		if(num == null) {
			num = 0;
		}
		brief.setMonSoldNum(num);
		
		//设置加权分 avg_score + (order_success_num - order_fail_num*0.9)/(order_success_num + 1) +1
		Double avgScore  = brief.getAvgScore();
		if(avgScore == null) {
			avgScore = 0.0;
		}
		
		Double weightScore = avgScore + (brief.getOrderSuccessNum() - brief.getOrderFailNum()*0.9+1)/(brief.getSuccessNum()+1) +1;
		brief.setWeightScore(weightScore);
		
		TGoodsBrief up = new TGoodsBrief();
		up.setGoodsId(goodsId);
		List<TGoodsBrief>  upList= goodsBriefMapper.select(up);
		if(upList.isEmpty()) {
			goodsBriefMapper.insert(brief);
		}else {
			brief.setId(upList.get(0).getId());
			goodsBriefMapper.updateByPrimaryKey(brief);
		}
		
		indexMapper.deleteByPrimaryKey(goodsId);
	}

	private void deleted(String goodsId) {
		indexMapper.deleteByPrimaryKey(goodsId);
		TGoodsBrief record = new TGoodsBrief();
		record.setGoodsId(goodsId);
		goodsBriefMapper.delete(record);
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	private List<TTaskGoodsIndex> getIndex() {
		List<TTaskGoodsIndex> list = indexMapper.selectAll();
		return list;
	}

}
