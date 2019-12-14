package com.hospitalsheet.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.command.Command;
import com.data.dto.DataDto;
import com.hospitalsheet.dao.HospitalSheetDAO;
import com.hospitalsheet.dto.HospitalSheetDTO;
import com.hospitalsheet.dto.SimpleHospitalSheetDTO;
import com.ipfs.dao.IPFSDao;
import com.ipfs.singleton.MyIPFS;
import com.patient.dao.PatientDAO;

import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;

public class HSGetSheet implements Command{

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		String patient_name = (String)request.getParameter("patient_name");
		String patient_number = (String)request.getParameter("patient_number_1") + "-" + (String)request.getParameter("patient_number_2");
		
		PatientDAO PDao = new PatientDAO();
		
		if(PDao.isExistPatient(patient_name, patient_number)) {
			HttpSession session = request.getSession();
			String id = (String)session.getAttribute("id");
			String name = (String)session.getAttribute("name");
			HospitalSheetDAO dao = new HospitalSheetDAO();
			ArrayList<SimpleHospitalSheetDTO> dtos = dao.getListById(patient_name, patient_number);
			if(dtos != null) {
				request.setAttribute("list", dtos);
				session.setAttribute("search_patient_name", patient_name);
				session.setAttribute("search_patient_number", patient_number);
				String viewHash = MyIPFS.getInstance().viewLogUpdate(id, name, patient_name, patient_number);
				if(viewHash != null) {
					IPFSDao IDao = new IPFSDao();
					IDao.insertLog(viewHash, "view");
				}
				return 1;
			}
		}
		return 0;
	}

}
