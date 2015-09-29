package com.zhibo.lotterystore;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Application;
import android.content.Context;

import com.zhibo.lotterystore.dao.AppInfo;
import com.zhibo.lotterystore.dao.DaoMaster;
import com.zhibo.lotterystore.dao.DaoMaster.OpenHelper;
import com.zhibo.lotterystore.dao.DaoSession;
import com.zhibo.lotterystore.db.DBContract;
import com.zhibo.lotterystore.util.PackageInfoUtil;

public class BaseApplication extends Application {

	public static HashMap<String, Object> dataMap = new HashMap<String, Object>();
	private static DaoMaster daoMaster;  
    private static DaoSession daoSession; 
    public static BaseApplication mInstance;
    
    @Override  
    public void onCreate() {  
        super.onCreate();  
        mInstance = this; 
        PackageInfoUtil.initCategoryData();
    }  
    
	/** 
     * 取得DaoMaster 
     * @param context 
     * @return 
     */  
    public static DaoMaster getDaoMaster(Context context) {  
        if (daoMaster == null) {  
            OpenHelper helper = new DaoMaster.DevOpenHelper(context,DBContract.DATABASE_NAME, null);  
            daoMaster = new DaoMaster(helper.getWritableDatabase());  
        }  
        return daoMaster;  
    }  
      
    /** 
     * 取得DaoSession 
     * @param context 
     * @return 
     */  
    public static DaoSession getDaoSession(Context context) {  
        if (daoSession == null) {  
            if (daoMaster == null) {  
                daoMaster = getDaoMaster(context);  
            }  
            daoSession = daoMaster.newSession();  
        }  
        return daoSession;  
    }  

}
