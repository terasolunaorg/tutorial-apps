package org.terasoluna.securelogin.domain.repository.account;

import org.apache.ibatis.annotations.Param;

import org.terasoluna.securelogin.domain.model.Account;

public interface AccountRepository {
	Account findOne(String username);

	boolean updatePassword(@Param("username") String username,
			@Param("password") String password);

}
