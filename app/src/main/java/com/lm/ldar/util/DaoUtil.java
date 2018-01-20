package com.lm.ldar.util;

import android.app.Activity;

import com.lm.ldar.LMApplication;
import com.lm.ldar.dao.AreaDao;
import com.lm.ldar.dao.CtypeDao;
import com.lm.ldar.dao.DaoSession;
import com.lm.ldar.dao.DepartmentDao;
import com.lm.ldar.dao.DeviceDao;
import com.lm.ldar.dao.EnterpriseDao;
import com.lm.ldar.dao.FactoryDao;
import com.lm.ldar.dao.NamerulesDao;
import com.lm.ldar.dao.PictureversionDao;
import com.lm.ldar.dao.UserDao;
import com.lm.ldar.dao.WorkplanDao;
import com.lm.ldar.entity.Area;
import com.lm.ldar.entity.Ctype;
import com.lm.ldar.entity.Department;
import com.lm.ldar.entity.Device;
import com.lm.ldar.entity.Enterprise;
import com.lm.ldar.entity.Factory;
import com.lm.ldar.entity.Namerules;
import com.lm.ldar.entity.Pictureversion;
import com.lm.ldar.entity.User;
import com.lm.ldar.entity.Workplan;

import java.util.List;

/**
 * Created by fanshengyue on 2018/1/14.
 */

public class DaoUtil {

    /**
     * 根据UserId 删除有userid关联的表数据
     *
     * @param activity
     * @param user_id
     */
    public static void DeleteByUserId(Activity activity, Long user_id) {
        DaoSession daoSession = ((LMApplication) activity.getApplication()).getDaoSession();
        //删除数据库
        UserDao userDao = daoSession.getUserDao();
        User queryUser = userDao.queryBuilder().where(UserDao.Properties.Id.eq(user_id)).unique();
        if (queryUser != null) {
            //删除用户
            userDao.delete(queryUser);
            //删除企业信息
            EnterpriseDao enterpriseDao = daoSession.getEnterpriseDao();
            Enterprise ep_queryEntity = enterpriseDao.queryBuilder().where(EnterpriseDao.Properties.Id.eq(queryUser.getEid())).unique();
            if (ep_queryEntity != null) {
                enterpriseDao.delete(ep_queryEntity);
                //删除厂区信息
                FactoryDao factoryDao = daoSession.getFactoryDao();
                List<Factory> factories = factoryDao.queryBuilder().where(FactoryDao.Properties.Eid.eq(ep_queryEntity.getId())).build().list();
                if (factories != null && factories.size() > 0) {
                    for (Factory factory : factories) {
                        factoryDao.delete(factory);
                    }
                }
                // 删除图像版本
                PictureversionDao pictureversionDao = daoSession.getPictureversionDao();
                List<Pictureversion> pictureversions = pictureversionDao.queryBuilder().where(PictureversionDao.Properties.Eid.eq(ep_queryEntity.getId())).build().list();
                if(pictureversions!=null&&pictureversions.size()>0){
                    for(Pictureversion pictureversion:pictureversions){
                        pictureversionDao.delete(pictureversion);
                    }
                }
                // 删除子区域
                AreaDao areaDao = daoSession.getAreaDao();
                List<Area> areas = areaDao.queryBuilder().where(AreaDao.Properties.Eid.eq(ep_queryEntity.getId())).build().list();
                if(areas!=null&&areas.size()>0){
                    for (Area area:areas){
                        areaDao.delete(area);
                    }
                }
                // 删除装置
                DeviceDao deviceDao = daoSession.getDeviceDao();
                List<Device> devices = deviceDao.queryBuilder().where(DeviceDao.Properties.Eid.eq(ep_queryEntity.getId())).build().list();
                if(devices!=null&&devices.size()>0){
                    for (Device device:devices){
                        deviceDao.delete(device);
                    }
                }
                // 删除命名规则
                NamerulesDao namerulesDao = daoSession.getNamerulesDao();
                Namerules namerules = namerulesDao.queryBuilder().where(NamerulesDao.Properties.Eid.eq(ep_queryEntity.getId())).build().unique();
                namerulesDao.delete(namerules);
                // 删除工作计划
                WorkplanDao workplanDao = daoSession.getWorkplanDao();
                Workplan workplan = workplanDao.queryBuilder().where(WorkplanDao.Properties.Eid.eq(ep_queryEntity.getId())).build().unique();
                workplanDao.delete(workplan);
                // 删除部门
                DepartmentDao departmentDao = daoSession.getDepartmentDao();
                List<Department> departments = departmentDao.queryBuilder().where(DepartmentDao.Properties.Eid.eq(ep_queryEntity.getId())).build().list();
                if (departments!=null&&departments.size()>0){
                    for (Department department:departments){
                        departmentDao.delete(department);
                    }
                }

            }

        }

    }

