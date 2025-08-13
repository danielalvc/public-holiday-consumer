package com.danielalvc.public_holiday.client;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClient;

import com.danielalvc.public_holiday.model.dto.HolidayDTO;

@Component
public class DateClient {

	public static final Logger LOGGER = LoggerFactory.getLogger(DateClient.class);

	private RestClient restClient = RestClient.builder().build();

	@Value("${holiday.api}" + "${holiday.api.prefix}")
	private String uriBase;

	@Value("${holiday.api.holidays}")
	private String countryHolidaysInYearApi;

	public List<HolidayDTO> getCountryHolidays(Integer year, String countryCode) {

		LOGGER.info("Fetching holidays in year {} for country code {}...", year, countryCode);
		
		try {
		ResponseEntity<List<HolidayDTO>> holidayDTOsResponse =
				restClient.get()
				.uri(uriBase + countryHolidaysInYearApi, year, countryCode)
				.accept(APPLICATION_JSON)
				.retrieve()
				.toEntity(new ParameterizedTypeReference<List<HolidayDTO>>() {});

		if (holidayDTOsResponse.getStatusCode() == NO_CONTENT) {
			LOGGER.warn("Country by code {} doesn't have public holidays registered!", countryCode);
			return new ArrayList<HolidayDTO>();
		}
		
		return holidayDTOsResponse.getBody();
		
		} catch(HttpStatusCodeException ex) {
			switch((HttpStatus) ex.getStatusCode()) {
				case NOT_FOUND:
					LOGGER.error("Country code {} not found", countryCode);
					throw ex;
				case BAD_REQUEST:
					LOGGER.error("Bad request for country code {}: {}", countryCode, ex.getMessage());
					throw ex;
				default:
					LOGGER.error("Internal error calling {} with country {} in year {}: {}",
							countryHolidaysInYearApi, countryCode, year, ex.getMessage());
					throw ex;
			}
		}
	}

}
