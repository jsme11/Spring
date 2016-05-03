package com.ktds.bhyu.article.dao;

import java.util.List;

import com.ktds.bhyu.article.vo.ArticleSearchVO;
import com.ktds.bhyu.article.vo.ArticleVO;

public interface ArticleDAO {

	public int nextArticleSeq();

	public String nowDate();

	public int insertNewArticle(ArticleVO articleVO);

	public int getTotalArticleCount();

	public List<ArticleVO> getAllArticle(ArticleSearchVO searchVO);

	public ArticleVO getOneArticle(String articleId);

	public int doDeleteArticle(String articleId);

	public int doModifyArticle(ArticleVO articleVO);

	public String getOneArticleIdByWriter(String writer);

}
