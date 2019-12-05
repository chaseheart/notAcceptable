package com.isolver.service.wechat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isolver.common.constant.SysStaticConst;
import com.isolver.common.util.Dateutil;
import com.isolver.dao.vacationRecord.VacationRecordRepository;
import com.isolver.dto.wechat.WechatVacationRecordDto;

@Service
public class VacationRecordService {

	@Autowired
	private VacationRecordRepository vacationRecordRepository;

	/**
	 * 获得休假列表
	 * 
	 * @param user
	 * @param date
	 * @return
	 */
	public List<WechatVacationRecordDto> getRecord(Long user, String date) {
		return vacationRecordRepository.getByUserAndDate(user, Dateutil.stringToDate(date), SysStaticConst.NOTDELE);
	}

}
