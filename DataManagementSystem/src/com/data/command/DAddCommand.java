package com.data.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.command.Command;
import com.data.dao.DataDao;
import com.member.dao.MemberDao;

public class DAddCommand implements Command{

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		System.out.println("id");
		String site_id = request.getParameter("site_id");
		String site_pw = request.getParameter("site_pw");
		String site_url = request.getParameter("site");
		String site_level = request.getParameter("data_level");
		String site_description = request.getParameter("data_description");
		MemberDao MDao = new MemberDao();
		int level = MDao.getUserLevelById(id);
		DataDao DDao = new DataDao();
		int ret = DDao.addData(id, level, site_id, site_pw, site_url, Integer.parseInt(site_level), site_description);
		return ret;
	}

}