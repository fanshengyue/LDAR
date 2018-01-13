package com.lm.ldar.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetWorkUtil {

	private ConnectivityManager cm;

	public NetWorkUtil(Context context) {
		cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
	}

	/**
	 * 检测网络是否连接
	 * 
	 * @return
	 */
	public boolean isNetConnected() {
		if (cm != null) {
			NetworkInfo[] infos = cm.getAllNetworkInfo();
			if (infos != null) {
				for (NetworkInfo ni : infos) {
					if (ni.isConnected()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * 检测wifi是否连接
	 * 
	 * @return
	 */
	public boolean isWifiConnected() {
		if (cm != null) {
			NetworkInfo networkInfo = cm.getActiveNetworkInfo();
			if (networkInfo != null
					&& networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 检测手机网络否连接
	 * 
	 * @return
	 */
	public boolean isMobileNetConnected() {
		if (cm != null) {
			NetworkInfo networkInfo = cm.getActiveNetworkInfo();
			if (networkInfo != null
					&& networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
				return true;
			}
		}
		return false;
	}

}
