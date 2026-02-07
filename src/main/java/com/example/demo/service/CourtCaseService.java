package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.CourtCaseRepository;
import com.example.demo.model.CourtCase;

@Service
public class CourtCaseService {

    @Autowired
    private CourtCaseRepository repo;

    public List<CourtCase> getAllCases() {
        return repo.findAll();
    }

    public CourtCase createCase(CourtCase courtCase) {
        return repo.save(courtCase);
    }

    public CourtCase updateCase(Long id, CourtCase updatedCase) {
        return repo.findById(id).map(c -> {
            c.setCaseTitle(updatedCase.getCaseTitle());
            c.setDescription(updatedCase.getDescription());
            c.setCaseType(updatedCase.getCaseType());
            c.setStatus(updatedCase.getStatus());
            c.setDefenderName(updatedCase.getDefenderName());
            c.setOffenderName(updatedCase.getOffenderName());
            return repo.save(c);
        }).orElseThrow(() -> new RuntimeException("Case not found"));
    }


    public void deleteCase(Long id) {
        repo.deleteById(id);
    }
}
