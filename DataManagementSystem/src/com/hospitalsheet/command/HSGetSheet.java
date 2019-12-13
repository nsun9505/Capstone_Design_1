package com.hospitalsheet.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.command.Command;
import com.data.dto.DataDto;
import com.hospitalsheet.dao.HospitalSheetDAO;
import com.hospitalsheet.dto.HospitalSheetDTO;
import com.hospitalsheet.dto.SimpleHospitalSheetDTO;
import com.patient.dao.PatientDAO;

public class HSGetSheet implements Command{

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		String patient_name = (String)request.getParameter("patient_name");
		String patient_number = (String)request.getParameter("patient_number_1") + "-" + (String)request.getParameter("patient_number_2");
		
		PatientDAO PDao = new PatientDAO();
		
		if(PDao.isExistPatient(patient_name, patient_number)) {
			HospitalSheetDAO dao = new HospitalSheetDAO();
			ArrayList<SimpleHospitalSheetDTO> dtos = dao.getListById(patient_name, patient_number);
			request.setAttribute("list", dtos);
			return 1;
		}
		return 0;
	}

}