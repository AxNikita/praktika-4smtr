CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  login VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  subscribers VARCHAR(255)
);

INSERT INTO users (login, password) VALUES
  ('NikitaKotov', '123456'),
  ('JaneDoe', '123456');