package com.tarbus.controllers;

import com.tarbus.exceptions.RequestException;
import com.tarbus.payload.dto.ChatUserDto;
import com.tarbus.payload.dto.CountMessageDto;
import com.tarbus.payload.entity.Role;
import com.tarbus.payload.entity.User;
import com.tarbus.payload.entity.mongo.message.ChatMessage;
import com.tarbus.payload.entity.mongo.room.ChatRoom;
import com.tarbus.payload.entity.mongo.room.types.ChannelChatRoom;
import com.tarbus.payload.enums.ERole;
import com.tarbus.payload.mappers.*;
import com.tarbus.payload.request.CreateRoomRequest;
import com.tarbus.payload.response.ChatRoomResponse;
import com.tarbus.payload.response.MessageResponse;
import com.tarbus.repositories.jpa.RoleRepository;
import com.tarbus.security.UserDetailsImpl;
import com.tarbus.services.AuthService;
import com.tarbus.services.ChatMessageService;
import com.tarbus.services.ChatRoomService;
import com.tarbus.services.UserDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import java.util.*;

@Controller
@CrossOrigin
@RequestMapping("/")
public class ChatController {
  private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

  private final AuthService authService;
  private final UserDataService userDataService;
  private final SimpMessagingTemplate messagingTemplate;
  private final ChatMessageService chatMessageService;
  private final ChatRoomService chatRoomService;
  private final ChatUserDtoMapper chatUserDtoMapper;
  private final ChatMessageMapper chatMessageMapper;
  private final RoleRepository roleRepository;
  private final ChatMessageResponseMapper chatMessageResponseMapper;
  private final ChatRoomResponseMapper chatRoomResponseMapper;

  @Autowired
  public ChatController(AuthService authService, UserDataService userDataService, SimpMessagingTemplate messagingTemplate, ChatMessageService chatMessageService, ChatRoomService chatRoomService, ChatUserDtoMapper chatUserDtoMapper, ChatMessageMapper chatMessageMapper, RoleRepository roleRepository, ChatMessageResponseMapper chatMessageResponseMapper, ChatRoomResponseMapper chatRoomResponseMapper) {
    this.authService = authService;
    this.userDataService = userDataService;
    this.messagingTemplate = messagingTemplate;
    this.chatMessageService = chatMessageService;
    this.chatRoomService = chatRoomService;
    this.chatUserDtoMapper = chatUserDtoMapper;
    this.chatMessageMapper = chatMessageMapper;
    this.roleRepository = roleRepository;
    this.chatMessageResponseMapper = chatMessageResponseMapper;
    this.chatRoomResponseMapper = chatRoomResponseMapper;
  }


  @MessageMapping("/chat")
  public void processMessage(@Payload Map<String, Object> messageData, UsernamePasswordAuthenticationToken token) throws RestClientException {
    System.out.println("Received message: " + messageData);
    User user = authService.getUserFromPrincipal((UserDetailsImpl) token.getPrincipal());
    final ChatUserDto actualUser = chatUserDtoMapper.mapToEntity(user);
    final ChatMessage message = chatMessageMapper.mapToEntity(messageData, actualUser);
    final ChatMessage savedMessage = chatMessageService.saveMessage(message);

    ChatRoom room = chatRoomService.findChatRoomDetails(savedMessage.getRoomId());
    room.setUpdatedAt(new Date());
    chatRoomService.updateRoom(room);

    notifyChatListeners(room, chatMessageResponseMapper.mapToEntity(message));
  }

  @GetMapping("/chat/room/{roomId}/messages")
  public ResponseEntity<?> findChatMessages(@PathVariable String roomId) {
    List<ChatMessage> messages = chatMessageService.findChatMessages(roomId);
    List<MessageResponse> messagesResponse = new ArrayList<>();
    for( ChatMessage chatMessage : messages ) {
      messagesResponse.add(chatMessageResponseMapper.mapToEntity(chatMessage));
    }
    return new ResponseEntity<>(messagesResponse, HttpStatus.OK);
  }

  @GetMapping("/chat/messages/{id}")
  public ResponseEntity<?> findMessage(@PathVariable String id) {
    ChatMessage chatMessage = chatMessageService.findById(id);
    MessageResponse messageResponse = chatMessageResponseMapper.mapToEntity(chatMessage);
    return new ResponseEntity<>(messageResponse, HttpStatus.OK);
  }

