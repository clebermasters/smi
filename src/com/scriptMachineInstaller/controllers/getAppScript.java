package com.scriptMachineInstaller.controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.scriptMachineInstaller.scriptEngine.SMIEngine;
import com.scriptMachineInstaller.scriptEngine.StaticDefinitions;


public class getAppScript extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,HttpServletResponse response)	throws IOException, ServletException {
		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();

		ServletContext servletContext = getServletContext();
		String contextPath = servletContext.getRealPath(File.separator);
		String blockFound = "";

		String app = request.getParameter("app");
		
		SMIEngine smi = new SMIEngine(contextPath,null);
		
		String appBlockName = StaticDefinitions.PREFIX + app;
		blockFound = smi.loadBlock(appBlockName);

		jsonObject.add("data", gson.toJsonTree(blockFound));

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.print(jsonObject.toString());
		out.flush();
		out.close();

	}
}