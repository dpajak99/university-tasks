package com.tarbus.payload.entity;

import com.google.common.base.Objects;
import com.tarbus.payload.enums.ERole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;

	private String objectId;

	public Role(ERole name, String objectId) {
		this.name = name;
		this.objectId = objectId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Role)) return false;
		Role role = (Role) o;
		return Objects.equal(id, role.id) && name == role.name && Objects.equal(objectId, role.objectId);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id, name, objectId);
	}
}