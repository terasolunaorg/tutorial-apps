package com.example.security.domain.service.userdetails;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.example.security.domain.model.Account;

public class SampleUserDetails extends User { // (1)
    private static final long serialVersionUID = 1L;

    private final Account account; // (2)

    public SampleUserDetails(Account account) {
        // (3)
        super(account.getUsername(), account.getPassword(), AuthorityUtils
                .createAuthorityList("ROLE_USER")); // (4)
        this.account = account;
    }

    public Account getAccount() { // (5)
        return account;
    }

}

