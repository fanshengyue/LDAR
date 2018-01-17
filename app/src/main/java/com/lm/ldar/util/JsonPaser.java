
package com.lm.ldar.util;

import android.app.Activity;
import com.lm.ldar.entity.Enterprise;
import com.lm.ldar.entity.Factory;
import com.lm.ldar.entity.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonPaser {
	/**
	 * 解析User
	 * @param content
	 * @return
	 * @throws JSONException
	 */
	public static User parseUser(String content) throws JSONException {
		User user = new User();
		if(!IsNullOrEmpty.isEmpty(content)){
			JSONObject jsonObject=new JSONObject(content);
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
	 * @param content
	 * @return
	 * @throws JSONException
	 */
	public static Enterprise parseEnterprise(String content) throws JSONException {
		Enterprise enterprise = new Enterprise();
		if(!IsNullOrEmpty.isEmpty(content)){
			JSONObject jsonObject=new JSONObject(content);
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
	 * @param content
	 * @return
	 * @throws JSONException
	 */
	public static List<Factory> parseFactory(String content) throws JSONException{
		List<Factory> factories = new ArrayList<>();
		if(!IsNullOrEmpty.isEmpty(content)){
			JSONArray jsonArray=new JSONArray(content);
			if(jsonArray!=null && jsonArray.length()>0){
				for(int i=0;i<jsonArray.length();i++){
					JSONObject jsonObject=jsonArray.optJSONObject(i);
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

}
