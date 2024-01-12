package com.deloitte.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.entity.Policy;


@Repository
public interface PolicyRepository extends MongoRepository<Policy, String>{
	Policy findByPolicyId(String policyId);
}
