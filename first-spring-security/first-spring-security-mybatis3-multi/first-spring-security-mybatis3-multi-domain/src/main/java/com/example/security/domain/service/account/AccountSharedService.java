package com.example.security.domain.service.account;

import com.example.security.domain.model.Account;

public interface AccountSharedService {
    Account findOne(String username);
}

