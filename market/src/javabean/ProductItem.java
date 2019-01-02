package javabean;

public class ProductItem {
		private Product product;//购买的商品
		private int count;//商品数量
		public double totalMoney(){//小计
			double price=product.getProduct_price();//获取商品单价
			return price*count;
		}
		
		@Override
		public String toString() {
			return "ProductItem [product=" + product + ", count=" + count + "]";
		}

		public ProductItem() {
			super();
		}
	 
		public ProductItem(Product product, int count) {
			super();
			this.product = product;
			this.count = count;
		}
	 
		public Product getProduct() {
			return product;
		}
		public void setProduct(Product product) {
			this.product = product;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		

}
