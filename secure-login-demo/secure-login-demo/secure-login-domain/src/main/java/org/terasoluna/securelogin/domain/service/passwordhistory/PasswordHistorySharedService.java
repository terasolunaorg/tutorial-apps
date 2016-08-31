package org.terasoluna.securelogin.domain.service.passwordhistory;

import java.time.LocalDateTime;
import java.util.List;

import org.terasoluna.securelogin.domain.model.PasswordHistory;

public interface PasswordHistorySharedService {

	int insert(PasswordHistory history);

	List<PasswordHistory> findHistoriesByUseFrom(String username,
			LocalDateTime useFrom);

	List<PasswordHistory> findLatest(String username, int limit);

}
