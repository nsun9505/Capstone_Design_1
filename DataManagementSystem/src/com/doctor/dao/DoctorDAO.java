package com.doctor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DoctorDAO {
	DataSource dataSrc;

	public DoctorDAO() {
		try {
			Context context = new InitialContext();
			dataSrc = (DataSource)context.lookup("java:comp/env/jdbc/Oracle");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String isExistDoctor(int license) {
		String name = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSrc.getConnection();
			pstmt = conn.prepareStatement("select name from doctor where license_number=?");
			pstmt.setInt(1, license);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				name = rs.getString(1);
				System.out.println("¿Ã∏ß : " + name);
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return name;
	}
}
