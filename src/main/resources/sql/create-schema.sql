DROP TABLE user IF EXISTS;

CREATE TABLE user (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(15),
  password VARCHAR(50),
  lastname VARCHAR(15),
  firstname VARCHAR(30)
);