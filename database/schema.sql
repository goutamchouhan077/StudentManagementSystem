CREATE DATABASE IF NOT EXISTS student_management_system;
USE student_management_system;
-- Hibernate generates the production schema from the JPA entities on first startup.
-- This SQL is a reference schema for review / MySQL Workbench.
CREATE TABLE IF NOT EXISTS users (
 id BIGINT PRIMARY KEY AUTO_INCREMENT, username VARCHAR(100) NOT NULL UNIQUE,
 name VARCHAR(160), email VARCHAR(180) UNIQUE, password VARCHAR(100) NOT NULL,
 role VARCHAR(20) NOT NULL, active BOOLEAN NOT NULL DEFAULT TRUE
);
CREATE TABLE IF NOT EXISTS students (
 id BIGINT PRIMARY KEY AUTO_INCREMENT, student_id VARCHAR(80) NOT NULL UNIQUE,
 full_name VARCHAR(180) NOT NULL, email VARCHAR(180), phone VARCHAR(40),
 department VARCHAR(120), course VARCHAR(120), semester INT, admission_date DATE,
 gender VARCHAR(30), address VARCHAR(500)
);
CREATE TABLE IF NOT EXISTS teachers (
 id BIGINT PRIMARY KEY AUTO_INCREMENT, employee_id VARCHAR(80) UNIQUE,
 full_name VARCHAR(180), email VARCHAR(180), phone VARCHAR(40), department VARCHAR(120)
);
CREATE TABLE IF NOT EXISTS courses (
 id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(160) NOT NULL UNIQUE,
 department VARCHAR(120), duration_semesters INT
);
CREATE TABLE IF NOT EXISTS subjects (
 id BIGINT PRIMARY KEY AUTO_INCREMENT, code VARCHAR(60), name VARCHAR(160) NOT NULL,
 course VARCHAR(160), semester INT, maximum_marks DOUBLE
);
CREATE TABLE IF NOT EXISTS attendance (
 id BIGINT PRIMARY KEY AUTO_INCREMENT, student_id VARCHAR(80) NOT NULL,
 attendance_date DATE NOT NULL, course VARCHAR(160), status VARCHAR(20) NOT NULL,
 marked_by VARCHAR(100), UNIQUE KEY uq_attendance_student_date_course(student_id, attendance_date, course),
 INDEX idx_attendance_date(attendance_date), INDEX idx_attendance_student(student_id)
);
CREATE TABLE IF NOT EXISTS marks (
 id BIGINT PRIMARY KEY AUTO_INCREMENT, student_id VARCHAR(80), subject VARCHAR(160),
 course VARCHAR(160), semester INT, obtained_marks DOUBLE, maximum_marks DOUBLE, grade VARCHAR(10),
 INDEX idx_marks_student(student_id)
);
CREATE TABLE IF NOT EXISTS fees (
 id BIGINT PRIMARY KEY AUTO_INCREMENT, student_id VARCHAR(80) NOT NULL UNIQUE,
 total_fees DECIMAL(12,2), paid_fees DECIMAL(12,2), pending_fees DECIMAL(12,2), updated_at DATE
);
