package com.websystique.springmvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.websystique.springmvc.domain.Country;
@RestController
public class CountryController {
	
	@RequestMapping(value = "/countries", method = RequestMethod.GET)
	public ResponseEntity<List<Country>> getCountries()
	{
		List<Country> listOfCountries = new ArrayList<Country>();
		listOfCountries=createCountryList();
		 return new ResponseEntity<List<Country>>(listOfCountries, HttpStatus.OK);
	}

	@RequestMapping(value = "/country/{id}", method = RequestMethod.GET)
	public Country getCountryById(@PathVariable int id)
	{
		List<Country> listOfCountries = new ArrayList<Country>();
		listOfCountries=createCountryList();

		for (Country country: listOfCountries) {
			if(country.getId()==id)
				return country;
		}
		
		return null;
	}

// Utiliy method to create country list.
	public List<Country> createCountryList()
	{
		Country indiaCountry=new Country(1, "India");
		Country chinaCountry=new Country(4, "China");
		Country nepalCountry=new Country(3, "Nepal");
		Country bhutanCountry=new Country(2, "Bhutan");

		List<Country> listOfCountries = new ArrayList<Country>();
		listOfCountries.add(indiaCountry);
		listOfCountries.add(chinaCountry);
		listOfCountries.add(nepalCountry);
		listOfCountries.add(bhutanCountry);
		return listOfCountries;
	}
}
