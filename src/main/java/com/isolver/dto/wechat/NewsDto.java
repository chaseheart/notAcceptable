package com.isolver.dto.wechat;

import com.isolver.entity.FileInfo;
import com.isolver.entity.News;

/**
 * 
 * @author IS1907006
 *
 */
public class NewsDto {
	/** ID **/
	private String id;
	/** 标题 **/
	private String title;
	/** 类型 **/
	private String type;
	/** 类型 **/
	private String content;
	/** 类型 **/
	private String introduction;
	/** 类型 **/
	private String filePath;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public NewsDto() {
		
	}

	public NewsDto(String id, String title, String type) {
		super();
		this.id = id;
		this.title = title;
		this.type = type;
	}
	
	public NewsDto(News news, FileInfo file) {
		this.id = news.getId().toString();
		this.title = news.getTitle();
		this.type = news.getType().toString();
		this.content = news.getContent();
		this.introduction = news.getIntroduction();
		if(file.getId() != null) {
			this.filePath = file.getFilePath() + file.getFileName();
		}
		
	}
}
