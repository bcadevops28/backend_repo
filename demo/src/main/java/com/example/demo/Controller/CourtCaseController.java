package com.example.demo.controller;

import com.example.demo.entity.CaseType;
import com.example.demo.entity.CourtCase;
import com.example.demo.service.CourtCaseService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {
        "http://localhost:3000",
        "https://frontend-repo-gold.vercel.app"
})
@RestController
@RequestMapping("/api/cases")
public class CourtCaseController {

    private final CourtCaseService service;

    public CourtCaseController(CourtCaseService service) {
        this.service = service;
    }

    @GetMapping
    public List<CourtCase> getAllCases() {
        return service.getAllCases();
    }

    @GetMapping("/type/{type}")
    public List<CourtCase> getByType(@PathVariable String type) {
        CaseType caseType = CaseType.valueOf(type.toUpperCase());
        return service.getCasesByType(caseType);
    }

    @PostMapping
    public CourtCase createCase(@RequestBody CourtCase courtCase) {
        return service.createCase(courtCase);
    }

    @PutMapping("/{id}")
    public CourtCase updateCase(@PathVariable Long id,
                                @RequestBody CourtCase updatedCase) {
        return service.updateCase(id, updatedCase);
    }

    @DeleteMapping("/{id}")
    public void deleteCase(@PathVariable Long id) {
        service.deleteCase(id);
    }
}
