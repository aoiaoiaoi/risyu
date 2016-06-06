/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pair.paircourse;

import java.io.*;
import java.util.*;
import javax.jdo.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
//import model.Lesson;

import com.google.appengine.api.datastore.KeyFactory;

/**
 *
 * @author g14911ih
 */

public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on
	// the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/plain");
		response.getWriter().println("no url...");
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PersistenceManagerFactory factory = PMF.get();
		PersistenceManager manager = factory.getPersistenceManager();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		String className = request.getParameter("className");
		String teacher = request.getParameter("teacher");
		String term = request.getParameter("term");
		String week = request.getParameter("week");
		String query = "select from " + Lesson.class.getName() + " where className == " + className;
		// PrintWriter out = response.getWriter();
		List<Lesson> list = null;
		try {
			list = (List<Lesson>) manager.newQuery(query).execute();
			// http://puyooboe.blogspot.jp/2015/12/gae.html
		} catch (JDOObjectNotFoundException e) {
		}
		String res = "[";
		if (list != null) {
			for (Lesson data : list) {
				res += "{id:" + data.getId() + ",className:'" + data.getClassName() + "',teacher:'"
						+ data.getTeacher() + "',period:'" + data.getPeriod() + "',credit:'" + data.getCredit()
						+ "',teacher:'" + data.getTeacher() + "',term:'" + data.getTerm() + "',explanation:'"
						+ data.getExplanation() + "',week:'" + data.getWeek() + "'},";
			}
		}
		res += "]";
		manager.close();
		response.sendRedirect("searchResult.jsp");
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>
}
