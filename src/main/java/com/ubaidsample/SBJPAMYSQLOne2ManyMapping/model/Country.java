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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/*
 * @author Muhammad Ubaid Ur Raheem aka Shahbaz Haroon
 */

@Entity
@Table(name = "Country")
public class Country extends MainEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long countryId;

	@Column(unique = true)
	@NotNull
	private String countryCode, countryName;

	@NotNull
	private String capital, currency;

	// Mapping with States
	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<State> states;

	// Default OR No Argument Constructor
	public Country() {
	}

	// Full Argument Constructor
	public Country(String countryCode, String countryName, String capital, String currency) {
		this.countryCode = countryCode;
		this.countryName = countryName;
		this.capital = capital;
		this.currency = currency;
	}

	// Getters & Setters - Start
	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}
	// Getters & Setters - End
}