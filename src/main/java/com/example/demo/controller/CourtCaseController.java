package com.example.demo.controller;

import com.example.demo.entity.CourtCase;
import com.example.demo.service.CourtCaseService;

import org.springframework.beans.factory.annotation.Autowired;
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

    // ✅ GET ALL CASES
    @GetMapping
    public List<CourtCase> getAllCases() {
        return courtCaseService.getAllCases();
    }

    // ✅ ADD CASE
    @PostMapping
    public CourtCase addCase(@RequestBody CourtCase courtCase) {
        return courtCaseService.saveCase(courtCase);
    }

    // ✅ DELETE CASE WITH PASSWORD
    @DeleteMapping("/{id}")
    public String deleteCase(@PathVariable Long id,
                             @RequestParam String password) {

        if (!password.equals("admin123")) {
            throw new RuntimeException("❌ Invalid Password!");
        }

        courtCaseService.deleteCase(id);
        return "✅ Case deleted successfully";
    }
}