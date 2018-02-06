package com.example.network.framework;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;

public interface SuccessfulCallback {
	
	public void success(String str) throws JSONException;
	public void success(InputStream ism, long conentLength) throws IOException;

}
