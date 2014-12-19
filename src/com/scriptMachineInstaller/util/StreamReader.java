package com.scriptMachineInstaller.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamReader extends Thread{
	private InputStream is;
	private StringBuilder value;
	boolean printValueStream;

	public StreamReader(InputStream is, boolean printValueStream){
		this.is = is;
		this.value = new StringBuilder();
		this.printValueStream = printValueStream;
	}

	public void run(){
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = "";
			while ((line = br.readLine()) != null){
				if (printValueStream)
				System.out.println(value);
				value.append(line).append("\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getValue() {
		return value.toString();
	}
}
