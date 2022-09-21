package com.tarbus.payload.response.messages;


import com.tarbus.payload.dto.ChatUserDto;
import com.tarbus.payload.entity.mongo.message.ChatMessage;
import com.tarbus.payload.entity.mongo.message.MessageStatus;
import com.tarbus.payload.entity.mongo.message.MessageType;
import com.tarbus.payload.response.MessageResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Map;

@Document(collection = "chat_messages")
@Getter
@Setter
@NoArgsConstructor
public class CustomMessageResponse extends MessageResponse {
  private String id;
  private ChatUserDto author;
  private Date createdAt;
  private Date updatedAt;
  private Map<String, Object> metadata;
  private String roomId;
  private MessageType type;
  private MessageStatus status;
}
