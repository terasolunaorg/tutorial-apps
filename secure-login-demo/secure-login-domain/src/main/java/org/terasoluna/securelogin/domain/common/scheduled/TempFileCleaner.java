package org.terasoluna.securelogin.domain.common.scheduled;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.terasoluna.gfw.common.date.ClassicDateFactory;
import org.terasoluna.securelogin.domain.service.fileupload.FileUploadSharedService;

public class TempFileCleaner {

	@Inject
	ClassicDateFactory dateFactory;
	
	@Inject
	FileUploadSharedService fileUploadSharedService;
	
	@Value("${security.tempFileCleanupSeconds}")
	int cleanupInterval;
	
	public void cleanup() {
		fileUploadSharedService.cleanUp(dateFactory.newTimestamp().toLocalDateTime().minusSeconds(cleanupInterval));
	}
}
