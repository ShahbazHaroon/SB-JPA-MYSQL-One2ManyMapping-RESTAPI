package com.ubaidsample.SBJPAMYSQLOne2ManyMapping.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
 * @author Muhammad Ubaid Ur Raheem aka Shahbaz Haroon
 */

@Entity
@Table(name = "State")
public class State extends MainEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long stateId;

	@Column(unique = true)
	@NotNull
	private String stateName;

	// Mapping with Country
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "countryId", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Country country;

	// Mapping with City
	@OneToMany(mappedBy = "state", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<City> cities;

	// Default OR No Argument Constructor
	public State() {
	}

	// Full Argument Constructor
	public State(String stateName, Country country) {
		this.stateName = stateName;
		this.country = country;
	}

	// Getters & Setters - Start
	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	// Getters & Setters - End
}