package com.tarbus.repositories.jpa;

import com.tarbus.payload.entity.jpa.DefaultChannelChatRoom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefaultChatRoomRepository extends CrudRepository<DefaultChannelChatRoom, Long> {
  DefaultChannelChatRoom findByRoleObjectId(String roleObjectId);
}