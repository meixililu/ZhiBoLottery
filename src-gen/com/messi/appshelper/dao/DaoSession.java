package com.zhibo.lotterystore.dao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.zhibo.lotterystore.dao.AppInfo;
import com.zhibo.lotterystore.dao.Category;

import com.zhibo.lotterystore.dao.AppInfoDao;
import com.zhibo.lotterystore.dao.CategoryDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig appInfoDaoConfig;
    private final DaoConfig categoryDaoConfig;

    private final AppInfoDao appInfoDao;
    private final CategoryDao categoryDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        appInfoDaoConfig = daoConfigMap.get(AppInfoDao.class).clone();
        appInfoDaoConfig.initIdentityScope(type);

        categoryDaoConfig = daoConfigMap.get(CategoryDao.class).clone();
        categoryDaoConfig.initIdentityScope(type);

        appInfoDao = new AppInfoDao(appInfoDaoConfig, this);
        categoryDao = new CategoryDao(categoryDaoConfig, this);

        registerDao(AppInfo.class, appInfoDao);
        registerDao(Category.class, categoryDao);
    }
    
    public void clear() {
        appInfoDaoConfig.getIdentityScope().clear();
        categoryDaoConfig.getIdentityScope().clear();
    }

    public AppInfoDao getAppInfoDao() {
        return appInfoDao;
    }

    public CategoryDao getCategoryDao() {
        return categoryDao;
    }

}
