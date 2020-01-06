package com.ubaidsample.SBJPAMYSQLOne2ManyMapping.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ubaidsample.SBJPAMYSQLOne2ManyMapping.model.State;
import com.ubaidsample.SBJPAMYSQLOne2ManyMapping.service.StateService;

/*
 * @author Muhammad Ubaid Ur Raheem aka Shahbaz Haroon
 */

@RestController
@PropertySource({ "classpath:messages.properties" })
public class StateController {

	private static final Logger logger = Logger.getLogger(StateController.class);

	// Messages - Start
	@Value("${STATE_NAME}")
	private String STATE_NAME;

	@Value("${STATE_CREATED}")
	private String STATE_CREATED;

	@Value("${STATE_SAVE_ERROR}")
	private String STATE_SAVE_ERROR;

	@Value("${STATE_DELETED}")
	private String STATE_DELETED;

	@Value("${STATE_DELETE_ERROR}")
	private String STATE_DELETE_ERROR;
	// Messages - End

	@Autowired
	private StateService stateService;

	String message;

	@GetMapping("/allStates")
	public List<State> findAllStates() {

		// http://localhost:8000/allStates

		logger.info("findAllStates Called...");
		return stateService.findAll();
	}

	@GetMapping("/createState")
	public String saveState(@ModelAttribute State state, HttpServletRequest request) {

		// http://localhost:8000/createState?stateName=Pakistan
		// http://localhost:8000/createState?stateName=India

		state.setCreateByAddress(request.getRemoteAddr());
		state.setCreateBy(request.getRemoteUser());
		state.setCreateDate(new Date());
		state.setUpdateByAddress(request.getRemoteAddr());
		state.setUpdateBy(request.getRemoteUser());
		state.setUpdateDate(new Date());

		try {
			if (inputValidation(state.getStateName()) == 0) {
				logger.info("saveState Called...");
				stateService.save(state);
				return STATE_CREATED + " " + "stateId = " + state.getStateId() + "stateName = "
						+ state.getStateName();

			}
		} catch (Exception ex) {
			logger.info("Error: " + ex);
			return STATE_SAVE_ERROR + " " + ex.toString();
		}
		return message;
	}

	@GetMapping("/updateState")
	public State updateState(@RequestParam Long stateId) {

		// http://localhost:8000/updateState?=stateId=1

		logger.info("updateState Called...");
		try {
			stateService.findByStateId(stateId);
		} catch (Exception ex) {
			logger.info("Error: " + ex);
			ex.printStackTrace();
		}
		return stateService.findByStateId(stateId);
	}

	@GetMapping("/deleteState")
	public String deleteState(@RequestParam Long stateId) {

		// http://localhost:8000/deleteCountry?=countryId=1

		logger.info("deleteState Called...");
		try {
			stateService.delete(stateId);
		} catch (Exception ex) {
			return STATE_DELETE_ERROR + " " + ex.toString();
		}
		return STATE_DELETED;
	}

	private int inputValidation(String stateName) {

		logger.info("inputValidation for saveState Called...");

		int a = 0;

		if (stateName == null || stateName.trim().equals("")) {
			message = STATE_NAME;
			a++;
		}

		return a;
	}
}