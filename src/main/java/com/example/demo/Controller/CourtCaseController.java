package com.example.demo.Controller;

<<<<<<< HEAD
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.CourtCase;
import com.example.demo.service.CourtCaseService;

@CrossOrigin(origins = {
        "http://localhost:3000",
        "https://frontend-repo-gold.vercel.app"
})
@RestController
@RequestMapping("/api/cases")
public class CourtCaseController {

    @Autowired
    private CourtCaseService service;

=======
import com.example.demo.Entity.CourtCase;
import com.example.demo.Entity.CaseType;
import com.example.demo.Service.CourtCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cases")
@CrossOrigin(origins = "http://localhost:3000")
public class CourtCaseController {
    @Autowired
    private CourtCaseService service;

    @PostMapping
    public CourtCase createCase(@RequestBody CourtCase courtCase) {
        return service.saveCase(courtCase);
    }

>>>>>>> 3c05b6fa735cae5be9f32e8988f3152240e79c2b
    @GetMapping
    public List<CourtCase> getAllCases() {
        return service.getAllCases();
    }

<<<<<<< HEAD
    @PostMapping
    public CourtCase createCase(@RequestBody CourtCase courtCase) {
        return service.createCase(courtCase);
    }

    @PutMapping("/{id}")
    public CourtCase updateCase(
            @PathVariable Long id,
            @RequestBody CourtCase updatedCase) {
        return service.updateCase(id, updatedCase);
=======
    @GetMapping("/type/{type}")
    public List<CourtCase> getCasesByType(@PathVariable CaseType type) {
        return service.getCasesByType(type);
    }

    @PutMapping("/{id}")
    public CourtCase updateCase(@PathVariable Long id, @RequestBody CourtCase updatedCase) {
        updatedCase.setId(id);
        return service.saveCase(updatedCase);
>>>>>>> 3c05b6fa735cae5be9f32e8988f3152240e79c2b
    }

    @DeleteMapping("/{id}")
    public void deleteCase(@PathVariable Long id) {
        service.deleteCase(id);
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 3c05b6fa735cae5be9f32e8988f3152240e79c2b
