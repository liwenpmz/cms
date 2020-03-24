package com.briup.service.impl;
/**
 * 栏目管理相关实现类
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.bean.Article;
import com.briup.bean.ArticleExample;
import com.briup.bean.Category;
import com.briup.bean.CategoryExample;
import com.briup.bean.ex.CategoryEx;
import com.briup.mapper.ArticleMapper;
import com.briup.mapper.CategoryMapper;
import com.briup.mapper.ex.CategoryExMapper;
import com.briup.service.ICategoryService;
import com.briup.utils.CustomerException;
import com.briup.utils.StatusCodeUtil;
@Service
public class CategoryServiceImpl implements ICategoryService {
	//栏目
	@Autowired
	private CategoryMapper categoryMapper;
	//文章
	@Autowired
	private ArticleMapper articleMapper;
	//栏目的拓展dao
	@Autowired
	private CategoryExMapper categoryExMapper;
	
	@Override
	public List<Category> findAllCategorys() throws CustomerException {
		
		return categoryMapper.selectByExample(new CategoryExample());
	}

	@Override
	public void saveOrUpdateCategory(Category category) throws CustomerException {
		//判断Category对象是否为空
		if(category==null) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE, "参数为空！");
		}
		//判断category对象的id是否为空，如果为空则新增，如果不为空则修改链接。
		if(category.getId()==null) {
			CategoryExample example = new CategoryExample();
			example.createCriteria().andCodeEqualTo(category.getCode());
			example.or(example.createCriteria().andNameEqualTo(category.getName()));
			List<Category> list = categoryMapper.selectByExample(example);
			if(list.size()>0)
			{
				throw new  CustomerException(StatusCodeUtil.NOFOUND_CODE, "栏目存在！");
			}
			categoryMapper.insert(category);
		}else {
			categoryMapper.updateByPrimaryKey(category);
			}
	}

	@Override
	public void deleteCategoryById(int id) throws CustomerException {
		//删除栏目的同时，需要先删除栏目包含的文章信息
		ArticleExample example = new ArticleExample();
		example.createCriteria().andCategoryIdEqualTo(id);
		articleMapper.deleteByExample(example);
		
		
		categoryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Category findCategoryById(int id) throws CustomerException {
		
		return categoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<CategoryEx> findAllCategoryEx() throws CustomerException {
		
		return categoryExMapper.findAllCategoryExs();
	}

	@Override
	public CategoryEx findCategoryExById(int id) throws CustomerException {
	
		return 	categoryExMapper.findCategoryExById(id);
	}



}
