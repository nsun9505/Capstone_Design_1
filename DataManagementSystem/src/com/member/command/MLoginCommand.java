package com.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.command.Command;
import com.member.dao.MemberDao;

public class MLoginCommand implements Command{
	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String user_id = request.getParameter("id");
		String user_pw = request.getParameter("pw");
		
		MemberDao dao = new MemberDao();
		int ret = dao.memberCheck(user_id, user_pw);
		if(ret == 1)
			request.setAttribute("id", user_id);
		System.out.println(user_id + " " + user_pw);
//		System.out.println("logincheck : " + ret);
		return ret;
	}
}
