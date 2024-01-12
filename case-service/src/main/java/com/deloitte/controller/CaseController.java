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

import com.deloitte.client.MemberFeign;
import com.deloitte.client.PolicyFeign;
import com.deloitte.dummy.Member;
import com.deloitte.dummy.Policy;
import com.deloitte.entity.Case;
import com.deloitte.service.CaseService;

@RestController
@RequestMapping("/case")
public class CaseController {
	
	@Autowired
	CaseService caseService;
	
	@Autowired
	MemberFeign memberFeign;
	
	@Autowired
	PolicyFeign policyFeign;
	
	@PostMapping("/addCase")
	ResponseEntity<?> addCase(@RequestBody Case cases){
		Case newCase = caseService.addCase(cases);
		if(newCase != null ) {
			return ResponseEntity.status(HttpStatus.CREATED).body(cases);
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Case not created");
		}
	}
	
	@PutMapping("/updateCase")
	ResponseEntity<?> updateCase(@RequestBody Case cases){
		Case newCase = caseService.findCaseByCaseId(cases.getCaseId());
		if(newCase != null) {
			caseService.updateCase(cases);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(cases);
		}
		else{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CaseId: "+cases.getCaseId()+" does not exist");
		}
	}
	
	@GetMapping("{caseId}")
	ResponseEntity<?> searchCaseByCaseId(@PathVariable String caseId){
		Case newCase = caseService.findCaseByCaseId(caseId);
		if(newCase != null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(newCase);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Case found for CaseId:"+caseId);
		}
	}
	
	@GetMapping
	ResponseEntity<?> getAllCases(){
		List<Case> cases = caseService.findAllCases();
		if(cases.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(cases);
		}
		else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Cases found");
		}
	}
	
	@GetMapping("/memberDetail/{memberId}")
	Member getMemberByMemberId(@PathVariable String memberId){
		return memberFeign.searchMemberByMemberId(memberId);
	}
	
	@GetMapping("/policyDetail/{policyId}")
	ResponseEntity<?> getPolicyByPolicyId(@PathVariable String policyId){
		Policy policy = policyFeign.findPolicyByPolicyId(policyId);
		if(policy != null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(policy);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No policy with policyId:"+policyId+" found");
		}
	}
	
	@GetMapping("/allMembers")
	List<Member> getAllMembers(){
		return memberFeign.findAllMembers();
	}
}
