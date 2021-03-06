package com.ubaidsample.SBJPAMYSQLOne2ManyMapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ubaidsample.SBJPAMYSQLOne2ManyMapping.model.State;

/*
 * @author Muhammad Ubaid Ur Raheem aka Shahbaz Haroon
 */

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

}