/**
 * Copyright (c) 2017-2018 The Elastos Developers
 * <p>
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.elastos.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import com.google.gson.Gson;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.elastos.exception.ApiRequestDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author clark
 * 
 * Apr 25, 2018 12:09:11 PM
 */
public class JsonUtil {

	private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

	public static <T> T jsonStr2Entity(String jsonStr,Class<T> clazz){
		if(StrKit.isBlank(jsonStr)){
			throw new ApiRequestDataException("data can not be blank");
		};
		try{
			return new Gson().fromJson(jsonStr, clazz);
		}catch(Exception ex){
			throw new ApiRequestDataException(ex.getMessage());
		}
	}

	public static Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
	    Map<String, Object> retMap = new HashMap<String, Object>();

	    if(json != null) {
	        retMap = toMap(json);
	    }
	    return retMap;
	}

	public static Map<String, Object> toMap(JSONObject object) throws JSONException {
	    Map<String, Object> map = new HashMap<String, Object>();

	    Iterator<String> keysItr = object.keys();
	    while(keysItr.hasNext()) {
	        String key = keysItr.next();
	        Object value = object.get(key);

	        if(value instanceof JSONArray) {
	            value = toList((JSONArray) value);
	        }

	        else if(value instanceof JSONObject) {
	            value = toMap((JSONObject) value);
	        }
	        map.put(key, value);
	    }
	    return map;
	}

	public static List<Object> toList(JSONArray array) throws JSONException {
	    List<Object> list = new ArrayList<Object>();
	    for(int i = 0; i < array.size(); i++) {
	        Object value = array.get(i);
	        if(value instanceof JSONArray) {
	            value = toList((JSONArray) value);
	        }

	        else if(value instanceof JSONObject) {
	            value = toMap((JSONObject) value);
	        }
	        list.add(value);
	    }
	    return list;
	}

	public static String getValues(Object obj){
		return typeOf(obj);
	}

	private static String getMapValues(Map<String,Object> src){
		if(src == null || src.size() == 0){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		Set<String> set = src.keySet();
		Iterator<String> it = set.iterator();
		while(it.hasNext()){
			Object key = it.next();
			sb.append(typeOf(src.get(key)));
		}
		return sb.toString();
	}

	private static String getListValues(List src){

		if(src == null || src.size() == 0){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<(src).size();i++){
			sb.append(typeOf(src.get(i)));
		}

		return sb.toString();
	}

	/**
	 * judge the type of object.
	 * @param o
	 * @return
	 */
	private static String typeOf(Object o){
		if(o instanceof Map) {
			return getMapValues((Map)o);
		}else if(o instanceof List){
			return getListValues((List)o);
		}else{
			return o +"";
		}
	}
}
