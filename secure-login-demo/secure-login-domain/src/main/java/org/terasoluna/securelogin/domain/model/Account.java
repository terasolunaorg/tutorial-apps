package org.terasoluna.securelogin.domain.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	private String username;

	private String password;

	private String firstName;

	private String lastName;

	private String email;
	
	private String url;
	
	private String profile;

	private List<Role> roles;

}
