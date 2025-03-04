-- generate columns for students table in postgre sql
CREATE TABLE students (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL unique
);