package com.cloudcomputing.dimayor.utils;

import endpoints.repackaged.com.google.gson.Gson;

public class JsonUtil {

	public static String toJson(Object object) {
		return new Gson().toJson(object);
	}

	public static <T> T jsonToObject(String json, Class<T> objClass) {
		return new Gson().fromJson(json, objClass);
	}
	
}