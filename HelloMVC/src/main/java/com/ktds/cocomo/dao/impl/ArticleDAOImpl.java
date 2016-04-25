package com.ktds.cocomo.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.cocomo.dao.ArticleDAO;
import com.ktds.cocomo.vo.EmployeesVO;

public class ArticleDAOImpl extends SqlSessionDaoSupport implements ArticleDAO {

	@Override
	public String getNowSystemDate() {
		return getSqlSession().selectOne("ArticleDAO.getNowSystemDate");
	}

	@Override
	public List<EmployeesVO> getAllEmployeeInfo() {
		
		Map<String, Object> parameters = new HashMap<String, Object>();

		EmployeesVO employee = new EmployeesVO();
//		employee.setLastName("King");
//		employee.setFirstName("Steven");
		
		List<Integer> managerId = new ArrayList<Integer>();
		managerId.add(100);
		managerId.add(101);
		managerId.add(102);
		managerId.add(103);
		
		parameters.put("employee", employee);
		parameters.put("managerId", managerId);
		
		return getSqlSession().selectList("ArticleDAO.getAllEmployeeInfo", parameters);
	}
}
