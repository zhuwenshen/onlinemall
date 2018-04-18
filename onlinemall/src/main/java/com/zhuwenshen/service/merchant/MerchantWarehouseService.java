package com.zhuwenshen.service.merchant;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhuwenshen.exception.JsonResultException;
import com.zhuwenshen.mapper.ClassDeatilMapperCustom;
import com.zhuwenshen.mapper.GoodsPriceMapperCustom;
import com.zhuwenshen.mapper.MerchantGoodsMapperCustom;
import com.zhuwenshen.mapper.TGoodsMapper;
import com.zhuwenshen.mapper.TGoodsPriceMapper;
import com.zhuwenshen.mapper.TPriceMapper;
import com.zhuwenshen.model.TGoods;
import com.zhuwenshen.model.TGoodsPrice;
import com.zhuwenshen.model.TPrice;
import com.zhuwenshen.model.custom.JsonResult;
import com.zhuwenshen.model.custom.User;
import com.zhuwenshen.model.custom.merchant.QueryGoodsPrice;
import com.zhuwenshen.model.custom.merchant.QueryGoodsPriceParam;
import com.zhuwenshen.model.custom.merchant.QueryMerchantGoodsParam;

@Service
public class MerchantWarehouseService {
	
	@Autowired
	private MerchantGoodsMapperCustom goodsMC;
	
	@Autowired
	private ClassDeatilMapperCustom detailMC;
	
	@Autowired
	private TGoodsMapper goodsMapper;
	
	@Autowired
	private TPriceMapper priceMapper;
	
	@Autowired
	private TGoodsPriceMapper goodsPriceMapper;
	
	@Autowired
	private GoodsPriceMapperCustom gpMC;
	
	
	/**
	 * 查询当前商家的所有商品名称
	 * @param user
	 * @param unshelve 
	 * @return
	 */
	public JsonResult listGoods(User user, Boolean unshelve) {
		QueryMerchantGoodsParam qmgp = new QueryMerchantGoodsParam();
		qmgp.setMerchantInformationId(user.getMerchantId());
		qmgp.setUnshelve(unshelve);	
		
		List<?> g = goodsMC.selectMerchantGoodsForSelect(qmgp);
		return JsonResult.ok("", g);
	}

	/**
	 * 查询商品的所有分类
	 * @param user
	 * @param goodsId
	 * @return
	 * @throws JsonResultException 
	 */
	public JsonResult listGoodsClass(String goodsId) throws JsonResultException {
		if(StringUtils.isEmpty(goodsId)) {
			throw new JsonResultException(JsonResult.fail("商品id不能为空"));
		}
		List<?> list  = detailMC.selectGoodsClassByGoodsId(goodsId, null, null);
		return JsonResult.ok("查询成功", list);
	}

	/**
	 * 分页查找商品价格信息
	 * @param user
	 * @param qgpp
	 * @return
	 * @throws JsonResultException
	 */
	public JsonResult queryGoodsPrice(User user, QueryGoodsPriceParam qgpp)throws JsonResultException {
		//检查参数
		if(qgpp == null) {
			throw new JsonResultException(JsonResult.fail("参数错误"));
		}
		if(StringUtils.isEmpty(qgpp.getGoodsId())) {
			throw new JsonResultException(JsonResult.fail("商品id不能为空"));
		}
		//检查当前商品是否属于登录商家
		TGoods g = new TGoods();
		g.setId(qgpp.getGoodsId());
		g.setMerchantInformationId(user.getMerchantId());
		g.setDeleted(false);
		
		Integer num = goodsMapper.selectCount(g);
		
		if(num<=0) {
			throw new JsonResultException(JsonResult.fail("该商品不属于本商家"));
		}
		int len = qgpp.getClassDetailId() == null ? 0 : qgpp.getClassDetailId().length;
		
		// 开始分页
		PageHelper.startPage(qgpp.getPageNum(), qgpp.getPageSize());
		//查询商品跟分类组合的价格是否存在
		Page<?> page =  gpMC.selectPriceByGoodsAndClassForPage(qgpp.getGoodsId(), qgpp.getClassDetailId(), len);		
		
		System.out.println(page);		
		return null;
	}

