package com.example.demo.service;

import com.example.demo.entity.CourtCase;
import com.example.demo.repository.CourtCaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourtCaseService {

    @Autowired
    private CourtCaseRepository courtCaseRepository;

    // ✅ GET ALL
    public List<CourtCase> getAllCases() {
        return courtCaseRepository.findAll();
    }

    // ✅ SAVE
    public CourtCase saveCase(CourtCase courtCase) {
        return courtCaseRepository.save(courtCase);
    }

    public CourtCase addCase(CourtCase courtCase) {
        return courtCaseRepository.save(courtCase); // MUST return saved object
    }
    // ✅ DELETE
    public void deleteCase(Long id) {
        courtCaseRepository.deleteById(id);
    }
}