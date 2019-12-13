package com.isolver.dao.department;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.isolver.dto.DepartDto;
import com.isolver.dto.DepartOrgDto;
import com.isolver.entity.Department;
import com.isolver.entity.Organization;

/**
 * @author IS1907005
 * @date 2019/11/12
 * @class DepartmentRepository.java
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

	@Query("select new com.isolver.dto.DepartDto(CONCAT(a.id,'' )as id, CONCAT(a.departName,'' ) as departName ) from Department a where a.org = :org and a.deleteFlag = :deleteFlag")
	public List<DepartDto> findAllByOrgAndDeleteflag(@Param("org") Organization org, @Param("deleteFlag") Boolean deleteFlag);

	@Query("select new com.isolver.dto.DepartDto(CONCAT(a.id,'' )as id, CONCAT(a.departName,'' ) as departName ) from Department a where a.deleteFlag = :deleteFlag")
	public List<DepartDto> findAllByDeleteflag(@Param("deleteFlag") Boolean deleteFlag);

	@Query("select new com.isolver.dto.DepartDto(CONCAT(a.id,'' )as id, CONCAT(a.departName,'' ) as departName , CONCAT(a.departName.org.id,'' ) as orgId ) from Department a where a.id = :id")
	public DepartDto findOneById(@Param("id") Long id);
	/**
	 * 取得所有部门
	 * 
	 * @param deleteFlag
	 * @return
	 */
	@Query(" SELECT new com.isolver.dto.DepartOrgDto(CONCAT(a.id, '') AS id, CONCAT(a.departName, '') AS departName, CONCAT(a.org.orgName, '') AS orgName) FROM Department a WHERE a.deleteFlag = :deleteFlag ")
	public List<DepartOrgDto> findWithOrgByDeleteFlag(@Param("deleteFlag") Boolean deleteFlag);

	/**
	 * 通过部门名检索部门
	 * 
	 * @param departName
	 * @return
	 */
	public Department findByDepartNameAndDeleteFlag(String departName,Boolean deleteFlag);
	/**
	 * 通过部门名检索部门
	 * 
	 * @param departName
	 * @return
	 */
	public Department findByDepartNameAndOrgAndDeleteFlag(String departName,Organization org,Boolean deleteFlag);
	/**
	 * 通过组织检索部门
	 * 
	 * @param departName
	 * @return
	 */
	public List<Department> findByOrgAndDeleteFlag(Organization org, Boolean deleteFlag);
}
