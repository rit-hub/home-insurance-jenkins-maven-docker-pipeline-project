package com.cg.ritam.HomeInsurance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ritam.HomeInsurance.entities.Policy;
import com.cg.ritam.HomeInsurance.exceptions.PolicyNotFoundException;
import com.cg.ritam.HomeInsurance.repository.IPolicyRepository;


@Service
public class PolicyServiceImpl implements IPolicyService{
	
	@Autowired
	IPolicyRepository iPolicyRepository;
	
	@Override
	public Policy addPolicy(Policy policy) {
		return this.iPolicyRepository.save(policy);
	}

	@Override
	public Policy updatePolicy(Policy policy) throws PolicyNotFoundException {
		
		Optional<Policy> op = this.iPolicyRepository.findById(String.valueOf(policy.getPolicyId()));
		if(op.isPresent()) {
		return this.iPolicyRepository.save(policy);
		}
		else throw new PolicyNotFoundException("NO Policy Found !!");	
	}

	@Override
	public Policy findPolicyById(int policyId) throws PolicyNotFoundException {
		Optional<Policy> op = this.iPolicyRepository.findById(String.valueOf(policyId));
		if(op.isPresent()) return op.get();
		else throw new PolicyNotFoundException("NO Policy Found !!");	
	}
	
	@Override
	public Policy removePolicy(int policyId) throws PolicyNotFoundException {
		Optional<Policy> op = this.iPolicyRepository.findById(String.valueOf(policyId));
		if(op.isPresent()) {
			Policy entity = op.get();
			this.iPolicyRepository.delete(entity);
			return entity;
		}
		else throw new PolicyNotFoundException("Policy not found!!");
	}

	@Override
	public List<Policy> showAllPolicies() {
		return this.iPolicyRepository.findAll();
	}

}
