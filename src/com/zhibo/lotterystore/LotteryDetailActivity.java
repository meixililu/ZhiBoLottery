package com.zhibo.lotterystore;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.ListView;

import com.google.gson.Gson;
import com.zhibo.lotterystore.adapter.LotteryDetailListAdapter;
import com.zhibo.lotterystore.dao.Result;
import com.zhibo.lotterystore.dao.RootResult;
import com.zhibo.lotterystore.http.LotteryHttpClient;
import com.zhibo.lotterystore.http.RequestParams;
import com.zhibo.lotterystore.http.TextHttpResponseHandler;
import com.zhibo.lotterystore.util.KeyUtil;
import com.zhibo.lotterystore.util.LogUtil;
import com.zhibo.lotterystore.util.Settings;

public class LotteryDetailActivity extends BaseActivity{

	private ListView listview;
	private LayoutInflater mInflater;
	private LotteryDetailListAdapter mAdapter;
	private List<Result> mList;
	private String lotteryCode;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lottery_detail_activity);
		init();
		onCreateShowProgressbar();
		RequestData();
	}
	
	private void init(){
		lotteryCode = getIntent().getStringExtra(KeyUtil.CategoryCodeKey);
		mInflater = LayoutInflater.from(this);
		initSwipeRefresh();
		mList = new ArrayList<Result>();
		listview = (ListView) findViewById(R.id.listview);
		mAdapter = new LotteryDetailListAdapter(this,mInflater,mList);
		listview.setAdapter(mAdapter);
	}
	
	private void RequestData(){
		showProgressbar();
		RequestParams mRequestParams = new RequestParams();
		mRequestParams.put("code", lotteryCode);
		mRequestParams.put("count", "100");
		mRequestParams.put("endTime", String.valueOf(System.currentTimeMillis()));
		mRequestParams.put("showapi_appid", Settings.showapi_appid);
		mRequestParams.put("showapi_timestamp", String.valueOf(System.currentTimeMillis()));
		mRequestParams.put("showapi_sign", Settings.showapi_sign);
		LotteryHttpClient.client.get(Settings.detailUrl, mRequestParams, new TextHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers,String responseString) {
				LogUtil.DefalutLog("");
				if(!TextUtils.isEmpty(responseString)){
					RootResult mRoot = new Gson().fromJson(responseString, RootResult.class);
					if(mRoot != null){
						List<Result> tList = mRoot.getShowapi_res_body().getResult();
						if(tList != null){
							mList.clear();
							mList.addAll(tList);
							mAdapter.notifyDataSetChanged();
						}
					}
				}
			}
			@Override
			public void onFailure(int statusCode, Header[] headers,String responseString, Throwable throwable) {
			}
			@Override
			public void onFinish() {
				hideProgressbar();
				onSwipeRefreshLayoutFinish();
			}
		});
	}
	
	public void onSwipeRefreshLayoutRefresh(){
		RequestData();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
	}

}
