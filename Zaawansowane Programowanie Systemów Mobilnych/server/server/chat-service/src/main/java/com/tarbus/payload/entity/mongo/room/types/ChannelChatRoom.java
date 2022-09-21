package com.tarbus.payload.entity.mongo.room.types;

import com.tarbus.payload.entity.mongo.room.ChatRoom;
import com.tarbus.payload.entity.mongo.room.RoomType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "chat_rooms")
@Getter
@Setter
@NoArgsConstructor
public class ChannelChatRoom extends ChatRoom {
  private String roleObjectId;
}
