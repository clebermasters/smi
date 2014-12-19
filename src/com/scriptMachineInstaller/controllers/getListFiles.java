package com.scriptMachineInstaller.controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.scriptMachineInstaller.bean.AppBean;
import com.scriptMachineInstaller.util.FileParser;


public class getListFiles extends HttpServlet {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response)	throws IOException, ServletException {

		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();

		
		 ServletContext servletContext = getServletContext();
	     String contextPath = servletContext.getRealPath(File.separator);
	     FileParser fileParser = new FileParser(contextPath);
		List<AppBean> appBeanLis1 = fileParser.getListFiles();
		
		
		jsonObject.add("data", gson.toJsonTree(appBeanLis1));
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.print(jsonObject.toString());
		out.flush();
		out.close();

	}
	
	
	

	
	
}