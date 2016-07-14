package com.ktds.cocomo.memo.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktds.cocomo.memo.service.MemoService;

@Controller
public class MemoController {

	private MemoService memoService;

	public void setMemoService(MemoService memoService) {
		this.memoService = memoService;
	}

	@RequestMapping("test")
	public void test() {
		System.out.println("test");
	}
	
	@RequestMapping("/addMemo")
	@ResponseBody
	public Map<String, String> addMemo(HttpServletRequest request) {
		System.out.println("addMemo Controller");
		return memoService.addMemo(request);
	}
	
	@RequestMapping("/modifyMemo")
	@ResponseBody
	public Map<String, String> modifyMemo(HttpServletRequest request) {
		System.out.println("modifyMemo Controller");
		return memoService.modifyMemo(request);
	}
	
	@RequestMapping("/deleteMemo")
	@ResponseBody
	public Map<String, String> deleteMemo(HttpServletRequest request) {
		System.out.println("deleteMemo Controller");
		return memoService.deleteMemo(request);
	}
	
}
