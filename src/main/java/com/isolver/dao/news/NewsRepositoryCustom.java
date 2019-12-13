package com.isolver.dao.news;

import java.util.List;

import com.isolver.dto.wechat.NewsDto;

public interface NewsRepositoryCustom {

	/**
	 * 根据内容和类型检索通知
	 * @param content 内容
	 * @param type 类型
	 * @param deleteFlag 消除flg
	 * @return
	 */
	public List<NewsDto> getNoticeData(String content, String type, Boolean deleteFlag);
}
