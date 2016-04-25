package com.ktds.cocomo.article.biz.impl;

import com.ktds.cocomo.article.biz.ArticleBiz;
import com.ktds.cocomo.article.dao.ArticleDAO;
import com.ktds.cocomo.article.vo.ArticleVO;

public class ArticleBizImpl implements ArticleBiz {

	private ArticleDAO articleDAO;

	public void setArticleDAO(ArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
	}

	@Override
	public boolean doWriteAction(ArticleVO articleVO) {
		return articleDAO.doWriteAction(articleVO) > 0;
	}

}
