INSERT INTO account(username, password, first_name, last_name, email, url, profile) VALUES ('demo', '{pbkdf2@SpringSecurity_v5_8}9cccc80b1782715d013a4db1bd33306e53fc534b5052f9b5ff7f50062f3d6df8d4f3395639686016e5eb803639ca1d10', 'Taro', 'Yamada', 'taro.yamada@test.ntt.co.jp', 'http://securelogin.sample.jp', 'I am an user.');
INSERT INTO account(username, password, first_name, last_name, email, url, profile) VALUES ('admin', '{pbkdf2@SpringSecurity_v5_8}9cccc80b1782715d013a4db1bd33306e53fc534b5052f9b5ff7f50062f3d6df8d4f3395639686016e5eb803639ca1d10', 'Jiro', 'Sato', 'jiro.sato@test.nttdata.co.jp', 'http://sample.application.jp', 'I am an administrator.');

INSERT INTO account_image(username, body, extension) VALUES ('demo', FILE_READ('classpath:database/testdata/demo.png'), 'png');
INSERT INTO account_image(username, body, extension) VALUES ('admin', FILE_READ('classpath:database/testdata/admin.png'), 'png');

INSERT INTO role (username, role) VALUES ('demo', 'USER');
INSERT INTO role (username, role) VALUES ('admin', 'USER');
INSERT INTO role (username, role) VALUES ('admin', 'ADMIN');
COMMIT;