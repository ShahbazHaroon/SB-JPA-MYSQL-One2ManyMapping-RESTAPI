package com.ubaidsample.SBJPAMYSQLOne2ManyMapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ubaidsample.SBJPAMYSQLOne2ManyMapping.model.City;

/*
 * @author Muhammad Ubaid Ur Raheem aka Shahbaz Haroon
 */

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

}