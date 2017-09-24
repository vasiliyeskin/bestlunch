DELETE FROM user_roles;
DELETE FROM dishesofday;
DELETE FROM DISHES;
DELETE FROM users;
DELETE FROM RESTAURANTS;
DELETE FROM VOTES;
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

INSERT INTO restaurants (title, address, email, site, REGISTERED) VALUES
  ('boltzmann''s restaurant', 'Nizhny Novgorod, 5 Gagarina ave.', 'boltzmann@mail.ru', 'boltzmann.ru', now()),
  ('maxwell''s restaurant', 'Nizhny Novgorod, 15 Gagarina ave.', 'maxwell@mail.ru', 'maxwell.ru', now()),
  ('feynman''s restaurant', 'Nizhny Novgorod, 7 Gagarina ave.', 'feynman@mail.ru', 'feynman.ru', now());

INSERT INTO dishes (name, price, registered, restaurant_id) VALUES
  ('lobster', 10000, now(), (SELECT id
                             FROM RESTAURANTS
                             WHERE email = 'maxwell@mail.ru')),
  ('burger', 2000, now(), (SELECT id
                           FROM RESTAURANTS
                           WHERE email = 'maxwell@mail.ru')),
  ('borsh', 50000, now(), (SELECT id
                           FROM RESTAURANTS
                           WHERE email = 'maxwell@mail.ru')),
  ('lobster', 55000, now(), (SELECT id
                             FROM RESTAURANTS
                             WHERE email = 'feynman@mail.ru')),
  ('burger', 2500, now(), (SELECT id
                           FROM RESTAURANTS
                           WHERE email = 'feynman@mail.ru')),
  ('borsh', 30000, now(), (SELECT id
                           FROM RESTAURANTS
                           WHERE email = 'boltzmann@mail.ru'));

INSERT INTO DISHESOFDAY (datelunch, DISH_ID) VALUES
  (now(),(SELECT id FROM DISHES WHERE name = 'lobster' and RESTAURANT_ID=(SELECT id FROM RESTAURANTS WHERE email = 'maxwell@mail.ru'))),
  (now(),(SELECT id FROM DISHES WHERE name = 'burger' and RESTAURANT_ID=(SELECT id FROM RESTAURANTS WHERE email = 'maxwell@mail.ru'))),
  (now(),(SELECT id FROM DISHES WHERE name = 'borsh' and RESTAURANT_ID=(SELECT id FROM RESTAURANTS WHERE email = 'maxwell@mail.ru'))),
  (now(),(SELECT id FROM DISHES WHERE name = 'lobster' and RESTAURANT_ID=(SELECT id FROM RESTAURANTS WHERE email = 'feynman@mail.ru'))),
  (now(),(SELECT id FROM DISHES WHERE name = 'burger' and RESTAURANT_ID=(SELECT id FROM RESTAURANTS WHERE email = 'feynman@mail.ru'))),
  (now(),(SELECT id FROM DISHES WHERE name = 'borsh' and RESTAURANT_ID=(SELECT id FROM RESTAURANTS WHERE email = 'boltzmann@mail.ru')));

INSERT INTO votes(DATELUNCH, USER_ID, RESTAURANT_ID) VALUES
  (now(),(SELECT id FROM USERS WHERE email = 'user@yandex.ru'),(SELECT id FROM RESTAURANTS WHERE email = 'maxwell@mail.ru')),
  (now(),(SELECT id FROM USERS WHERE email = 'admin@gmail.com'),(SELECT id FROM RESTAURANTS WHERE email = 'maxwell@mail.ru')),
  (now(),(SELECT id FROM USERS WHERE email = 'user2@yandex.ru'),(SELECT id FROM RESTAURANTS WHERE email = 'feynman@mail.ru')),
  (now(),(SELECT id FROM USERS WHERE email = 'user3@yandex.ru'),(SELECT id FROM RESTAURANTS WHERE email = 'feynman@mail.ru')),
  (now(),(SELECT id FROM USERS WHERE email = 'user4@yandex.ru'),(SELECT id FROM RESTAURANTS WHERE email = 'boltzmann@mail.ru'));