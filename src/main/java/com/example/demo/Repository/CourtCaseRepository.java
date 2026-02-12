package com.example.demo.Repository;

import com.example.demo.Entity.CourtCase;
import com.example.demo.Entity.CaseType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourtCaseRepository extends JpaRepository<CourtCase, Long> {
    // Find cases by type (NORMAL or CRIMINAL)
    List<CourtCase> findByCaseType(CaseType caseType);
}

