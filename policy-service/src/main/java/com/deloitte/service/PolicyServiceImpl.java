package com.deloitte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.entity.Policy;
import com.deloitte.repository.PolicyRepository;

@Service
public class PolicyServiceImpl implements PolicyService {

	@Autowired
	PolicyRepository policyRepository;
	
	@Override
	public Policy addNewPolicy(Policy policy) {
		return policyRepository.save(policy);
	}

	@Override
	public Policy updateOldPolicy(Policy policy) {
		return policyRepository.save(policy);
	}

	@Override
	public Policy findPolicyByPolicyId(String policyId) {
		return policyRepository.findByPolicyId(policyId);
	}

	@Override
	public List<Policy> listOfAllPolicies() {
		return policyRepository.findAll();
	}

}
