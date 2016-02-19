package org.terasoluna.securelogin.domain.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class FailedPasswordReissue {

	private String token;

	private LocalDateTime attemptDate;

}
