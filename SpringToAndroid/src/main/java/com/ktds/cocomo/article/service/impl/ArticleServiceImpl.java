package com.ktds.cocomo.article.service.impl;

import com.ktds.cocomo.article.biz.ArticleBiz;
import com.ktds.cocomo.article.service.ArticleService;

public class ArticleServiceImpl implements ArticleService {

	private ArticleBiz articleBiz;

	public void setArticleBiz(ArticleBiz articleBiz) {
		this.articleBiz = articleBiz;
	}

}
