package com.deloitte.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.deloitte.dummy.Policy;

@FeignClient(name="POLICY-SERVICE")
public interface PolicyFeign {

	@GetMapping("policy/find/{policyId}")
	Policy searchPolicyByPolicyId(@PathVariable String policyId);
}
