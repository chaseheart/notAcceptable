package com.isolver.service.wechat;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isolver.common.constant.SysStaticConst;
import com.isolver.common.util.Dateutil;
import com.isolver.dao.fileInfo.FileInfoRepository;
import com.isolver.dao.user.UserRepository;
import com.isolver.entity.FileInfo;
import com.isolver.entity.User;
import com.isolver.form.wechat.FileSaveResultForm;

/**
 * @author IS1907005
 * @date 2019/08/19
 * @class FileService.java
 */
@Service
public class FileService {
	@Autowired
	private FileInfoRepository fileInfoRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	private static final String TABLENAME = "claiming_expenses";

	public Long createFile(List<FileSaveResultForm> fileSaveResultFormList, Long userId) {

		Timestamp presentTime = Dateutil.getTimestamp();
		FileInfo pic = new FileInfo();
		FileSaveResultForm fileSaveResultForm = fileSaveResultFormList.get(0);
		User user = userRepository.findOne(userId);

		pic.setFileName(fileSaveResultForm.getFileName());
		pic.setFilePath(fileSaveResultForm.getFilePath());

		pic.setInsertTime(presentTime);
		pic.setInsertUserId(user.getId());
		pic.setUpdateTime(pic.getInsertTime());
		pic.setUpdateUserId(user.getId());
		// 是否删除
		pic.setDeleteFlag(SysStaticConst.NOTDELE);
		// 版本
		pic.setVersion(0);

		FileInfo fileInfo = fileInfoRepository.saveAndFlush(pic);
		return fileInfo.getId();
	}
	
	/**
	 * 删除以前上传的图片
	 * @param id
	 */
	public void deleteOldPic(String id, List<Long> photoIdList) {
		List<FileInfo> fileInfoList = fileInfoRepository.findByTableNameAndTableIdAndDeleteFlag(TABLENAME, id, SysStaticConst.NOTDELE);
		List<FileInfo> oldFileList = new ArrayList<>();
		for(FileInfo file : fileInfoList) {
			if(!photoIdList.contains(file.getId())) {
				oldFileList.add(file);
			}
		}
		fileInfoRepository.deleteInBatch(oldFileList);
	}
}
