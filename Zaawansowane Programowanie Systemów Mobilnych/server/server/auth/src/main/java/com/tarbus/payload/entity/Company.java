package com.tarbus.payload.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "company")
@Getter
@Setter
@NoArgsConstructor
public class Company {
  @Id
  @Column(name = "id")
  private String id;
  @Column(name = "name")
  private String name;
  @Column(name = "website")
  private String website;
  @Column(name = "phone")
  private String phone;
  @Column(name = "email")
  private String email;
  @Column(name = "timezone")
  private String timezone;
  @Column(name = "lang")
  private String lang;
  @OneToOne()
  @JoinColumn(name = "avatar_id", referencedColumnName = "id")
  private FileObject avatar;
  @Column(name = "business_card")
  private String businessCard;
}
