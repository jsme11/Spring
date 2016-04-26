package com.ktds.cocomo.article.service.impl;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.cocomo.article.biz.ArticleBiz;
import com.ktds.cocomo.article.service.ArticleService;
import com.ktds.cocomo.article.vo.ArticleListVO;
import com.ktds.cocomo.article.vo.ArticleSearchVO;
import com.ktds.cocomo.article.vo.ArticleVO;

import kr.co.hucloud.utilities.web.Paging;

public class ArticleServiceImpl implements ArticleService {

	private ArticleBiz articleBiz;

	public void setArticleBiz(ArticleBiz articleBiz) {
		this.articleBiz = articleBiz;
	}

	@Override
	public ModelAndView doWriteAction(ArticleVO articleVO, Errors errors) {

		ModelAndView view = new ModelAndView();

		if (errors.hasErrors()) {
			view.setViewName("article/write");
			view.addObject("articleVO", articleVO);
		} else {
			boolean isSuccess = articleBiz.doWriteAction(articleVO);
			if (isSuccess) {
				view.setViewName("redirect:/list");
			} else {
				throw new RuntimeException("일시적인 에러가 발생했습니다.");
			}
		}
		return view;
	}

	@Override
	public ModelAndView getAllArticleList(int pageNo) {

		ArticleListVO articleListVO = new ArticleListVO();
		Paging paging = new Paging();
		articleListVO.setPaging(paging);

		paging.setPageNumber(pageNo + "");
		paging.setTotalArticleCount(articleBiz.getTotalArticleCount());

		ArticleSearchVO searchVO = new ArticleSearchVO();
		searchVO.setStartIndex(paging.getStartArticleNumber());
		searchVO.setEndIndex(paging.getEndArticleNumber());

		List<ArticleVO> articleList = articleBiz.getAllArticleList(searchVO);
		articleListVO.setArticleList(articleList);

		ModelAndView view = new ModelAndView();
		view.setViewName("/article/list");
		view.addObject("articleListVO", articleListVO);

		return view;
	}

	@Override
	public ModelAndView getOneArticle(String articleId) {

		ModelAndView view = new ModelAndView();
		view.setViewName("/article/detail");
		view.addObject("articleVO", articleBiz.getOneArticle(articleId));

		return view;
	}

	@Override
	public ModelAndView doModifyAction(ArticleVO articleVO, Errors errors) {
		
		ModelAndView view = new ModelAndView();

		if (errors.hasErrors()) {
			view.setViewName("article/modify");
			view.addObject("articleVO", articleVO);
		} else {
			boolean isSuccess = articleBiz.doModifyAction(articleVO);
			if (isSuccess) {
				view.setViewName("redirect:/detail/" + articleVO.getArticleId());
			} else {
				throw new RuntimeException("일시적인 에러가 발생했습니다.");
			}
		}
		return view;
	}
}
