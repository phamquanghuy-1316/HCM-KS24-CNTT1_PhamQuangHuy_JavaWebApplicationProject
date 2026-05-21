package com.re.project.dto;

import lombok.*;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@Builder

public class EvaluationRequest {

    private Long sessionId;

    private String evaluation;

    private Integer score;

    private Long equipmentId;

    private Integer quantity;
}