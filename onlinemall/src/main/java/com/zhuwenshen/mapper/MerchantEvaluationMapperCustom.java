package com.zhuwenshen.mapper;

import java.util.List;

import com.zhuwenshen.model.custom.merchant.QueryEvaluation;
import com.zhuwenshen.model.custom.merchant.QueryEvaluationParam;

public interface MerchantEvaluationMapperCustom {

	public List<QueryEvaluation> selectEvaluation(QueryEvaluationParam param);
}
