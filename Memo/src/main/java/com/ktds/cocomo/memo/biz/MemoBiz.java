package com.ktds.cocomo.memo.biz;

import javax.servlet.http.HttpServletRequest;

import com.ktds.cocomo.memo.vo.MemoVo;

public interface MemoBiz {

	public MemoVo addMemo(HttpServletRequest request);

	public boolean modifyMemo(MemoVo memo);

	public boolean deleteMemo(MemoVo memo);

}
