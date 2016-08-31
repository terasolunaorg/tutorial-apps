package org.terasoluna.securelogin.app.passwordreissue;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class CreateReissueInfoForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@NotEmpty
	private String username;
}
