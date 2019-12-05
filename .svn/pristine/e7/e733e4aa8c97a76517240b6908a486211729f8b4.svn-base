package com.isolver.dao.hiFlow;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.isolver.dto.wechat.ApproveHistoryDto;
import com.isolver.entity.HiFlow;

/**
 * @author IS1907005
 * @date 2019/11/18
 * @class HiflowRepository.java
 */
public interface HiFlowRepository extends JpaRepository<HiFlow, Long>, HiFlowRepositoryCustom {

	/**
	 * 根据运行流程id检索申请历史
	 * @param ruFlowId
	 * @return
	 */
	@Query("select new com.isolver.dto.wechat.ApproveHistoryDto(CONCAT(a.ruFlowId.id,'') as ruFlowId, a.dealPeople.username as dealPerson, (case when a.operate is null and a.state = 1 then '已申请' when a.operate is null and a.state <> 1 then '已审批' else a.operate end) as operate, CONCAT(a.updateTime, '') as updateEndTime) from HiFlow a where a.ruFlowId.id = :ruFlowId")
	public List<ApproveHistoryDto> getByRuFlowId(Long ruFlowId);
}
