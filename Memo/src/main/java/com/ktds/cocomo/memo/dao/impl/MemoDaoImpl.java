package com.ktds.cocomo.memo.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.cocomo.memo.dao.MemoDao;
import com.ktds.cocomo.memo.vo.MemoVo;

public class MemoDaoImpl extends SqlSessionDaoSupport implements MemoDao {

	@Override
	public String nowDate() {
		return getSqlSession().selectOne("MemoDao.nowDate");

	}

	@Override
	public int nextMemoIdSeq() {
		return getSqlSession().selectOne("MemoDao.nextMemoIdSeq");

	}

	@Override
	public int addMemoHistory(MemoVo memo) {
		System.out.println("******************");
		System.out.println("addMemoHistory Dao");
		System.out.println(memo.getMemoId());
		System.out.println(memo.getTitle());
		System.out.println(memo.getDescription());
		System.out.println(memo.getCreatedDate());
		System.out.println(memo.getCode());
		System.out.println("******************");
		return getSqlSession().insert("MemoDao.addMemoHistory", memo);
	}
}
