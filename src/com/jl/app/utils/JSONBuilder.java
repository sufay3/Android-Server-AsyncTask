package com.jl.app.utils;

import org.json.JSONObject;
import org.json.JSONException;

public class JSONBuilder {
	public static JSONObject build(String json){
		try{
			return new JSONObject(json);
		}catch(JSONException e){
			return null;
		}
	}
}
