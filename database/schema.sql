
CREATE TABLE divisions (
    division_id INT AUTO_INCREMENT PRIMARY KEY,
    division_name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE job_titles (
    job_title_id INT AUTO_INCREMENT PRIMARY KEY,
    job_title_name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE employees (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,

    first_name VARCHAR(50),
    last_name VARCHAR(50),

    dob DATE,
    ssn VARCHAR(11) UNIQUE,

    salary DECIMAL(10,2),
    hire_date DATE,

    division_id INT,
    job_title_id INT,

    FOREIGN KEY (division_id) REFERENCES divisions(division_id),
    FOREIGN KEY (job_title_id) REFERENCES job_titles(job_title_id)
);

CREATE TABLE roles(
	role_id INT AUTO_INCREMENT PRIMARY KEY,
	
	role_name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE users (
	user_id INT AUTO_INCREMENT PRIMARY KEY,
	employee_id INT UNIQUE,
	role_id INT NOT NULL,
	
	username VARCHAR(50) UNIQUE NOT NULL,
	password_hash VARCHAR(255) NOT NULL,
	is_active BOOLEAN NOT NULL DEFAULT TRUE,
	
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_login TIMESTAMP NULL,
	
	FOREIGN KEY (employee_id) REFERENCES employees(employee_id),
	FOREIGN KEY (role_id) REFERENCES roles(role_id)
);

