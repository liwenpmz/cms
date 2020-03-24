package com.briup.service;

import java.util.List;

import com.briup.bean.Article;
import com.briup.utils.CustomerException;

/**
 * 文章相关内容的service接口
 * @author i子陌
 *
 */
public interface IArticleService {
	/**
	 * 新增或修改文章
	 */
	public void saveOrUpateArticle(Article article) throws CustomerException;
	/**
	 * 删除文章
	 */
	public void deleteArticleById(int id) throws CustomerException;
	/**
	 * 
	 * @param keyStr 搜索框
	 * @param condition 栏目框
	 * @return
	 * @throws CustomerException
	 */
	public List<Article> findArticleCondition(String keyStr,String condition) throws CustomerException;
	/**
	 * 根据id查询文章
	 */
	public Article findArticleById(int id) throws CustomerException;
	/**
	 * 点击文章，显示详情页面。并且将相关数据展示。（文章的内容/标题/作者/时间/浏览次数）
	 */
	
}
