package com.isolver.dao.claimingExpenses;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.isolver.dto.ApplicationDto;
import com.isolver.dto.PendingDto;
import com.isolver.dto.wechat.ClaimingExpensesDto;
import com.isolver.entity.ClaimingExpenses;
import com.isolver.entity.RuFlow;
import com.isolver.entity.User;

/**
 * @author IS1907005
 * @date 2019/11/12
 * @class ClaimingExpensesRepository.java
 */
@Repository
public interface ClaimingExpensesRepository extends JpaRepository<ClaimingExpenses, Long> {

	/**
	 * 根据用户和消除flag检索报销记录
	 * 
	 * @param user
	 * @param deleteFlag
	 * @return
	 */
	@Query(" SELECT a FROM ClaimingExpenses a WHERE a.user.id = :user AND a.deleteFlag = :deleteFlag AND a.ruFlow.state > 0 AND a.ruFlow.state < 6 order By a.id desc")
	public List<ClaimingExpenses> findByUserAndDeleteFlag(@Param("user") Long user,@Param("deleteFlag") Boolean deleteFlag);

	/**
	 * 根据用户和消除flag检索报销历史
	 * 
	 * @param user
	 * @param deleteFlag
	 * @return
	 */
	@Query(" SELECT a FROM ClaimingExpenses a WHERE a.user.id = :user AND a.deleteFlag = :deleteFlag AND (a.ruFlow.state = 6 OR a.ruFlow.state = 0) order By a.id desc")
	public List<ClaimingExpenses> findByUserAndDeleteFlagEnd(@Param("user") Long user,@Param("deleteFlag") Boolean deleteFlag);

	/**
	 * 检索自己提出的所有申请
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@Query("select new com.isolver.dto.ApplicationDto(CONCAT(a.ruFlow.id,'' )as appId, CONCAT(a.type,'' ) as typeDetail,"
			+ " CONCAT(a.ruFlow.dealPeople.username,'' ) as assigner, " + " CONCAT(a.content,'' ) as content )"
			+ " from ClaimingExpenses a where a.user = :user and a.insertTime Between :startTime and :endTime")
	public List<ApplicationDto> findApplicationDtoByUnusualAttendanceDateBetween(@Param("startTime") Timestamp startTime,@Param("endTime")  Timestamp endTime,
			@Param("user") User user);

	/**
	 * 检索所有待处理申请
	 * 
	 * @param dealPeople
	 * @param deleteFlag
	 * @return
	 */
	@Query("select new com.isolver.dto.PendingDto(CONCAT(a.ruFlow.id,'' )as appId, "
			+ " CONCAT(a.user.username,'' ) as appUsername, CONCAT(a.ruFlow.insertTime,'' ) as appStartTime, CONCAT(a.ruFlow.dealPeopleNow.username,'' ) as dealPeople, "
			+ " CONCAT(a.ruFlow.updateTime,'' ) as appEndtTime )"
			+ " from ClaimingExpenses a where a.ruFlow.dealPeople= :dealPeople and a.ruFlow.deleteFlag = :deleteFlag and a.user.username like :username and a.user.workId like :workId and a.ruFlow.state > 1 and a.ruFlow.state < 6  ORDER BY a.ruFlow.id DESC")
	public List<PendingDto> findApplicationDtoByUndo(@Param("dealPeople") User dealPeople,@Param("deleteFlag") Boolean deleteFlag,@Param("workId") String workId,
			@Param("username") String username);

	/**
	 * 检索我的报销申请
	 * 
	 * @param dealPeople
	 * @param deleteFlag
	 * @return
	 */
	@Query("select new com.isolver.dto.PendingDto(CONCAT(a.ruFlow.id,'' )as appId, "
			+ " CONCAT(a.user.username,'' ) as appUsername, CONCAT(a.ruFlow.insertTime,'' ) as appStartTime, CONCAT(a.ruFlow.dealPeopleNow.username,'' ) as dealPeople, "
			+ " CONCAT(a.ruFlow.updateTime,'' ) as appEndtTime, CONCAT(a.ruFlow.state,'' ) as state)"
			+ " from ClaimingExpenses a where a.user= :user")
	public List<PendingDto> findMyApplicationDto(@Param("user") User user);

