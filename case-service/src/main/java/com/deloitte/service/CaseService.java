package com.deloitte.service;

import java.util.List;

import com.deloitte.entity.Case;

public interface CaseService {
	Case addCase(Case cases);
	Case updateCase(Case cases);
	Case findCaseByCaseId(String caseId);
	List<Case> findAllCases();
}
