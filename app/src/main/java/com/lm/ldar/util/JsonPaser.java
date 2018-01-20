
package com.lm.ldar.util;

import android.app.Activity;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonPaser {
    /**
     * 解析User
     *
     * @param content
     * @return
     * @throws JSONException
     */
    public static User parseUser(String content) throws JSONException {
        User user = new User();
        if (!IsNullOrEmpty.isEmpty(content)) {
            JSONObject jsonObject = new JSONObject(content);
            user.setId(jsonObject.optLong("id"));
            user.setUsername(jsonObject.optString("username"));
            user.setPassword(jsonObject.optString("password"));
            user.setTruename(jsonObject.optString("truename"));
            user.setLastlogintime(jsonObject.optString("lastlogintime"));
            user.setLastloginip(jsonObject.optString("lastloginip"));
            user.setValid(jsonObject.optInt("valid"));
            user.setCreatetime(jsonObject.optString("createtime"));
            user.setEid(jsonObject.optLong("eid"));
            user.setLogintimes(jsonObject.optInt("logintimes"));
            user.setPhone(jsonObject.optString("phone"));
            user.setEmail(jsonObject.optString("email"));
            user.setUsergroups(jsonObject.optString("usergroups"));
        }
        return user;
    }

    /**
     * 解析Enterprise
     *
     * @param content
     * @return
     * @throws JSONException
     */
    public static Enterprise parseEnterprise(String content) throws JSONException {
        Enterprise enterprise = new Enterprise();
        if (!IsNullOrEmpty.isEmpty(content)) {
            JSONObject jsonObject = new JSONObject(content);
            enterprise.setId(jsonObject.optLong("id"));
            enterprise.setEcode(jsonObject.optString("ecode"));
            enterprise.setEname(jsonObject.optString("ename"));
            enterprise.setLegalperson(jsonObject.optString("legalperson"));
            enterprise.setIndustry(jsonObject.optString("industry"));
            enterprise.setCreatetime(jsonObject.optString("createtime"));
            enterprise.setValid(jsonObject.optInt("valid"));
            enterprise.setNid(jsonObject.optInt("nid"));
            enterprise.setPpid(jsonObject.optInt("ppid"));
            enterprise.setEpid(jsonObject.optInt("epid"));
        }
        return enterprise;
    }

    /**
     * 解析Factory
     *
     * @param content
     * @return
     * @throws JSONException
     */
    public static List<Factory> parseFactory(String content) throws JSONException {
        List<Factory> factories = new ArrayList<>();
        if (!IsNullOrEmpty.isEmpty(content)) {
            JSONArray jsonArray = new JSONArray(content);
            if (jsonArray != null && jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.optJSONObject(i);
                    Factory factory = new Factory();
                    factory.setId(jsonObject.optLong("id"));
                    factory.setNumber(jsonObject.optString("number"));
                    factory.setName(jsonObject.optString("name"));
                    factory.setCreatetime(jsonObject.optString("createtime"));
                    factory.setValid(jsonObject.getInt("valid"));
                    factory.setEid(jsonObject.getInt("eid"));
                    factories.add(factory);
                }
            }
        }
        return factories;
    }

    /**
     * 解析Pictureversion
     *
     * @param content
     * @return
     * @throws JSONException
     */
    public static List<Pictureversion> parsePictureversion(String content) throws JSONException {
        List<Pictureversion> pictureversions = new ArrayList<>();
        if (!IsNullOrEmpty.isEmpty(content)) {
            JSONArray jsonArray = new JSONArray(content);
            if (jsonArray != null && jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.optJSONObject(i);
                    Pictureversion pictureversion = new Pictureversion();
                    pictureversion.setId(jsonObject.optLong("id"));
                    pictureversion.setName(jsonObject.getString("name"));
                    pictureversion.setIntroduction(jsonObject.getString("introduction"));
                    pictureversion.setCreatetime(jsonObject.getString("createtime"));
                    pictureversion.setEid(jsonObject.getInt("eid"));
                    pictureversion.setUid(jsonObject.getInt("uid"));
                    pictureversion.setPvid(jsonObject.getInt("pvid"));
                    pictureversion.setType(jsonObject.getInt("type"));
                    pictureversions.add(pictureversion);
                }
            }
        }
        return pictureversions;
    }

    /**
     * 解析Area
     *
     * @param content
     * @return
     * @throws JSONException
     */
    public static List<Area> parseArea(String content) throws JSONException {
        List<Area> areas = new ArrayList<>();
        if (!IsNullOrEmpty.isEmpty(content)) {
            JSONArray jsonArray = new JSONArray(content);
            if (jsonArray != null && jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.optJSONObject(i);
                    Area area = new Area();
                    area.setId(jsonObject.optLong("id"));
                    area.setNumber(jsonObject.getString("number"));
                    area.setName(jsonObject.getString("name"));
                    area.setCreatetime(jsonObject.getString("createtime"));
                    area.setDid(jsonObject.getInt("did"));
                    area.setEid(jsonObject.getInt("eid"));
                    area.setValid(jsonObject.getInt("valid"));
                    areas.add(area);
                }
            }
        }
        return areas;
    }

    /**
     * 解析装置
     * @param content
     * @return
     * @throws JSONException
     */
	public static List<Device> parseDevice(String content) throws JSONException{
        List<Device> devices = new ArrayList<>();
        if (!IsNullOrEmpty.isEmpty(content)) {
            JSONArray jsonArray = new JSONArray(content);
            if (jsonArray != null && jsonArray.length() > 0){
                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.optJSONObject(i);
                    Device device = new Device();
                    device.setId(jsonObject.optLong("id"));
                    device.setNumber(jsonObject.getString("number"));
                    device.setName(jsonObject.getString("name"));
                    device.setCreatetime(jsonObject.getString("createtime"));
                    device.setDid(jsonObject.getInt("did"));
                    device.setEid(jsonObject.getInt("eid"));
                    device.setProductiontime(jsonObject.getString("productiontime"));
                    device.setValid(jsonObject.getInt("valid"));
                    device.setSafeguard(jsonObject.getString("safeguard"));
                    devices.add(device);
                }
            }
        }
        return devices;
    }

    /**
     * 解析namerules
     *
     * @param content
     * @return
     * @throws JSONException
     */
    public static Namerules parseNamerules(String content) throws JSONException {
        Namerules namerules = new Namerules();
        if (!IsNullOrEmpty.isEmpty(content)) {
            JSONObject jsonObject = new JSONObject(content);
            namerules.setId(jsonObject.optLong("id"));
            namerules.setEid(jsonObject.getInt("eid"));
            namerules.setName(jsonObject.getString("name"));
            namerules.setFamen(jsonObject.getString("famen"));
            namerules.setBeng(jsonObject.getString("beng"));
            namerules.setFalan(jsonObject.getString("falan"));
            namerules.setYasuoji(jsonObject.getString("yasuoji"));
            namerules.setJiaoban(jsonObject.getString("jiaoban"));
            namerules.setXieya(jsonObject.getString("xieya"));
            namerules.setLianjie(jsonObject.getString("lianjie"));
            namerules.setKaikou(jsonObject.getString("kaikou"));
            namerules.setCaiyang(jsonObject.getString("caiyang"));
            namerules.setOther(jsonObject.getString("other"));
        }
        return namerules;
    }

    /**
     * 解析ctype
     * @param content
     * @return
     * @throws JSONException
     */
    public static List<Ctype> parseCtype(String content) throws JSONException{
        List<Ctype> ctypes = new ArrayList<>();
        if (!IsNullOrEmpty.isEmpty(content)) {
            JSONArray jsonArray = new JSONArray(content);
            if (!IsNullOrEmpty.isEmpty(content)) {
                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = new JSONObject(content);
                    Ctype ctype = new Ctype();
                    ctype.setId(jsonObject.getString("id"));
                    ctype.setDescription(jsonObject.getString("description"));
                    ctypes.add(ctype);
                }
            }
        }
        return ctypes;
    }

    /**
     * 解析workplan
     *
     * @param content
     * @return
     * @throws JSONException
     */
    public static Workplan parseWorkplan(String content) throws JSONException {
        Workplan workplan = new Workplan();
        if (!IsNullOrEmpty.isEmpty(content)) {
            JSONObject jsonObject = new JSONObject(content);
            workplan.setId(jsonObject.optLong("id"));
            workplan.setName(jsonObject.getString("name"));
            workplan.setEid(jsonObject.getInt("eid"));
            workplan.setState(jsonObject.getInt("state"));
            workplan.setNid(jsonObject.getInt("nid"));
            workplan.setSid(jsonObject.getInt("sid"));
            workplan.setPvid(jsonObject.getInt("pvid"));
        }
        return workplan;
    }

    /**
     * 解析department
     * @param content
     * @return
     * @throws JSONException
     */
    public static List<Department> parseDepartment(String content) throws JSONException{
        List<Department> departments = new ArrayList<>();
        if (!IsNullOrEmpty.isEmpty(content)) {
            JSONArray jsonArray = new JSONArray(content);
            if (!IsNullOrEmpty.isEmpty(content)) {
                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = new JSONObject(content);
                    Department department = new Department();
                    department.setId(jsonObject.optLong("id"));
                    department.setNumber(jsonObject.getString("number"));
                    department.setName(jsonObject.getString("name"));
                    department.setCreatetime(jsonObject.getString("createtime"));
                    department.setFid(jsonObject.getInt("fid"));
                    department.setEid(jsonObject.getInt("eid"));
                    department.setValid(jsonObject.getInt("valid"));
                    departments.add(department);
                }
            }
        }
        return departments;
    }





}
