package com.frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.command.Command;
import com.data.command.DAddCommand;
import com.data.command.DDeleteCommand;
import com.data.command.DListCommand;
import com.data.command.DModifiyCommand;
import com.doctor.command.DTAuthLicense;
import com.employee.command.EMPAuthNumber;
import com.member.command.MGetOtpQRCode;
import com.member.command.MIdCheckCommand;
import com.member.command.MJoinCommand;
import com.member.command.MLoginCommand;
import com.member.command.MOtpCheckCommand;
import com.otp.command.OCheckCommand;
import com.otp.command.OGenerateCommand;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");

		String viewPage = null;
		Command cmd = null;

		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());

		if (com.equals("/loginCheck.do")) {
			cmd = new MLoginCommand();
			int ret = cmd.execute(request, response);
			if (ret == 1)
				viewPage = "authOTP.do"; // if(ret = 1) viewPage = "authOTP.do";
			else
				viewPage = "loginFail.jsp";
		} else if (com.equals("/joinOk.do")) {
			cmd = new MJoinCommand();
			int ret = cmd.execute(request, response);
			viewPage = "joinOk.jsp";
		} else if (com.equals("/idCheck.do")) {
			cmd = new MIdCheckCommand();
			String type = request.getParameter("account_type");
			int ret = cmd.execute(request, response);
			if (type.equals("의사"))
				viewPage = "doctorJoinForm.jsp";
			else if (type.equals("직원"))
				viewPage = "employeeJoinForm.jsp";
		} else if (com.equals("/authOTP.do")) {
			cmd = new MGetOtpQRCode();
			int ret = cmd.execute(request, response);
			viewPage = "AuthOTP.jsp";
		} else if (com.equals("/CheckOTP.do")) {
			cmd = new MOtpCheckCommand();
			int ret = cmd.execute(request, response);
			if (ret == 1)
				viewPage = "main.jsp";
			else
				viewPage = "loginFail.jsp";
		} else if (com.equals("/list.do")) {
			cmd = new DListCommand();
			int ret = cmd.execute(request, response);
			viewPage = "main.jsp";
		} else if (com.equals("/addData.do")) {
			cmd = new DAddCommand();
			int ret = cmd.execute(request, response);
			if (ret == 1)
				viewPage = "addOk.jsp";
			else
				viewPage = "updateFail.jsp";
		} else if (com.equals("/deleteData.do")) {
			cmd = new DDeleteCommand();
			int ret = cmd.execute(request, response);
			if (ret == 1)
				viewPage = "deleteOk.jsp";
			else
				viewPage = "updateFail.jsp";
		} else if (com.equals("/modifyData.do")) {
			cmd = new DModifiyCommand();
			int ret = cmd.execute(request, response);
			if (ret == 1)
				viewPage = "modifyOk.jsp";
			else
				viewPage = "updateFail.jsp";
		} else if (com.equals("/authDoctor.do")) {
			cmd = new DTAuthLicense();
			int ret = cmd.execute(request, response);
			if (ret == 1)
				viewPage = "doctorJoinForm.jsp";
			else
				viewPage = "authDoctor.html";
		} 
		else if(com.equals("/authEmployee.do")) {
			cmd = new EMPAuthNumber();
			int ret = cmd.execute(request, response);
			if(ret == 1)
				viewPage = "employeeJoinForm.jsp";
			else
				viewPage = "authEmployee.html";
		}
		else {
			viewPage = "notExistCommand.jsp";
		}
		System.out.println(viewPage);
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		return;
	}

}
