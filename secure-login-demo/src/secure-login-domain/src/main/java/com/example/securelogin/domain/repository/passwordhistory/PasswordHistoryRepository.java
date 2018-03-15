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
package com.example.securelogin.domain.repository.passwordhistory;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.example.securelogin.domain.model.PasswordHistory;

public interface PasswordHistoryRepository {
	int create(PasswordHistory history);

	List<PasswordHistory> findByUseFrom(@Param("username") String username,
			@Param("useFrom") LocalDateTime useFrom);

	List<PasswordHistory> findLatest(@Param("username") String username,
			@Param("limit") int limit);
}
