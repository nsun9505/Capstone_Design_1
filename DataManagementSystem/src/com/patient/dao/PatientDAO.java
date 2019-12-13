package com.patient.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class PatientDAO {
	DataSource dataSrc;
	
	public PatientDAO() {
		try {
			Context context = new InitialContext();
			dataSrc = (DataSource)context.lookup("java:comp/env/jdbc/Oracle");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean isExistPatient(String name, String reg_number) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean ret = false;
		try {
			conn = dataSrc.getConnection();
			pstmt = conn.prepareStatement("select count(*) from patient where name=? AND reg_number=?");
			pstmt.setString(1, name);
			pstmt.setString(2,reg_number);
			rs = pstmt.executeQuery();
			rs.next();
			if(rs.getInt(1) == 1)
				ret = true;
			
		}catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		return ret;
	}
}
