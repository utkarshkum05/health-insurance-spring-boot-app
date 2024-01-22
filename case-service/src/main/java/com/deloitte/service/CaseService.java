package com.deloitte.service;

import java.util.List;

import com.deloitte.dummy.Member;
import com.deloitte.dummy.Policy;
import com.deloitte.entity.Case;

public interface CaseService {
	Case addCase(Case cases);
	Case updateCase(Case cases);
	Case findCaseByCaseId(String caseId);
	List<Case> findAllCases();
	Member findMemberByMemberId(String memberId);
	Policy findPolicyByPolicyId(String policyId);
}
