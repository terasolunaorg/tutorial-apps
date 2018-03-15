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
package com.example.securelogin.app.common.filter;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import com.example.securelogin.app.common.filter.exception.InvalidCharacterException;

import com.google.common.primitives.Chars;

public class InputValidationFilter extends OncePerRequestFilter {

	private final List<Character> prohibitedChars;

	private final List<Character> prohibitedCharsForFileName;

	public InputValidationFilter(char[] prohibitedChars,
			char[] prohibitedCharsForFileName) {
		this.prohibitedChars = Chars.asList(prohibitedChars);
		this.prohibitedCharsForFileName = Chars
				.asList(prohibitedCharsForFileName);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (request != null) {
			validateRequestParams(request);

			if (request instanceof MultipartRequest) {
				validateFileNames((MultipartRequest) request);
			}
		}
		filterChain.doFilter(request, response);
	}

	private void validateRequestParams(HttpServletRequest request) {
		Map<String, String[]> params = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : params.entrySet()) {
			validate(entry.getKey(), prohibitedChars);
			for (String value : entry.getValue()) {
				validate(value, prohibitedChars);
			}
		}
	}

	private void validateFileNames(MultipartRequest request) {
		for (Map.Entry<String, MultipartFile> entry : request.getFileMap()
				.entrySet()) {
			String filename = new File(entry.getValue().getOriginalFilename())
					.getName();
			validate(filename, prohibitedCharsForFileName);
		}
	}

	private void validate(String target, List<Character> prohibited) {
		if (StringUtils.hasLength(target)) {
			List<Character> chars = Chars.asList(target.toCharArray());
			for (Character prohibitedChar : prohibited) {
				if (chars.contains(prohibitedChar)) {
					throw new InvalidCharacterException(
							"The request contains prohibited charcter.");
				}
			}
		}
	}

}
