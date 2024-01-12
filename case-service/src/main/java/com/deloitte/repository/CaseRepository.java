package com.deloitte.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.entity.Case;



@Repository
public interface CaseRepository extends MongoRepository<Case, String>{
	
	Case findByCaseId(String caseId);
}
