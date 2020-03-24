package com.briup.service;
/**
 * 栏目相关的Service层
 * @author i子陌
 *
 */

import java.util.List;

import com.briup.bean.Article;
import com.briup.bean.Category;
import com.briup.bean.ex.CategoryEx;
import com.briup.utils.CustomerException;

public interface ICategoryService {
	/**
	 * 查询所有的栏目
	 */
	public List<Category> findAllCategorys()  throws CustomerException;
	
	/**
	 * 添加或修改栏目信息
	 */
	public void saveOrUpdateCategory(Category category) throws CustomerException;
	/**
	 * 根据id删除栏目信息
	 */
	public void deleteCategoryById(int id) throws CustomerException;
	/**
	 * 根据id查找指定的栏目信息
	 */
	public Category findCategoryById(int id) throws CustomerException;
	/**
	 * 查询栏目信息并且级联查询包含的文章信息
	 */
	public List<CategoryEx> findAllCategoryEx() throws CustomerException;
	/**
	 * 通过id查询对应栏目及其包含的信息
	 */
	public CategoryEx findCategoryExById(int id) throws CustomerException;
	
}
