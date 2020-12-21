DROP TABLE IF EXISTS task;

CREATE TABLE task (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

INSERT INTO task(name) VALUES
  ('task 1'),
  ('task 2'),
  ('task 3');
