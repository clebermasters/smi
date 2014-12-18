package com.scriptMachineInstaller.controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scriptMachineInstaller.util.ManageFilesOnServer;


public class sentToServer extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response)	throws IOException, ServletException {

		  //content type must be set to text/event-stream
        response.setContentType("text/event-stream");   
 
        //encoding must be set to UTF-8
        response.setCharacterEncoding("UTF-8");
        
        PrintWriter writer = response.getWriter();
        ServletContext servletContext = getServletContext();
        String contextPath = servletContext.getRealPath(File.separator);
        ManageFilesOnServer manageFileOnServer = new ManageFilesOnServer(contextPath);
		String url = request.getParameter("url");
		String key = request.getParameter("key");
		manageFileOnServer.registerFileDownload(key, url, true, writer);
		writer.write("data: "+ "finished" +"\n\n");
		writer.close();
		} 
		
	}
