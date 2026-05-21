package com.re.project.controller;

import com.re.project.entity.Equipment;

import com.re.project.service.EquipmentService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor

@RequestMapping("/admin/equipments")

public class EquipmentController {

    private final EquipmentService
            equipmentService;

    // LIST

    @GetMapping
    public String list(Model model) {

        model.addAttribute(
                "equipments",
                equipmentService.findAll()
        );

        return "admin/equipment/list";
    }

    // SHOW CREATE FORM

    @GetMapping("/create")
    public String createForm(Model model) {

        model.addAttribute(
                "equipment",
                new Equipment()
        );

        return "admin/equipment/form";
    }

    // SAVE

    @PostMapping("/save")
    public String save(

            @ModelAttribute Equipment equipment

    ) {

        equipmentService.save(equipment);

        return "redirect:/admin/equipments";
    }

    // EDIT

    @GetMapping("/edit/{id}")
    public String edit(

            @PathVariable Long id,

            Model model

    ) {

        model.addAttribute(
                "equipment",
                equipmentService.findById(id)
        );

        return "admin/equipment/form";
    }

    // DELETE

    @GetMapping("/delete/{id}")
    public String delete(

            @PathVariable Long id

    ) {

        equipmentService.delete(id);

        return "redirect:/admin/equipments";
    }
}