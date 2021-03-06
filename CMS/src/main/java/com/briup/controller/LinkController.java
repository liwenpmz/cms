package com.briup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.bean.Link;
import com.briup.service.ILinkService;
import com.briup.utils.CustomerException;
import com.briup.utils.Message;
import com.briup.utils.MessageUtil;
import com.briup.utils.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 与链接相关的 和前端交互的web层
 * @author i子陌
 *
 */

//生成json格式的数据信息
@RestController
@Api(description = "链接相关接口")
public class LinkController {
	@Autowired
	private ILinkService ilinkService;
	
	@PostMapping("/addLink")
	@ApiOperation("新增链接")
	public Message<String> addLink(Link link) {
		
		try {
			ilinkService.saveOrUpdateLink(link);
			return MessageUtil.success();
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_CODE, "系统错误："+e.getMessage());
		}
		
	}
	@PostMapping("/updateLink")
	@ApiOperation("修改链接信息")
	public	Message<String> updateLink(Link link){
			ilinkService.saveOrUpdateLink(link);
			return MessageUtil.success();
		
	}
	@GetMapping("selectLinks")
	@ApiOperation("查询所有链接")
	public Message<List<Link>> selectLinks(){
		List<Link> list = ilinkService.findAllLinks();
		return MessageUtil.success(list);
	}

	@GetMapping("/deleteLinkById")
	@ApiOperation("根据id删除链接")
	public Message<String> deleteById(int id){
		ilinkService.deleteLinkById(id);
		return MessageUtil.success();
	}
	@GetMapping("/selectLinkByName")
	@ApiOperation("根据链接名查询")
	public Message<List<Link>> selectLinkByName(String name){
		List<Link> list = ilinkService.findLinksByName(name);
		return MessageUtil.success(list);
	}
}
