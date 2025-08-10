package com.danielalvc.public_holiday.model.dto;

import java.time.LocalDate;

public final class HolidayDTO {

	private LocalDate date;
	private String localName;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate localDate) {
		this.date = localDate;
	}

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	@Override
	public String toString() {
		return "HolidayDTO [date=" + date + ", localName=" + localName + "]";
	}

}
