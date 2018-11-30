package com.ycnet.core;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class GetHostIp {

	/**
	 * 
	* @Title: getHostIp 
	* @Description: 获取本机IP 
	* @param @return    
	* @return String
	* @author liucanghai 
	* @throws
	* @date 2018年3月22日 上午10:31:46 
	* @version V1.0
	 */
    public static String getHostIp(){  
        try{  
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();  
            while (allNetInterfaces.hasMoreElements()){  
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();  
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();  
                while (addresses.hasMoreElements()){  
                    InetAddress ip = (InetAddress) addresses.nextElement();  
                    if (ip != null   
                            && ip instanceof Inet4Address  
                            && !ip.isLoopbackAddress() //loopback地址即本机地址，IPv4的loopback范围是127.0.0.0 ~ 127.255.255.255  
                            && ip.getHostAddress().indexOf(":")==-1){  
                        System.out.println("本机的IP = " + ip.getHostAddress());  
                        return ip.getHostAddress();  
                    }   
                }  
            }  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
        return null;  
    }  
    /**
     * 
    * @Title: duan 
    * @Description: 获得端口8080 
    * @param @param request
    * @param @return    
    * @return Integer
    * @author liucanghai 
    * @throws
    * @date 2018年4月23日 上午10:20:35 
    * @version V1.0
     */
    public static Integer getDuan(HttpServletRequest request) {
    	return request.getLocalPort();
    }
    
}
