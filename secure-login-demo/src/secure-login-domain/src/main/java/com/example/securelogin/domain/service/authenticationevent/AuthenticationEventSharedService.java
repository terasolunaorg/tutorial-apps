/*
 * Copyright (C) 2013-2018 NTT DATA Corporation
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
package com.example.securelogin.domain.service.authenticationevent;

import java.util.List;

import com.example.securelogin.domain.model.FailedAuthentication;
import com.example.securelogin.domain.model.SuccessfulAuthentication;

public interface AuthenticationEventSharedService {

    List<SuccessfulAuthentication> findLatestSuccessEvents(
            String username, int count);

    List<FailedAuthentication> findLatestFailureEvents(
            String username, int count);

    void authenticationSuccess(String username);

    void authenticationFailure(String username);

    int deleteFailureEventByUsername(String username);
}
