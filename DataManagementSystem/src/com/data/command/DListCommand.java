package com.data.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.command.Command;
import com.data.dao.DataDao;
import com.data.dto.DataDto;
import com.member.dao.MemberDao;

public class DListCommand implements Command{
	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		DataDao DDao = new DataDao();
		MemberDao MDao = new MemberDao();
		System.out.println(id);
		int level = MDao.getUserLevelById(id);
		ArrayList<DataDto> dtos = DDao.getListById(level);
		for(DataDto dto : dtos) {
			System.out.println(dto.getSite_id() + " " + dto.getSite_pw() + " " + dto.getSite_url());
		}
		request.setAttribute("list", dtos);
		request.setAttribute("level", level);
		return 1;
	}
}
