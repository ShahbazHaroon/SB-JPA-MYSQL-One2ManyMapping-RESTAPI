package com.ubaidsample.SBJPAMYSQLOne2ManyMapping.service;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.ubaidsample.SBJPAMYSQLOne2ManyMapping.model.City;
import com.ubaidsample.SBJPAMYSQLOne2ManyMapping.repository.CityRepository;

/*
 * @author Muhammad Ubaid Ur Raheem aka Shahbaz Haroon
 */

@Service
@Transactional
public class CityService {

	private final CityRepository cityRepository;

	public CityService(CityRepository cityRepository) {
		this.cityRepository = cityRepository;
	}

	public List<City> findAll() {
		List<City> cityList = new ArrayList<>();

		for (City city : cityRepository.findAll()) {
			cityList.add(city);
		}
		return cityList;
	}

	public void save(City city) {
		cityRepository.save(city);
	}

	public City findByCityId(Long cityId) {
		return cityRepository.findOne(cityId);
	}

	public void delete(Long cityId) {
		cityRepository.delete(cityId);
	}

	public boolean exist(Long cityId) {
		return cityRepository.exists(cityId);
	}
}