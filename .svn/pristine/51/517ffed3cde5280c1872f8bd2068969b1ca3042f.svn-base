package com.isolver.dao.fileInfo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.isolver.dto.wechat.FileInfoDto;
import com.isolver.entity.FileInfo;

/**
 * @author IS1907005
 * @date 2019/08/20
 * @class FileInfoRepository.java
 */
@Repository
public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
	
	/**
	 * 根据表名和表id查找附件
	 * @param tableName
	 * @param tableId
	 * @return
	 */
	public List<FileInfo> findByTableNameAndTableIdAndDeleteFlag(String tableName, String tableId, Boolean deleteFlag);
	
	/**
	 * 根据表名和表id查找附件
	 * @param tableName
	 * @param tableId
	 * @return
	 */
	@Query("select new com.isolver.dto.wechat.FileInfoDto(CONCAT(a.filePath,'' ) as filePath, "
			+ " CONCAT(a.fileName,'' ) as fileName ) from FileInfo a where a.tableName  = :tableName and a.tableId  = :tableId and a.deleteFlag = :deleteFlag")
	public List<FileInfoDto> findFileInfoDtoByTableNameAndTableIdAndDeleteFlag(String tableName, String tableId, Boolean deleteFlag);

}
