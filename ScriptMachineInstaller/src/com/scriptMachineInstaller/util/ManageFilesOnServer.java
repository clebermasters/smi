package com.scriptMachineInstaller.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.scriptMachineInstaller.bean.AppBean;

public class ManageFilesOnServer {
	public String contextPath = "WebContent/";
	static String folderDownload = "Downloads/";
	
	public static void main(String[] args) throws IOException, InterruptedException {
		ManageFilesOnServer manageFileOnServer = new ManageFilesOnServer(null);
		manageFileOnServer.doDeleteFile("NVIDIA-Solaris-x86-319.17.run","");
//		AppBean appBean = LinksManage.getApp("FileTemp12$1&32");
//		doDownload(appBean.getLink());
//		registerFileDownload(null, null,true);
	}
	
	public ManageFilesOnServer (String contextPath) {
		this.contextPath = contextPath == null ? this.contextPath : contextPath;
	}
	
	public boolean registerFileDownload(String key, String url, boolean isNew, PrintWriter writer){
		boolean isSuccess = false;
		try {
			isSuccess = doDownload(url, writer);
			if (isSuccess) {
				List<AppBean> appBeanList = new ArrayList<AppBean>();
				AppPropertiesManager appPropertiesManager = new AppPropertiesManager(this.contextPath);
				AppBean appDownloaded = new AppBean();
				appDownloaded.setKey(key);
				appDownloaded.setLink(url);
				appBeanList.add(appDownloaded);
				appPropertiesManager.writeNewLink(appBeanList);
				ScriptManager scriptManager = new ScriptManager(this.contextPath,null);
				scriptManager.createFileDownloader(null);
				scriptManager.updateFileScript(appDownloaded.getFileName(), key, isNew);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return isSuccess;
	}

	public boolean doDownload (String url, PrintWriter response) throws IOException, InterruptedException {
		ProcessBuilder pb = new ProcessBuilder(new String[] {
			"wget",
			"-nc",
			url,
		});
		String dir = this.contextPath + folderDownload;
		pb.environment().putAll(System.getenv());
		pb.directory(new File(dir));

		Process p = pb.start();
//		StreamReaderDownloadFile inReader = new StreamReaderDownloadFile(p.getInputStream(),true, response);
		StreamReaderDownloadFile errReader = new StreamReaderDownloadFile(p.getErrorStream(),true, response );

//		inReader.start();
		errReader.start();

		p.waitFor();

//		inReader.join();
//		errReader.join();

//		String cmdResult = inReader.getValue();
		
//		System.out.println(cmdResult);
		System.out.println("Finished Download");
//		return cmdResult.length() > 0;
		return errReader.isSuccess();
	}
	
	
	public boolean doDeleteFile (String fileName, String key) throws IOException, InterruptedException {
		ProcessBuilder pb = new ProcessBuilder(new String[] {
			"rm",
			fileName,
		});
		String dir = this.contextPath + folderDownload;
		pb.environment().putAll(System.getenv());
		pb.directory(new File(dir));

		Process p = pb.start();
		StreamReader inReader = new StreamReader(p.getInputStream(),true);
		StreamReader errReader = new StreamReader(p.getErrorStream(),true);

		inReader.start();
		errReader.start();

		p.waitFor();

		inReader.join();
		errReader.join();

		String cmdResult = inReader.getValue();
		System.out.println(cmdResult);
		
		AppPropertiesManager appPropertiesManager = new AppPropertiesManager(this.contextPath);
		AppBean app = new AppBean();
		app.setFileName(fileName);
		app.setKey(key);
		appPropertiesManager.removeApp(app);
		
		System.out.println("The file was deleted");
		return cmdResult.length() > 0;
	}
	

}