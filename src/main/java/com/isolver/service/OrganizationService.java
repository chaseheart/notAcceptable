package com.isolver.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isolver.common.constant.SysStaticConst;
import com.isolver.dao.department.DepartmentRepository;
import com.isolver.dao.organization.OrganizationRepository;
import com.isolver.dto.DepartDto;
import com.isolver.dto.OrgDto;
import com.isolver.entity.Organization;

/**
 * @author IS1907005
 * @date 2019/11/12
 * @class OrganizationService.java
 */
@Service
public class OrganizationService {
	@Autowired
	private OrganizationRepository organizationRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	public List<OrgDto> findAllOrg() {

		List<Organization> orgList = organizationRepository.findByDeleteFlag(SysStaticConst.NOTDELE);
		List<OrgDto> orgdtoList = new ArrayList<>();
		for (Organization o : orgList) {
			List<DepartDto> departDto = departmentRepository.findAllByOrgAndDeleteflag(o, SysStaticConst.NOTDELE);
			OrgDto orgDto = new OrgDto(o, departDto);
			orgdtoList.add(orgDto);
		}
		return orgdtoList;
	}
}
