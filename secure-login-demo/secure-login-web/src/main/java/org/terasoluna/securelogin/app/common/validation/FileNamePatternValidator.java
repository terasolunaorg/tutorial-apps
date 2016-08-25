package org.terasoluna.securelogin.app.common.validation;

import java.io.File;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class FileNamePatternValidator implements
		ConstraintValidator<FileNamePattern, MultipartFile> {

	private Pattern pattern;

	@Override
	public void initialize(FileNamePattern constraintAnnotation) {
		this.pattern = Pattern.compile(constraintAnnotation.pattern());
	}

	@Override
	public boolean isValid(MultipartFile value,
			ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		String filename = new File(value.getOriginalFilename()).getName();
		return pattern.matcher(filename).matches();
	}

}
