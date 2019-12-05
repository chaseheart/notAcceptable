/**
 * @author 刘志欣
 * LoginRepository.java
 * 2018/12/01
 */
package com.isolver.dao.wechat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.isolver.dto.wechat.LoginDto;
import com.isolver.entity.User;

@Repository
public interface LoginRepository extends JpaRepository<User, Long> {

	/**
	 * 后台登录
	 * 
	 * @param password
	 * @param userName
	 * @return User
	 */
	@Query("select new com.isolver.dto.wechat.LoginDto(a.id as id , a.username as userName) from User a where a.password = :password And a.workId = :workId and a.deleteFlag = :deleteFlag ")
	public LoginDto getByLoginIdAndPassword(@Param("password") String password, @Param("workId") String workId, @Param("deleteFlag") Boolean deleteFlag);

}
