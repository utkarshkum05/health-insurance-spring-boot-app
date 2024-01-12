package com.deloitte.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.entity.Member;

@Repository
public interface MemberRepository extends MongoRepository<Member, String>{

}
