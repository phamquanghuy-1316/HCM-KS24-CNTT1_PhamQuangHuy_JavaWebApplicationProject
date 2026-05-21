package com.re.project.repository;

import com.re.project.entity.Department;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository
        extends JpaRepository<Department, Long> {
}
