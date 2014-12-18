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
import com.scriptMachineInstaller.util.ScriptManager;


public class loadScriptInstaller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response)	throws IOException, ServletException {

		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();

		ServletContext servletContext = getServletContext();
        String contextPath = servletContext.getRealPath(File.separator);
        ScriptManager scriptManager = new ScriptManager(contextPath,null);
        
		String teste = scriptManager.loadScriptInstaller32();
		jsonObject.add("data", gson.toJsonTree(teste));
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.print(jsonObject.toString());
		out.flush();
		out.close();

	}
}