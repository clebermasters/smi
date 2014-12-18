package com.scriptMachineInstaller.scriptEngine;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Field;


public class SMIEngine {
	//###FILES DEFINITION###
	
	public String urlServer = "http://localhost:8080/ScriptMachineInstaller/";
	static String urlServerDownloads = "Downloads/";
	static String command = "wget ";
	public String context = "WebContent/";

	static String folderServer = "Scripts/";
	static String fileNameDownloader = "scriptDownloader.sh";
	static String scriptInstaller32 = "install_workspace32.sh";
	static String FILEDEFINITION = "###FILES DEFINITION###";
	
	public static void main(String[] args) {
		SMIEngine smi = new SMIEngine(null,null);
		String blockName = smi.getStaticBlockName("FLAGTOINSTALL");
		
		String text = smi.loadBlock(blockName);
		
		System.out.println(text);
		 
	}
	
	public String getStaticBlockName(String block) {
		String blockName = "";
		
		StaticDefinitions obj = new StaticDefinitions();
	    for (Field field : obj.getClass().getDeclaredFields()) {
	        //field.setAccessible(true); // if you want to modify private fields
	        try {
	        	
	        	if (field.getName().equals(block))
	        		blockName = field.get(obj).toString();
	        	
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
	    }
	    
	    return blockName;
	}
	
	public SMIEngine (String context, String urlPath) {
		String dir = context == null ? this.context : context;
		String urlPathServer = urlPath == null ? this.urlServer + urlServerDownloads : this.urlServer + urlServerDownloads;
		this.context = dir;
		this.urlServer = urlPathServer;
	}

	public String loadBlock(String blockName){
		String line = "";
		String input = "";
		Reader reader = null;
		boolean foundBegin = false;
		boolean foundEnd = false;
		
		int lineCount = 0;
		
		String dir = this.context + folderServer + scriptInstaller32;

		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(dir), "utf-8"));

			while ((line = ((BufferedReader) reader).readLine()) != null && foundEnd == false) {
				lineCount++;
				
				if (lineCount == 171){
					
				 System.out.println(line);
				}
				
				if (line.trim().matches(blockName.concat("-END")))
					foundEnd = true;
				
				if (foundBegin && !foundEnd){
					input += line + '\n';
				}
				
				if (line.trim().matches(blockName))
					foundBegin = true;
			}

		} catch (IOException ex) {
			// report
		} finally {
			try {reader.close();
			} 
			catch (Exception ex) {}
		}
		return input;
	}
	
	
	
	
	public void updateFileScript(String fileName, String key, boolean isNew){
//		key ="nvidiaDrive";
//		fileName = "chupa.file";
		String line = "";
		String input = "";
		boolean nextLineInsert = false;
		
		String dir = this.urlServer + scriptInstaller32;

		Reader reader = null;
		FileOutputStream writer = null;

		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(dir), "utf-8"));

			while ((line = ((BufferedReader) reader).readLine()) != null) {
//				System.out.println(line);
				
				if (isNew && line.startsWith(FILEDEFINITION)){
					nextLineInsert = true;
					input += FILEDEFINITION + '\n';
					isNew = false;
				}
				
				if(line.startsWith(key) || nextLineInsert){
					nextLineInsert = false;
					input += key + "=" + "\""+ fileName + "\"" + '\n';
				} else
					input += line + '\n';
			}
			writer = new FileOutputStream(dir);
			writer.write(input.getBytes());

		} catch (IOException ex) {
			// report
		} finally {
			try {reader.close();
			writer.close();
			} 
			catch (Exception ex) {}
		}
	}
	
	public String updateBlock(String blockName, String block){
		String line = "";
		String input = "";
		Reader reader = null;
		FileOutputStream writer = null;

		boolean foundBegin = false;
		boolean skipExistingLines = false;
		
		int lineCount = 0;
		
		
		String dir = this.context + folderServer + scriptInstaller32;

		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(dir), "utf-8"));

			while ((line = ((BufferedReader) reader).readLine()) != null) {
				lineCount++;
				
				if (lineCount == 171){
				 System.out.println(line);
				}
				
				if (line.trim().matches(blockName))
					foundBegin = true;
				
				if (foundBegin){
					input += line + '\n'; // Copy Block Header
					input += block + '\n';
					foundBegin = false;
					skipExistingLines = true;
				}
				
				if (line.trim().matches(blockName.concat("-END"))){
					skipExistingLines = false;
				}
					
				
				if (!skipExistingLines) {
					input += line + '\n';
				}
			}
			
			writer = new FileOutputStream(dir);
			writer.write(input.getBytes());

		} catch (IOException ex) {
			// report
		} finally {
			try {reader.close();
			writer.close();
			} 
			catch (Exception ex) {}
		}
		return input;
	}
	
	
}
