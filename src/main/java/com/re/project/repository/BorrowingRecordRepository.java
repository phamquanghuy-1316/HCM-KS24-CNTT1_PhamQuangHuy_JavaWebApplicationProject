package com.re.project.repository;

import com.re.project.entity.BorrowingRecord;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingRecordRepository
        extends JpaRepository<BorrowingRecord, Long> {
}