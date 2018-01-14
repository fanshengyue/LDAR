package com.lm.ldar.util;

import android.app.Activity;

import com.lm.ldar.LMApplication;
import com.lm.ldar.dao.DaoSession;
import com.lm.ldar.dao.EnterpriseDao;
import com.lm.ldar.dao.FactoryDao;
import com.lm.ldar.dao.UserDao;
import com.lm.ldar.entity.Enterprise;
import com.lm.ldar.entity.Factory;
import com.lm.ldar.entity.User;

import java.util.List;

/**
 * Created by fanshengyue on 2018/1/14.
 */

public class DaoUtil {

    /**
     * 根据UserId 删除有userid关联的表数据
     * @param activity
     * @param user_id
     */
    public static void DeleteByUserId(Activity activity, Long user_id){
        DaoSession daoSession= ((LMApplication)activity.getApplication()).getDaoSession();
        //删除数据库
        UserDao userDao=daoSession.getUserDao();
        User queryUser=userDao.queryBuilder().where(UserDao.Properties.Id.eq(user_id)).unique();
        if(queryUser!=null){
            //删除用户
            userDao.delete(queryUser);
            //删除企业信息
            EnterpriseDao enterpriseDao=daoSession.getEnterpriseDao();
            Enterprise ep_queryEntity=enterpriseDao.queryBuilder().where(EnterpriseDao.Properties.Id.eq(queryUser.getEid())).unique();
            if(ep_queryEntity!=null){
                enterpriseDao.delete(ep_queryEntity);
                //删除厂区信息
                FactoryDao factoryDao=daoSession.getFactoryDao();
                List<Factory> factories=factoryDao.queryBuilder().where(FactoryDao.Properties.Eid.eq(ep_queryEntity.getId())).build().list();
            }

        }

    }

    /**
     * 添加User,有则更新，无则插入
     */
    public static void UpdateUser(UserDao userDao,User user){
        if(user!=null){
            User userQueryEntity=userDao.queryBuilder().where(UserDao.Properties.Id.eq(user.getId())).unique();
            if(userQueryEntity!=null){
                userDao.update(user);
            }else {
                userDao.insert(user);
            }
        }
    }

    /**
     * 增加企业Enterprise
     */
    public static void UpdateEnterprise(EnterpriseDao enterpriseDao,Enterprise enterprise){
        if(enterprise!=null){
            Enterprise ep_queryEntity=enterpriseDao.queryBuilder().where(EnterpriseDao.Properties.Id.eq(enterprise.getId())).unique();
            if(ep_queryEntity!=null){
                enterpriseDao.update(enterprise);
            }else{
                enterpriseDao.insert(enterprise);
            }
        }
    }

    /**
     * 增加厂区
     */
    public static void UpdateFactory(FactoryDao factoryDao,Factory factory){
        if(factory!=null){
            Factory query_fac=factoryDao.queryBuilder().where(FactoryDao.Properties.Id.eq(factory.getId())).unique();
            if(query_fac!=null){
                factoryDao.update(factory);
            }else{
                factoryDao.insert(factory);
            }
        }
    }

}
