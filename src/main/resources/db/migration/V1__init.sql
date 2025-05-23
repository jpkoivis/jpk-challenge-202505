CREATE TABLE message_template (
  id VARCHAR(100) PRIMARY KEY,
  traffic_type VARCHAR(30) NOT NULL,
  create_time TIMESTAMP NOT NULL,
  update_time TIMESTAMP NOT NULL,
  revision INT NOT NULL,
  content TEXT NOT NULL
);

CREATE INDEX traffic_type_idx ON message_template(traffic_type)
