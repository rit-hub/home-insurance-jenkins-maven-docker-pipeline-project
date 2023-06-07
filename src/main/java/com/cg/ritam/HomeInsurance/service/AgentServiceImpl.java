package com.cg.ritam.HomeInsurance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ritam.HomeInsurance.entities.Agent;
import com.cg.ritam.HomeInsurance.exceptions.AgentNotFoundException;
import com.cg.ritam.HomeInsurance.repository.IAgentRepository;

@Service
public class AgentServiceImpl implements IAgentService{
	
	@Autowired
	private IAgentRepository iAgentRepository;

	@Override
	public Agent addAgent(Agent agent) {
		return this.iAgentRepository.save(agent);
	}

	@Override
	public Agent updateAgent(Agent agent) throws AgentNotFoundException {
		Optional<Agent> oa = this.iAgentRepository.findById(agent.getAgentId());
		if(oa.isPresent()) {
		return this.iAgentRepository.save(agent);
		}
		else throw new AgentNotFoundException("Enter correct Agent");
	}

	@Override
	public Agent removeAgent(int agentId) throws AgentNotFoundException {
		Optional<Agent> oa = this.iAgentRepository.findById(agentId);
		if(oa.isPresent()) {
			Agent entity = oa.get();
			this.iAgentRepository.delete(entity);
			return entity;
		}
		else throw new AgentNotFoundException("Enter correct Agent");
	}

	@Override
	public Agent findAgentById(int agentId) throws AgentNotFoundException {
		Optional<Agent> oa = this.iAgentRepository.findById(agentId);
		if(oa.isPresent()) {
		return oa.get();
		}
		else throw new AgentNotFoundException("Enter correct Agent");
	}

	@Override
	public List<Agent> viewAllAgents() {
		
		return this.iAgentRepository.findAll();
	}

	

}
