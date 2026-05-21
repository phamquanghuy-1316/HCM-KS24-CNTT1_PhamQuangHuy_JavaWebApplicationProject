package com.re.project.service;

import com.re.project.entity.Equipment;

import java.util.List;

public interface EquipmentService {

    List<Equipment> findAll();

    Equipment findById(Long id);

    void save(Equipment equipment);

    void delete(Long id);
}