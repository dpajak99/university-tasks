package com.tarbus.payload.entity.mongo.message;


import com.tarbus.payload.dto.ChatUserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.util.Date;
import java.util.Map;

/**
 * General class that defines message
 * from chat which is sent from user
 *
 * Classes that inherit from Chat Message:
 * - CustomMessage
 * - FileMessage
 * - ImageMessage
 * - TextMessage
 * - UnsupportedMessage
 */
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class ChatMessage {
  /**
   * Unique value that identifies the message object
   * Usually generated on the frontend side
   * */
  @Id
  private String id;

  /**
   * Parameter stored in MongoDB database that defines
   * unchangeable message author id
   */
  private String authorId;

  /**
   * Stores message creation date
   */
  private Date createdAt;

  /**
   * Stores message update date
   */
  private Date updatedAt;

  /**
   * Contains custom metadata assigned to specific message
   */
  private Map<String, Object> metadata;

  /**
   * Defines the chat room where chat message is assigned to
   */
  private String roomId;

  /**
   * Defines message type.
   * Basing on this parameter we can call specific constructor
   * to build appropriate instance of Chat Message
   */
  private MessageType type;

  /**
   * Tells us if the message has been sent, received
   * or if it has any other status
   */
  private MessageStatus status;

  @Override
  public String toString() {
    return "ChatMessage{" +
      "id='" + id + '\'' +
      ", authorId='" + authorId + '\'' +
      ", createdAt=" + createdAt +
      ", updatedAt=" + updatedAt +
      ", metadata=" + metadata +
      ", roomId='" + roomId + '\'' +
      ", type=" + type +
      ", status=" + status +
      '}';
  }
}
