package com.scriptMachineInstaller.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.scriptMachineInstaller.bean.AppBean;

public class FileParser {

	 public static String[] getFiles = new String[] {
				"ls","-m"
			};
	 
	 String contextPath = "WebContent";
	 static String folderDownload = "Downloads/";
	 
	 public FileParser (String context){
		 this.contextPath = context == null ? this.contextPath : context;
	 }
	 
//	 public static String folder = "/Users/cleber/Documents/workspace/ScriptMachineInstaller/WebContent/Downloads";
	
	 public static void main(String[] args) {
		 FileParser fileParser = new FileParser(null);
		 List<AppBean> appBeanList = fileParser.getListFiles();
		 System.out.println(appBeanList.size() + " files founds");
	 }
	 
	 public List<AppBean> getListFiles () {
		 String result;
		 List<AppBean> appBeanList = new ArrayList<AppBean>();
		 
		 String dir = this.contextPath + folderDownload;
		 
		try {
			result = performAction(getFiles, dir);
			 String[]  listFiles =  result.split(",");
			  appBeanList = parseFilesStringToAppBean(listFiles);
			 
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 return appBeanList;
	 }
	 
	 
	public static String performAction(String[] commands, String folder) throws IOException, InterruptedException {
		
		ProcessBuilder pb = new ProcessBuilder(commands);
		
		pb.environment().putAll(System.getenv());
		pb.directory(new File(folder));

		Process p = pb.start();
		StreamReader inReader = new StreamReader(p.getInputStream(),false);
		StreamReader errReader = new StreamReader(p.getErrorStream(),false);

		inReader.start();
		errReader.start();

		p.waitFor();

		inReader.join();
		errReader.join();

		String cmdResult = inReader.getValue();
//		System.out.println(cmdResult);
		return cmdResult;
	}
	
	public List<AppBean> parseFilesStringToAppBean (String[] files) {
		List<AppBean> appBeanList = new ArrayList<AppBean>();
		AppPropertiesManager appPropertiesManager = new AppPropertiesManager(this.contextPath);
		for(String file : files){
			AppBean appBean = new AppBean();
			appBean.setFileName(file.trim());
			appBean.setSize(getFileSize(appBean));
			appBean.setExtension(getExtension(appBean.getFileName()));
			appBean.setKey(appPropertiesManager.getApp(null, appBean.getFileName()).getKey());
			appBeanList.add(appBean);
		}
		
		return appBeanList;
	}
	
	public long getFileSize (AppBean appBean) {
		File file = new File(this.contextPath + folderDownload + appBean.getFileName());
		return file.length();
	}
	
	
	public static String getExtension(String fileName) {
	    String ext = null;
	    int i = fileName.lastIndexOf('.');

	    if (i > 0 &&  i < fileName.length() - 1) {
	        ext = fileName.substring(i+1).toLowerCase();
	    }
	    return ext;
	}
}
