package com.zhuwenshen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhuwenshen.exception.JsonResultException;
import com.zhuwenshen.service.LabelService;

@Controller
public class LabelController {

	@Autowired
	private LabelService service;
	
	@GetMapping("/label/get1Level")
	@ResponseBody
	public String get1Level() {
		return service.get1Level().toString();
	}
	
	@PostMapping("/label/add")
	@ResponseBody
	public String add(String labelId1, String[] addLabelName) {
		try {
			return service.add(labelId1,  addLabelName).toString();
		} catch (JsonResultException e) {
			return e.getJson().toString();
		}
	}
	
	@GetMapping("/label/getLabel/{labelId}")
	@ResponseBody
	public String getLabelById(@PathVariable("labelId")String labelId) {
		try {
			return service.getLabelById(labelId).toString();
		} catch (JsonResultException e) {
			return e.getJson().toString();
		}
	}
}
