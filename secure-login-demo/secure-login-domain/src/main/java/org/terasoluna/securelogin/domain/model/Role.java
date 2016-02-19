package org.terasoluna.securelogin.domain.model;

public enum Role {
	ADMIN("administrator"), USER("user");

	private final String roleLabel;

	private Role(String roleLabel) {
		this.roleLabel = roleLabel;
	}

	public String getRoleLabel() {
		return roleLabel;
	}

	public String getRoleValue() {
		return this.name();
	}

}