  @PostMapping("/chat/messages")
  public ResponseEntity<?> addMessage(@RequestBody Map<String, Object> messageData) {
    try {
      final User user = authService.getSessionUser();
      final ChatUserDto actualUser = chatUserDtoMapper.mapToEntity(user);
      final ChatMessage message = chatMessageMapper.mapToEntity(messageData, actualUser);
      final ChatMessage savedMessage = chatMessageService.saveMessage(message);

      ChatRoom room = chatRoomService.findChatRoomDetails(savedMessage.getRoomId());
      room.setUpdatedAt(new Date());
      chatRoomService.updateRoom(room);

      notifyChatListeners(room, chatMessageResponseMapper.mapToEntity(message));

      return new ResponseEntity<>(message, HttpStatus.OK);
    } catch (RequestException e) {
      return e.toResponseEntity();
    }
  }

  @GetMapping("/chat/messages/count")
  public ResponseEntity<?> countMessages() {
    try {
      User user = authService.getSessionUser();
      List<CountMessageDto> messages = chatMessageService.countMessages(user);

      return new ResponseEntity<>(messages, HttpStatus.OK);
    } catch (RequestException e) {
      return e.toResponseEntity();
    }
  }

  @GetMapping("/user/{id}/chat/rooms")
  public ResponseEntity<?> findUserChatRooms(@PathVariable String id) {
    Set<ChatRoom> userChatRooms = chatRoomService.findOrCreateUserRooms(id);
    List<ChatRoomResponse> chatRoomsResponse = new ArrayList<>();
    for( ChatRoom chatRoom : userChatRooms) {
      chatRoomsResponse.add(chatRoomResponseMapper.mapToEntity(chatRoom));
    }
    return new ResponseEntity<>(chatRoomsResponse, HttpStatus.OK);
  }

  @GetMapping("/user/chat/rooms")
  public ResponseEntity<?> findSessionUserChatRooms() {
    try {
      User user = authService.getSessionUser();
      Set<ChatRoom> userChatRooms = chatRoomService.findOrCreateUserRooms(user.getId());
      List<ChatRoomResponse> chatRoomsResponse = new ArrayList<>();
      for (ChatRoom chatRoom : userChatRooms) {
        chatRoomsResponse.add(chatRoomResponseMapper.mapToEntity(chatRoom));
      }
      return new ResponseEntity<>(chatRoomsResponse, HttpStatus.OK);
    } catch(RequestException e) {
      return e.toResponseEntity();
    }
  }

  @GetMapping("/chat/room")
  public ResponseEntity<?> getChatRoomDetails(@RequestParam String roomId) {
    ChatRoom chatRoom = chatRoomService.findChatRoomDetails(roomId);
    ChatRoomResponse chatRoomResponse = chatRoomResponseMapper.mapToEntity(chatRoom);
    return new ResponseEntity<>(chatRoomResponse, HttpStatus.OK);
  }

  @PostMapping("/chat/room")
  public ResponseEntity<?> createRoom(@RequestBody CreateRoomRequest roomData) {
    ChatRoom chatRoom = chatRoomService.createRoom(roomData);
    ChatRoomResponse chatRoomResponse = chatRoomResponseMapper.mapToEntity(chatRoom);
    return new ResponseEntity<>(chatRoomResponse, HttpStatus.OK);
  }

  private void notifyChatListeners(ChatRoom chatRoom, MessageResponse messageResponse) {
    // Notify users from chat room
    for (String groupUserId : chatRoom.getUsersId()) {
      messagingTemplate.convertAndSendToUser(groupUserId, "/queue/messages", messageResponse);
    }

    // Notify room moderators
    if (chatRoom instanceof ChannelChatRoom) {
      String roleObjectId = ((ChannelChatRoom) chatRoom).getRoleObjectId();
      Optional<Role> roleOptional = roleRepository.findByNameAndObjectId(ERole.CHAT_READ_ADMIN_ROOM, roleObjectId);
      if (roleOptional.isPresent()) {
        Set<User> roomModerators = userDataService.getUsersByRole(roleOptional.get());
        for (User moderator : roomModerators) {
          messagingTemplate.convertAndSendToUser(moderator.getId(), "/queue/messages", messageResponse);
        }
      }
    }
  }

}
