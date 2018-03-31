package com.zhuwenshen.service;

import java.util.Arrays;
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
import com.zhuwenshen.mapper.TGlobalConstantMapperCustom;
import com.zhuwenshen.model.TGlobalConstant;
import com.zhuwenshen.model.custom.JsonResult;
import com.zhuwenshen.model.custom.QueryConstant;
import com.zhuwenshen.model.custom.UpdateConstant;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class ConstantService {

	Logger log = LoggerFactory.getLogger(ConstantService.class);

	@Autowired
	private TGlobalConstantMapper gcm;

	@Autowired
	private TGlobalConstantMapperCustom gcmc;

	/**
	 * 根据kind获取kindName
	 * 
	 * @param kind
	 * @return
	 */
	public String getKindNameByKind(String kind) {

		TGlobalConstant record = new TGlobalConstant();
		record.setKind(kind);
		record.setDeleted(false);
		List<TGlobalConstant> list = gcm.select(record);
		if (list.isEmpty()) {
			return null;
		}

		return list.get(0).getKindName();

	}

	/**
	 * 增加常量
	 * 
	 * @param gc
	 * @return
	 */
	public JsonResult addConstant(TGlobalConstant gc) {

		// 检查是否为空
		if (gc == null)
			return JsonResult.fail("对象为null，插入失败");
		if (StringUtils.isEmpty(gc.getKind())) {
			return JsonResult.fail("分类(英文)不能为空");
		}
		if (StringUtils.isEmpty(gc.getKindName())) {
			return JsonResult.fail("分类(中文)不能为空");
		}
		if (StringUtils.isEmpty(gc.getName())) {
			return JsonResult.fail("变量名称(英文)不能为空");
		}

		if (StringUtils.isEmpty(gc.getNameCn())) {
			return JsonResult.fail("变量名称(中文)不能为空");
		}

		if (StringUtils.isEmpty(gc.getValue1())) {
			return JsonResult.fail("变量值不能为空");
		}

		if (gc.getUseful() == null) {
			return JsonResult.fail("有效值不能为空");
		}

		// 检查kind跟kindName是否匹配
		String s = getKindNameByKind(gc.getKind());
		if (s != null && !gc.getKindName().equals(s)) {
			return JsonResult.fail("分类(中文)与已存在的不一致，应为 " + s);
		}

		// 检查是否存在相同的记录，即kind，name相同
		TGlobalConstant record = new TGlobalConstant();
		record.setKind(gc.getKind());
		record.setName(gc.getName());
		record.setDeleted(false);

		int num = gcm.selectCount(record);
		if (num >= 1) {
			return JsonResult.fail("已存在相同分类和变量名的常量");
		}

		// 插入数据
		gcm.insert(gc);

		// TODO 缓存到redis中

		return JsonResult.ok("保存成功");
	}

	/**
	 * 获取最近插入或更改的常量记录（三天）
	 * 
	 * @return
	 */
	public JsonResult getConstantChangedLately() {
		Example example = new Example(TGlobalConstant.class);
		example.setCountProperty("15");
		example.setOrderByClause("update_time DESC");
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("deleted", false).andGreaterThan("updateTime",
				new Date(new Date().getTime() - (long) 3 * 24 * 60 * 60 * 1000));

		List<TGlobalConstant> list = gcm.selectByExample(example);
		log.debug("获取最近插入或更改的常量记录:\n" + list);

		return JsonResult.ok("获取成功", list);
	}

	/**
	 * 分页查询常量记录
	 * 
	 * @param qc
	 * @return
	 */
	public String queryConstant(QueryConstant qc) {
		System.out.println(qc);

		if (qc == null) {
			qc = new QueryConstant();
			qc.setPageNum(1);
			qc.setPageSize(10);
		}
		// 开始分页
		PageHelper.startPage(qc.getPageNum(), qc.getPageSize());
		Example example = new Example(TGlobalConstant.class);
		Criteria c = example.createCriteria();
		if (!StringUtils.isEmpty(qc.getKind())) {
			c.andLike("kind", "%" + qc.getKind() + "%");
		}
		if (!StringUtils.isEmpty(qc.getKindName())) {
			c.andLike("kindName", "%" + qc.getKindName() + "%");
		}
		if (!StringUtils.isEmpty(qc.getName())) {
			c.andLike("name", "%" + qc.getName() + "%");
		}
		if (!StringUtils.isEmpty(qc.getNameCn())) {
			c.andLike("nameCn", "%" + qc.getNameCn() + "%");
		}
		if (qc.getUseful() != null) {
			c.andEqualTo("useful", qc.getUseful());
		}
		c.andEqualTo("deleted", false);

		example.orderBy("kind").asc();
		example.orderBy("value1").asc();

		Page<TGlobalConstant> page = (Page<TGlobalConstant>) gcm.selectByExample(example);

		return JsonResult.okToPageList(page);
	}

	/**
	 * 根据id查询常量（用于更新）
	 * 
	 * @param idU
	 * @return
	 */
	public JsonResult getConstantById(String idU) {
		UpdateConstant uc = gcmc.getConstantById(idU);
		if (uc != null) {
			return JsonResult.ok("", uc);
		}
		return JsonResult.fail("找不到查找常量");
	}

	/**
	 * 单个更新常量
	 * 
	 * @param uc
	 * @return
	 */
	public JsonResult updateConstant(UpdateConstant uc) {
		if (uc == null)
			return JsonResult.fail("对象为null，插入失败");		
		
		TGlobalConstant gc = new TGlobalConstant();
		gc.setId(uc.getIdU());
		gc.setKind(uc.getKindU());
		gc.setKindName(uc.getKindNameU());
		gc.setName(uc.getNameU());
		gc.setNameCn(uc.getNameCnU());
		gc.setRemake(uc.getRemakeU());
		gc.setUseful(uc.getUsefulU());
		gc.setValue1(uc.getValue1U());

		System.out.println(uc);
		System.out.println(gc);
		
		if (StringUtils.isEmpty(gc.getKind())) {
			return JsonResult.fail("分类(英文)不能为空");
		}
		if (StringUtils.isEmpty(gc.getKindName())) {
			return JsonResult.fail("分类(中文)不能为空");
		}
		if (StringUtils.isEmpty(gc.getName())) {
			return JsonResult.fail("变量名称(英文)不能为空");
		}

		if (StringUtils.isEmpty(gc.getNameCn())) {
			return JsonResult.fail("变量名称(中文)不能为空");
		}

		if (StringUtils.isEmpty(gc.getValue1())) {
			return JsonResult.fail("变量值不能为空");
		}

		if (gc.getUseful() == null) {
			return JsonResult.fail("有效值不能为空");
		}
		

		// 检查kind跟kindName是否匹配
		String s = getKindNameByKind(gc.getKind());
		if (s != null && !gc.getKindName().equals(s)) {
			return JsonResult.fail("分类(中文)与已存在的不一致，应为 " + s);
		}

		// 检查是否存在相同的记录，即kind，name相同，但是id不同
		Example example = new Example(TGlobalConstant.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("kind", gc.getKind());
		criteria.andEqualTo("name",gc.getName());
		criteria.andEqualTo("deleted", false);
		criteria.andNotEqualTo("id", gc.getId());		

		int num = gcm.selectCountByExample(example);
		if (num >= 1) {
			return JsonResult.fail("已存在相同分类和变量名的常量");
		}

		//更新数据
		gcm.updateByPrimaryKeySelective(gc);

		// TODO 删除redis缓存，更新缓存

		return JsonResult.ok("更新成功");
	}

	/**
	 * 删除常量
	 * @param id
	 * @return
	 */
	public JsonResult deleteConstantById(String id) {
		TGlobalConstant record = new TGlobalConstant();
		record.setId(id);
		record.setDeleted(true);
		
		int num = gcm.updateByPrimaryKeySelective(record);
		
		if(num<=0) {
			return JsonResult.fail("删除失败，数据错误");
		}
		return JsonResult.ok("删除成功，共删除 "+num +" 条数据");
	}

	/**
	 * 批量删除常量
	 * @param ids
	 * @return
	 */
	public JsonResult deleteConstantByIds(String ids) {
		
		if(StringUtils.isEmpty(ids)) {
			return JsonResult.fail("未选中删除项");
		}
		List<String> list = Arrays.asList(ids.split(","));
		
		TGlobalConstant record = new TGlobalConstant();	
		record.setDeleted(true);
		Example example = new Example(TGlobalConstant.class);
		Criteria criteria = example.createCriteria();
		criteria.andIn("id", list);		
		
		int num = gcm.updateByExampleSelective(record, example);
		
		if(num<=0) {
			return JsonResult.fail("删除失败，数据错误");
		}
		
		return JsonResult.ok("删除成功，共删除 "+num +" 条数据");
	}

	

}
