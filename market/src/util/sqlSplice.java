package util;

import java.util.HashMap;
import java.util.Iterator;

public class sqlSplice {
	public static String sqlsplice(HashMap<String,String> findconditionmap,String sql){
		if(findconditionmap.size()>0){
			sql = sql+" where ";
			Iterator<String> it=findconditionmap.keySet().iterator();
			while(it.hasNext()){
				String key=it.next();
				if(key.equals("lowPrice")){
					sql+="product_price > "+findconditionmap.get(key)+" and ";
				}else
				if(key.equals("highPrice")){
					sql+="product_price < "+findconditionmap.get(key)+" and ";
				}else
				if(key.equals("categoryId")){
						sql+="product_id in (select product_id from productcategory where category_id="+findconditionmap.get(key)+") and ";
					}
				else if(key.equals("createTime")){
					sql+="createTime like '"+findconditionmap.get(key)+"%' and ";
				}else
				sql+=key+"='"+findconditionmap.get(key)+"' and ";
				
			}
			sql=sql.substring(0, sql.length()-4);
		}
		return sql;
	}
}
