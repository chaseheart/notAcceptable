package com.isolver.interfaces.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.isolver.common.constant.SysStaticConst;
import com.isolver.common.constant.SysStatusCodeConst;
import com.isolver.common.constant.SysUrlConst;
import com.isolver.common.util.FileUtil;
import com.isolver.common.util.HttpUtil;
import com.isolver.common.util.Result;
import com.isolver.form.wechat.FileSaveResultForm;
import com.isolver.interfaces.FileApi;
import com.isolver.service.wechat.FileService;

@RestController
public class FileApiImpl implements FileApi {

	@Autowired
	private FileService fileService;

	/**
	 * 文件上传2报销
	 * 
	 * @param file      文件内容
	 * @param fieldId   字段id
	 * @param fieldType 文件类型
	 * @param userId    登录者id
	 * @return
	 * @throws FileNotFoundException m("runProcessId") Long
	 *                               userId, @RequestParam("runProcessId") int
	 *                               fieldFuncti
	 */
	@Override
	public Result<Object> saveFiles(MultipartFile[] file, Long userId, String ruFlowId) throws FileNotFoundException {
		List<FileSaveResultForm> picFormList = FileUtil.createPic(file, SysUrlConst.FILEPATH + "\\" + SysStaticConst.CLAIMINGEXPENSES, SysUrlConst.PICPATH + SysStaticConst.CLAIMINGEXPENSES + "/");
		// 。压缩文件
		File zipfile = FileUtil.makeZip(SysUrlConst.FILEPATH + "\\" + SysStaticConst.CLAIMINGEXPENSES, SysStaticConst.ZIPNAME, SysUrlConst.ZIPPATH);
		// 。调用图片服务器接口
		HttpUtil.postData(SysUrlConst.PICSERVERPATH, zipfile);
		Long id = fileService.createFile(picFormList, userId);
		// 。删除源文件
		FileUtil.deleteFile(SysUrlConst.ZIPPATH + SysStaticConst.ZIPNAME);
		return new Result<>(SysStatusCodeConst.SUCCESS, id);
	}

}
