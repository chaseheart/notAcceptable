package com.isolver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isolver.common.constant.SysStaticConst;
import com.isolver.dao.department.DepartmentRepository;
import com.isolver.dao.organization.OrganizationRepository;
import com.isolver.dto.DepartDto;
import com.isolver.dto.DepartOrgDto;
import com.isolver.entity.Organization;

/**
 * @author IS1907005
 * @date 2019/11/25
 * @class DepartmentService.java
 */
@Service
public class DepartmentService {
	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private OrganizationRepository organizationRepository;

	public List<DepartDto> getDepartList() {
		return departmentRepository.findAllByDeleteflag(SysStaticConst.NOTDELE);
	}

	public List<DepartDto> getAimDepartList(Long id) {
		Organization org = organizationRepository.findOne(id);
		return departmentRepository.findAllByOrgAndDeleteflag(org, SysStaticConst.NOTDELE);
	}

	/**
	 * 取得所有部门
	 * 
	 * @return
	 */
	public List<DepartOrgDto> getDepartListWithOrg() {
		return departmentRepository.findWithOrgByDeleteFlag(SysStaticConst.NOTDELE);
	}
}
