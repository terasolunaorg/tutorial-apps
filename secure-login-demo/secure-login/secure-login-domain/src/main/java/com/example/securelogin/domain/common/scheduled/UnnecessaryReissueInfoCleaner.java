/*
 * Copyright(c) 2013 NTT DATA Corporation. Copyright(c) 2013 NTT Corporation.
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
package com.example.securelogin.domain.common.scheduled;

import java.time.LocalDateTime;

import org.terasoluna.gfw.common.time.ClockFactory;

import com.example.securelogin.domain.service.passwordreissue.PasswordReissueService;

import jakarta.inject.Inject;

public class UnnecessaryReissueInfoCleaner {

    @Inject
    ClockFactory dateFactory;

    @Inject
    PasswordReissueService passwordReissueService;

    public void cleanup() {
        passwordReissueService.removeExpired(LocalDateTime.now(dateFactory
                .tick()));
    }

}
