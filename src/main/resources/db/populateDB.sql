DELETE FROM user_role;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_role (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (user_id, date_time, description, calories)
VALUES (100000, '2023-02-21 07:00', 'breakfast', 500),
       (100000, '2023-02-21 14:00', 'lunch', 1000),
       (100000, '2023-02-21 20:00', 'dinner', 600),
       (100001, '2023-02-21 20:00', 'dinner', 1000),
       (100000, '2023-02-22 07:00', 'breakfast', 500),
       (100000, '2023-02-22 14:00', 'lunch', 1000),
       (100000, '2023-02-22 20:00', 'dinner', 500),
       (100000, '2023-02-23 00:00', 'border line meal', 100);
