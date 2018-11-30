package com.ycnet.core;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
 
/*
 * 客户端
 */
public class UDPClient {
	
	private static final int MAXNUM = 5; // 设置重发数据的最多次数
	private static final int REV_SIZE = 1024; //接收数据的存储空间大小
	
	/**
	 * 向服务端发送数据，并等待返回
	* @Title: sendServer 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param ip
	* @param @param port
	* @param @param data
	* @param @return    入参
	* @return String    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年8月7日 上午9:51:07 
	* @version V1.0
	 */
	public static String sendServer(String ip,Integer port,String data) {
		
		/*
		 * 向服务器端发送数据
		 */
		//1.定义服务器的地址、端口号、数据
		InetAddress address = null;
		DatagramSocket socket = null;
		String result = "";
		int send_count = 0; // 重发数据的次数
		boolean revResponse = false; // 是否接收到数据的标志位
		try {
			address = InetAddress.getByName(ip);
		
			byte[] dataByte=data.getBytes();
			//2.创建数据报，包含发送的数据信息
			DatagramPacket packet=new DatagramPacket(dataByte, dataByte.length, address, port);
			//3.创建DatagramSocket对象
			socket=new DatagramSocket();
			//4.向服务器端发送数据报
			
			/*
			 * 接收服务器端响应的数据
			 */
			//1.创建数据报，用于接收服务器端响应的数据
			byte[] data2=new byte[REV_SIZE];
			DatagramPacket packet2=new DatagramPacket(data2, data2.length);
			
			while (!revResponse && send_count < MAXNUM) {
				try {
					socket.send(packet);
					socket.setSoTimeout(10*1000);

					//2.接收服务器响应的数据
					socket.receive(packet2);
					//3.读取数据
					result=new String(data2, 0, packet2.getLength());
					System.out.println("我是客户端，服务器说："+result);
					revResponse = true;
				}catch (InterruptedIOException e) {
					// 如果接收数据时阻塞超时，重发并减少一次重发的次数
					send_count += 1;
					System.out.println("Time out," + (MAXNUM - send_count)
					                         + " more tries...");
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//4.关闭资源
			socket.close();
		}
		return result;
	}
	
	
	
	
	public static void main(String[] args) throws IOException {
		
		System.err.println(sendServer("localhost",8800,"用户名：admin;密码：123"));
	}
}

