package com.employee.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.command.Command;
import com.doctor.dao.DoctorDAO;
import com.employee.dao.EmployeeDAO;
import com.employee.dto.EmployeeDTO;

public class EMPAuthNumber implements Command{

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		EmployeeDAO dao = new EmployeeDAO();
		int ret = 0;
		int license = Integer.parseInt((String)request.getParameter("employee_number"));
		EmployeeDTO dto = dao.isExistEmployee(license);
		if(dto != null) {
			HttpSession session = request.getSession();
			session.setAttribute("name", dto.getName());
			session.setAttribute("hospital_name", dto.getHospital_name());
			ret = 1;
		}
		return ret;
	}
	
}
