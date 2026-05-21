package com.re.project.repository;

import com.re.project.entity.Equipment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository
        extends JpaRepository<Equipment, Long> {
}