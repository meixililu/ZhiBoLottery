package com.zhibo.lotterystore.dao;

import java.util.List;

public class Showapi_res_body {
	private List<Result> result;

	private int ret_code;

	public List<Result> getResult() {
		return result;
	}

	public void setResult(List<Result> result) {
		this.result = result;
	}

	public void setRet_code(int ret_code) {
		this.ret_code = ret_code;
	}

	public int getRet_code() {
		return this.ret_code;
	}

}
