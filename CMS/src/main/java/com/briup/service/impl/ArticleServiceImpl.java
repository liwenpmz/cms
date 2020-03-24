package com.briup.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.bean.Article;
import com.briup.bean.ArticleExample;
import com.briup.bean.Category;
import com.briup.bean.CategoryExample;
import com.briup.mapper.ArticleMapper;
import com.briup.mapper.CategoryMapper;
import com.briup.service.IArticleService;
import com.briup.utils.CustomerException;
import com.briup.utils.StatusCodeUtil;

/**
 * 实现文章管理相关的逻辑类
 * @author i子陌
 *
 */
@Service
public class ArticleServiceImpl implements IArticleService{
	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	private CategoryMapper categoryMapper;
	@Override
	public void saveOrUpateArticle(Article article) throws CustomerException {
		if(article==null) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE, "参数为空");
			
		}
		if(article.getId()==null) {
			//需要额外添加两条数据
			article.setPublishdate(new Date());
			article.setClicktimes(0);
			articleMapper.insert(article);
		}else {
			//article.setPublishdate(new Date());
			articleMapper.updateByPrimaryKey(article);
		}
		
			
	}

	@Override
	public void deleteArticleById(int id) throws CustomerException {
		articleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Article> findArticleCondition(String keyStr, String condition) throws CustomerException {
		/*分三种情况
		 * 1.没有添加任何条件，则查询所以文章
		 * 2.没有指定栏目，但指定了查询的关键字，则根据关键字查询满足的所有文章；
		 * 3.指定栏目，同时指定查询的关键字，则根据栏目和关键字查询满足条件的文章。
		 * 4.指定栏目，没有指定查询的关键字，则根据栏目查询满足条件的文章。
		*/
		keyStr=keyStr==null?"":keyStr.trim();
		condition=condition==null?"":condition.trim();
		ArticleExample example = new ArticleExample();
		
		if("".equals(keyStr) && "".equals(condition) ) {
			//情况1
			return articleMapper.selectByExample(example);
		}else if( !"".equals(keyStr) && "".equals(condition)) {
			//情况2
			example.createCriteria().andTitleLike("%"+keyStr+"%");
			example.or().andAuthorLike("%"+keyStr+"%");
			return articleMapper.selectByExample(example);
		}else if(!"".equals(condition)&& "".equals(keyStr)) {
			//情况4
			
			CategoryExample categoryExample = new CategoryExample();
			categoryExample.createCriteria().andNameEqualTo(condition);
			List<Category> category = categoryMapper.selectByExample(categoryExample);
			if(category.size()>0) {
				//根据栏目信息找到里面所有的文章
				example.createCriteria().andCategoryIdEqualTo(category.get(0).getId());
			}else {
				
				throw new CustomerException(StatusCodeUtil.ERROR_CODE, "没有制定的搜索栏目");
			}
			
			return articleMapper.selectByExample(example);
					
		}else {//情况3
			CategoryExample categoryExample = new CategoryExample();
			categoryExample.createCriteria().andNameEqualTo(condition);
			List<Category> category = categoryMapper.selectByExample(categoryExample);
			//and 的方式拼接条件
			example.createCriteria().andCategoryIdEqualTo(category.get(0).getId()).
			andTitleLike("%"+keyStr+"%");
			//or 拼接
			//example.or().andTitleLike("%"+keyStr+"%");
			if(category.size()>0) {
				//根据栏目信息找到里面所有的文章
				example.createCriteria().andCategoryIdEqualTo(category.get(0).getId());
			}else {
				
				throw new CustomerException(StatusCodeUtil.ERROR_CODE, "没有制定的搜索栏目");
			}
			return articleMapper.selectByExample(example);
		}
	}

	@Override
	public Article findArticleById(int id) throws CustomerException {
		Article article = articleMapper.selectByPrimaryKey(id);
		//添加点击次数
		Integer clicktimes =article.getClicktimes()==null?0:article.getClicktimes();
		article.setClicktimes(clicktimes+1);
		this.saveOrUpateArticle(article);
		return article;
	}

}
