package com.lesson.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.catalina.tribes.group.Response;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 * Servlet implementation class upLoadFile
 */
@WebServlet("/upLoadFile")
@MultipartConfig
public class upLoadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletConfig config;
    
	
    @Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		this.config = config;
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public upLoadFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
//		Part part = request.getPart("upLoadFile");
//		String filename = part.getHeader("content-disposition");
//		String realfilename = filename.substring(filename.lastIndexOf("=")+2,filename.length()-1);
//		String real=realfilename.substring(realfilename.lastIndexOf("\\")+1, realfilename.length());
//		String path = request.getRealPath("/headImage");
//		File file=new File(path);
//        if(!file.exists()){
//        	file.mkdirs();
//        }
//        System.out.println(path);
//		part.write(path);
		PrintWriter out = response.getWriter();
		try {
			SmartUpload su = new SmartUpload();
			//定义文件允许上传类型
			su.setAllowedFilesList("jpg,doc");
			//定义文件不允许上传类型
			su.setDeniedFilesList("txt");
			su.setMaxFileSize(200000);
			//开始上传
//			su.upload();
			su.initialize(config,request, response);
			su.upload();
			//String rootpath = config.getServletContext().getRealPath("/");
			String uemail = su.getRequest().getParameter("uemail");
			String path = request.getRealPath("/headImage");  
//			System.out.println(path);
			File f=new File(path);
	        if(!f.exists()){
	        	f.mkdirs();
	        }
//			File f = new File(rootpath+uname);
//			if(!f.exists()){
//				f.mkdirs();
//			}
//			int count = su.save(f.getAbsolutePath());
			com.jspsmart.upload.File file = su.getFiles().getFile(0);
//			System.out.println(file);
			int len = file.getFileName().length();
			String name = f.getAbsolutePath()+"/"+uemail+file.getFileName().substring(len-4, len);
			System.out.println(name);
			file.saveAs(name,su.SAVE_PHYSICAL);
			//out.println("success");
			response.sendRedirect("main.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
