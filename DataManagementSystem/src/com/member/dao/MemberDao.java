package com.member.dao;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.codec.binary.Base32;

public class MemberDao {
	DataSource dataSrc;
	private static final String HOST = "computer.knu.ac.kr";
	private static final String userPassUpdateQuery = "update login set pw=? where login_id=?";
	private static final String getPassQuery = "select login_pw from login where login_id=?";
	private static final String deleteQuery = "delete from login where login_id=?";
	private static final String checkQuery = "select * from login where login_id = ? AND login_pw = ?";
	private static final String insertQuery = "insert into login values(?,?,?,?,?,?)";
	private static final String idCheckQuery = "select * from login where login_id = ?";
//	private static final String UpdateOTPKeyQuery = "update member set otpkey = ? where id = ?";
	private static final String getOtpKeyQuery = "select login_otp from login where login_id = ?";
	public MemberDao() {
		try {
			Context context = new InitialContext();
			dataSrc = (DataSource)context.lookup("java:comp/env/jdbc/Oracle");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int joinMember(String user_id, String user_pw, String user_name, String user_level, String user_account_type) {
		int ret = 0;
		Connection conn = null;
		PreparedStatement preStmt = null;
		int level = Integer.parseInt(user_level);
		try {
			conn = dataSrc.getConnection();
			preStmt = conn.prepareStatement(insertQuery);
			preStmt.setString(1, user_id);
			preStmt.setString(2, user_pw);
			preStmt.setString(3, user_name);
			preStmt.setInt(4, level);
			preStmt.setString(5, user_account_type);
			preStmt.setString(6, generate(user_id));
			ret = preStmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(preStmt != null) preStmt.close();
				if(conn != null) conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return ret;
	}
	
	public int memberCheck(String id, String pw) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rSet = null;
		int ret = 0;
		try {
			conn = dataSrc.getConnection();
			preStmt = conn.prepareStatement(checkQuery);
			preStmt.setString(1, id);
			preStmt.setString(2, pw);
			rSet = preStmt.executeQuery();
			if(rSet.next())
				if(id.equals(rSet.getString("login_id")) && pw.equals(rSet.getString("login_pw")))
					ret = 1;		// login success
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
		return ret;
	}
	public int checkUserId(String user_id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rSet = null;
		int ret = 1;
		try {
			conn = dataSrc.getConnection();
			preStmt = conn.prepareStatement(idCheckQuery);
			preStmt.setString(1, user_id);
			rSet = preStmt.executeQuery();
			if(rSet.next()) {
				if(user_id.equals(rSet.getString("user_id"))) {
					ret = 0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rSet != null) rSet.close();
				if(preStmt != null) preStmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ret;
	}
	
	public int deleteMember(String user_id, String user_pw) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rSet = null;
		int ret = 0;
		try {
			conn = dataSrc.getConnection();
			preStmt = conn.prepareStatement(getPassQuery);
			preStmt.setString(1, user_id);
			rSet = preStmt.executeQuery();
			if(rSet.next())
				if(rSet.getString("user_pw").equals(user_pw))
					ret = 1;
			if(ret == 1) {
				preStmt.close();
				preStmt = conn.prepareStatement(deleteQuery);
				preStmt.setString(1, user_id);
				preStmt.executeUpdate();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rSet != null) rSet.close();
				if(preStmt != null) preStmt.close();
				if(conn != null) conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return ret;
	}
	
	public int updateUserPass(String user_id, String user_pw) {
		int ret = 0;
		Connection conn = null;
		PreparedStatement preStmt = null;
		try {
			conn = dataSrc.getConnection();
			preStmt = conn.prepareStatement(userPassUpdateQuery);
			preStmt.setString(1, user_pw);
			preStmt.setString(2, user_id);
			ret = preStmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(preStmt != null) preStmt.close();
				if(conn != null) conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return ret;
	}
	
	public int getUserLevelById(String id) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rSet = null;
		int level = 0;
		try {
			conn = dataSrc.getConnection();
			preStmt = conn.prepareStatement(idCheckQuery);
			preStmt.setString(1, id);
			rSet = preStmt.executeQuery();
			if(rSet.next())
				level = rSet.getInt("login_level");
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
		return level;
	}
	
	
	public String getOtpUrl(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String url = null;
		try {
			conn = dataSrc.getConnection();
			pstmt = conn.prepareStatement(getOtpKeyQuery);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			url = getQRBarcodeURL(id, HOST, rs.getString(1));
		}catch(SQLException e) {
			System.err.println("sql error : " + e.getMessage());
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch(SQLException e) {
				System.err.println("sql error : " + e.getMessage());
			}
		}
		
		return url;
	}

	public String generate(String user_id) {
	//	HashMap<String, String> map = new HashMap<String, String>();
	//	int ret = 0;
	//	Connection conn = null;
	//	PreparedStatement preStmt = null;
	//	ResultSet rs = null;
		
		byte[] buffer = new byte[5 + 5 * 5];
		new Random().nextBytes(buffer);
		Base32 codec = new Base32();
		byte[] secretKey = Arrays.copyOf(buffer, 10);
		byte[] bEncodedKey = codec.encode(secretKey);

		return new String(bEncodedKey);
//		String url = getQRBarcodeURL(user_id, HOST, encodedKey);
/*		
  		try {
			conn = dataSrc.getConnection();
			preStmt = conn.prepareStatement(UpdateOTPKeyQuery);
			preStmt.setString(1, encodedKey);
			preStmt.setString(2, user_id);
			ret = preStmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(preStmt != null) preStmt.close();
				if(conn != null) conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		// Google OTP 앱에 userName@hostName 으로 저장됨
		// key를 입력하거나 생성된 QR코드를 바코드 스캔하여 등록
		map.put("encodedKey", encodedKey);
		map.put("url", url);
		*/
//		return map;
	}

	public boolean checkCode(String user_id, String userCode) {
		long otpnum = Integer.parseInt(userCode); // Google OTP 앱에 표시되는 6자리 숫자
		long wave = new Date().getTime() / 30000; // Google OTP의 주기는 30초
		boolean result = false;
		String otpkey = null;
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rSet = null;
		try {
			conn = dataSrc.getConnection();
			preStmt = conn.prepareStatement(getOtpKeyQuery);
			preStmt.setString(1, user_id);
			rSet = preStmt.executeQuery();
			if(rSet.next())
				otpkey = rSet.getString("login_otp");
			System.out.println(otpkey);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rSet != null) rSet.close();
				if(preStmt != null) preStmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		try {
			Base32 codec = new Base32();
			byte[] decodedKey = codec.decode(otpkey);
			int window = 3;
			for (int i = -window; i <= window; ++i) {
				long hash = verify_code(decodedKey, wave + i);
				if (hash == otpnum) result = true;
			}
		} catch (InvalidKeyException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}

	private static int verify_code(byte[] key, long t) throws NoSuchAlgorithmException, InvalidKeyException {
		byte[] data = new byte[8];
		long value = t;
		for (int i = 8; i-- > 0; value >>>= 8) {
			data[i] = (byte) value;
		}

		SecretKeySpec signKey = new SecretKeySpec(key, "HmacSHA1");
		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(signKey);
		byte[] hash = mac.doFinal(data);

		int offset = hash[20 - 1] & 0xF;

		// We're using a long because Java hasn't got unsigned int.
		long truncatedHash = 0;
		for (int i = 0; i < 4; ++i) {
			truncatedHash <<= 8;
			// We are dealing with signed bytes:
			// we just keep the first byte.
			truncatedHash |= (hash[offset + i] & 0xFF);
		}

		truncatedHash &= 0x7FFFFFFF;
		truncatedHash %= 1000000;

		return (int) truncatedHash;
	}

	public static String getQRBarcodeURL(String user, String host, String secret) {
		// QR코드 주소 생성
		String format2 = "http://chart.apis.google.com/chart?cht=qr&chs=200x200&chl=otpauth://totp/%s@%s%%3Fsecret%%3D%s&chld=H|0";
		return String.format(format2, user, host, secret);
	}

	public int checkLevel(int level, String requestTask) {
		// TODO Auto-generated method stub
		int ret = 1;
		switch(requestTask) {
		case "add":
			if(level == 2)
				ret = 1;
			break;
		case "delete":
		case "modify":
			if(level == 3)
				ret = 1;
		}
		return ret;
	}
}
