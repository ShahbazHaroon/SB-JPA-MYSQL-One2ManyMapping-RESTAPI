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

import com.ubaidsample.SBJPAMYSQLOne2ManyMapping.model.City;
import com.ubaidsample.SBJPAMYSQLOne2ManyMapping.service.CityService;

/*
 * @author Muhammad Ubaid Ur Raheem aka Shahbaz Haroon
 */

@RestController
@PropertySource({ "classpath:messages.properties" })
public class CityController {

	private static final Logger logger = Logger.getLogger(CityController.class);

	// Messages - Start
	@Value("${CITY_CODE}")
	private String CITY_CODE;

	@Value("${CITY_NAME}")
	private String CITY_NAME;

	@Value("${CITY_CREATED}")
	private String CITY_CREATED;

	@Value("${CITY_SAVE_ERROR}")
	private String CITY_SAVE_ERROR;

	@Value("${CITY_DELETED}")
	private String CITY_DELETED;

	@Value("${CITY_DELETE_ERROR}")
	private String CITY_DELETE_ERROR;
	// Messages - End

	@Autowired
	private CityService cityService;

	String message;

	@GetMapping("/allCities")
	public List<City> findAllCities() {

		// http://localhost:8000/allCities

		logger.info("findAllCities Called...");
		return cityService.findAll();
	}

	@GetMapping("/createCity")
	public String saveCity(@ModelAttribute City city, HttpServletRequest request) {

		// http://localhost:8000/createCity?cityCode=PK&cityName=Pakistan
		// http://localhost:8000/createCity?cityCode=IN&cityName=India

		city.setCreateByAddress(request.getRemoteAddr());
		city.setCreateBy(request.getRemoteUser());
		city.setCreateDate(new Date());
		city.setUpdateByAddress(request.getRemoteAddr());
		city.setUpdateBy(request.getRemoteUser());
		city.setUpdateDate(new Date());

		try {
			if (inputValidation(city.getCityCode(), city.getCityName()) == 0) {
				logger.info("saveCity Called...");
				cityService.save(city);
				return CITY_CREATED + " " + "cityId = " + city.getCityId() + "cityName = " + city.getCityName();

			}
		} catch (Exception ex) {
			logger.info("Error: " + ex);
			return CITY_SAVE_ERROR + " " + ex.toString();
		}
		return message;
	}

	@GetMapping("/updateCity")
	public City updateCity(@RequestParam Long cityId) {

		// http://localhost:8000/updateCity?=cityId=1

		logger.info("updateCity Called...");
		try {
			cityService.findByCityId(cityId);
		} catch (Exception ex) {
			logger.info("Error: " + ex);
			ex.printStackTrace();
		}
		return cityService.findByCityId(cityId);
	}

	@GetMapping("/deleteCity")
	public String deleteCity(@RequestParam Long cityId) {

		// http://localhost:8000/deleteCity?=cityId=1

		logger.info("deleteCity Called...");
		try {
			cityService.delete(cityId);
		} catch (Exception ex) {
			return CITY_DELETE_ERROR + " " + ex.toString();
		}
		return CITY_DELETED;
	}

	private int inputValidation(String cityCode, String cityName) {

		logger.info("inputValidation for saveCountry Called...");

		int a = 0;

		if (cityCode == null || cityCode.trim().equals("")) {
			message = CITY_CODE;
			a++;
		} else if (cityName == null || cityName.trim().equals("")) {
			message = CITY_NAME;
			a++;
		}

		return a;
	}
}