INSERT INTO roles(role_name)
VALUES('HR_ADMIN'),
('EMPLOYEE');

INSERT INTO divisions(division_name)
VALUES('Marketing'),
('Front-end'),
('Back-end'),
('DevOps');

INSERT INTO job_titles (job_title_name)
VALUES('Software Engineer'),
('Back-end Developer'),
('Team Lead');

INSERT INTO employees (first_name, last_name, dob, ssn, salary, hire_date, division_id, job_title_id)
VALUES
('John', 'Smith', '1995-06-15', '123-45-6789', 85000, '2022-03-01', 3, 2),
('Emma', 'Johnson', '1998-09-21', '234-56-7890', 78000, '2023-01-10', 2, 1),
('Michael', 'Brown', '1990-12-05', '345-67-8901', 95000, '2021-07-20', 4, 3),
('Olivia', 'Davis', '1997-04-18', '456-78-9012', 72000, '2023-06-12', 1, 1),
('Daniel', 'Wilson', '1993-11-30', '567-89-0123', 88000, '2022-09-25', 3, 2);


INSERT INTO users (employee_id, role_id, username, password_hash)
VALUES
(1, 1, 'admin', 'admin123'),        -- HR Admin
(2, 2, 'emma.j', 'pass123'),
(3, 2, 'michael.b', 'pass123'),
(4, 2, 'olivia.d', 'pass123'),
(5, 2, 'daniel.w', 'pass123');

