package com.ktds.cocomo.memo.dao;

import com.ktds.cocomo.memo.vo.MemoVo;

public interface MemoDao {

	public String nowDate();

	public int nextMemoIdSeq();

	public int addMemoHistory(MemoVo memo);

}
