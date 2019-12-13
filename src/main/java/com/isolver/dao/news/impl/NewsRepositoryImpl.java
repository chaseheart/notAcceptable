package com.isolver.dao.news.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import com.isolver.dao.news.NewsRepositoryCustom;
import com.isolver.dto.wechat.NewsDto;

/**
 * ·步骤-角色层级对应更新 查询
 * 
 * @author IS1907011
 * @date 2019/12/03
 * @class OaFlowStepRepository.java
 */
public class NewsRepositoryImpl implements NewsRepositoryCustom {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * 根据内容和类型检索通知
	 * @param content 内容
	 * @param type 类型
	 * @param deleteFlag 消除flg
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<NewsDto> getNoticeData(String content, String type, Boolean deleteFlag) {
		List<NewsDto> newsList = new ArrayList<>();
		String sql = " SELECT" //
				   + "       CONCAT(a.id, '') as id, "
				   + "       CONCAT(a.title, '') as title, "
				   + "       (CASE a.type WHEN 1 THEN '新闻' WHEN 2 THEN '通知' WHEN 3 THEN '贴士' END) as type "
				   + " FROM news a "
				   + " where a.delete_flag = :deleteFlag";
		if(StringUtils.isNotEmpty(content)) {
			sql += " AND (a.content LIKE :content "
				 + " OR a.title LIKE :content "
				 + " OR a.introduction LIKE :content) ";
		}
		if(StringUtils.isNotEmpty(type)) {
			sql += " AND a.type = :type ";
		}
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("deleteFlag", deleteFlag);
		if(StringUtils.isNotEmpty(content)) {
			query.setParameter("content", content);
		}
		if(StringUtils.isNotEmpty(type)) {
			query.setParameter("type", Integer.parseInt(type));
		}
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(NewsDto.class));
		newsList = query.getResultList();
		return newsList;
	}
}
