package com.tarbus.payload.entity;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "user_rates")
@Getter
@Setter
@NoArgsConstructor
public class UserRate {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
    name = "UUID",
    strategy = "org.hibernate.id.UUIDGenerator"
  )
  @Column(name = "id", updatable = false, nullable = false)
  private String id;

  @Column(name = "rate")
  private double rate;

  @OneToOne()
  @JoinColumn(name = "given_to_user_id", referencedColumnName = "id")
  private User givenTo;

  @OneToOne()
  @JoinColumn(name = "given_by_user_id", referencedColumnName = "id")
  private User givenBy;

  @Column(name = "created_on", nullable=true)
  private Date timestamp;

  @PrePersist
  protected void onCreate() {
    timestamp = new Date();
  }
}


