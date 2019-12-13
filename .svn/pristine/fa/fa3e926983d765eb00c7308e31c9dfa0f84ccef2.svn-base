package com.isolver.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isolver.dao.authority.AuthorityRepository;
import com.isolver.dao.roleAuthority.RoleAuthorityRepository;
import com.isolver.dto.AuthorityDto;
import com.isolver.entity.Authority;
import com.isolver.entity.RoleAuthority;
import com.isolver.entity.User;

/**
 * ·菜单权限
 * @date 2019/12/06
 * @author IS1907011
 *
 */
@Service
public class AuthorityService {
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private RoleAuthorityRepository roleAuthorityRepository;
	
	/**
	 * ·检索所有权限
	 * @return
	 */
	public List<AuthorityDto> getAllAuthority() {
		List<Authority> entityList = authorityRepository.findByOrderByMenuCode();
		List<AuthorityDto> dtoList = new ArrayList<>();
		for (Authority entity : entityList) {
			dtoList.add(new AuthorityDto(entity));
		} 
		return dtoList;
	}
	
	/**
	 * ·检索用户的权限
	 * @param user 登录用户
	 * @return
	 */
	public List<String> getUserAuthority(User user){
		List<RoleAuthority> authorityList = roleAuthorityRepository.findByRole(user.getRole());
		List<String> authorityString = new ArrayList<>();
		for(RoleAuthority roleAuthority : authorityList) {
			authorityString.add(roleAuthority.getAuthority().getMenuCode());
		}
		return authorityString;
	}
}
