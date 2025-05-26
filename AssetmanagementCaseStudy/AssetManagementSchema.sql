CREATE DATABASE asset_mgmt;
USE asset_mgmt;

-- Drop Database asset_mgmt;

CREATE TABLE users (
  user_id INT AUTO_INCREMENT PRIMARY KEY,
  role ENUM('employee','admin') NOT NULL,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  email VARCHAR(100) NOT NULL UNIQUE,
  phone VARCHAR(20),
  department VARCHAR(50),
  address VARCHAR(255)	
);

CREATE TABLE asset_category (
  category_id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  description VARCHAR(255)
);

CREATE TABLE assets (
  asset_id INT AUTO_INCREMENT PRIMARY KEY,
  category_id INT NOT NULL,
  serial_number VARCHAR(100) UNIQUE,
  specs VARCHAR(255),
  status ENUM('available','not_available') NOT NULL DEFAULT 'available',
  assigned_to INT DEFAULT NULL,
  FOREIGN KEY (category_id) REFERENCES asset_category(category_id) ON DELETE RESTRICT,
  FOREIGN KEY (assigned_to) REFERENCES users(user_id) ON DELETE SET NULL
);

CREATE TABLE asset_requests (
  request_id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT NOT NULL,
  asset_id INT NOT NULL,
  status ENUM('pending','approved','rejected') NOT NULL DEFAULT 'pending',
  FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
  FOREIGN KEY (asset_id) REFERENCES assets(asset_id) ON DELETE RESTRICT
);

CREATE TABLE assigned_assets (
  assignment_id INT AUTO_INCREMENT PRIMARY KEY,
  asset_id INT NOT NULL,
  user_id INT NOT NULL,
  FOREIGN KEY (asset_id) REFERENCES assets(asset_id) ON DELETE RESTRICT,
  FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE service_requests (
  service_id INT AUTO_INCREMENT PRIMARY KEY,
  asset_id INT NOT NULL,
  user_id INT NOT NULL,
  status ENUM('pending','in_progress','completed') NOT NULL DEFAULT 'pending',
  priority ENUM('low', 'medium', 'high'),
  description VARCHAR(255),
  FOREIGN KEY (asset_id) REFERENCES assets(asset_id) ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE return_requests (
  return_id INT AUTO_INCREMENT PRIMARY KEY,
  asset_id INT NOT NULL,
  user_id INT NOT NULL,
  status ENUM('pending','completed') NOT NULL DEFAULT 'pending',
  FOREIGN KEY (asset_id) REFERENCES assets(asset_id) ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE audit_assignments (
  audit_id INT AUTO_INCREMENT PRIMARY KEY,
  asset_id INT NOT NULL,
  employee_id INT NOT NULL,
  due_date DATE,
  status ENUM('pending','submitted','overdue') NOT NULL DEFAULT 'pending',
  FOREIGN KEY (asset_id) REFERENCES assets(asset_id) ON DELETE CASCADE,
  FOREIGN KEY (employee_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE audit_submissions (
  submission_id INT AUTO_INCREMENT PRIMARY KEY,
  audit_id INT NOT NULL,
  operational_state ENUM('working','needsrepair') NOT NULL DEFAULT 'working',
  submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  comments TEXT NOT NULL,
  FOREIGN KEY (audit_id) REFERENCES audit_assignments(audit_id) ON DELETE CASCADE
);
