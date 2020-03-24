package com.briup.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.bean.Article;
import com.briup.bean.ArticleExample;
import com.briup.bean.Category;
import com.briup.bean.CategoryExample;
import com.briup.bean.Link;
import com.briup.bean.ex.CategoryEx;
import com.briup.bean.ex.IndexResult;
import com.briup.mapper.ArticleMapper;
import com.briup.service.ICategoryService;
import com.briup.service.IIndexResultService;
import com.briup.service.ILinkService;
import com.briup.utils.CustomerException;

/**
 * 查询首页所有数据的实现类
 * @author i子陌
 *
 */
@Service
public class IndexResultServiceImpl implements IIndexResultService {
	
	//关联超链接的service层接口
	@Autowired
	private ILinkService linkService;
	//关联文章的service层接口
	@Autowired
	private ICategoryService categoryService;
	@Override
	public IndexResult findIndexAllResult() throws CustomerException {
		
		IndexResult indexResult = new IndexResult();
		//设置所有的超链接信息
		indexResult.setLinks(linkService.findAllLinks());
		//设置所有的栏目及其包含的所有文章信息
		List<CategoryEx> categoryExs = categoryService.findAllCategoryEx();
		indexResult.setCategoryExs(categoryExs);
		return indexResult;
	}


}
