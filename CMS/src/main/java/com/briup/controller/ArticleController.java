package com.briup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.briup.bean.Article;
import com.briup.service.IArticleService;
import com.briup.utils.CustomerException;
import com.briup.utils.Message;
import com.briup.utils.MessageUtil;
import com.briup.utils.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
/**
 * 与文章相关的  和前端交互的web层
 * @author i子陌
 *
 */
@RestController
@Api(description = "文章相关接口")
public class ArticleController {
	@Autowired
	private IArticleService articleService;
	@PostMapping("/addArticle")
	@ApiOperation("添加文章信息")
	public Message<String> saveArticle(Article article){
		try {
			articleService.saveOrUpateArticle(article);
			return MessageUtil.success();
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误"+e.getMessage());
		}
	}
	
	@PostMapping("/updateArticle")
	@ApiOperation("修改文章信息")
	public Message<String> updateArticle(Article article){
		try {
			articleService.saveOrUpateArticle(article);
			return MessageUtil.success();
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误"+e.getMessage());
		}
	}
	@GetMapping("/deleteArticleById")
	@ApiOperation("根据id删除文章信息")
	public Message<String> deleteArticleById(int id){
		articleService.deleteArticleById(id);
		return MessageUtil.success();
	}
	@GetMapping("/findArticleByCondition")
	@ApiOperation("根据条件查询文章信息")
	public Message<List<Article>> getArticleByCondition(String keyStr,String condition){
		try {
			List<Article> list = articleService.findArticleCondition(keyStr, condition);
			return MessageUtil.success(list);
		} catch (CustomerException e) {
			e.printStackTrace();
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误"+e.getMessage());
		}
	}
	@GetMapping("/findArticleById")
	@ApiOperation("根据id条件查询文章信息")
	public Message<Article> getArticleById(int id){
		 Article article = articleService.findArticleById(id);
		return MessageUtil.success(article);
	}
}
