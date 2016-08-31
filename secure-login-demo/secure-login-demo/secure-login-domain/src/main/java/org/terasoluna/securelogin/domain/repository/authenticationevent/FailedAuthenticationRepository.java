package org.terasoluna.securelogin.domain.repository.authenticationevent;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import org.terasoluna.securelogin.domain.model.FailedAuthentication;

public interface FailedAuthenticationRepository {

	int create(FailedAuthentication event);

	List<FailedAuthentication> findLatest(@Param("username") String username,
			@Param("count") long count);

	int deleteByUsername(@Param("username") String username);
}
