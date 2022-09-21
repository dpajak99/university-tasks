package com.tarbus.payload.response.rooms;

import com.tarbus.payload.dto.FileObjectDto;
import com.tarbus.payload.entity.mongo.room.RoomType;
import com.tarbus.payload.response.ChatRoomResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChannelRoomResponse extends ChatRoomResponse {
  private String name;
  private FileObjectDto image;
}
