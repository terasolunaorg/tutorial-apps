CREATE TABLE account (
	id VARCHAR(36) PRIMARY KEY,
	email VARCHAR(255) UNIQUE NOT NULL ,
	encoded_password VARCHAR(255),
	name VARCHAR(255),
	birthday DATE,
	zip CHAR(7),
	address VARCHAR(255),
	card_number VARCHAR(16),
	card_expiration_date DATE,
	card_security_code VARCHAR(10)
);

CREATE TABLE category (
  category_id   INT PRIMARY KEY,
  category_name VARCHAR(128)
);

CREATE TABLE goods (
  goods_id    VARCHAR(36) PRIMARY KEY,
  goods_name  VARCHAR(255),
  description VARCHAR(512),
  category_id INT,
  price       INT,
  FOREIGN KEY (category_id) REFERENCES category (category_id)
);

CREATE TABLE orders (
  order_id   VARCHAR(36) PRIMARY KEY,
  email      VARCHAR(255),
  order_date DATE
);

CREATE TABLE order_line (
  order_id VARCHAR(36),
  goods_id VARCHAR(36),
  quantity INT,
  PRIMARY KEY (order_id, goods_id),
  FOREIGN KEY (order_id) REFERENCES orders (order_id),
  FOREIGN KEY (goods_id) REFERENCES goods (goods_id)
);