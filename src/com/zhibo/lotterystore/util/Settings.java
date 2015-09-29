package com.zhibo.lotterystore.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.text.ClipboardManager;
import android.view.KeyEvent;

import com.zhibo.lotterystore.R;

public class Settings {

	public static String baseUrl = "http://route.showapi.com/44-6";
	
	public static String detailUrl = "http://route.showapi.com/44-2";
	
	public static String showapi_appid = "9350";
	
	public static String showapi_sign = "b026b0ea53e6462998d7c6964954c194";
	
	
	public static void contantUs(Context mContext){
		try {
			Intent emailIntent = new Intent(Intent.ACTION_SEND);
			emailIntent.setType("message/rfc822");
			emailIntent.putExtra(Intent.EXTRA_EMAIL,new String[] { "" });
			mContext.startActivity(emailIntent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * copy function
	 */
	public static void copy(Context mContext,String dstString){
		// 得到剪贴板管理器
		ClipboardManager cmb = (ClipboardManager)mContext.getSystemService(Context.CLIPBOARD_SERVICE);
		cmb.setText(dstString);
		ToastUtil.diaplayMesShort(mContext, mContext.getResources().getString(R.string.copy_success));
	}
	
	public static void AdjustStreamVolume(Context mContext, int action){
		AudioManager mAudioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE); 
		if(action == KeyEvent.KEYCODE_VOLUME_UP){
			mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
		}else{
			mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
		}
	}
	
}
