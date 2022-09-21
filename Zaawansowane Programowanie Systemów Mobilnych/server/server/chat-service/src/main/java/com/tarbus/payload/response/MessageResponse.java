package com.tarbus.payload.response;

import com.tarbus.payload.dto.ChatUserDto;
import com.tarbus.payload.entity.mongo.message.ChatMessage;
import com.tarbus.payload.entity.mongo.message.MessageStatus;
import com.tarbus.payload.entity.mongo.message.MessageType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class MessageResponse {
  private String id;
  private ChatUserDto author;
  private Date createdAt;
  private Date updatedAt;
  private Map<String, Object> metadata;
  private String roomId;
  private MessageType type;
  private MessageStatus status;
}
