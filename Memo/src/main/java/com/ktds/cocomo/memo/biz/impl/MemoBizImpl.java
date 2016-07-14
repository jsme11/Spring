package com.ktds.cocomo.memo.biz.impl;

import javax.servlet.http.HttpServletRequest;

import com.ktds.cocomo.memo.biz.MemoBiz;
import com.ktds.cocomo.memo.dao.MemoDao;
import com.ktds.cocomo.memo.vo.MemoVo;

public class MemoBizImpl implements MemoBiz {

	private MemoDao memoDao;

	public void setMemoDao(MemoDao memoDao) {
		this.memoDao = memoDao;
	}

	@Override
	public MemoVo addMemo(HttpServletRequest request) {

		System.out.println("deleteMemo Biz");
		
		String nowDate = memoDao.nowDate();
		String onlyDate = nowDate.substring(0, 8);
		int nextMemoId = memoDao.nextMemoIdSeq();
		String memoId = "MEMO-" + onlyDate + "-" + lpad(nextMemoId + "", 6, "0");

		MemoVo memo = new MemoVo();
		memo.setMemoId(memoId);
		memo.setTitle(request.getParameter("title"));
		memo.setDescription(request.getParameter("description"));
		memo.setCreatedDate(nowDate);
		memo.setCode("I");
		
		if( memoDao.addMemoHistory(memo) == 0 ) {
			throw new RuntimeException("일시적인 에러가 발생했습니다.");
		}

		return memo;
	}

	@Override
	public boolean modifyMemo(MemoVo memo) {
		
		System.out.println("deleteMemo Biz");
		
		String nowDate = memoDao.nowDate();
		String onlyDate = nowDate.substring(0, 8);
		int nextMemoId = memoDao.nextMemoIdSeq();
		String memoId = "MEMO-" + onlyDate + "-" + lpad(nextMemoId + "", 6, "0");
		
		MemoVo newMemo = new MemoVo();
		newMemo.setMemoId(memoId);
		newMemo.setTitle(memo.getTitle());
		newMemo.setDescription(memo.getDescription());
		newMemo.setCreatedDate(nowDate);
		newMemo.setCode("M");
		
		return memoDao.addMemoHistory(newMemo) > 0;
	}

	@Override
	public boolean deleteMemo(MemoVo memo) {
		
		System.out.println("deleteMemo Biz");
		
		String nowDate = memoDao.nowDate();
		String onlyDate = nowDate.substring(0, 8);
		int nextMemoId = memoDao.nextMemoIdSeq();
		String memoId = "MEMO-" + onlyDate + "-" + lpad(nextMemoId + "", 6, "0");
		
		MemoVo newMemo = new MemoVo();
		newMemo.setMemoId(memoId);
		newMemo.setTitle(memo.getTitle());
		newMemo.setDescription(memo.getDescription());
		newMemo.setCreatedDate(nowDate);
		newMemo.setCode("D");
		
		return memoDao.addMemoHistory(newMemo) > 0;
	}
	
	private String lpad(String source, int length, String defValue) {
		int sourceLength = source.length();
		int needLength = length - sourceLength;
		for (int i = 0; i < needLength; i++) {
			source = defValue + source;
		}
		return source;
	}
}
