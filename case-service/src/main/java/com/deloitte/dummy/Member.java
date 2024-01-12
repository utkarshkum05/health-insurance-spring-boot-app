package com.deloitte.dummy;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Member {

	@Id
	private String memberId;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String gender;
	private String email;
	private String phoneNo;
	private String employer;
	private String policyId;
	private LocalDate purchaseDate;
	private LocalDate expiryDate;
}