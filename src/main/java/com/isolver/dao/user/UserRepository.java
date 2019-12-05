package com.isolver.dao.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.isolver.dto.UserDto;
import com.isolver.entity.Department;
import com.isolver.entity.Role;
import com.isolver.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

	/**
	 * 根据登录id检索用户
	 * 
	 * @param workId 登录id
	 * @return
	 */
	public User findByWorkId(String workId);

	/**
	 * 根据角色检索相应用户
	 * 
	 * @param role 角色
	 * @return
	 */
	@Query("select new com.isolver.dto.UserDto(a.id as id, a.username as username) from User a where a.role = :role and a.depart = :depart and a.deleteFlag = :deleteFlag")
	public List<UserDto> findByRoleAndDepartAndDeleteFlag(@Param("role") Role role, @Param("depart") Department depart, @Param("deleteFlag") Boolean deleteFlag);

	/**
	 * 根据用户id检索相应角色
	 * 
	 * @param id 用户id
	 * @return
	 */
	@Query("select a.role.id from User a where a.id= :id")
	public Long findRoleId(@Param("id") Long id);

	/**
	 * 根据用户id检索相应用户信息
	 * 
	 * @param id 用户id
	 * @return
	 */
	public User findByIdAndDeleteFlag(Long id, Boolean deleteFlag);

	/**
	 * 根据用户名检索用户
	 * 
	 * @param id 用户id
	 * @return
	 */
	public List<User> findByUsernameLike(String username);

	/**
	 * 根据角色检索相应用户
	 * 
	 * @param role 角色
	 * @return
	 */
	@Query("select new com.isolver.dto.UserDto(a.id as id, a.username as username) from User a where a.role = :role and a.deleteFlag = :deleteFlag")
	public List<UserDto> findByRoleDeleteFlag(@Param("role") Role role, @Param("deleteFlag") Boolean deleteFlag);

	/**
	 * 分页检索所有用户 TODO
	 * 
	 * @return
	 */
	@Query("select new com.isolver.dto.UserDto(a.id as id,a.workId as workId, a.username as username,a.org.orgName as org,a.depart.departName as depart , a.role.roleName as role , a.holidayType  as vacationType) from User a where a.workId like :workId and a.username like :username and a.deleteFlag = :deleteFlag")
	public List<UserDto> findAllUserByPageAndDelete(@Param("deleteFlag") Boolean deleteFlag);

	/**
	 * 初期化检索所有用户
	 * 
	 * @return
	 */
	@Query("select new com.isolver.dto.UserDto(a.id as id,a.workId as workId, a.username as username,a.org.orgName as org,a.depart.departName as depart , a.role.roleName as role , a.holidayType  as vacationType) from User a where a.deleteFlag = :deleteFlag order by workId")
	public Page<UserDto> findAllUserByDelete(@Param("deleteFlag") Boolean deleteFlag, Pageable pageable);
}
