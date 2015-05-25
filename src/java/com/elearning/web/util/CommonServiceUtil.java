package com.elearning.web.util;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


/**
 * 
 * TODO 业务逻辑公共类
 * 
 * @author guocy
 * @date 2014年8月10日 下午1:04:53
 *
 */
public class CommonServiceUtil {

	
	/**
	 * 是否是邮箱
	 * @param s
	 * @return
	 */
	public static boolean isEmail(String s)
	{
		 boolean flag = false;
	      try{
	       String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	       Pattern regex = Pattern.compile(check);
	       Matcher matcher = regex.matcher(s);
	       flag = matcher.matches();
	      }catch(Exception e){
	       flag = false;
	      }
	       
	      return flag;
	}
	
	/**
	 * 是否是手机号 
	 * @param s
	 * @return
	 */
	public static boolean isMobile(String s)
	{
		boolean flag = false;
		try{
		Pattern p = Pattern.compile("^1\\d{10}$");
		Matcher m = p.matcher(s);
		flag = m.matches();
		}catch(Exception e){
		flag = false;
		}
		return flag;
	}
	
	/**
	 * 根据authkey 算出保存到memcache 中的key 值
	 * @param authkey
	 * @return
	 */
	public static String getMemcacheUserKey(String authkey)
	{
		String sret ="";
		String stra [] = authkey.split("-");
		if(stra !=null && stra.length >=2)
		{
			sret = stra[0] + "-loginkey";
		}
		
		return sret;
	}
	
	
	public static String getMemcacheUserKey(long userid)
	{
		String sret ="";
		sret = userid+ "-loginkey";
		return sret;
	}
	/**
	 * 
	 * @param mp
	 * @param response
	 * @throws Exception
	 */
	public static void SendToClient(Map mp ,HttpServletResponse response) throws Exception 
	{
		if(mp.get("ncode") ==null)
		{
			throw new Exception("无效的数据, 没ncode 字段");
		}
		response.setContentType("text/html; charset=utf-8");
		String s = JSONUtil.BeanToJSONString(mp, null);
		response.getWriter().write(s);
		response.getWriter().flush();
		
	}
	
	public static void beanToJsonWrite(Object obj , HttpServletResponse response){
		if(null != obj){
			Gson gson = new Gson();
			response.setContentType("text/html; charset=utf-8");
			try {
				response.getWriter().write(gson.toJson(obj));
				response.getWriter().flush();
			} catch (IOException e) {
			}
		}
	}
	
	public static void main(String args [] )
	{
		
		System.out.println (isMobile("12999999999"));
		System.out.println (getMemcacheUserKey("2222-chenbaoji"));
		
	}
	
}
