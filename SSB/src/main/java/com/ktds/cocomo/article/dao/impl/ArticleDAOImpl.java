package com.ktds.cocomo.article.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.cocomo.article.dao.ArticleDAO;
import com.ktds.cocomo.article.vo.ArticleSearchVO;
import com.ktds.cocomo.article.vo.ArticleVO;

public class ArticleDAOImpl extends SqlSessionDaoSupport implements ArticleDAO {

	@Override
	public int doWriteAction(ArticleVO articleVO) {
		return getSqlSession().insert("ArticleDAO.doWriteAction", articleVO);
	}

	@Override
	public int getNextArticleId() {
		return getSqlSession().selectOne("ArticleDAO.getNextArticleId");
	}

	@Override
	public String getNowDate() {
		return getSqlSession().selectOne("ArticleDAO.getNowDate");
	}

	@Override
	public int getTotalArticleCount() {
		return getSqlSession().selectOne("ArticleDAO.getTotalArticleCount");
	}

	@Override
	public List<ArticleVO> getAllArticleList(ArticleSearchVO searchVO) {
		return getSqlSession().selectList("ArticleDAO.getAllArticleList", searchVO);
	}

	@Override
	public ArticleVO getOneArticle(String articleId) {
		return getSqlSession().selectOne("ArticleDAO.getOneArticle", articleId);
	}

	@Override
	public int doModifyAction(ArticleVO changedArticleVO) {
		return getSqlSession().selectOne("ArticleDAO.doModifyAction", changedArticleVO);
	}
}
