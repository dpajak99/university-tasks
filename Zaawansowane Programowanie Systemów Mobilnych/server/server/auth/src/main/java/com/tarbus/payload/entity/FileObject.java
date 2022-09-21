package com.tarbus.payload.entity;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "files")
@Getter
@Setter
@NoArgsConstructor
public class FileObject {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
    name = "UUID",
    strategy = "org.hibernate.id.UUIDGenerator"
  )
  @Column(name = "id", updatable = false, nullable = false)
  private String id;

  @Column(name = "name")
  private String name;

  @Column(name = "extension")
  private String extension;

  @OneToOne()
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  @Column(name = "upload_date", nullable=true)
  private Date timestamp;

  @PrePersist
  protected void onCreate() {
    timestamp = new Date();
  }

  public String getParsedName() {
    return id + "." + extension;
  }

  public boolean isImage() {
    List<String> imageExtensions = Lists.newArrayList("png", "jpg", "jpeg");
    return imageExtensions.contains(extension);
  }
}


