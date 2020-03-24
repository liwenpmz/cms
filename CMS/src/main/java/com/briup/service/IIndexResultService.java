package com.briup.service;

import java.util.List;

import com.briup.bean.Article;
import com.briup.bean.ex.IndexResult;
import com.briup.utils.CustomerException;

/**+
 * 首页数据管理的Service层接口
 * @author i子陌
 *
 */
public interface IIndexResultService {
	/**
	 * 查询首页的所有数据
	 * @return
	 * @throws CustomerException
	 */
	IndexResult findIndexAllResult() throws CustomerException;
	
	
}
