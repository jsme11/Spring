package com.ktds.cocomo.article.service;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.cocomo.article.vo.ArticleVO;

public interface ArticleService {

	public ModelAndView doWriteAction(ArticleVO articleVO, Errors errors);

	public ModelAndView getAllArticleList(int pageNo);

	public ModelAndView getOneArticle(String articleId);

	public ModelAndView doModifyAction(ArticleVO articleVO, Errors errors);

}
