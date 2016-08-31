package org.terasoluna.securelogin.domain.model;

import java.io.InputStream;
import java.io.Serializable;

import lombok.Data;

@Data
public class AccountImage implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String username;
	
	private InputStream body;
	
	private String extension;
}
