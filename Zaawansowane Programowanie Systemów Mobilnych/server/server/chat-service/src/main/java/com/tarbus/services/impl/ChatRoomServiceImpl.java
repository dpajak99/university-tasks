package com.tarbus.services.impl;

import com.google.common.collect.Lists;
import com.tarbus.exceptions.BadRequestException;
import com.tarbus.payload.dto.RoleDto;
import com.tarbus.payload.entity.Role;
import com.tarbus.payload.entity.User;
import com.tarbus.payload.entity.jpa.DefaultChannelChatRoom;
import com.tarbus.payload.entity.mongo.room.ChatRoom;
import com.tarbus.payload.entity.mongo.room.RoomType;
import com.tarbus.payload.entity.mongo.room.types.ChannelChatRoom;
import com.tarbus.payload.enums.ERole;
import com.tarbus.payload.mappers.ChatRoomMapper;
import com.tarbus.payload.mappers.RoleDtoMapper;
import com.tarbus.payload.request.CreateRoomRequest;
import com.tarbus.repositories.jpa.DefaultChatRoomRepository;
import com.tarbus.repositories.mongo.ChatRoomRepository;
import com.tarbus.services.ChatRoomService;
import com.tarbus.services.UserDataService;
import com.tarbus.utilities.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {
  private static final Logger logger = LoggerFactory.getLogger(ChatRoomServiceImpl.class);

  private final DefaultChatRoomRepository defaultChatRoomRepository;
  private final ChatRoomRepository chatRoomRepository;
  private final UserDataService userDataService;
  private final ChatRoomMapper chatRoomMapper;
  private final RoleDtoMapper roleDtoMapper;

  @Autowired
  public ChatRoomServiceImpl(ChatRoomRepository chatRoomRepository, UserDataService userDataService, DefaultChatRoomRepository defaultChatRoomRepository, ChatRoomMapper chatRoomMapper, RoleDtoMapper roleDtoMapper) {
    this.chatRoomRepository = chatRoomRepository;
    this.userDataService = userDataService;
    this.defaultChatRoomRepository = defaultChatRoomRepository;
    this.chatRoomMapper = chatRoomMapper;
    this.roleDtoMapper = roleDtoMapper;
  }

  @Override
  public Set<ChatRoom> findOrCreateUserRooms(String userId) {
    User user = userDataService.getUserById(userId);
    List<ChatRoom> defaultRooms = getDefaultChannelRooms(user);
    List<ChatRoom> roleRooms = getRoleChatRooms(user);
    List<ChatRoom> privateRooms = getAllUserRooms(user);

    Set<ChatRoom> allRooms = new HashSet<>();
    allRooms.addAll(defaultRooms);
    allRooms.addAll(roleRooms);
    allRooms.addAll(privateRooms);
    return allRooms;
  }

  @Override
  public ChatRoom findChatRoomDetails(String roomId) {
    Optional<ChatRoom> roomOptional = chatRoomRepository.findById(roomId);
    if( roomOptional.isPresent() ) {
      return roomOptional.get();
    }
    throw new BadRequestException("Error: Room not exists");
  }

  @Override
  public ChatRoom createRoom(CreateRoomRequest roomData) {
//    List<ChatRoom> chatRoom = chatRoomRepository.findByUsers(roomData.getUsers());
//    if( chatRoom.size() > 0 ) {
//      return chatRoom.get(0);
//    }
    final ChatRoom newChatRoom = chatRoomMapper.mapToEntity(roomData);
    chatRoomRepository.save(newChatRoom);
    return newChatRoom;
  }

  @Override
  public ChatRoom updateRoom(ChatRoom chatRoom) {
    return chatRoomRepository.save(chatRoom);
  }

  private List<ChatRoom> getAllUserRooms(User user) {
    return chatRoomRepository.findByTypeAndUserId(RoomType.group, user.getId());
  }

  private List<ChatRoom> getRoleChatRooms(User user) {
    List<Role> chatRoles = user.getRoles().stream().filter((Role role) -> role.getName() == ERole.CHAT_READ_ADMIN_ROOM).collect(Collectors.toList());
    List<ChatRoom> roleRooms = new ArrayList<>();
    for (Role role : chatRoles) {
      RoleDto roleDto = roleDtoMapper.mapToEntity(role);
      roleRooms.addAll(chatRoomRepository.findByChannelRole(roleDto));
    }
    return roleRooms;
  }

  private List<ChatRoom> getDefaultChannelRooms(User user) {
    List<DefaultChannelChatRoom> defaultChannelRooms = Lists.newArrayList(defaultChatRoomRepository.findAll());
    List<ChatRoom> existingChannelRooms = chatRoomRepository.findByTypeAndUserId(RoomType.channel, user.getId());
    List<ChatRoom> notExistingChatRooms = new ArrayList<>();
    for (DefaultChannelChatRoom defaultChannelRoom : defaultChannelRooms) {
      boolean roomNotExists = true;
      for( ChatRoom existingChatRoom : existingChannelRooms ) {
        if (existingChatRoom instanceof ChannelChatRoom) {
          boolean roomsEquals = Objects.equals(((ChannelChatRoom) existingChatRoom).getRoleObjectId(), defaultChannelRoom.getRoleObjectId());
          if( roomsEquals ) {
            roomNotExists = false;
            break;
          }
        }
      }
      if (roomNotExists) {
        notExistingChatRooms.add(chatRoomMapper.mapToEntity(defaultChannelRoom, user));
      }
    }
    existingChannelRooms.addAll(chatRoomRepository.saveAll(notExistingChatRooms));
    return existingChannelRooms;
  }
}
