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

    // 🔐 Hardcoded password (you can change this)
    private static final String ADMIN_PASSWORD = "admin123";

    // ✅ GET ALL
    @GetMapping
    public List<CourtCase> getAllCases() {
        return courtCaseService.getAllCases();
    }

    // ✅ ADD (no password needed)
    @PostMapping
    public ResponseEntity<?> addCase(@RequestBody CourtCase courtCase) {
        try {
            CourtCase saved = courtCaseService.addCase(courtCase);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ ERROR: " + e.getMessage());
        }
    }

    // 🔐 DELETE WITH PASSWORD
    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deleteCase(@PathVariable Long id,
                                        @RequestParam String password) {

        if (!ADMIN_PASSWORD.equals(password)) {
            return ResponseEntity.status(403).body("❌ Invalid Password");
        }

        CourtCase existing = courtCaseService.getById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        courtCaseService.deleteCase(id);
        return ResponseEntity.ok("🗑️ Deleted");
    }

    // 🔐 UPDATE WITH PASSWORD
    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateCase(@PathVariable Long id,
                                        @RequestParam String password,
                                        @RequestBody CourtCase courtCase) {
        try {
            if (!ADMIN_PASSWORD.equals(password)) {
                return ResponseEntity.status(403).body("❌ Invalid Password");
            }

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

    // ✅ TEST
    @GetMapping("/test")
    public String test() {
        return "PASSWORD PROTECTED VERSION";
    }
}