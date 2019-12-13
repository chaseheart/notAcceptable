package com.isolver.dao.roleAuthority;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isolver.entity.Role;
import com.isolver.entity.RoleAuthority;

/**
 * 角色权限检索
 * @date 2019/12/06
 * @author IS1907011
 *
 */
@Repository
public interface RoleAuthorityRepository extends JpaRepository<RoleAuthority,Long>{

	/**
	 * 
	 * ·角色检索权限
	 * @param role
	 * @return
	 */
	public List<RoleAuthority> findByRole(Role role);
}
