package com.example.demo.Controller;

import com.example.demo.Entity.CourtCase;
import com.example.demo.Entity.CaseType;
import com.example.demo.Service.CourtCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {
        "http://localhost:3000",
        "https://frontend-repo-gold.vercel.app"
})
@RestController
@RequestMapping("/api/cases")
public class CourtCaseController {

    @Autowired
    private CourtCaseService service;

    @PostMapping
    public CourtCase createCase(@RequestBody CourtCase courtCase) {
        return service.saveCase(courtCase);
    }

    @GetMapping
    public List<CourtCase> getAllCases() {
        return service.getAllCases();
    }

    @GetMapping("/type/{type}")
    public List<CourtCase> getCasesByType(@PathVariable CaseType type) {
        return service.getCasesByType(type);
    }

    @PutMapping("/{id}")
    public CourtCase updateCase(@PathVariable Long id, @RequestBody CourtCase updatedCase) {
        updatedCase.setId(id);
        return service.saveCase(updatedCase);
    }

    @DeleteMapping("/{id}")
    public void deleteCase(@PathVariable Long id) {
        service.deleteCase(id);
    }
}
