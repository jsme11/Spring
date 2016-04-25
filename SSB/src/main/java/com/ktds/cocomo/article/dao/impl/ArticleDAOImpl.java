package com.ktds.cocomo.article.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.cocomo.article.dao.ArticleDAO;
import com.ktds.cocomo.article.vo.ArticleVO;

public class ArticleDAOImpl extends SqlSessionDaoSupport implements ArticleDAO {

	@Override
	public int doWriteAction(ArticleVO articleVO) {
		return getSqlSession().insert("ArticleDAO.doWriteAction", articleVO);
	}
}
