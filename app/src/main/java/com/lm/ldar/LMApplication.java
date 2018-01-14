package com.lm.ldar;

import android.app.Application;
import android.content.Context;

import com.lm.ldar.dao.DaoMaster;
import com.lm.ldar.dao.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by fanshengyue on 2018/1/12.
 */

public class LMApplication extends Application {
    public static Context applicationContext;
    private static LMApplication instance;
    private DaoSession daoSession =null;
    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext=this;
        instance=this;
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this,"ldar.db",null);
        Database db = devOpenHelper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public static LMApplication getInstance(){
        return instance;
    }

    public DaoSession getDaoSession(){
        return  daoSession;
    }

}
