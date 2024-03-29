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
package com.example.session.domain.service.userdetails;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;

import com.example.session.domain.model.Account;
import com.example.session.domain.service.account.AccountService;

import jakarta.inject.Inject;

@Service
public class AccountDetailsService implements UserDetailsService {

    @Inject
    AccountService accountService;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(
            String email) throws UsernameNotFoundException {

        try {
            Account account = accountService.findOne(email);
            return new AccountDetails(account);
        } catch (ResourceNotFoundException e) {
            throw new UsernameNotFoundException("ユーザが存在しません", e);
        }

    }

}
