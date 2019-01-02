package javabean;

public class ProductItem {
		private Product product;//�������Ʒ
		private int count;//��Ʒ����
		public double totalMoney(){//С��
			double price=product.getProduct_price();//��ȡ��Ʒ����
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
