package com.scriptMachineInstaller.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class StreamReaderDownloadFile extends Thread{
	private InputStream is;
	private StringBuilder value;
	boolean printValueStream;
	HttpServletResponse response;
	PrintWriter writer;
	private boolean isSuccess = true;
	
	
	static String ALREAY_EXIST = "already there";

	public StreamReaderDownloadFile(InputStream is, boolean printValueStream, PrintWriter response){
		this.is = is;
		this.value = new StringBuilder();
		this.printValueStream = printValueStream;
		this.writer = response;
	}

	public void run(){
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = "";
			
			while ((line = br.readLine()) != null){
				if (line.contains(ALREAY_EXIST)){
					setSuccess(false);
					writer.write("data: "+ ALREAY_EXIST +"\n\n");
				} else 
				 writer.write("data: "+ line +"\n\n");
				 writer.flush();
				 System.out.println(line);
//				value.append(line).append("\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getValue() {
		return value.toString();
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
}
