package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.CourtCase;
import com.example.demo.service.CourtCaseService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/cases")
public class CourtCaseController {

    @Autowired
    private CourtCaseService service;

    @GetMapping
    public List<CourtCase> getAllCases() {
        return service.getAllCases();
    }

    @PostMapping
    public CourtCase createCase(@RequestBody CourtCase courtCase) {
        return service.createCase(courtCase);
    }

    @PutMapping("/{id}")
    public CourtCase updateCase(
            @PathVariable Long id,
            @RequestBody CourtCase updatedCase) {
        return service.updateCase(id, updatedCase);
    }

    @DeleteMapping("/{id}")
    public void deleteCase(@PathVariable Long id) {
        service.deleteCase(id);
    }
}
