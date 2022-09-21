package com.tarbus.payload.mappers;

import com.tarbus.payload.dto.ChatUserDto;
import com.tarbus.payload.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ChatUserDtoMapper {
  private final FileObjectDtoMapper fileObjectDtoMapper;

  public ChatUserDtoMapper(FileObjectDtoMapper fileObjectDtoMapper) {
    this.fileObjectDtoMapper = fileObjectDtoMapper;
  }


  public ChatUserDto mapToEntity(User user) {
    ChatUserDto chatUserDto = new ChatUserDto();

    chatUserDto.setId(user.getId());
    chatUserDto.setFirstName(user.getFirstName());
    chatUserDto.setLastName(user.getLastName());
    chatUserDto.setEmail(user.getEmail());
    chatUserDto.setAvatar(fileObjectDtoMapper.mapToEntity(user.getAvatar()));
    return chatUserDto;
  }
}
