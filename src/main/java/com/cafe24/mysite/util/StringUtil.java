package com.cafe24.mysite.util;

public class StringUtil {
	public static String keywordBuilder(String str) {
		// 공백문자가 여러개 들어올때처리
		return "%"+str.replaceAll(" +", " ").replaceAll(" ", "%")+"%";
	}
}
