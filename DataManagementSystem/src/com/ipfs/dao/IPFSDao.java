package com.ipfs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class IPFSDao {
DataSource dataSrc;
	
	public IPFSDao() {
		try {
			Context context = new InitialContext();
			dataSrc = (DataSource)context.lookup("java:comp/env/jdbc/Oracle");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean insertLog(String hash, String type) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int queryRs = 0;
		try {
			conn = dataSrc.getConnection();
			pstmt = conn.prepareStatement("insert into IPFS_HASH_TABLE values(IPFS_HASH_SEQ.NEXTVAL,?,?)");
			pstmt.setString(1, hash);
			pstmt.setString(2, type);
			queryRs = pstmt.executeUpdate();
			if(queryRs == 1) {
				conn.commit();
				return true;	
			}
		}catch(SQLException e) {
			System.out.println("[insertLog] sql error : "+e.getMessage());
		}
		
		return false;
	}
}
