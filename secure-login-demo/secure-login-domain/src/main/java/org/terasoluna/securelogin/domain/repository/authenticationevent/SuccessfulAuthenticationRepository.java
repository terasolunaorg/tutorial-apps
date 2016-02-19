package org.terasoluna.securelogin.domain.repository.authenticationevent;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import org.terasoluna.securelogin.domain.model.SuccessfulAuthentication;

public interface SuccessfulAuthenticationRepository {

	int create(SuccessfulAuthentication event);

	List<SuccessfulAuthentication> findLatest(
			@Param("username") String username, @Param("count") long count);
}
