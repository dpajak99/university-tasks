package com.tarbus.payload.mappers;

import com.tarbus.payload.dto.ChatUserDto;
import com.tarbus.payload.dto.FileObjectDto;
import com.tarbus.payload.entity.FileObject;
import com.tarbus.payload.entity.User;
import com.tarbus.payload.entity.mongo.message.ChatMessage;
import com.tarbus.payload.entity.mongo.message.MessageType;
import com.tarbus.payload.entity.mongo.message.types.*;
import com.tarbus.payload.response.MessageResponse;
import com.tarbus.payload.response.messages.*;
import com.tarbus.services.FileService;
import com.tarbus.services.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatMessageResponseMapper {
  private final UserDataService userDataService;
  private final ChatUserDtoMapper chatUserDtoMapper;
  private final FileService fileService;
  private final FileObjectDtoMapper fileObjectDtoMapper;

  @Autowired
  public ChatMessageResponseMapper(UserDataService userDataService, ChatUserDtoMapper chatUserDtoMapper, FileService fileService, FileObjectDtoMapper fileObjectDtoMapper) {
    this.userDataService = userDataService;
    this.chatUserDtoMapper = chatUserDtoMapper;
    this.fileService = fileService;
    this.fileObjectDtoMapper = fileObjectDtoMapper;
  }

  public MessageResponse mapToEntity(ChatMessage chatMessage) {
    if (chatMessage == null) {
      return null;
    }
    switch (chatMessage.getType()) {
      case custom:
        return mapToCustomChatMessageResponse((CustomMessage) chatMessage);
      case file:
        return mapToFileChatMessageResponse((FileMessage) chatMessage);
      case image:
        return mapToImageChatMessageResponse((ImageMessage) chatMessage);
      case text:
        return mapToTextChatMessageResponse((TextMessage) chatMessage);
      default:
        return mapToUnsupportedChatMessageResponse((UnsupportedMessage) chatMessage);
    }
  }

  private MessageResponse mapToCustomChatMessageResponse(CustomMessage chatMessage) {
    CustomMessageResponse customMessage = new CustomMessageResponse();
    customMessage.setId(chatMessage.getId());
    customMessage.setType(MessageType.custom);
    customMessage.setCreatedAt(chatMessage.getCreatedAt());
    customMessage.setUpdatedAt(chatMessage.getUpdatedAt());
    customMessage.setStatus(chatMessage.getStatus());
    customMessage.setAuthor(getMessageAuthor(chatMessage));
    customMessage.setMetadata(chatMessage.getMetadata());
    customMessage.setRoomId(chatMessage.getRoomId());
    return customMessage;
  }

  private MessageResponse mapToFileChatMessageResponse(FileMessage chatMessage) {
    FileMessageResponse fileMessage = new FileMessageResponse();
    fileMessage.setId(chatMessage.getId());
    fileMessage.setType(MessageType.file);
    fileMessage.setCreatedAt(chatMessage.getCreatedAt());
    fileMessage.setUpdatedAt(chatMessage.getUpdatedAt());
    fileMessage.setStatus(chatMessage.getStatus());
    fileMessage.setAuthor(getMessageAuthor(chatMessage));
    fileMessage.setMetadata(chatMessage.getMetadata());
    fileMessage.setRoomId(chatMessage.getRoomId());

    FileObject fileObject = fileService.getFileById(chatMessage.getFileId());
    fileMessage.setFile(fileObjectDtoMapper.mapToEntity(fileObject));

    return fileMessage;
  }

  private MessageResponse mapToImageChatMessageResponse(ImageMessage chatMessage) {
    ImageMessageResponse imageMessage = new ImageMessageResponse();
    imageMessage.setId(chatMessage.getId());
    imageMessage.setType(MessageType.image);
    imageMessage.setCreatedAt(chatMessage.getCreatedAt());
    imageMessage.setUpdatedAt(chatMessage.getUpdatedAt());
    imageMessage.setStatus(chatMessage.getStatus());
    imageMessage.setAuthor(getMessageAuthor(chatMessage));
    imageMessage.setMetadata(chatMessage.getMetadata());
    imageMessage.setRoomId(chatMessage.getRoomId());

    FileObject fileObject = fileService.getFileById(chatMessage.getImageId());
    imageMessage.setImage(fileObjectDtoMapper.mapToEntity(fileObject));

    return imageMessage;
  }

  private MessageResponse mapToTextChatMessageResponse(TextMessage chatMessage) {
    System.out.println("mapToTextChatMessageResponse, {}" + chatMessage);
    TextMessageResponse textMessage = new TextMessageResponse();
    textMessage.setId(chatMessage.getId());
    textMessage.setType(MessageType.text);
    textMessage.setCreatedAt(chatMessage.getCreatedAt());
    textMessage.setUpdatedAt(chatMessage.getUpdatedAt());
    textMessage.setStatus(chatMessage.getStatus());
    textMessage.setAuthor(getMessageAuthor(chatMessage));
    textMessage.setMetadata(chatMessage.getMetadata());
    textMessage.setRoomId(chatMessage.getRoomId());

    textMessage.setText(chatMessage.getText());
    return textMessage;
  }

  private MessageResponse mapToUnsupportedChatMessageResponse(UnsupportedMessage chatMessage) {
    UnsupportedMessageResponse unsupportedMessage = new UnsupportedMessageResponse();
    unsupportedMessage.setId(chatMessage.getId());
    unsupportedMessage.setType(MessageType.unsupported);
    unsupportedMessage.setCreatedAt(chatMessage.getCreatedAt());
    unsupportedMessage.setUpdatedAt(chatMessage.getUpdatedAt());
    unsupportedMessage.setStatus(chatMessage.getStatus());
    unsupportedMessage.setAuthor(getMessageAuthor(chatMessage));
    unsupportedMessage.setMetadata(chatMessage.getMetadata());
    unsupportedMessage.setRoomId(chatMessage.getRoomId());
    return unsupportedMessage;
  }

  private ChatUserDto getMessageAuthor(ChatMessage message) {
    User user = userDataService.getUserById(message.getAuthorId());
    return chatUserDtoMapper.mapToEntity(user);
  }
}
