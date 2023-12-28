CREATE TABLE IF NOT EXISTS payloads (
    id SERIAL PRIMARY KEY,
    platform VARCHAR(50),
    message TEXT
);

CREATE TABLE IF NOT EXISTS k8s_manifest (
   id SERIAL PRIMARY KEY,
    payload_id INT,
   api_version VARCHAR(20),
    kind VARCHAR(20),
    creation_timestamp TIMESTAMP,
    generate_name VARCHAR(50),
    generation INT,
    name VARCHAR(50),
    namespace VARCHAR(50),
    resource_version VARCHAR(50),
    uid VARCHAR(50),
    FOREIGN KEY (payload_id) REFERENCES payloads(id)
    );
