package com.zhuwenshen.controller.merchant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.zhuwenshen.exception.JsonResultException;
import com.zhuwenshen.model.custom.User;
import com.zhuwenshen.model.custom.merchant.QueryGoodsPriceParam;
import com.zhuwenshen.service.merchant.MerchantWarehouseService;

@Controller
public class MerchantWarehouseController {

	@Autowired
	private MerchantWarehouseService mws;
	
	@GetMapping("m/warehouse/manage")
	public String manage() {
		return "/m/warehouse/manage";
	}
	
	@GetMapping("m/warehouse/addGoodsPrice")
	public String addGoodsPrice() {
		return "/m/warehouse/add";
	}
	
	/**
	 * 根据是否被下架查找出商品的基本信息
	 * @param user
	 * @param unshelve
	 * @return
	 */
	@GetMapping("m/warehouse/listGoods")
	@ResponseBody
	public String listGoods(@SessionAttribute("user")User user, Boolean unshelve) {
		return mws.listGoods(user , unshelve).toString();
	}
	
	/**
	 * 根据商品的id查找出商品的分类信息
	 * @param goodsId
	 * @return
	 */
	@GetMapping("m/warehouse/listGoodsClass")
	@ResponseBody
	public String listGoodsClass(String goodsId) {
		try {
			return mws.listGoodsClass(goodsId).toString();
		} catch (JsonResultException e) {
			return e.getJson().toString();
		}
	}
	
	/**
	 * 分页查找商品价格信息
	 * @param qgpp
	 * @return
	 */
	@PostMapping("m/warehouse/queryGoodsPrice")
	@ResponseBody
	public String queryGoodsPrice (@SessionAttribute("user")User user ,QueryGoodsPriceParam qgpp) {
		try {
			return mws.queryGoodsPrice(user, qgpp).toString();
		} catch (JsonResultException e) {
			return e.getJson().toString();
		}
	}
	
	/**
	 * 保存或更新一个商品价格
	 * @param user
	 * @param qgpp
	 * @return
	 */
	@PostMapping("m/warehouse/saveGoodsPrice")
	@ResponseBody
	public String saveGoodsPrice (@SessionAttribute("user")User user ,QueryGoodsPriceParam qgpp) {
		try {
			return mws.saveGoodsPrice(user, qgpp).toString();
		} catch (JsonResultException e) {
			return e.getJson().toString();
		}
	}
	
	/**
	 * 根据商品id和商品的子分类信息查找出当前商品价格
	 * @param user
	 * @param qgpp
	 * @return
	 */
	@PostMapping("m/warehouse/selectGoodsPrice")
	@ResponseBody
	public String selectGoodsPrice (@SessionAttribute("user")User user ,QueryGoodsPriceParam qgpp) {
		try {
			return mws.selectGoodsPrice(user, qgpp).toString();
		} catch (JsonResultException e) {
			return e.getJson().toString();
		}
	}
	
}
