package org.terasoluna.securelogin.app.passwordchange;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.terasoluna.securelogin.app.common.validation.Confirm;
import org.terasoluna.securelogin.app.common.validation.NotReusedPassword;
import org.terasoluna.securelogin.app.common.validation.StrongPassword;
import org.terasoluna.securelogin.app.common.validation.ConfirmOldPassword;

import lombok.Data;

@Data
@Confirm(propertyName = "newPassword")
@StrongPassword(usernamePropertyName = "username", newPasswordPropertyName = "newPassword")
@NotReusedPassword(usernamePropertyName = "username", newPasswordPropertyName = "newPassword")
@ConfirmOldPassword(usernamePropertyName = "username", oldPasswordPropertyName = "oldPassword")
public class PasswordChangeForm implements Serializable{

	private static final long serialVersionUID = 1L;

	@NotNull
	private String username;

	@NotNull
	private String oldPassword;

	@NotNull
	private String newPassword;

	@NotNull
	private String confirmNewPassword;

}
