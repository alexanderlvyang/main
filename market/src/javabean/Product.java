package javabean;

public class Product{
private int product_id;//商品id
private String product_name;//商品姓名
private String product_introduction;//商品简介
private double product_originalprice;//商品原价格
private double product_price;//商品实际价格
private String peoduct_state;//商品状态（上架/下架）
private int product_sort;//商品顺序
private String product_describe;//商品详细
private String product_thumbnail;//商品缩略图
private String product_category;
private String product_brand;
private int product_number;
private int attribute_id;
private int attr_value_id;
public Product() {
	super();
	// TODO Auto-generated constructor stub
}
public Product(int product_id, String product_name, String product_introduction, double product_originalprice,
		double product_price, String peoduct_state, int product_sort, String product_describe, String product_thumbnail,
		String product_category, String product_brand, int product_number, int attribute_id, int attr_value_id) {
	super();
	this.product_id = product_id;
	this.product_name = product_name;
	this.product_introduction = product_introduction;
	this.product_originalprice = product_originalprice;
	this.product_price = product_price;
	this.peoduct_state = peoduct_state;
	this.product_sort = product_sort;
	this.product_describe = product_describe;
	this.product_thumbnail = product_thumbnail;
	this.product_category = product_category;
	this.product_brand = product_brand;
	this.product_number = product_number;
	this.attribute_id = attribute_id;
	this.attr_value_id = attr_value_id;
}
@Override
public String toString() {
	return "Product [product_id=" + product_id + ", product_name=" + product_name + ", product_introduction="
			+ product_introduction + ", product_originalprice=" + product_originalprice + ", product_price="
			+ product_price + ", peoduct_state=" + peoduct_state + ", product_sort=" + product_sort
			+ ", product_describe=" + product_describe + ", product_thumbnail=" + product_thumbnail
			+ ", product_category=" + product_category + ", product_brand=" + product_brand + ", product_number="
			+ product_number + ", attribute_id=" + attribute_id + ", attr_value_id=" + attr_value_id + "]";
}
public int getProduct_id() {
	return product_id;
}
public Product setProduct_id(int product_id) {
	this.product_id = product_id;
	return this;
}
public String getProduct_name() {
	return product_name;
}
public Product setProduct_name(String product_name) {
	this.product_name = product_name;
	return this;
}
public String getProduct_introduction() {
	return product_introduction;
}
public Product setProduct_introduction(String product_introduction) {
	this.product_introduction = product_introduction;
	return this;
}
public double getProduct_originalprice() {
	return product_originalprice;
}
public Product setProduct_originalprice(double product_originalprice) {
	this.product_originalprice = product_originalprice;
	return this;
}
public double getProduct_price() {
	return product_price;
}
public Product setProduct_price(double product_price) {
	this.product_price = product_price;
	return this;
}
public String getPeoduct_state() {
	return peoduct_state;
}
public Product setPeoduct_state(String peoduct_state) {
	this.peoduct_state = peoduct_state;
	return this;
}
public int getProduct_sort() {
	return product_sort;
}
public Product setProduct_sort(int product_sort) {
	this.product_sort = product_sort;
	return this;
}
public String getProduct_describe() {
	return product_describe;
}
public Product setProduct_describe(String product_describe) {
	this.product_describe = product_describe;
	return this;
}
public String getProduct_thumbnail() {
	return product_thumbnail;
}
public Product setProduct_thumbnail(String product_thumbnail) {
	this.product_thumbnail = product_thumbnail;
	return this;
}
public String getProduct_category() {
	return product_category;
}
public Product setProduct_category(String product_category) {
	this.product_category = product_category;
	return this;
}
public String getProduct_brand() {
	return product_brand;
}
public Product setProduct_brand(String product_brand) {
	this.product_brand = product_brand;
	return this;
}
public int getProduct_number() {
	return product_number;
}
public Product setProduct_number(int product_number) {
	this.product_number = product_number;
	return this;
}
public int getAttribute_id() {
	return attribute_id;
}
public Product setAttribute_id(int attribute_id) {
	this.attribute_id = attribute_id;
	return this;
}
public int getAttr_value_id() {
	return attr_value_id;
}
public Product setAttr_value_id(int attr_value_id) {
	this.attr_value_id = attr_value_id;
	return this;
}


}
