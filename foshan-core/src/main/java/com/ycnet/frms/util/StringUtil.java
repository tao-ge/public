package com.ycnet.frms.util;

import java.util.ArrayList;
import java.util.List;

public class StringUtil { 

	public static Integer qurByChar(char chars , String param) {
		int num = 0;
		char[] charArray = param.toCharArray();
		for(char c : charArray) {
			if(c == chars) {
				++num;
			}
		}
		return num;
	}

	public static void main(String[] args) {
		String a = "1-2-3-1" ;
		System.out.println(qurByChar('-',a));
	}
	public static String sort(List<Long> list) {
		ArrayList<String> arrayList = new ArrayList<String>();
		String r="";
		if(list.size()<1) {
			return null;
		} 
		arrayList.add(list.get(0)+"");
		
		if(list.size()<2) {
			return list.get(0)+"";
		}
		for (int i = 1; i < list.size(); i++) {
			if(list.get(i)-list.get(i-1)==1) {
				
				if(arrayList.get(arrayList.size()-1).equals("-")) {
					
					if(i==list.size()-1) {
						if(arrayList.get(arrayList.size()-1).equals("-")) {
							arrayList.add(list.get(i)+"");
						}
					}
				}else {
					arrayList.add("-");
					if(i==list.size()-1) {
						arrayList.add(""+list.get(i));
					}
				}
				
			}else {
				if(arrayList.get(arrayList.size()-1).equals("-")) {
					arrayList.add(list.get(i-1)+"");
				}
				arrayList.add(","+list.get(i));
			}
			
		}
		for (String string : arrayList) {
			r+=string;
		}
		return r;
	}
}   
