package com.example.demo.Repository;

import com.example.demo.Entity.CourtCase;
import org.aspectj.lang.reflect.DeclareAnnotation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourtCaseRepository extends JpaRepository<CourtCase, Long> {
    // Find cases by type (NORMAL or CRIMINAL)
    List<CourtCase> findByCaseType(DeclareAnnotation.Kind caseType);
}