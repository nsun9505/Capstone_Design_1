package com.data.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.command.Command;
import com.data.dao.DataDao;
import com.member.dao.MemberDao;

public class DDeleteCommand implements Command{

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		System.out.println("id");

		int data_id = Integer.parseInt(request.getParameter("data_id"));
		MemberDao MDao = new MemberDao();
		int level = MDao.getUserLevelById(id);
		DataDao DDao = new DataDao();
		int ret = DDao.deleteData(id, level, data_id);
		return ret;
	}
}
