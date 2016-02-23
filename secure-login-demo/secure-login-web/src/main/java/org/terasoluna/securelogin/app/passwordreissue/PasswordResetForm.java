package org.terasoluna.securelogin.app.passwordreissue;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.terasoluna.securelogin.app.common.validation.NotReusedPassword;
import org.terasoluna.securelogin.app.common.validation.StrongPassword;
import org.terasoluna.securelogin.app.common.validation.Confirm;

import lombok.Data;

@Data
@Confirm(propertyName = "newPassword")
@StrongPassword(usernamePropertyName = "username", newPasswordPropertyName = "newPassword")
@NotReusedPassword(usernamePropertyName = "username", newPasswordPropertyName = "newPassword")
public class PasswordResetForm implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	private String username;

	@NotNull
	private String token;

	@NotNull
	private String secret;

	@NotNull
	private String newPassword;

	@NotNull
	private String confirmNewPassword;
}