    /**
     * 添加User,有则更新，无则插入
     */
    public static void UpdateUser(UserDao userDao, User user) {
        if (user != null) {
            User userQueryEntity = userDao.queryBuilder().where(UserDao.Properties.Id.eq(user.getId())).unique();
            if (userQueryEntity != null) {
                userDao.update(user);
            } else {
                userDao.insert(user);
            }
        }
    }

    /**
     * 增加企业Enterprise
     */
    public static void UpdateEnterprise(EnterpriseDao enterpriseDao, Enterprise enterprise) {
        if (enterprise != null) {
            Enterprise ep_queryEntity = enterpriseDao.queryBuilder().where(EnterpriseDao.Properties.Id.eq(enterprise.getId())).unique();
            if (ep_queryEntity != null) {
                enterpriseDao.update(enterprise);
            } else {
                enterpriseDao.insert(enterprise);
            }
        }
    }

    /**
     * 增加厂区
     */
    public static void UpdateFactory(FactoryDao factoryDao, Factory factory) {
        if (factory != null) {
            Factory query_fac = factoryDao.queryBuilder().where(FactoryDao.Properties.Id.eq(factory.getId())).unique();
            if (query_fac != null) {
                factoryDao.update(factory);
            } else {
                factoryDao.insert(factory);
            }
        }
    }

    /**
     * 增加子区域
     *
     * @param areaDao
     * @param area
     */
    public static void updateArea(AreaDao areaDao, Area area) {
        if (area != null) {
            Area query_area = areaDao.queryBuilder().where(AreaDao.Properties.Id.eq(area.getId())).unique();
            if (query_area != null) {
                areaDao.update(area);
            } else {
                areaDao.insert(area);
            }
        }
    }

    /**
     * 增加装置
     *
     * @param deviceDao
     * @param device
     */
    public static void updateDevice(DeviceDao deviceDao, Device device) {
        if (device != null) {
            Device query_dev = deviceDao.queryBuilder().where(DeviceDao.Properties.Id.eq(device.getId())).unique();
            if (query_dev != null) {
                deviceDao.update(device);
            } else {
                deviceDao.insert(device);
            }
        }
    }

    /**
     * 增加命名规则
     *
     * @param namerulesDao
     * @param namerules
     */
    public static void updateNamerules(NamerulesDao namerulesDao, Namerules namerules) {
        if (namerules != null) {
            Namerules query_nr = namerulesDao.queryBuilder().where(NamerulesDao.Properties.Id.eq(namerules.getId())).unique();
            if (query_nr != null) {
                namerulesDao.update(namerules);
            } else {
                namerulesDao.insert(namerules);
            }
        }
    }

    /**
     * 增加组件类型
     *
     * @param ctypeDao
     * @param ctype
     */
    public static void updateCtype(CtypeDao ctypeDao, Ctype ctype) {
        if (ctype != null) {
            Ctype query_ctype = ctypeDao.queryBuilder().where(CtypeDao.Properties.Id.eq(ctype.getId())).unique();
            if (query_ctype != null) {
                ctypeDao.update(ctype);
            } else {
                ctypeDao.insert(ctype);
            }
        }
    }

    /**
     * 增加工作计划
     *
     * @param workplanDao
     * @param workplan
     */
    public static void updateWorkplan(WorkplanDao workplanDao, Workplan workplan) {
        if (workplan != null) {
            Workplan query_wp = workplanDao.queryBuilder().where(WorkplanDao.Properties.Id.eq(workplan.getId())).unique();
            if (query_wp != null) {
                workplanDao.update(workplan);
            } else {
                workplanDao.insert(workplan);
            }
        }
    }

    /**
     * 增加部门
     * @param departmentDao
     * @param department
     */
    public static void updateDepartment(DepartmentDao departmentDao, Department department){
        if(department !=null){
            Department query_dep = departmentDao.queryBuilder().where(DepartmentDao.Properties.Id.eq(department.getId())).unique();
            if(query_dep!=null){
                departmentDao.update(department);
            }else{
                departmentDao.insert(department);
            }
        }
    }

    /**
     * 增加图片版本
     * @param pictureversionDao
     * @param pictureversion
     */
    public static void updatePictureversion(PictureversionDao pictureversionDao, Pictureversion pictureversion){
        if(pictureversion !=null){
            Pictureversion query_picv = pictureversionDao.queryBuilder().where(PictureversionDao.Properties.Id.eq(pictureversion.getId())).unique();
            if(query_picv!=null){
                pictureversionDao.update(pictureversion);
            }else{
                pictureversionDao.insert(pictureversion);
            }
        }
    }



}
