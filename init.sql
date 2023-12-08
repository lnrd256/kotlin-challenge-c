CREATE TABLE IF NOT EXISTS records (
   id SERIAL PRIMARY KEY,
   api_version VARCHAR(20),
    kind VARCHAR(20),
    creation_timestamp TIMESTAMP,
    generate_name VARCHAR(50),
    generation INT,
    name VARCHAR(50),
    namespace VARCHAR(50),
    resource_version VARCHAR(50),
    uid VARCHAR(50),
    message TEXT
    );

CREATE TABLE IF NOT EXISTS status (
    id SERIAL PRIMARY KEY,
    record_id INT,
    available_replicas INT,
    collision_count INT,
    current_replicas INT,
    current_revision varchar(100),
    fully_labeled_replicas INT,
    observed_generation INT,
    ready_replicas INT,
    replicas INT,
    updated_replicas INT,
    FOREIGN KEY (record_id) REFERENCES records(id)
    );

CREATE TABLE IF NOT EXISTS containers (
    id SERIAL PRIMARY KEY,
    record_id INT,
    image   varchar(100),
    image_pull_policy varchar(50),
    cpu_limit varchar(20),
    memory_limit varchar(20),
    cpu_request varchar(20),
    memory_request varchar(20),
    FOREIGN KEY (record_id) REFERENCES records(id)
    );