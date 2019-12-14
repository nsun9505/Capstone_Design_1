package com.ipfs.singleton;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;

public class MyIPFS {
	private IPFS ipfs;
	private MyIPFS() {
		ipfs = new IPFS("/ip4/127.0.0.1/tcp/5001");
		try {
			ipfs.refs.local();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static class LazyHolder {
		public static final MyIPFS INSTANCE = new MyIPFS();
	}

	public static MyIPFS getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	public IPFS getIPFS() {
		return this.ipfs;
	}
	
	public String viewLogUpdate(String id, String name, String patient_name, String patient_number) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar time = Calendar.getInstance();
		String viewDate = sdf.format(time.getTime());
		
		MerkleNode addResult = null;
		String ret = "viewer id : " + id + "\n"
				+ "patient_number : " + patient_number.substring(0, 8) + "\n"
				+ "type : [view]" + "\n" 
				+ "view date : " + viewDate;
		
		NamedStreamable.ByteArrayWrapper file = new NamedStreamable.ByteArrayWrapper(id+".txt", ret.getBytes());
		try {
			addResult = MyIPFS.getInstance().getIPFS().add(file).get(0);
			System.out.println(addResult.hash.toBase58());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(addResult == null)
			return null;
		
		return addResult.hash.toBase58();
	}
	
	public String addLogUpdate(int issue_number, String patient_number, int doctor_number, String diagnosis_date, String type) {
		MerkleNode addResult = null;
		String ret = "issue_number : " + issue_number + "\n"
				+ "patient_number : " + patient_number.substring(0, 8) + "\n"
				+ "doctor_number : " + doctor_number + "\n" 
				+ "type :" + type + "\n"
				+ "diagnosis_date : " + diagnosis_date;
		
		NamedStreamable.ByteArrayWrapper file = new NamedStreamable.ByteArrayWrapper(issue_number+".txt", ret.getBytes());
		try {
			addResult = MyIPFS.getInstance().getIPFS().add(file).get(0);
			System.out.println(addResult.hash.toBase58());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(addResult == null)
			return null;
		
		return addResult.hash.toBase58();
	}
}
