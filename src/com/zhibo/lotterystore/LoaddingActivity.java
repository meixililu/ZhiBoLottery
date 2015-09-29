package com.zhibo.lotterystore;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LoaddingActivity extends BaseActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		waittingTask();
	}
	
	private void toMainActivity(){
		Intent intent = new Intent(this,LotteryListActivity.class);
		startActivity(intent);
		finish();
	}
	
	private void waittingTask(){
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				toMainActivity();
			}
		}, 1500);
	}
	
}
