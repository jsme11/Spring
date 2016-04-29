package com.ktds.cocomo.article.biz;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ktds.bhyu.article.biz.ArticleBiz;
import com.ktds.bhyu.article.vo.ArticleSearchVO;
import com.ktds.bhyu.article.vo.ArticleVO;

import kr.co.hucloud.utilities.web.Paging;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml", "/articleContext.xml", "/rootContext.xml" })
public class ArticleBizTest {

	@Autowired
	private ArticleBiz articleBiz;
	private String articleId;

	@Test
	public void writeNewArticleTest() {

		ArticleVO articleVO = new ArticleVO();
		articleVO.setSubject("JUNIT Test, Subject in Biz Test");
		articleVO.setWriter("JUnit");
		articleVO.setDescription("JUnit Test, Description in Biz Test");

		boolean isSuccess = articleBiz.writeNewArticle(articleVO);
		assertTrue(isSuccess);
	}

	@Test
	public void getTotalArticleCountTest() {

		int totalArticleCount = articleBiz.getTotalArticleCount();
		assertTrue(totalArticleCount > 0);
	}

	@Test
	public void getAllArticleTest() {

		Paging paging = new Paging();
		paging.setPageNumber(0 + "");
		paging.setTotalArticleCount(articleBiz.getTotalArticleCount());

		if(paging != null) {
			
			ArticleSearchVO searchVO = new ArticleSearchVO();
			searchVO.setPageNo(0);
			searchVO.setStartIndex(paging.getStartArticleNumber());
			searchVO.setEndIndex(paging.getEndArticleNumber());
			
			List<ArticleVO> articleList = articleBiz.getAllArticle(searchVO);
			
			assertNotNull(articleList);
		} else {
			fail("fail");
		}
	}

	@Test
	public void getOneArticleTest() {

		String articleId = articleBiz.getArticleIdByWriter("JUnit");
		ArticleVO articleVO = articleBiz.getOneArticle(articleId);

		assertNotNull(articleVO);
	}

	@Test
	public void doDeleteArticleTest() {

		String articleId = articleBiz.getArticleIdByWriter("JUnit");
		
		boolean isSuccess = articleBiz.doDeleteArticle(articleId);

		assertTrue(isSuccess);
	}

	/*
	 * Modify - 1 변동사항 없음
	 */
	@Test
	public void doModifyArticleTest() {

		String articleId = articleBiz.getArticleIdByWriter("JUnit");
		ArticleVO articleVO = articleBiz.getOneArticle(articleId);

		if (articleVO != null) {
			boolean isSuccess = articleBiz.doModifyArticle(articleVO);
			assertTrue(isSuccess);
		} else {
			fail("fail");
		}
	}

	/*
	 * Modify - 2 제목만 변경
	 */
	@Test
	public void doModifyArticleTest2() {

		String articleId = articleBiz.getArticleIdByWriter("JUnit");
		ArticleVO articleVO = articleBiz.getOneArticle(articleId);

		if (articleVO != null) {
			articleVO.setSubject("JUNIT Test - 2, Changed Subject in Biz Test");
			boolean isSuccess = articleBiz.doModifyArticle(articleVO);
			assertTrue(isSuccess);
		} else {
			fail("fail");
		}
	}

	/*
	 * Modify - 3 내용만 변경
	 */
	@Test
	public void doModifyArticleTest3() {

		String articleId = articleBiz.getArticleIdByWriter("JUnit");
		ArticleVO articleVO = articleBiz.getOneArticle(articleId);

		if (articleVO != null) {
			articleVO.setDescription("JUNIT Test - 3, Changed Description in Biz Test");
			boolean isSuccess = articleBiz.doModifyArticle(articleVO);
			assertTrue(isSuccess);
		} else {
			fail("fail");
		}
	}

	/*
	 * Modify - 4 제목과 내용 모두 변경
	 */
	@Test
	public void doModifyArticleTest4() {

		String articleId = articleBiz.getArticleIdByWriter("JUnit");
		ArticleVO articleVO = articleBiz.getOneArticle(articleId);

		if (articleVO != null) {
			articleVO.setSubject("JUNIT Test - 4, Changed Subject in Biz Test");
			articleVO.setDescription("JUNIT Test - 4, Changed Description in Biz Test");
			boolean isSuccess = articleBiz.doModifyArticle(articleVO);
			assertTrue(isSuccess);
		} else {
			fail("fail");
		}
	}
}
