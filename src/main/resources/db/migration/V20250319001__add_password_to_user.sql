-- add password to users table
ALTER TABLE users ADD COLUMN password VARCHAR(255) not null;