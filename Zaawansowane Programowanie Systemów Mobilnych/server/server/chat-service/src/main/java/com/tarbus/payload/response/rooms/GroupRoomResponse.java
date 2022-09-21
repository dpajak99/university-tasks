package com.tarbus.payload.response.rooms;

import com.tarbus.payload.entity.FileObject;
import com.tarbus.payload.entity.mongo.room.RoomType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GroupRoomResponse {
  private FileObject image;
  private String name;
}
