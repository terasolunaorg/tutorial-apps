package org.terasoluna.securelogin.domain.service.fileupload;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.date.ClassicDateFactory;
import org.terasoluna.securelogin.domain.model.TempFile;
import org.terasoluna.securelogin.domain.repository.fileupload.TempFileRepository;

@Service
@Transactional
public class FileUploadSharedServiceImpl implements FileUploadSharedService {

	@Inject
	TempFileRepository tempFileRepository;
	
	@Inject
	ClassicDateFactory dateFactory;
	
	@Override
	public String uploadTempFile(TempFile tempFile) {
		tempFile.setId(UUID.randomUUID().toString());
		tempFile.setUploadedDate(dateFactory.newTimestamp().toLocalDateTime());
		tempFileRepository.create(tempFile);
		return tempFile.getId();
	}

	@Override
	public TempFile findTempFile(String id) {
		return tempFileRepository.findOne(id);
	}

	@Override
	public void deleteTempFile(String id) {
		tempFileRepository.delete(id);
	};
	
	@Override
	public void cleanUp(LocalDateTime deleteTo) {
		tempFileRepository.deleteByToDate(deleteTo);
	}

}
