package com.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.command.Command;
import com.member.dao.MemberDao;

public class MLevelCheckCommand implements Command{

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		int level = Integer.parseInt(request.getParameter("level"));
		String requestTask = request.getParameter("requestTask");
		MemberDao dao = new MemberDao();
		int ret = dao.checkLevel(level, requestTask);
		return ret;
	}
}
