DROP TABLE IF EXISTS successful_authentication;
DROP TABLE IF EXISTS failed_authentication;
DROP TABLE IF EXISTS password_history;
DROP TABLE IF EXISTS failed_password_reissue;
DROP TABLE IF EXISTS password_reissue_info;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS account;

CREATE TABLE account(
	username VARCHAR(128),
	password VARCHAR(60) NOT NULL,
	first_name VARCHAR(128) NOT NULL,
	last_name VARCHAR(128) NOT NULL,
	email VARCHAR(128) NOT NULL,
	CONSTRAINT pk_tbl_account PRIMARY KEY (username)
);

CREATE TABLE role(
	username VARCHAR(128),
	role VARCHAR(10) NOT NULL,
	CONSTRAINT pk_tbl_role PRIMARY KEY (username, role),
	CONSTRAINT fk_tbl_role FOREIGN KEY (username) REFERENCES account(username)
);

CREATE TABLE successful_authentication(
	username VARCHAR(128),
	authentication_timestamp TIMESTAMP,
	CONSTRAINT pk_tbl_sa PRIMARY KEY (username, authentication_timestamp),
	CONSTRAINT fk_tbl_sa FOREIGN KEY (username) REFERENCES account(username)
);

CREATE TABLE failed_authentication(
	username VARCHAR(128),
	authentication_timestamp TIMESTAMP,
	CONSTRAINT pk_tbl_fa PRIMARY KEY (username, authentication_timestamp),
	CONSTRAINT fk_tbl_fa FOREIGN KEY (username) REFERENCES account(username)
);

CREATE INDEX idx_tbl_aasl_t ON successful_authentication (authentication_timestamp);

CREATE INDEX idx_tbl_aafl_t ON failed_authentication (authentication_timestamp);

CREATE TABLE password_history(
	username VARCHAR(128),
	password VARCHAR(128) NOT NULL,
	use_from TIMESTAMP,
	CONSTRAINT pk_tbl_ph PRIMARY KEY (username, use_from),
	CONSTRAINT fk_tbl_ph FOREIGN KEY (username) REFERENCES account(username)
);

CREATE TABLE password_reissue_info(
	username VARCHAR(128) NOT NULL,
	token VARCHAR(128),
	secret VARCHAR(60) NOT NULL,
	expiry_date TIMESTAMP NOT NULL,
	CONSTRAINT pk_tbl_pri PRIMARY KEY (token),
	CONSTRAINT fk_tbl_pri FOREIGN KEY (username) REFERENCES account(username)
);

CREATE TABLE failed_password_reissue(
	token VARCHAR(128),
	attempt_date TIMESTAMP,
	CONSTRAINT pk_tbl_prfl PRIMARY KEY (token, attempt_date),
	CONSTRAINT fk_tbl_prfl FOREIGN KEY (token) REFERENCES password_reissue_info(token)
);

CREATE INDEX idx_tbl_prfl ON failed_password_reissue (token);