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

import com.deloitte.dummy.Member;
import com.deloitte.dummy.Policy;
import com.deloitte.entity.Case;
import com.deloitte.service.CaseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Case Service", description="Case service API")
@RestController
@RequestMapping("/case")
public class CaseController {
	
	@Autowired
	CaseService caseService;
	
	Logger logger = LoggerFactory.getLogger(CaseController.class);
	
	/*
	 * Adding new case
	 * 
	 * @param - case
	 * @return - New added case
	 */
	@Operation(summary = "Add new case",
			description = "Adds new case in collection after checking all valid conditions")
	@ApiResponse(responseCode = "202", description = "Accepted")
	@PostMapping("/addCase")
	ResponseEntity<?> addCase(@RequestBody Case cases){
		logger.info("Started addCase in controller");
		Case newCase = caseService.addCase(cases);
		Member member = caseService.findMemberByMemberId(cases.getMemberId());
		Policy policy = caseService.findPolicyByPolicyId(cases.getPolicyId());
		if(newCase != null && member != null && policy!= null &&
				cases.getClaimAmount() < policy.getCoverageAmount() && 
				cases.getClaimDate().compareTo(policy.getEndDate()) < 0 && 
				cases.getClaimDate().compareTo(member.getExpiryDate()) < 0) {
			logger.info("Added new case");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(cases);
		}
		else if(cases.getClaimAmount() > policy.getCoverageAmount()){
			logger.warn("Warning for claim amount");
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Claim amount cannot be higher than coverage amount");
		}
		else if(cases.getClaimDate().compareTo(policy.getEndDate()) > 0 || cases.getClaimDate().compareTo(member.getExpiryDate()) > 0) {
			logger.warn("Warning for policy/member expiry date");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("POLICY IS EXPIRED");
		}
		else {
			logger.warn("Warning in else condition of addCase");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Case not created");
		}
	}
	
	/*
	 * Updating existing case
	 * 
	 * @param - case
	 * @return - Updated case
	 */
	@Operation(summary = "Update existing case",
			description = "Update an existing case")
	@ApiResponse(responseCode = "202", description = "Accepted")
	@PutMapping("/updateCase")
	ResponseEntity<?> updateCase(@RequestBody Case cases){
		logger.info("Started updateCase in controller");
		Case newCase = caseService.findCaseByCaseId(cases.getCaseId());
		if(newCase != null) {
			logger.info("Updated the case");
			caseService.updateCase(cases);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(cases);
		}
		else{
			logger.warn("Given caseId to update case not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CaseId: "+cases.getCaseId()+" does not exist");
		}
	}
	
	/*
	 * Find case by given caseId
	 * 
	 * @param - caseId
	 * @return - Case with given caseId
	 */
	@Operation(summary = "Find case by CaseId",
			description = "Fetches case from collection with the given caseId")
	@ApiResponse(responseCode = "200", description = "Case found")
	@GetMapping("/find/{caseId}")
	ResponseEntity<?> searchCaseByCaseId(@PathVariable String caseId){
		logger.info("Started findCaseBycaseId in controller");
		Case newCase = caseService.findCaseByCaseId(caseId);
		if(newCase != null) {
			logger.info("Case with given caseId found");
			return ResponseEntity.status(HttpStatus.OK).body(newCase);
		}
		else {
			logger.warn("No case with given caseId found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Case found for CaseId:"+caseId);
		}
	}
	
	/*
	 * Fetch all cases
	 * 
	 * @param
	 * @return - List of all cases in collection
	 */
	@Operation(summary = "All cases",
			description = "Fetches all cases from the collection")
	@ApiResponse(responseCode = "200", description = "Successful opeartion")
	@GetMapping
	ResponseEntity<?> getAllCases(){
		logger.info("Started findAllCases operation from controller");
		List<Case> cases = caseService.findAllCases();

		if(cases.size() > 0) {
			logger.info("Successful operation");
			return ResponseEntity.status(HttpStatus.OK).body(cases);
		}
		else {
			logger.warn("No content");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Cases found");
		}
	}
	
	/*
	 * Find member from member-service using memberId
	 * 
	 * @param - memberId
	 * @return - member with given memberId
	 */
	@Operation(summary = "Fetching member using memberId",
			description = "Fetches member from member-service using memberId")
	@ApiResponse(responseCode = "200", description = "Successful opeartion")
	@GetMapping("/memberDetail/{memberId}")
	ResponseEntity<?> getMemberByMemberId(@PathVariable String memberId){
		logger.info("Started findMember in case-service controller");
		Member newMember = caseService.findMemberByMemberId(memberId);
		if(newMember != null) {
			logger.info("Member found");
			return ResponseEntity.status(HttpStatus.OK).body(newMember);
		}
		else {
			logger.warn("Member with given memberId not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member with memberId: "+	memberId+" not found");
		}
	}
	
	/*
	 * Find policy from member-service using policyId
	 * 
	 * @param - policyId
	 * @return - policy with given policyId
	 */
	@Operation(summary = "Fetching policy using policyId",
			description = "Fetches policy from policy-service using policyId")
	@ApiResponse(responseCode = "200", description = "Successful opeartion")
	@GetMapping("/policyDetail/{policyId}")
	ResponseEntity<?> getPolicyByPolicyId(@PathVariable String policyId){
		logger.info("Started findPolicy in case-service controller");
		Policy policy = caseService.findPolicyByPolicyId(policyId);
		if(policy != null) {
			logger.info("Member found");
			return ResponseEntity.status(HttpStatus.OK).body(policy);
		}
		else {
			logger.warn("Policy with given policyId not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No policy with policyId:"+policyId+" found");
		}
	}
}
