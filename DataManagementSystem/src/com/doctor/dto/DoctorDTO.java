package com.doctor.dto;

public class DoctorDTO {
	private int license_number;
	private String name;
	private String hospital_name;
	
	public DoctorDTO(int license, String name, String hostpital_name) {
		this.license_number = license;
		this.name = name;
		this.hospital_name = hostpital_name;
	}
	
	
	public int getLicense_number() {
		return license_number;
	}
	public void setLicense_number(int license_number) {
		this.license_number = license_number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHospital_name() {
		return hospital_name;
	}
	public void setHospital_name(String hospital_name) {
		this.hospital_name = hospital_name;
	}
	
	
}
