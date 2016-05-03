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

	@Test
	public void writeNewArticleTest() {

		ArticleVO articleVO = new ArticleVO();
		articleVO.setSubject("JUnit, Subject in Biz");
		articleVO.setWriter("JUnit");
		articleVO.setDescription("JUnit, Description in Biz");

		assertTrue(articleBiz.writeNewArticle(articleVO));
	}

	@Test
	public void getTotalArticleCountTest() {
		assertTrue(articleBiz.getTotalArticleCount() > 0);
	}

	@Test
	public void getAllArticleTest() {

		Paging paging = new Paging();
		paging.setPageNumber(0 + "");

		int totalArticleCount = articleBiz.getTotalArticleCount();

		if (totalArticleCount > 0) {
			paging.setTotalArticleCount(totalArticleCount);
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

		String articleId = articleBiz.getOneArticleIdByWriter("JUnit");

		if (articleId != null) {
			ArticleVO articleVO = articleBiz.getOneArticle(articleId);
			if(articleVO != null) {
				assertNotNull(articleVO.getArticleId());
				assertNotNull(articleVO.getArticleNumber());
				assertNotNull(articleVO.getWriter());
				assertNotNull(articleVO.getSubject());
				assertNotNull(articleVO.getDescription());
				assertNotNull(articleVO.getCreatedDate());
				assertNotNull(articleVO.getModifiedDate());
			}
		} else {
			fail("fail");
		}
	}

	@Test
	public void doDeleteArticleTest() {

		String articleId = articleBiz.getOneArticleIdByWriter("JUnit");

		if (articleId != null) {
			assertTrue(articleBiz.doDeleteArticle(articleId));
		} else {
			fail("fail");
		}
	}

	/*
	 * Modify - 1 변동사항 없음
	 */
	@Test
	public void doModifyArticleTest() {

		String articleId = articleBiz.getOneArticleIdByWriter("JUnit");

		if (articleId != null) {
			ArticleVO articleVO = articleBiz.getOneArticle(articleId);

			if (articleVO != null) {
				assertTrue(articleBiz.doModifyArticle(articleVO));
			} else {
				fail("fail");
			}
		} else {
			fail("fail");
		}
	}

	/*
	 * Modify - 2 제목만 변경
	 */
	@Test
	public void doModifyArticleTest2() {

		String articleId = articleBiz.getOneArticleIdByWriter("JUnit");
		if (articleId != null) {

			ArticleVO articleVO = articleBiz.getOneArticle(articleId);
			if (articleVO != null) {
				articleVO.setSubject("JUnit, Changed Subject in Biz");
				assertTrue(articleBiz.doModifyArticle(articleVO));
			} else {
				fail("fail");
			}
		} else {
			fail("fail");
		}
	}

	/*
	 * Modify - 3 내용만 변경
	 */
	@Test
	public void doModifyArticleTest3() {

		String articleId = articleBiz.getOneArticleIdByWriter("JUnit");

		if (articleId != null) {
			ArticleVO articleVO = articleBiz.getOneArticle(articleId);

			if (articleVO != null) {
				articleVO.setDescription("JUnit, Changed Description in Biz");
				assertTrue(articleBiz.doModifyArticle(articleVO));
			} else {
				fail("fail");
			}
		} else {
			fail("fail");
		}
	}

	/*
	 * Modify - 4 제목과 내용 모두 변경
	 */
	@Test
	public void doModifyArticleTest4() {

		String articleId = articleBiz.getOneArticleIdByWriter("JUnit");

		if (articleId != null) {
			ArticleVO articleVO = articleBiz.getOneArticle(articleId);

			if (articleVO != null) {
				articleVO.setSubject("JUnit, Changed Subject in Biz - 2");
				articleVO.setDescription("JUnit, Changed Description in Biz - 2");
				assertTrue(articleBiz.doModifyArticle(articleVO));
			} else {
				fail("fail");
			}
		} else {
			fail("fail");
		}
	}
}
