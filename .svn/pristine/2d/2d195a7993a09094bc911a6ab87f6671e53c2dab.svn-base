package com.isolver.interfaces.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.isolver.common.util.Result;
import com.isolver.interfaces.MenuApi;
import com.isolver.service.wechat.MenuSettingService;

@RestController
public class MenuApiImpl implements MenuApi {
	
	@Autowired
	private MenuSettingService menuSettingService;

	/**
	 * 初始化菜单
	 * @param userId 用户id
	 * @return
	 */
	@Override
	public Result<Object> initMenu(Long userId) {
			return menuSettingService.setMenu(userId);
	}

	/**
	 *  保存菜单
	 * @param menuArr 菜单集合
	 * @param userId 用户id
	 * @return
	 */
	@Override
	public Result<Object> saveMenu(String[] menuArr, Long userId) {
		return menuSettingService.saveMenu(menuArr, userId);
	}

	/**
	 *  首页菜单初期化
	 * @param userId 用户id
	 * @return
	 */
	@Override
	public Result<Object> pageMenu(Long userId) {
		return menuSettingService.pageMenu(userId);
	}

}
