package org.terasoluna.securelogin.domain.repository.passwordreissue;

import java.time.LocalDateTime;

import org.apache.ibatis.annotations.Param;
import org.terasoluna.securelogin.domain.model.FailedPasswordReissue;

public interface FailedPasswordReissueRepository {

	int countByToken(@Param("token") String token);

	int create(FailedPasswordReissue event);

	int deleteByToken(@Param("token") String token);

	int deleteExpired(@Param("date") LocalDateTime date);
}
