package org.terasoluna.securelogin.app.passwordreissue;

import java.io.Serializable;

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

	private String username;

	private String token;

	private String secret;

	private String newPassword;

	private String confirmNewPassword;
}
