package com.tarbus.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class AddUserToCompanyRequest {
	@NotBlank
	private String companyId;
}
