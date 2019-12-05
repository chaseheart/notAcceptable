package com.isolver.interfaces;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.isolver.common.util.Result;

@RequestMapping("/weixin/news")
public interface NewsApi {

	/**
	 * 新闻/通知/贴士一览
	 * @param type
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Result<Object> getNewsList(Integer type);
	
	/**
	 * 新闻/通知/贴士详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	public Result<Object> getNewsDetail(Long id);
	
	/**
	 * 根据类型和内容检索 新闻/通知/贴士
	 * @param type
	 * @param content
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public Result<Object> getNewsList(Integer type, String content);
}
