package com.danielalvc.public_holiday.model;

public class CountryHolidays {

	private String name;
	private Integer holidayNumber;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getHolidayNumber() {
		return holidayNumber;
	}

	public void setHolidayNumber(Integer holidayNumber) {
		this.holidayNumber = holidayNumber;
	}

	@Override
	public String toString() {
		return "CountryHolidays [name=" + name + ", holidayNumber=" + holidayNumber + "]";
	}

}