	/**
	 * 保存一个商品价格
	 * @param user 
	 * @param qgpp
	 * @return
	 */
	@Transactional
	public JsonResult saveGoodsPrice(User user, QueryGoodsPriceParam qgpp) throws JsonResultException{
		
		//检查参数
		if(qgpp == null) {
			throw new JsonResultException(JsonResult.fail("参数错误"));
		}
		if(StringUtils.isEmpty(qgpp.getGoodsId())) {
			throw new JsonResultException(JsonResult.fail("商品id不能为空"));
		}
		if(qgpp.getPrice() == null || qgpp.getPrice().compareTo(new BigDecimal(0))<=0) {
			throw new JsonResultException(JsonResult.fail("价格不能为空而且必须大于0"));
		}
		if(qgpp.getNum() == null || qgpp.getNum() < 0) {
			throw new JsonResultException(JsonResult.fail("进货数量不能为空而且必须大于或等于0"));
		}		
		
		//检查当前商品是否属于登录商家
		TGoods g = new TGoods();
		g.setId(qgpp.getGoodsId());
		g.setMerchantInformationId(user.getMerchantId());
		g.setDeleted(false);
		
		Integer num = goodsMapper.selectCount(g);
		
		if(num<=0) {
			throw new JsonResultException(JsonResult.fail("该商品不属于本商家"));
		}
		
		int len = qgpp.getClassDetailId() == null ? 0 : qgpp.getClassDetailId().length;
		
		//查询商品跟分类组合的价格是否存在
		List<String> list = gpMC.selectPriceByGoodsAndClass(qgpp.getGoodsId(), qgpp.getClassDetailId(), len);
				
		//不存在则插入
		if(list.isEmpty()) {
			//插入price
			TPrice price = new TPrice();
			price.setGoodsId(qgpp.getGoodsId());
			price.setAllNum(qgpp.getNum());
			price.setNum(qgpp.getNum());
			price.setPrice(qgpp.getPrice());
			price.setDeleted(false);
			priceMapper.insert(price);
			
			//插入关系
			TGoodsPrice goodsPrice = null;
			if(qgpp.getClassDetailId() != null && qgpp.getClassDetailId().length >0 ) {
				for(String deatilId : qgpp.getClassDetailId()) {
					goodsPrice = new TGoodsPrice();
					goodsPrice.setClassDetailId(deatilId);
					goodsPrice.setPriceId(price.getId());
					goodsPriceMapper.insert(goodsPrice);
				}
			}			
			
		}else {
			//存在则更新，入货数量相加
			String priceId = list.get(0);
			TPrice price = priceMapper.selectByPrimaryKey(priceId);
			if(price == null) {
				throw new JsonResultException(JsonResult.fail("数据错误"));
			}
			
			price.setAllNum(price.getAllNum()+qgpp.getNum());
			price.setNum(price.getNum()+qgpp.getNum());
			price.setPrice(qgpp.getPrice());
			
			priceMapper.updateByPrimaryKeySelective(price);			
		}
		
		
		
		return JsonResult.ok("操作成功");
	}

	/**
	 * 根据商品id和商品的子分类信息查找出当前商品价格
	 * @param user
	 * @param qgpp
	 * @return
	 * @throws JsonResultException
	 */
	public JsonResult selectGoodsPrice(User user, QueryGoodsPriceParam qgpp)throws JsonResultException {
		//检查参数
		if(qgpp == null) {
			throw new JsonResultException(JsonResult.fail("参数错误"));
		}
		if(StringUtils.isEmpty(qgpp.getGoodsId())) {
			throw new JsonResultException(JsonResult.fail("商品id不能为空"));
		}
		//检查当前商品是否属于登录商家
		TGoods g = new TGoods();
		g.setId(qgpp.getGoodsId());
		g.setMerchantInformationId(user.getMerchantId());
		g.setDeleted(false);
		
		Integer num = goodsMapper.selectCount(g);
		
		if(num<=0) {
			throw new JsonResultException(JsonResult.fail("该商品不属于本商家"));
		}
		
		int len = qgpp.getClassDetailId() == null ? 0 : qgpp.getClassDetailId().length;
		
		//查询商品跟分类组合的价格是否存在
		List<String> list = gpMC.selectPriceByGoodsAndClass(qgpp.getGoodsId(), qgpp.getClassDetailId(), len);
		
		if(list.isEmpty()) {
			throw new JsonResultException(JsonResult.fail("无查询结果"));
		}
		
		QueryGoodsPrice p =  gpMC.selectOneGoodsPrice(list.get(0));
		if(p==null) {
			return JsonResult.fail("无数据");
		}
		
		return JsonResult.ok("查询成功", p);
	}
	
	
	
	
	
	
	
	
}
