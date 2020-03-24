package com.briup.mapper.ex;
/**
 * 处理查询栏目及其包含的文章信息
 * @author i子陌
 *
 */

import java.util.List;

import com.briup.bean.ex.CategoryEx;

public interface CategoryExMapper {
	/**
	 * 实现查询所以栏目及其包含的文章信息
	 * @return
	 */
	List<CategoryEx>  findAllCategoryExs();
	/**
	 * 通过id查询对应栏目及其包含的信息
	 * @param id
	 * @return
	 */
	CategoryEx	findCategoryExById(Integer id);
}	
