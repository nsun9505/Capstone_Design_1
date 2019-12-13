package com.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.command.Command;
import com.member.dao.MemberDao;

public class MOtpCheckCommand implements Command{

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String userCode = request.getParameter("checkCode");
		System.out.println("\n\n" + id + " " + userCode + "\n\n");
		MemberDao dao = new MemberDao();
		boolean ret = dao.checkCode(id, userCode);
		if(ret == true) {
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			session.setAttribute("ValidMem", "yes");
			return 1;
		}
		return 0;
	}
}
