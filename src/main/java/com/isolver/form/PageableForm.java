package com.isolver.form;

public class PageableForm {

	/** 。当前页面 **/
	private Integer page;
	/** 。每页条数 **/
	private Integer size;

	/**
	 * @return the page
	 */
	public Integer getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(Integer page) {
		this.page = page;
	}

	/**
	 * @return the size
	 */
	public Integer getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(Integer size) {
		this.size = size;
	}

	/**
	 * PageableForm.java
	 * @param page
	 * @param size
	 * 2019/12/06
	 */
	public PageableForm(Integer page, Integer size) {
		super();
		this.page = page;
		this.size = size;
	}

	/**
	 * PageableForm.java
	 * 2019/12/06
	 */
	public PageableForm() {
		super();
	}

}
