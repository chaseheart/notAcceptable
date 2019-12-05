package com.isolver.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.isolver.common.constant.SysStaticConst;
import com.isolver.common.util.Dateutil;
import com.isolver.common.util.Encodeutil;
import com.isolver.common.util.PinYinUtil;
import com.isolver.dao.department.DepartmentRepository;
import com.isolver.dao.menu.MenuRepository;
import com.isolver.dao.menuSetting.MenuSettingRepository;
import com.isolver.dao.oaFlowStep.OaFlowStepRepository;
import com.isolver.dao.role.RoleRepository;
import com.isolver.dao.user.UserRepository;
import com.isolver.dto.SysUserDto;
import com.isolver.dto.UserDto;
import com.isolver.entity.Department;
import com.isolver.entity.Menu;
import com.isolver.entity.MenuSetting;
import com.isolver.entity.User;
import com.isolver.form.PageableForm;

/**
 * 用户管理
 * 
 * @author IS1907006
 * @date 2019/08/20
 * @class UserService.java
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private OaFlowStepRepository oaFlowStepRepository;
	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private MenuSettingRepository menuSettingRepository;
	@Autowired
	private MenuRepository menuRepository;

	/**
	 * 条件检索所有用户信息
	 * 
	 * @param workId
	 * @param username
	 * @param org
	 * @param depart
	 * @return
	 */
	public Map<String, Object> findAllUserByCondition(String workId, String username, Long org, Long depart, PageableForm pageableForm) {

		// TODO sql重写，条件检索需写原生sql
//		return userRepository.findAllUserByPageAndDelete(SysStaticConst.NOTDELE);
		return userRepository.findAllUserByPageAndDelete(workId, username, org, depart, SysStaticConst.NOTDELE, pageableForm);
	}

	/**
	 * 初期化检索所有数据
	 * 
	 * @return
	 */
	public Page<UserDto> findAllUser() {
		Pageable pageable = new PageRequest(SysStaticConst.FIRSTPAGE, SysStaticConst.INITPAGESIZE);
		return userRepository.findAllUserByDelete(SysStaticConst.NOTDELE, pageable);
	}

	/**
	 * 根据登录id检索用户
	 * 
	 * @param loginId 登录id
	 * @return
	 */
	public User getUserById(Long id) {
		return userRepository.findOne(id);
	}

	/**
	 * 根据用户层级、部门id、流程类型检索用户
	 * 
	 * @param departId  部门id
	 * @param userLevel 用户层级
	 * @param flowType  流程类型
	 * @return
	 */
	public List<SysUserDto> findUserByDepartAndRole(Long departId, Integer userLevel, Long flowType) {
		return oaFlowStepRepository.getNextStepUserList(userLevel, flowType, departId);
	}

	/**
	 * 检索所有用户
	 * 
	 * @return
	 */
	public Map<String, List<UserDto>> findAll() {
		List<UserDto> resultList = new ArrayList<>();
		List<User> userList = userRepository.findAll();
		for (User user : userList) {
			UserDto model = new UserDto(user);
			model.setFirstChart(PinYinUtil.getFirstLetter(user.getUsername()));
			resultList.add(model);
		}

		Map<String, List<UserDto>> resultMap = resultList.stream().collect(Collectors.groupingBy(UserDto::getFirstChart));
		return resultMap;
	}

	/**
	 * 模糊检索用户
	 * 
	 * @return
	 */
	public Map<String, List<UserDto>> findUserLike(String username) {
		List<UserDto> resultList = new ArrayList<>();
		List<User> userList = userRepository.findByUsernameLike(username);
		for (User user : userList) {
			UserDto model = new UserDto(user);
			model.setFirstChart(PinYinUtil.getFirstLetter(user.getUsername()));
			resultList.add(model);
		}

		Map<String, List<UserDto>> resultMap = resultList.stream().collect(Collectors.groupingBy(UserDto::getFirstChart));
		return resultMap;
	}

	/**
	 * 检索一条用户
	 * 
	 * @param userId 用户ID
	 * @return
	 */
	public UserDto findAllById(Long userId) {
		User user = userRepository.findByIdAndDeleteFlag(userId, SysStaticConst.NOTDELE);

		UserDto userDto = new UserDto(user);
		return userDto;
	}

	/**
	 * 工号检索用户
	 * 
	 * @param workId
	 * @return
	 */
	public UserDto findByWorkId(String workId) {
		User user = userRepository.findByWorkIdAndDeleteFlag(workId, SysStaticConst.NOTDELE);
		UserDto userDto = new UserDto(user);
		return userDto;
	}

	public User findUserByworkId(String workId) {
		User user = userRepository.findByWorkIdAndDeleteFlag(workId, SysStaticConst.NOTDELE);
		return user;
	}

	/**
	 * 
	 * 检索上级用户（报销用）
	 * 
	 * @param level
	 * @return
	 */
	public List<SysUserDto> findAllByRole(Integer level) {
		return oaFlowStepRepository.getNextStepUserList(level, 2L, null);

	}

	public Boolean setUserList(List<String> userList, Long userId) {
		String workId = userList.get(2);
		User user = userRepository.findByWorkId(workId);
		Department depart = departmentRepository.findByDepartName(userList.get(2));
		if (depart != null) {
			if (user == null) {
				user = new User();
				user.setUsername(userList.get(1));
				user.setWorkId(workId);
				user.setDepart(depart);
				user.setPassword(Encodeutil.MD5Util(workId + "123456"));
				user.setPhone(userList.get(4));
				user.setEmail(userList.get(5));
				user.setInsertUserId(userId);
				user.setInsertTime(Dateutil.getTimestamp());
				user.setUpdateUserId(userId);
				user.setUpdateTime(Dateutil.getTimestamp());
				user.setDeleteFlag(SysStaticConst.NOTDELE);
				user = userRepository.saveAndFlush(user);
				List<Menu> menu = menuRepository.findAll();
				for (Menu m : menu) {
					MenuSetting menuSetting = new MenuSetting();
					menuSetting.setUserId(user);
					menuSetting.setMenuId(m);
					menuSetting.setIsVisible(true);
					menuSetting.setInsertUserId(userId);
					menuSetting.setInsertTime(Dateutil.getTimestamp());
					menuSetting.setUpdateUserId(userId);
					menuSetting.setUpdateTime(Dateutil.getTimestamp());
					menuSetting.setDeleteFlag(SysStaticConst.NOTDELE);
					menuSettingRepository.saveAndFlush(menuSetting);
				}

			} else {
				User entity = new User();
				BeanUtils.copyProperties(user, entity);
				entity.setUsername(userList.get(1));
				entity.setWorkId(workId);
				user.setDepart(depart);
				user.setPassword(Encodeutil.MD5Util(workId + "123456"));
				entity.setPhone(userList.get(4));
				entity.setEmail(userList.get(5));
				entity.setUpdateUserId(userId);
				entity.setUpdateTime(Dateutil.getTimestamp());
				userRepository.saveAndFlush(entity);
			}
			return true;
		}
		return false;
	}
}
