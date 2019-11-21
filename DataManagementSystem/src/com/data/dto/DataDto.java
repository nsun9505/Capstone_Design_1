package com.data.dto;

public class DataDto {
	int data_id;
	String site_id;
	String site_pw;
	String site_url;
	int data_level;
	String data_description;
	
	public DataDto(int data_id, String site_id, String site_pw, String site_url, int data_level,
			String data_description) {
		super();
		this.data_id = data_id;
		this.site_id = site_id;
		this.site_pw = site_pw;
		this.site_url = site_url;
		this.data_level = data_level;
		this.data_description = data_description;
	}

	public int getData_id() {
		return data_id;
	}

	public void setData_id(int data_id) {
		this.data_id = data_id;
	}

	public String getSite_id() {
		return site_id;
	}

	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}

	public String getSite_pw() {
		return site_pw;
	}

	public void setSite_pw(String site_pw) {
		this.site_pw = site_pw;
	}

	public String getSite_url() {
		return site_url;
	}

	public void setSite_url(String site_url) {
		this.site_url = site_url;
	}

	public int getData_level() {
		return data_level;
	}

	public void setData_level(int data_level) {
		this.data_level = data_level;
	}

	public String getData_description() {
		return data_description;
	}

	public void setData_description(String data_description) {
		this.data_description = data_description;
	}
}
