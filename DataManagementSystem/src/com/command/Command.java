package com.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
	public int execute(HttpServletRequest request, HttpServletResponse response);
}
