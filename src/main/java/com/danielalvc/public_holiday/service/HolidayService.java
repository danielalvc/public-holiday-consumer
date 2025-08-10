package com.danielalvc.public_holiday.service;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
		return IntStream.range(0, 3)
				.mapToObj(i -> dateClient.getCountryHolidays(LocalDate.now().getYear() - i, countryCode))
				.flatMap(List::stream)
				.filter(h -> h.getDate().isBefore(LocalDate.now()))
				.sorted(Comparator.comparing(HolidayDTO::getDate).reversed())
				.limit(3)
				.collect(Collectors.toList());
	}

	@GetMapping("/getNumberOfHolidaysOnWeek/{year}")
	public List<Integer> getNumberOfHolidaysOnWeek(@PathVariable Integer year, @RequestParam String countryCodes) {
		
		return extractCountryCodesIntoList(countryCodes).stream()
				.map(countryCode -> dateClient.getCountryHolidays(year, countryCode))
				.map(holiday -> (int) holiday.stream()
						.filter(h -> h.getDate().getDayOfWeek() != SATURDAY && h.getDate().getDayOfWeek() != SUNDAY)
						.count())
				.sorted(Comparator.reverseOrder())
				.collect(Collectors.toList());
	}

	@GetMapping("/getNumberOfHolidaysOnWeekWithCountries/{year}")
	public List<CountryHolidays> getNumberOfHolidaysOnWeekWithCountries(@PathVariable Integer year,
			@RequestParam String countryCodes) {
		
		return extractCountryCodesIntoList(countryCodes).stream()
				.map(countryCode -> {
					List<HolidayDTO> holidays = dateClient.getCountryHolidays(year, countryCode);
					
					long weekdayHolidays = holidays.stream()
							.filter(h -> h.getDate().getDayOfWeek() != SATURDAY && h.getDate().getDayOfWeek() != SUNDAY)
							.count();
					
					return new CountryHolidays(countryCode, (int) weekdayHolidays);
				})
				.sorted(Comparator.comparing(CountryHolidays::getName).reversed())
				.sorted(Comparator.comparing(CountryHolidays::getHolidayNumber).reversed())
				.collect(Collectors.toList());
	}

	@GetMapping("/getCommonHolidays/{year}/{firstCountryCode}/{secondCountryCode}")
	public List<CommonHoliday> getCommonHolidays(@PathVariable Integer year, @PathVariable String firstCountryCode,
			@PathVariable String secondCountryCode) {
		
	    List<HolidayDTO> firstCountryHolidays = dateClient.getCountryHolidays(year, firstCountryCode);
	    List<HolidayDTO> secondCountryHolidays = dateClient.getCountryHolidays(year, secondCountryCode);
	    
	    Map<LocalDate, List<HolidayDTO>> firstCountryHolidaysByDate = firstCountryHolidays.stream()
	        .collect(Collectors.groupingBy(HolidayDTO::getDate));
	    
	    Map<LocalDate, List<HolidayDTO>> secondCountryHolidaysByDate = secondCountryHolidays.stream()
	        .collect(Collectors.groupingBy(HolidayDTO::getDate));
	    
	    Set<LocalDate> commonDates = new HashSet<>(firstCountryHolidaysByDate.keySet());
	    commonDates.retainAll(secondCountryHolidaysByDate.keySet());
	    
	    return commonDates.stream()
	        .flatMap(date -> {
	            List<HolidayDTO> firstHolidays = firstCountryHolidaysByDate.get(date);
	            List<HolidayDTO> secondHolidays = secondCountryHolidaysByDate.get(date);
        
	            return firstHolidays.stream()
	                .flatMap(first -> secondHolidays.stream()
	                    .map(second -> new CommonHoliday(date, first.getLocalName(), second.getLocalName())));
	        })
	        .collect(Collectors.toList());
	}

	private List<String> extractCountryCodesIntoList(String countryCodes) {
		return Arrays.stream(countryCodes.trim().split(","))
				.map(String::trim)
				.filter(countryCode -> countryCode.length() == 2)
				.collect(Collectors.toList());
	}
	
}
