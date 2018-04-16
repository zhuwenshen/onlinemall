package com.zhuwenshen.service.merchant;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.zhuwenshen.exception.JsonResultException;
import com.zhuwenshen.mapper.ClassDeatilMapperCustom;
import com.zhuwenshen.mapper.TGoodsClassDetailMapper;
import com.zhuwenshen.mapper.TGoodsClassMapper;
import com.zhuwenshen.mapper.TGoodsMapper;
import com.zhuwenshen.model.TGoods;
import com.zhuwenshen.model.TGoodsClass;
import com.zhuwenshen.model.TGoodsClassDetail;
import com.zhuwenshen.model.custom.ClassDetail;
import com.zhuwenshen.model.custom.GoodsClass;
import com.zhuwenshen.model.custom.JsonResult;

@Service
public class MerchantGoodsClassService {

	@Autowired
	private TGoodsMapper goodsMapper;
	
	@Autowired
	private TGoodsClassMapper classMapper;
	
	@Autowired
	private TGoodsClassDetailMapper detailMapper;
	
	@Autowired
	private ClassDeatilMapperCustom detailMC;
	
	/**
	 * 更新商品的分类信息
	 * @param merchanId
	 * @param data
	 * @return
	 * @throws JsonResultException 
	 */
	@Transactional
	public JsonResult updateClass(String merchanId, List<GoodsClass> list) throws JsonResultException {
		
		//检查分类是否存在，存在则更新，不存在则插入
		TGoods goods = null;
		GoodsClass gc = null;
		List<String> classNameList = new ArrayList<>();
		for(int i=0;i<list.size(); i++) {
			//检查商品是否属于当前商家
			gc = list.get(i);
			if(gc == null || StringUtils.isEmpty(gc.getGoodsId())) {
				throw new JsonResultException(JsonResult.fail("商品id不能为空"));
			}
			classNameList.add(gc.getName());
			
			goods = new TGoods();
			goods.setId(gc.getGoodsId());
			goods.setMerchantInformationId(merchanId);
			
			Integer num = goodsMapper.selectCount(goods);
			if(num<=0) {
				throw new JsonResultException(JsonResult.fail("操作商品不存在"));
			}
			
			updateClassDetail(gc , i);
		}
		
		//删除不存在的分类
		detailMC.deleteUnuserGoodsClass(gc.getGoodsId(), classNameList);
		return JsonResult.ok("操作成功");
	}

	/**
	 * 更新商品分类具体信息
	 * @param gc
	 * @throws JsonResultException 
	 */
	private void updateClassDetail(GoodsClass gc, Integer sort) throws JsonResultException {
		if(gc == null) {
			throw new JsonResultException(JsonResult.fail("更新的商品分类不能为空"));
		}
		
		if(StringUtils.isEmpty(gc.getGoodsId())) {
			throw new JsonResultException(JsonResult.fail("更新的商品分类不能为空"));
		}
		
		if(StringUtils.isEmpty(gc.getName())) {
			throw new JsonResultException(JsonResult.fail("更新的商品分类名称不能为空"));
		}
		
		if(sort == null) {
			sort = 0;
		}
		
		gc.setSort(sort);
		
		List<ClassDetail> list = gc.getDetails();
		if(list == null || list.isEmpty()) {
			throw new JsonResultException(JsonResult.fail("更新的商品分类名称为 " + gc.getName()+" 的具体分类信息不能为空"));
		}
		//查询分类
		TGoodsClass tgc = new TGoodsClass();
		tgc.setName(gc.getName());
		tgc.setGoodsId(gc.getGoodsId());			
		TGoodsClass c2 = classMapper.selectOne(tgc);
		
		//插入或更新分类
		if(c2 == null) {
			tgc.setDeleted(false);	
			tgc.setSort(sort);
			classMapper.insertSelective(tgc);
		}else {
			tgc = c2;
			tgc.setDeleted(false);	
			tgc.setSort(sort);
			classMapper.updateByPrimaryKeySelective(tgc);
		}
		
		ClassDetail detail = null;
		TGoodsClassDetail tgcd = null;
		List<String> detailNameList = new ArrayList<>();
		for(int i=0;i<list.size();i++) {
			detail = list.get(i);
			if(detail == null) {
				throw new JsonResultException(JsonResult.fail("更新的商品分类名称为 " + gc.getName()+" 的具体分类信息不能为空"));
			}
			if(StringUtils.isEmpty(detail.getName())) {
				throw new JsonResultException(JsonResult.fail("更新的商品分类名称为 " + gc.getName()+" 的具体分类的子分类不能为空"));
			}
			detailNameList.add(detail.getName());
			
			detail.setClassId(tgc.getId());
			detail.setSort(i);
			//查询子分类
			tgcd = new TGoodsClassDetail();
			tgcd.setClassId(detail.getClassId());
			tgcd.setName(detail.getName());						
			TGoodsClassDetail d2 = detailMapper.selectOne(tgcd);
			
			//插入或更新子分类
			if(d2 == null) {	
				tgcd.setDeleted(false);
				tgcd.setSort(detail.getSort());
				detailMapper.insertSelective(tgcd);
			}else {
				tgcd = d2;
				tgcd.setDeleted(false);
				tgcd.setSort(detail.getSort());
				detailMapper.updateByPrimaryKeySelective(tgcd);				
			}			
		}
		
		//删除不在列表的子分类
		detailMC.deleteUnuseClassDetail(tgc.getId(), detailNameList);
	}

	/**
	 * 根据商品id查询出分类信息
	 * @param goodsId
	 * @return
	 * @throws JsonResultException 
	 */
	public String queryClassByGoodsId(String goodsId) throws JsonResultException {
		if(StringUtils.isEmpty(goodsId)) {
			throw new JsonResultException(JsonResult.fail("商品id不能为空"));
		}
		List<?> list  = detailMC.selectGoodsClassByGoodsId(goodsId, null, null);
		return JsonResult.ok("查询成功", list).toString();
	}

	public String getGoodsNameById(String goodsId) {
		TGoods goods = goodsMapper.selectByPrimaryKey(goodsId);
		if(goods!= null) {
			return goods.getName();
		}
		return null;
	}

}
