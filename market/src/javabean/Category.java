package javabean;

public class Category {
	private int category_id;
	private String category_name;
	private int category_father;
	private int category_level;
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Category(int category_id, String category_name, int category_father, int category_level) {
		super();
		this.category_id = category_id;
		this.category_name = category_name;
		this.category_father = category_father;
		this.category_level = category_level;
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
	public int getCategory_level() {
		return category_level;
	}
	public void setCategory_level(int category_level) {
		this.category_level = category_level;
	}
	@Override
	public String toString() {
		return "Category [category_id=" + category_id + ", category_name=" + category_name + ", category_father="
				+ category_father + ", category_level=" + category_level + "]";
	}
	
}	
