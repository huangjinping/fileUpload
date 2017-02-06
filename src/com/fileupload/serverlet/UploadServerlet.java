package com.fileupload.serverlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

public class UploadServerlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("==========com.fileupload.serverlet.UploadServerlet=" + req.getParameter("title"));
		boolean multipartContent = ServletFileUpload.isMultipartContent(req);
		if (!multipartContent) {
			throw new RuntimeException(" your  form is not mulitlpart/form-data");
		}
		// 创建工厂类DiskFileItemFactory
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 使用工厂创建解析器对象
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		fileUpload.setHeaderEncoding("utf-8");
		fileUpload.setFileSizeMax(3*1024*1024);
//		fileUpload.setSizeMax(1024*1024*3);
		
		
		// 使用解析器来解析request
		try {
			List<FileItem> parseRequest = fileUpload.parseRequest(req);
			// 遍历表单项数据
			for (FileItem fileItem:parseRequest) {
				if (fileItem.isFormField()) {
					//
				processFormField(fileItem);
				}else{
					processFormFileField(fileItem);

				}
			}

		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void processFormField(FileItem fileItem) {
		String fieldName = fileItem.getFieldName();
		String fieldvalue=fileItem.getString();
		System.out.println(fieldName+"====fieldName==========="+fieldvalue);
	}

	private void processFormFileField(FileItem fileItem) throws IOException {
		String filename=fileItem.getName();
		System.out.println("文件名字"+filename);
		//得到这个文件流
		InputStream inputStream = fileItem.getInputStream();
		
		//创建一尺文件存
		String directory=this.getServletContext().getRealPath("/WEB-INF/upload");
		//即代表文件有代表目录
		File storeDirectory=new File(directory);
		if (!storeDirectory.exists()) {
			storeDirectory.mkdirs();
		}
		filename=UUID.randomUUID()+"_"+filename;
	
		
		
		
//		filename=filename.substring(filename.lastIndexOf(File.separator+1));
		
		if (filename!=null) {
			filename= FilenameUtils.getName(filename);
		}
	
		
		
		//目录打散
		String childDirectory=makeChildDirectory(storeDirectory, filename);
		
		
		//创建一个文件实体
		File file=new File(storeDirectory,childDirectory+File.separator+filename);
		
	   //通过输出流将上传文件保存到磁盘
		
		
		FileOutputStream outputStream=new FileOutputStream(file);
		
			  
	   int len = 0;
		byte[] b = new byte[1024];
		while ((len = inputStream.read(b)) != -1) {
			
			System.out.println("============");
			outputStream.write(b, 0, len);
		}
		outputStream.flush();
		outputStream.close();
		inputStream.close();
	}

	private String makeChildDirectory(File storeDirectory, String filename) {
		int hashcode=filename.hashCode();//返回字符转换的32位hashCode
		System.out.println(hashcode);
		String code=Integer.toHexString(hashcode);//把hascode 转换为16进制的字符 
		System.out.println(code);
		String childDirectory=code.charAt(0)+File.separator+code.charAt(1);
		//穿件制定目录
		
		File file=new File(storeDirectory,childDirectory);
		if (!file.exists()) {
			file.mkdirs();
		}
		
		return childDirectory;
	}

	private String makeChildDirectory(File storeDirectory) {
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(new Date());
		File file=new File(storeDirectory,date);
		if (!file.exists()) {
			file.mkdirs();
		}
		return date;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
