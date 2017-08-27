package com.progresssoft.controller;

import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.progresssoft.bean.Files;
import com.progresssoft.service.DealsService;
import com.progresssoft.service.FilesService;

import java.io.File;
import java.util.List;
import java.util.Properties;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class Upload
 */
@WebServlet("/uploud")
public class Upload extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FilesService filesService = new FilesService();
	private DealsService dealsService = new DealsService();
	final static Logger logger = Logger.getLogger(Upload.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Upload() {
		super();

		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			InputStream input = getServletContext().getResourceAsStream("/WEB-INF/prop/config.properties");
			Properties prop = new Properties();
			prop.load(input);
			Files file = new Files();
			String UPLOAD_DIRECTORY = prop.getProperty("UPLOAD_DIRECTORY");
			String fileName = "";
			if (ServletFileUpload.isMultipartContent(request)) {
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						fileName = new File(item.getName()).getName();
						try {
							item.write(new File(UPLOAD_DIRECTORY + File.separator + fileName));
						} catch (Exception e) {
							logger.error("Faild to upload the file");
						}
					}
				}
			}			
			PrintWriter writer = response.getWriter();
			file = getFilesService().createFile(fileName);
			if(file.getFileId() == 0){
				logger.error("File already imported");
				response.sendRedirect("index.jsp");
				return;
			}
			logger.info("This is info : Start");
			getDealsService().InsertDeals(file);
			logger.info("This is info : End");
			writer.println("<h1>Import " + file.getFileName() + " Successfully</h1>");
			writer.close();			
		}catch (FileUploadException e) {
			logger.error("File Upload Faild due to "+e);
		}
	}

	public FilesService getFilesService() {
		return filesService;
	}

	public void setFilesService(FilesService filesService) {
		this.filesService = filesService;
	}

	public DealsService getDealsService() {
		return dealsService;
	}

	public void setDealsService(DealsService dealsService) {
		this.dealsService = dealsService;
	}

}
