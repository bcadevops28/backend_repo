package com.example.demo.service;

import com.example.demo.entity.CourtCase;
import com.example.demo.repository.CourtCaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class CourtCaseService {

    @Autowired
    private CourtCaseRepository courtCaseRepository;

    public List<CourtCase> getAllCases() {
        return courtCaseRepository.findAll();
    }

    @Transactional
    public CourtCase addCase(CourtCase courtCase) {
        CourtCase saved = courtCaseRepository.saveAndFlush(courtCase); // 👈 saveAndFlush
        return saved;
    }

    @Transactional
    public void deleteCase(Long id) {
        courtCaseRepository.deleteById(id);
    }
    public CourtCase getById(Long id) {
        return courtCaseRepository.findById(id).orElse(null);
    }
    @GetMapping("/check")
    public String check() {
        return "NEW VERSION";
    }
}