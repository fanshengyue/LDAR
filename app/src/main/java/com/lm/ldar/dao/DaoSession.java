package com.lm.ldar.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.lm.ldar.entity.Enterprise;
import com.lm.ldar.entity.User;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig userDaoConfig;
    private final DaoConfig enterpriseDaoConfig;

    private final UserDao userDao;
    private final EnterpriseDao enterpriseDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        enterpriseDaoConfig = daoConfigMap.get(EnterpriseDao.class).clone();
        enterpriseDaoConfig.initIdentityScope(type);

        userDao = new UserDao(userDaoConfig, this);
        enterpriseDao = new EnterpriseDao(enterpriseDaoConfig, this);

        registerDao(User.class, userDao);
        registerDao(Enterprise.class, enterpriseDao);
    }
    
    public void clear() {
        userDaoConfig.clearIdentityScope();
        enterpriseDaoConfig.clearIdentityScope();
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public EnterpriseDao getEnterpriseDao() {
        return enterpriseDao;
    }

}