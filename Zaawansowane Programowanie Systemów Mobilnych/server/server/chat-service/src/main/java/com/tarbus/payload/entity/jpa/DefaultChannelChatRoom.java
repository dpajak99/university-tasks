package com.tarbus.payload.entity.jpa;

import com.tarbus.payload.entity.FileObject;
import com.tarbus.payload.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "default_chat_rooms")
@Getter
@Setter
@NoArgsConstructor
public class DefaultChannelChatRoom {
  @Id
  private Long id;

  private String name;

  @OneToOne()
  @JoinColumn(name = "avatar_id", referencedColumnName = "id")
  private FileObject avatar;

  private String roleObjectId;
}
