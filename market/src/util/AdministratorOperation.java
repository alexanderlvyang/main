package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AdministratorOperation {
	public void adminOperationLog(String operationName,int staffId,int staffName,String operationContent,String updateTime){
		SimpleDateFormat simpledateformat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createTime=simpledateformat.format(new Date());
		if(updateTime==null){
			updateTime=simpledateformat.format(new Date());
		}
	}
}
