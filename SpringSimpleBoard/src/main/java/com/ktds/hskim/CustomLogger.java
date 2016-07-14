package com.ktds.hskim;

import org.slf4j.Logger;

// 추상클래스
public abstract class CustomLogger {

	// protected : 상속관계에서만 사용 가능
	protected Logger logger;

	public CustomLogger(Logger logger) {
		this.logger = logger;
	}

	public void trace(String msg) {
		logger.trace(msg);
		writeTrace(msg);
	}

	public void debug(String msg) {
		logger.debug(msg);
		writeDebug(msg);
	}

	public void info(String msg) {
		logger.info(msg);
		writeInfo(msg);
	}

	public void warn(String msg) {
		logger.warn(msg);
		writeWarn(msg);
	}

	public void error(String msg) {
		logger.error(msg);
		writeError(msg);
	}

	// 추상 클래스
	// 위의 메소드에서 logger 기능은 CustomLogger에서 담당하지만
	// write... 기능들은 CustomLogger를 상속받은 클래스에게 맡긴다.
	protected abstract void writeTrace(String msg);

	protected abstract void writeDebug(String msg);

	protected abstract void writeInfo(String msg);

	protected abstract void writeWarn(String msg);

	protected abstract void writeError(String msg);

}
