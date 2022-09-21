package com.tarbus.payload.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class CompanyUsersId implements Serializable {
  @OneToOne()
  @JoinColumn(name = "company_id", referencedColumnName = "id")
  private Company company;

  @OneToOne()
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  public CompanyUsersId(Company company, User user) {
    this.company = company;
    this.user = user;
  }
}