	/**
	 * 检索所有待处理申请(报销)
	 * 
	 * @param dealPeople
	 * @param deleteFlag
	 * @return
	 */
	@Query("select new com.isolver.dto.PendingDto(CONCAT(a.ruFlow.id,'' )as appId, "
			+ " CONCAT(a.user.username,'' ) as appUsername, CONCAT(a.ruFlow.insertTime,'' ) as appStartTime, CONCAT(a.ruFlow.dealPeopleNow.username,'' ) as dealPeople, "
			+ " CONCAT(a.ruFlow.updateTime,'' ) as appEndtTime )"
			+ " from ClaimingExpenses a where a.ruFlow.dealPeople.id= :dealPeople and a.ruFlow.deleteFlag = :deleteFlag and a.ruFlow.state between 2 and 5 order by a.id desc")
	public List<PendingDto> findApplicationDtoByUndoWechat(@Param("dealPeople") Long dealPeople,@Param("deleteFlag") Boolean deleteFlag);

	/**
	 * 根据报销id和消除flag检索报销记录
	 * 
	 * @param id
	 * @param deleteFlag
	 * @return
	 */
	@Query("select new com.isolver.dto.wechat.ClaimingExpensesDto(a.department.departName as departId, a.type as type, a.expensesType as expensesType, a.money as money, a.projectId as projectId, a.customerName as customerName, a.expenseCompany as expenseCompany, a.content as content, a.ruFlow.dealPeople.username as flowUser) from ClaimingExpenses a where a.id=:id and a.deleteFlag =:deleteFlag")
	public ClaimingExpensesDto findByIdAndDeleteFlag(@Param("id") Long id,@Param("deleteFlag") Boolean deleteFlag);

	/**
	 * 根据报销id和消除flag检索报销记录
	 * 
	 * @param id
	 * @param deleteFlag
	 * @return
	 */
	@Query("select new com.isolver.dto.wechat.ClaimingExpensesDto(CONCAT(a.department.id , '') as departId, a.type as type, a.expensesType as expensesType, a.money as money, a.projectId as projectId, a.customerName as customerName, a.expenseCompany as expenseCompany, a.content as content, CONCAT(a.ruFlow.dealPeople.id , '') as flowUser) from ClaimingExpenses a where a.id=:id and a.deleteFlag =:deleteFlag")
	public ClaimingExpensesDto findByIdAndDeleteFlagEdit(@Param("id") Long id,@Param("deleteFlag") Boolean deleteFlag);

	/**
	 * 根据报销id和消除flag检索报销记录
	 * 
	 * @param id
	 * @param deleteFlag
	 * @return
	 */
	@Query("select new com.isolver.dto.wechat.ClaimingExpensesDto(concat(a.user.workId,'') as userWorkId,concat(a.user.username,'') as username,concat(a.id,'') as id , concat(a.department.id , '')as departId, a.type as type, a.expensesType as expensesType, a.money as money, a.projectId as projectId, a.customerName as customerName, a.expenseCompany as expenseCompany, a.content as content, a.ruFlow.dealPeople.username as flowUser) from ClaimingExpenses a where a.ruFlow.id=:id and a.deleteFlag =:deleteFlag")
	public ClaimingExpensesDto findByRuFlowIdAndDeleteFlag(@Param("id") Long id,@Param("deleteFlag") Boolean deleteFlag);

	/**
	 * 根据运行流程申请ID检索 休假申请详情
	 * 
	 * @param ruFlow
	 * @return
	 */
	public ClaimingExpenses findByRuFlow(RuFlow ruFlow);
	
	/**
	 * 根据报销id和消除flag检索报销记录
	 * 
	 * @param id
	 * @param deleteFlag
	 * @return
	 */
	@Query("select a from ClaimingExpenses a where a.ruFlow.id=:id and a.deleteFlag =:deleteFlag")
	public ClaimingExpenses findDataByRuFlowIdAndDeleteFlag(@Param("id") Long id,@Param("deleteFlag") Boolean deleteFlag);
}
