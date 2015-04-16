package com.kael.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
	public static enum RndType {
		ONLY_LOWER,
		ONLY_UPPER,
		LETTERS,
		ONLY_NUMBERS,
		COMPLEX_NUM_LETTER;
	}

	static String rand(int len, RndType rt) {
		char[] cs = new char[len];
		for (int i = 0; i < len; i++) {
			switch (rt) {
			case ONLY_LOWER:
				cs[i] = (char) RandUtil.rand('a', 'z' + 1);
				break;
			case ONLY_UPPER:
				cs[i] = (char) RandUtil.rand('A', 'Z' + 1);
				break;
			case ONLY_NUMBERS:
				cs[i] = (char) RandUtil.rand('0', '9' + 1);
			default:
				break;
			}
		}
		return new String(cs);
	}

	/**
	 * 
	 * @param phoneNum 传入的参数仅仅是一个电话号码时，调用此方法
	 * @return 如果匹配正确，return true , else return else
	 */
	//如果传进来的是电话号码，则对电话号码进行正则匹配
	public static boolean regexPhoneNumber(String phoneNum){
		//电话号码匹配结果
		return phoneNum != null && phoneNum.matches("1[358]\\d{9}");
	}
	
	/**
	 * 
	 * @param email 传入的参数仅仅是一个邮箱地址时，调用此方法
	 * @return  如果匹配正确，return true , else return false
	 */
	//如果传进来的是邮箱地址，则对邮箱进行正则匹配
	public static boolean regexEmailAddress(String email){
		
		//邮箱匹配结果
		return email != null && email.matches("[a-zA-Z_0-9]+@[a-zA-Z0-9]+(\\.[a-zA-Z]{2,}){1,3}");
	}
	
	/**
	 * 
	 * @param phoneNum  传入的电话号码
	 * @param email     传入的邮箱地址
	 * @return   如果匹配正确，return true , else return false
	 */
	public static boolean regexEmailAddressAndPhoneNum(String phoneNum , String email){
		return regexPhoneNumber(phoneNum) && regexEmailAddress(email);
	}
	
	/**
	 * 
	 * @param qqNum 传入的QQ
	 * @return  如果匹配正确，return true， else  return false
	 */
	public static boolean regexQQNumber(String qqNum){
		
		//QQ号匹配结果
		return qqNum != null && qqNum.matches("[1-9]\\d{2,11}");
	}
	
	/**
	 * 
	 * @param pwd 传入的是 密码
	 * @return 如果匹配正确，满足密码规则，return true， else return false
	 */
	public static boolean regexPassWord(String pwd){
		
		//密码匹配结果
		return pwd != null && pwd.matches("[0-9a-zA-Z_@$@]{6,12}");
		
	}
	
	public static final boolean macthDomain(String url,String domain){
		Matcher m = Pattern.compile("^http://[^/]+").matcher(url);
		if(!m.find()){
			return false;
		}
		return m.group().contains(domain);
	}
	
	/**
	 * 从一个URL地址抽取域名
	 * @return
	 */
	public static final String extractDomain(String url){
		Matcher m = Pattern.compile("^http://[^/]+").matcher(url);
		if(m.find()){
			return m.group();
		}
		return null;
	}
}
