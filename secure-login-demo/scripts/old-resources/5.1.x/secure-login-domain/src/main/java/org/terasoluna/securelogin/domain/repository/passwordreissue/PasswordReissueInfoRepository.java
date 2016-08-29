package org.terasoluna.securelogin.domain.repository.passwordreissue;

import java.time.LocalDateTime;

import org.apache.ibatis.annotations.Param;
import org.terasoluna.securelogin.domain.model.PasswordReissueInfo;


public interface PasswordReissueInfoRepository {

	void create(PasswordReissueInfo info);

	PasswordReissueInfo findOne(@Param("token") String token);

	int delete(@Param("token") String token);

	int deleteExpired(@Param("date") LocalDateTime date);
}
