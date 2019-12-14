package com.hospitalsheet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import com.data.dto.DataDto;
import com.hospitalsheet.dto.HospitalSheetDTO;
import com.hospitalsheet.dto.SimpleHospitalSheetDTO;

public class HospitalSheetDAO {
DataSource dataSrc;
	
	public HospitalSheetDAO() {
		try {
			Context context = new InitialContext();
			dataSrc = (DataSource)context.lookup("java:comp/env/jdbc/Oracle");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<SimpleHospitalSheetDTO> getListById(String patient_name, String patient_number){
		ArrayList<SimpleHospitalSheetDTO> dtos = null;
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rSet = null;
		try {
			conn = dataSrc.getConnection();
			preStmt = conn.prepareStatement("select hs.issue_number, d.name, hs.hospital_name, hs.diagnosis_content, hs.diagnosis_date " + 
					"from hospitalsheet hs, doctor d " + 
					"where hs.patient_number=? " + 
					"AND hs.doctor_number = d.license_number " + 
					"order by hs.diagnosis_date desc ");
			preStmt.setString(1, patient_number);
			rSet = preStmt.executeQuery();
			dtos = new ArrayList<SimpleHospitalSheetDTO>();
			while(rSet.next()) 
				dtos.add(new SimpleHospitalSheetDTO(rSet.getInt(1), patient_name, rSet.getString(2), rSet.getString(3), rSet.getString(4),rSet.getDate(5)));
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rSet != null) rSet.close();
				if(preStmt != null) preStmt.close();
				if(conn != null) conn.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}
	
	public boolean addHospitalSheet(HospitalSheetDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSrc.getConnection();
			pstmt = conn.prepareStatement("insert into hospitalsheet values(?,?,?,?,?,?,?)");
			pstmt.setInt(1, dto.getIssue_number());
			pstmt.setString(2, dto.getPatient_number());
			pstmt.setInt(3, dto.getDoctor_number());
			pstmt.setString(4, dto.getHostpital_name());
			pstmt.setString(5, dto.getDiagnosis_content());
			pstmt.setDate(6, dto.getDiagnosis_date());
			pstmt.setDate(7, dto.getPrint_date());
			int result = pstmt.executeUpdate();
			if(result == 1)
				return true;
		}catch(SQLException e) {
			System.out.println("[addHospitalSheet] sql error : "+e.getMessage());
			
		}
		return false;
	}
	
	public int getIssueNumber() {
		int ret = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSrc.getConnection();
			while(true) {
				pstmt = conn.prepareStatement("select count(*) from hospitalsheet where issue_number=?");
				int issue_number =(int)(Math.random()*(90000000-1))+10000000;
				pstmt.setInt(1, issue_number);
				ResultSet rs = pstmt.executeQuery();
				rs.next();
				if(rs.getInt(1) == 0)
					return issue_number;
			}
		}catch(SQLException e) {
			System.out.println("[getIssueNumber] sql error : "+e.getMessage());
		}
		return ret;
	}
}
