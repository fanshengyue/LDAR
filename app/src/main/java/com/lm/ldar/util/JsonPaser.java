
package com.lm.ldar.util;


import android.app.Activity;

import com.lm.ldar.entity.User;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonPaser {
	public static User parseUser(Activity activity,String content) throws JSONException {
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
			user.setEid(jsonObject.optString("eid"));
			user.setLogintimes(jsonObject.optInt("logintimes"));
			user.setPhone(jsonObject.optString("phone"));
			user.setEmail(jsonObject.optString("email"));
			user.setUsergroups(jsonObject.optString("usergroups"));
		}
		return user;
	}

}
