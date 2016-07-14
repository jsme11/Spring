package com.ktds.cocomo.article.biz.impl;

import java.util.List;

import com.ktds.cocomo.article.biz.ArticleBiz;
import com.ktds.cocomo.article.dao.ArticleDAO;
import com.ktds.cocomo.article.vo.ArticleSearchVO;
import com.ktds.cocomo.article.vo.ArticleVO;

public class ArticleBizImpl implements ArticleBiz {

	private ArticleDAO articleDAO;

	public void setArticleDAO(ArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
	}

	@Override
	public boolean doWriteAction(ArticleVO articleVO) {

		int articleNumber = articleDAO.getNextArticleId();
		String nowDate = articleDAO.getNowDate();

		String articleId = "AR-" + nowDate + "-" + lpad(articleNumber + "", 6, "0");

		articleVO.setArticleId(articleId);
		articleVO.setArticleNumber(articleNumber);

		return articleDAO.doWriteAction(articleVO) > 0;
	}

	private String lpad(String stringToLpad, int totalLength, String padValue) {

		int needLength = totalLength - stringToLpad.length();

		for (int i = 0; i < needLength; i++) {
			stringToLpad = padValue + stringToLpad;
		}

		return stringToLpad;
	}

	@Override
	public int getTotalArticleCount() {
		return articleDAO.getTotalArticleCount();
	}

	@Override
	public List<ArticleVO> getAllArticleList(ArticleSearchVO searchVO) {
		return articleDAO.getAllArticleList(searchVO);
	}

	@Override
	public ArticleVO getOneArticle(String articleId) {
		return articleDAO.getOneArticle(articleId);
	}

	@Override
	public boolean doModifyAction(ArticleVO articleVO) {

		ArticleVO originArticleVO = articleDAO.getOneArticle(articleVO.getArticleId());
		ArticleVO changedArticleVO = new ArticleVO();

		boolean isChangedSubject = !originArticleVO.getSubject().equals(articleVO.getSubject());
		boolean isChangedDescription = !originArticleVO.getDescription().equals(articleVO.getDescription());

		if (!isChangedSubject && !isChangedDescription) {
			return true;
		}
		if (isChangedSubject) {
			changedArticleVO.setSubject(articleVO.getSubject());
		}
		if (isChangedDescription) {
			changedArticleVO.setSubject(articleVO.getDescription());
		}

		return articleDAO.doModifyAction(changedArticleVO) > 0;
	}

}
