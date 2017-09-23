DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM RESTAURANTS;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (firstname, lastname, email, password)
VALUES ('User', 'Smith', 'user@yandex.ru', 'password');

INSERT INTO users (firstname, lastname, email, password)
VALUES ('Admin', 'Smith', 'admin@gmail.com', 'admin');

INSERT INTO users (firstname, lastname, email, password)
VALUES ('User2', 'Smith', 'user2@yandex.ru', 'password'),
  ('User3', 'Smith', 'user3@yandex.ru', 'password'),
  ('User4', 'Smith', 'user4@yandex.ru', 'password');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', (SELECT id FROM USERS WHERE email='user@yandex.ru')),
  ('ROLE_ADMIN', (SELECT id FROM USERS WHERE email='admin@gmail.com')),
  ('ROLE_USER', (SELECT id FROM USERS WHERE email='admin@gmail.com')),
  ('ROLE_USER', (SELECT id FROM USERS WHERE email='user2@yandex.ru')),
  ('ROLE_USER', (SELECT id FROM USERS WHERE email='user3@yandex.ru')),
  ('ROLE_USER', (SELECT id FROM USERS WHERE email='user4@yandex.ru'));

INSERT INTO restaurants (title, address, email, site) VALUES
  ('boltzmann''s restaurant', 'Nizhny Novgorod, 5 Gagarina ave.', 'boltzmann@mail.ru','boltzmann.ru' ),
  ('maxvell''s restaurant', 'Nizhny Novgorod, 15 Gagarina ave.', 'maxvell@mail.ru','maxvell.ru' ),
  ('feynman''s restaurant', 'Nizhny Novgorod, 7 Gagarina ave.', 'feynman@mail.ru','feynman.ru' );