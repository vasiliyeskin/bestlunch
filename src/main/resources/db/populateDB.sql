DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (firstname, lastname, email, password)
VALUES ('User', 'Smith', 'user@yandex.ru', 'password');

INSERT INTO users (firstname, lastname, email, password)
VALUES ('Admin', 'Smith', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100001);