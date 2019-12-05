package com.isolver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isolver.common.constant.SysStaticConst;
import com.isolver.dao.role.RoleRepository;
import com.isolver.entity.Role;

/**
 * @author IS1907005
 * @date 2019/11/25
 * @class RoleService.java
 */
@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;

	public List<Role> getAllRole() {
		return roleRepository.findByDeleteFlag(SysStaticConst.NOTDELE);
	}

}
