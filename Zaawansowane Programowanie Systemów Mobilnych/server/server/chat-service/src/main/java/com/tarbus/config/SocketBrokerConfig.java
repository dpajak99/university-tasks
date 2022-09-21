package com.tarbus.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.DefaultContentTypeResolver;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

import java.util.List;

@Configuration
@EnableWebSocketMessageBroker

public class SocketBrokerConfig implements WebSocketMessageBrokerConfigurer {

  //  public static final String ENDPOINT_CONNECT = "/connect";
  public static final String ENDPOINT_CONNECT = "/ws";
  //  public static final String SUBSCRIBE_USER_PREFIX = "/private";
  public static final String SUBSCRIBE_USER_PREFIX = "/user";
  public static final String SUBSCRIBE_USER_REPLY = "/reply";
  public static final String SUBSCRIBE_QUEUE = "/user";

  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {
    config.enableSimpleBroker(SUBSCRIBE_QUEUE);
    config.setApplicationDestinationPrefixes("/app");
    config.setUserDestinationPrefix(SUBSCRIBE_USER_PREFIX);

  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint(ENDPOINT_CONNECT)
      .setAllowedOriginPatterns("*").withSockJS();
  }

  @Override
  public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
    registration.setMessageSizeLimit(50 * 1024 * 1024); // default : 64 * 1024
    registration.setSendTimeLimit(20 * 10000); // default : 10 * 10000
    registration.setSendBufferSizeLimit(10 * 1024 * 1024); // default : 512 * 1024

  }

  @Override
  public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
    final DefaultContentTypeResolver resolver = new DefaultContentTypeResolver();
    resolver.setDefaultMimeType(MimeTypeUtils.APPLICATION_JSON);
    final MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
    converter.setObjectMapper(new ObjectMapper());
    converter.setContentTypeResolver(resolver);
    messageConverters.add(converter);
    return false;
  }

}
