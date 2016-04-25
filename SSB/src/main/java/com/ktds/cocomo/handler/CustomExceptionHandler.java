package com.ktds.cocomo.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice("com.ktds.cocomo")
public class CustomExceptionHandler {

	@ExceptionHandler({ RuntimeException.class })
	public ModelAndView runtimeExceptionHandler(RuntimeException re, HttpServletRequest request) {

		ModelAndView view = new ModelAndView();
		view.setViewName("error/500");
		view.addObject("message", re.getMessage());

		String referer = request.getHeader("Referer");
		view.addObject("from", referer);

		view.addObject("content", "내용");

		return view;
	}

}
