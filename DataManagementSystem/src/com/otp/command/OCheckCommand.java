package com.otp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.command.Command;
import com.otp.dao.OtpDao;

public class OCheckCommand implements Command{

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String userCode = request.getParameter("checkCode");
//		System.out.println("\n\n" + id + " " + userCode + "\n\n");
		OtpDao dao = new OtpDao();
		boolean ret = dao.checkCode(id, userCode);
		if(ret == true)
			return 1;
		return 0;
	}
}
