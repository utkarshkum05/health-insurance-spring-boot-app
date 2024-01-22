package com.deloitte.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.entity.Policy;
import com.deloitte.service.PolicyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Policy Service", description = "Policy service API")
@RestController
@RequestMapping("/policy")
public class PolicyController {
	
	Logger logger = LoggerFactory.getLogger(PolicyController.class);
	
	@Autowired
	PolicyService policyService;

	@Operation(summary = "Add new policy",
			description = "Adds new policy in collection after checking all valid conditions")
	@ApiResponse(responseCode = "202", description = "Accepted")
	@PostMapping("/addPolicy")
	ResponseEntity<?> addPolicy(@RequestBody Policy policy){
		logger.info("Started addPolicy in controller");
		Policy newPolicy = policyService.addNewPolicy(policy);
		if(newPolicy != null) {
			logger.info("Added new policy");
			return ResponseEntity.status(HttpStatus.CREATED).body(policy);
		}
		else {
			logger.warn("Warning for adding new policy");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Policy not created");
		}
	}
	
	@Operation(summary = "Update existing policy",
			description = "Update an existing policy")
	@ApiResponse(responseCode = "202", description = "Accepted")
	@PutMapping("/updatePolicy")
	ResponseEntity<?> updatePolicy(@RequestBody Policy policy){
		logger.info("Started updatePolicy in controller");
		Policy newPolicy = policyService.findPolicyByPolicyId(policy.getPolicyId());
		if(newPolicy != null) {
			logger.info("Update existing policy");
			policyService.updateOldPolicy(policy);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(policy);
		}
		else{
			logger.warn("Warning in updating policy");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PolicyId: "+policy.getPolicyId()+" does not exist");
		}
	}
	
	@Operation(summary = "Find policy by policyId",
			description = "Fetches policy from collection with the given policyId")
	@ApiResponse(responseCode = "200", description = "Case found")
	@GetMapping("/find/{policyId}")
	ResponseEntity<?> searchPolicyByPolicyId(@PathVariable String policyId){
		logger.info("Started finding policy by policyId");
		Policy newPolicy = policyService.findPolicyByPolicyId(policyId);
		if(newPolicy != null) {
			logger.info("Policy with given policyId found");
			return ResponseEntity.status(HttpStatus.OK).body(newPolicy);
		}
		else {
			logger.warn("Policy not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No policy found for policyId:"+policyId);
		}
	}
	
	@Operation(summary = "All policies",
			description = "Fetches all policies from the collection")
	@ApiResponse(responseCode = "200", description = "Successful opeartion")
	@GetMapping
	ResponseEntity<?> getAllPolicies(){
		logger.info("Started find all policies in controller");
		List<Policy> policies = policyService.listOfAllPolicies();
		if(policies.size() > 0) {
			logger.info("Fetch all policies");
			return ResponseEntity.status(HttpStatus.OK).body(policies);
		}
		else {
			logger.warn("Warning in fetching all policies");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Policies found");
		}
	}
}
