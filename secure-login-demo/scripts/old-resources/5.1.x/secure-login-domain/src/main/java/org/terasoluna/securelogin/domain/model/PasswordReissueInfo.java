package org.terasoluna.securelogin.domain.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PasswordReissueInfo {

	private String username;

	private String token;

	private String secret;

	private LocalDateTime expiryDate;
}
