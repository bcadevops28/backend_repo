package com.example.demo.controller;

import com.example.demo.entity.CourtCase;
import com.example.demo.service.CourtCaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {
        "http://localhost:3000",
        "https://frontend-repo-gold.vercel.app/"
})
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
            e.printStackTrace();
            return ResponseEntity.badRequest().body("❌ ERROR: " + e.getMessage());
        }
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCase(@PathVariable Long id,
                                        @RequestParam String password) {

        if (!password.equals("admin123")) {
            return ResponseEntity.badRequest().body("❌ Invalid Password");
        }

        courtCaseService.deleteCase(id);
        return ResponseEntity.ok("✅ Deleted");
    }
}