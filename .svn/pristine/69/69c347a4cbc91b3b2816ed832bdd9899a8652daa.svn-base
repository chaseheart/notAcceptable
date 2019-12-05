package com.isolver.dao.role;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isolver.entity.Role;

/**
 * @author IS1907005
 * @date 2019/11/13
 * @class RoleRepository.java
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	public Role findByLevel(Integer level);

	/**
	 * 查找所有权限
	 * 
	 * @param deleteflag
	 * 
	 */
	public List<Role> findByDeleteFlag(Boolean deleteflag);
}
