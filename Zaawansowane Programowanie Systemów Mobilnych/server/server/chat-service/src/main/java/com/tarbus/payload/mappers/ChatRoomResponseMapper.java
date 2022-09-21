package com.tarbus.payload.mappers;

import com.tarbus.payload.dto.ChatUserDto;
import com.tarbus.payload.entity.jpa.DefaultChannelChatRoom;
import com.tarbus.payload.entity.mongo.message.ChatMessage;
import com.tarbus.payload.entity.mongo.room.ChatRoom;
import com.tarbus.payload.entity.mongo.room.RoomType;
import com.tarbus.payload.entity.mongo.room.types.ChannelChatRoom;
import com.tarbus.payload.entity.mongo.room.types.GroupChatRoom;
import com.tarbus.payload.response.ChatRoomResponse;
import com.tarbus.payload.response.rooms.ChannelRoomResponse;
import com.tarbus.repositories.jpa.DefaultChatRoomRepository;
import com.tarbus.services.ChatMessageService;
import com.tarbus.services.FileService;
import com.tarbus.services.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChatRoomResponseMapper {
  private final UserDataService userDataService;
  private final ChatUserDtoMapper chatUserDtoMapper;
  private final DefaultChatRoomRepository defaultChatRoomRepository;
  private final FileObjectDtoMapper fileObjectDtoMapper;
  private final FileService fileService;
  private final ChatMessageService chatMessageService;
  private final ChatMessageResponseMapper chatMessageResponseMapper;

  @Autowired
  public ChatRoomResponseMapper(UserDataService userDataService, ChatUserDtoMapper chatUserDtoMapper, DefaultChatRoomRepository defaultChatRoomRepository, FileObjectDtoMapper fileObjectDtoMapper, FileService fileService, ChatMessageService chatMessageService, ChatMessageResponseMapper chatMessageResponseMapper) {
    this.userDataService = userDataService;
    this.chatUserDtoMapper = chatUserDtoMapper;
    this.defaultChatRoomRepository = defaultChatRoomRepository;
    this.fileObjectDtoMapper = fileObjectDtoMapper;
    this.fileService = fileService;
    this.chatMessageService = chatMessageService;
    this.chatMessageResponseMapper = chatMessageResponseMapper;
  }


  public ChatRoomResponse mapToEntity(ChatRoom chatRoom) {
    if (chatRoom.getRoomType() == RoomType.channel) {
      return mapToChannelRoomResponse((ChannelChatRoom) chatRoom);
    }
    return mapToGroupRoomResponse((GroupChatRoom) chatRoom);
  }

  private ChatRoomResponse mapToChannelRoomResponse(ChannelChatRoom chatRoom) {
    ChannelRoomResponse chatRoomResponse = new ChannelRoomResponse();
    chatRoomResponse.setId(chatRoom.getId());
    chatRoomResponse.setRoomType(chatRoom.getRoomType());
    chatRoomResponse.setCreatedAt(chatRoom.getUpdatedAt());
    chatRoomResponse.setUpdatedAt(chatRoom.getUpdatedAt());
    chatRoomResponse.setMetadata(chatRoom.getMetadata());
    chatRoomResponse.setUsers(getRoomUsers(chatRoom));
    ChatMessage lastChatMessage = chatMessageService.findLastChatMessageForRoom(chatRoom.getId());
    chatRoomResponse.setLastChatMessage(chatMessageResponseMapper.mapToEntity(lastChatMessage));

    DefaultChannelChatRoom defaultChannelChatRoom = defaultChatRoomRepository.findByRoleObjectId(chatRoom.getRoleObjectId());
    chatRoomResponse.setName(defaultChannelChatRoom.getName());
    chatRoomResponse.setImage(fileObjectDtoMapper.mapToEntity(defaultChannelChatRoom.getAvatar()));
    return chatRoomResponse;
  }

  private ChatRoomResponse mapToGroupRoomResponse(GroupChatRoom chatRoom) {
    ChannelRoomResponse chatRoomResponse = new ChannelRoomResponse();
    chatRoomResponse.setId(chatRoom.getId());
    chatRoomResponse.setRoomType(chatRoom.getRoomType());
    chatRoomResponse.setCreatedAt(chatRoom.getUpdatedAt());
    chatRoomResponse.setUpdatedAt(chatRoom.getUpdatedAt());
    chatRoomResponse.setMetadata(chatRoom.getMetadata());
    chatRoomResponse.setUsers(getRoomUsers(chatRoom));
    chatRoomResponse.setName(chatRoom.getName());
    ChatMessage lastChatMessage = chatMessageService.findLastChatMessageForRoom(chatRoom.getId());
    chatRoomResponse.setLastChatMessage(chatMessageResponseMapper.mapToEntity(lastChatMessage));
    if( chatRoom.getImageId() != null ) {
      chatRoomResponse.setImage(fileObjectDtoMapper.mapToEntity(fileService.getFileById(chatRoom.getImageId())));
    }
    return chatRoomResponse;
  }

  private List<ChatUserDto> getRoomUsers(ChatRoom chatRoom) {
    List<ChatUserDto> users = new ArrayList<>();
    for( String userId : chatRoom.getUsersId()) {
      users.add(chatUserDtoMapper.mapToEntity(userDataService.getUserById(userId)));
    }
    return users;
  }
}
