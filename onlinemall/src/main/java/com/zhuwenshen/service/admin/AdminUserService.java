package com.zhuwenshen.service.admin;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhuwenshen.mapper.AdminUserMapperCustom;
import com.zhuwenshen.mapper.TFrozenInformationMapper;
import com.zhuwenshen.mapper.TLoginHistoryMapper;
import com.zhuwenshen.mapper.TUserMapper;
import com.zhuwenshen.model.TFrozenInformation;
import com.zhuwenshen.model.TLoginHistory;
import com.zhuwenshen.model.TUser;
import com.zhuwenshen.model.custom.JsonResult;
import com.zhuwenshen.model.custom.User;
import com.zhuwenshen.model.custom.admin.QueryAdminUserParam;
import com.zhuwenshen.service.FrozenInformationService;
import com.zhuwenshen.service.RedisService;
import com.zhuwenshen.util.DateFormatUtils;
import com.zhuwenshen.util.GlobalSession;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class AdminUserService {
	
	@Autowired
	private AdminUserMapperCustom aumc;
	
	@Autowired
	private TUserMapper userMapper;
	
	@Autowired
	private FrozenInformationService frozenInformationService;
	
	@Autowired
	private TFrozenInformationMapper frozenInformationMapper;
	
	@Autowired
	private TLoginHistoryMapper loginHistoryMapper;
	
	@Autowired
	private RedisService redisService;
	
	public String queryUser(QueryAdminUserParam qaup) {
		if(qaup == null) {
			qaup = new QueryAdminUserParam();
		}
		
		// 开始分页
		Page<?> page = PageHelper.startPage(qaup.getPageNum(), qaup.getPageSize());
		aumc.selectAdminUser(qaup);			
		
		return JsonResult.okToPageList(page);
	}

	/**
	 * 冻结
	 * @param qaup
	 * @param user 
	 * @return
	 */
	@Transactional
	public JsonResult onFrozen(QueryAdminUserParam qaup, User user) {
		
		// 检查参数输入
		if(qaup == null) {
			return JsonResult.fail("参数输入错误");
		}
		
		if(StringUtils.isEmpty(qaup.getIdU())) {
			return JsonResult.fail("id不能为空");
		}
		
		if(StringUtils.isEmpty(qaup.getUnfreezingTimeU())) {
			return JsonResult.fail("解封时间不能空");
		}
		
		if(StringUtils.isEmpty(qaup.getFrozenReasonU())) {
			return JsonResult.fail("冻结理由不能空");
		}
		
		//解封时间不能小于当前时间
		if(qaup.getUnfreezingTimeU().before(new Date())) {
			return JsonResult.fail("解封时间不能小于当前时间");
		}
		
		
		//查找出当前用户
		TUser tuser = userMapper.selectByPrimaryKey(qaup.getIdU());
		
		if(tuser == null || tuser.getDeleted()) {
			return JsonResult.fail("该用户不存在");
		}
		
		if(tuser.getUserType().equals(6)) {
			return JsonResult.fail("超级管理员不能被冻结");
		}		
		
		//判断当前用户是否被冻结
		TFrozenInformation fi =  frozenInformationService.frozenInformation(qaup.getIdU());
		//冻结了把以前的解封时间为当前时间
		//更改被冻结记录的解封时间
		if(fi != null) {
			TFrozenInformation fiu = new TFrozenInformation();
			fiu.setUnfreezingTime(new Date());			
			
			Example example = new Example(TFrozenInformation.class);
			Criteria c = example.createCriteria();
			c.andEqualTo("frozenUserId", qaup.getIdU());
			c.andEqualTo("deleted", false);
			c.andGreaterThanOrEqualTo("unfreezingTime", new Date());
			
			frozenInformationMapper.updateByExampleSelective(fiu, example);
			
		}
		
		//在重新插入一条冻结记录
		TFrozenInformation fii = new TFrozenInformation();
		fii.setFrozenUserId(tuser.getId());
		fii.setOperatingUserId(user.getId());
		fii.setFrozenTime(new Date());
		fii.setFrozenReason(qaup.getFrozenReasonU());
		fii.setUnfreezingTime(qaup.getUnfreezingTimeU());		
		fii.setDeleted(false);
		
		frozenInformationMapper.insert(fii);
		
		//更改用户状态
		tuser.setFrozen(true);
		tuser.setUnfreezingTime(qaup.getUnfreezingTimeU());
		
		userMapper.updateByPrimaryKey(tuser);
		System.out.println("tuser:"+tuser);
		
		//TODO 推送用户被冻结
		
		//TODO 强制正在上线的用户下线（调用用户端接口）		
		//检查该用户是否存在有用的token
//		TLoginHistory l2 = new TLoginHistory();
//		l2.setDeleted(false);
//		l2.setUseful(true);
//		l2.setUserId(tuser.getId());
//		List<TLoginHistory> list = loginHistoryMapper.select(l2);
//		for(TLoginHistory l3 :list) {
//			//redisSession中删除
//			redisService.deleteSession(l3.getToken() , l3.getIp());
//			
//			//在session中删除
//			GlobalSession.invalidateSession(l3.getToken());
//		}
//				
//		// 更改冻结用户的登录历史为无效
//		TLoginHistory lh = new TLoginHistory();
//		lh.setUseful(false);
//		Example example = new Example(TLoginHistory.class);
//		Criteria criteria = example.createCriteria();
//		criteria.andEqualTo("userId", tuser.getId());
//		criteria.andEqualTo("useful", true);
//		loginHistoryMapper.updateByExampleSelective(lh, example);
		

		return JsonResult.ok("昵称："+tuser.getName()+" 冻结成功，解封时间为"+DateFormatUtils.formatToDateAndTime(qaup.getUnfreezingTimeU()));
	}

	/**
	 * 解封
	 * @param id
	 * @param user 
	 * @return
	 */
	@Transactional
	public JsonResult onUnfrozen(String id, User user) {
		
		if(StringUtils.isEmpty(id)) {
			JsonResult.fail("id不能为空");
		}
		
		//查找出当前对象
		TUser tuser = userMapper.selectByPrimaryKey(id);
		if(tuser == null || tuser.getDeleted()) {
			JsonResult.fail("该用户不存在");
		}
		
		//判断该用户是否被冻结
		TFrozenInformation fi =  frozenInformationService.frozenInformation(id);
		
		
		//更改被冻结记录的解封时间
		if(fi != null) {
			TFrozenInformation fiu = new TFrozenInformation();
			fiu.setUnfreezingTime(new Date());			
			
			Example example = new Example(TFrozenInformation.class);
			Criteria c = example.createCriteria();
			c.andEqualTo("frozenUserId", id);
			c.andEqualTo("deleted", false);
			c.andGreaterThanOrEqualTo("unfreezingTime", new Date());
			
			frozenInformationMapper.updateByExampleSelective(fiu, example);
			
		}
		
		
		//更改当前用户冻结状态
		tuser.setFrozen(false);
		tuser.setUnfreezingTime(null);
		
		userMapper.updateByPrimaryKey(tuser);
		
		return JsonResult.ok("昵称："+tuser.getName()+" 被解封成功");
	}

	/**
	 * 定时任务之解冻到期用户
	 */
	public void unfreezingUserTask() {
		TUser u = new TUser();
		u.setFrozen(false);
		
		Example example = new Example(TUser.class);
		Criteria c = example.createCriteria();
		c.andEqualTo("frozen", true);
		c.andEqualTo("deleted", false);
		c.andCondition("unfreezing_time < ", new Date());
		
		userMapper.updateByExampleSelective(u, example);
		
	}

}
