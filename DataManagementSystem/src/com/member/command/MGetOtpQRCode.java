package com.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.command.Command;
import com.member.dao.MemberDao;

public class MGetOtpQRCode implements Command {

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		MemberDao dao = new MemberDao();
		String url = dao.getOtpUrl(id);
		if(url != null) {
			request.setAttribute("id", id);
			request.setAttribute("url", url);
			return 1;
		}
		return 0;
	}

}
