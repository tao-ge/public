package com.ycnet.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{

	@Override
	public void registerStompEndpoints(
			StompEndpointRegistry registry) {
		//registry.addEndpoint("/add");
		registry.addEndpoint("/socktendpoint").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		//super.configureMessageBroker(registry);
		registry.enableSimpleBroker("/topic","/queue");
		//registry.enableStompBrokerRelay("/topic","/queue");
		registry.setApplicationDestinationPrefixes("/app");
	}
	
	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		//registration.taskExecutor().corePoolSize(4).maxPoolSize(8);
	}
	
	@Override
	public void configureClientOutboundChannel(ChannelRegistration registration) {
		//registration.taskExecutor().corePoolSize(4).maxPoolSize(8);
	}
}
