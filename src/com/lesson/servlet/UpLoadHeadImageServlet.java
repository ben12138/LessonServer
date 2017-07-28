package com.lesson.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UpLoadHeadImage
 */
@WebServlet("/UpLoadHeadImageServlet")
public class UpLoadHeadImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpLoadHeadImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");  //���ñ���  
        DiskFileItemFactory factory = new DiskFileItemFactory();  
        String path = request.getRealPath("/headImage");  
        File file=new File(path);
        if(!file.exists()){
        	file.mkdirs();
        }
        System.out.println(file.getAbsolutePath());
        factory.setRepository(new File(path));   
        factory.setSizeThreshold(1024*1024) ;  
        ServletFileUpload upload = new ServletFileUpload(factory);  
        try {  
            List<FileItem> list = (List<FileItem>)upload.parseRequest(request);  
            for(FileItem item : list){  
                String name = item.getFieldName();   
                if(item.isFormField()){                     
                    String value = item.getString() ;  
//                    request.setAttribute(name, value);  
                }else{  
                    String value = item.getName() ;  
                    int start = value.lastIndexOf("\\");  
                    String filename = value.substring(start+1);  
//                    request.setAttribute(name, filename);  
                    File image = new File(path,filename) ;
                    if(image.exists()){
                    	image.delete();
                    }
                    item.write( new File(path,filename) ); 
                    response.getOutputStream().print("success");
                }  
            }  
              
        } catch (Exception e) {  
        	//上传失败
        	response.getWriter().print("failure"+e.getMessage());
        }  
	}

}
