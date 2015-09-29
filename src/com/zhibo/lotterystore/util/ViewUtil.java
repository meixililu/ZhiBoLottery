package com.zhibo.lotterystore.util;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhibo.lotterystore.R;
import com.zhibo.lotterystore.view.AutoWrapView;

public class ViewUtil {

	/***蓝球***/
	public static final int COLOR_BLUE = 2;
	/***红球***/
	public static final int COLOR_RED = 1;
	/***方球***/
	public static final int COLOR_RED_RECT = 4;
	
	/**
	 * 根据号码显示View
	 * @param mContext 当前this
	 * @param lotteryId 彩种ID
	 * @param number 号码
	 * @param isSelect 是否选中状态
	 * @return AutoWrapView 用于显示
	 * @return state 用于区分不同的显示方式，比如快3：状态1表示色子图片跟和值，2表示数字跟和值
	 * @throws Exception
	 */
	public static AutoWrapView getAutoView(Context mContext, String number) throws Exception{
		AutoWrapView mWrapView = new AutoWrapView(mContext);
		boolean isHasBlueBall = number.contains("+");
		String[] numArray = number.split("\\+");
		if(isHasBlueBall){
			getCheckBox(mContext, mWrapView, numArray[0], ",", COLOR_RED);
			if(numArray.length > 1){
				getCheckBox(mContext, mWrapView, numArray[1], ",", COLOR_BLUE);
			}
		}else{
			getCheckBox(mContext, mWrapView, numArray[0], ",", COLOR_RED);
		}
		
		return mWrapView;
	}
	
	private static void getCheckBox(Context mContext, AutoWrapView mWrapView, String number, String split, int color) throws Exception{
		String[] temp = number.split(split);
		for(String tempNum: temp){
			if(TextUtils.isEmpty(tempNum)) continue;
			TextView mTv = getBall(mContext, tempNum, color);
			mWrapView.addView(mTv);
		}
	}
	
	
	public static TextView getBall(Context context, String content, int color){
		TextView textview = (TextView) LayoutInflater.from(context).inflate(R.layout.buy_lottery_agent_scheme_ball, null);
		if(color == ViewUtil.COLOR_BLUE){
			textview.setBackgroundResource(R.drawable.ball_blue);
		}else if(color == ViewUtil.COLOR_RED){
			textview.setBackgroundResource(R.drawable.ball_red);
		}else if(color == ViewUtil.COLOR_RED_RECT){
			textview.setBackgroundResource(R.drawable.jc_result_bg);
		}
		textview.setTextColor(context.getResources().getColor(R.color.white));
		textview.setText(content);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		textview.setLayoutParams(params);
		return textview;
	}
}
