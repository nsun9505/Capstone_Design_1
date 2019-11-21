package com.data.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.command.Command;
import com.data.dao.DataDao;
import com.member.dao.MemberDao;

public class DModifiyCommand implements Command{

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		System.out.println("id");
		String data_id = request.getParameter("data_id");
		String site_id = request.getParameter("site_id");
		String site_pw = request.getParameter("site_pw");
		String site_url = request.getParameter("site");
		String data_level = request.getParameter("data_level");
		String data_description = request.getParameter("data_description");
		MemberDao MDao = new MemberDao();
		int level = MDao.getUserLevelById(id);
		DataDao DDao = new DataDao();
		int ret = DDao.modifyData(id, level, site_id, site_pw, site_url, Integer.parseInt(data_level), data_description, Integer.parseInt(data_id));
		return ret;
	}
}
