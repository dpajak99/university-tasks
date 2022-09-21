package com.tarbus.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequest {
	@NotBlank
	private String email;

	@NotBlank
	private String password;

	@Override
	public String toString() {
		return "LoginRequest{" +
			"email='" + email + '\'' +
			", password='" + password + '\'' +
			'}';
	}
}
