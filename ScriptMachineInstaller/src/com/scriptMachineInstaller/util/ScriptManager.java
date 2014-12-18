package com.scriptMachineInstaller.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

import com.scriptMachineInstaller.bean.AppBean;

public class ScriptManager {
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
		ScriptManager scriptManager = new ScriptManager(null,null);
		scriptManager.createFileDownloader(null);
//		scriptManager.updateFileScript(null,null,true);
	}
	
	public ScriptManager (String context, String urlPath) {
		String dir = context == null ? this.context : context;
		String urlPathServer = urlPath == null ? this.urlServer + urlServerDownloads : this.urlServer + urlServerDownloads;
		this.context = dir;
		this.urlServer = urlPathServer;
	}
	

	public void createFileDownloader(List<AppBean> appBeanList){
		FileParser fileParser = new FileParser(context);
		
		if (appBeanList == null)
			appBeanList = fileParser.getListFiles();

		String dir = this.context + folderServer + fileNameDownloader;

		
		Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(dir), "utf-8"));

			for (AppBean bean : appBeanList){
				writer.write(command + this.urlServer + bean.getFileName() + "\n");
			}

		} catch (IOException ex) {
			// report
		} finally {
			try {writer.close();} catch (Exception ex) {}
		}
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
	
	public String loadScriptInstaller32(){
		String line = "";
		String input = "";
		Reader reader = null;
		String dir = this.context + folderServer + scriptInstaller32;

		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(dir), "utf-8"));

			while ((line = ((BufferedReader) reader).readLine()) != null) {
					input += line + '\n';
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
}
