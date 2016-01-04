package com.coc.ak.database;

public class COCData {

	private long id;
	private String acronym;
	private String value;

	public COCData() {

	}

	public COCData(long i, String acronym, String value) {
		this.id = i;
		this.acronym = acronym;
		this.value = value;
	}

	public COCData(String acronym, String value) {
		this.acronym = acronym;
		this.value = value;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String d) {
		this.value = d;
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String value) {
		this.acronym = value;
	}

	// Will be used by the ArrayAdapter in the ListView
	// @Override
	// public String toString() {
	// return data;
	// }
}
