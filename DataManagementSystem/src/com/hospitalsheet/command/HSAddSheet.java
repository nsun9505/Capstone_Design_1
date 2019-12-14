package com.hospitalsheet.command;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.command.Command;
import com.hospitalsheet.dao.HospitalSheetDAO;
import com.hospitalsheet.dto.HospitalSheetDTO;
import com.ipfs.dao.IPFSDao;
import com.ipfs.singleton.MyIPFS;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;

public class HSAddSheet implements Command{

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		String ret = "";
		String type= null;
		HttpSession session = request.getSession();
		int doctor_number = Integer.parseInt((String)session.getAttribute("id"));
		String patient_number = (String)request.getParameter("patient_number");
		String hostpital_name = (String)request.getParameter("hospital_name");
		String diagnosis_date = (String) request.getParameter("diagnosis_date");
		String diagnosis_content = (String) request.getParameter("diagnosis_content");
		HospitalSheetDAO dao = new HospitalSheetDAO();
		HospitalSheetDTO dto = new HospitalSheetDTO(dao.getIssueNumber(), patient_number, doctor_number, hostpital_name, diagnosis_content, Date.valueOf(diagnosis_date), Date.valueOf(diagnosis_date));
		if(dao.addHospitalSheet(dto)) {
			String operation_type = (String)request.getParameter("operation_type");
			if(operation_type.equals("add"))
				type = "[add]";
			else if(operation_type.equals("modify"))
				type="[modify]";
			else 
				type = null;
			
			if(type != null) {
				ret = MyIPFS.getInstance().addLogUpdate(dto.getIssue_number(), patient_number, doctor_number, diagnosis_date, type);
				IPFSDao IDao = new IPFSDao();
				IDao.insertLog(ret, type);
			}
			return 1;
		}else {
			return 0;
		}
	}
	
}
