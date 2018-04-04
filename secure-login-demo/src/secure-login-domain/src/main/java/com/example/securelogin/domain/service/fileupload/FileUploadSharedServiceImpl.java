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
package com.example.securelogin.domain.service.fileupload;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.date.ClassicDateFactory;
import com.example.securelogin.domain.model.TempFile;
import com.example.securelogin.domain.repository.fileupload.TempFileRepository;

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
