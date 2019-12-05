package com.isolver.interfaces;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.isolver.common.util.Result;

@RequestMapping("weixin/menu")
public interface MenuApi {

	/**
	 *菜单设置初期化 初始化菜单
	 * @param userId 用户id
	 * @return
	 */
	@RequestMapping(value = "/initEdit", method = RequestMethod.POST)
	public Result<Object> initMenu(@RequestParam(value = "userId") Long userId);
	
	/**
	 * 保存菜单
	 * @param menuArr 菜单集合
	 * @param userId 用户id
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public Result<Object> saveMenu(@RequestParam(value = "menuArr") String[] menuArr,
			@RequestParam(value = "userId") Long userId);
	
	/**
	 *  首页菜单初期化
	 * @param userId 用户id
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public Result<Object> pageMenu(@RequestParam(value = "userId") Long userId);
}
