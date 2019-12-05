package com.isolver.dao.menu.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import com.isolver.dao.menu.MenuRepositoryCustom;
import com.isolver.dto.wechat.MenuDto;

/**
 * @author IS1907005
 * @date 2019/11/08
 * @class ServicePerformanceRepositoryImpl.java
 */
public class MenuRepositoryImpl implements MenuRepositoryCustom {
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<MenuDto> getInitMenu(Long userId, Boolean deleteFlag) {
		List<MenuDto> spDto = new ArrayList<>();
		String sql = " SELECT "
				   + "    CONCAT(a.id, '') as id," 
				   + "    a.menu_name as menuName, " 
				   + "    a.menu_icon as menuIcon, " 
				   + "    a.menu_url as menuUrl, " 
				   + "	  CONCAT(case when count( b.id ) =1 then 1 end, '') as isExisted "
				   + " FROM menu a"  
				   + " LEFT JOIN menu_setting b ON a.id = b.menu_id "
				   + " AND b.user_id = :userId "
				   + " WHERE a.delete_flag = :deleteFlag "
				   + " GROUP BY a.id ";
		
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter("userId", userId);
		query.setParameter("deleteFlag", deleteFlag);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(MenuDto.class));
		spDto = query.getResultList();
		return spDto;
	}

}
