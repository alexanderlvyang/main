package javabean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class buyCar {
private Map<String,ProductItem> buycarmap=new LinkedHashMap<String, ProductItem>();


public Map<String, ProductItem> getMap() {
	return buycarmap;
}

public void addProductItem(ProductItem productitem,int productcount){
	int productid=productitem.getProduct().getProduct_id();
	Iterator<String> keys = buycarmap.keySet().iterator();
	while(keys.hasNext()){
		String key = (String)keys.next();
		if(String.valueOf(productid).equals(key)){
			ProductItem productItem=buycarmap.get(key);
			productitem.setCount(productItem.getCount()+productcount);
		}
			
		}
	buycarmap.put(String.valueOf(productid), productitem);
}



/*public double getCountPrice(){
	double result=0.00;
	Collection<ProductItem> productItems = buycarmap.values();
	Iterator<ProductItem> iterator = productItems.iterator();
	while(iterator.hasNext()){
		ProductItem productItem = iterator.next();
		double money=productItem.totalMoney();
		result+=money;
	}

	return result;
}*/
public boolean deleteProductItem(int productid){
	Iterator<String> keys = buycarmap.keySet().iterator();
	while(keys.hasNext()){
		String key = (String)keys.next();
		if(String.valueOf(productid).equals(key)){
			buycarmap.remove(key);
			return true;
		}
	}
	return false;
}
}
