package com.ktds.cocomo.web;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.cocomo.vo.LoginVO;

import kr.co.hucloud.utilities.excel.option.ReadOption;
import kr.co.hucloud.utilities.excel.read.ExcelRead;

// servlet을 대체하는 것은 뒤에 Controller라고 이름을 짓는다.
// annotation Controller라고 한다.
@Controller
public class IndexController {

	private Logger logger = LoggerFactory.getLogger(IndexController.class);

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String index() {
		return "mainPage";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpSession session) {
		if (session.getAttribute("_MEMBER_") != null) {
			return "redirect:/home";
		}

		return "login/login";
	}

	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public ModelAndView doLogin(@Valid LoginVO loginVO, Errors errors, HttpSession session) {

		ModelAndView view = new ModelAndView();

		if (errors.hasErrors()) {
			view.setViewName("login/login");
		}
		view.setViewName("redirect:/home");

		MultipartFile uploadFile = loginVO.getUploadFile();
		if (!uploadFile.isEmpty()) {

			/*
			 * random 파일명 적용 String randomFileName =
			 * UUID.randomUUID().toString(); File tempFile = new File("D:\\" +
			 * randomFileName);
			 */

			File tempFile = new File("D:\\" + uploadFile.getOriginalFilename());
			try {
				uploadFile.transferTo(tempFile);
				String filePath = tempFile.getAbsolutePath();

				if (filePath.toUpperCase().endsWith(".XLS") || filePath.toUpperCase().endsWith(".XLSX")) {
					ReadOption option = new ReadOption();
					option.setFilePath(filePath);
					option.setStartRow(1);
					option.setOutputColumns("A", "B", "C");

					List<Map<String, String>> excel = ExcelRead.read(option);

					String content = "";
					for (Map<String, String> map : excel) {
						content = "";
						content += map.get("A");
						content += "\t";
						content += map.get("B");
						content += "\t";
						content += map.get("C");
						logger.info(content);
					}
				}

			} catch (IllegalStateException | IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		session.setAttribute("_MEMBER_", loginVO);
		return view;
	}
}
