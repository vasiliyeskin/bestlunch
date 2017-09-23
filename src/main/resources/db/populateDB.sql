DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM RESTAURANTS;
DELETE FROM DISHES;
ALTER SEQUENCE global_seq
RESTART WITH 100000;
ALTER SEQUENCE rest_seq
RESTART WITH 200000;
ALTER SEQUENCE dish_seq
RESTART WITH 300000;

INSERT INTO users (firstname, lastname, email, password)
VALUES ('User', 'Smith', 'user@yandex.ru', 'password');

INSERT INTO users (firstname, lastname, email, password)
VALUES ('Admin', 'Smith', 'admin@gmail.com', 'admin');

INSERT INTO users (firstname, lastname, email, password)
VALUES ('User2', 'Smith', 'user2@yandex.ru', 'password'),
  ('User3', 'Smith', 'user3@yandex.ru', 'password'),
  ('User4', 'Smith', 'user4@yandex.ru', 'password');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', (SELECT id
                 FROM USERS
                 WHERE email = 'user@yandex.ru')),
  ('ROLE_ADMIN', (SELECT id
                  FROM USERS
                  WHERE email = 'admin@gmail.com')),
  ('ROLE_USER', (SELECT id
                 FROM USERS
                 WHERE email = 'admin@gmail.com')),
  ('ROLE_USER', (SELECT id
                 FROM USERS
                 WHERE email = 'user2@yandex.ru')),
  ('ROLE_USER', (SELECT id
                 FROM USERS
                 WHERE email = 'user3@yandex.ru')),
  ('ROLE_USER', (SELECT id
                 FROM USERS
                 WHERE email = 'user4@yandex.ru'));

INSERT INTO restaurants (title, address, email, site) VALUES
  ('boltzmann''s restaurant', 'Nizhny Novgorod, 5 Gagarina ave.', 'boltzmann@mail.ru', 'boltzmann.ru'),
  ('maxvell''s restaurant', 'Nizhny Novgorod, 15 Gagarina ave.', 'maxvell@mail.ru', 'maxvell.ru'),
  ('feynman''s restaurant', 'Nizhny Novgorod, 7 Gagarina ave.', 'feynman@mail.ru', 'feynman.ru');

INSERT INTO dishes (name, datelunch, price, restaurant_id) VALUES
  ('lobstar', now(), 10000, (SELECT id
                             FROM RESTAURANTS
                             WHERE email = 'maxvell@mail.ru')),
  ('burger', now(), 2000, (SELECT id
                           FROM RESTAURANTS
                           WHERE email = 'maxvell@mail.ru')),
  ('borsh', now(), 50000, (SELECT id
                             FROM RESTAURANTS
                             WHERE email = 'maxvell@mail.ru')),
  ('lobstar', now(), 55000, (SELECT id
                             FROM RESTAURANTS
                             WHERE email = 'feynman@mail.ru')),
  ('burger', now(), 2500, (SELECT id
                           FROM RESTAURANTS
                           WHERE email = 'feynman@mail.ru')),
  ('borsh', now(), 30000, (SELECT id
                           FROM RESTAURANTS
                           WHERE email = 'boltzmann@mail.ru'));