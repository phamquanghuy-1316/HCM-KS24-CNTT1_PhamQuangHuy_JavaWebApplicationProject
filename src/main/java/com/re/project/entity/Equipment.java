package com.re.project.entity;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "equipments")

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Equipment {

    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)

    private Long id;

    @Column(name = "equipment_name")
    private String name;

    private String code;

    private Integer quantity;

    private Integer availableQuantity;

    @Column(columnDefinition = "TEXT")
    private String description;

    private LocalDateTime createdAt;
}