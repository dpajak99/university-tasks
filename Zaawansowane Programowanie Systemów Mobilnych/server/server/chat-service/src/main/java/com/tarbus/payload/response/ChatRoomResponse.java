package com.tarbus.payload.response;

import com.tarbus.payload.entity.mongo.message.ChatMessage;
import com.tarbus.payload.entity.mongo.room.RoomType;
import com.tarbus.payload.dto.ChatUserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoomResponse {
  private String id;
  private RoomType roomType;
  private Date createdAt;
  private Date updatedAt;
  private Map<String, Object> metadata;
  private List<ChatUserDto> users;
  private MessageResponse lastChatMessage;
}
