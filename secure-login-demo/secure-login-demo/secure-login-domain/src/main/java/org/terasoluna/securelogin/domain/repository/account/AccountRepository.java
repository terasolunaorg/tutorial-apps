package org.terasoluna.securelogin.domain.repository.account;

import org.apache.ibatis.annotations.Param;
import org.terasoluna.securelogin.domain.model.Account;
import org.terasoluna.securelogin.domain.model.AccountImage;

public interface AccountRepository {
	Account findOne(String username);

	boolean updatePassword(@Param("username") String username,
			@Param("password") String password);

	boolean create(Account account);
	
	boolean createRoles(Account account);
	
	AccountImage findImage(String username);
	
	boolean createImage(AccountImage accountImage);
}
