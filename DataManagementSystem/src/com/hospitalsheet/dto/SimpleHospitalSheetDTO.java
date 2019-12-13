package com.hospitalsheet.dto;

import java.sql.Date;

import oracle.sql.DATE;

public class SimpleHospitalSheetDTO {
	private int issue_number;
	private String patient_name;
	private String doctor_name;
	private String hospital_name;
	private String content;
	private Date diagnosis_date;
	public SimpleHospitalSheetDTO(int issue_number, String patient_name, String doctor_name, String hospital_name,
			String content, Date diagnosis_date) {
		super();
		this.issue_number = issue_number;
		this.patient_name = patient_name;
		this.doctor_name = doctor_name;
		this.hospital_name = hospital_name;
		this.content = content;
		this.diagnosis_date = diagnosis_date;
	}
	public int getIssue_number() {
		return issue_number;
	}
	public void setIssue_number(int issue_number) {
		this.issue_number = issue_number;
	}
	public String getPatient_name() {
		return patient_name;
	}
	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}
	public String getDoctor_name() {
		return doctor_name;
	}
	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}
	public String getHospital_name() {
		return hospital_name;
	}
	public void setHospital_name(String hospital_name) {
		this.hospital_name = hospital_name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDiagnosis_date() {
		return diagnosis_date;
	}
	public void setDiagnosis_date(Date diagnosis_date) {
		this.diagnosis_date = diagnosis_date;
	}
	
	
}
