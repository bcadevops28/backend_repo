package com.example.demo.entity;

import com.example.demo.model.CaseStatus;
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

    @Enumerated(EnumType.STRING)
    private CaseType caseType;        // ✅ FIXED (field added)

    @Enumerated(EnumType.STRING)
    private CaseStatus caseStatus;    // ✅ ENUM instead of String

    public CourtCase() {}

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

    public CaseType getCaseType() { return caseType; }
    public void setCaseType(CaseType caseType) { this.caseType = caseType; }

    public CaseStatus getCaseStatus() { return caseStatus; }
    public void setCaseStatus(CaseStatus caseStatus) { this.caseStatus = caseStatus; }
}