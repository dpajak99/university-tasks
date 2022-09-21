package com.tarbus.payload.entity.mongo.room;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.common.base.Objects;
import com.tarbus.payload.entity.Role;
import com.tarbus.payload.enums.ERole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class ChatRoom {
  @Id
  private String id;
  private RoomType roomType;
  private Date createdAt;
  private Date updatedAt;
  private Map<String, Object> metadata;
  private List<String> usersId;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ChatRoom)) return false;
    ChatRoom chatRoom = (ChatRoom) o;
    return Objects.equal(id, chatRoom.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
