package com.deloitte.controller;

import java.util.List;

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

@RestController
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	/*
	 * Request: 'Member'
	 * Process: 'Adding new member'
	 * Response: 'Newly added member'
	 */
	@PostMapping("/addMember")
	ResponseEntity<?> addMember(@RequestBody Member member){
		Member newMember = memberService.addNewMember(member);
		if(newMember != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(member);
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to add this member");
		}
	}
	
	
	/*
	 * Request: 'Member'
	 * Process: 'Updating already present member'
	 * Response: 'Updated member'
	 */
	@PutMapping("/updateMember")
	ResponseEntity<?> updateMember(@RequestBody Member member){
		Member newMember = memberService.updateOldMember(member);
		if(newMember != null) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(member);
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unable to add this member");
		}
	}
	
	
	/*
	 * Request: 'memberId'
	 * Process: 'Finding member'
	 * Response: 'Member with the requested memberId'
	 */
	@GetMapping("/{memberId}")
	ResponseEntity<?> searchMemberByMemberId(@PathVariable String memberId){
		
		Member newMember = memberService.searchMemberByMemberId(memberId);
		System.out.println("In member-service");
		if(newMember != null) {
			return ResponseEntity.status(HttpStatus.OK).body(newMember);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("MemberId: "+memberId+" NOT FOUND");
		}
	}
	
	@GetMapping("")
	ResponseEntity<?> findAllMembers(){
		List<Member> members = memberService.getAllMembers();
		if(members.size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(members);
		}
		else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No members in the list");
		}
	}
	
	@DeleteMapping("/deleteMember/{memberId}") 
	ResponseEntity<?> deleteMemberByMemberId(@PathVariable String memberId){
		Member member = memberService.searchMemberByMemberId(memberId);
		if(member != null) {
			memberService.deleteMemberByMemberId(memberId);
			return ResponseEntity.status(HttpStatus.OK).body("MemberId: "+memberId+" DELETED");
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("MemberId: "+memberId+" cannot be deleted");
		}
	}
}
