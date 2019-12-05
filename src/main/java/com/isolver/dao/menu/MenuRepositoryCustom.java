package com.isolver.dao.menu;

import java.util.List;

import com.isolver.dto.wechat.MenuDto;

/**
 * @author IS1907005
 * @date 2019/11/08
 * @class ServicePerformanceRepositoryCustom.java
 */
public interface MenuRepositoryCustom {
	/**
	 * 检索初始化菜单
	 * @param userId
	 * @param deleteFlag
	 * @return
	 */
	public List<MenuDto> getInitMenu(Long userId, Boolean deleteFlag);
	
	
}
