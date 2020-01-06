package com.ubaidsample.SBJPAMYSQLOne2ManyMapping.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ubaidsample.SBJPAMYSQLOne2ManyMapping.model.Country;
import com.ubaidsample.SBJPAMYSQLOne2ManyMapping.service.CountryService;

/*
 * @author Muhammad Ubaid Ur Raheem aka Shahbaz Haroon
 */

@RestController
@PropertySource({ "classpath:messages.properties" })
public class CountryController {

	private static final Logger logger = Logger.getLogger(CountryController.class);

	// Messages - Start
	@Value("${COUNTRY_CODE}")
	private String COUNTRY_CODE;

	@Value("${COUNTRY_NAME}")
	private String COUNTRY_NAME;

	@Value("${CAPITAL}")
	private String CAPITAL;

	@Value("${CURRENCY}")
	private String CURRENCY;

	@Value("${COUNTRY_CREATED}")
	private String COUNTRY_CREATED;

	@Value("${COUNTRY_SAVE_ERROR}")
	private String COUNTRY_SAVE_ERROR;

	@Value("${COUNTRY_DELETED}")
	private String COUNTRY_DELETED;

	@Value("${COUNTRY_DELETE_ERROR}")
	private String COUNTRY_DELETE_ERROR;
	// Messages - End

	@Autowired
	private CountryService countryService;

	String message;

	@GetMapping("/allCountries")
	public List<Country> findAllCountries() {

		// http://localhost:8000/allCountries

		logger.info("findAllCountries Called...");
		return countryService.findAll();
	}

	@GetMapping("/createCountry")
	//@ResponseStatus(HttpStatus.CREATED)
	public String saveCountry(@ModelAttribute Country country, HttpServletRequest request) {

		// http://localhost:8000/createCountry?countryCode=PK&countryName=Pakistan&capital=Islamabad&currency=PKR
		// http://localhost:8000/createCountry?countryCode=IN&countryName=India&capital=Delhi&currency=IND
		
		//PostMapping
		//http://localhost:8000/createCountry
			//countryCode PK
			//countryName Pakistan
			//capital Islamabad
			//currency PKR

		country.setCreateByAddress(request.getRemoteAddr());
		country.setCreateBy(request.getRemoteUser());
		country.setCreateDate(new Date());
		country.setUpdateByAddress(request.getRemoteAddr());
		country.setUpdateBy(request.getRemoteUser());
		country.setUpdateDate(new Date());

		try {
			if (inputValidation(country.getCountryCode(), country.getCountryName(), country.getCapital(),
					country.getCurrency()) == 0) {
				logger.info("saveCountry Called...");
				countryService.save(country);
				return COUNTRY_CREATED + " " + "countryId = " + country.getCountryId() + "countryName = "
						+ country.getCountryName();

			}
		} catch (Exception ex) {
			logger.info("Error: " + ex);
			return COUNTRY_SAVE_ERROR + " " + ex.toString();
		}
		return message;
	}

	@GetMapping("/updateCountry")
	public Country updateCountry(@RequestParam Long countryId) {

		// http://localhost:8000/updateCountry?=countryId=1

		logger.info("updateCountry Called...");
		try {
			countryService.findByCountryId(countryId);
		} catch (Exception ex) {
			logger.info("Error: " + ex);
			ex.printStackTrace();
		}
		return countryService.findByCountryId(countryId);
	}

	@GetMapping("/deleteCountry")
	//@DeleteMapping("/students/{id}")
	// replace @RequestParam WITH @PathVariable
	// http://localhost:8000/deleteCountry1
	public String deleteCountry(@RequestParam Long countryId) {

		// http://localhost:8000/deleteCountry?=countryId=1

		logger.info("deleteCountry Called...");
		try {
			countryService.delete(countryId);
		} catch (Exception ex) {
			return COUNTRY_DELETE_ERROR + " " + ex.toString();
		}
		return COUNTRY_DELETED;
	}

	private int inputValidation(String countryCode, String countryName, String capital, String currency) {

		logger.info("inputValidation for saveCountry Called...");

		int a = 0;

		if (countryCode == null || countryCode.trim().equals("")) {
			message = COUNTRY_CODE;
			a++;
		} else if (countryName == null || countryName.trim().equals("")) {
			message = COUNTRY_NAME;
			a++;
		} else if (capital == null || capital.equals("")) {
			message = CAPITAL;
			a++;
		} else if (currency == null || currency.equals("")) {
			message = CURRENCY;
			a++;
		}

		return a;
	}
}