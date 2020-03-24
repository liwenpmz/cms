package com.briup.service;

import java.util.List;

import com.briup.bean.Link;
import com.briup.utils.CustomerException;

/**
 * 关于链接的相关操作
 * @author i子陌
 *
 */
public interface ILinkService {
	/**
	 * 保持或者修改链接信息
	 * @param link
	 */
	void saveOrUpdateLink(Link link) throws CustomerException;
	
	
	/**
	 * 查询所有链接信息
	 */
	List<Link> findAllLinks() throws CustomerException;
	/**
	 * 根据 链接名 查询链接
	 */
	List<Link> findLinksByName(String name) throws CustomerException;
	/**
	 * 根据id删除对应链接
	 */
	void deleteLinkById(int id)throws CustomerException;
}
