package com.ktds.cocomo.article.biz;

import java.util.List;

import com.ktds.cocomo.article.vo.ArticleSearchVO;
import com.ktds.cocomo.article.vo.ArticleVO;

public interface ArticleBiz {

	public boolean doWriteAction(ArticleVO articleVO);

	public int getTotalArticleCount();

	public List<ArticleVO> getAllArticleList(ArticleSearchVO searchVO);

	public ArticleVO getOneArticle(String articleId);

	public boolean doModifyAction(ArticleVO articleVO);

}
