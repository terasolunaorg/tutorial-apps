package org.terasoluna.securelogin.app.unlock;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class UnlockForm implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	private String username;
}
