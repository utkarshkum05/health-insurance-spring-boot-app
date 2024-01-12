package com.deloitte.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
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
	

//	public Member() {
//		
//	}
//
//	public Member(String memberId, String firstName, String lastName, LocalDate dateOfBirth, String gender,
//			String email, String phoneNo, String employer, String policyId, LocalDate purchaseDate, LocalDate expiryDate) {
//		super();
//		this.memberId = memberId;
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.dateOfBirth = dateOfBirth;
//		this.gender = gender;
//		this.email = email;
//		this.phoneNo = phoneNo;
//		this.employer = employer;
//		this.policyId = policyId;
//		this.purchaseDate = purchaseDate;
//		this.expiryDate = expiryDate;
//	}

//	public String getMemberId() {
//		return memberId;
//	}
//
//	public void setMemberId(String memberId) {
//		this.memberId = memberId;
//	}
//
//	public String getFirstName() {
//		return firstName;
//	}
//
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//
//	public String getLastName() {
//		return lastName;
//	}
//
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//
//	public LocalDate getDateOfBirth() {
//		return dateOfBirth;
//	}
//
//	public void setDateOfBirth(LocalDate dateOfBirth) {
//		this.dateOfBirth = dateOfBirth;
//	}
//
//	public String getGender() {
//		return gender;
//	}
//
//	public void setGender(String gender) {
//		this.gender = gender;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getPhoneNo() {
//		return phoneNo;
//	}
//
//	public void setPhoneNo(String phoneNo) {
//		this.phoneNo = phoneNo;
//	}
//
//	public String getEmployer() {
//		return employer;
//	}
//
//	public void setEmployer(String employer) {
//		this.employer = employer;
//	}
//
//	public String getPolicyId() {
//		return policyId;
//	}
//
//	public void setPolicyId(String policyId) {
//		this.policyId = policyId;
//	}
//	
//	public LocalDate getpurchaseDate() {
//		return purchaseDate;
//	}
//
//	public void setpurchaseDate(LocalDate purchaseDate) {
//		this.purchaseDate = purchaseDate;
//	}
//
//	public LocalDate getExpiryDate() {
//		return expiryDate;
//	}
//
//	public void setExpiryDate(LocalDate expiryDate) {
//		this.expiryDate = expiryDate;
//	}
//	
}
