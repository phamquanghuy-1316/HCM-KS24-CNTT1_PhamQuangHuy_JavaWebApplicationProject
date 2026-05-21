package com.re.project.repository;

import com.re.project.entity.Lecturer;

import com.re.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LecturerRepository
        extends JpaRepository<Lecturer, Long> {

    Optional<Lecturer> findByUser(
            User user
    );

    List<Lecturer>
    findByDepartmentId(Long departmentId);

}
