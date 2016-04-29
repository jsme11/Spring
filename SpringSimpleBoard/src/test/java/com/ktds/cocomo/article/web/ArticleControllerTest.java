package com.ktds.cocomo.article.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.bhyu.article.vo.ArticleListVO;
import com.ktds.bhyu.article.vo.ArticleVO;
import com.ktds.bhyu.article.web.ArticleController;

import kr.co.hucloud.utilities.web.Paging;

/*
 * 아래 Context 안에 있는 bean 들을 jUnit이 사용 하겠다! 선언
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml", "/articleContext.xml", "/rootContext.xml" })
public class ArticleControllerTest {

	/*
	 * @Autowired : 위에서 불러들인 파일 내에 articleController와 이름이 같은 bean이 있으면 가져옴
	 */
	@Autowired
	private ArticleController articleContoller;

	/*
	 * @Test : 테스트할 메소드를 명시 테스트 페이지에서 메소드를 사용할때 거의 리턴값은 'void'
	 */
	@Test
	public void viewWritePageTest() {

		ModelAndView view = articleContoller.viewWritePage();
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "article/write");
		} else {
			fail("fail");
		}
	}

	@Test
	public void doWriteActionTest() {

		ModelAndView listView = articleContoller.viewListPage(0);
		ArticleListVO articleListVO = (ArticleListVO) listView.getModel().get("articleListVO");
		int prevTotalCount = articleListVO.getPaging().getTotalArticleCount();

		ArticleVO articleVO = new ArticleVO();
		articleVO.setSubject("JUNIT Test, Subject");
		articleVO.setWriter("JUnit Test, Writer");
		articleVO.setDescription("JUnit Test, Description");

		BindingResult errors = new BeanPropertyBindingResult(articleVO, "writeForm");

		ModelAndView view = articleContoller.doWriteAction(articleVO, errors);
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "redirect:/list");

			listView = articleContoller.viewListPage(0);
			articleListVO = (ArticleListVO) listView.getModel().get("articleListVO");
			int nextTotalCount = articleListVO.getPaging().getTotalArticleCount();

			assertTrue(nextTotalCount - prevTotalCount == 1);

		} else {
			fail("fail");
		}
	}

	@Test
	public void doWriteActionTestWithError() {
		ArticleVO articleVO = new ArticleVO();
		articleVO.setWriter("JUnit Test2, Writer");
		articleVO.setDescription("JUnit Test2, Description");

		BindingResult errors = new BeanPropertyBindingResult(articleVO, "writeForm");
		ArticleVOValidator validator = new ArticleVOValidator();
		validator.validate(articleVO, errors);

		ModelAndView view = articleContoller.doWriteAction(articleVO, errors);
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "article/write");

			assertTrue(errors.getErrorCount() == 1);
		} else {
			fail("fail");
		}
	}

	@Test
	public void doWriteActionTestWithError2() {
		ArticleVO articleVO = new ArticleVO();
		articleVO.setSubject("JUnit Test2, Subject");
		articleVO.setWriter("JUnit Test2, Writer");

		BindingResult errors = new BeanPropertyBindingResult(articleVO, "writeForm");
		ArticleVOValidator validator = new ArticleVOValidator();
		validator.validate(articleVO, errors);

		ModelAndView view = articleContoller.doWriteAction(articleVO, errors);
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "article/write");

			assertTrue(errors.getErrorCount() == 1);
		} else {
			fail("fail");
		}
	}

	@Test
	public void doWriteActionTestWithError3() {
		ArticleVO articleVO = new ArticleVO();
		articleVO.setWriter("JUnit Test2, Writer");

		BindingResult errors = new BeanPropertyBindingResult(articleVO, "writeForm");
		ArticleVOValidator validator = new ArticleVOValidator();
		validator.validate(articleVO, errors);

		ModelAndView view = articleContoller.doWriteAction(articleVO, errors);
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "article/write");

			assertTrue(errors.getErrorCount() == 2);
		} else {
			fail("fail");
		}
	}

	@Test
	public void viewListPageTest() {
		ModelAndView view = articleContoller.viewListPage(0);
		assertNotNull(view);

		if (view != null) {
			String viewName = view.getViewName();
			assertNotNull(viewName);
			assertEquals(viewName, "article/list");

			ArticleListVO articleListVO = (ArticleListVO) view.getModelMap().get("articleListVO");
			assertNotNull(articleListVO);

			List<ArticleVO> articleList = articleListVO.getArticleList();
			assertNotNull(articleList);
			assertTrue(articleList.size() > 0);

			Paging paging = articleListVO.getPaging();
			assertNotNull(paging);
			assertTrue(paging.getTotalArticleCount() > 0);

		} else {
			fail("fail");
		}
	}

	public class ArticleVOValidator implements Validator {

		@Override
		public boolean supports(Class<?> clazz) {
			return ArticleVO.class.isAssignableFrom(clazz);
		}

		@Override
		public void validate(Object target, Errors errors) {
			ArticleVO articleVO = (ArticleVO) target;

			String subject = articleVO.getSubject();
			if (subject == null || subject.length() == 0) {
				errors.rejectValue("subject", "field.required", "error default message");
			}

			String description = articleVO.getDescription();
			if (description == null || description.length() == 0) {
				errors.rejectValue("description", "field.required", "error default message");
			}
		}
	}
}
