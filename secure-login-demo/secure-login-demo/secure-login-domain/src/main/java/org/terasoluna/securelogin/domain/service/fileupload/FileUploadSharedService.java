package org.terasoluna.securelogin.domain.service.fileupload;

import java.time.LocalDateTime;

import org.terasoluna.securelogin.domain.model.TempFile;

public interface FileUploadSharedService {

	String uploadTempFile(TempFile tempFile);
	
	TempFile findTempFile(String id);
	
	void deleteTempFile(String id);
	
	void cleanUp(LocalDateTime deleteTo);
	
}
