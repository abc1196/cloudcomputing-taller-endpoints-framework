package com.cloudcomputing.dimayor.dto;

import java.util.Date;

public class TeamDTO {

	private Integer id;

	private String name;

	private Date fundationYear;

	private int titles;

	public TeamDTO() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getFundationYear() {
		return fundationYear;
	}

	public void setFundationYear(Date fundationYear) {
		this.fundationYear = fundationYear;
	}

	public int getTitles() {
		return titles;
	}

	public void setTitles(int titles) {
		this.titles = titles;
	}

}
