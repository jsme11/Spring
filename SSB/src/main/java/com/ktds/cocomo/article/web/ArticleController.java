package com.ktds.cocomo.article.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.cocomo.article.service.ArticleService;
import com.ktds.cocomo.article.vo.ArticleVO;

@Controller
public class ArticleController {

	private ArticleService articleService;

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	@RequestMapping("/write")
	public ModelAndView viewWritePage() {
		ModelAndView view = new ModelAndView();
		view.setViewName("article/write");
		return view;
	}

	@RequestMapping("/doWriteAction")
	public ModelAndView doWriteAction(@Valid ArticleVO articleVO, Errors errors) {
		return articleService.doWriteAction(articleVO, errors);
	}
}
