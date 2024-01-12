package com.deloitte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.entity.Member;
import com.deloitte.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberRepository memberRepository;

	@Override
	public Member addNewMember(Member member) {
		return memberRepository.save(member);
	}

	@Override
	public Member updateOldMember(Member member) {
		return memberRepository.save(member);
	}
	
	@Override
	public Member searchMemberByMemberId(String memberId) {
		return memberRepository.findById(memberId).orElse(null);
	}

	@Override
	public List<Member> getAllMembers() {
		return memberRepository.findAll();
	}

	@Override
	public void deleteMemberByMemberId(String memberId) {
		memberRepository.deleteById(memberId);
	}

}
