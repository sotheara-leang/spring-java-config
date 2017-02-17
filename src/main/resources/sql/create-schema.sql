DROP TABLE user IF EXISTS;

CREATE TABLE user (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(15),
  password VARCHAR(50),
  last_name VARCHAR(15),
  first_name VARCHAR(30),
  creator_id BIGINT REFERENCES public.user(id),
  create_date TIMESTAMP,
  updater_id BIGINT REFERENCES public.user(id),
  update_date TIMESTAMP
);