package org.terasoluna.securelogin.domain.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PasswordHistory {
	private String username;

	private String password;

	private LocalDateTime useFrom;
}
