package com.briup.bean.ex;

import java.io.Serializable;
import java.util.List;

import com.briup.bean.Link;

/**
 * 保持首页的所有数据
 * @author i子陌
 *
 */
public class IndexResult implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<CategoryEx> categoryExs;
	private List<Link> Links;
	public IndexResult() {
	}
	public IndexResult(List<CategoryEx> categoryExs, List<Link> links) {
		super();
		this.categoryExs = categoryExs;
		Links = links;
	}

	public List<CategoryEx> getCategoryExs() {
		return categoryExs;
	}
	public void setCategoryExs(List<CategoryEx> categoryExs) {
		this.categoryExs = categoryExs;
	}
	public List<Link> getLinks() {
		return Links;
	}
	public void setLinks(List<Link> links) {
		Links = links;
	}
	
}
