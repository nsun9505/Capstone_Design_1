package com.member.dto;

public class MemberDto {
	private String user_id;
	private String user_pw;
	private String user_name;
	private int user_level;
	private String user_rank;
	private String otpkey;
	
	public MemberDto(String id, String pw, String name, int user_level, String user_rank, String otpkey) {
		this.user_id = id;
		this.user_pw = pw;
		this.user_name = name;
		this.user_level = user_level;
		this.user_rank = user_rank;
		this.otpkey = otpkey;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pw() {
		return user_pw;
	}

	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public int getUser_level() {
		return user_level;
	}

	public void setUser_level(int user_level) {
		this.user_level = user_level;
	}

	public String getOtpkey() {
		return otpkey;
	}

	public void setOtpkey(String otpkey) {
		this.otpkey = otpkey;
	}
}
