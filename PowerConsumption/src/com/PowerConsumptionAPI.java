package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PowerConsumptionAPI
 */
@WebServlet("/PowerConsumptionAPI")
public class PowerConsumptionAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PowerConsumption PCDataObj = new PowerConsumption();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PowerConsumptionAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String output = PCDataObj.insertPCData(request.getParameter("c_ID"), 
				 						   request.getParameter("c_commercial"), 
				 						   request.getParameter("c_agriculture"), 
				 						   request.getParameter("c_residential"),
				 						   request.getParameter("c_date")); 
	    response.getWriter().write(output);
		
		doGet(request, response);
	}
	
	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request); 
		 String output = PCDataObj.deletePCData(paras.get("pc_ID").toString()); 
		response.getWriter().write(output);
	}
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request) 
	{ 
	 Map<String, String> map = new HashMap<String, String>(); 
	try
	 { 
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
	 String queryString = scanner.hasNext() ? 
	 scanner.useDelimiter("\\A").next() : ""; 
	 scanner.close(); 
	 String[] params = queryString.split("&"); 
	 for (String param : params) 
	 {
		 String[] p = param.split("=");
		 map.put(p[0], p[1]); 
		 } 
		 } 
		catch (Exception e) 
		 { 
		 } 
		return map; 
		}
	
	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request); 
		 String output = PCDataObj.updatePCData(paras.get("hidPCIDSave").toString(), 
				 							paras.get("c_ID").toString(), 
				 							paras.get("c_commercial").toString(), 
				 							paras.get("c_agriculture").toString(),
				 							paras.get("c_residential").toString(),
				 							paras.get("c_date").toString()); 
		response.getWriter().write(output); 
	}

}
