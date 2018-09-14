INSERT INTO account(username, password, first_name, last_name, email, url, profile) VALUES ('demo', '{pbkdf2}1dd84f42a7a9a173f8f806d736d34939bed6a36e2948e8bfe88801ee5e6e61b815efc389d03165a4', 'Taro', 'Yamada', 'taro.yamada@test.ntt.co.jp', 'http://securelogin.sample.jp', 'I am an user.');
INSERT INTO account(username, password, first_name, last_name, email, url, profile) VALUES ('admin', '{pbkdf2}1dd84f42a7a9a173f8f806d736d34939bed6a36e2948e8bfe88801ee5e6e61b815efc389d03165a4', 'Jiro', 'Sato', 'jiro.sato@test.nttdata.co.jp', 'http://sample.application.jp', 'I am an administrator.');

INSERT INTO account_image(username, body, extension) VALUES ('demo', FILE_READ('classpath:database/testdata/demo.png'), 'png');
INSERT INTO account_image(username, body, extension) VALUES ('admin', FILE_READ('classpath:database/testdata/admin.png'), 'png');

INSERT INTO role (username, role) VALUES ('demo', 'USER');
INSERT INTO role (username, role) VALUES ('admin', 'USER');
INSERT INTO role (username, role) VALUES ('admin', 'ADMIN');
COMMIT;