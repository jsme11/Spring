package com.ktds.cocomo.article.service;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.cocomo.article.vo.ArticleVO;

public interface ArticleService {

	public ModelAndView doWriteAction(ArticleVO articleVO, Errors errors);

}
