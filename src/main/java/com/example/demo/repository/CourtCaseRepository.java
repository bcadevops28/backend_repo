package com.example.demo.repository;

import com.example.demo.entity.CourtCase;
import org.aspectj.lang.reflect.DeclareAnnotation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourtCaseRepository extends JpaRepository<CourtCase, Long> {
    List<CourtCase> findByCaseType(DeclareAnnotation.Kind caseType);
}
