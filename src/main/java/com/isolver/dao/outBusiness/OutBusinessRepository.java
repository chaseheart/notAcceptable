package com.isolver.dao.outBusiness;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.isolver.dto.ApplicationDto;
import com.isolver.dto.PendingDto;
import com.isolver.dto.wechat.PendingDtoWechat;
import com.isolver.entity.OutBusiness;
import com.isolver.entity.RuFlow;
import com.isolver.entity.User;

/**
 * @author IS1907005
 * @date 2019/11/20
 * @class OutBusinessRepository.java
 */
@Repository
public interface OutBusinessRepository extends JpaRepository<OutBusiness, Long> {

	@Query("select new com.isolver.dto.ApplicationDto(CONCAT(a.ruFlow.id,'' )as appId,"
			+ " CONCAT(a.outBusinessStart,'' ) as appStart, CONCAT(a.outBusinessEnd,'' ) as appEnd, CONCAT(a.ruFlow.dealPeople.username,'' ) as assigner, CONCAT(a.businessReason,'' ) as typeDetail " + ", CONCAT(a.ruFlow.state,'' ) as state )"
			+ " from OutBusiness a where a.user = :user and a.outBusinessStart Between :startTime and :endTime group by a.ruFlow.id , a.businessReason,a.outBusinessStart,a.outBusinessEnd,a.ruFlow.dealPeople.username,a.content")
	public List<ApplicationDto> findApplicationDtoByBetween(Timestamp startTime, Timestamp endTime, User user);

	@Query("select new com.isolver.dto.PendingDto(CONCAT(a.ruFlow.id,'' )as appId, "
			+ " CONCAT(a.user.username,'' ) as appUsername, CONCAT(a.ruFlow.insertTime,'' ) as appStartTime, CONCAT(a.ruFlow.dealPeopleNow.username,'' ) as dealPeople, "
			+ " CONCAT(a.ruFlow.updateTime,'' ) as appEndtTime )"
			+ " from OutBusiness a where a.ruFlow.dealPeople = :dealPeople and a.ruFlow.deleteFlag = :deleteFlag and a.user.username like :username and a.user.workId like :workId and a.ruFlow.state > 1 and a.ruFlow.state < 4 group by a.ruFlow.id, a.user.username, a.ruFlow.insertTime, a.ruFlow.dealPeopleNow.username, a.ruFlow.updateTime ")
	public List<PendingDto> findApplicationDtoByUndo(User dealPeople, Boolean deleteFlag, String workId, String username);

	@Query("select new com.isolver.dto.wechat.PendingDtoWechat(CONCAT(a.ruFlow.id,'' ) as appId,  CONCAT(a.ruFlow.updateTime,'' ) as appEndTime, '外出公务申请' as appName, (case when a.ruFlow.state = 1 then '再申请' when a.ruFlow.state = 2 or a.ruFlow.state = 3 then '审批中' end) as appstate, CONCAT(a.ruFlow.insertTime,'' ) as appStartTime) from OutBusiness a where a.user = :user and a.ruFlow.deleteFlag = :deleteFlag and a.ruFlow.state < :state and a.ruFlow.state > 0 group by a.ruFlow.id")
	public List<PendingDtoWechat> getDataByUser(User user, Boolean deleteFlag, Integer state);

	/**
	 * 根据运行流程申请ID检索 外出公务申请详情
	 * 
	 * @param ruFlow
	 * @return
	 */
	public List<OutBusiness> findByRuFlow(RuFlow ruFlow);
	
	/**
	 * 根据用户和消除flag检索申请历史
	 * 
	 * @param user
	 * @param deleteFlag
	 * @return
	 */
	@Query(" SELECT a FROM OutBusiness a WHERE a.user = :user AND a.deleteFlag = :deleteFlag AND (a.ruFlow.state = 4 OR a.ruFlow.state = 0)")
	public List<OutBusiness> findByUserAndDeleteFlagEnd(User user, Boolean deleteFlag);
}
