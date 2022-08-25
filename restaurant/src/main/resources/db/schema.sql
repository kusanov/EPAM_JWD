DROP TABLE restaurant.order_details;
DROP TABLE restaurant.invoices;
DROP TABLE restaurant.orders;
DROP TABLE restaurant.menu;
DROP TABLE restaurant.users;
DROP TABLE restaurant.roles;
DROP TABLE restaurant.categories;
DROP TABLE restaurant.payments;
DROP TABLE restaurant.statuses;

CREATE TABLE IF NOT EXISTS restaurant.roles (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_role VARCHAR(30)
) engine=InnoDB;

INSERT IGNORE INTO restaurant.roles VALUES (1, 'admin');
INSERT IGNORE INTO restaurant.roles VALUES (2, 'visitor');

CREATE TABLE IF NOT EXISTS restaurant.payments (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  payment VARCHAR(30)
) engine=InnoDB;

INSERT IGNORE INTO restaurant.payments VALUES (1,'unpaid');
INSERT IGNORE INTO restaurant.payments VALUES (2,'paid_by_cash');
INSERT IGNORE INTO restaurant.payments VALUES (3,'paid_by_card');

CREATE TABLE IF NOT EXISTS restaurant.categories (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  category VARCHAR(30)
) engine=InnoDB;

INSERT IGNORE INTO restaurant.categories VALUES (1, 'soups');
INSERT IGNORE INTO restaurant.categories VALUES (2, 'salads');
INSERT IGNORE INTO restaurant.categories VALUES (3, 'drinks');

CREATE TABLE IF NOT EXISTS restaurant.statuses (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  order_status VARCHAR(30)
) engine=InnoDB;

INSERT IGNORE INTO restaurant.statuses VALUES (1,'new order');
INSERT IGNORE INTO restaurant.statuses VALUES (2,'ready');

CREATE TABLE IF NOT EXISTS restaurant.users (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  login VARCHAR(30),
  user_password VARCHAR(30),
  role_id INT UNSIGNED,
  user_status tinyint,
  INDEX(login),
  FOREIGN KEY (role_id) REFERENCES roles(id)
) engine=InnoDB;

INSERT IGNORE INTO restaurant.users VALUES (1, 'admin', 'admin', 1, true);
INSERT IGNORE INTO restaurant.users VALUES (2, 'visitor1', 'password1', 2, true);
INSERT IGNORE INTO restaurant.users VALUES (3, 'visitor2', 'password2', 2, true);

CREATE TABLE IF NOT EXISTS restaurant.menu (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  category_id INT UNSIGNED,
  dish VARCHAR(50),
  dish_description VARCHAR(100),
  price DOUBLE,
  FOREIGN KEY (category_id) REFERENCES categories(id)
) engine=InnoDB;

INSERT IGNORE INTO restaurant.menu VALUES (1, 1,'soup', 'nice soup', 2);
INSERT IGNORE INTO restaurant.menu VALUES (2, 1,'soup2', 'nice soup', 2.5);
INSERT IGNORE INTO restaurant.menu VALUES (3, 2,'salad', 'nice salad', 3.5);
INSERT IGNORE INTO restaurant.menu VALUES (4, 3,'beer', 'good beer', 1.5);
INSERT IGNORE INTO restaurant.menu VALUES (5, 3,'red wine', 'fine red wine', 1.2);
INSERT IGNORE INTO restaurant.menu VALUES (6, 3,'white wine', 'fine white wine', 1.4);

CREATE TABLE IF NOT EXISTS restaurant.orders (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_id INT UNSIGNED,
  visit_date DATE,
  status_id INT UNSIGNED,
 cost DOUBLE,
 FOREIGN KEY (user_id) REFERENCES users(id),
 FOREIGN KEY (status_id) REFERENCES statuses(id)
) engine=InnoDB;

INSERT IGNORE INTO restaurant.orders VALUES (1, 1, '2020-05-30', 2, 9.0);
INSERT IGNORE INTO restaurant.orders VALUES (2, 2, '2020-05-30', 2, 5.0);
INSERT IGNORE INTO restaurant.orders VALUES (3, 1, '2020-05-30', 1, 2.5);


CREATE TABLE IF NOT EXISTS restaurant.order_details (
  order_id INT UNSIGNED,
  menu_id INT UNSIGNED,
  quantity INT,
  cost double,
  FOREIGN KEY (order_id) REFERENCES orders(id),
  FOREIGN KEY (menu_id) REFERENCES menu(id),
UNIQUE (order_id,menu_id)
) engine=InnoDB;

INSERT IGNORE INTO restaurant.order_details VALUES (1, 1, 2, 2);
INSERT IGNORE INTO restaurant.order_details VALUES (1, 2, 2, 2.5);
INSERT IGNORE INTO restaurant.order_details VALUES (2, 2, 1, 2.5);
INSERT IGNORE INTO restaurant.order_details VALUES (3, 2, 1, 2.5);

CREATE TABLE IF NOT EXISTS restaurant.invoices (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  order_id INT UNSIGNED,
  user_id INT UNSIGNED,
  cost DOUBLE,
  payment_id INT UNSIGNED,
  FOREIGN KEY (order_id) REFERENCES orders(id),
 FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (payment_id) REFERENCES payments(id)
) engine=InnoDB;

INSERT IGNORE INTO restaurant.invoices VALUES (1, 1, 1, 9.0, 1);
INSERT IGNORE INTO restaurant.invoices VALUES (2, 2, 2, 5.0, 1);