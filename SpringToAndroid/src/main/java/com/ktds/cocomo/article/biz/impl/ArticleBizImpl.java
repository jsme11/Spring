package com.ktds.cocomo.article.biz.impl;

import com.ktds.cocomo.article.biz.ArticleBiz;
import com.ktds.cocomo.article.dao.ArticleDao;

public class ArticleBizImpl implements ArticleBiz {

	private ArticleDao articleDao;

	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

}
