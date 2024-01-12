package com.deloitte.controller;

import java.util.List;

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

@RestController
@RequestMapping("/policy")
public class PolicyController {
	
	@Autowired
	PolicyService policyService;

	@PostMapping("/addPolicy")
	ResponseEntity<?> addPolicy(@RequestBody Policy policy){
		Policy newPolicy = policyService.addNewPolicy(policy);
		if(newPolicy != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(policy);
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Policy not created");
		}
	}
	
	@PutMapping("/updatePolicy")
	ResponseEntity<?> updatePolicy(@RequestBody Policy policy){
		Policy newPolicy = policyService.findPolicyByPolicyId(policy.getPolicyId());
		if(newPolicy != null) {
			policyService.updateOldPolicy(policy);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(policy);
		}
		else{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PolicyId: "+policy.getPolicyId()+" does not exist");
		}
	}
	
	@GetMapping("/{policyId}")
	ResponseEntity<?> searchPolicyByPolicyId(@PathVariable String policyId){
		Policy newPolicy = policyService.findPolicyByPolicyId(policyId);
		if(newPolicy != null) {
			return ResponseEntity.status(HttpStatus.OK).body(newPolicy);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No policy found for policyId:"+policyId);
		}
	}
	
	@GetMapping
	ResponseEntity<?> getAllPolicies(){
		List<Policy> policies = policyService.listOfAllPolicies();
		if(policies.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(policies);
		}
		else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Policies found");
		}
	}
}
