-- add users table in postgre to have id, name, email, is_active, created_by, created_at
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL unique,
    is_active BOOLEAN,
    created_by BIGINT DEFAULT 1,
    created_at TIMESTAMP WITHOUT TIME ZONE
);