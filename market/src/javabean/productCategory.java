package javabean;

public class productCategory {
	private int category_id;
	private String category_name;
	private int category_father;
	public productCategory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public productCategory(int category_id, String category_name, int category_father) {
		super();
		this.category_id = category_id;
		this.category_name = category_name;
		this.category_father = category_father;
	}
	@Override
	public String toString() {
		return "productCategory [category_id=" + category_id + ", category_name=" + category_name + ", category_father="
				+ category_father + "]";
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public int getCategory_father() {
		return category_father;
	}
	public void setCategory_father(int category_father) {
		this.category_father = category_father;
	}
	
}
