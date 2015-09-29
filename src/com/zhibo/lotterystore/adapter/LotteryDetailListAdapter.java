package com.zhibo.lotterystore.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhibo.lotterystore.R;
import com.zhibo.lotterystore.dao.Result;
import com.zhibo.lotterystore.util.TimeUtil;
import com.zhibo.lotterystore.util.ViewUtil;

public class LotteryDetailListAdapter extends BaseAdapter {

	private Context mContext; 
	private LayoutInflater mInflater;
	private List<Result> mCategoryList;
	
	public LotteryDetailListAdapter(Context mContext,LayoutInflater mInflater,List<Result> mAppInfoList){
		this.mContext = mContext;
		this.mInflater = mInflater;
		this.mCategoryList = mAppInfoList;
	}
	
	
	@Override
	public int getCount() {
		return mCategoryList.size();
	}

	@Override
	public Result getItem(int position) {
		return mCategoryList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.lottery_detail_adapter_item, null);
			holder = new ViewHolder();
			holder.lottery_cover = (FrameLayout) convertView.findViewById(R.id.lottery_cover);
			holder.lottery_result = (LinearLayout) convertView.findViewById(R.id.lottery_result);
			holder.lottery_issue = (TextView) convertView.findViewById(R.id.lottery_issue);
			holder.lottery__time = (TextView) convertView.findViewById(R.id.lottery__time);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final Result mCategory = mCategoryList.get(position);
		holder.lottery_issue.setText(mCategory.getExpect());
		holder.lottery__time.setText( TimeUtil.customFormatDate(mCategory.getTime(),TimeUtil.DateFormat, TimeUtil.MonthMinuteFormat) );
		//显示球
		try {
			holder.lottery_result.removeAllViews();
			holder.lottery_result.addView(ViewUtil.getAutoView(mContext, mCategory.getOpenCode()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return convertView;
	}
	
	static class ViewHolder {
		FrameLayout lottery_cover;
		LinearLayout lottery_result;
		TextView lottery_issue;
		TextView lottery__time;
	}
	
}
