package com.isolver.common.util;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;

import com.isolver.common.constant.ProcessEngineUtilConst;


public class ProcessEngineUtil {
	
	public static ProcessEngine getProcessEngine() {
		ProcessEngine processEngine = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration()
				.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_FALSE)
				.setJdbcUrl(ProcessEngineUtilConst.JDBCURL).setJdbcDriver(ProcessEngineUtilConst.JDBCDRIVER)
				.setJdbcUsername(ProcessEngineUtilConst.JDBCUSERNAME).setJdbcPassword(ProcessEngineUtilConst.JDBCPASSWORD).setDatabaseSchemaUpdate("true")
				.setActivityFontName("宋体").setLabelFontName("宋体").buildProcessEngine();
		return processEngine;

	}
}
