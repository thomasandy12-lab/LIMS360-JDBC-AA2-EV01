CREATE DATABASE IF NOT EXISTS lims360
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE lims360;

CREATE TABLE IF NOT EXISTS profiles (
    id CHAR(36) NOT NULL,
    user_id CHAR(36) NOT NULL,
    full_name VARCHAR(150) NOT NULL,
    email VARCHAR(120) NOT NULL,
    department VARCHAR(100) NULL,
    position VARCHAR(100) NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_profiles PRIMARY KEY (id),
    CONSTRAINT uq_profiles_user_id UNIQUE (user_id)
) ENGINE=InnoDB;
