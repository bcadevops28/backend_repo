package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.CourtCase;

public interface CourtCaseRepository extends JpaRepository<CourtCase, Long> {
}
