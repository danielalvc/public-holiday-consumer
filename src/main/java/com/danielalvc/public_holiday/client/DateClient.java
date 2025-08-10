package com.danielalvc.public_holiday.client;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.danielalvc.public_holiday.model.dto.HolidayDTO;

@Component
public class DateClient {

	RestClient restClient = RestClient.builder().build();

	@Value("${holiday.api}" + "${holiday.api.prefix}")
	private String uriBase;

	@Value("${holiday.api.holidays}")
	private String countryHolidaysInYearApi;

	public List<HolidayDTO> getCountryHolidays(Integer year, String countryCode) {

		List<HolidayDTO> holidayDTOs =
				restClient.get()
				.uri(uriBase + countryHolidaysInYearApi, year, countryCode)
				.accept(APPLICATION_JSON)
				.retrieve()
				.body(new ParameterizedTypeReference<List<HolidayDTO>>() {});

		return holidayDTOs;
	}

}
