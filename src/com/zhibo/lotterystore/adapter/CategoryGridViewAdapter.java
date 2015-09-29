package com.zhibo.lotterystore.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zhibo.lotterystore.LotteryDetailActivity;
import com.zhibo.lotterystore.R;
import com.zhibo.lotterystore.dao.Result;
import com.zhibo.lotterystore.util.KeyUtil;

public class CategoryGridViewAdapter extends BaseAdapter {

	private Context mContext; 
	private LayoutInflater mInflater;
	private List<Result> mCategoryList;
	
	public CategoryGridViewAdapter(Context mContext,LayoutInflater mInflater,List<Result> mAppInfoList){
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
			convertView = mInflater.inflate(R.layout.category_gridview_adapter_item, null);
			holder = new ViewHolder();
			holder.app_cover = (FrameLayout) convertView.findViewById(R.id.app_cover);
			holder.lottery_name = (TextView) convertView.findViewById(R.id.lottery_name);
			holder.lottery_area = (TextView) convertView.findViewById(R.id.lottery_area);
			holder.lottery_des = (TextView) convertView.findViewById(R.id.lottery_des);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final Result mCategory = mCategoryList.get(position);
		if(!TextUtils.isEmpty(mCategory.getDescr())){
			holder.lottery_name.setText(mCategory.getDescr());
			holder.lottery_des.setText(mCategory.getNotes());
		}
		if(!TextUtils.isEmpty(mCategory.getArea())){
			holder.lottery_area.setText(mCategory.getArea());
		}else{
			holder.lottery_area.setText("");
		}
		holder.app_cover.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startIntent(mCategory.getCode(), mCategory.getDescr());
			}
		});
		return convertView;
	}
	
	private void startIntent(String code, String title){
		Intent intent = new Intent(mContext,LotteryDetailActivity.class);
		intent.putExtra(KeyUtil.CategoryCodeKey, code);
		intent.putExtra(KeyUtil.ActionbarTitle, title);
		mContext.startActivity(intent);
	}

	static class ViewHolder {
		FrameLayout app_cover;
		TextView lottery_name;
		TextView lottery_des;
		TextView lottery_area;
	}
	
}
