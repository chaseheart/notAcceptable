package com.isolver.dao.menuSetting;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.isolver.entity.Menu;
import com.isolver.entity.MenuSetting;
import com.isolver.entity.User;

/**
 * @author IS1907005
 * @date 2019/11/08
 * @class HolidayRepository.java
 */
@Repository
public interface MenuSettingRepository extends JpaRepository<MenuSetting, Long> {

	/**
	 * 根据用户id获取所有菜单
	 * @param id
	 * @return
	 */
	@Query("select a from MenuSetting a where a.userId = :user and a.deleteFlag = :deleteFlag")
	public List<MenuSetting> findByUserId(@Param("user") User user, @Param("deleteFlag") Boolean deleteFlag);
	
	/**
	 * 根据用户id获取所有菜单
	 * @param user
	 * @param deleteFlag
	 * @return
	 */
	@Query("select a.menuId from MenuSetting a where a.userId = :user and a.deleteFlag = :deleteFlag")
	public List<Menu> findMenuByUserId(@Param("user") User user, @Param("deleteFlag") Boolean deleteFlag);
	
	
}
