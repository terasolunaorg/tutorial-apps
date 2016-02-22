package org.terasoluna.securelogin.domain.repository.passwordhistory;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.terasoluna.securelogin.domain.model.PasswordHistory;

public interface PasswordHistoryRepository {
	int create(PasswordHistory history);

	List<PasswordHistory> findByUseFrom(@Param("username") String username,
			@Param("useFrom") LocalDateTime useFrom);

	List<PasswordHistory> findLatest(
			@Param("username") String username, @Param("limit") int limit);
}
