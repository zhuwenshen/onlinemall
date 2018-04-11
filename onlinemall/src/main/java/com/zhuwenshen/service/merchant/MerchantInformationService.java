package com.zhuwenshen.service.merchant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.zhuwenshen.mapper.MerchantImformatinMapperCustom;
import com.zhuwenshen.mapper.TMerchantInformationMapper;
import com.zhuwenshen.model.TMerchantInformation;
import com.zhuwenshen.model.custom.JsonResult;
import com.zhuwenshen.model.custom.User;
import com.zhuwenshen.model.custom.admin.QueryMerchantInformation;
import com.zhuwenshen.util.PicUtils;

@Service
public class MerchantInformationService {
	
	@Autowired
	private TMerchantInformationMapper mim;
	
	@Autowired
	private MerchantImformatinMapperCustom mimc;

	public String showMerchantInformation(User user) {
		List<QueryMerchantInformation> list = mimc.selectMerchantInformationById( user.getMerchantId());
		if(list == null || list.isEmpty()) {
			return JsonResult.fail("无已登陆的商家信息").toString();
		}
		System.out.println(list.get(0));
		return JsonResult.ok("", list.get(0)).toString();
	}

	/**
	 * 更换头像
	 * @param user
	 * @param imgurl
	 * @return
	 */
	@Transactional
	public JsonResult updateMerchantPortrait(User user, String imgurl) {
		if(StringUtils.isEmpty(imgurl)) {
			return JsonResult.fail("头像url参数不能为空");
		}
		
		TMerchantInformation mi  = new TMerchantInformation();
		mi.setMerchantPortraitUrl(imgurl);
		mi.setId(user.getMerchantId());
		
		mim.updateByPrimaryKeySelective(mi);
		
		return JsonResult.ok("更换成功",PicUtils.addPicIp(imgurl));
	}

	public JsonResult updateMerchantImformation(User user, QueryMerchantInformation qmi) {
		if(qmi == null) {
			return JsonResult.fail("请求参数错误");
		}
		TMerchantInformation mi  = new TMerchantInformation();
		if(StringUtils.isEmpty(qmi.getPhone())) {
			mi.setMerchantPortraitUrl(qmi.getPhone());
		}
		
		if(StringUtils.isEmpty(qmi.getDetailedAddress())) {
			mi.setMerchantPortraitUrl(qmi.getDetailedAddress());
		}
		
		mi.setId(user.getMerchantId());
		
		mim.updateByPrimaryKeySelective(mi);
		
		return JsonResult.ok("更新成功");
	}
	

	/**
	 * 更新图片描述
	 * @param user
	 * @param imgDes
	 * @return
	 */
	@Transactional
	public JsonResult updateImgDes(User user, String[] imgDes) {		
		
		if(imgDes == null ||imgDes.length<=0) {
			imgDes =  new String[4];
		}
		
		QueryMerchantInformation qmi = new QueryMerchantInformation();
		qmi.setId(user.getMerchantId());
		
		if(imgDes.length>=1 && !StringUtils.isEmpty(imgDes[0])) {
			qmi.setDescribingImg1Url(imgDes[0]);
		}
		
		if(imgDes.length>=2 && !StringUtils.isEmpty(imgDes[1])) {
			qmi.setDescribingImg2Url(imgDes[1]);
		}
		
		if(imgDes.length>=3 && !StringUtils.isEmpty(imgDes[2])) {
			qmi.setDescribingImg3Url(imgDes[2]);
		}
		
		if(imgDes.length>=4 && !StringUtils.isEmpty(imgDes[3])) {
			qmi.setDescribingImg4Url(imgDes[3]);
		}
		
		
		mimc.updateImgDes(qmi);
		

		
		return JsonResult.ok("操作成功");
	}

}
