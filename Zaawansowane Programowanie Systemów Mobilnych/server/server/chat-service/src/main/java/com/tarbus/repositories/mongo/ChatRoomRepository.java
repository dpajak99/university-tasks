package com.tarbus.repositories.mongo;


import com.tarbus.payload.dto.RoleDto;
import com.tarbus.payload.entity.Role;
import com.tarbus.payload.entity.mongo.room.RoomType;
import com.tarbus.payload.enums.ERole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.tarbus.payload.entity.mongo.room.ChatRoom;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {

  @Query(value = "{ $and: [{roomType: ?0}, {usersId : {$all : [?1] }}]}")
  List<ChatRoom> findByTypeAndUserId(RoomType type, String userId);

  @Query(value = "{ channelRole : ?1}")
  List<ChatRoom> findByChannelRole(RoleDto roleDto);

  @Query(value = "{ usersId : {$all : [?0] }}")
  List<ChatRoom> findUserRooms(String userId);

  @Query(value = "{ usersId : ?0}")
  List<ChatRoom> findByUsers(List<String> users);
}
