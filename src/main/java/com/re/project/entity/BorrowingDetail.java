package com.re.project.entity;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "borrowing_details")

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BorrowingDetail {

    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)

    private Long id;

    @ManyToOne
    @JoinColumn(name = "record_id")
    private BorrowingRecord record;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    private Integer quantity;
}