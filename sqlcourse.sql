CREATE TABLE User (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    role VARCHAR(50),
    otherDetails VARCHAR(255)
);
CREATE TABLE Class (
    id INT AUTO_INCREMENT PRIMARY KEY,
    classId INT UNIQUE,
    teacherId INT,
    className VARCHAR(255),
    subject VARCHAR(255),
    section VARCHAR(50),
    miscellaneousInfo VARCHAR(255),
    FOREIGN KEY (teacherId) REFERENCES User(id)
);
CREATE TABLE Student (
    id INT AUTO_INCREMENT PRIMARY KEY,
    classId INT,
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    otherDetails VARCHAR(255),
    FOREIGN KEY (classId) REFERENCES Class(id)
);
CREATE TABLE Assignment (
    id INT AUTO_INCREMENT PRIMARY KEY,
    classId INT,
    title VARCHAR(255),
    description TEXT,
    dueDate DATE,
    submissionDetails VARCHAR(255),
    FOREIGN KEY (classId) REFERENCES Class(id)
);
CREATE TABLE Event (
    id INT AUTO_INCREMENT PRIMARY KEY,
    classId INT,
    title VARCHAR(255),
    description TEXT,
    startDate DATE,
    endDate DATE,
    location VARCHAR(255),
    otherDetails VARCHAR(255),
    FOREIGN KEY (classId) REFERENCES Class(id)
);
CREATE TABLE Examination (
    id INT AUTO_INCREMENT PRIMARY KEY,
    classId INT,
    type VARCHAR(255),
    date DATE,
    duration INT,
    gradingDetails VARCHAR(255),
    FOREIGN KEY (classId) REFERENCES Class(id)
);

CREATE TABLE ExamResult (
    id INT AUTO_INCREMENT PRIMARY KEY,
    classId INT,
    examType VARCHAR(255),
    studentResults VARCHAR(255),
    overallGrade VARCHAR(50),
    feedback TEXT,
    FOREIGN KEY (classId) REFERENCES Class(id)
);
CREATE TABLE Attendance (
    id INT AUTO_INCREMENT PRIMARY KEY,
    classId INT,
    date DATE,
    presentStudents VARCHAR(255),
    notes TEXT,
    FOREIGN KEY (classId) REFERENCES Class(id)
);
CREATE TABLE Question (
    id INT AUTO_INCREMENT PRIMARY KEY,
    questionText TEXT,
    questionType VARCHAR(50),
    options VARCHAR(255),
    correctAnswer VARCHAR(255),
    partialPoints INT,
    difficultyLevel VARCHAR(50),
    subject VARCHAR(255),
    topic VARCHAR(255),
    source VARCHAR(255),
    createdBy INT,
    lastModifiedBy INT,
    FOREIGN KEY (createdBy) REFERENCES User(id),
    FOREIGN KEY (lastModifiedBy) REFERENCES User(id)
);
CREATE TABLE Resource (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    description TEXT,
    url VARCHAR(255),
    type VARCHAR(50),
    subject VARCHAR(255),
    topic VARCHAR(255),
    createdBy INT,
    lastModifiedBy INT,
    FOREIGN KEY (createdBy) REFERENCES User(id),
    FOREIGN KEY (lastModifiedBy) REFERENCES User(id)
);







