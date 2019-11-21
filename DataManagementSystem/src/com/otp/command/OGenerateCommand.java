package com.otp.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.command.Command;
import com.otp.dao.OtpDao;

public class OGenerateCommand implements Command{
	private static final String HOST = "computer.knu.ac.kr";
	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		OtpDao dao = new OtpDao();
		String url = dao.generate(id, HOST);
		request.setAttribute("url", url);
		return 0;
	}
}
