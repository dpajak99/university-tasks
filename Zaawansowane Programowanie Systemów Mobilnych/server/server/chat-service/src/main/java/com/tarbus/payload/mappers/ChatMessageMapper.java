package com.tarbus.payload.mappers;

import com.tarbus.payload.entity.mongo.message.ChatMessage;
import com.tarbus.payload.entity.mongo.message.MessageType;
import com.tarbus.payload.dto.ChatUserDto;
import com.tarbus.payload.entity.mongo.message.types.*;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ChatMessageMapper {
  public ChatMessage mapToEntity(Map<String, Object> json, ChatUserDto chatUserDto) {
    switch ((String) json.get("type")) {
      case "custom":
        return mapToCustomChatMessage(json, chatUserDto);
      case "file":
        return mapToFileChatMessage(json, chatUserDto);
      case "image":
        return mapToImageChatMessage(json, chatUserDto);
      case "text":
        return mapToTextChatMessage(json, chatUserDto);
      default:
        return mapToUnsupportedChatMessage(json, chatUserDto);
    }
  }

  private ChatMessage mapToCustomChatMessage(Map<String, Object> json, ChatUserDto chatUserDto) {
    CustomMessage customMessage = new CustomMessage();
    customMessage.setId((String) json.get("id"));
    customMessage.setType(MessageType.custom);
    customMessage.setAuthorId(chatUserDto.getId());
    customMessage.setMetadata((Map<String, Object>) json.get("metadata"));
    customMessage.setRoomId((String) json.get("roomId"));
    return customMessage;
  }

  private ChatMessage mapToFileChatMessage(Map<String, Object> json, ChatUserDto chatUserDto) {
    FileMessage fileMessage = new FileMessage();
    fileMessage.setId((String) json.get("id"));
    fileMessage.setType(MessageType.file);
    fileMessage.setAuthorId(chatUserDto.getId());
    fileMessage.setMetadata((Map<String, Object>) json.get("metadata"));
    fileMessage.setRoomId((String) json.get("roomId"));
    fileMessage.setFileId((String) json.get("fileId"));
    return fileMessage;
  }

  private ChatMessage mapToImageChatMessage(Map<String, Object> json, ChatUserDto chatUserDto) {
    ImageMessage imageMessage = new ImageMessage();
    imageMessage.setId((String) json.get("id"));
    imageMessage.setType(MessageType.image);
    imageMessage.setAuthorId(chatUserDto.getId());
    imageMessage.setMetadata((Map<String, Object>) json.get("metadata"));
    imageMessage.setRoomId((String) json.get("roomId"));
    imageMessage.setImageId((String) json.get("imageId"));
    return imageMessage;
  }

  private ChatMessage mapToTextChatMessage(Map<String, Object> json, ChatUserDto chatUserDto) {
    TextMessage textMessage = new TextMessage();
    textMessage.setId((String) json.get("id"));
    textMessage.setType(MessageType.text);
    textMessage.setAuthorId(chatUserDto.getId());
    textMessage.setMetadata((Map<String, Object>) json.get("metadata"));
    textMessage.setRoomId((String) json.get("roomId"));
    textMessage.setText((String) json.get("text"));
    return textMessage;
  }

  private ChatMessage mapToUnsupportedChatMessage(Map<String, Object> json, ChatUserDto chatUserDto) {
    UnsupportedMessage unsupportedMessage = new UnsupportedMessage();
    unsupportedMessage.setId((String) json.get("id"));
    unsupportedMessage.setType(MessageType.unsupported);
    unsupportedMessage.setAuthorId(chatUserDto.getId());
    unsupportedMessage.setMetadata((Map<String, Object>) json.get("metadata"));
    unsupportedMessage.setRoomId((String) json.get("roomId"));
    return unsupportedMessage;
  }
}
