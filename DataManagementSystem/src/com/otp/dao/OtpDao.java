package com.otp.dao;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.codec.binary.Base32;

public class OtpDao {
	private DataSource dataSrc;
	private static final String UpdateOTPKeyQuery = "update login set otpkey = ? where id = ?";
	private static final String getOtpKeyQuery = "select otpkey from login where id = ?";
	public OtpDao() {
		try {
			Context context = new InitialContext();
			dataSrc = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getOtpKey(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String otpKey = null;
		ResultSet rs = null;
		try {
			conn = dataSrc.getConnection();
			pstmt = conn.prepareStatement(getOtpKeyQuery);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			otpKey = rs.getString(1);
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
		return otpKey;
	}

	public String generate(String user_id, String hostName) {
		//HashMap<String, String> map = new HashMap<String, String>();
		int ret = 0;
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		String otpKey = getOtpKey(user_id);
		if(otpKey != null)
			return getQRBarcodeURL(user_id, hostName, otpKey);
		
		byte[] buffer = new byte[5 + 5 * 5];
		new Random().nextBytes(buffer);
		Base32 codec = new Base32();
		byte[] secretKey = Arrays.copyOf(buffer, 10);
		byte[] bEncodedKey = codec.encode(secretKey);

		String encodedKey = new String(bEncodedKey);
		String url = getQRBarcodeURL(user_id, hostName, encodedKey);
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

		//map.put("encodedKey", encodedKey);
		//map.put("url", url);
		return url;
	}

	public boolean checkCode(String user_id, String userCode) {
		long otpnum = Integer.parseInt(userCode); // Google OTP 앱에 표시되는 6자리 숫자
		long wave = new Date().getTime() / 30000; // Google OTP의 주기는 30초
		boolean result = false;
		String otpkey = null;
		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rSet = null;
		int ret = 1;
		try {
			conn = dataSrc.getConnection();
			preStmt = conn.prepareStatement(getOtpKeyQuery);
			preStmt.setString(1, user_id);
			rSet = preStmt.executeQuery();
			if(rSet.next()) {
				otpkey = rSet.getString("otpkey");
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
}
