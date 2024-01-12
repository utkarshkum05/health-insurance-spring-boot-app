package com.deloitte.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Policy {
	
	private String policyId;
	private boolean cashless;
	private boolean taxBenifit;
	private long coverageAmount;
	private long premiumAmount;
	private LocalDate startDate;
	private LocalDate endDate;
	
//	public Policy(boolean cashless, boolean taxBenifit, long coverageAmount, long premiumAmount,
//			LocalDate startDate, LocalDate endDate) {
//		super();
//		this.cashless = cashless;
//		this.taxBenifit = taxBenifit;
//		this.coverageAmount = coverageAmount;
//		this.premiumAmount = premiumAmount;
//		this.startDate = startDate;
//		this.endDate = endDate;
//	}

//	public String getPolicyId() {
//		return policyId;
//	}
//
//	public void setPolicyId(String policyId) {
//		this.policyId = policyId;
//	}
//
//	public boolean isCashless() {
//		return cashless;
//	}
//
//	public void setCashless(boolean cashless) {
//		this.cashless = cashless;
//	}
//
//	public boolean isTaxBenifit() {
//		return taxBenifit;
//	}
//
//	public void setTaxBenifit(boolean taxBenifit) {
//		this.taxBenifit = taxBenifit;
//	}
//
//	public long getCoverageAmount() {
//		return coverageAmount;
//	}
//
//	public void setCoverageAmount(long coverageAmount) {
//		this.coverageAmount = coverageAmount;
//	}
//
//	public long getPremiumAmount() {
//		return premiumAmount;
//	}
//
//	public void setPremiumAmount(long premiumAmount) {
//		this.premiumAmount = premiumAmount;
//	}
//
//	public LocalDate getStartDate() {
//		return startDate;
//	}
//
//	public void setStartDate(LocalDate startDate) {
//		this.startDate = startDate;
//	}
//
//	public LocalDate getEndDate() {
//		return endDate;
//	}
//
//	public void setEndDate(LocalDate endDate) {
//		this.endDate = endDate;
//	}
//	
	
}
