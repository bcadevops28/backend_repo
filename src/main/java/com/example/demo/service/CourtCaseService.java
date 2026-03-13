package com.example.demo.service;

import com.example.demo.entity.CourtCase;
import com.example.demo.entity.CaseType;
import com.example.demo.repository.CourtCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourtCaseService {
    @Autowired
    private CourtCaseRepository repository;

    public CourtCase saveCase(CourtCase courtCase) {
        return repository.save(courtCase);
    }

    public List<CourtCase> getAllCases() {
        return repository.findAll();
    }

    public List<CourtCase> getCasesByType(CaseType type) {
        return repository.findByCaseType(type);
    }

    public void deleteCase(Long id) {
        repository.deleteById(id);
    }
}
