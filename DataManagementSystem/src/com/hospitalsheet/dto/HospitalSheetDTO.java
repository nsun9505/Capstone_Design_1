package com.hospitalsheet.dto;

import java.sql.Date;

public class HospitalSheetDTO {
	private int issue_number;
	private String patient_number;
	private int doctor_number;
	private String hostpital_name;
	private String diagnosis_content;
	private Date diagnosis_date;
	private Date print_date;
	
	public HospitalSheetDTO(int issue_number, String patient_number, int doctor_number, String hostpital_name,
			String diagnosis_content, Date diagnosis_date, Date print_date) {
		super();
		this.issue_number = issue_number;
		this.patient_number = patient_number;
		this.doctor_number = doctor_number;
		this.hostpital_name = hostpital_name;
		this.diagnosis_content = diagnosis_content;
		this.diagnosis_date = diagnosis_date;
		this.print_date = print_date;
	}

	public int getIssue_number() {
		return issue_number;
	}

	public void setIssue_number(int issue_number) {
		this.issue_number = issue_number;
	}

	public String getPatient_number() {
		return patient_number;
	}

	public void setPatient_number(String patient_number) {
		this.patient_number = patient_number;
	}

	public int getDoctor_number() {
		return doctor_number;
	}

	public void setDoctor_number(int doctor_number) {
		this.doctor_number = doctor_number;
	}

	public String getHostpital_name() {
		return hostpital_name;
	}

	public void setHostpital_name(String hostpital_name) {
		this.hostpital_name = hostpital_name;
	}

	public String getDiagnosis_content() {
		return diagnosis_content;
	}

	public void setDiagnosis_content(String diagnosis_content) {
		this.diagnosis_content = diagnosis_content;
	}

	public Date getDiagnosis_date() {
		return diagnosis_date;
	}

	public void setDiagnosis_date(Date diagnosis_date) {
		this.diagnosis_date = diagnosis_date;
	}

	public Date getPrint_date() {
		return print_date;
	}

	public void setPrint_date(Date print_date) {
		this.print_date = print_date;
	}
	
	
}
