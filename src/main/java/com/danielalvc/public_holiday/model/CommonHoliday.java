package com.danielalvc.public_holiday.model;

import java.time.LocalDate;

public class CommonHoliday {

	private LocalDate date;
	private String firstLocalName;
	private String secondLocalName;

	public CommonHoliday() {
		super();
	}

	public CommonHoliday(LocalDate date, String firstLocalName, String secondLocalName) {
		super();
		this.date = date;
		this.firstLocalName = firstLocalName;
		this.secondLocalName = secondLocalName;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getFirstLocalName() {
		return firstLocalName;
	}

	public void setFirstLocalName(String firstLocalName) {
		this.firstLocalName = firstLocalName;
	}

	public String getSecondLocalName() {
		return secondLocalName;
	}

	public void setSecondLocalName(String secondLocalName) {
		this.secondLocalName = secondLocalName;
	}

	@Override
	public String toString() {
		return "CommonHoliday [date=" + date + ", firstLocalName=" + firstLocalName + ", secondLocalName="
				+ secondLocalName + "]";
	}

}
