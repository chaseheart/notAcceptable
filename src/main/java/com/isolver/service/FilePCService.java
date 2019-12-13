package com.isolver.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isolver.common.constant.SysStaticConst;
import com.isolver.common.util.Dateutil;
import com.isolver.dao.fileInfo.FileInfoRepository;
import com.isolver.dao.user.UserRepository;
import com.isolver.dto.wechat.FileInfoDto;
import com.isolver.entity.FileInfo;
import com.isolver.entity.User;
import com.isolver.form.wechat.FileSaveResultForm;

/**
 * @author IS1907005
 * @date 2019/11/25
 * @class FileService.java
 */
@Service
public class FilePCService {
	@Autowired
	private FileInfoRepository fileInfoRepository;

	@Autowired
	private UserRepository userRepository;

	public List<FileInfo> createFile(List<FileSaveResultForm> fileSaveResultFormList, Long userId) {

		List<FileInfo> picList = new ArrayList<>();
		Timestamp presentTime = Dateutil.getTimestamp();
		
		for (FileSaveResultForm p : fileSaveResultFormList) {
			// 获取uuid
//			String u_id = UUID.randomUUID().toString().replace(SysStaticConst.DASH, StringUtils.EMPTY);
//			pic = new FileInfo(p, u_id, insertid);
			FileInfo pic = new FileInfo();
			User user = userRepository.findOne(userId);
			pic.setFileName(p.getFileName());
			pic.setFilePath(p.getFilePath());

			pic.setInsertTime(presentTime);
			pic.setInsertUserId(user.getId());
			pic.setUpdateTime(pic.getInsertTime());
			pic.setUpdateUserId(user.getId());
			// 是否删除
			pic.setDeleteFlag(SysStaticConst.NOTDELE);
			picList.add(pic);
		}

		fileInfoRepository.save(picList);
		fileInfoRepository.flush();
		return picList;
	}

	public List<FileInfoDto> getAllFile(String tableName, String tableId) {

		return fileInfoRepository.findFileInfoDtoByTableNameAndTableIdAndDeleteFlag(tableName, tableId, SysStaticConst.NOTDELE);

	}
}
