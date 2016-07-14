package com.ktds.hskim.article.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.hskim.CustomLogger;
import com.ktds.hskim.MongoLogger;
import com.ktds.hskim.article.service.ArticleService;
import com.ktds.hskim.article.vo.ArticleVO;

@Controller
public class ArticleController {
	
	private Logger logger = LoggerFactory.getLogger(ArticleController.class);
	
	// Subclass is a Superclass
	// Subclass → Superclass
	// Superclass ← Subclass
	// CustomLogger ← MongoLogger 
	private CustomLogger cLogger = new MongoLogger(logger, "mongoTemplate");
	
	private ArticleService articleService;
	
	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	@RequestMapping("/")
	public void index() {
		// 로그
		cLogger.info("Main Page 접근했습니다.");
	}
	
	@RequestMapping("/write")
	public ModelAndView viewWritePage() {
		
		ModelAndView view = new ModelAndView();
		view.setViewName("article/write");
		
		// 로그
		cLogger.info("Write Page 접근했습니다.");
		return view;
	}
	
	@RequestMapping("/doWriteAction")
	public ModelAndView doWriteAction(@Valid ArticleVO articleVO, Errors errors) {
		
		// 로그
		cLogger.info("글을 작성했습니다.");
		return articleService.writeNewArticle(articleVO, errors);
	}
	
	@RequestMapping("/list")
	public ModelAndView viewListPage(@RequestParam(required=false, defaultValue="0") int pageNo) {

		// 로그
		cLogger.info("List Page 접근했습니다.");
		return articleService.getAllArticleList(pageNo);
	}
	
	@RequestMapping("/detail/{articleId}")
	public ModelAndView viewDetailPage(@PathVariable String articleId) {
		
		ModelAndView view = new ModelAndView();
		view.setViewName("article/detail");
		view.addObject("article", articleService.getOneArticle(articleId));
		
		// 로그
		cLogger.info("Detail Page 접근했습니다.");
		return view;
	}
	
	@RequestMapping("/delete/{articleId}")
	public ModelAndView doDelete(@PathVariable String articleId) {
		
		// 로그
		cLogger.info("삭제를 꼼꼼이 했습니다.");
		return articleService.deleteArticle(articleId);
	}
	
	@RequestMapping("/modify/{articleId}")
	public ModelAndView doModify(@PathVariable String articleId) {
		
		// 로그
		cLogger.info("수정을 꼼꼼이 했습니다.");
		return articleService.modifyArticle(articleId);
	}
	
}
