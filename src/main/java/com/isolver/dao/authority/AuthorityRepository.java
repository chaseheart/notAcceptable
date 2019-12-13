package com.isolver.dao.authority;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.isolver.entity.Authority;

/**
 *·权限(菜单)检索
 * @date 2019/12/06
 * @author IS1907011
 *
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Long>{
	
	/**
	 * ·检索所有菜单权限
	 * @return
	 */
	public List<Authority> findByOrderByMenuCode();
}
