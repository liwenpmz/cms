package com.briup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.bean.Article;
import com.briup.bean.Category;
import com.briup.bean.Link;
import com.briup.bean.ex.CategoryEx;
import com.briup.service.ICategoryService;
import com.briup.utils.CustomerException;
import com.briup.utils.Message;
import com.briup.utils.MessageUtil;
import com.briup.utils.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 与栏目相关的  和前端交互的web层
 * @author i子陌
 *
 */
@RestController
@Api(description = "栏目相关接口")
public class CategoryContorller {
	@Autowired
	private ICategoryService iCategoryService;
	@PostMapping("/addCategory")
	@ApiOperation("新增栏目")
	public Message<String> addCategory(Category category){
		try {
			
			iCategoryService.saveOrUpdateCategory(category);
			
			return MessageUtil.success();
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误："+e.getMessage());
		}
	}
	@PostMapping("/updateCategory")
	@ApiOperation("修改栏目")
	public Message<String> updateCategory(Category category){
		try {
			iCategoryService.saveOrUpdateCategory(category);
			return MessageUtil.success();
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误："+e.getMessage());
		}
	}
	@GetMapping("/selectCategorys")
	@ApiOperation("查询所有栏目")
	public Message<List<Category>> selectCategorys(){
		List<Category> list = iCategoryService.findAllCategorys();
		return MessageUtil.success(list);
	}
	@GetMapping("/selectCategoryById")
	@ApiOperation("通过id查询栏目")
	public Message<Category> selectCategoryById(int id){
		return MessageUtil.success(iCategoryService.findCategoryById(id));
	}
	@GetMapping("/deleteCategoryById")
	@ApiOperation("通过id删除栏目")
	public Message<String> deleteCategoryById(int id){
		iCategoryService.deleteCategoryById(id);
		return MessageUtil.success();
	}
	@GetMapping("/findAllCategoryEx")
	@ApiOperation("选中栏目，然后展示其中文章")
	public Message<CategoryEx> findAllCategoryEx(int id){
	
		return 	MessageUtil.success(iCategoryService.findCategoryExById(id));
	}
	
}
