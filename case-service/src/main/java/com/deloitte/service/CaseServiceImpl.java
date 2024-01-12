package com.deloitte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.entity.Case;
import com.deloitte.repository.CaseRepository;

@Service
public class CaseServiceImpl implements CaseService {

	@Autowired
	CaseRepository caseRepository;
	
	@Override
	public Case addCase(Case cases) {
		return caseRepository.save(cases);
	}

	@Override
	public Case updateCase(Case cases) {
		return caseRepository.save(cases);
	}

	@Override
	public Case findCaseByCaseId(String caseId) {
		return caseRepository.findByCaseId(caseId);
	}

	@Override
	public List<Case> findAllCases() {
		return caseRepository.findAll();
	}

}
