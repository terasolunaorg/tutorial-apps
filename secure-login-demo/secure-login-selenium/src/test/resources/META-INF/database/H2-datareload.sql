SET REFERENTIAL_INTEGRITY FALSE;
TRUNCATE TABLE successful_authentication;
TRUNCATE TABLE failed_authentication;
TRUNCATE TABLE password_history;
TRUNCATE TABLE failed_password_reissue;
TRUNCATE TABLE password_reissue_info;
TRUNCATE TABLE role;
TRUNCATE TABLE account;

INSERT INTO account(username, password, first_name, last_name, email) VALUES('demo', '$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK', 'Taro', 'Yamada', 'taro.yamada@test.com');
INSERT INTO account(username, password, first_name, last_name, email) VALUES('admin', '$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK', 'Jiro', 'Sato', 'jiro.sato@mail.jp');

INSERT INTO role (username, role) VALUES ('demo', 'USER');
INSERT INTO role (username, role) VALUES ('admin', 'USER');
INSERT INTO role (username, role) VALUES ('admin', 'ADMIN');
COMMIT;