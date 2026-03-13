package com.example.demo.repository;

import com.example.demo.entity.CourtCase;
import com.example.demo.entity.CaseType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourtCaseRepository extends JpaRepository<CourtCase, Long> {
    List<CourtCase> findByCaseType(CaseType caseType);
}
