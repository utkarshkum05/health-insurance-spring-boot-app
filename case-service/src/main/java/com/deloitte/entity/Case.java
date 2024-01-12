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
public class Case {
	
	@Id
	private String caseId;
	private String memberId;
	private String policyId;
	private long claimAmount;
	private LocalDate claimDate;	
}
