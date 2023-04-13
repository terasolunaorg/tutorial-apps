/*
 * Copyright(c) 2013 NTT DATA Corporation. Copyright(c) 2013 NTT Corporation.
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
package com.example.securelogin.app.common.validation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FileExtensionValidator implements
                                    ConstraintValidator<FileExtension, MultipartFile> {

    private Set<String> extensions;

    private boolean ignoreCase;

    @Override
    public void initialize(FileExtension constraintAnnotation) {
        this.extensions = new HashSet<String>(Arrays.asList(constraintAnnotation
                .extensions()));
        this.ignoreCase = constraintAnnotation.ignoreCase();
    }

    @Override
    public boolean isValid(MultipartFile value,
            ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        String fileNameExtension = StringUtils.getFilenameExtension(value
                .getOriginalFilename());
        if (!StringUtils.hasLength(fileNameExtension)) {
            return false;
        }

        for (String extension : extensions) {
            if (fileNameExtension.equals(extension) || ignoreCase
                    && fileNameExtension.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }

}
