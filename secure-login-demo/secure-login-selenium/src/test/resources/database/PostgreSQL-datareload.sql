TRUNCATE TABLE account, role, password_reissue_info, successful_authentication, failed_authentication, password_history, failed_password_reissue;

INSERT INTO account(username, password, first_name, last_name, email) VALUES('demo', '$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK', 'Taro', 'Yamada', 'taro.yamada@test.com');
INSERT INTO account(username, password, first_name, last_name, email) VALUES('admin', '$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK', 'Jiro', 'Sato', 'jiro.sato@mail.jp');

INSERT INTO role (username, role) VALUES ('demo', 'USER');
INSERT INTO role (username, role) VALUES ('admin', 'USER');
INSERT INTO role (username, role) VALUES ('admin', 'ADMIN');
COMMIT;