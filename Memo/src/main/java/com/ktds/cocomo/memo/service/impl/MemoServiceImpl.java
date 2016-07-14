package com.ktds.cocomo.memo.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ktds.cocomo.memo.biz.MemoBiz;
import com.ktds.cocomo.memo.service.MemoService;
import com.ktds.cocomo.memo.vo.MemoVo;

public class MemoServiceImpl implements MemoService {

	private MemoBiz memoBiz;

	public void setMemoBiz(MemoBiz memoBiz) {
		this.memoBiz = memoBiz;
	}

	@Override
	public Map<String, String> addMemo(HttpServletRequest request) {

		System.out.println("addMemo Service");
		
		MemoVo memo = memoBiz.addMemo(request);
		Map<String, String> result = new HashMap<String, String>();
		result.put("memoId", memo.getMemoId());
		result.put("title", memo.getTitle());
		result.put("description", memo.getDescription());
		result.put("createdDate", memo.getCreatedDate());
		result.put("code", "I");
		
		return result;
	}

	@Override
	public Map<String, String> modifyMemo(HttpServletRequest request) {
		
		System.out.println("modifyMemo Service");
		
		MemoVo memo = new MemoVo();
		memo.setMemoId(request.getParameter("memoId"));
		memo.setTitle(request.getParameter("title"));
		memo.setDescription(request.getParameter("description"));
		memo.setCode("M");
		
		Map<String, String> result = new HashMap<String, String>();
		if( memoBiz.modifyMemo(memo) ) {
			result.put("memoId", memo.getMemoId());
			result.put("title", memo.getTitle());
			result.put("description", memo.getDescription());
			result.put("code", "M");
		} else {
			throw new RuntimeException("일시적인 에러가 발생했습니다.");
		}
		
		return result;
	}

	@Override
	public Map<String, String> deleteMemo(HttpServletRequest request) {
		
		System.out.println("deleteMemo Service");
		
		MemoVo memo = new MemoVo();
		memo.setMemoId(request.getParameter("memoId"));
		memo.setTitle(request.getParameter("title"));
		memo.setDescription(request.getParameter("description"));
		memo.setCode("D");
		
		Map<String, String> result = new HashMap<String, String>();
		if( memoBiz.deleteMemo(memo) ) {
			result.put("memoId", memo.getMemoId());
			result.put("code", "D");
		} else {
			throw new RuntimeException("일시적인 에러가 발생했습니다.");
		}
		
		return result;
	}
}
