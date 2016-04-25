package com.ktds.cocomo.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.cocomo.biz.ArticleBiz;
import com.ktds.cocomo.vo.EmployeesVO;

@Controller
public class ArticleController {

	// Logger 생성은 클래스의 가장 위에 두어야 한다.
	private Logger logger = LoggerFactory.getLogger(ArticleController.class);
	
	private ArticleBiz articleBiz;
	
	public void setArticleBiz(ArticleBiz articleBiz) {
		this.articleBiz = articleBiz;
	}

	// view한테 어떤 데이터를 보내줄 것인지를 결정하는 것
	@RequestMapping("/list")
	public ModelAndView aritcleList() {
		
		articleBiz.insertNewArticle();
		
		logger.trace("안녕하세요. 트레이스 입니다.");
		logger.debug("안녕하세요. 디버그 입니다.");
		logger.info("안녕하세요. 인포 입니다.");
		logger.warn("안녕하세요. 워닝 입니다.");
		logger.error("안녕하세요. 에러 입니다.");
		
		ModelAndView view = new ModelAndView();
		view.setViewName("article/list");
		
		// Get All Employees
		List<EmployeesVO> allEmployees = articleBiz.getAllEmployeeInfo();
		view.addObject("allEmployees", allEmployees);
		
		// request.setAttribute("key", "value");
		view.addObject("title", "제목");
		view.addObject("number", "1");
		view.addObject("author", "홍길동");
		
		return view;
	}
	
	// public ModelAndView detail(HttpServletRequest reuqest)
	
	@RequestMapping("/detail/{articleNumber}")
	public ModelAndView detail(@PathVariable int articleNumber) {
		ModelAndView view = new ModelAndView();
		view.setViewName("article/detail");
		view.addObject("articleNumber", articleNumber);
		return view;
	}
}
