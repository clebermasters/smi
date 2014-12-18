package com.scriptMachineInstaller.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.scriptMachineInstaller.bean.AppBean;

public class AppPropertiesManager {
	
//	public static String fileSrc = "WebContent/Properties/links.properties";
	 public String context = "/WebContent/";
	 static String folderProperties = "Properties/";
	 static String fileProperties = "app.properties";
	 
	 
	 public AppPropertiesManager (String context) {
		 this.context = context == null ? this.context : context;
	 }
	
	public static void main(String[] args) {
		List<AppBean> appBeanList = new ArrayList<AppBean>();
		AppPropertiesManager appPropertiesManager = new AppPropertiesManager(null);
		AppBean app1 = new AppBean();
		app1.setFileName("FileTemp12");
		app1.setLink("http://debian.c3sl.ufpr.br/debian-cd/ls-lR.gz");
		app1.setBits(32);
		app1.setVersion("1");
		
		appBeanList.add(app1);
		
//		appPropertiesManager.writeNewLink(appBeanList);
		
//		appPropertiesManager.getAllLinks();
		
		
		AppBean app2 = appPropertiesManager.getApp(null,"7z920.tar.bz2");
		System.out.println(app2.getKey());
	}
	
	public void writeNewLink(List<AppBean> appBeanList){
		OutputStream output = null;
		Properties linksProp = new Properties();
		InputStream input = null;
		
		String dir = this.context + folderProperties + fileProperties;
		
		File file = new File(dir);
		try {
			 
			input = new FileInputStream(file);
			linksProp.load(input);
			linksProp.remove("FileTemp$1");
			for (AppBean app : appBeanList){
				linksProp.setProperty(app.getKey(), app.getFileName());
			}
			output = new FileOutputStream(file);
			linksProp.store(output, null);
	 
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	 
		}
	}
	
	
	public List<AppBean> getAllLinks(){
		List<AppBean> appBeanList = new ArrayList<AppBean>();
		Properties linksProp = new Properties();
		InputStream input = null;
		AppBean appBean = new AppBean();
		String dir = this.context + folderProperties + fileProperties;
		
		File file = new File(dir);
		try {
			input = new FileInputStream(file);
			linksProp.load(input);
			
			for(String key : linksProp.stringPropertyNames()) {
				appBean.setKey(key);
				appBean.setFileName(linksProp.getProperty(key));
				//	System.out.println(key + " => " + appBean.getLink());
				appBeanList.add(appBean);
			}
			
		} catch (IOException io) {
			System.out.println("Error on try read the file");
		}
		return appBeanList;
	}
	
	public AppBean getApp(String key, String fileName){
		AppBean appBean = new AppBean();
		Properties linksProp = new Properties();
		InputStream input = null;
		String dir = this.context + folderProperties + fileProperties;

		File file = new File(dir);

		String fileNameFound = "";
		String keyFound = "";
		
		try {
			input = new FileInputStream(file);
			linksProp.load(input);
			
			if (fileName != null) {
				for(String temp : linksProp.stringPropertyNames()) {
					if (linksProp.getProperty(temp).equals(fileName)){
						keyFound = temp;
					}
				}
			}
			
			if (key != null)
			fileNameFound = linksProp.getProperty(keyFound);
			
			appBean.setFileName(fileNameFound.isEmpty() ? "File not found" : fileNameFound);
			appBean.setKey(keyFound.isEmpty() ? "App not found for: " + fileName: keyFound);
	 
		} catch (IOException io) {
			System.out.println("Error on try read the file");
		}
		
		return appBean;
	}
	
	public boolean removeApp(AppBean appBean){
		OutputStream output = null;
		Properties linksProp = new Properties();
		InputStream input = null;
		String dir = this.context + folderProperties + fileProperties;

		File file = new File(dir);
		
		try {
			 
			input = new FileInputStream(file);
			linksProp.load(input);
			linksProp.remove(appBean.getKey());
			output = new FileOutputStream(file);
			linksProp.store(output, null);
	 
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}
	
}
