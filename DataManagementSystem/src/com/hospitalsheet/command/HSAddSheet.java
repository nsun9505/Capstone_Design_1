package com.hospitalsheet.command;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.command.Command;
import com.hospitalsheet.dao.HospitalSheetDAO;
import com.hospitalsheet.dto.HospitalSheetDTO;

public class HSAddSheet implements Command{

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int doctor_number = Integer.parseInt((String)session.getAttribute("id"));
		String patient_number = (String)request.getParameter("patient_number");
		String hostpital_name = (String)request.getParameter("hospital_name");
		String diagnosis_date = (String) request.getParameter("diagnosis_date");
		String diagnosis_content = (String) request.getParameter("diagnosis_content");
		HospitalSheetDAO dao = new HospitalSheetDAO();
		HospitalSheetDTO dto = new HospitalSheetDTO(dao.getIssueNumber(), patient_number, doctor_number, hostpital_name, diagnosis_content, Date.valueOf(diagnosis_date), Date.valueOf(diagnosis_date));
		if(dao.addHospitalSheet(dto))
			return 1;
		else
			return 0;
	}
	
}
