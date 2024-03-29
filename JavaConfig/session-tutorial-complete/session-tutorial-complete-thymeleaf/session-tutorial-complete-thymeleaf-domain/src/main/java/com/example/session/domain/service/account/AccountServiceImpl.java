/*
 * Copyright(c) 2024 NTT DATA Group Corporation. Copyright(c) 2013 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package com.example.session.domain.service.account;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;

import com.example.session.domain.model.Account;
import com.example.session.domain.repository.account.AccountRepository;

import jakarta.inject.Inject;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Inject
    AccountRepository accountRespository;

    @Inject
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public Account findOne(String email) {
        Account account = accountRespository.findByEmail(email).orElse(null);
        if (account == null) {
            throw new ResourceNotFoundException("アカウントが存在しません");
        }
        return account;
    }

    @Override
    public void update(Account account) {
        accountRespository.update(account);
    }

    @Override
    public void create(Account account, String password) {
        account.setEncodedPassword(passwordEncoder.encode(password));
        accountRespository.create(account);
    }

}
