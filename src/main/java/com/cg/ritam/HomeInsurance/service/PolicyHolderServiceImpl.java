package com.cg.ritam.HomeInsurance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ritam.HomeInsurance.entities.PolicyHolder;
import com.cg.ritam.HomeInsurance.exceptions.PolicyHolderNotFoundException;
import com.cg.ritam.HomeInsurance.repository.IPolicyHolderRepository;

@Service
public class PolicyHolderServiceImpl implements IPolicyHolderService{
	
	@Autowired
	IPolicyHolderRepository iPolicyHolderRepository;

	@Override
	public PolicyHolder addPolicyHolder(PolicyHolder policyHolder) {
		return this.iPolicyHolderRepository.save(policyHolder);
	}

	@Override
	public PolicyHolder updatePolicyHolder(PolicyHolder policyHolder) throws PolicyHolderNotFoundException {
		Optional<PolicyHolder> oph = this.iPolicyHolderRepository.findById(policyHolder.getPolicyHolderId());
		if(oph.isPresent()) {
		return this.iPolicyHolderRepository.save(policyHolder);
		}
		else throw new PolicyHolderNotFoundException("policy Holder Not Found!!");
	}

	@Override
	public PolicyHolder findPolicyHolderById(int id) throws PolicyHolderNotFoundException {
		
		Optional<PolicyHolder> oph = this.iPolicyHolderRepository.findById(id);
		if(oph.isPresent()) {
		return oph.get();
		}
		else throw new PolicyHolderNotFoundException("policy Holder Not Found!!");
	}

	@Override
	public PolicyHolder removePolicyHolder(int id) throws PolicyHolderNotFoundException {
		Optional<PolicyHolder> oph = this.iPolicyHolderRepository.findById(id);
		if(oph.isPresent()) {
			PolicyHolder entity = oph.get();
			this.iPolicyHolderRepository.delete(entity);
			return entity;
		}
		else throw new PolicyHolderNotFoundException("policy Holder Not Found!!");
	}

	@Override
	public List<PolicyHolder> showAllPolicyHolders() {
		
		return this.iPolicyHolderRepository.findAll();
	}

}
