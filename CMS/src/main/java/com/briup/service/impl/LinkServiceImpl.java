package com.briup.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.bean.Link;
import com.briup.bean.LinkExample;
import com.briup.bean.LinkExample.Criteria;
import com.briup.mapper.LinkMapper;
import com.briup.service.ILinkService;
import com.briup.utils.CustomerException;
import com.briup.utils.StatusCodeUtil;
/**
 * 保持链接sercice功能类
 * @author i子陌
 *
 */
// @Service 放到ioc容器中 事务管理
@Service
public class LinkServiceImpl implements ILinkService {
	@Autowired
	private LinkMapper linkMapper;
	@Override
	public void saveOrUpdateLink(Link link) throws CustomerException {
		//参数为引用类型，要判空处理
		if(link==null) {
			throw new CustomerException(StatusCodeUtil.ERROR_CODE, "参数为空！");
		}
		//判断link对象的id是否为空，如果为空则新增，如果不为空则修改链接。
		if(link.getId()==null) {
			linkMapper.insert(link);
		}else {
			linkMapper.updateByPrimaryKey(link);
		}
	}
	@Override
	public List<Link> findAllLinks() throws CustomerException {
		LinkExample example = new LinkExample();
		List<Link> list = linkMapper.selectByExample(example);
		return list;
	}
	@Override
	public List<Link> findLinksByName(String name) throws CustomerException {
		name= name==null ? "" : name.trim();
		LinkExample example = new LinkExample();
		if("".equals(name)) {
			//如果搜索条件没写，则返回所有数据
			return linkMapper.selectByExample(example);
		}else {
			//如果搜索条件不为null，则返回满足数据
			Criteria criteria = example.createCriteria();
			criteria.andNameLike("%"+name+"%");
			return linkMapper.selectByExample(example);
		}
		
	}
	@Override
	public void deleteLinkById(int id) throws CustomerException {
		linkMapper.deleteByPrimaryKey(id);
	}

}
