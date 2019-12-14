package com.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.command.Command;
import com.doctor.dao.DoctorDAO;
import com.doctor.dto.DoctorDTO;
import com.employee.dao.EmployeeDAO;
import com.employee.dto.EmployeeDTO;
import com.member.dao.MemberDao;

public class MLoginCommand implements Command{
	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String user_id = request.getParameter("id");
		String user_pw = request.getParameter("pw");
		
		MemberDao dao = new MemberDao();
		int ret = dao.memberCheck(user_id, user_pw);
		if(ret == 1) {
			request.setAttribute("id", user_id);
			EmployeeDAO EDao = new EmployeeDAO();
			EmployeeDTO EDto = EDao.isExistEmployee(Integer.parseInt(user_id));
			HttpSession session = request.getSession();
			if(EDto == null) {
				DoctorDAO DDao = new DoctorDAO();
				DoctorDTO DDto = DDao.isExistDoctor(Integer.parseInt(user_id));
				session.setAttribute("name", DDto.getName());
				session.setAttribute("hospital_name", DDto.getHospital_name());
			} else {
				session.setAttribute("name", EDto.getName());
				session.setAttribute("hospital_name", EDto.getHospital_name());
			}
		}
		return ret;
	}
}
