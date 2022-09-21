package com.tarbus.payload.mappers;

import com.tarbus.payload.dto.ChatUserDto;
import com.tarbus.payload.dto.CountMessageDto;
import com.tarbus.payload.entity.User;
import com.tarbus.services.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class CountMessageDtoMapper {
  private final UserDataService userDataService;
  private final ChatUserDtoMapper chatUserDtoMapper;

  @Autowired
  public CountMessageDtoMapper(UserDataService userDataService, ChatUserDtoMapper chatUserDtoMapper) {
    this.userDataService = userDataService;
    this.chatUserDtoMapper = chatUserDtoMapper;
  }

  public List<CountMessageDto> mapToList(Map<String, Integer> countMap) {
    List<CountMessageDto> messages = new ArrayList<>();
    for( Map.Entry<String, Integer> entry : countMap.entrySet() ) {
      try {
        messages.add(mapToEntity(entry.getKey(), entry.getValue()));
      } catch(Exception ignored) {}
    }
    return messages;
  }

  public CountMessageDto mapToEntity(String userId, Integer count) {
    System.out.println("userId: " + userId + " count: " + count);
    CountMessageDto countMessageDto = new CountMessageDto();
    User user = userDataService.getUserById(userId);
    ChatUserDto chatUserDto = chatUserDtoMapper.mapToEntity(user);

    countMessageDto.setUser(chatUserDto);
    countMessageDto.setMessages(count);
    return countMessageDto;
  }
}
