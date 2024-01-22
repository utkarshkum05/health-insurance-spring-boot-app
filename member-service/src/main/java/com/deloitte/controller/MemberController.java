 package com.deloitte.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.entity.Member;
import com.deloitte.service.MemberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Member Service", description = "Memeber service API")
@RestController
@RequestMapping("/member")
public class MemberController {
	
	Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberService memberService;
	
//TODO : format of this comment
	/*
	 * Add new Member
	 * 
	 * @param - member - Member entity
	 * @return - member that is added 
	 * 
	 */
	@Operation(summary = "Add new member",
			description = "Adds new member in collection after checking all valid conditions")
	@ApiResponse(responseCode = "201", description = "New member added")
	@PostMapping("/addMember")
	ResponseEntity<?> addMember(@RequestBody Member member){
		logger.info("Started add member in controller");
		Member newMember = memberService.addNewMember(member);
		if(newMember != null) {
			logger.info("Added new member");
			return ResponseEntity.status(HttpStatus.CREATED).body(member);
		}
		else {
			logger.warn("Warning in add member");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to add this member");
		}
	}
	
	
	/*
	 * Update existing member
	 * 
	 * @param - member
	 * @return - Updated member details
	 * 
	 */
	@Operation(summary = "Update existing member",
			description = "Update an existing member")
	@ApiResponse(responseCode = "202", description = "Accepted")
	@PutMapping("/updateMember")
	ResponseEntity<?> updateMember(@RequestBody Member member){
		logger.info("Started add member");
		Member newMember = memberService.updateOldMember(member);
		if(newMember != null) {
			logger.info("Updated member");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(member);
		}
		else {
			logger.warn("Warning in update member");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to add this member");
		}
	}
	
	
	/*
	 * Find member by MemberId
	 * 
	 * @param - memberId
	 * @return - member with given memberId
	 */
	@Operation(summary = "Find member by memberId",
			description = "Fetches member from collection with the given memberId")
	@ApiResponse(responseCode = "200", description = "Case found")
	@GetMapping("/find/{memberId}")
	ResponseEntity<?> searchMemberByMemberId(@PathVariable String memberId){
		logger.info("Started find member using memberId");
		Member newMember = memberService.searchMemberByMemberId(memberId);
		System.out.println("In member-service");
		if(newMember != null) {
			logger.info("Member with given memberId found");
			return ResponseEntity.status(HttpStatus.OK).body(newMember);
		}
		else {
			logger.warn("Warning in finding member");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("MemberId: "+memberId+" NOT FOUND");
		}
	}
	
	/*
	 * Fetch all members
	 * 
	 * @param
	 * @return - List of all members
	 */
	
	@Operation(summary = "All members",
			description = "Fetches all members from the collection")
	@ApiResponse(responseCode = "200", description = "Successful opeartion")
	@GetMapping
	ResponseEntity<?> findAllMembers(){
		logger.info("Started fetch all members");
		List<Member> members = memberService.getAllMembers();
		if(members.size() > 0) {
			logger.info("Fetched all members");
			return ResponseEntity.status(HttpStatus.OK).body(members);
		}
		else {
			logger.warn("Warning in find all members");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No members in the list");
		}
	}
	
	@Operation(summary = "Delete member",
			description = "Delete member from the collection with given memberId")
	@ApiResponse(responseCode = "200", description = "Successful opeartion")
	@DeleteMapping("/deleteMember/{memberId}") 
	ResponseEntity<?> deleteMemberByMemberId(@PathVariable String memberId){
		logger.info("Started delete member");
		Member member = memberService.searchMemberByMemberId(memberId);
		if(member != null) {
			logger.info("Deleted given member");
			memberService.deleteMemberByMemberId(memberId);
			return ResponseEntity.status(HttpStatus.OK).body("MemberId: "+memberId+" DELETED");
		}
		else {
			logger.warn("Warning in delete member");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("MemberId: "+memberId+" cannot be deleted");
		}
	}
}
