package com.re.project.service;

import com.re.project.dto.EvaluationRequest;

public interface EvaluationService {

    void evaluate(
            EvaluationRequest request
    );
}