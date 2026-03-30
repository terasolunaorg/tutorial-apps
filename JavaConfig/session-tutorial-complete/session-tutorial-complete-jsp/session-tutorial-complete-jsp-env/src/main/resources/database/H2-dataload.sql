-- 初期アカウント
INSERT into account values('gaggagargrgasg','a@b.com','{pbkdf2}9cccc80b1782715d013a4db1bd33306e53fc534b5052f9b5ff7f50062f3d6df8d4f3395639686016e5eb803639ca1d10','xxx','2015-08-01','1111111','Tokyo','1111111111111111','2015-08-01','111');

--　カテゴリ
INSERT INTO category (category_id, category_name) VALUES (1, 'book');
INSERT INTO category (category_id, category_name) VALUES (2, 'music');
INSERT INTO category (category_id, category_name) VALUES (3, 'appliance');
INSERT INTO category (category_id, category_name) VALUES (4, 'PC');

-- 本
INSERT INTO goods (goods_id, goods_name, description, category_id, price)
VALUES ('366cf3a4-68c5-4dae-a557-673769f76840', 'Kokoro', 'Souseki Natsume wrote this book', 1, 900);
INSERT INTO goods (goods_id, goods_name, description, category_id, price)
VALUES ('366cf3a4-68c5-4dae-a557-673769f76841', '〔Ame ni mo Makezu〕', 'Kenji Miyazawa worte this book', 1, 800);
INSERT INTO goods (goods_id, goods_name, description, category_id, price)
VALUES ('366cf3a4-68c5-4dae-a557-673769f76842', 'Run, Melos!', 'Osamu Dazai wrote this book', 1, 880);
INSERT INTO goods (goods_id, goods_name, description, category_id, price)
VALUES ('366cf3a4-68c5-4dae-a557-673769f76843', 'I am a cat', 'Souseki Natsume wrote this book', 1, 900);
INSERT INTO goods (goods_id, goods_name, description, category_id, price)
VALUES ('366cf3a4-68c5-4dae-a557-673769f76844', 'No Longer Human', 'Osamu Dazai wrote this book', 1, 880);

-- 音楽

INSERT INTO goods (goods_id, goods_name, description, category_id, price)
VALUES ('366cf3a4-68c5-4dae-a557-673769f76850', 'Symphony No. 5 in C minor (Fate)', 'Beethoven composed this music', 2, 1200);
INSERT INTO goods (goods_id, goods_name, description, category_id, price)
VALUES ('366cf3a4-68c5-4dae-a557-673769f76851', 'Eine kleine Nachtmusik', 'Mozart composed this music', 2, 1000);
INSERT INTO goods (goods_id, goods_name, description, category_id, price)
VALUES ('366cf3a4-68c5-4dae-a557-673769f76852', 'Swan Lake', 'Tchaikovsky composed this music', 2, 900);
INSERT INTO goods (goods_id, goods_name, description, category_id, price)
VALUES ('366cf3a4-68c5-4dae-a557-673769f76853', 'Nocturnes', 'Chopin composed this music', 2, 1000);
INSERT INTO goods (goods_id, goods_name, description, category_id, price)
VALUES ('366cf3a4-68c5-4dae-a557-673769f76854', 'Air on the G String', 'Bach composed this music', 2, 800);

-- 家電

INSERT INTO goods (goods_id, goods_name, description, category_id, price)
VALUES ('366cf3a4-68c5-4dae-a557-673769f76860', 'Refrigerator', 'This refrigerator has a small power consumption', 3, 108000);
INSERT INTO goods (goods_id, goods_name, description, category_id, price)
VALUES ('366cf3a4-68c5-4dae-a557-673769f76861', 'Washing machine', 'This washing machine remove any stains', 3, 216000);
INSERT INTO goods (goods_id, goods_name, description, category_id, price)
VALUES ('366cf3a4-68c5-4dae-a557-673769f76862', 'Microwave', 'This microwave has over 10 options for cooking', 3, 10800);
INSERT INTO goods (goods_id, goods_name, description, category_id, price)
VALUES ('366cf3a4-68c5-4dae-a557-673769f76863', 'TV', 'This TV has a large screen and high image quality', 3, 10800);
INSERT INTO goods (goods_id, goods_name, description, category_id, price)
VALUES ('366cf3a4-68c5-4dae-a557-673769f76864', 'Digital Camera', 'This camera has shake correction device', 3, 54000);

-- パソコン

INSERT INTO goods (goods_id, goods_name, description, category_id, price)
VALUES ('366cf3a4-68c5-4dae-a557-673769f76870', 'Desktop PC', 'This Desktop PC has the latest CPU', 4, 216000);
INSERT INTO goods (goods_id, goods_name, description, category_id, price)
VALUES ('366cf3a4-68c5-4dae-a557-673769f76871', 'Laptop PC', 'This Laptop PC is convenient to carry', 4, 162000);
INSERT INTO goods (goods_id, goods_name, description, category_id, price)
VALUES ('366cf3a4-68c5-4dae-a557-673769f76872', 'Tablet', 'This tablet has an option of burglary insurance', 4, 108000);
INSERT INTO goods (goods_id, goods_name, description, category_id, price)
VALUES ('366cf3a4-68c5-4dae-a557-673769f76873', 'Integrated PC', 'This integreated PC has large screen and high performance', 4, 183600);

commit;