package com.example.session.domain.repository.account;

import com.example.session.domain.model.Account;

public interface AccountRepository {

	Account findOne(String email);

	void create(Account account);

	boolean update(Account account);

}
