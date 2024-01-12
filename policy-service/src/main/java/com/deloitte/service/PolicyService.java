package com.deloitte.service;

import java.util.List;

import com.deloitte.entity.Policy;

public interface PolicyService {
	Policy addNewPolicy(Policy policy);
	Policy updateOldPolicy(Policy policy);
	Policy findPolicyByPolicyId(String policyId);
	List<Policy> listOfAllPolicies();
}
