package com.danielalvc.public_holiday.model.dto;

import java.util.Date;

public final class HolidayDTO {

	private Date date;
	private String localName;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
