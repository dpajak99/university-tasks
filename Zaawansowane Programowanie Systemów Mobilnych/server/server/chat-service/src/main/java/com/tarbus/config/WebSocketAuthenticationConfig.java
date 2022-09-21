package com.tarbus.config;

import com.tarbus.security.UserDetailsServiceImpl;
import com.tarbus.utilities.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
public class WebSocketAuthenticationConfig implements WebSocketMessageBrokerConfigurer {
  private final JwtUtils jwtUtil;
  private final UserDetailsServiceImpl userService;

  @Autowired
  public WebSocketAuthenticationConfig(JwtUtils jwtUtil, UserDetailsServiceImpl userService) {
    this.jwtUtil = jwtUtil;
    this.userService = userService;
  }

  @Override
  public void configureClientInboundChannel(ChannelRegistration registration) {
    registration.interceptors(new ChannelInterceptor() {
      @Override
      public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        assert accessor != null;
        List<String> tokenList = accessor.getNativeHeader("Authorization");
        String jwt = null;
        if (tokenList == null || tokenList.size() < 1) {
          return message;
        } else {
          jwt = tokenList.get(0).substring(7);
          if (jwt == null) {
            return message;
          }
        }
        String username = jwtUtil.getEmailFromJwtToken(jwt);

        UserDetails userDetails = userService.loadUserByUsername(username);
        if (jwtUtil.validateJwtToken(jwt)) {
          UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
          SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
          accessor.setUser(authentication);
        }
        return message;
      }
    });
  }
}