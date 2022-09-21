package com.tarbus.payload.response;

import com.tarbus.payload.dto.AccessTokenDto;
import com.tarbus.payload.dto.FileObjectDto;
import com.tarbus.payload.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class JwtResponse {
	private AccessTokenDto token;
	private String id;
	private String email;
	private String firstName;
	private String lastName;
	private FileObjectDto avatar;
	private Set<Role> roles;
}
