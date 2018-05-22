package com.zhuwenshen.service.merchant;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhuwenshen.exception.JsonResultException;
import com.zhuwenshen.mapper.MerchantGoodsMapperCustom;
import com.zhuwenshen.mapper.TGoodsDetailMapper;
import com.zhuwenshen.mapper.TGoodsMapper;
import com.zhuwenshen.model.TGoods;
import com.zhuwenshen.model.TGoodsDetail;
import com.zhuwenshen.model.custom.JsonResult;
import com.zhuwenshen.model.custom.User;
import com.zhuwenshen.model.custom.merchant.QueryMerchantGoods;
import com.zhuwenshen.model.custom.merchant.QueryMerchantGoodsParam;
import com.zhuwenshen.util.DateFormatUtils;
import com.zhuwenshen.util.PicUtils;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class MerchantGoodsService {

	@Autowired
	private MerchantGoodsMapperCustom mgmc;
	
	@Autowired
	private TGoodsMapper goodsMapper;
	
	@Autowired
	private MerchantGoodsLabelService merchantGoodsLabelService;
	
	@Autowired
	private TGoodsDetailMapper goodsDetailMapper;
	
	@SuppressWarnings("unchecked")
	public String queryGoods(User user , QueryMerchantGoodsParam qmgp) {
		if(qmgp == null) {
			qmgp = new QueryMerchantGoodsParam();
		}
		qmgp.setMerchantInformationId(user.getMerchantId());
		// 开始分页
		Page<?> page = PageHelper.startPage(qmgp.getPageNum(), qmgp.getPageSize());
		mgmc.selectMerchantGoodsList(qmgp);	
		
		List<QueryMerchantGoods> list = (List<QueryMerchantGoods>) page.getResult();
		for(int i = 0 ; i< list.size() ; i++) {
			list.get(i).setLabelList(merchantGoodsLabelService.queryGoosLabel(list.get(i).getId()));
		}
		
		return JsonResult.okToPageList(page);
	}

	/**
	 * 增加商品
	 * @param user 
	 * @param goods
	 * @return
	 * @throws Exception 
	 */
	@Transactional
	public JsonResult addGoods(User user, QueryMerchantGoodsParam goods) throws JsonResultException {
		
		if(goods == null) {
			return JsonResult.fail("参数错误");
		}
		
		TGoods g =  new TGoods();
		g.setName(goods.getName());
		g.setMerchantInformationId(user.getMerchantId());
		g.setDeleted(false);
		
		if(StringUtils.isEmpty(goods.getName())) {
			return JsonResult.fail("商品名不能为空");
		}else {
			//检查同一家商家是否存在同名商品		
			
			Integer num = goodsMapper.selectCount(g);
			if(num>1) {
				return JsonResult.fail("已存在同名商品");
			}
			
		}
		
		if(StringUtils.isEmpty(goods.getDescriptionImg1Url())) {
			return JsonResult.fail("商品名不能为没有描述图片");
		}
		
		if(goods.getLabel() == null || goods.getLabel().length ==0) {
			return JsonResult.fail("商品不能没有标签");
		}
		
		goods.setMerchantInformationId(user.getMerchantId());
		if(goods.getShelveTime() == null) {
			goods.setShelveTime(new Date());
		}
		
		goods.setUnshelveByAdmin(false);		
		goods.setUnshelve(false);
		
		if(goods.getShelveTime().after(new Date())) {
			goods.setUnshelve(true);
		}		
		
		if(goods.getUnshelveTime() != null) {
			if(goods.getUnshelveTime().before(goods.getShelveTime())) {
				return JsonResult.fail("商品下架时间不能比下架时间早");
			}
		}
				
		//设置goods信息
		g.setDescription(goods.getDescription());
		g.setDescriptionImg1Url(goods.getDescriptionImg1Url());
		
		if(!StringUtils.isEmpty(goods.getDescriptionImg2Url())) {
			g.setDescriptionImg2Url(goods.getDescriptionImg2Url());
		}
		if(!StringUtils.isEmpty(goods.getDescriptionImg3Url())) {
			g.setDescriptionImg3Url(goods.getDescriptionImg3Url());
		}
		if(!StringUtils.isEmpty(goods.getDescriptionImg4url())) {
			g.setDescriptionImg4url(goods.getDescriptionImg4url());
		}
		
		g.setUnshelveByAdmin(false);
		
		
		if(goods.getShelveTime() != null) {
			g.setShelveTime(goods.getShelveTime());
		}
		
		if(goods.getUnshelveTime() != null) {
			g.setUnshelveTime(goods.getUnshelveTime());
		}
		g.setUnshelve(goods.getUnshelve());
		
		
		goodsMapper.insertSelective(g);
		
		//添加商品标签
		this.addGoodsLable(user, g.getId(), goods.getLabel());
	
		JsonResult json = null;
		//添加商品详情
		json = addGoodsDetail(user, g.getId(), goods);
		if(!json.isStatus()) {
			throw new JsonResultException(json);
		}
		
		return JsonResult.ok("添加成功", goods);
	}

	/**
	 * 添加商品标签
	 * @param user
	 * @param goodsId
	 * @param labels
	 * @return
	 * @throws JsonResultException 
	 */	
	@Transactional
	public JsonResult addGoodsLable(User user, String goodsId, String[] labels) throws JsonResultException {
		//检查参数
		if(StringUtils.isEmpty(goodsId)) {
			throw new JsonResultException(JsonResult.fail("商品id不能为空"));
		}
		
		if(labels == null || labels.length <= 0) {
			throw new JsonResultException(JsonResult.fail("商品标签不能为空"));			
		}
		//检查商品是否属于当前商家
		TGoods g = new TGoods();
		g.setId(goodsId);
		g.setMerchantInformationId(user.getMerchantId());
		g.setDeleted(false);
		
		Integer num = goodsMapper.selectCount(g);
		
		if(num<=0) {
			throw new JsonResultException(JsonResult.fail("该商品不属于本商家"));	
		}		
		
		//检查label关系是否存在并添加		
		for (String string : labels) {
			if(!StringUtils.isEmpty(string)) {
				merchantGoodsLabelService.addGoodsLabel(goodsId, string);
			}
		}		
		
		return JsonResult.ok("添加成功");
	}
	
	/**
	 * 更改商品的标签
	 * @param user
	 * @param goodsId
	 * @param labelIds
	 * @return
	 * @throws JsonResultException 
	 */
	public JsonResult updateGoodsLable(User user, String goodsId, String[] labelIds) throws JsonResultException {
		
		this.addGoodsLable(user, goodsId, labelIds);		
		
		
		//删除不需要的标签
		Integer num = merchantGoodsLabelService.deleteGoodsLabelNotInLabels(goodsId, labelIds);
		
		return JsonResult.ok("更新成功" , num);
	}
	
	private JsonResult addGoodsDetail(User user, String goodsId, QueryMerchantGoodsParam qmgp) {
		String[] imgUrl =  qmgp.getDetailImgUrl();
		if(imgUrl==null || imgUrl.length == 0) {
			JsonResult.fail("商品详情图片不能为空");
		}
		
		TGoodsDetail detail = new TGoodsDetail();
		detail.setGoodsId(goodsId);
		detail.setProducer(qmgp.getProducer());
		detail.setSpecification(qmgp.getSpecification());
		//detail.setDetail(qmgp.getde);
		
		if(imgUrl.length >= 1) {
			detail.setDescriptionImg1Url(imgUrl[0]);
		}
		if(imgUrl.length >= 2) {
			detail.setDescriptionImg2Url(imgUrl[1]);
		}
		if(imgUrl.length >= 3) {
			detail.setDescriptionImg3Url(imgUrl[2]);
		}
		if(imgUrl.length >= 4) {
			detail.setDescriptionImg4Url(imgUrl[3]);
		}
		if(imgUrl.length >= 5) {
			detail.setDescriptionImg5Url(imgUrl[4]);
		}
		if(imgUrl.length >= 6) {
			detail.setDescriptionImg6Url(imgUrl[5]);
		}
		if(imgUrl.length >= 7) {
			detail.setDescriptionImg7Url(imgUrl[6]);
		}
		if(imgUrl.length >= 8) {
			detail.setDescriptionImg8Url(imgUrl[7]);
		}
		if(imgUrl.length >= 9) {
			detail.setDescriptionImg9Url(imgUrl[8]);
		}
		if(imgUrl.length >= 10) {
			detail.setDescriptionImg10Url(imgUrl[9]);
		}
		
		goodsDetailMapper.insertSelective(detail);
		
		
		return JsonResult.ok("添加成功");
		
	}
	
	private JsonResult updateGoodsDetail(User user, String goodsId, QueryMerchantGoodsParam qmgp) {
		String[] imgUrl =  qmgp.getDetailImgUrl();
		if(imgUrl==null || imgUrl.length == 0) {
			JsonResult.fail("商品详情图片不能为空");
		}
		TGoodsDetail detail = new TGoodsDetail();
		detail.setGoodsId(goodsId);
		
		//查找出对象
		TGoodsDetail d2  = goodsDetailMapper.selectOne(detail);
		//没有对象则插入
		if(d2 == null) {
			return addGoodsDetail(user,  goodsId,  qmgp);
		}		
		//设置id
		detail.setId(d2.getId());		
		
		detail.setProducer(qmgp.getProducer());
		detail.setSpecification(qmgp.getSpecification());
		//detail.setDetail(qmgp.getde);
		
		if(imgUrl.length >= 1) {
			detail.setDescriptionImg1Url(imgUrl[0]);
		}
		if(imgUrl.length >= 2) {
			detail.setDescriptionImg2Url(imgUrl[1]);
		}
		if(imgUrl.length >= 3) {
			detail.setDescriptionImg3Url(imgUrl[2]);
		}
		if(imgUrl.length >= 4) {
			detail.setDescriptionImg4Url(imgUrl[3]);
		}
		if(imgUrl.length >= 5) {
			detail.setDescriptionImg5Url(imgUrl[4]);
		}
		if(imgUrl.length >= 6) {
			detail.setDescriptionImg6Url(imgUrl[5]);
		}
		if(imgUrl.length >= 7) {
			detail.setDescriptionImg7Url(imgUrl[6]);
		}
		if(imgUrl.length >= 8) {
			detail.setDescriptionImg8Url(imgUrl[7]);
		}
		if(imgUrl.length >= 9) {
			detail.setDescriptionImg9Url(imgUrl[8]);
		}
		if(imgUrl.length >= 10) {
			detail.setDescriptionImg10Url(imgUrl[9]);
		}
		
		goodsDetailMapper.updateByPrimaryKeySelective(detail);
		
		
		return JsonResult.ok("更新成功");
	}

	public String queryChangeRrecently(User user) {
		// 开始分页
		PageHelper.startPage(1, 10);
		Date date = new Date(new Date().getTime() - (long) 3 * 24 * 60 * 60 * 1000);
		List<?> list = mgmc.selectChangedRecently(user.getMerchantId(), date);
				
		return JsonResult.okToPageList((Page<?>) list);
	}

	/**
	 * 查询当前商家的标签
	 * @param user
	 * @return
	 */
	public String goodsLabel(User user) {
		List<?> list = mgmc.selectGoodsLabel(user.getMerchantId(), null);
		return JsonResult.ok("", list).toString();
	}

	/**
	 * 根据商品id查找商品
	 * @param user
	 * @param goodsId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getGoodsDetailById(User user, String goodsId) {
		QueryMerchantGoodsParam qmgp = new QueryMerchantGoodsParam();		
		
		
		if(StringUtils.isEmpty(goodsId)) {
			return JsonResult.fail("商品id不能为空").toString();
		}
		qmgp.setId(goodsId);
		qmgp.setMerchantInformationId(user.getMerchantId());		
		// 开始分页
		Page<?> page = PageHelper.startPage(qmgp.getPageNum(), qmgp.getPageSize());
		mgmc.selectMerchantGoodsList(qmgp);	
		
		List<QueryMerchantGoods> list = (List<QueryMerchantGoods>) page.getResult();
		if(list.isEmpty()) {
			return JsonResult.fail("无查询结果，该商品已下架或该商品不存在").toString();
		}
		
		list.get(0).setLabelList(merchantGoodsLabelService.queryGoosLabel(list.get(0).getId()));
		
		QueryMerchantGoods g = list.get(0);
		
		//设置商品详情信息
		TGoodsDetail record = new TGoodsDetail();
		record.setGoodsId(goodsId);
		List<TGoodsDetail> details  = goodsDetailMapper.select(record);
		if(!details.isEmpty()) {
			record = details.get(0);
			
			g.setProducer(record.getProducer());
			g.setSpecification(record.getSpecification());
			
			ArrayList<String> imgUrl = new ArrayList<String>();
			
			if(!StringUtils.isEmpty(record.getDescriptionImg1Url())) {
				imgUrl.add(PicUtils.addPicIp(record.getDescriptionImg1Url()));
			}
			if(!StringUtils.isEmpty(record.getDescriptionImg2Url())) {
				imgUrl.add(PicUtils.addPicIp(record.getDescriptionImg2Url()));
			}
			if(!StringUtils.isEmpty(record.getDescriptionImg3Url())) {
				imgUrl.add(PicUtils.addPicIp(record.getDescriptionImg3Url()));
			}
			if(!StringUtils.isEmpty(record.getDescriptionImg4Url())) {
				imgUrl.add(PicUtils.addPicIp(record.getDescriptionImg4Url()));
			}
			if(!StringUtils.isEmpty(record.getDescriptionImg5Url())) {
				imgUrl.add(PicUtils.addPicIp(record.getDescriptionImg5Url()));
			}
			if(!StringUtils.isEmpty(record.getDescriptionImg6Url())) {
				imgUrl.add(PicUtils.addPicIp(record.getDescriptionImg6Url()));
			}
			if(!StringUtils.isEmpty(record.getDescriptionImg7Url())) {
				imgUrl.add(PicUtils.addPicIp(record.getDescriptionImg7Url()));
			}
			if(!StringUtils.isEmpty(record.getDescriptionImg8Url())) {
				imgUrl.add(PicUtils.addPicIp(record.getDescriptionImg8Url()));
			}
			if(!StringUtils.isEmpty(record.getDescriptionImg9Url())) {
				imgUrl.add(PicUtils.addPicIp(record.getDescriptionImg9Url()));
			}
			if(!StringUtils.isEmpty(record.getDescriptionImg10Url())) {
				imgUrl.add(PicUtils.addPicIp(record.getDescriptionImg10Url()));
			}
			
			g.setDetailImgUrl(imgUrl);
		}
		
		return JsonResult.ok("查询成功", g).toString();
	}

	/**
	 * 更新商品
	 * @param user
	 * @param goods
	 * @return
	 * @throws JsonResultException 
	 */
	@Transactional
	public JsonResult updateGoods(User user, QueryMerchantGoodsParam goods) throws JsonResultException {
		
		if(goods == null) {
			return JsonResult.fail("参数错误");
		}
		
		TGoods g =  new TGoods();
		g.setName(goods.getName());
		g.setMerchantInformationId(user.getMerchantId());
		g.setDeleted(false);
		
		if(StringUtils.isEmpty(goods.getName())) {
			return JsonResult.fail("商品名不能为空");
		}else {
			//检查同一家商家是否存在同名商品		
			Example example = new Example(TGoods.class);
			Criteria c = example.createCriteria();
			c.andEqualTo(g);
			c.andNotEqualTo("id", goods.getId());
			
			Integer num = goodsMapper.selectCountByExample(example);
			if(num>1) {
				return JsonResult.fail("已存在同名商品");
			}
			
		}
		
		if(StringUtils.isEmpty(goods.getDescriptionImg1Url())) {
			return JsonResult.fail("商品名不能为没有描述图片");
		}
		
		if(goods.getLabel() == null || goods.getLabel().length ==0) {
			return JsonResult.fail("商品不能没有标签");
		}
		
		goods.setMerchantInformationId(user.getMerchantId());
		if(goods.getShelveTime() == null) {
			goods.setShelveTime(new Date());
		}
		
		//goods.setUnshelve(false);
		
		if(goods.getShelveTime().after(new Date())) {
			goods.setUnshelve(true);
		}		
		
		if(goods.getUnshelveTime() != null) {
			if(goods.getUnshelveTime().before(goods.getShelveTime())) {
				return JsonResult.fail("商品下架时间不能比上架时间早");
			}
			
			if(goods.getUnshelveTime().before(new Date())) {
				g.setUnshelve(true);
			}
		}
		
				
		//设置goods信息
		g.setId(goods.getId());
		g.setDescription(goods.getDescription());
		g.setDescriptionImg1Url(goods.getDescriptionImg1Url());
		
		if(!StringUtils.isEmpty(goods.getDescriptionImg2Url())) {
			g.setDescriptionImg2Url(goods.getDescriptionImg2Url());
		}
		if(!StringUtils.isEmpty(goods.getDescriptionImg3Url())) {
			g.setDescriptionImg3Url(goods.getDescriptionImg3Url());
		}
		if(!StringUtils.isEmpty(goods.getDescriptionImg4url())) {
			g.setDescriptionImg4url(goods.getDescriptionImg4url());
		}		
		
		
		if(goods.getShelveTime() != null) {
			g.setShelveTime(goods.getShelveTime());
		}
		
		if(goods.getUnshelveTime() != null) {
			g.setUnshelveTime(goods.getUnshelveTime());
		}
		if(goods.getUnshelve() != null) {
			g.setUnshelve(goods.getUnshelve());
		}
		
		
		
		goodsMapper.updateByPrimaryKeySelective(g);
		
		//添加商品标签
		JsonResult json = this.updateGoodsLable(user, g.getId(), goods.getLabel());
		if(!json.isStatus()) {
			throw new JsonResultException(json);
		}
		
		//添加商品详情
		json = updateGoodsDetail(user, g.getId(), goods);
		if(!json.isStatus()) {
			throw new JsonResultException(json);
		}
		
		return JsonResult.ok("更新成功", goods);
	}

	/**
	 * 下架商品
	 * @param user
	 * @param id
	 * @return
	 */
	public JsonResult unshelveGoods(User user, String goodsId) {
		JsonResult json = this.checkGoods(user, goodsId);
		if(!json.isStatus()) {
			return json;
		}
		TGoods g = goodsMapper.selectByPrimaryKey(goodsId);
		if(g.getUnshelve()) {
			return JsonResult.fail("商品已经下架");
		}
		
		g = new TGoods();
		g.setId(goodsId);
		g.setUnshelve(true);
		g.setUnshelveTime(new Date());
		goodsMapper.updateByPrimaryKeySelective(g);
		
		return JsonResult.ok("下架成功");
	}

	/**
	 * 检查商品是否属于当前商家
	 * @param user
	 * @param goodsId
	 * @return
	 */
	private JsonResult checkGoods(User user, String goodsId) {
		//检查参数
		if(StringUtils.isEmpty(goodsId)) {
			return JsonResult.fail("商品id不能为空");
		}
		
		//检查商品是否属于当前商家
		TGoods g = new TGoods();
		g.setId(goodsId);
		g.setMerchantInformationId(user.getMerchantId());
		g.setDeleted(false);
		
		Integer num = goodsMapper.selectCount(g);
		
		if(num<=0) {
			return JsonResult.fail("该商品不属于本商家");
		}	
		
		return JsonResult.ok();
	}

	/**
	 * 更改商品下架时间
	 * @param user
	 * @param goodsId
	 * @param unshelveTime
	 * @return
	 */
	public JsonResult updateUnshelveTime(User user, String goodsId, Date unshelveTime) {
		JsonResult json = this.checkGoods(user, goodsId);
		if(!json.isStatus()) {
			return json;
		}
		
		if(unshelveTime == null) {
			return JsonResult.fail("下架时间不能为空");
		}
		
		TGoods g = goodsMapper.selectByPrimaryKey(goodsId);
		//是否被管理员下架
		if(g.getUnshelveByAdmin()) {
			return JsonResult.fail("已经被管理员下架了。无法更改下架时间");
		}
		
		if(unshelveTime.before(new Date())) {
			return JsonResult.fail("下架时间不能早于现在时间");
		}
		
		boolean status = true;
		
		//是否已经下架
		if(g.getUnshelve()) {
			if(g.getShelveTime() == null) {
				g.setShelveTime(new Date());
			}
			if(g.getShelveTime().before(new Date()) && unshelveTime.after(new Date())) {
				status = false;
			}			
		}
		String msg = "";
		g = new TGoods();
		g.setId(goodsId);
		g.setUnshelveTime(unshelveTime);
		if(!status) {
			g.setUnshelve(status);
			msg = "更改下架时间为 " + DateFormatUtils.formatToDateAndTime(unshelveTime) +" ,该商品已经重新上线";
		}else {
			msg = "更改下架时间为 " + DateFormatUtils.formatToDateAndTime(unshelveTime);
		}
		
		goodsMapper.updateByPrimaryKeySelective(g);				
		
		return JsonResult.ok(msg);
	}

	/**
	 * 删除商品
	 * @param user
	 * @param id
	 * @return
	 */
	public JsonResult deleteGoods(User user, String goodsId) {
		//检查参数
		if(StringUtils.isEmpty(goodsId)) {
			return JsonResult.fail("商品id不能为空");
		}
		
		TGoods g = new TGoods();
		g.setDeleted(true);
		
		Example example = new Example(TGoods.class);
		Criteria c = example.createCriteria();
		c.andEqualTo("id", goodsId);
		c.andEqualTo("merchantInformationId", user.getMerchantId());
		
		Integer num = goodsMapper.updateByExampleSelective(g, example);
		if(num>0) {
			return JsonResult.ok("删除成功");
		}
		return JsonResult.fail("删除失败，数据异常");
	}
	
}
