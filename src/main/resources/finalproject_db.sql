CREATE DATABASE IF NOT EXISTS finalproject_db;

USE finalproject_db;

-- =====================================================
-- USERS
-- =====================================================

CREATE TABLE IF NOT EXISTS users (

    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    username VARCHAR(100) UNIQUE NOT NULL,

    email VARCHAR(150) UNIQUE NOT NULL,

    password VARCHAR(255) NOT NULL,

    role ENUM(
        'STUDENT',
        'LECTURER',
        'ADMIN'
    ) NOT NULL,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =====================================================
-- USER PROFILES
-- =====================================================

CREATE TABLE IF NOT EXISTS user_profiles (

    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    full_name VARCHAR(150),

    phone VARCHAR(20),

    address TEXT,

    dob DATE,

    avatar VARCHAR(255),

    user_id BIGINT UNIQUE,

    CONSTRAINT fk_profile_user
    FOREIGN KEY(user_id)
    REFERENCES users(id)
    ON DELETE CASCADE
);

-- =====================================================
-- DEPARTMENTS
-- =====================================================

CREATE TABLE IF NOT EXISTS departments (

    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    name VARCHAR(150) NOT NULL UNIQUE,

    description TEXT
);

-- =====================================================
-- LECTURERS
-- =====================================================

CREATE TABLE IF NOT EXISTS lecturers (

    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    specialization VARCHAR(255),

    academic_degree VARCHAR(100),

    experience_years INT DEFAULT 0,

    department_id BIGINT,

    user_id BIGINT UNIQUE,

    CONSTRAINT fk_lecturer_department
    FOREIGN KEY(department_id)
    REFERENCES departments(id)
    ON DELETE SET NULL,

    CONSTRAINT fk_lecturer_user
    FOREIGN KEY(user_id)
    REFERENCES users(id)
    ON DELETE CASCADE
);

-- =====================================================
-- EQUIPMENTS
-- =====================================================

CREATE TABLE IF NOT EXISTS equipments (

    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    equipment_name VARCHAR(255) NOT NULL,

    description TEXT,

    quantity INT NOT NULL DEFAULT 0,

    available_quantity INT NOT NULL DEFAULT 0,

    status ENUM(
        'AVAILABLE',
        'OUT_OF_STOCK',
        'MAINTENANCE'
    ) DEFAULT 'AVAILABLE',

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =====================================================
-- MENTORING SESSIONS
-- =====================================================

CREATE TABLE IF NOT EXISTS mentoring_sessions (

    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    student_id BIGINT NOT NULL,

    lecturer_id BIGINT NOT NULL,

    department_id BIGINT NOT NULL,

    session_date DATE NOT NULL,

    start_time TIME NOT NULL,

    end_time TIME NOT NULL,

    topic VARCHAR(255),

    note TEXT,

    status ENUM(
        'PENDING',
        'CONFIRMED',
        'COMPLETED',
        'CANCELLED'
    ) DEFAULT 'PENDING',

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_session_student
    FOREIGN KEY(student_id)
    REFERENCES users(id)
    ON DELETE CASCADE,

    CONSTRAINT fk_session_lecturer
    FOREIGN KEY(lecturer_id)
    REFERENCES lecturers(id)
    ON DELETE CASCADE,

    CONSTRAINT fk_session_department
    FOREIGN KEY(department_id)
    REFERENCES departments(id)
    ON DELETE CASCADE
);

-- =====================================================
-- ACADEMIC EVALUATIONS
-- =====================================================

CREATE TABLE IF NOT EXISTS academic_evaluations (

    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    mentoring_session_id BIGINT UNIQUE,

    lecturer_id BIGINT,

    student_id BIGINT,

    evaluation TEXT,

    score DECIMAL(5,2),

    recommendation TEXT,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_evaluation_session
    FOREIGN KEY(mentoring_session_id)
    REFERENCES mentoring_sessions(id)
    ON DELETE CASCADE,

    CONSTRAINT fk_evaluation_lecturer
    FOREIGN KEY(lecturer_id)
    REFERENCES lecturers(id)
    ON DELETE SET NULL,

    CONSTRAINT fk_evaluation_student
    FOREIGN KEY(student_id)
    REFERENCES users(id)
    ON DELETE CASCADE
);

-- =====================================================
-- BORROWING RECORDS
-- =====================================================

CREATE TABLE IF NOT EXISTS borrowing_records (

    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    mentoring_session_id BIGINT,

    student_id BIGINT,

    approved_by BIGINT,

    borrow_date DATE,

    return_date DATE,

    status ENUM(
        'PENDING',
        'APPROVED',
        'BORROWED',
        'RETURNED',
        'REJECTED'
    ) DEFAULT 'PENDING',

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_borrow_session
    FOREIGN KEY(mentoring_session_id)
    REFERENCES mentoring_sessions(id)
    ON DELETE CASCADE,

    CONSTRAINT fk_borrow_student
    FOREIGN KEY(student_id)
    REFERENCES users(id)
    ON DELETE CASCADE,

    CONSTRAINT fk_borrow_admin
    FOREIGN KEY(approved_by)
    REFERENCES users(id)
    ON DELETE SET NULL
);

-- =====================================================
-- BORROWING DETAILS
-- =====================================================

CREATE TABLE IF NOT EXISTS borrowing_details (

    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    borrowing_record_id BIGINT,

    equipment_id BIGINT,

    quantity INT NOT NULL DEFAULT 1,

    note TEXT,

    CONSTRAINT fk_detail_record
    FOREIGN KEY(borrowing_record_id)
    REFERENCES borrowing_records(id)
    ON DELETE CASCADE,

    CONSTRAINT fk_detail_equipment
    FOREIGN KEY(equipment_id)
    REFERENCES equipments(id)
    ON DELETE CASCADE
);

-- =====================================================
-- SEED DATA - DEPARTMENTS
-- =====================================================
-- =====================================================
-- TEST QUERIES
-- =====================================================

SELECT * FROM users;

SELECT * FROM user_profiles;

SELECT * FROM departments;

SELECT * FROM lecturers;

SELECT * FROM equipments;

SELECT * FROM mentoring_sessions;

SELECT * FROM academic_evaluations;

SELECT * FROM borrowing_records;

SELECT * FROM borrowing_details;