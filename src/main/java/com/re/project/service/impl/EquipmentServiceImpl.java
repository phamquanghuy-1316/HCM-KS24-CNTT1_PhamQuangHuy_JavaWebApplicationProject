package com.re.project.service.impl;

import com.re.project.entity.Equipment;

import com.re.project.repository.EquipmentRepository;

import com.re.project.service.EquipmentService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl
        implements EquipmentService {

    private final EquipmentRepository
            equipmentRepository;

    @Override
    public List<Equipment> findAll() {

        return equipmentRepository.findAll();
    }

    @Override
    public Equipment findById(Long id) {

        return equipmentRepository
                .findById(id)
                .orElseThrow();
    }

    @Override
    public void save(Equipment equipment) {

        if(equipment.getCreatedAt() == null) {

            equipment.setCreatedAt(
                    LocalDateTime.now()
            );
        }

        equipmentRepository.save(equipment);
    }

    @Override
    public void delete(Long id) {

        equipmentRepository.deleteById(id);
    }
}