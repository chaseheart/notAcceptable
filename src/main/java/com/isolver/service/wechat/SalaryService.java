package com.isolver.service.wechat;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isolver.common.constant.SysStaticConst;
import com.isolver.common.constant.SysStatusCodeConst;
import com.isolver.common.util.Dateutil;
import com.isolver.common.util.Result;
import com.isolver.dao.salary.SalaryRepository;
import com.isolver.dto.wechat.SalaryListDto;
import com.isolver.entity.Salary;
import com.isolver.entity.User;

@Service
public class SalaryService {

	@Autowired
	private SalaryRepository salaryRepository;

	/**
	 * 获得休假列表
	 * 
	 * @param user
	 * @param date
	 * @return
	 */
	public List<SalaryListDto> getRecord(User user, String date) {
		return salaryRepository.getSalaryByUserAndDate(user, Dateutil.stringToDate(date), SysStaticConst.NOTDELE);
	}

	/**
	 * 删除一条记录
	 * 
	 * @param id
	 * @param userId
	 * @param version
	 * 
	 * @return
	 */
	public Result<Object> delOneSalary(Long id, Long userId, Integer version) {
		Salary salary = salaryRepository.findOne(id);
		if (salary != null) {
			Timestamp presentTime = Dateutil.getTimestamp();

			Salary entity = new Salary();
			BeanUtils.copyProperties(salary, entity);
			entity.setDeleteFlag(SysStaticConst.ISDELE);
			entity.setUpdateUserId(userId);
			entity.setUpdateTime(presentTime);
			salaryRepository.save(entity);
			return new Result<>(SysStatusCodeConst.SUCCESS);
		}
		return new Result<>(SysStatusCodeConst.NODATA);
	}

	/**
	 * 获得一条记录
	 * 
	 * @param passwordForm passwordForm
	 * @return
	 */
	public Salary getOneSalary(Long id) {
		Salary salary = salaryRepository.findOne(id);
		return salary;
	}

}
