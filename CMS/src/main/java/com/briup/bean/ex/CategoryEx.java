package com.briup.bean.ex;

import java.io.Serializable;
import java.util.List;

import com.briup.bean.Article;

import io.swagger.annotations.ApiParam;

/**
 * 栏目的扩展类
 * 	除了栏目信息，还会级联保持栏目对应的所有文章信息
 * @author i子陌
 *
 */
public class CategoryEx implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	@ApiParam(value ="栏目编号 ",required = true)
	private Long code;
	@ApiParam(value ="栏目名称 ",required = true)
    private String name;
	//添加级联的所有文章
	private List<Article> article;
	public CategoryEx() {
		// TODO Auto-generated constructor stub
	}
	
	public CategoryEx(Integer id, Long code, String name, List<Article> article) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.article = article;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Article> getArticle() {
		return article;
	}
	public void setArticle(List<Article> article) {
		this.article = article;
	}
	
}
