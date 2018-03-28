package com.zhuwenshen.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhuwenshen.mapper.TGlobalConstantMapper;
import com.zhuwenshen.model.TGlobalConstant;
import com.zhuwenshen.model.custom.JsonResult;
import com.zhuwenshen.model.custom.QueryConstant;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class ConstantService {
	
	Logger log= LoggerFactory.getLogger(ConstantService.class);

	@Autowired
	private  TGlobalConstantMapper gcm;
	
	/**
	 * 根据kind获取kindName
	 * @param kind
	 * @return
	 */
	public String getKindNameByKind(String kind) {
		
		TGlobalConstant record = new TGlobalConstant();
		record.setKind(kind);
		record.setDeleted(false);
		List<TGlobalConstant> list = gcm.select(record);
		if(list.isEmpty()) {
			return null;
		}
		
		return list.get(0).getKindName();
		
	}

	/**
	 * 增加常量
	 * @param gc
	 * @return
	 */
	public JsonResult addConstant(TGlobalConstant gc) {		
		
		//检查是否为空
		if(gc == null) return JsonResult.fail("对象为null，插入失败");
		if(StringUtils.isEmpty(gc.getKind())) {
			return JsonResult.fail("分类(英文)不能为空");
		}
		if(StringUtils.isEmpty(gc.getKindName())) {
			return JsonResult.fail("分类(中文)不能为空");
		}
		if(StringUtils.isEmpty(gc.getName())) {
			return JsonResult.fail("变量名称(英文)不能为空");
		}
		
		if(StringUtils.isEmpty(gc.getNameCn())) {
			return JsonResult.fail("变量名称(中文)不能为空");
		}
		
		if(StringUtils.isEmpty(gc.getValue1())) {
			return JsonResult.fail("变量值不能为空");
		}
		
		if(gc.getUserful() == null) {
			return JsonResult.fail("有效值不能为空");
		}
		
		//检查kind跟kindName是否匹配
		String s = getKindNameByKind(gc.getKind());
		if(s!=null&&!gc.getKindName().equals(s)) {
			return JsonResult.fail("分类(中文)与已存在的不一致，应为 " + s);
		}	
		
		//检查是否存在相同的记录，即kind，name相同
		TGlobalConstant record = new TGlobalConstant();
		record.setKind(gc.getKind());
		record.setName(gc.getName());
		record.setDeleted(false);
		
		int num = gcm.selectCount(record);
		if(num>=1) {
			return JsonResult.fail("已存在相同分类和变量名的常量");
		}
		
		//插入数据
		gcm.insert(gc);
		
		//TODO 缓存到redis中
		
		
		return JsonResult.ok("保存成功");
	}

	/**
	 * 获取最近插入或更改的常量记录（三天）
	 * @return
	 */
	public JsonResult getConstantChangedLately() {
		Example example = new Example(TGlobalConstant.class);
		example.setCountProperty("15");
		example.setOrderByClause("update_time DESC");
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("deleted", false)
				.andGreaterThan("updateTime", new Date(new Date().getTime() - (long)3 * 24 * 60 * 60 * 1000));
		
		List<TGlobalConstant> list = gcm.selectByExample(example);
		log.debug("获取最近插入或更改的常量记录:\n"+list);
		
		return JsonResult.ok("获取成功", list);
	}

	/**
	 * 分页查询常量记录
	 * @param qc
	 * @return
	 */
	public String queryConstant(QueryConstant qc) {
		if(qc == null) {
			qc = new QueryConstant();
			qc.setPageNum(1);
			qc.setPageSize(15);
		}
		// 开始分页
        PageHelper.startPage(qc.getPageNum(), qc.getPageSize());
        Example example = new Example(TGlobalConstant.class);
        Criteria c =  example.createCriteria();
        if(!StringUtils.isEmpty(qc.getKind())) {
        	c.andLike("kind","%"+qc.getKind()+"%");
        }
        if(!StringUtils.isEmpty(qc.getKindName())) {
        	c.andLike("kindName","%"+qc.getKindName()+"%");
        }
        if(!StringUtils.isEmpty(qc.getName())) {
        	c.andLike("name","%"+qc.getName()+"%");
        }
        if(!StringUtils.isEmpty(qc.getNameCn())) {
        	c.andLike("nameCn","%"+qc.getNameCn()+"%");
        }
        if(qc.getUseful() != null) {
        	c.andEqualTo("useful", qc.getUseful());
        }
        c.andEqualTo("deleted", false);
        
        
        example.orderBy("kind").asc();
        example.orderBy("value1").asc();
        
        Page<TGlobalConstant> page = (Page<TGlobalConstant>) gcm.selectByExample(example);        
		
		return JsonResult.okToPageList(page);
	}
	
	
}
