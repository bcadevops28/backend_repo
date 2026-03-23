package com.example.demo.controller;

import com.example.demo.entity.CourtCase;
import com.example.demo.service.CourtCaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cases")
public class CourtCaseController {

    @Autowired
    private CourtCaseService courtCaseService;

    // ✅ GET ALL
    @GetMapping
    public List<CourtCase> getAllCases() {
        return courtCaseService.getAllCases();
    }

    // ✅ ADD
    @PostMapping
    public ResponseEntity<?> addCase(@RequestBody CourtCase courtCase) {
        try {
            CourtCase saved = courtCaseService.addCase(courtCase);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ ERROR: " + e.getMessage());
        }
    }

    // ✅ UPDATE (POST instead of PUT for Azure)
    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateCase(@PathVariable Long id,
                                        @RequestBody CourtCase courtCase) {
        try {
            CourtCase existing = courtCaseService.getById(id);
            if (existing == null) {
                return ResponseEntity.notFound().build();
            }

            existing.setCaseTitle(courtCase.getCaseTitle());
            existing.setDescription(courtCase.getDescription());
            existing.setDefenderName(courtCase.getDefenderName());
            existing.setOffenderName(courtCase.getOffenderName());
            existing.setCaseStatus(courtCase.getCaseStatus());
            existing.setCaseType(courtCase.getCaseType());

            CourtCase updated = courtCaseService.addCase(existing);
            return ResponseEntity.ok(updated);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ ERROR: " + e.getMessage());
        }
    }
}