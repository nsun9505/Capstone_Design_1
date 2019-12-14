package com.employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.employee.dto.EmployeeDTO;

public class EmployeeDAO {
	DataSource dataSrc;

	public EmployeeDAO() {
		try {
			Context context = new InitialContext();
			dataSrc = (DataSource)context.lookup("java:comp/env/jdbc/Oracle");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public EmployeeDTO isExistEmployee(int employee_number) {
		String name = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EmployeeDTO dto= null;
		try {
			conn = dataSrc.getConnection();
			pstmt = conn.prepareStatement("select * from employee where employee_number=?");
			pstmt.setInt(1, employee_number);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new EmployeeDTO(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return dto;
	}
}
