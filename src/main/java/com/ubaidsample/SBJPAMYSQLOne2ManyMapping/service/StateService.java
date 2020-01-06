package com.ubaidsample.SBJPAMYSQLOne2ManyMapping.service;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.ubaidsample.SBJPAMYSQLOne2ManyMapping.model.State;
import com.ubaidsample.SBJPAMYSQLOne2ManyMapping.repository.StateRepository;

/*
 * @author Muhammad Ubaid Ur Raheem aka Shahbaz Haroon
 */

@Service
@Transactional
public class StateService {

	private final StateRepository stateRepository;

	public StateService(StateRepository stateRepository) {
		this.stateRepository = stateRepository;
	}

	public List<State> findAll() {
		List<State> stateList = new ArrayList<>();

		for (State state : stateRepository.findAll()) {
			stateList.add(state);
		}
		return stateList;
	}

	public void save(State state) {
		stateRepository.save(state);
	}

	public State findByStateId(Long stateId) {
		return stateRepository.findOne(stateId);
	}

	public void delete(Long stateId) {
		stateRepository.delete(stateId);
	}

	public boolean exist(Long stateId) {
		return stateRepository.exists(stateId);
	}
}