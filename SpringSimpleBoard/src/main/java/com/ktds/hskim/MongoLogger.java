package com.ktds.hskim;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class MongoLogger extends CustomLogger {

	private MongoTemplate mongoTemplate;

	private MongoLogger(Logger logger) {
		super(logger);
	}

	// 받아온 mongoTemplate을 입력한다.
	public MongoLogger(Logger logger, MongoTemplate mongoTemplate) {
		super(logger);
		this.mongoTemplate = mongoTemplate;
	}

	// mongoTemplate을 보낼 수 없을 때, beanName을 보내서
	// beanName을 통해 mongoTemplate을 입력한다.
	public MongoLogger(Logger logger, String mongoTemplateBeanName) {
		super(logger);
//		setMongoTemplate(mongoTemplateBeanName);
		setMongoTemplateFromXML(mongoTemplateBeanName);
	}

	/**
	 * Set Mongo Template
	 * 
	 * @param mongoTemplateBeanName
	 */
	private void setMongoTemplate(String mongoTemplateBeanName) {

		// Request
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		// Session
		HttpSession session = request.getSession();

		// Context
		ServletContext context = session.getServletContext();

		// WebContext
		WebApplicationContext webContext = WebApplicationContextUtils.getWebApplicationContext(context);

		// MongoTemplate 입력
		this.mongoTemplate = (MongoTemplate) webContext.getBean(mongoTemplateBeanName);
	}
	
	/**
	 * Set Mongo Template From XML
	 * 
	 * @param mongoTemplateBeanName
	 */
	private void setMongoTemplateFromXML(String mongoTemplateBeanName) {
		
		String mongoContext = "/mongoContextForLogger.xml";
		AbstractApplicationContext ctx =
				new ClassPathXmlApplicationContext(mongoContext);
		
		this.mongoTemplate = (MongoTemplate) ctx.getBean(mongoTemplateBeanName);
	}

	/*
	 * < 필요한 정보 >
	 * 
	 * 1. LEVEL : TRACE, DEBUG, INFO, WARN, ERROR
	 * 2. 시간 : 언제 찍혔는지
	 * 3. 요청자 : 누가찍었는지
	 * 4. 로그 메시지 : 무엇을 찍었는지
	 */
	@Override
	protected void writeTrace(String msg) {
		Map<String, Object> log = new HashMap<String, Object>();
		log.put("LEVEL", "TRACE");
		log.put("DATETIME", new Date());
		log.put("REQUESTER", logger.getName());
		log.put("MESSAGE", msg);

		mongoTemplate.insert(log, "log");
	}

	@Override
	protected void writeDebug(String msg) {
		Map<String, Object> log = new HashMap<String, Object>();
		log.put("LEVEL", "DEBUG");
		log.put("DATETIME", new Date());
		log.put("REQUESTER", logger.getName());
		log.put("MESSAGE", msg);

		mongoTemplate.insert(log, "log");
	}

	@Override
	protected void writeInfo(String msg) {
		Map<String, Object> log = new HashMap<String, Object>();
		log.put("LEVEL", "INFO");
		log.put("DATETIME", new Date());
		log.put("REQUESTER", logger.getName());
		log.put("MESSAGE", msg);

		mongoTemplate.insert(log, "log");
	}

	@Override
	protected void writeWarn(String msg) {
		Map<String, Object> log = new HashMap<String, Object>();
		log.put("LEVEL", "WARN");
		log.put("DATETIME", new Date());
		log.put("REQUESTER", logger.getName());
		log.put("MESSAGE", msg);

		mongoTemplate.insert(log, "log");
	}

	@Override
	protected void writeError(String msg) {
		Map<String, Object> log = new HashMap<String, Object>();
		log.put("LEVEL", "ERROR");
		log.put("DATETIME", new Date());
		log.put("REQUESTER", logger.getName());
		log.put("MESSAGE", msg);

		mongoTemplate.insert(log, "log");
	}

}
