package com.example.demo.controller;

import com.example.demo.entity.CourtCase;
import com.example.demo.service.CourtCaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {
        "http://localhost:3000",
        "https://your-frontend-url.vercel.app"
})
@RestController
@RequestMapping("/api/cases")
public class CourtCaseController {

    @Autowired
    private CourtCaseService courtCaseService;

    // GET ALL
    @GetMapping
    public List<CourtCase> getAllCases() {
        return courtCaseService.getAllCases();
    }

    // ADD CASE
    @PostMapping
    public ResponseEntity<CourtCase> addCase(@RequestBody CourtCase courtCase) {
        CourtCase saved = courtCaseService.addCase(courtCase);
        return ResponseEntity.ok(saved);
    }

    // DELETE CASE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCase(
            @PathVariable Long id,
            @RequestParam String password) {

        if (!password.equals("admin123")) {
            return ResponseEntity.status(403).body("Invalid Password");
        }

        courtCaseService.deleteCase(id);
        return ResponseEntity.ok("Case deleted successfully");
    }
}