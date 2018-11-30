package com.ycnet.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

@Component
@PropertySources(value={@PropertySource("classpath:com/ycnet/web/config/socket.properties")})
public class SocketDataConfig {

	
	@Value("${tcp.ip}")
	private String tcpIp;
	
	@Value("${tcp.port}")
	private String tcpPort;
	
	@Value("${udp.ip}")
	private String udpIp;
	
	@Value("${udp.port}")
	private String udpPort;

	public String getTcpIp() {
		return tcpIp;
	}

	public void setTcpIp(String tcpIp) {
		this.tcpIp = tcpIp;
	}

	public String getTcpPort() {
		return tcpPort;
	}

	public void setTcpPort(String tcpPort) {
		this.tcpPort = tcpPort;
	}

	public String getUdpIp() {
		return udpIp;
	}

	public void setUdpIp(String udpIp) {
		this.udpIp = udpIp;
	}

	public String getUdpPort() {
		return udpPort;
	}

	public void setUdpPort(String udpPort) {
		this.udpPort = udpPort;
	}
	
}
