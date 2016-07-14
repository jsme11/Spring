package com.ktds.cocomo.article.dao;

import java.util.List;

import com.ktds.cocomo.article.vo.ArticleSearchVO;
import com.ktds.cocomo.article.vo.ArticleVO;

public interface ArticleDAO {

	public int doWriteAction(ArticleVO articleVO);

	public int getNextArticleId();

	public String getNowDate();

	public int getTotalArticleCount();

	public List<ArticleVO> getAllArticleList(ArticleSearchVO searchVO);

	public ArticleVO getOneArticle(String articleId);

	public int doModifyAction(ArticleVO changedArticleVO);

}
