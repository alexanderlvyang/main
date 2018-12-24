package org.yang.test;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yang.pojo.Brand;
import org.yang.pojo.Category;
import org.yang.pojo.Product;
import org.yang.pojo.Users;
import org.yang.service.BrandService;
import org.yang.service.CategoryService;
import org.yang.service.ProductService;
import org.yang.service.UsersService;
@Controller
public class TestController {
	@Resource
	private UsersService usersServiceImpl;
	@Resource
	private CategoryService categoryServiceImpl;
	@Resource
	private BrandService brandServiceImpl;
	@Resource
	private ProductService productServiceImpl;
	@RequestMapping("insertTest")
	@ResponseBody
	public void insertDataTest() {
		for(int i=0;i<60;i++) {
			Users user=new Users();
			user.setUsername("test"+new Date().getTime());
			user.setEmail("1549424192@qq.com");
			user.setPassword("lvyang0.0");
			Random rand=new Random();
			StringBuffer phoneNumber=new StringBuffer();
			for(int j=0;j<11;j++) {
				phoneNumber.append(rand.nextInt(9));
			}
			user.setPhone(phoneNumber.toString());
			user.setStatus("test");
			usersServiceImpl.registerUser(user);
		}
	}
	@RequestMapping("categoryInsert")
	@ResponseBody
	public void insertCategory() {
		for(int i=0;i<10;i++) {
			Category category = new Category();
			category.setName("text"+new Date().getTime());
			category.setParentId(i+1);
			categoryServiceImpl.addCategory(category,"",null);
		}
	}
	@RequestMapping("brandInsert")
	@ResponseBody
	public void insertBrand() {
		for(int i=0;i<30;i++) {
			Brand brand=new Brand();
			brand.setBrand_name("text"+new Date().getTime());
			brand.setBrand_englishName("text"+new Date().getTime());
			brand.setBrand_joinTime(Calendar.getInstance().getTime().toString());
			brand.setBrand_company("金洋科技股份有限公司");
			brandServiceImpl.addBrand(brand,null);
		}
		
	}
/*	@RequestMapping("productInsert")
	@ResponseBody
	public void insertProduct() {
		for(int i=0;i<60;i++) {
			Product product=new Product();
			Random rand=new Random();
			product.setProduct_brand(rand.nextInt(9));
			product.setProduct_describe("desc"+rand.nextInt(255));
			product.setProduct_introduction("intrp"+rand.nextInt(255));
			product.setProduct_joinTime("time"+Calendar.getInstance().getTimeInMillis());
			product.setProduct_name("name"+rand.nextInt(255));
			product.setProduct_price(rand.nextInt(255));
			product.setProduct_status("上架");
			product.setProduct_thumbnail("thumb"+rand.nextInt(255));
			product.setProduct_category(+rand.nextInt(10));
			productServiceImpl.addProduct(product);
		}
	}*/
}
