package com.danielalvc.public_holiday.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.danielalvc.public_holiday.client.DateClient;
import com.danielalvc.public_holiday.model.CommonHoliday;
import com.danielalvc.public_holiday.model.CountryHolidays;
import com.danielalvc.public_holiday.model.dto.HolidayDTO;

@RestController
public class HolidayService {

	@Autowired
	DateClient dateClient;

	@GetMapping("/getLastThreeHolidays/{countryCode}")
	public List<HolidayDTO> getLastThreeHolidays(@PathVariable String countryCode) {

		return new ArrayList<HolidayDTO>();
	}

	@GetMapping("/getNumberOfHolidaysOnWeek/{year}")
	public List<Integer> getNumberOfHolidaysOnWeek(@PathVariable Integer year, @RequestParam String countryCodes) {

		return new ArrayList<Integer>();
	}

	@GetMapping("/getNumberOfHolidaysOnWeekWithCountries/{year}")
	public List<CountryHolidays> getNumberOfHolidaysOnWeekWithCountries(@PathVariable Integer year,
			@RequestParam String countryCodes) {

		return new ArrayList<CountryHolidays>();
	}

	@GetMapping("/getCommonHolidays/{year}/{firstCountryCode}/{secondCountryCode}")
	public List<CommonHoliday> getCommonHolidays(@PathVariable Integer year, @PathVariable String firstCountryCode,
			@PathVariable String secondCountryCode) {

		return new ArrayList<CommonHoliday>();
	}

}
