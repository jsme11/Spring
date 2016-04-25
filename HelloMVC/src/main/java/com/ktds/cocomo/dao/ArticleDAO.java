package com.ktds.cocomo.dao;

import java.util.List;

import com.ktds.cocomo.vo.EmployeesVO;

public interface ArticleDAO {
	
	public String getNowSystemDate();
	
	public List<EmployeesVO> getAllEmployeeInfo();
	
}
