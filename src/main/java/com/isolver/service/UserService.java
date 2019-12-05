package com.isolver.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.isolver.common.constant.SysStaticConst;
import com.isolver.common.util.PinYinUtil;
import com.isolver.dao.oaFlowStep.OaFlowStepRepository;
import com.isolver.dao.role.RoleRepository;
import com.isolver.dao.user.UserRepository;
import com.isolver.dto.SysUserDto;
import com.isolver.dto.UserDto;
import com.isolver.entity.Role;
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
	 * @param departId 部门id
	 * @param userLevel 用户层级
	 * @param flowType 流程类型
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
	 * 
	 * 检索上级用户（报销用）
	 * 
	 * @param level
	 * @return
	 */
	public List<SysUserDto> findAllByRole(Integer level) {
		return oaFlowStepRepository.getNextStepUserList(level, 2L, null);

	}
}
