package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class CourtCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String caseTitle;
    private String description;
    private String defenderName;
    private String offenderName;
    private String caseType;
    private String status;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCaseTitle() { return caseTitle; }
    public void setCaseTitle(String caseTitle) { this.caseTitle = caseTitle; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDefenderName() { return defenderName; }
    public void setDefenderName(String defenderName) { this.defenderName = defenderName; }

    public String getOffenderName() { return offenderName; }
    public void setOffenderName(String offenderName) { this.offenderName = offenderName; }

    public String getCaseType() { return caseType; }
    public void setCaseType(String caseType) { this.caseType = caseType; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
