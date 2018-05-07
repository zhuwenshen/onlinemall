package com.zhuwenshen.service.merchant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.zhuwenshen.mapper.MerchantGoodsLabelMapperCustom;
import com.zhuwenshen.mapper.MerchantGoodsMapperCustom;
import com.zhuwenshen.mapper.TGoodsLabelMapper;
import com.zhuwenshen.mapper.TLabelMapper;
import com.zhuwenshen.model.TGoodsLabel;
import com.zhuwenshen.model.TLabel;

@Service
public class MerchantGoodsLabelService {

	@Autowired
	private TGoodsLabelMapper goodsLabelMapper;
		
	@Autowired
	private TLabelMapper labelMapper;
	
	@Autowired
	private MerchantGoodsMapperCustom mgmc;
	
	@Autowired
	private MerchantGoodsLabelMapperCustom mglmc;;
	
	/**
	 * 给商品添加标签
	 * @param goodsId
	 * @param labelId
	 */
	public void addGoodsLabel(String goodsId , String labelId) {
		if(StringUtils.isEmpty(goodsId)) {
			return ;
		}
		
		if(StringUtils.isEmpty(labelId)) {
			return;
		}
		
		TLabel label =  labelMapper.selectByPrimaryKey(labelId);
		if(label == null) {			
			return;
		}
		if(label.getDeleted()) {
			return;
		}
		
		TGoodsLabel record = new TGoodsLabel();
		record.setGoodsId(goodsId);
		record.setLabelId(label.getId());
		record.setDeleted(false);
		
		Integer num = goodsLabelMapper.selectCount(record);
		
		if(num == 0) {
			goodsLabelMapper.insert(record);
		}
	}
	
	/**
	 * 获取一个商品的标签
	 * @param goodsId
	 * @return
	 */
	public List<?> queryGoosLabel(String goodsId) {
		return mgmc.selectGoodsLabel(null, goodsId);
	}

	/**
	 * 删除不存在的标签
	 * @param goodsId
	 * @param labelIds
	 * @return 
	 */
	public Integer deleteGoodsLabelNotInLabels(String goodsId, String[] labelIds) {
		//return  mglmc.deleteGoodsLabelNotInLabels(goodsId, labels);		
		return  mglmc.deleteGoodsLabelNotInLabelIds(goodsId, labelIds);	
	}
}
