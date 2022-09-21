package com.tarbus.payload.mappers;

import com.google.common.collect.Lists;
import com.tarbus.payload.entity.User;
import com.tarbus.payload.entity.jpa.DefaultChannelChatRoom;
import com.tarbus.payload.entity.mongo.room.ChatRoom;
import com.tarbus.payload.entity.mongo.room.RoomType;
import com.tarbus.payload.entity.mongo.room.types.ChannelChatRoom;
import com.tarbus.payload.entity.mongo.room.types.GroupChatRoom;
import com.tarbus.payload.request.CreateRoomRequest;
import com.tarbus.repositories.jpa.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Component
public class ChatRoomMapper {
  private final RoleRepository roleRepository;
  private final RoleDtoMapper roleDtoMapper;

  @Autowired
  public ChatRoomMapper(RoleRepository roleRepository, RoleDtoMapper roleDtoMapper) {
    this.roleRepository = roleRepository;
    this.roleDtoMapper = roleDtoMapper;
  }

  public ChatRoom mapToEntity(DefaultChannelChatRoom defaultChannelChatRoom, User user) {
    List<String> usersId = Lists.newArrayList(user.getId());

    ChannelChatRoom chatRoom = new ChannelChatRoom();
    chatRoom.setId(UUID.randomUUID().toString());
    chatRoom.setCreatedAt(new Date());
    chatRoom.setUpdatedAt(new Date());
    chatRoom.setMetadata(new HashMap<>());
    chatRoom.setRoomType(RoomType.channel);
    chatRoom.setUsersId(usersId);
    chatRoom.setRoleObjectId(defaultChannelChatRoom.getRoleObjectId());

    return chatRoom;
  }

  public ChatRoom mapToEntity(CreateRoomRequest createRoomRequest) {
    GroupChatRoom chatRoom = new GroupChatRoom();
    chatRoom.setId(UUID.randomUUID().toString());
    chatRoom.setCreatedAt(new Date());
    chatRoom.setUpdatedAt(new Date());
    chatRoom.setMetadata(new HashMap<>());
    chatRoom.setRoomType(RoomType.group);
    chatRoom.setUsersId(createRoomRequest.getUsers());
    return chatRoom;
  }
}
