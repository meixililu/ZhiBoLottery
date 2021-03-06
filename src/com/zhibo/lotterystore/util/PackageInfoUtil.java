package com.zhibo.lotterystore.util;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;

import com.zhibo.lotterystore.dao.AppInfo;
import com.zhibo.lotterystore.dao.Category;
import com.zhibo.lotterystore.db.DataBaseUtil;

public class PackageInfoUtil {

	public static List<AppInfo> mAppInfoList;
	
	public static void getPackageInfoList(Context mContext){
		List<PackageInfo> packages = mContext.getPackageManager().getInstalledPackages(0);
		mAppInfoList = new ArrayList<AppInfo>();
		List<AppInfo> mList = new ArrayList<AppInfo>();
		for(int i=0;i<packages.size();i++) { 
			PackageInfo packageInfo = packages.get(i); 
			AppInfo tmpInfo = DataBaseUtil.getInstance().isExit(packageInfo.packageName);
			if(tmpInfo == null){
				if (null != mContext.getPackageManager().getLaunchIntentForPackage(packageInfo.packageName)) {
					tmpInfo =new AppInfo(); 
					tmpInfo.setAppName( packageInfo.applicationInfo.loadLabel(mContext.getPackageManager()).toString() ); 
					tmpInfo.setPackageName( packageInfo.packageName ); 
					tmpInfo.setVersionName( packageInfo.versionName ); 
					tmpInfo.setVersionCode( packageInfo.versionCode ); 
					mList.add(tmpInfo);
				}else{
					continue;
				}
			}
			tmpInfo.setAppIcon( packageInfo.applicationInfo.loadIcon(mContext.getPackageManager()) );
			mAppInfoList.add(tmpInfo);
			LogUtil.DefalutLog("getPackageInfoList:"+tmpInfo.toString());
		}
		saveAppInfoList(mList);
	}
	
	public static void getPackageInfoListBackground(final Context mContext){
		new Thread(new Runnable() {
			@Override
			public void run() {
				getPackageInfoList(mContext);
			}
		}).start();
	}
	
	public static void saveAppInfoList(final List<AppInfo> mAppInfoList){
		new Thread(new Runnable() {
			@Override
			public void run() {
				DataBaseUtil.getInstance().insertAppInfoList(mAppInfoList);
			}
		}).start();
	}
	
	public static List<AppInfo> getAppInfoByCategory(String category){
		List<AppInfo> categoryList = new ArrayList<AppInfo>();
		if(mAppInfoList != null){
			for(AppInfo mAppInfo : mAppInfoList){
				if(!TextUtils.isEmpty(mAppInfo.getDefaultType())){
					if(mAppInfo.getDefaultType().equals(category)){
						categoryList.add(mAppInfo);
					}
				}
			}
		}
		return categoryList;
	}
	
	public static void initCategoryData(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				long size = DataBaseUtil.getInstance().getCategoryCount();
				if(size < 1){
					ArrayList<Category> CategoryList = getDefaultCategory();
					DataBaseUtil.getInstance().insertCategoryList(CategoryList);
				}
				
			}
		}).start();
	}
	
	public static ArrayList<Category> getDefaultCategory(){
		ArrayList<Category> CategoryList = new ArrayList<Category>();
		CategoryList.add(new Category("游戏","1001","0"));
		CategoryList.add(new Category("社交","1002","0"));
		CategoryList.add(new Category("购物","1004","0"));
		CategoryList.add(new Category("理财","1005","0"));
		CategoryList.add(new Category("学习","1006","0"));
		CategoryList.add(new Category("电影","1007","0"));
		CategoryList.add(new Category("音乐","1008","0"));
		CategoryList.add(new Category("新闻","1009","0"));
		CategoryList.add(new Category("出行","1010","0"));
		CategoryList.add(new Category("娱乐","1011","0"));
		CategoryList.add(new Category("生活","1012","0"));
		CategoryList.add(new Category("工具","1003","0"));
		CategoryList.add(new Category("系统","1013","0"));
		CategoryList.add(new Category("其他","1014","0"));
		return CategoryList;
	}
}
