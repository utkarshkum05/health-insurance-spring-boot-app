package com.deloitte.dummy;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Policy {

	private String policyId;
	private boolean cashless;
	private boolean taxBenifit;
	private long coverageAmount;
	private long premiumAmount;
	private LocalDate startDate;
	private LocalDate endDate;
}
	