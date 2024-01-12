package com.deloitte.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.deloitte.dummy.Member;

@FeignClient(name="MEMBER-SERVICE")
public interface MemberFeign {

	@GetMapping("/member/{memberId}")
	Member searchMemberByMemberId(@PathVariable String memberId);
	
	@GetMapping("/member")
	List<Member> findAllMembers();
}
