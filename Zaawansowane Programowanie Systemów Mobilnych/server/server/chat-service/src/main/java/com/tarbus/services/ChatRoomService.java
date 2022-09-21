package com.tarbus.services;

import com.tarbus.payload.entity.mongo.room.ChatRoom;
import com.tarbus.payload.response.ChatRoomResponse;
import com.tarbus.payload.request.CreateRoomRequest;

import java.util.List;
import java.util.Set;

public interface ChatRoomService {
    /**
     * Finds all user rooms basing on session user
     * Creates user default rooms if not exists
     * @return - Returns list of user chat rooms
     */
    Set<ChatRoom> findOrCreateUserRooms(String userId);

    /**
     * Finds chat room by provided id
     * @return - Returns full chat room details
     */
    ChatRoom findChatRoomDetails(String roomId);

    /**
     * Saving ChatRoom in database
     * @return - ChatRoom with assigned data
     */
    ChatRoom createRoom(CreateRoomRequest roomData);

    ChatRoom updateRoom(ChatRoom chatRoom);

}
