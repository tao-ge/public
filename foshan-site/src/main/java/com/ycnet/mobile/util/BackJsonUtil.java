package com.ycnet.mobile.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.PropertyFilter;

public class BackJsonUtil {
	
	private static List<String> stayList;
	
	static {
		stayList = new ArrayList<String>();
		stayList.add("code");
		stayList.add("message");
		stayList.add("tranCode");
		stayList.add("dto");
	}

	/**
	 * 
	 * @Title: StayProperties
	 * @Description: 筛选想要的属性.通常都用这个就可以.
	 * @param @param stayList
	 * @param @return 
	 * @return Object 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月25日 下午3:46:18
	 * @version V1.0
	 */
	public static JsonConfig StayProperties(final List<String> stayList1){
		JsonConfig config = new JsonConfig();
		config.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object arg0, String arg1, Object arg2) {
				if(stayList != null && stayList.size()>0) {
					//整合想要留下的属性
					stayList.addAll(stayList1);
					if (!stayList.contains(arg1)) {
						return true;
					} else {
						return false;
					}
				}
				return false;
			}
		});
		config.registerJsonValueProcessor(Date.class, new JsonValueProcessor() {  
            public Object processArrayValue(Object value, JsonConfig jsonConfig) {  
                return value;  
            }  
            public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {  
                if(value instanceof Date){ 
                	return ((Date)value).getTime();
                }  
                return value;  
            }  
        }); 
		return config;
	}
	
	/**
	 * 
	 * @Title: RemoveProperties
	 * @Description: 过滤掉不需要的属性.不经常修改的,比较稳定的bean可以用这个
	 * @param @param removeList
	 * @param @return 
	 * @return Object 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月25日 下午3:48:04
	 * @version V1.0
	 */
	public static JsonConfig RemoveProperties(final List<String> removeList){
		JsonConfig config = new JsonConfig();
		config.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object arg0, String arg1, Object arg2) {
				if(removeList != null && removeList.size()>0) {
					//arg1是key
					if (removeList.contains(arg1)) {
						return true;
					} else {
						return false;
					}
				}
				return false;
			}
		});
		config.registerJsonValueProcessor(Date.class, new JsonValueProcessor() {  
            public Object processArrayValue(Object value, JsonConfig jsonConfig) {  
                return value;  
            }  
            public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {  
                if(value instanceof Date){ 
                	return ((Date)value).getTime();
                }  
                return value;  
            }  
        }); 
		return config;
	}
}
  
    