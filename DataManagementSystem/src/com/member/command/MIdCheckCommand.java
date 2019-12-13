package com.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.command.Command;
import com.member.dao.MemberDao;

public class MIdCheckCommand implements Command{
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String type = request.getParameter("account_type");
		System.out.println(type);
		MemberDao dao = new MemberDao();
		int ret = dao.checkUserId(id);
		if(ret == 1)
			request.setAttribute("checkOkId", id);
		return ret;
	}
}
