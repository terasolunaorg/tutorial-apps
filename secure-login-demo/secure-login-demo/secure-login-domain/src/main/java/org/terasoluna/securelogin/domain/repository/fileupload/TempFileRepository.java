package org.terasoluna.securelogin.domain.repository.fileupload;

import java.time.LocalDateTime;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.terasoluna.securelogin.domain.model.TempFile;

@Repository
public interface TempFileRepository {

	TempFile findOne(@Param("id") String id);
	
	boolean create(TempFile tempFile);
	
	int delete(@Param("id") String id);
	
	int deleteByToDate(@Param("date") LocalDateTime date);
	
}
