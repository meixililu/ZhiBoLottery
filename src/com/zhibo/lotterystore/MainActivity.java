package com.zhibo.lotterystore;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.gc.materialdesign.views.ProgressBarCircularIndeterminate;
import com.zhibo.lotterystore.adapter.MainPageAdapter;
import com.zhibo.lotterystore.listener.FragmentProgressbarListener;
import com.zhibo.lotterystore.util.PackageInfoUtil;
import com.zhibo.lotterystore.view.PagerSlidingTabStrip;

public class MainActivity extends BaseActivity implements FragmentProgressbarListener{

	private ViewPager viewPager;
	private PagerSlidingTabStrip indicator;
	private ProgressBarCircularIndeterminate mProgressbar;
	private MainPageAdapter mAdapter;
	private long exitTime = 0;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		startPushTask();
		init();
	}
	
	private void startPushTask(){
	}
	
	private void init(){
		if (toolbar != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(false);
		}
		viewPager = (ViewPager) findViewById(R.id.pager);
		indicator = (PagerSlidingTabStrip) findViewById(R.id.indicator);
		mProgressbar = (ProgressBarCircularIndeterminate) findViewById(R.id.progressbar);
		mAdapter = new MainPageAdapter(this.getSupportFragmentManager(),this);
		viewPager.setAdapter(mAdapter);
		viewPager.setOffscreenPageLimit(3);
		indicator.setViewPager(viewPager);
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

	public void showProgressbar(){
		mProgressbar.setVisibility(View.VISIBLE);
	}
	
	public void hideProgressbar(){
		mProgressbar.setVisibility(View.GONE);
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
		PackageInfoUtil.mAppInfoList.clear();
		PackageInfoUtil.mAppInfoList = null;
	}
}
