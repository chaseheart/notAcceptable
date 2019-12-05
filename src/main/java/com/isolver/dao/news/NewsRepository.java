package com.isolver.dao.news;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.isolver.entity.News;

/**
 * @author IS1907005
 * @date 2019/11/08
 * @class NewsRepository.java
 */
@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

	/**
	 * 根据消息类型查询新闻
	 * 
	 * @param type
	 * @param deleteFlag
	 * @return
	 */
	public List<News> findByTypeAndDeleteFlag(Integer type, Boolean deleteFlag);

	/**
	 * 获得新闻详细
	 * 
	 * @param id
	 * @param deleteFlag
	 * @return
	 */
	public News findByIdAndDeleteFlag(Long id, Boolean deleteFlag);

	/**
	 * 检索新闻
	 * 
	 * @param content
	 * @param title
	 * @param type
	 * @param deleteFlag
	 * @return
	 */
	@Query("SELECT a FROM News a WHERE (a.content LIKE :content OR a.title LIKE :content ) AND a.type = :type AND a.deleteFlag = :deleteFlag")
	public List<News> findByContent(@Param("content") String content, @Param("type") Integer type, @Param("deleteFlag") Boolean deleteFlag);
}
