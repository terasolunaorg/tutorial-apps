package org.terasoluna.securelogin.domain.service.passwordhistory;

import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.securelogin.domain.model.PasswordHistory;
import org.terasoluna.securelogin.domain.repository.passwordhistory.PasswordHistoryRepository;

@Service
@Transactional
public class PasswordHistorySharedServiceImpl implements
		PasswordHistorySharedService {

	@Inject
	PasswordHistoryRepository passwordHistoryRepository;

	public int insert(PasswordHistory history) {
		return passwordHistoryRepository.create(history);
	}

	@Transactional(readOnly = true)
	public List<PasswordHistory> findHistoriesByUseFrom(String username,
			LocalDateTime useFrom) {
		return passwordHistoryRepository.findByUseFrom(username, useFrom);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PasswordHistory> findLatest(String username, int limit) {
		return passwordHistoryRepository.findLatest(username, limit);
	}

}
