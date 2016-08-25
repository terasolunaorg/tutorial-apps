package org.terasoluna.securelogin.app.common.validation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileExtensionValidator implements
		ConstraintValidator<FileExtension, MultipartFile> {

	private Set<String> extensions;

	private boolean ignoreCase;

	@Override
	public void initialize(FileExtension constraintAnnotation) {
		this.extensions = new HashSet<String>(
				Arrays.asList(constraintAnnotation.extensions()));
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