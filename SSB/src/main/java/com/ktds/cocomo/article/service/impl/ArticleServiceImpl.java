package com.ktds.cocomo.article.service.impl;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.cocomo.article.biz.ArticleBiz;
import com.ktds.cocomo.article.service.ArticleService;
import com.ktds.cocomo.article.vo.ArticleVO;

public class ArticleServiceImpl implements ArticleService {

	private ArticleBiz articleBiz;

	public void setArticleBiz(ArticleBiz articleBiz) {
		this.articleBiz = articleBiz;
	}

	@Override
	public ModelAndView doWriteAction(ArticleVO articleVO, Errors errors) {

		ModelAndView view = new ModelAndView();

		if (errors.hasErrors()) {
			view.setViewName("article/write");
			view.addObject("articleVO", articleVO);
		} else {
			boolean isSuccess = articleBiz.doWriteAction(articleVO);
			if (isSuccess) {
				view.setViewName("redirect:/list");
			} else {
				throw new RuntimeException("일시적인 에러가 발생했습니다.");
			}
		}
		return view;
	}
}
