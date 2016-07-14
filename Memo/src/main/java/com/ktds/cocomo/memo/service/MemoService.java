package com.ktds.cocomo.memo.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface MemoService {

	public Map<String, String> addMemo(HttpServletRequest request);

	public Map<String, String> modifyMemo(HttpServletRequest request);

	public Map<String, String> deleteMemo(HttpServletRequest request);

}
