package com.progresssoft.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.progresssoft.bean.Files;
import com.progresssoft.bean.InValidDeals;
import com.progresssoft.bean.ValidDeals;
import com.progresssoft.service.DealsService;
import com.progresssoft.service.FilesService;

/**
 * Servlet implementation class Search
 */

public class Search extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FilesService filesService = new FilesService();
	private DealsService dealsService = new DealsService();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			List<ValidDeals> validDeals = new ArrayList<ValidDeals>();
			List<InValidDeals> inValidDeals = new ArrayList<InValidDeals>();
			String filename = request.getParameter("filename");
			filename = filename + ".csv";
			Files file = getFilesService().getFileByName(filename);
			validDeals = dealsService.getValidDealsByFileName(filename);
			inValidDeals = dealsService.getInValidDealsByFileName(filename);
			request.setAttribute("validDeals", validDeals);
            RequestDispatcher view = request.getRequestDispatcher("searchResult.jsp");
            view.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public FilesService getFilesService() {
		return filesService;
	}

	public void setFilesService(FilesService filesService) {
		this.filesService = filesService;
	}
}
