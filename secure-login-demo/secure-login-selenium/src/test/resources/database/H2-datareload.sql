SET REFERENTIAL_INTEGRITY FALSE;
TRUNCATE TABLE successful_authentication;
TRUNCATE TABLE failed_authentication;
TRUNCATE TABLE password_history;
TRUNCATE TABLE failed_password_reissue;
TRUNCATE TABLE password_reissue_info;
TRUNCATE TABLE account_image;
TRUNCATE TABLE role;
TRUNCATE TABLE account;

INSERT INTO account(username, password, first_name, last_name, email, url, profile) VALUES ('demo', '$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK', 'Taro', 'Yamada', 'taro.yamada@test.ntt.co.jp', 'http://securelogin.sample.jp', 'I am an user.');
INSERT INTO account(username, password, first_name, last_name, email, url, profile) VALUES ('admin', '$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK', 'Jiro', 'Sato', 'jiro.sato@test.nttdata.co.jp', 'http://sample.application.jp', 'I am an administrator.');

INSERT INTO account_image(username, body, extension) VALUES ('demo', FILE_READ('classpath:database/testdata/demo.png'), 'png');
INSERT INTO account_image(username, body, extension) VALUES ('admin', FILE_READ('classpath:database/testdata/admin.png'), 'png');

INSERT INTO role (username, role) VALUES ('demo', 'USER');
INSERT INTO role (username, role) VALUES ('admin', 'USER');
INSERT INTO role (username, role) VALUES ('admin', 'ADMIN');
COMMIT;