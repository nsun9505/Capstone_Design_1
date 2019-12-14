package com.doctor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.doctor.dto.DoctorDTO;

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
	
	public DoctorDTO isExistDoctor(int license) {
		String name = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DoctorDTO dto = null;
		try {
			conn = dataSrc.getConnection();
			pstmt = conn.prepareStatement("select * from doctor where license_number=?");
			pstmt.setInt(1, license);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new DoctorDTO(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return dto;
	}
}
