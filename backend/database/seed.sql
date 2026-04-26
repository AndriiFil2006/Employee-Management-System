INSERT INTO roles(role_name)
VALUES('HR_ADMIN'),
('EMPLOYEE');

INSERT INTO divisions(division_name)
VALUES('Marketing'),
('Front-end'),
('Back-end'),
('DevOps'),
('Management'),
('HR');

INSERT INTO job_titles (job_title_name)
VALUES('Software Engineer'),
('Back-end Developer'),
('UI Designer'),
('Team Lead'),
('Software Architecture'),
('Data Analyst'),
('CEO'),
('CFO'),
('XEO'),
('HR Manager');

INSERT INTO employees (first_name, last_name, dob, ssn, salary, hire_date, division_id, job_title_id)
VALUES
('Bruce', 'Banner', '1969-12-18', '123-45-6789', 85000, '2022-03-01', 3, 5),
('Kelly', 'Clarkson', '1982-04-24', '234-56-7890', 78000, '2023-01-10', 2, 1),
('Michael', 'Jackson', '1958-08-29', '345-67-8901', 95000, '2021-07-20', 4, 4),
('Ali', 'Wong', '1982-04-18', '456-78-9012', 72000, '2023-06-12', 1, 3),
('Daniel', 'Wilson', '1993-11-30', '567-89-0123', 88000, '2022-09-25', 3, 2),
('Terrell', 'Whiting', '1990-03-04', '070-34-0123', 215000, '2025-04-26',5, 8),
('Andrii', 'Fil', '2005-03-04', '071-34-0123', 215000, '2025-04-22',5, 7),
('Ammanuel', 'Roberts', '1997-03-04', '072-34-0123', 215000, '2025-04-24',5, 9),
('Warren', 'Buffet', '1930-08-30', '073-34-0123', 215000, '1999-04-26',4, 6),
('Ada', 'Lovelace', '1815-12-10', '074-34-0123', 215000, '1910-04-26',6, 10);


--admin password: admin123, the rest: pass123
INSERT INTO users (employee_id, role_id, username, password_hash)
VALUES
(1, 1, 'admin', '$argon2id$v=19$m=65536,t=3,p=1$SF44olxtgpF/AWTSs4VfOA$bAGM386L8pIGD0oEuuR8k2qm1Wnu6MFzZCVVIepgWA8'),        -- HR Admin

(2, 2, 'bruce.b', '$argon2id$v=19$m=65536,t=3,p=1$wriFuKy8lf+iu0y9tLa4+Q$rm1QoMNtr9X/krXRSiU8vajyntGKS2pe9TQKax/3dhk'),
(3, 2, 'kelly.c', '$argon2id$v=19$m=65536,t=3,p=1$wPW0IZe+oDne0mcl4FHbpw$v3UO2tpmxybZ4hHpVzONutuGeJJQgOBFwu6t6v/Ipbg'),
(3, 2, 'michael.j', '$argon2id$v=19$m=65536,t=3,p=1$aPBhXyc9nKCYb5fqHUEnPg$VRchoFAEf1KgyqOF9v/jfGF8jPuSBdFGRDXNXoZVUg0'),
(4, 2, 'ali.w', '$argon2id$v=19$m=65536,t=3,p=1$UYk07aI7HbCHtenzgobAaw$WtyYZysmH6RcaTnIQQyvXS5aVmHeA+IpGbdMK2PFNJ0'),
(5, 2, 'daniel.w', '$argon2id$v=19$m=65536,t=3,p=1$BMv3YHC7slNvGrIbeuQVfQ$HiieJgYnx/mDeBsVzIzprcfmO9hjagsa2FHxWfIZV4k');
(3, 2, 'terrell.w', '$argon2id$v=19$m=65536,t=3,p=1$HbV+hAg8pqcoPE7N1fXU/A$qEMNYKcBRa9ssm/DqA3Gw9YwAJczHfhHasVgHC4aCM4'),
(3, 2, 'andrii.f', '$argon2id$v=19$m=65536,t=3,p=1$qkQFDBMjIwKPJG9Ju7q4IQ$8wudjnDW0NGkH2zZr7ZPOjxfgnQpES86Qp7TfLapFec'),
(3, 2, 'ammanuel.r', '$argon2id$v=19$m=65536,t=3,p=1$7OfRMYkdVocndm5Xjub7jg$jIVvZCDRw/0ABd9NPTkJ88y6lSm8deTZ/vtroXt60uE'),
(3, 2, 'warren.b', '$argon2id$v=19$m=65536,t=3,p=1$h68zc0Z0R1SyyvkmSqGOyQ$pUHzCyT6fNNnhU4yigPlycKmnqjd7v6yYd+bwYtoSow'),
(3, 2, 'ada.l', '$argon2id$v=19$m=65536,t=3,p=1$AttL3whmLrQH58sxtMnKuw$4lA4eeNOx9ZTpWzXtlFEc4TNUAm+Wf5sOrCAwdk5rdo'),


