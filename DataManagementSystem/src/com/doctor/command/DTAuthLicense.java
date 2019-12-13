package com.doctor.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.command.Command;
import com.doctor.dao.DoctorDAO;

public class DTAuthLicense implements Command{

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		DoctorDAO dao = new DoctorDAO();
		int ret = 0;
		String str = (String)request.getParameter("license_number");
		int license = Integer.parseInt(str);
		System.out.println(str);
		String name = dao.isExistDoctor(license);
		System.out.println(name);
		if(name != null) {
			HttpSession session = request.getSession();
			session.setAttribute("name", name);
			ret = 1;
		}
		
		return ret;
	}
}
