package com.danielalvc.public_holiday.model;

import java.util.Date;

public class CommonHoliday {

	private Date date;
	private String firstLocalName;
	private String secondLocalName;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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
