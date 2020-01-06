package com.ubaidsample.SBJPAMYSQLOne2ManyMapping.service;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.ubaidsample.SBJPAMYSQLOne2ManyMapping.model.Country;
import com.ubaidsample.SBJPAMYSQLOne2ManyMapping.repository.CountryRepository;

/*
 * @author Muhammad Ubaid Ur Raheem aka Shahbaz Haroon
 */

@Service
@Transactional
public class CountryService {

	private final CountryRepository countryRepository;

	public CountryService(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	public List<Country> findAll() {
		List<Country> countryList = new ArrayList<>();

		for (Country country : countryRepository.findAll()) {
			countryList.add(country);
		}
		return countryList;
	}

	public void save(Country country) {
		countryRepository.save(country);
	}

	public Country findByCountryId(Long countryId) {
		return countryRepository.findOne(countryId);
	}

	public void delete(Long countryId) {
		countryRepository.delete(countryId);
	}

	public boolean exist(Long countryId) {
		return countryRepository.exists(countryId);
	}
}