package org.yang.pojo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ShoppingCart {
	private int cart_id;
	private int product_id;
	private int specification_id;
	private int id;
	private double total_price;
	private int count;
	private Map<String,ProductInfo> cartMap=new HashMap<String,ProductInfo>();
	
	public Map<String, ProductInfo> getCartMap() {
		return cartMap;
	}
	public void addCartMap(String productId,ProductInfo productInfo,int count) {
		Iterator<String> keys = cartMap.keySet().iterator();
		while(keys.hasNext()){
			String key = (String)keys.next();
			if(String.valueOf(productInfo.getSpecification_id()).equals(key)){
				ProductInfo productItem=cartMap.get(key);
				productInfo.setCount(productItem.getCount()+count);
			}
		}
		cartMap.put(String.valueOf(productInfo.getSpecification_id()), productInfo);
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getTotal_price() {
		return total_price;
	}
	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}
	public int getCart_id() {
		return cart_id;
	}
	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getSpecification_id() {
		return specification_id;
	}
	public void setSpecification_id(int specification_id) {
		this.specification_id = specification_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
