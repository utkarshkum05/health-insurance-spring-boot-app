package com.deloitte.service;

import java.util.List;

import com.deloitte.entity.Member;

public interface MemberService {

	Member searchMemberByMemberId(String memberId);
	Member addNewMember(Member member);
	Member updateOldMember(Member member);
	List<Member> getAllMembers();
	void deleteMemberByMemberId(String memberId);
}
