package com.re.project.repository;

import com.re.project.entity.BorrowingDetail;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingDetailRepository
        extends JpaRepository<BorrowingDetail, Long> {
}
