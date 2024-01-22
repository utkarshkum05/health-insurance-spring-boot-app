package com.deloitte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.client.MemberFeign;
import com.deloitte.client.PolicyFeign;
import com.deloitte.dummy.Member;
import com.deloitte.dummy.Policy;
import com.deloitte.entity.Case;
import com.deloitte.repository.CaseRepository;

@Service
public class CaseServiceImpl implements CaseService {

	@Autowired
	CaseRepository caseRepository;
	
	@Autowired
	MemberFeign memberFeign;
	
	@Autowired
	PolicyFeign policyFeign;
	
	@Override
	public Case addCase(Case cases) {
		// TODO move the business logic form controller 
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

	@Override
	public Member findMemberByMemberId(String memberId) {
		return memberFeign.searchMemberByMemberId(memberId);
	}

	@Override
	public Policy findPolicyByPolicyId(String policyId) {
		return policyFeign.searchPolicyByPolicyId(policyId);
	}
	
}
