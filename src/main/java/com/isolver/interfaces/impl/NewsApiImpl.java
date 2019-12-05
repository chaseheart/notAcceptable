package com.isolver.interfaces.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.isolver.common.constant.SysStatusCodeConst;
import com.isolver.common.util.Result;
import com.isolver.interfaces.NewsApi;
import com.isolver.service.wechat.NewsService;

@RestController
public class NewsApiImpl implements NewsApi {
	
	@Autowired
	private NewsService newsService;

	/**
	 * 新闻/通知/贴士一览
	 * @param type
	 * @return
	 */
	@Override
	public Result<Object> getNewsList(Integer type) {
		return new Result<>(SysStatusCodeConst.SUCCESS, newsService.getNewsList(type));
	}

	/**
	 * 新闻/通知/贴士详情
	 * @param id
	 * @return
	 */
	@Override
	public Result<Object> getNewsDetail(Long id) {
		return new Result<>(SysStatusCodeConst.SUCCESS, newsService.getNewsDetail(id));
	}

	/**
	 * 根据类型和内容检索 新闻/通知/贴士
	 * @param type
	 * @param content
	 * @return
	 */
	@Override
	public Result<Object> getNewsList(Integer type, String content) {
		return new Result<>(SysStatusCodeConst.SUCCESS, newsService.searchNews(content, type));
	}
	

}
