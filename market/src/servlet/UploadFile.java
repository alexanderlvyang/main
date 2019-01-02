package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.FileOperation;
import daoimpl.FileOperationImpl;
public class UploadFile extends HttpServlet {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * 
	 */


	public void destroy() {
		super.destroy();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
	
			
		DiskFileItemFactory diskfileitemfactory=new DiskFileItemFactory();
		ServletFileUpload servletfileupload=new ServletFileUpload(diskfileitemfactory);
		servletfileupload.setHeaderEncoding("UTF-8");//上传文件名的乱码
		diskfileitemfactory.setSizeThreshold(10*1024*1024);
		diskfileitemfactory.setRepository(new File("D:/temp"));
		Logger log=Logger.getLogger(this.getClass().getName());
		log.info("1234");
		try {
			
			List<FileItem> filelist=servletfileupload.parseRequest(request);
			for (FileItem Item : filelist) {
				
				if(Item.isFormField()){
					log.info("idform");
					String name=Item.getFieldName();
					String value=Item.getString("UTF-8");
					log.info(name);
					log.info(value);
				}
				
				else{
					log.info(".....");
					//随机名称
					String originalname=Item.getName();
					log.info(originalname);
					String newname=originalname.substring(originalname.lastIndexOf("."), originalname.length());
					String filename=UUID.randomUUID().toString();
					String finalfilename=filename+newname;
					int filehashname=filename.hashCode();//哈希算法生成随机的名称
					int firstfilepath=filehashname&15;
					int secondfilepath=(filehashname>>>4)&15;
					
					//创建路径
					String path = getServletConfig().getServletContext().getRealPath("file");
					String src=path+File.separator+firstfilepath+File.separator+secondfilepath+File.separator;
					int position=src.indexOf("file");
					String fileurl=src.substring(position, src.length());
					String Path = request.getContextPath();
					String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+Path+"/";
					String paths=basePath+fileurl+finalfilename;
					
					File myPath = new File( src );  
			            if ( !myPath.exists()){//若此目录不存在，则创建之  
			                myPath.mkdirs();  
			            }  
					Item.write(new File(src+finalfilename));
					Item.delete();
					
					log.info(paths+"");
					out.print(paths);
				/*	FileOperation fileoperation=new FileOperationImpl();
					int result=fileoperation.upfile(paths);
					if(result==1){
						out.print("上传成功");
						out.flush();
						out.close();
					}
					else{
						out.print("上传失败");
					}*/
					}
					
				}
				
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void init() throws ServletException {
	
	}

}
