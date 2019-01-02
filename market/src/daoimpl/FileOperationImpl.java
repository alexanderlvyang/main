package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dao.FileOperation;
import util.DbManager;

public class FileOperationImpl implements FileOperation {
	//上传文件
	public int upfile(String fileurl)
	{	
		Connection conn=null;
		PreparedStatement prep = null; 
		int result = 0;
		String path=fileurl;
		//FileIfo fi=new FileIfo(beginname,filename,path,timestamp,Size,date,types);
		String sql="insert into sku(product_thumbnail) value(?)";
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, fileurl);
			result=prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeConnection(conn);
		}
		
		return result;
	}
}
