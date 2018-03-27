package com.zhuwenshen.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.zhuwenshen.exception.RedisException;
import com.zhuwenshen.mapper.TLoginHistoryMapper;
import com.zhuwenshen.mapper.TUserMapper;
import com.zhuwenshen.mapper.TUserMapperCustom;
import com.zhuwenshen.model.TLoginHistory;
import com.zhuwenshen.model.TUser;
import com.zhuwenshen.model.custom.JsonResult;
import com.zhuwenshen.model.custom.User;
import com.zhuwenshen.util.ContextUtils;
import com.zhuwenshen.util.MySid;
import com.zhuwenshen.util.ValidResultUtils;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class UserService {

	private static Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private TUserMapperCustom userMapperCustom;

	@Autowired
	private TUserMapper userMapper;

	//@Autowired
	//private RedisService redisService;

	@Autowired
	private FrozenInformationService frozenInformationService;

	@Autowired
	private TLoginHistoryMapper loginHistoryMapper;
	
	
	private String contextPath = "";

	/**
	 * 验证手机号是否已经注册，或者是登录ID
	 * 
	 * @param phone
	 * @return
	 */
	public String validPhoneForRegister(String phone) {
		int num = userMapperCustom.selectCountPhoneOrLoginId(phone);

		if (num == 0) {
			return ValidResultUtils.ok();
		}

		return ValidResultUtils.failed();
	}

	/**
	 * 验证手机号是否已经注册，或者是登录ID
	 * 
	 * @param phone
	 * @return
	 */
	public String validLoginIdForLogin(String phone) {
		int num = userMapperCustom.selectCountPhoneOrLoginId(phone);

		if (num == 0) {
			return ValidResultUtils.failed();
		}

		return ValidResultUtils.ok();
	}

	/**
	 * 用户注册
	 * 
	 * @param phone
	 * @param password
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public JsonResult register(String phone, String password) {
		log.debug("注册开始phone=" + phone + ",password=" + password);
		// 验证手机号是否已经注册
		int num = userMapperCustom.selectCountPhoneOrLoginId(phone);
		if (num > 0) {
			return JsonResult.fail("手机号：" + phone + " 已经注册！");
		}

		// 在t_user表中增加记录
		TUser user = new TUser();
		user.setName("用户" + MySid.nextInt());
		user.setPhone(phone);
		// 设置默认头像
		user.setHeadPortraitUrl("");
		user.setIntegral(new BigDecimal(0));
		user.setAllIntegral(new BigDecimal(0));
		user.setFund(new BigDecimal(0));
		// 设置性别为隐藏
		user.setSex(3);
		// 设置用户类型为普通用户
		user.setUserType(2);
		user.setLoginId(phone);
		user.setLoginPassworld(password);
		user.setFrozen(false);
		userMapper.insertSelective(user);
		log.info("注册完成");

		return JsonResult.ok("注册成功");
	}

	/**
	 * 登录
	 * @param session 
	 * @param loginId
	 * @param password
	 * @param client_type
	 * @param ip
	 * @param location
	 * @return
	 */
	public JsonResult login(HttpSession session, String loginId, String password, String clientType, String ip, String location) {

		if (StringUtils.isEmpty(loginId)) {
			return JsonResult.fail("账号不能为空");
		}

		if (StringUtils.isEmpty(password)) {
			return JsonResult.fail("密码不能为空");
		}

		if (StringUtils.isEmpty(clientType)) {
			return JsonResult.fail("客户端类型不能为空");
		}

		try {
			Integer.valueOf(clientType);
		} catch (NumberFormatException e1) {
			return JsonResult.fail("客户端类型为整数");
		}

		TUser tuser = new TUser();
		tuser.setLoginId(loginId);
		tuser.setLoginPassworld(password);
		tuser.setDeleted(false);
		if (userMapper.selectCount(tuser) <= 0) {
			return JsonResult.fail("密码错误");
		}

		tuser = userMapper.select(tuser).get(0);

		// 判断是否冻结
		if (tuser.getFrozen()) {
			Date date = frozenInformationService.isFrozen(tuser.getId());
			if (date != null) {
				return JsonResult.fail("账号 " + loginId + " 已被冻结，解封时间为 " + date);
			} else {
				TUser u2 = new TUser();
				u2.setId(tuser.getId());
				u2.setFrozen(false);
				userMapper.updateByPrimaryKeySelective(u2);
			}
		}

		User u = getUserByTUser(tuser);
				
		// TODO 这是权限url
		// u.setUrls( );

		String token = MySid.nextLong();		
		
				
		/*//检查该用户是否存在有用的token
		TLoginHistory l2 = loginHistoryMapper.selectUsefulByUserIdAndClientType(u.getId(), Integer.valueOf(clientType));
		if(l2!=null) {
			//在redis中删除有用的token
			redisService.deleteSession(l2.getToken());
		}
		
		try {
			token = redisService.setObjectLong(null, u, u.getClass(), RedisKind.SESSION).getToken();
		} catch (RedisException e) {
			//return JsonResult.fail("服务器异常，请联系管理员," + e.getMsg());
			log.error("redis服务器异常");
			token = MySid.nextLong();
		}*/
		
		
		//存放到session
		session.setAttribute("user", u);
		
		
		//更改以前的登录历史为无效
		TLoginHistory lh= new TLoginHistory();
		lh.setUseful(false);
		Example example = new Example(TLoginHistory.class);
		Criteria criteria  = example.createCriteria();
		criteria.andEqualTo("userId", u.getId());
		criteria.andEqualTo("clientType", Integer.valueOf(clientType));
		criteria.andEqualTo("useful", true);		
		loginHistoryMapper.updateByExampleSelective(lh, example);

		// 插入登录历史
		TLoginHistory loginHistory = new TLoginHistory();
		loginHistory.setUserId(tuser.getId());
		loginHistory.setClientType(Integer.valueOf(clientType));
		loginHistory.setLoginTime(new Date());
		loginHistory.setToken(token);
		loginHistory.setIp(ip);
		loginHistory.setUseful(true);
		loginHistory.setLocation(location);

		loginHistoryMapper.insertSelective(loginHistory);
		log.debug("登录成功");
		
		
		
		
		Map<String, String> data = new HashMap<String, String>();
		data.put("t", token);

		contextPath = ContextUtils.contextPath;
		String uri = contextPath+"/login";
		switch (u.getUserType()) {
		case (1):
			uri = contextPath+"/index";
			break;
		case (2):
			uri = contextPath+"/index";
			break;
		case (3):
			uri = contextPath+"/m/index";
			break;
		case (4):
			uri = contextPath+"/m/index";
			break;
		case (5):
			uri = contextPath+"/a/index";
			break;
		default:
			uri = contextPath+"/a/index";
			break;
		}
		data.put("uri", uri);

		return JsonResult.ok("登录成功", data);
	}

	/**
	 * 获取登陆对象User
	 * 
	 * @param token
	 * @param httpSession 
	 * @return
	 * @throws RedisException
	 */
	public User getSession(String token, HttpSession session) {
		User u = null;	
		
		// 到登录历史中查询最新登录
		TLoginHistory loginHistory = loginHistoryMapper.selectByUsefulToken(token);
		if(loginHistory!=null) {
			u = getUserByTUser(userMapper.selectByPrimaryKey(loginHistory.getUserId()));
		}
		//将u放到session中		
		if(u!=null) {
			session.setAttribute("user", u);
		}

		return u;
	}

	public User getUserByTUser(TUser t) {
		User u = new User(t);
		// TODO 这是权限url
		// u.setUrls( );
		return u;
	}

}
