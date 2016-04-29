package com.ktds.cocomo.article.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ktds.bhyu.article.dao.ArticleDAO;
import com.ktds.bhyu.article.vo.ArticleSearchVO;
import com.ktds.bhyu.article.vo.ArticleVO;

import kr.co.hucloud.utilities.web.Paging;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml", "/articleContext.xml", "/rootContext.xml" })
public class ArticleDAOTest {

	@Autowired
	private ArticleDAO articleDAO;

	@Test
	public void insertNewArticleTest() {

		int articleNumber = articleDAO.nextArticleSeq();
		if (articleNumber > 0) {
			String nowDate = articleDAO.nowDate();

			/*
			 * ArticleID의 형식 AR-20160421-000001
			 */
			String articleId = "AR-" + nowDate + "-" + lpad(articleNumber + "", 6, "0");
			if (nowDate != null) {

				ArticleVO articleVO = new ArticleVO();
				articleVO.setArticleId(articleId);
				articleVO.setArticleNumber(articleNumber);
				articleVO.setWriter("JUnit");
				articleVO.setSubject("JUnit test subject in DAO Test insertNewArticle Method");
				articleVO.setDescription("JUnit test Description in DAO Test insertNewArticle Method");

				int executeQuery = articleDAO.insertNewArticle(articleVO);
				assertTrue(executeQuery > 0);
			} else {
				fail("fail");
			}
		} else {
			fail("fail");
		}
	}

	@Test
	public void getTotalArticleCountTest() {

		int executeQuery = articleDAO.getTotalArticleCount();
		assertTrue(executeQuery > 0);
	}

	@Test
	public void getAllArticleTest() {

		Paging paging = new Paging();
		paging.setPageNumber(0 + "");
		paging.setTotalArticleCount(articleDAO.getTotalArticleCount());

		if (paging != null) {

			ArticleSearchVO searchVO = new ArticleSearchVO();
			searchVO.setPageNo(0);
			searchVO.setStartIndex(paging.getStartArticleNumber());
			searchVO.setEndIndex(paging.getEndArticleNumber());

			List<ArticleVO> articleList = articleDAO.getAllArticle(searchVO);
			assertNotNull(articleList);
		} else {
			fail("fail");
		}
	}

	@Test
	public void getOneArticleTest() {

		String articleId = articleDAO.getArticleIdByWriter("JUnit");

		if (articleId != null) {
			ArticleVO articleVO = articleDAO.getOneArticle(articleId);
			if (articleVO != null) {
				assertNotNull(articleVO.getArticleId());
				assertNotNull(articleVO.getArticleNumber());
				assertNotNull(articleVO.getWriter());
				assertNotNull(articleVO.getSubject());
				assertNotNull(articleVO.getDescription());
				assertNotNull(articleVO.getCreatedDate());
				assertNotNull(articleVO.getModifiedDate());
			} else {
				fail("fail");
			}
		} else {
			fail("fail");
		}
	}

	@Test
	public void doDeleteArticleTest() {

		String articleId = articleDAO.getArticleIdByWriter("JUnit");
		if (articleId != null) {
			int executeQuery = articleDAO.doDeleteArticle(articleId);
			assertTrue(executeQuery > 0);
		} else {
			fail("fail");
		}
	}

	@Test
	public void doModifyArticleTest() {

		String articleId = articleDAO.getArticleIdByWriter("JUnit");

		if (articleId != null) {
			ArticleVO articleVO = articleDAO.getOneArticle(articleId);

			if (articleVO != null) {
				int executeQuery = articleDAO.doModifyArticle(articleVO);
				assertTrue(executeQuery > 0);
			} else {
				fail("fail");
			}
		} else {
			fail("fail");
		}
	}

	@Test
	public void doModifyArticleTest2() {

		String articleId = articleDAO.getArticleIdByWriter("JUnit");

		if (articleId != null) {
			ArticleVO articleVO = articleDAO.getOneArticle(articleId);

			if (articleVO != null) {
				articleVO.setSubject("JUnit test - 2 Subject in DAO Test");
				int executeQuery = articleDAO.doModifyArticle(articleVO);
				assertTrue(executeQuery > 0);
			} else {
				fail("fail");
			}
		} else {
			fail("fail");
		}
	}

	@Test
	public void doModifyArticleTest3() {

		String articleId = articleDAO.getArticleIdByWriter("JUnit");

		if (articleId != null) {
			ArticleVO articleVO = articleDAO.getOneArticle(articleId);

			if (articleVO != null) {
				articleVO.setDescription("JUnit test - 3 Description in DAO Test");
				int executeQuery = articleDAO.doModifyArticle(articleVO);
				assertTrue(executeQuery > 0);
			} else {
				fail("fail");
			}
		} else {
			fail("fail");
		}
	}

	@Test
	public void doModifyArticleTest4() {

		String articleId = articleDAO.getArticleIdByWriter("JUnit");

		if (articleId != null) {
			ArticleVO articleVO = articleDAO.getOneArticle(articleId);

			if (articleVO != null) {
				articleVO.setSubject("JUnit test - 4 Subject in DAO Test");
				articleVO.setDescription("JUnit test - 4 Description in DAO Test");
				int executeQuery = articleDAO.doModifyArticle(articleVO);
				assertTrue(executeQuery > 0);
			} else {
				fail("fail");
			}
		} else {
			fail("fail");
		}
	}

	/**
	 * 
	 * @param source
	 *            원본
	 * @param length
	 *            만들어져야 하는 길이
	 * @param defValue
	 *            채워질 글자
	 * @return
	 */
	private String lpad(String source, int length, String defValue) {
		int sourceLength = source.length();
		int needLength = length - sourceLength;

		for (int i = 0; i < needLength; i++) {
			source = defValue + source;
		}
		return source;
	}
}
