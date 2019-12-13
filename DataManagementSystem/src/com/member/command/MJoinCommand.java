package com.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.command.Command;
import com.member.dao.MemberDao;

public class MJoinCommand implements Command{
	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		String user_name = request.getParameter("name");
		String user_id = request.getParameter("id");
		String user_pw = request.getParameter("pw");
		String user_level = request.getParameter("level");
		String user_account_type = request.getParameter("account_type");
		System.out.println(user_name + " " + user_id + " " + user_pw);
		MemberDao dao = new MemberDao();
		int ret = dao.joinMember(user_id, user_pw, user_name, user_level, user_account_type);
		return ret;
	}
}
