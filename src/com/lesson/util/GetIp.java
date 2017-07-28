package com.lesson.util;

import javax.servlet.http.HttpServletRequest;

public class GetIp {
	private GetIp(){}
	public static String getip(HttpServletRequest request){
		String ip = request.getHeader("x-forwarded-for");
		  if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		   ip = request.getHeader("Proxy-Client-IP");
		  }
		  if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		   ip = request.getHeader("WL-Proxy-Client-IP");
		  }
		  if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
		   ip = request.getRemoteAddr();
		  }
		  if (ip != null && ip.length() > 0){
		   String[] ips = ip.split(",");
		   for(int i=0;i<ips.length;i++){
		    if(ips[i].trim().length() > 0 && !"unknown".equalsIgnoreCase(ips[i].trim())){
		     ip = ips[i].trim();
		     break;
		    }
		   }
		  }
		  return ip;
	}
}
