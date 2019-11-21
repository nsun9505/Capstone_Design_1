package com.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.data.dto.DataDto;

public class DataDao {
	DataSource dataSrc;
//	private static String deleteAllQuery = "delete from userList where User_id=?";
//	private static String deleteTaskByStatusQuery = "delete from userList where Use=? AND taskStatus=?";
//	private static String deleteQuery = "delete from userList where User_id=? AND reg_num=?";
//	private static String updateQuery = "update userlist set User_pw=? where User_id=? AND regNum=?";
	private static String getListQuery = "select * from data where data_level <= ?";
	private static String insertDataQuery = "insert into data values(data_seq.nextVal, ?, ?, ?, ?, ?)";
	private static String updateDataQuery = "update data set site_id = ?, site_pw=?, site=?, data_level=?, data_description=?  where data_id = ?";
	private static String deleteDataQuery = "delete data where data_id = ?";
//	private static String insertQuery = "insert into userList values(?,?,?)";
	public DataDao() {
		try {
			Context context = new InitialContext();
			dataSrc = (DataSource)context.lookup("java:comp/env/jdbc/Oracle");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("resource")
	public ArrayList<DataDto> getListById(int level){
		ArrayList<DataDto> dtos = null;
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rSet = null;
		try {
			conn = dataSrc.getConnection();
			preStmt = conn.prepareStatement(getListQuery);
			preStmt.setInt(1, level);
			rSet = preStmt.executeQuery();
			dtos = new ArrayList<DataDto>();
			while(rSet.next()) {
				int data_id = rSet.getInt("data_id");
				String site_id = rSet.getString("site_id");
				String site_pw = rSet.getString("site_pw");
				String site = rSet.getString("site");
				int data_level = rSet.getInt("data_level");
				String data_description = rSet.getString("data_description");
//				System.out.println(User_id + " " + User_pw + " " + site);
				dtos.add(new DataDto(data_id, site_id, site_pw, site, data_level, data_description));
			}
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

	public int addData(String id, int user_level, String site_id, String site_pw, String site_url, int site_level, String site_description) {
		Connection conn = null;
		PreparedStatement pstmt;
		int ret = 0;
		
		if(user_level < 2)
			return 0;
		try {
			conn = dataSrc.getConnection();
			pstmt = conn.prepareStatement(insertDataQuery);
			pstmt.setString(1, site_id);
			pstmt.setString(2, site_pw);
			pstmt.setString(3, site_url);
			pstmt.setInt(4, site_level);
			pstmt.setString(5, site_description);
			
			ret = pstmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println("sql error : " + e.getMessage());
		}
		return ret;
	}
	
	public int deleteData(String id, int user_level, int data_id) {
		Connection conn = null;
		PreparedStatement pstmt;
		int ret = 0;
		
		if(user_level < 3)
			return 0;
		
		try {
			conn = dataSrc.getConnection();
			pstmt = conn.prepareStatement(deleteDataQuery);
			pstmt.setInt(1, data_id);
			ret = pstmt.executeUpdate();
		}catch(SQLException e) {
			System.err.println("sql error : "+e.getMessage());
		}
		return 0;
	}
	
	public int modifyData(String id, int user_level, String site_id, String site_pw, String site_url, int site_level, String site_description, int data_id) {
		Connection conn = null;
		PreparedStatement pstmt;
		int ret = 0;
		
		if(user_level < 3)
			return 0;
		try {
			conn = dataSrc.getConnection();
			pstmt = conn.prepareStatement(updateDataQuery);
			pstmt.setString(1, site_id);
			pstmt.setString(2, site_pw);
			pstmt.setString(3, site_url);
			pstmt.setInt(4, site_level);
			pstmt.setString(5, site_description);
			pstmt.setInt(6, data_id);
			
			ret = pstmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println("sql error : " + e.getMessage());
		}
		return ret;
	}
}
