package com.hospitalsheet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

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
}
