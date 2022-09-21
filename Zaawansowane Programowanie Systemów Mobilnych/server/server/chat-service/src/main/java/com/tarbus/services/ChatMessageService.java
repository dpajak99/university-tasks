package com.tarbus.services;

import com.tarbus.payload.dto.CountMessageDto;
import com.tarbus.payload.entity.User;
import com.tarbus.payload.entity.mongo.message.ChatMessage;

import java.util.List;
import java.util.Optional;

public interface ChatMessageService {
    /**
     * Saves chat message in MongoDB database
     * @param chatMessage - ChatMessage Object
     * @return - ChatMessage object with assigned parameters
     */
    ChatMessage saveMessage(ChatMessage chatMessage);

    /**
     * Finds last chat message sent in selected room
     * @param roomId - Room ID parameter
     * @return - Returns last chat message
     */
    ChatMessage findLastChatMessageForRoom(String roomId);


    /**
     * Finds all messages from selected room
     * @param roomId - Room ID parameter
     * @return - Returns list of ChatMessage from room
     */
    // TODO(dominik): Allow to range search
    //                (example: page1, page2 or from index 0 to index 20)
    List<ChatMessage> findChatMessages(String roomId);

    /**
     * Finds message by provided ID
     * @param id - Searched message id
     * @return - ChatMessage if its found
     */
    ChatMessage findById(String id);

    List<CountMessageDto> countMessages(User user);
}
