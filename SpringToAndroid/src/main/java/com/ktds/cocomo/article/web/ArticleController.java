package com.ktds.cocomo.article.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktds.cocomo.article.service.ArticleService;

@Controller
public class ArticleController {

	private ArticleService articleService;

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	@RequestMapping("/test")
	public String test(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		return "aa";
	}
	
	@RequestMapping("/android")
	public void androidTest() {
		System.out.println("ANDROID로 접근했습니다.");
	}
	
	@RequestMapping("/android2")
	public void androidTestWithRequest(HttpServletRequest request) {
	//		System.out.println(request.getParameter("test"));
		System.out.println(request.getParameter("title"));
		System.out.println(request.getParameter("memo"));
	}
	
	@RequestMapping("/android3")
	@ResponseBody
	public Map<String, String> androidTestWithRequestAndResponse(HttpServletRequest request) {
		System.out.println(request.getParameter("title"));
		System.out.println(request.getParameter("memo"));
		
		Map<String, String> result = new HashMap<String, String>();
		result.put("data1", "웹에서 보낸 메모1");
		result.put("data2", "웹에서 보낸 메모2");
		
		return result;
	}
	
	@RequestMapping("/sendPicture")
	public void sendPicture(HttpServletRequest request) {
		System.out.println(request.getParameter("image"));
	}
}
