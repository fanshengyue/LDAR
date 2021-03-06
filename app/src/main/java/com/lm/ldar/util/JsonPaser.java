
package com.lm.ldar.util;

import android.app.Activity;

import com.lm.ldar.dao.PictureDao;
import com.lm.ldar.entity.Area;
import com.lm.ldar.entity.CheckInfo;
import com.lm.ldar.entity.Ctype;
import com.lm.ldar.entity.Department;
import com.lm.ldar.entity.Device;
import com.lm.ldar.entity.Element;
import com.lm.ldar.entity.Enterprise;
import com.lm.ldar.entity.Factory;
import com.lm.ldar.entity.Instrument;
import com.lm.ldar.entity.Namerules;
import com.lm.ldar.entity.PictureDownload;
import com.lm.ldar.entity.Pictureversion;
import com.lm.ldar.entity.Repair;
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
                    factory.setValid(jsonObject.optInt("valid"));
                    factory.setEid(jsonObject.optInt("eid"));
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
                    pictureversion.setName(jsonObject.optString("name"));
                    pictureversion.setIntroduction(jsonObject.optString("introduction"));
                    pictureversion.setCreatetime(jsonObject.optString("createtime"));
                    pictureversion.setEid(jsonObject.optInt("eid"));
                    pictureversion.setUid(jsonObject.optInt("uid"));
                    pictureversion.setPvid(jsonObject.optInt("pvid"));
                    pictureversion.setType(jsonObject.optInt("type"));
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
                    area.setNumber(jsonObject.optString("number"));
                    area.setName(jsonObject.optString("name"));
                    area.setCreatetime(jsonObject.optString("createtime"));
                    area.setDid(jsonObject.optInt("did"));
                    area.setEid(jsonObject.optInt("eid"));
                    area.setValid(jsonObject.optInt("valid"));
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
                    device.setNumber(jsonObject.optString("number"));
                    device.setName(jsonObject.optString("name"));
                    device.setCreatetime(jsonObject.optString("createtime"));
                    device.setDid(jsonObject.optInt("did"));
                    device.setEid(jsonObject.optInt("eid"));
                    device.setProductiontime(jsonObject.optString("productiontime"));
                    device.setValid(jsonObject.optInt("valid"));
                    device.setSafeguard(jsonObject.optString("safeguard"));
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
            namerules.setEid(jsonObject.optInt("eid"));
            namerules.setName(jsonObject.optString("name"));
            namerules.setFamen(jsonObject.optString("famen"));
            namerules.setBeng(jsonObject.optString("beng"));
            namerules.setFalan(jsonObject.optString("falan"));
            namerules.setYasuoji(jsonObject.optString("yasuoji"));
            namerules.setJiaoban(jsonObject.optString("jiaoban"));
            namerules.setXieya(jsonObject.optString("xieya"));
            namerules.setLianjie(jsonObject.optString("lianjie"));
            namerules.setKaikou(jsonObject.optString("kaikou"));
            namerules.setCaiyang(jsonObject.optString("caiyang"));
            namerules.setOther(jsonObject.optString("other"));
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
            if (jsonArray != null && jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.optJSONObject(i);
                    Ctype ctype = new Ctype();
                    ctype.setId(jsonObject.optString("id"));
                    ctype.setDescription(jsonObject.optString("description"));
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
            workplan.setName(jsonObject.optString("name"));
            workplan.setEid(jsonObject.optInt("eid"));
            workplan.setState(jsonObject.optInt("state"));
            workplan.setNid(jsonObject.optInt("nid"));
            workplan.setSid(jsonObject.optInt("sid"));
            workplan.setPvid(jsonObject.optInt("pvid"));
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
            if (jsonArray != null && jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.optJSONObject(i);
                    Department department = new Department();
                    department.setId(jsonObject.optLong("id"));
                    department.setNumber(jsonObject.optString("number"));
                    department.setName(jsonObject.optString("name"));
                    department.setCreatetime(jsonObject.optString("createtime"));
                    department.setFid(jsonObject.optInt("fid"));
                    department.setEid(jsonObject.optInt("eid"));
                    department.setValid(jsonObject.optInt("valid"));
                    departments.add(department);
                }
            }
        }
        return departments;
    }


    /**
     * 解析下载图片数据
     */
    public static List<PictureDownload> getDownloadImgData(String content,int ischeck) throws JSONException {
        List<PictureDownload>pictureList=new ArrayList<>();
        if(!IsNullOrEmpty.isEmpty(content)){
            JSONArray jsonArray=new JSONArray(content);
            if(jsonArray != null && jsonArray.length() > 0){
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.optJSONObject(i);
                    PictureDownload picture=new PictureDownload();
                    picture.setId(jsonObject.optLong("id"));
                    picture.setNumber(jsonObject.optString("number"));
                    picture.setName(jsonObject.optString("name"));
                    picture.setStatus(jsonObject.optString("status"));
                    picture.setCreatetime(jsonObject.optString("createtime"));
                    picture.setDeviceinfo(jsonObject.optString("deviceinfo"));
                    picture.setMaterial(jsonObject.optString("material"));
                    picture.setPosition(jsonObject.optString("position"));
                    picture.setDid(jsonObject.optInt("did"));
                    picture.setAid(jsonObject.optInt("aid"));
                    picture.setEid(jsonObject.optInt("eid"));
                    picture.setElementname(jsonObject.optString("elementname"));
                    picture.setPidnumber(jsonObject.optString("pidnumber"));
                    picture.setPvid(jsonObject.optInt("pvid"));
                    picture.setSketch(jsonObject.optString("sketch"));
                    picture.setLatitude(jsonObject.optDouble("lat"));
                    picture.setLongitude(jsonObject.optDouble("lon"));
                    picture.setIscheck(ischeck);
                    picture.setXid(jsonObject.optLong("id"));
                    pictureList.add(picture);
                }
            }
        }
        return pictureList;

    }

    /**
     * 解析Instrument
     *
     * @param content
     * @return
     * @throws JSONException
     */
    public static List<Instrument> parseInstrument(String content) throws JSONException {
        List<Instrument> instruments = new ArrayList<>();
        if (!IsNullOrEmpty.isEmpty(content)) {
            JSONArray jsonArray = new JSONArray(content);
            if (jsonArray != null && jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.optJSONObject(i);
                    Instrument instrument = new Instrument();
                    instrument.setId(jsonObject.optLong("id"));
                    instrument.setInsname(jsonObject.optString("insname"));
                    instrument.setEid(jsonObject.optLong("eid"));
                    instruments.add(instrument);
                }
            }
        }
        return instruments;
    }

    /**
     * 解析Repair
     */
    private double leakagerate;//泄漏量ppm
    private double repairleakagerate;//修复后泄漏量ppm
    private Long pvid;//图像版本ID
    private String firstcheckpeople;//第一次复测人员
    private String seccheckpeople;//第二次复测人员
    public static List<Repair> getRepair(String content) throws JSONException {
        List<Repair>repairList=new ArrayList<>();
        if(!IsNullOrEmpty.isEmpty(content)){
            JSONArray jsonArray=new JSONArray(content);
            if(jsonArray != null && jsonArray.length() > 0){
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.optJSONObject(i);
                    Repair repair=new Repair();
                    repair.setId(jsonObject.optLong("id"));
                    repair.setTag(jsonObject.optString("tag"));
                    repair.setPbvalue(jsonObject.optString("pbvalue"));
                    repair.setFirstname(jsonObject.optString("firstname"));
                    repair.setFirsttime(jsonObject.optString("firsttime"));
                    repair.setFirstmethod(jsonObject.optString("firstmethod"));
                    repair.setFirstreplay(jsonObject.optString("firstreplay"));
                    repair.setFirstdropnumber(jsonObject.optString("firstdropnumber"));
                    repair.setSecname(jsonObject.optString("secname"));
                    repair.setSectime(jsonObject.optString("sectime"));
                    repair.setSecmethod(jsonObject.optString("secmethod"));
                    repair.setSecreplay(jsonObject.optString("secreplay"));
                    repair.setSecdropnumber(jsonObject.optString("secdropnumber"));
                    repair.setFcvalue(jsonObject.optString("fcvalue"));
                    repair.setDefine(jsonObject.optString("define"));
                    repair.setIssuccess(jsonObject.optInt("issuccess"));
                    repair.setDelayreason(jsonObject.optString("delayreason"));
                    repair.setIsdelay(jsonObject.optInt("isdelay"));
                    repair.setUid(jsonObject.optLong("uid"));
                    repair.setChecktime(jsonObject.optString("checktime"));
                    repair.setDid(jsonObject.optLong("did"));
                    repair.setAid(jsonObject.optLong("aid"));
                    repair.setTid(jsonObject.optLong("tid"));
                    repair.setPid(jsonObject.optLong("pid"));
                    repair.setWid(jsonObject.optLong("wid"));
                    repair.setEleid(jsonObject.optLong("eleid"));
                    repair.setLeakagerate(jsonObject.optDouble("leakagerate"));
                    repair.setRepairleakagerate(jsonObject.optDouble("repairleakagerate"));
                    repair.setPvid(jsonObject.optLong("pvid"));
                    repair.setFirstcheckpeople(jsonObject.optString("firstcheckpeople"));
                    repair.setSeccheckpeople(jsonObject.optString("seccheckpeople"));
                    repairList.add(repair);
                }
            }
        }
        return repairList;

    }

    /**
     * 解析CheckInfo
     */
    public static List<CheckInfo> getCheckInfoData(String content) throws JSONException {
        List<CheckInfo>checkInfoList=new ArrayList<>();
        if(!IsNullOrEmpty.isEmpty(content)){
            JSONArray jsonArray=new JSONArray(content);
            if(jsonArray != null && jsonArray.length() > 0){
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.optJSONObject(i);
                    CheckInfo checkInfo=new CheckInfo();
                    checkInfo.setId(jsonObject.optLong("id"));
                    checkInfo.setDate(jsonObject.optString("date"));
                    checkInfo.setTime(jsonObject.optString("time"));
                    checkInfo.setMedium(jsonObject.optString("medium"));
                    checkInfo.setType(jsonObject.optString("type"));
                    checkInfo.setTag(jsonObject.optString("tag"));
                    checkInfo.setPbvalue(jsonObject.optString("pbvalue"));
                    checkInfo.setPbunit(jsonObject.optString("pbunit"));
                    checkInfo.setPbstate(jsonObject.optString("pbstate"));
                    checkInfo.setPcvalue(jsonObject.optString("pcvalue"));
                    checkInfo.setPcunit(jsonObject.optString("pcunit"));
                    checkInfo.setPcstate(jsonObject.optString("pcstate"));
                    checkInfo.setFbvalue(jsonObject.optString("fbvalue"));
                    checkInfo.setFbunit(jsonObject.optString("fbunit"));
                    checkInfo.setFbstate(jsonObject.optString("fbstate"));
                    checkInfo.setFcvalue(jsonObject.optString("fcvalue"));
                    checkInfo.setFcunit(jsonObject.optString("fcunit"));
                    checkInfo.setFcstate(jsonObject.optString("fcstate"));
                    checkInfo.setDefine(jsonObject.optString("define"));
                    checkInfo.setUid(jsonObject.optLong("uid"));
                    checkInfo.setInsid(jsonObject.optLong("insid"));
                    checkInfo.setDid(jsonObject.optLong("did"));
                    checkInfo.setAid(jsonObject.optLong("aid"));
                    checkInfo.setTid(jsonObject.optLong("tid"));
                    checkInfo.setWid(jsonObject.optLong("wid"));
                    checkInfo.setEleid(jsonObject.optLong("eleid"));
                    checkInfo.setDays(jsonObject.optDouble("days"));
                    checkInfo.setHorus(jsonObject.optDouble("horus"));
                    checkInfo.setIsleak(jsonObject.optInt("isleak"));
                    checkInfo.setLeakagerate(jsonObject.optDouble("leakagerate"));
                    checkInfo.setLeakageratepjxs(jsonObject.optDouble("leakageratepjxs"));
                    checkInfo.setDet(jsonObject.optString("det"));
                    checkInfo.setLeak(jsonObject.optString("leak"));
                    checkInfo.setLeaksource(jsonObject.optString("leaksource"));
                    checkInfo.setRepairmethod(jsonObject.optString("repairmethod"));
                    checkInfo.setOriginalfcvalue(jsonObject.optString("originalfcvalue"));
                    checkInfo.setPvid(jsonObject.optLong("pvid"));
                    checkInfo.setCheckpeople(jsonObject.optString("checkpeople"));
                    checkInfoList.add(checkInfo);
                }
            }
        }
        return checkInfoList;

    }

    /**
     * 解析CheckInfo
     */
    public static List<Element> getElementData(String content) throws JSONException {
        List<Element>elementList=new ArrayList<>();
        if(!IsNullOrEmpty.isEmpty(content)){
            JSONArray jsonArray=new JSONArray(content);
            if(jsonArray != null && jsonArray.length() > 0){
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.optJSONObject(i);
                    Element element=new Element();
                    element.setId(jsonObject.optLong("id"));
                    element.setType(jsonObject.optString("type"));
                    element.setDatetime(jsonObject.optString("datetime"));
                    element.setDid(jsonObject.optLong("did"));
                    element.setAid(jsonObject.optLong("aid"));
                    element.setEid(jsonObject.optLong("eid"));
                    element.setPid(jsonObject.optLong("pid"));
                    element.setText(jsonObject.optString("text"));
                    element.setMethod(jsonObject.optString("method"));
                    element.setNumber(jsonObject.optString("number"));
                    element.setUpdatetime(jsonObject.optString("updatetie"));
                    element.setIsreach(jsonObject.optString("isreach"));
                    element.setPidnumber(jsonObject.optString("pidnumber"));
                    element.setPvid(jsonObject.optLong("pvid"));
                    elementList.add(element);
                }
            }
        }
        return elementList;

    }


}
