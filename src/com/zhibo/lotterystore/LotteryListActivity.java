package com.zhibo.lotterystore;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhibo.lotterystore.adapter.CategoryGridViewAdapter;
import com.zhibo.lotterystore.dao.Result;
import com.zhibo.lotterystore.dao.RootResult;
import com.zhibo.lotterystore.http.LotteryHttpClient;
import com.zhibo.lotterystore.http.RequestParams;
import com.zhibo.lotterystore.http.TextHttpResponseHandler;
import com.zhibo.lotterystore.util.LogUtil;
import com.zhibo.lotterystore.util.Settings;

public class LotteryListActivity extends BaseActivity{

	private ListView gridview;
	private LayoutInflater mInflater;
	private CategoryGridViewAdapter mAdapter;
	private List<Result> mList;
	private long exitTime = 0;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category_fragment);
		init();
		onCreateShowProgressbar();
		RequestData();
	}
	
	private void init(){
		if (toolbar != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(false);
		}
		mInflater = LayoutInflater.from(this);
		initSwipeRefresh();
		mList = new ArrayList<Result>();
		gridview = (ListView) findViewById(R.id.listview);
		mAdapter = new CategoryGridViewAdapter(this,mInflater,mList);
		gridview.setAdapter(mAdapter);
	}
	
	private void RequestData(){
		showProgressbar();
		RequestParams mRequestParams = new RequestParams();
		mRequestParams.put("showapi_appid", Settings.showapi_appid);
		mRequestParams.put("showapi_timestamp", String.valueOf(System.currentTimeMillis()));
		mRequestParams.put("showapi_sign", Settings.showapi_sign);
		LotteryHttpClient.client.get(Settings.baseUrl, mRequestParams, new TextHttpResponseHandler() {
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
	public boolean onCreateOptionsMenu(Menu menu) {
//		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
		return super.onOptionsItemSelected(item);
	}

	
	@Override
	public void onBackPressed() {
    	if ((System.currentTimeMillis() - exitTime) > 2000) {
			Toast.makeText(getApplicationContext(), this.getResources().getString(R.string.exit_program), 0).show();
			exitTime = System.currentTimeMillis();
		} else {
			finish();
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
	}

}
