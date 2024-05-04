create database course;
use course;
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    roles_id INT,
    contact VARCHAR(255),
    class_id INT,
    staff BOOLEAN,
    gender VARCHAR(255),
    guardian_name VARCHAR(255)
);

CREATE TABLE roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(255)
);

CREATE TABLE classes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    class_name VARCHAR(255)
);

CREATE TABLE timetable (
    id INT AUTO_INCREMENT PRIMARY KEY,
    date DATE,
    class_id INT,
    description TEXT
);

CREATE TABLE events_calendar (
    id INT AUTO_INCREMENT PRIMARY KEY,
    event_name VARCHAR(255),
    start_time DATETIME,
    end_time DATETIME,
    full_day BOOLEAN,
    class_id INT
);

CREATE TABLE assignments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    subject VARCHAR(255),
    title VARCHAR(255),
    deadline DATE,
    assigned_by VARCHAR(255),
    class_id INT
);

CREATE TABLE examinations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    exam_name VARCHAR(255),
    subject VARCHAR(255),
    description TEXT,
    exam_type VARCHAR(255),
    class_id INT
);

CREATE TABLE result (
    id INT AUTO_INCREMENT PRIMARY KEY,
    exam_type VARCHAR(255),
    pass_percentage DECIMAL(5,2),
    description TEXT,
    class_id INT
);

CREATE TABLE attendance (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_name VARCHAR(255),
    present BOOLEAN,
    class_id INT
);

CREATE TABLE miscellaneous_info (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    progress INT,
    class_id INT
);
ALTER TABLE users ADD CONSTRAINT fk_roles_id FOREIGN KEY (roles_id) REFERENCES roles(id);
ALTER TABLE users ADD CONSTRAINT fk_class_id FOREIGN KEY (class_id) REFERENCES classes(id);
ALTER TABLE timetable ADD CONSTRAINT fk_class_id_timetable FOREIGN KEY (class_id) REFERENCES classes(id);
ALTER TABLE eventsCalendar ADD CONSTRAINT fk_class_id_events FOREIGN KEY (class_id) REFERENCES classes(id);
ALTER TABLE assignments ADD CONSTRAINT fk_class_id_assignment FOREIGN KEY (class_id) REFERENCES classes(id);
ALTER TABLE examinations ADD CONSTRAINT fk_class_id_examinations FOREIGN KEY (class_id) REFERENCES classes(id);
ALTER TABLE result ADD CONSTRAINT fk_class_id_result FOREIGN KEY (class_id) REFERENCES classes(id);
ALTER TABLE attendance ADD CONSTRAINT fk_class_id_attendance FOREIGN KEY (class_id) REFERENCES classes(id);
ALTER TABLE miscellaneous_info ADD CONSTRAINT fk_class_id_miscellaneous FOREIGN KEY (class_id) REFERENCES classes(id);