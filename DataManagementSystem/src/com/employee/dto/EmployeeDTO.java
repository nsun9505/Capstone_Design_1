package com.employee.dto;

public class EmployeeDTO {
	private int employee_number;
	private String name;
	private String hospital_name;
	
	
	public EmployeeDTO(int employee_number, String name, String hospital_name) {
		super();
		this.employee_number = employee_number;
		this.name = name;
		this.hospital_name = hospital_name;
	}
	public int getEmployee_number() {
		return employee_number;
	}
	public void setEmployee_number(int employee_number) {
		this.employee_number = employee_number;
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
