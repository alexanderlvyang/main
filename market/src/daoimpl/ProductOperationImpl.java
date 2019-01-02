package daoimpl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferUShort;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.naming.spi.DirStateFactory.Result;

import dao.ProductOperation;
import javabean.Brand;
import javabean.Category;
import javabean.CategoryAttribute;
import javabean.CategoryAttributeValue;
import javabean.Comment;
import javabean.CommentImage;
import javabean.Product;
import javabean.ProductItem;
import javabean.ReceiverAddress;
import javabean.Report;
import javabean.SkuAttribute;
import javabean.Staff;
import javabean.buyCar;
import javabean.Customer;
import javabean.Sensitive;
import javabean.Image;
import javabean.MyOrder;
import javabean.OperationLog;
import javabean.Order;
import javabean.productCategory;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sourceforge.pinyin4j.PinyinHelper;
import util.DbManager;
import util.apiutil;
import util.sqlSplice;

public class ProductOperationImpl implements ProductOperation {
	Logger log = Logger.getLogger(this.getClass().getName());
	// 查询商品的所有信息
	@Override
	public int gettotalpages(HashMap<String, String> findconditionmap) {
		int count = 0; // 总条数
		int totalpages = 0; // 总页数
		int limit = 4; // 每页显示记录条数
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		String countsql = "select count(*) from sku";
		if (findconditionmap!=null) {
			countsql = sqlSplice.sqlsplice(findconditionmap, countsql);
		}
		
		try {
			prep = conn.prepareStatement(countsql);
			result = prep.executeQuery();
			if (result.next()) {
				count = result.getInt(1);// 结果为count(*)表，只有一列。这里通过列的下标索引（1）来获取值
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		totalpages = (int) Math.ceil(count / (limit * 1.0));
		return totalpages;
	}
	public ArrayList<Product> findProductListByCondition(HashMap<String, String> findconditionmap, int currentPage) {
		int totalpages=0;
		int limit = 4; // 每页显示记录条数
		
		PreparedStatement prep = null;
		ResultSet result = null;
		Connection conn = DbManager.getConnection();
		totalpages=gettotalpages(findconditionmap);
		
		
		ArrayList<Product> productList = null;
		//String sql = "select * from sku order by product_id limit " + (currentPage - 1) * limit + "," + limit;
		String sql="select * from sku";
		if(findconditionmap!=null){
			sql=sqlSplice.sqlsplice(findconditionmap, sql);
			log.info(sql+"------------");
			/*sql = sql+" where ";
			Iterator<String> it=findconditionmap.keySet().iterator();
			while(it.hasNext()){
				String key=it.next();
				if(key.equals("lowPrice")){
					sql+="product_price > "+findconditionmap.get(key)+" and ";
				}else
				if(key.equals("highPrice")){
					sql+="product_price < "+findconditionmap.get(key)+" and ";
				}else
				sql+=key+"='"+findconditionmap.get(key)+"' and ";
				
			}
			sql=sql.substring(0, sql.length()-4);*/
		}
		sql+=" order by product_id limit " + (currentPage - 1) * limit + "," + limit;
		productList = new ArrayList<Product>();
		// PreparedStatement prep=null;
		// ResultSet result=null;
		NumberFormat numberformat = NumberFormat.getNumberInstance();
		numberformat.setMaximumFractionDigits(2);
		numberformat.setGroupingUsed(false);
		try {
			prep = conn.prepareStatement(sql);
			result = prep.executeQuery();
			while (result.next()) {
				Product product = new Product();
				String product_category=findProductCategoryByProductId(result.getInt("product_id"));
				product.setProduct_id(result.getInt("product_id"))
						.setProduct_describe(result.getString("product_describe"))
						.setPeoduct_state(result.getString("product_state"))
						.setProduct_introduction(result.getString("product_introduction"))
						.setProduct_name(result.getString("product_name"))
						.setProduct_originalprice(Double
								.valueOf(numberformat.format(result.getDouble("product_originalprice"))).doubleValue())
						.setProduct_price(
								Double.valueOf(numberformat.format(result.getDouble("product_price"))).doubleValue())
						.setProduct_sort(result.getInt("product_sort"))
						.setProduct_number(Integer.parseInt(result.getString("product_number")))
						.setProduct_thumbnail(result.getString("product_thumbnail"))
						.setProduct_category(product_category);
				productList.add(product);// 将对象放入到链表中
				
			}
			// json=JSONArray.fromObject(list);//将链表加入到json并返回前台
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}

		return productList;
	}

	@Override
	// 插入产品信息
	public int insertProductAttribute(String product_name,int groupId,String product_introduction, double product_originalprice,
			double product_price, String peoduct_state, int product_sort, String product_describe,
			String product_thumbnail,int product_number,int brand_id,String pinyin) {
		String sql = "insert into sku(product_name,product_introduction,product_originalprice,product_price,product_state,product_sort,product_describe,product_thumbnail,product_number,brand_id,product_pinyin,createTime,updateTime,group_id) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn = DbManager.getConnection();
		PreparedStatement prep = null;
		int result = 0;
		try {
			prep = conn.prepareStatement(sql);
			prep.setString(1, product_name);
			prep.setString(2, product_introduction);
			prep.setDouble(3, product_originalprice);
			prep.setDouble(4, product_price);
			prep.setString(5, peoduct_state);
			prep.setInt(6, product_sort);
			prep.setString(7, product_describe);
			prep.setString(8, product_thumbnail);
			prep.setInt(9, product_number);
			prep.setInt(10, brand_id);
			prep.setString(11, pinyin);
			prep.setLong(12, new Date().getTime());
			prep.setLong(13, new Date().getTime());
			prep.setInt(14, groupId);
			result = prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbManager.closePreparedStatement(prep);
			DbManager.closeConnection(conn);
		}
		return result;
	}
	@Override
	// 更新产品状态
	public int updateProductState(int product_id) {
		Connection conn = DbManager.getConnection();
		String state = findProductState(product_id);
		PreparedStatement prep = null;
		int result = 0;
		switch (state) {
		case "上架":
			String upsql = "update sku set product_state=?,updateTime=? where product_id=?";
			try {
				prep = conn.prepareStatement(upsql);
				prep.setString(1, "下架");
				prep.setLong(2,new Date().getTime());
				prep.setInt(3, product_id);
				result = prep.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DbManager.closePreparedStatement(prep);
				DbManager.closeConnection(conn);
			}
			break;
		case "下架":
			String downsql = "update sku set product_state=?,updateTime=? where product_id=?";
			try {
				prep = conn.prepareStatement(downsql);
				prep.setString(1, "上架");
				prep.setLong(2, new Date().getTime());
				prep.setInt(3, product_id);
				result = prep.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DbManager.closePreparedStatement(prep);
				DbManager.closeConnection(conn);
			}
			break;
		}
		return result;
	}
	@Override
	// 查询产品状态
	public String findProductState(int product_id) {
		Connection conn = DbManager.getConnection();
		String sql = "select product_state from sku where product_id=?";
		ResultSet result = null;
		String returnresult = null;
		PreparedStatement prep = null;
		try {
			prep = conn.prepareStatement(sql);
			prep.setInt(1, product_id);
			result = prep.executeQuery();
			if (result.next()) {
				returnresult = result.getString("product_state");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
		}
		return returnresult;
	}
	@Override
	public int updateProductAttribute(String product_name, String product_introduction, double product_originalprice,
			double product_price, String product_state, int product_sort, String product_describe,
			String product_thumbnail, int product_id,int product_number,int brand_id,String pinyin) {
		String sql = "update sku set product_name=?,product_introduction=?,product_originalprice=?,product_price=?,product_state=?,product_sort=?,product_describe=?,product_thumbnail=?,product_number=?,brand_id=?,product_pinyin=?,updateTime=? where product_id=?";
		Connection conn = DbManager.getConnection();
		PreparedStatement prep = null;
		int result = 0;
		try {
			prep = conn.prepareStatement(sql);
			prep.setString(1, product_name);
			prep.setString(2, product_introduction);
			prep.setDouble(3, product_originalprice);
			prep.setDouble(4, product_price);
			prep.setString(5, product_state);
			prep.setInt(6, product_sort);
			prep.setString(7, product_describe);
			prep.setString(8, product_thumbnail);
			prep.setInt(9, product_number);
			prep.setInt(10, brand_id);
			prep.setString(11, pinyin);
			prep.setLong(12, new Date().getTime());
			prep.setInt(13, product_id);
			result = prep.executeUpdate();
			log.info(result+"");
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DbManager.closePreparedStatement(prep);
			DbManager.closeConnection(conn);
		}
		return result;
	}
	@Override
	public ArrayList<Product> getProductAttributeListById(int product_id) {
		String sql = "select * from sku where product_id=?";
		Connection conn = DbManager.getConnection();
		PreparedStatement prep = null;
		ResultSet result = null;
		ArrayList<Product> productList = new ArrayList<Product>();
		try{
			prep = conn.prepareStatement(sql);
			prep.setInt(1, product_id);
			result = prep.executeQuery();
			while (result.next()) {
				Product product = new Product();
				product.setProduct_id(result.getInt("product_id"))
						.setProduct_describe(result.getString("product_describe"))
						.setPeoduct_state(result.getString("product_state"))
						.setProduct_introduction(result.getString("product_introduction"))
						.setProduct_name(result.getString("product_name"))
						.setProduct_originalprice(result.getDouble("product_originalprice"))
						.setProduct_price(result.getDouble("product_price"))
						.setProduct_sort(result.getInt("product_sort"))
						.setProduct_thumbnail(result.getString("product_thumbnail"))
						.setProduct_number(result.getInt("product_number"));
				productList.add(product);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return productList;
	}
	public ArrayList<Product> findProduct() {
		// TODO Auto-generated method stub
		ArrayList<Product> productItemList=new ArrayList<Product>();
		String sql="select * from sku";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		try {
			prep=conn.prepareStatement(sql);
			result=prep.executeQuery();
			while(result.next()){
				Product productItem=new Product();
				productItem.setProduct_id(result.getInt("product_id")).setProduct_name(result.getString("product_name"))
				.setProduct_number(result.getInt("product_number")).setProduct_price(result.getDouble("product_price"))
				.setProduct_thumbnail(result.getString("product_thumbnail")).setProduct_introduction(result.getString("product_introduction"))
				.setProduct_describe(result.getString("product_describ"));
				productItemList.add(productItem);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return productItemList;
	}

	@Override
	public Product findProductByID(int product_id) {
		String sql="select * from sku where product_id=?";
		Product product=new Product();
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, product_id);
			result=prep.executeQuery();
			while(result.next()){
				product.setProduct_id(result.getInt("product_id")).setProduct_name(result.getString("product_name"))
				.setProduct_number(result.getInt("product_number")).setProduct_price(result.getDouble("product_price"))
				.setProduct_thumbnail(result.getString("product_thumbnail")).setProduct_introduction(result.getString("product_introduction"))
				.setProduct_describe(result.getString("product_describe"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
			
		}
		return product;
	}
	

	public buyCar deleteProductByID(int product_id,buyCar buycar){
		boolean isdelete=buycar.deleteProductItem(product_id);
		if(isdelete){
		return buycar;
		}
		return null;
	}

	@Override
	public Customer LoginVrify(String staff_username, String staff_password) {
		if(staff_username==null||staff_password==null){
			return null;
		}
		String sql="SELECT * FROM customer WHERE (customer_username=? OR customer_phoneNumber=?) AND customer_password=?";
		Connection conn=DbManager.getConnection();//获取数据库连接
		Customer customer=new Customer();
		ResultSet result=null;
		PreparedStatement prep=null;
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, staff_username);
			prep.setString(2, staff_username);
			prep.setString(3, staff_password);
			result=prep.executeQuery();
			if(result.next()){
				customer.setCustomerId(result.getInt("customer_id"));
				customer.setCustomerUsername(result.getString("customer_username"));
				customer.setCustomerPassword(result.getString("customer_password"));
				customer.setCustomerPhoneNumber(result.getString("customer_phoneNumber"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeConnection(conn);
		}
		
		return customer;
	}

	@Override
	public boolean addCarProductToDatabase(Product product, int customerid, int productcount) {
		String sql="insert into buycar(customer_id,product_id,product_name,product_price,product_count,product_thumbnail,product_introduction,createTime,updateTime) values(?,?,?,?,?,?,?,?,?)";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		int result=0;
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, customerid);
			prep.setInt(2, product.getProduct_id());
			prep.setString(3, product.getProduct_name());
			prep.setDouble(4, product.getProduct_price());
			prep.setInt(5, productcount);
			prep.setString(6, product.getProduct_thumbnail());
			prep.setString(7, product.getProduct_introduction());
			prep.setLong(8, new Date().getTime());
			prep.setLong(9, new Date().getTime());
			result=prep.executeUpdate();
			if(result==1){
				return true;
			}else{
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeConnection(conn);
		}
		return false;
	}

	@Override
	public buyCar findBuyCarByCustomerId(int customerid,buyCar buycar) {
		String sql="select * from buycar where customer_id=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1,customerid);
			result=prep.executeQuery();
			while(result.next()){
				Product product=new Product();
				product.setProduct_id(result.getInt("product_id")).setProduct_name(result.getString("product_name"))
				.setProduct_introduction(result.getString("product_introduction")).setProduct_price(result.getDouble("product_price"))
				.setProduct_thumbnail(result.getString("product_thumbnail"));
				ProductItem productitem=new ProductItem();
				productitem.setProduct(product);
				productitem.setCount(result.getInt("product_count"));
				buycar.addProductItem(productitem, result.getInt("product_count"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closeResultSet(result);
			DbManager.closePreparedStatement(prep);
			DbManager.closeConnection(conn);
		}
		
		
		
		return buycar;
	}

	@Override
	public boolean deleteProductFromDatabase(int product_id) {
		boolean isdelete=false;
			Connection conn=DbManager.getConnection();
			PreparedStatement prep=null;
			String sql="delete from buycar where product_id=?";
			int result=0;
			try {
				prep=conn.prepareStatement(sql);
				prep.setInt(1, product_id);
				result=prep.executeUpdate();
				if(result>0){
					isdelete=true;
				}else{
					isdelete=false;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DbManager.closePreparedStatement(prep);
				DbManager.closeConnection(conn);
			}
			
		return isdelete;
	}

	@Override
	public ArrayList<Product> findProductByName(String product_name,String pinyin,int brandId) {
		log.info(product_name);
		String sql="select * from sku where product_name like ? or product_describe like ? or product_pinyin=? or product_pinyin like ? and product_state='上架' group by group_id";
		if(brandId==0){
			sql+=" and brand_id="+brandId;
		}
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		ArrayList<Product> productlist=new ArrayList<Product>();
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, "%"+product_name+"%");
			prep.setString(2, "%"+product_name+"%");
			if(pinyin!=null){
				prep.setString(3, pinyin);
				prep.setString(4, "%"+pinyin+"%");
			}else{
				prep.setString(3, product_name);
				prep.setString(4, "%"+product_name+"%");
			}
			result=prep.executeQuery();
			while(result.next()){
				Product product=new Product();
				product.setProduct_id(result.getInt("product_id")).setProduct_name(result.getString("product_name"))
				.setProduct_price(result.getDouble("product_price")).setProduct_introduction(result.getString("product_introduction"))
				.setProduct_describe(result.getString("product_describe")).setProduct_thumbnail(result.getString("product_thumbnail"));
				productlist.add(product);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		
				DbManager.closePreparedStatement(prep);
				DbManager.closeResultSet(result);
				DbManager.closeConnection(conn);
		
		}
		return productlist;
	}
	public ArrayList<Product> findProductByCategoryid(int categoryid) {
		String sql="select * from sku where product_id in(select product_id from productcategory where category_id=?) and product_state='上架' group by group_id";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		ArrayList<Product> productlist=new ArrayList<Product>();
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, categoryid);
			result=prep.executeQuery();
			while(result.next()){
				Product product=new Product();
				product.setProduct_id(result.getInt("product_id")).setProduct_name(result.getString("product_name"))
				.setProduct_price(result.getDouble("product_price")).setProduct_introduction(result.getString("product_introduction"))
				.setProduct_describe(result.getString("product_describe")).setProduct_thumbnail(result.getString("product_thumbnail"));
				productlist.add(product);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return productlist;
	}

	@Override
	public String transformStringToPinyin(String product_name) {
		char[] string=product_name.toCharArray();
		String result="";
		for (int i = 0; i < string.length; i++) {
			String[] pinyin=PinyinHelper.toHanyuPinyinStringArray(string[i]);
			String pinyinstring=new String(pinyin[0]);
			result = result + pinyin[0].substring(0, pinyinstring.length()-1);
		}
		return result;
	}

	@Override
	public Map<Integer,String> findProductCategory() {
		
		String sql="select * from sku_category where category_level=1";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		Map<Integer,String> categorynamemap=new HashMap<Integer,String>();
		try {
			prep=conn.prepareStatement(sql);
			result=prep.executeQuery();
			while(result.next()){
				categorynamemap.put(result.getInt("category_id"), result.getString("category_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
				DbManager.closePreparedStatement(prep);
				DbManager.closeResultSet(result);
				DbManager.closeConnection(conn);
			
		}
		return categorynamemap;
	}
	@Override
	public ArrayList<productCategory> findCategoryById(int category_id) {
		String sql="select * from sku_category where category_father=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		ArrayList<productCategory> categorylist=new ArrayList<productCategory>();
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, category_id);
			result=prep.executeQuery();
			while(result.next()){
				productCategory productcategory=new productCategory();
				productcategory.setCategory_id(result.getInt("category_id"));
				productcategory.setCategory_name(result.getString("category_name"));
				productcategory.setCategory_father(result.getInt("category_father"));
				categorylist.add(productcategory);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
				DbManager.closePreparedStatement(prep);
				DbManager.closeResultSet(result);
				DbManager.closeConnection(conn);
		
		}
		return categorylist;
	}
	@Override
	public String getMessage(String phonenumber,String captcha) {
		String requestUrl = "http://route.showapi.com/28-1";  
        Map params = new HashMap();
        params.put("showapi_appid","73114");
        /*params.put("content","您好，验证码为[code]");
        params.put("title","京东公司");*/
        JSONObject json=new JSONObject();
        json.put("code", captcha);
        params.put("content",json);
        params.put("mobile", phonenumber);
        params.put("tNum", "T170317002857");
        params.put("showapi_sign","2a56a062b13f4272b81ee8be0821e58c");
        String string = apiutil.httpRequest(requestUrl,params);
        //处理返回的JSON数据并返回
        JSONObject pageBean = JSONObject.fromObject(string).getJSONObject("showapi_res_body");
        log.info(pageBean+"123");
        return pageBean.getString("remark");
	}
	@Override
	public ArrayList<String> findCategoryName() {
		String sql="select category_name from sku_category where category_level=1";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		ArrayList<String> categoryList=new ArrayList<String>();
		try {
			prep=conn.prepareStatement(sql);
			result=prep.executeQuery();
			while(result.next()){
				categoryList.add(result.getString("category_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return categoryList;
	}
	@Override
	public ArrayList<Category> findCategory() {
		String sql="select * from sku_category where category_level=1";
		PreparedStatement prep=null;
		ResultSet result=null;
		Connection conn=DbManager.getConnection();
		ArrayList<Category> categoryList=new ArrayList<Category>();
		try {
			prep=conn.prepareStatement(sql);
			result=prep.executeQuery();
			while(result.next()){
				Category category=new Category();
				category.setCategory_id(result.getInt("category_id"));
				category.setCategory_name(result.getString("category_name"));
				category.setCategory_father(result.getInt("category_father"));
				category.setCategory_level(result.getInt("category_level"));
				categoryList.add(category);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return categoryList;
	}
	@Override
	public boolean addNewCategory(String category_name) {
		String sql="insert into sku_category(category_name,category_father,category_level,createTime,updateTime) values(?,?,?,?,?)";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		boolean issuccess=false;
		int result=0;
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, category_name);
			prep.setInt(2, 0);
			prep.setInt(3, 1);
			prep.setLong(4, new Date().getTime());
			prep.setLong(5, new Date().getTime());
			result=prep.executeUpdate();
			if(result==1){
				issuccess=true;
			}else{
				issuccess=false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeConnection(conn);
			
		}
		return issuccess;
	}
	@Override
	public ArrayList<Category> findChildCategoryById(int category_id) {
		String sql="select * from sku_category where category_father=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		ArrayList<Category> categoryList=new ArrayList<Category>();
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, category_id);
			result=prep.executeQuery();
			while(result.next()){
				Category category=new Category();
				category.setCategory_id(result.getInt("category_id"));
				category.setCategory_name(result.getString("category_name"));
				category.setCategory_father(result.getInt("category_father"));
				category.setCategory_level(result.getInt("category_level"));
				categoryList.add(category);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return categoryList;
	}
	@Override
	public boolean deleteCategoryById(int category_id) {
		String sql="delete from sku_category where category_id=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		int result=0;
		boolean isdelete=false;
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, category_id);
			result=prep.executeUpdate();
			if(result==1){
				isdelete=true;
			}else{
				isdelete=false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeConnection(conn);
		}
		return isdelete;
	}
	@Override
	public boolean findCustomerPhoneNumber(String customer_phoneNumber) {
		String sql="select customer_phoneNumber from customer where customer_phoneNumber=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		boolean isExist=false;
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, customer_phoneNumber);
			result=prep.executeQuery();
			if(result.next()){
				isExist=true;
			}else{
				isExist=false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeConnection(conn);
		}
		return isExist;
	}
	@Override
	public boolean addNewCustomer(Customer customer) {
		String sql="insert into customer(customer_username,customer_password,customer_phoneNumber,createTime,updateTime) values(?,?,?,?,?)";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		int result=0;
		boolean isSuccess=false;
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, customer.getCustomerUsername());
			prep.setString(2, customer.getCustomerPassword());
			prep.setString(3, customer.getCustomerPhoneNumber());
			prep.setLong(4, new Date().getTime());
			prep.setLong(5, new Date().getTime());
			result=prep.executeUpdate();
			if(result==1){
				isSuccess=true;
			}else{
				isSuccess=false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isSuccess;
	}
	@Override
	public boolean addChildCategory(String childCategory_name,int categoryFatherId,int categoryLevel) {
		log.info(categoryLevel+"");
		String sql="insert into sku_category(category_name,category_father,category_level,createTime,updateTime) values(?,?,?,?,?)";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		boolean issuccess=false;
		int result=0;
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, childCategory_name);
			prep.setInt(2, categoryFatherId);
			prep.setInt(3, categoryLevel);
			prep.setLong(4, new Date().getTime());
			prep.setLong(5, new Date().getTime());
			result=prep.executeUpdate();
			if(result==1){
				issuccess=true;
			}else{
				issuccess=false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeConnection(conn);
		}
		return issuccess;
	}
	@Override
	public ArrayList<Category> findChildCategoryByName(String categoryName) {
		String sql="SELECT * FROM sku_category WHERE category_father IN (SELECT category_id FROM sku_category WHERE category_name=?)";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		ArrayList<Category> categoryList=new ArrayList<Category>();
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, categoryName);
			result=prep.executeQuery();
			while(result.next()){
				Category category=new Category();
				category.setCategory_id(result.getInt("category_id"));
				category.setCategory_name(result.getString("category_name"));
				category.setCategory_father(result.getInt("category_father"));
				category.setCategory_level(result.getInt("category_level"));
				categoryList.add(category);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return categoryList;
	}
	@Override
	public ArrayList<Category> findInitialSecondChildCategory(String category_name) {
		String sql="select * from sku_category where category_father in (select category_id from sku_category where category_name=?)";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		ArrayList<Category> categoryList=new ArrayList<Category>();
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, category_name);
			result=prep.executeQuery();
			while(result.next()){
				Category category=new Category();
				category.setCategory_id(result.getInt("category_id"));
				category.setCategory_name(result.getString("category_name"));
				category.setCategory_father(result.getInt("category_father"));
				category.setCategory_level(result.getInt("category_level"));
				categoryList.add(category);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return categoryList;
	}
	@Override
	public ArrayList<Category> findInitialThirdChildCategory(String category_name) {
		String sql="select * from sku_category where category_father in (select category_id from sku_category where category_name=?)";
		ArrayList<Category> categoryList=new ArrayList<Category>();
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1,category_name);
			result=prep.executeQuery();
			while(result.next()){
				Category category=new Category();
				category.setCategory_id(result.getInt("category_id"));
				category.setCategory_name(result.getString("category_name"));
				category.setCategory_father(result.getInt("category_father"));
				category.setCategory_level(result.getInt("category_level"));
				categoryList.add(category);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return categoryList;
	}
	@Override
	public ArrayList<String> findBrandName() {
		String sql="select * from sku_brand";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		ArrayList<String> brandList=new ArrayList<String>();
		try {
			prep=conn.prepareStatement(sql);
			result=prep.executeQuery();
			while(result.next()){
				brandList.add(result.getString("brand_name"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return brandList;
	}
	@Override
	public int findBrandIdByName(String brand_name) {
		String sql="select brand_id from sku_brand where brand_name=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		int brand_id=0;
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, brand_name);
			result=prep.executeQuery();
			while(result.next()){
				brand_id=result.getInt("brand_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return brand_id;
	}
	@Override
	public int findCategoryIdByName(String category_name) {
		String sql="select category_id from sku_category where category_name=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		int category_id=0;
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1,category_name);
			result=prep.executeQuery();
			while(result.next()){
				category_id=result.getInt("category_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return category_id;
	}
	@SuppressWarnings("null")
	@Override
	public int findProductIdByName(String product_name) {
		String sql="SELECT product_id FROM sku WHERE product_name=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		int product_id=0;
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, product_name);
			result=prep.executeQuery();
			while(result.next()){
				product_id=result.getInt("product_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return product_id;
	}
	@Override
	public boolean addProductCategory(int product_id, int category_id,int category_level) {
		String sql="insert into productcategory(product_id,category_id,category_level,createTime,updateTime) values(?,?,?,?,?)";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		int result=0;
		boolean isAdd=false;
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, product_id);
			prep.setInt(2, category_id);
			prep.setInt(3, category_level);
			prep.setLong(4, new Date().getTime());
			prep.setLong(5, new Date().getTime());
			result=prep.executeUpdate();
			if(result==1){
				isAdd=true;
			}else{
				isAdd=false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeConnection(conn);
		}
		return isAdd;
	}
	@Override
	public String findProductCategoryByProductId(int product_id) {
		String sql="SELECT category_name FROM sku_category WHERE category_id IN (SELECT category_id FROM productcategory WHERE product_id=?) AND category_level=3";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		String category_name="";
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, product_id);
			result=prep.executeQuery();
			while(result.next()){
				category_name=result.getString("category_name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return category_name;
	}
	@Override
	public int findProductIdByCategoryName(String category_name) {
		String sql="select product_id from productcategory where category_id in (select category_id from sku_category where category_name=?)";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		int product_id=0;
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, category_name);
			result=prep.executeQuery();
			while(result.next()){
				product_id=result.getInt("product_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return product_id;
	}
	@Override
	public ArrayList<Brand> findAllBrand() {
		String sql="select * from sku_brand";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		ArrayList<Brand> brandList=new ArrayList<Brand>();
		try {
			prep=conn.prepareStatement(sql);
			result=prep.executeQuery();
			while(result.next()){
				Brand brand=new Brand();
				brand.setBrand_id(result.getInt("brand_id"));
				brand.setBrand_name(result.getString("brand_name"));
				brand.setBrand_englishName(result.getString("brand_englishName"));
				brandList.add(brand);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return brandList;
	}
	@Override
	public boolean addBrand(String brandName, String brandEnglishName) {
		String sql="insert into sku_brand(brand_name,brand_englishName,createTime,updateTime) values(?,?,?,?)";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		int result=0;
		boolean isAdd=false;
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, brandName);
			prep.setString(2, brandEnglishName);
			prep.setLong(3, new Date().getTime());
			prep.setLong(4, new Date().getTime());
			result=prep.executeUpdate();
			if(result==1){
				isAdd=true;
			}else{
				isAdd=false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeConnection(conn); 
		}
		
		return isAdd;
	}
	@Override
	public boolean deleteBrandById(int brandId) {
		String sql="delete from sku_brand where brand_id=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		int result=0;
		boolean isDelete=false;
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, brandId);
			result=prep.executeUpdate();
			if(result==1){
				isDelete=true;
			}else{
				isDelete=false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeConnection(conn);
		}
		return isDelete;
	}
	@Override
	public ArrayList<Product> findProductByBrandIdAndCategoryId(int brandId,int categoryId) {
		String sql="SELECT * FROM sku WHERE product_id IN(SELECT product_id FROM productcategory WHERE category_id=?) AND brand_id=? and product_state='上架' group by group_id";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		ArrayList<Product> productList = new ArrayList<Product>();
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, categoryId);
			prep.setInt(2, brandId);
			result=prep.executeQuery();
			while(result.next()){
				Product product=new Product();
				product.setProduct_id(result.getInt("product_id")).setProduct_name(result.getString("product_name"))
				.setProduct_price(result.getDouble("product_price")).setProduct_introduction(result.getString("product_introduction"))
				.setProduct_describe(result.getString("product_describe")).setProduct_thumbnail(result.getString("product_thumbnail"));
				productList.add(product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return productList;
	}
	@Override
	public boolean judgmentWhetherExistCategoryName(String categoryName) {
		String sql="select * from sku_category where category_name=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		boolean isExist=false;
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, categoryName);
			result=prep.executeQuery();
			if(result.next()){
				isExist=true;
			}else{
				isExist=false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return isExist;
	}
	@Override
	public ArrayList<Product> findProductByCategoryNameAndBrandId(String categoryName,int brandId) {
		String sql="select * from sku where product_id in (select product_id from productcategory where category_id in (select category_id from sku_category where category_name=?)) and brand_id=? and product_state='上架' group by group_id";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep= null;
		ResultSet result=null;
		ArrayList<Product> productList=new ArrayList<Product>();
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, categoryName);
			prep.setInt(2, brandId);
			result=prep.executeQuery();
			while(result.next()){
				Product product=new Product();
				product.setProduct_id(result.getInt("product_id")).setProduct_name(result.getString("product_name"))
				.setProduct_price(result.getDouble("product_price")).setProduct_introduction(result.getString("product_introduction"))
				.setProduct_describe(result.getString("product_describe")).setProduct_thumbnail(result.getString("product_thumbnail"));
				productList.add(product);
			}
			log.info(productList+"");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return productList;
	}
	@Override
	public boolean updateProductCategory(int productId, int categoryId, int categoryLevel) {
		String sql="update productcategory set category_id=?,updateTime=? where category_level=? and product_id=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		int result=0;
		boolean isUpdate=false;
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, categoryId);
			prep.setLong(2, new Date().getTime());
			prep.setInt(3, categoryLevel);
			prep.setInt(4, productId);
			result=prep.executeUpdate();
			if(result==1){
				isUpdate=true;
			}else{
				isUpdate=false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeConnection(conn);
		}
		return isUpdate;
	}
	@Override
	public boolean addImage(int productId, String imageUrl, String imageCategory) {
		String sql="insert into image(product_id,image_url,image_category,createTime,updateTime) values(?,?,?,?,?)";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		int result=0;
		boolean isAdd=false;
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, productId);
			prep.setString(2, imageUrl);
			prep.setString(3, imageCategory);
			prep.setLong(4, new Date().getTime());
			prep.setLong(5, new Date().getTime());
			result=prep.executeUpdate();
			if(result==1){
				isAdd=true;
			}
			else{
				isAdd=false;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeConnection(conn);
		}
		return isAdd;
	}
	
	@Override
	public int getTotalPages() {
		int count=0;
		int totalpages =0;
		int limit=10;
		String getCountSql="select count(*) from image";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		try {
			prep=conn.prepareStatement(getCountSql);
			result=prep.executeQuery();
			while(result.next()){
				count=result.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		totalpages=(int) Math.ceil(count / (limit * 1.0));
		return totalpages;
	}
	@Override
	public ArrayList<Image> findAllImage(int currentPage) {
		int limit=10;
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		ArrayList<Image> imageList=new ArrayList<Image>();
		String sql="select * from image order by image_id limit "+ (currentPage - 1) * limit + "," + limit;
		try {
			prep=conn.prepareStatement(sql);
			result=prep.executeQuery();
			while(result.next()){
				Image image=new Image();
				image.setImageId(result.getInt("image_id"));
				image.setImageUrl(result.getString("image_url"));
				image.setProductId(result.getInt("product_id"));
				image.setImageCategory(result.getString("image_category"));
				imageList.add(image);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		
		return imageList;
	}
	@Override
	public ArrayList<Image> findImageByProductId(int productId,int currentPage) {
		int limit=10;
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		ArrayList<Image> imageList=new ArrayList<Image>();
		String sql="select * from image  where product_id=? order by image_id limit "+ (currentPage - 1) * limit + "," + limit;
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, productId);
			result=prep.executeQuery();
			while(result.next()){
				Image image=new Image();
				image.setImageId(result.getInt("image_id"));
				image.setImageUrl(result.getString("image_url"));
				image.setProductId(result.getInt("product_id"));
				image.setImageCategory(result.getString("image_category"));
				imageList.add(image);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return imageList;
	}
	@Override
	public Image getImageUrlById(int imageId) {
		String sql="select * from image where image_id=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		String imageUrl="";
		Image image=new Image();
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, imageId);
			result=prep.executeQuery();
			while(result.next()){
				image.setImageCategory(result.getString("image_category"));
				image.setImageId(result.getInt("image_id"));
				image.setImageUrl(result.getString("image_url"));
				image.setProductId(result.getInt("productId"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return image;
	}
	@Override
	public boolean updateImageUrlById(String imageUrl,int imageId,String imageCategory) {
		String sql="update image set image_url=?,updateTime=?,image_category=? where image_id=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		int result=0;
		boolean isUpdate=false;
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, imageUrl);
			prep.setLong(2, new Date().getTime());
			prep.setString(3, imageCategory);
			prep.setInt(4, imageId);
			
			result=prep.executeUpdate();
			if(result==1){
				isUpdate=true;
			}else{
				isUpdate=false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeConnection(conn);
		}
		return isUpdate;
	}
	@Override
	public boolean deleteImageOperationById(int imageId) {
		String sql="delete from image where image_id=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		int result=0;
		boolean isDelete=false;
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, imageId);
			result=prep.executeUpdate();
			if(result==1){
				isDelete=true;
			}else{
				isDelete=false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeConnection(conn);
		}
		return isDelete;
	}
	@Override
	public boolean addBrandAndCategory(int brandId, int categoryId) {
		String sql="insert into brandcategory(brand_id,category_id,createTime,updateTime) values(?,?,?,?)";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		int result=0;
		boolean isInsert=false;
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, brandId);
			prep.setInt(2, categoryId);
			prep.setLong(3, new Date().getTime());
			prep.setLong(4, new Date().getTime());
			result=prep.executeUpdate();
			if(result==1){
				isInsert=true;
			}else{
				isInsert=false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeConnection(conn);
			
		}
		return isInsert;
	}
	@Override
	public ArrayList<String> findCatalogNameByProductId(int productId) {
		log.info("..");
		String sql="select category_name from sku_category where category_id in (select category_id from brandcategory where brand_id in (select brand_id from sku where product_id=?))";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		ArrayList<String> catalogNameList=new ArrayList<String>();
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, productId);
			result=prep.executeQuery();
			while(result.next()){
				String categoryName=result.getString("category_name");
				log.info(categoryName);
				catalogNameList.add(categoryName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
			
		}
		return catalogNameList;
	}
	@Override
	public ArrayList<String> findCategoryNameByProductId(int productId) {
		String sql="select category_name from sku_category where category_id in (select category_id from brandcategory where brand_id in (select brand_id from sku where product_id=?)) and category_level=1";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		ArrayList<String> categoryNameList=new ArrayList<String>();
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, productId);
			result=prep.executeQuery();
			while(result.next()){
				String categoryName=result.getString("category_name");
				log.info(categoryName);
				categoryNameList.add(categoryName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
			
		}
		return categoryNameList;
	}
	@Override
	public ArrayList<productCategory> findCategoryByProductId(int productId) {
		ArrayList<productCategory> categorylist=new ArrayList<productCategory>();
		String sql="select * from sku_category where category_id in (select category_id from brandcategory where brand_id in (select brand_id from sku where product_id=?)) and category_level=1";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, productId);
			result=prep.executeQuery();
			while(result.next()){
				productCategory productcategory=new productCategory();
				productcategory.setCategory_id(result.getInt("category_id"));
				productcategory.setCategory_name(result.getString("category_name"));
				productcategory.setCategory_father(result.getInt("category_father"));
				categorylist.add(productcategory);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
				DbManager.closePreparedStatement(prep);
				DbManager.closeResultSet(result);
				DbManager.closeConnection(conn);
		
		}
		return categorylist;
	}
	@Override
	public ArrayList<String> findProductNameByCategoryId(int categoryId) {
		String sql="select product_name from sku where product_id in (select product_id from productcategory where category_id=?)";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		ArrayList<String> productNameList=new ArrayList<String>();
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, categoryId);
			result=prep.executeQuery();
			while(result.next()){
				String productName=result.getString("product_name");
				productNameList.add(productName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		
		return productNameList;
	}
	@Override
	public ArrayList<Product> findProductByCategoryName(String categoryName) {
		String sql="select * from sku where product_id in (select product_id from productcategory where category_id in (select category_id from sku_category where category_name=?)) and product_state='上架'";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep= null;
		ResultSet result=null;
		ArrayList<Product> productList=new ArrayList<Product>();
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, categoryName);
			result=prep.executeQuery();
			while(result.next()){
				Product product=new Product();
				product.setProduct_id(result.getInt("product_id")).setProduct_name(result.getString("product_name"))
				.setProduct_price(result.getDouble("product_price")).setProduct_introduction(result.getString("product_introduction"))
				.setProduct_describe(result.getString("product_describe")).setProduct_thumbnail(result.getString("product_thumbnail"));
				productList.add(product);
			}
			log.info(productList+"");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return productList;
	}
	@Override
	public ArrayList<String> findShowImageUrlByProductIdAndImageCategory(int productId,String imageCategory) {
		String sql="select image_url from image where product_id=? and image_category=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		ArrayList<String> showImageUrlList=new ArrayList<String>();
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, productId);
			prep.setString(2, imageCategory);
			result=prep.executeQuery();
			while(result.next()){
				String imageUrl=result.getString("image_url");
				showImageUrlList.add(imageUrl);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return showImageUrlList;
	}
	@Override
	public void addStaffOperationLog(Staff staff, OperationLog operationLog) {
		String sql="insert into log(staff_id,staff_name,operation_name,operation_content,createTime,updateTime) values(?,?,?,?,?,?)";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		int result=0;
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, staff.getStaffId());
			prep.setString(2, staff.getStaffName());
			prep.setString(3, operationLog.getOperationName());
			prep.setString(4, operationLog.getOperationContent());
			prep.setLong(5, operationLog.getCreateTime());
			prep.setLong(6, operationLog.getUpdateTime());
			result=prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeConnection(conn);
			
		}
	}
	@Override
	public int findImageIdByImageUrl(String imageUrl) {
		String sql="select image_id from image where image_url=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		int imageId=0;
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, imageUrl);
			result=prep.executeQuery();
			while(result.next()){
				imageId=result.getInt("image_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return imageId;
	}
	@Override
	public boolean addAttributeByCategoryId(int categoryId,String attributeName) {
		String sql="insert into category_attribute(attribute_name,category_id) values(?,?)";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		boolean isAdd=false;
		int result=0;
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, attributeName);
			prep.setInt(2, categoryId);
			result=prep.executeUpdate();
			if(result==1){
				isAdd=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeConnection(conn);
		}
		return isAdd;
	}
	@Override
	public Map<Integer,String> findAttributeByCategoryId(int categoryId) {
		String sql="select * from category_attribute where category_id=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		Map<Integer,String> attributeMap=new HashMap<Integer, String>();
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, categoryId);
			result=prep.executeQuery();
			while(result.next()){
				attributeMap.put(result.getInt("attribute_id"), result.getString("attribute_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return attributeMap;
	}
	@Override
	public void updateAttributeByAttributeId(int attributeId,String attributeName) {
		String sql="update category_attribute set attribute_name=? where attribute_id=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		int result=0;
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, attributeName);
			prep.setInt(2, attributeId);
			result=prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeConnection(conn);
			
		}
	}
	@Override
	public boolean deleteAttributeByAttributeId(int attributeId) {
		String sql="delete from category_attribute where attribute_id=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		int result=0;
		boolean isDelete=false;
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, attributeId);
			result=prep.executeUpdate();
			if(result==1){
				isDelete=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeConnection(conn);
		}
		return isDelete;
	}
	@Override
	public ArrayList<String> findCategoryAttributeByProductId(int productId) {
		String sql="select * from category_attribute where category_id in (select category_id from productcategory where product_id=? and category_level=1)";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		ArrayList<String> attributeList=new ArrayList<String>();
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, productId);
			result=prep.executeQuery();
			while(result.next()){
				attributeList.add(result.getString("attribute_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return attributeList;
	}
	@Override
	public boolean addProductComment(int productId, int customerId,String customerName,String productContent, String commentLevel,String createTime,String updateTime) {
		String sql="insert into sku_comment(comment_value,product_id,comment_level,customer_id,createTime,updateTime,customer_name) values(?,?,?,?,?,?,?)";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		boolean isAdd=false;
		int result=0;
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, productContent);
			prep.setInt(2, productId);
			prep.setString(3, commentLevel);
			prep.setInt(4, customerId);
			prep.setString(5, createTime);
			prep.setString(6, updateTime);
			prep.setString(7, customerName);
			result=prep.executeUpdate();
			if(result==1){
				isAdd=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeConnection(conn);
		}
		return isAdd;
	}
	@Override
	public ArrayList<Comment> findCommentByProductId(int productId,int currentPage) {
		int limit=10;
		String sql="select * from sku_comment where product_id=? order by comment_id limit "+(currentPage-1)*limit+","+limit;
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		ArrayList<Comment> commentList=new ArrayList<Comment>();
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, productId);
			result=prep.executeQuery();
			while(result.next()){
				Comment comment=new Comment();
				comment.setCommentId(result.getInt("comment_id"));
				comment.setCommentValue(result.getString("comment_value"));
				comment.setCreateTime(result.getString("createTime"));
				comment.setCommentLevel(result.getString("comment_level"));
				comment.setCustomerId(result.getInt("customer_id"));
				comment.setCustomerName(result.getString("customer_name"));
				comment.setProductId(result.getInt("product_id"));
				comment.setUpdateTime(result.getString("updateTime"));
				commentList.add(comment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
			
		}
		return commentList;
	}
	@Override
	public String findCustomerNameByCustomerId(int customerId) {
		String sql="select customer_username from customer where customer_id=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		String customerName="";
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, customerId);
			result=prep.executeQuery();
			while(result.next()){
				customerName=result.getString("customer_username");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeConnection(conn);
			DbManager.closeConnection(conn);
		}
		return customerName;
	}
	
	@Override
	public Map<String,Integer> findCommentCountByCommentLevel(String commentLevel,Map<String,Integer> commentLevelMap,int productId) {
		String sql="select count(*) from sku_comment where comment_level=? and product_id=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		int count=0;
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, commentLevel);
			prep.setInt(2, productId);
			result=prep.executeQuery();
			while(result.next()){
				count=result.getInt(1);
			}
			commentLevelMap.put(commentLevel, count);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
				
		return commentLevelMap;
	}
	
	public int getCommentTotalPages(int productId) {
		int count=0;
		int totalpages =0;
		int limit=10;
		String getCountSql="select count(*) from sku_comment where product_id=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		try {
			prep=conn.prepareStatement(getCountSql);
			prep.setInt(1, productId);
			result=prep.executeQuery();
			while(result.next()){
				count=result.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		totalpages=(int) Math.ceil(count / (limit * 1.0));
		return totalpages;
	}
	
	@Override
	public ArrayList<Comment> findCommentByLevel(String commentLevel,int currentPage,int productId) {
		int limit=10;
		String sql="select * from sku_comment where comment_level=? and product_id=? order by comment_id limit "+ (currentPage - 1) * limit + "," + limit;
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		ArrayList<Comment> commentList=new ArrayList<Comment>();
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, commentLevel);
			prep.setInt(2, productId);
			result=prep.executeQuery();
			while(result.next()){
				Comment comment=new Comment();
				comment.setCommentId(result.getInt("comment_id"));
				comment.setCommentLevel(result.getString("comment_level"));
				comment.setCommentValue(result.getString("comment_value"));
				comment.setCreateTime(result.getString("createTime"));
				comment.setCustomerId(result.getInt("customer_id"));
				comment.setCustomerName(result.getString("customer_name"));
				comment.setProductId(result.getInt("product_id"));
				comment.setUpdateTime(result.getString("updateTime"));
				commentList.add(comment);
			}
			log.info(commentList+"list");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return commentList;
	}
	@Override
	public int getCommentTotalPagesByCommentLevel(String commentLevel,int productId) {
		int count=0;
		int totalpages =0;
		int limit=10;
		String getCountSql="select count(*) from sku_comment where comment_level=? and product_id=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		try {
			prep=conn.prepareStatement(getCountSql);
			prep.setString(1, commentLevel);
			prep.setInt(2, productId);
			result=prep.executeQuery();
			while(result.next()){
				count=result.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		totalpages=(int) Math.ceil(count / (limit * 1.0));
		return totalpages;
	}
	@Override
	public ArrayList<CategoryAttribute> findAttributeNameByCategoryId(int categoryId) {
		String sql="select * from category_attribute where category_id=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		ArrayList<CategoryAttribute> attributeList=new ArrayList<CategoryAttribute>();
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, categoryId);
			result=prep.executeQuery();
			while(result.next()){
				CategoryAttribute categoryAttribute=new CategoryAttribute();
				categoryAttribute.setAttributeId(result.getInt("attribute_id"));
				categoryAttribute.setAttributeName(result.getString("attribute_name"));
				categoryAttribute.setCategoryId(result.getInt("category_id"));
				attributeList.add(categoryAttribute);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return attributeList;
	}
	@Override
	public int findCategoryIdByProductId(int productId) {
		String sql="select category_id from productcategory where product_id=? and category_level=1";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		int categoryId=0;
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, productId);
			result=prep.executeQuery();
			while(result.next()){
				categoryId=result.getInt("category_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return categoryId;
	}
	@Override
	public int findAttributeIdByName(String attributeName,int categoryId) {
		String sql="select attribute_id from category_attribute where attribute_name=? and category_id=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		int attributeId=0;
		ResultSet result=null;
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, attributeName);
			prep.setInt(2, categoryId);
			result=prep.executeQuery();
			while(result.next()){
				attributeId=result.getInt("attribute_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
			
		}
		return attributeId;
	}
	/*@Override
	public boolean addSkuAttribute(int productId,int attributeId,String attributeValue) {
		String sql="insert into sku_attribute(product_id,attribute_id,attribute_value) values(?,?,?)";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		int result=0;
		boolean isAdd=false;
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, productId);
			prep.setInt(2, attributeId);
			prep.setString(3, attributeValue);
			result=prep.executeUpdate();
			if(result==1){
				isAdd=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeConnection(conn);
		}
		return isAdd;
	}*/
	@Override
	public List<SkuAttribute> findAttributeByProductId(int productId) {
		String sql="SELECT attribute_name,attribute_value,sku_id,product_id FROM category_attribute,sku_attribute WHERE category_attribute.attribute_id=sku_attribute.attribute_id AND sku_attribute.product_id=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		List<SkuAttribute> attributeList=new ArrayList<SkuAttribute>();
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, productId);
			result=prep.executeQuery();
			while(result.next()){
				SkuAttribute skuAttribute=new SkuAttribute();
				skuAttribute.setAttributeName(result.getString("attribute_name"));
				skuAttribute.setAttributeValue(result.getString("attribute_value"));
				skuAttribute.setProductId(result.getInt("product_id"));
				skuAttribute.setSkuId(result.getInt("sku_id"));
				attributeList.add(skuAttribute);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return attributeList;
	}
	@Override
	public List<String> findAttributeNameByProductId(int productId) {
		String sql="select attribute_name from category_attribute where category_id=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		int count=0;
		List<String> attributeNameList=new ArrayList<String>();
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, productId);
			result=prep.executeQuery();
			while(result.next()){
				attributeNameList.add(result.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return attributeNameList;
	}
	@Override
	public Map<String, List<String>> findProductAttributeListByProductIdAndCategoryId(int categoryId,
			int productId) {
		String sql="select * from category_attribute where category_id=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		Map<String, List<String>> productAttributeMap=new HashMap<String, List<String>>();
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, categoryId);
			result=prep.executeQuery();
			while(result.next()){
				int attributeId=result.getInt("attribute_id");
				String attributeName=result.getString("attribute_name");
				String nextsql="select * from sku_attribute where attribute_id=? and product_id=?";
				PreparedStatement nextprep=null;
				ResultSet nextresult=null;
				List<String> attributeValueList=new ArrayList<String>();
				nextprep=conn.prepareStatement(nextsql);
				nextprep.setInt(1, attributeId);
				nextprep.setInt(2, productId);
				nextresult=nextprep.executeQuery();
				while(nextresult.next()){
					attributeValueList.add(nextresult.getString("attribute_value"));
				}
				DbManager.closePreparedStatement(nextprep);
				DbManager.closeResultSet(nextresult);
				productAttributeMap.put(attributeName, attributeValueList);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
				
		return productAttributeMap;
	}
	@Override
	public ArrayList<Sensitive> getAllSensitive() {
		String sql="select * from comment_sensitive";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		ArrayList<Sensitive> genericList=new ArrayList<Sensitive>();
		try {
			prep=conn.prepareStatement(sql);
			result=prep.executeQuery();
			while(result.next()){
				Sensitive sensitive=new Sensitive();
				sensitive.setSensitiveId(result.getInt("sensitive_id"));
				sensitive.setSensitiveValue(result.getString("sensitive_value"));
				genericList.add(sensitive);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return genericList;
	}
	@Override
	public boolean deleteSensitiveById(int sensitiveId) {
		String sql="delete from comment_sensitive where sensitive_id=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		boolean isDelete=false;
		int result=0;
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, sensitiveId);
			result=prep.executeUpdate();
			if(result==1){
				isDelete=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeConnection(conn);
		}
		return isDelete;
	}
	@Override
	public boolean addSensitive(String sensitiveValue) {
		String sql="insert into comment_sensitive(sensitive_value) values(?)";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		int result=0;
		boolean isAdd=false;
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, sensitiveValue);
			result=prep.executeUpdate();
			if(result==1){
				isAdd=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeConnection(conn);
		}
		return isAdd;
	}
	@Override
	public boolean addCommentImage(String imageSrc, int productId, int customerId, int commentId,String customerName) {
		String sql="insert into comment_image(comment_id,product_id,customer_id,image_src,customer_name) values(?,?,?,?,?)";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		boolean isAdd=false;
		int result=0;
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, commentId);
			prep.setInt(2, productId);
			prep.setInt(3, customerId);
			prep.setString(4, imageSrc);
			prep.setString(5, customerName);
			result=prep.executeUpdate();
			if(result==1){
				isAdd=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeConnection(conn);
		}
		return isAdd;
	}
	@Override
	public int findCommentIdByTimeAndCustomerId(int customerId, String createTime) {
		String sql="select comment_id from sku_comment where customer_id=? and createTime=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		int commentId=0;
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, customerId);
			prep.setString(2, createTime);
			result=prep.executeQuery();
			while(result.next()){
				commentId=result.getInt("comment_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return commentId;
	}
	@Override
	public ArrayList<String> findImageSrcByComentId(int commentId) {
		// TODO Auto-generated method stub
		String sql="select * from comment_image where comment_id=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		ArrayList<String> commentImageList=new ArrayList<String>();
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, commentId);
			result=prep.executeQuery();
			while(result.next()){
				CommentImage commentImage=new CommentImage();
				commentImage.setCommentId(result.getInt("comment_id")).setCommentImageId(result.getInt("commentimage_id")).setCreateTime(result.getString("createTime")).setCustomerId(result.getInt("customer_id"))
				.setCustomerName(result.getString("customer_name")).setImageSrc(result.getString("image_src")).setProductId(result.getInt("product_id"));
				commentImageList.add(commentImage.getImageSrc());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return commentImageList;
	}
	@Override
	public ArrayList<Comment> findComment(int currentPage) {
		int limit=20;
		String sql="select * from sku_comment order by comment_id limit "+(currentPage-1)*limit+","+limit;
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		ArrayList<Comment> commentList=new ArrayList<Comment>();
		try {
			prep=conn.prepareStatement(sql);
			result=prep.executeQuery();
			while(result.next()){
				Comment comment=new Comment();
				comment.setCommentId(result.getInt("comment_id")).setCommentLevel(result.getString("comment_level"))
				.setCommentValue(result.getString("comment_value")).setCreateTime(result.getString("createTime"))
				.setCustomerId(result.getInt("customer_id")).setCustomerName(result.getString("customer_name"))
				.setProductId(result.getInt("product_id")).setUpdateTime(result.getString("updateTime"));
				commentList.add(comment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return commentList;
	}
	@Override
	public int getCommentTotalPage(HashMap<String,String> conditionMap) {
		int count=0;
		int totalpages =0;
		int limit=20;
		String getCountSql="select count(*) from sku_comment";
		if (conditionMap!=null) {
			getCountSql = sqlSplice.sqlsplice(conditionMap, getCountSql);
		}
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		try {
			prep=conn.prepareStatement(getCountSql);
			result=prep.executeQuery();
			while(result.next()){
				count=result.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn);
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
		}
		totalpages=(int) Math.ceil(count / (limit * 1.0));
		return totalpages;
	}
	@Override
	public ArrayList<CommentImage> findCommentImageByComentId(int commentId) {
		// TODO Auto-generated method stub
				String sql="select * from comment_image where comment_id=?";
				Connection conn=DbManager.getConnection();
				PreparedStatement prep=null;
				ResultSet result=null;
				ArrayList<CommentImage> commentImageList=new ArrayList<CommentImage>();
				try {
					prep=conn.prepareStatement(sql);
					prep.setInt(1, commentId);
					result=prep.executeQuery();
					while(result.next()){
						CommentImage commentImage=new CommentImage();
						commentImage.setCommentId(result.getInt("comment_id")).setCommentImageId(result.getInt("commentimage_id")).setCreateTime(result.getString("createTime")).setCustomerId(result.getInt("customer_id"))
						.setCustomerName(result.getString("customer_name")).setImageSrc(result.getString("image_src")).setProductId(result.getInt("product_id"));
						commentImageList.add(commentImage);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					DbManager.closePreparedStatement(prep);
					DbManager.closeResultSet(result);
					DbManager.closeConnection(conn);
				}
				return commentImageList;
	}
	@Override
	public boolean deleteCommentImageBycommentImageId(int commentId) {
		String sql="delete from comment_image where commentImage_id=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		int result=0;
		boolean isDelete=false;
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, commentId);
			result=prep.executeUpdate();
			if(result==1){
				isDelete=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeConnection(conn);
		}
		return isDelete;
	}
	@Override
	public ArrayList<Comment> findCommentByCondition(HashMap<String,String> conditionMap,int currentPage) {
		int limit=20;
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		ArrayList<Comment> commentList=new ArrayList<Comment>();
		String sql="select * from sku_comment";
		if(conditionMap!=null){
			sql=sqlSplice.sqlsplice(conditionMap, sql);
			sql+=" order by comment_id limit " + (currentPage - 1) * limit + "," + limit;
		}
		log.info(sql);
		try {
			prep=conn.prepareStatement(sql);
			result=prep.executeQuery();
			while(result.next()){
				Comment comment=new Comment();
				comment.setCommentId(result.getInt("comment_id")).setCommentLevel(result.getString("comment_level"))
				.setCommentValue(result.getString("comment_value")).setCreateTime(result.getString("createTime"))
				.setCustomerId(result.getInt("customer_id")).setCustomerName(result.getString("customer_name"))
				.setProductId(result.getInt("product_id")).setUpdateTime(result.getString("updateTime"));
				commentList.add(comment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return commentList;
	}
	@Override
	public boolean deleteCommentById(int commentId) {
		String sql="delete from sku_comment where comment_id=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		int result=0;
		boolean isDelete=false;
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, commentId);
			result=prep.executeUpdate();
			if(result==1){
				isDelete=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeConnection(conn);
		}
		return isDelete;
	}
	@Override
	public ArrayList<String> findCategoryAttributeNameById(int categoryId) {
		String sql="select * from category_attribute where category_id=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		ArrayList<String> categoryAttributeList=new ArrayList<String>();
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, categoryId);
			result=prep.executeQuery();
			while(result.next()){
				String attributeName=result.getString("attribute_name");
				categoryAttributeList.add(attributeName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return categoryAttributeList;
	}
	@Override
	public ArrayList<CategoryAttributeValue> findCategoryAttributeValueById(int attributeId) {
		String sql="select * from c_attribute_value where attribute_id=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		ArrayList<CategoryAttributeValue> categoryAttributeValueList=new ArrayList<CategoryAttributeValue>();
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, attributeId);
			result=prep.executeQuery();
			while(result.next()){
				CategoryAttributeValue categoryAttributeValue=new CategoryAttributeValue();
				categoryAttributeValue.setAttributeId(result.getInt("attribute_id"));
				categoryAttributeValue.setAttributeValue(result.getString("attribute_value"));
				categoryAttributeValue.setAttributeValueId(result.getInt("attributeValue_id"));
				categoryAttributeValueList.add(categoryAttributeValue);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return categoryAttributeValueList;
	}
	@Override
	public boolean addCategoryAttributeValue(int attributeId, String c_attr_value) {
		String sql="insert into c_attribute_value(attribute_id,attribute_value) values(?,?)";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		boolean isAdd=false;
		int result=0;
		log.info(c_attr_value);
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, attributeId);
			prep.setString(2, c_attr_value);
			result=prep.executeUpdate();
			if(result==1){
				isAdd=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeConnection(conn);
		}
		return isAdd;
	}
	@Override
	public ArrayList<String> findCategoryAttributeValueStringById(int attributeId) {
		String sql="select * from c_attribute_value where attribute_id=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		ArrayList<String> categoryAttributeValueList=new ArrayList<String>();
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, attributeId);
			result=prep.executeQuery();
			while(result.next()){
				String attributeValue=result.getString("attribute_value");
				categoryAttributeValueList.add(attributeValue);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return categoryAttributeValueList;
	}
	@Override
	public boolean deleteAttributeValueById(int attributeValueId) {
		String sql="delete from c_attribute_value where c_attr_value_id=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		int result=0;
		boolean isDelete=false;
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, attributeValueId);
			result=prep.executeUpdate();
			if(result==1){
				isDelete=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeConnection(conn);
		}
		return isDelete;
	}
	@Override
	public boolean addSkuAttribute(int skuId, int attributeId, int attributeValueId,int groupSign) {
		String sql="insert into sku_attribute(product_id,attribute_id,attributeValue_id,group_sign) values(?,?,?,?)";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		int result=0;
		boolean isAdd=false;
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, skuId);
			prep.setInt(2, attributeId);
			prep.setInt(3, attributeValueId);
			prep.setInt(4, groupSign);
			result=prep.executeUpdate();
			if(result==1){
				isAdd=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeConnection(conn);
		}
		return isAdd;
	}
	@Override
	public int isExistProduct(String prouductName) {
		String sql="SELECT group_id FROM sku WHERE product_name=? GROUP BY product_id";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		int groupId=0;
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, prouductName);
			result=prep.executeQuery();
			while(result.next()){
				groupId=result.getInt("group_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return groupId;
	}
	@Override
	public boolean addReceiverAddress(int customerId, String receiverAddress, String phoneNumber, String receiverName,
			String email,String otherAddressName) {
		log.info(otherAddressName+customerId+email+phoneNumber+receiverAddress+receiverName+"---------------");
		String sql="insert into customer_receiver(customer_id,receiver_address,receiver_name,phone_number,email,otherAddress_name) values(?,?,?,?,?,?)";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		int result=0;
		boolean isAdd=false;
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, customerId);
			prep.setString(2, receiverAddress);
			prep.setString(3, receiverName);
			prep.setString(4, phoneNumber);
			prep.setString(5, email);
			prep.setString(6, otherAddressName);
			result=prep.executeUpdate();
			if(result==1){
				isAdd=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeConnection(conn);
		}
		return isAdd;
	}
	@Override
	public ArrayList<ReceiverAddress> findReseiverAddressByCustomerId(int customerId) {
		String sql="select * from customer_receiver where customer_id=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		ArrayList<ReceiverAddress> receiverAddressList=new ArrayList<ReceiverAddress>();
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, customerId);
			result=prep.executeQuery();
			while(result.next()){
				ReceiverAddress receiverAddress=new ReceiverAddress();
				receiverAddress.setCustomerId(result.getInt("customer_id"));
				receiverAddress.setEmail(result.getString("email"));
				receiverAddress.setOtherAddressName(result.getString("otherAddress_name"));
				receiverAddress.setReceiverAddress(result.getString("receiver_address"));
				receiverAddress.setReceiverId(result.getInt("receiver_id"));
				receiverAddress.setReceiverName(result.getString("receiver_name"));
				receiverAddress.setReceiverPhone(result.getString("phone_number"));
				receiverAddressList.add(receiverAddress);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return receiverAddressList;
	}
	@Override
	public int findAttributeValueIdByattributeValue(int attributeId, String attributeValue) {
		String sql="select attributeValue_id from c_attribute_value where attribute_id=? and attribute_value=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		int attributeValueId=0;
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, attributeId);
			prep.setString(2, attributeValue);
			result=prep.executeQuery();
			while(result.next()){
				attributeValueId=result.getInt("attributeValue_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return attributeValueId;
	}
	@Override
	public ArrayList<String> findAttributeValueByProductId(int productId, String attributeName) {
		String sql="SELECT attribute_value FROM c_attribute_value WHERE attributeValue_id IN("+
				"SELECT attributeValue_id from sku_attribute skua,sku WHERE sku.product_id=skua.product_id AND product_state='上架' AND attribute_id IN (SELECT attribute_id FROM "+
				"category_attribute WHERE attribute_name=? AND category_id=(SELECT category_id "+
				"FROM productcategory WHERE product_id=? AND category_level=1)) AND sku.product_id IN(SELECT product_id "+
				"FROM sku WHERE group_id=(SELECT group_id FROM sku WHERE product_id=?)))";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		ArrayList<String> attributeValueList=new ArrayList<String>();
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, attributeName);
			prep.setInt(2, productId);
			prep.setInt(3, productId);
			result=prep.executeQuery();
			while(result.next()){
				log.info(result.getString("attribute_value"));
				attributeValueList.add(result.getString("attribute_value"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return attributeValueList;
	}
	@Override
	public int findMaxGroupId() {
		String sql="SELECT MAX(group_id) as group_id FROM sku GROUP BY group_id";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		int groupId=0;
		try {
			prep=conn.prepareStatement(sql);
			result=prep.executeQuery();
			while(result.next()){
				groupId=result.getInt("group_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return groupId;
	}
	@Override
	public ArrayList<String> findProductSpecificationByProductId(int productId) {
		String sql="SELECT attribute_value FROM c_attribute_value WHERE attributeValue_id IN(SELECT attributeValue_id FROM sku_attribute WHERE product_id=?)";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		ArrayList<String> productSpecificationList=new ArrayList<String>();
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, productId);
			result=prep.executeQuery();
			while(result.next()){
				productSpecificationList.add(result.getString("attribute_value"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return productSpecificationList;
	}
	@Override
	public int findProductIdByAttributeId(int oneattributeValueId, int anotherattributeValueId, int groupId) {
		String sql="SELECT product_id FROM sku_attribute WHERE product_id IN(SELECT product_id FROM sku_attribute WHERE attributeValue_id=? AND product_id IN(SELECT product_id FROM sku WHERE group_id=? and product_state='上架')) AND attributeValue_id=? ";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		int productId=0;
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, oneattributeValueId);
			prep.setInt(2, groupId);
			prep.setInt(3, anotherattributeValueId);
			result=prep.executeQuery();
			while(result.next()){
				productId=result.getInt("product_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return productId;
	}
	@Override
	public int findGroupIdByProductId(int productId) {
		String sql="SELECT group_id FROM sku WHERE product_id=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		int groupId=0;
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, productId);
			result=prep.executeQuery();
			while(result.next()){
				groupId=result.getInt("group_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return groupId;
	}
	@Override
	public int findAttributeValueIdByCategoryIdAndAttributeValue(int categoryId, String attributeValue) {
		String sql="SELECT attributeValue_id FROM category_attribute ca,c_attribute_value cav WHERE ca.attribute_id=cav.attribute_id AND category_id=? AND attribute_value=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		int attributeValueId=0;
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, categoryId);
			prep.setString(2, attributeValue);
			result=prep.executeQuery();
			while(result.next()){
				attributeValueId=result.getInt("attributeValue_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return attributeValueId;
	}
	@Override
	public String findCustomerPhoneByCustomerId(int customerId) {
		String sql="select customer_phoneNumber from customer where customer_id=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		String customerPhone="";
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, customerId);
			result=prep.executeQuery();
			while(result.next()){
				customerPhone=result.getString("customer_phoneNumber");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return customerPhone;
	}
	@Override
	public boolean addOrderInformation(Order order) {
		String sql="insert into orders(order_number,aliOrder_number,product_id,customer_id,customer_phone,order_totalPrice,receiver_name,receiver_address,receiver_phone,createTime,updateTime,order_state) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		boolean isAdd=false;
		int result=0;
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, order.getOrderNumber());
			prep.setString(2, order.getAliOrderNumber());
			prep.setString(3, order.getProductId());
			prep.setInt(4, order.getCustomerId());
			prep.setString(5, order.getCustomerPhone());
			prep.setDouble(6, order.getTotalPrice());
			prep.setString(7, order.getReceiverName());
			prep.setString(8, order.getReceiverAddress());
			prep.setString(9, order.getReceiverPhone());
			prep.setString(10, order.getCreateTime());
			prep.setString(11, order.getUpdateTime());
			prep.setString(12, order.getOrderState());
			result=prep.executeUpdate();
			if(result==1){
				isAdd=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn);
			DbManager.closePreparedStatement(prep);
			
		}
		return isAdd;
	}
	@Override
	public ArrayList<Order> findOrderListByCondition(HashMap<String,String> conditionMap, int currentPage) {
		int limit=20;
		String sql="select * from orders";
		if(conditionMap==null){
			sql="select * from orders order by order_id limit "+(currentPage-1)*limit+","+limit;
		}else{
			sql=sqlSplice.sqlsplice(conditionMap, sql);
			sql=sql+" order by order_id limit "+(currentPage-1)*limit+","+limit;
		}
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		ArrayList<Order> orderList=new ArrayList<Order>();
		try {
			prep=conn.prepareStatement(sql);
			result=prep.executeQuery();
			while(result.next()){
				Order order=new Order();
				order.setAliOrderNumber(result.getString("aliOrder_number")).setCreateTime(result.getString("createTime")).setCustomerId(result.getInt("customer_id"))
				.setCustomerPhone(result.getString("customer_phone")).setOrderNumber(result.getString("order_number")).setProductId(result.getString("product_id"))
				.setReceiverAddress(result.getString("receiver_address")).setReceiverName(result.getString("receiver_name")).setReceiverPhone(result.getString("receiver_phone"))
				.setTotalPrice(result.getDouble("order_totalPrice")).setUpdateTime(result.getString("updateTime")).setOrderState(result.getString("order_state"));
				orderList.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn);
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
		}
		
		return orderList;
	}
	@Override
	public int getOrderTotalPage(HashMap<String, String> conditionMap) {
		int count=0;
		int totalpages =0;
		int limit=20;
		String getCountSql="select count(*) from orders";
		if (conditionMap!=null) {
			getCountSql = sqlSplice.sqlsplice(conditionMap, getCountSql);
		}
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		try {
			prep=conn.prepareStatement(getCountSql);
			result=prep.executeQuery();
			while(result.next()){
				count=result.getInt(1);
				log.info(count+"");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn);
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
		}
		totalpages=(int) Math.ceil(count / (limit * 1.0));
		log.info(totalpages+"");
		return totalpages;
	}
	@Override
	public void updateDeliverGoodsStateByOrderNumber(String orderNumber) {
		String findDeliverGoodsStateSql="select order_state from orders where order_number=?";
		String changeDeliverGoodsStateSql="update orders set order_state=? where order_number=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet findResult=null;
		String state="";
		int updateResult=0;
		try {
			prep=conn.prepareStatement(findDeliverGoodsStateSql);
			prep.setString(1, orderNumber);
			findResult=prep.executeQuery();
			while(findResult.next()){
				state=findResult.getString("order_state");
			}
			if(state.equals("未发货")){
				prep=conn.prepareStatement(changeDeliverGoodsStateSql);
				prep.setString(1, "已发货");
				prep.setString(2, orderNumber);
				updateResult=prep.executeUpdate();
			}else{
				prep=conn.prepareStatement(changeDeliverGoodsStateSql);
				prep.setString(1, "未发货");
				prep.setString(2, orderNumber);
				updateResult=prep.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn);
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(findResult);
		}
	}
	@Override
	public double findTotalPriceByOrderNumber(String orderNumber) {
		String sql="select sum(order_totalPrice) from orders where order_number=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		double orderTotalPrice=0.00;
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, orderNumber);
			result=prep.executeQuery();
			while(result.next()){
				orderTotalPrice=result.getDouble(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn);
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
		}
		return orderTotalPrice;
	}
	@Override
	public Order findOrderByOrderNumber(String orderNumber) {
		String sql="select * from orders where order_number=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		Order order=new Order();
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, orderNumber);
			result=prep.executeQuery();
			while(result.next()){
				order.setAliOrderNumber(result.getString("aliOrder_number")).setCreateTime(result.getString("createTime")).setCustomerId(result.getInt("customer_id"))
				.setCustomerPhone(result.getString("customer_phone")).setOrderState(result.getString("order_state")).setOrderNumber(result.getString("order_number"))
				.setProductId(result.getString("product_id")).setReceiverAddress(result.getString("receiver_address")).setReceiverName(result.getString("receiver_name"))
				.setReceiverPhone(result.getString("receiver_phone")).setTotalPrice(result.getDouble("order_totalPrice")).setUpdateTime(result.getString("updateTime"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn);
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
		}
		return order;
	}
	@Override
	public boolean updateOrderRefundStateByOrderNumber(String orderNumber) {
		String sql="update orders set order_state=? where order_number=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		boolean isDelete=false;
		int result=0;
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, "已退款");
			prep.setString(2, orderNumber);
			result=prep.executeUpdate();
			if(result==1){
				isDelete=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn);
			DbManager.closePreparedStatement(prep);
		}
		return isDelete;
	}
	@Override
	public ArrayList<String> findReceiverInfoByOrderNumber(String orderNumber) {
		String sql="select * from orders where order_number=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		ArrayList<String> receiverInfoList=new ArrayList<String>();
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, orderNumber);
			result=prep.executeQuery();
			while(result.next()){
				receiverInfoList.add(result.getString("receiver_name"));
				receiverInfoList.add(result.getString("receiver_address"));
				receiverInfoList.add(result.getString("receiver_phone"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn);
			DbManager.closePreparedStatement(prep);
		}
		return receiverInfoList;
	}
	@Override
	public boolean updateReveiverInfoByOrderNumber(String orderNumber,String receiverName,String receiverPhone,String receiverAddress) {
		String sql="UPDATE orders SET receiver_name=?,receiver_phone=?,receiver_address=? WHERE order_number=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		boolean isUpdate=false;
		int result=0;
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, receiverName);
			prep.setString(2, receiverPhone);
			prep.setString(3, receiverAddress);
			prep.setString(4, orderNumber);
			result=prep.executeUpdate();
			log.info(result+"");
			if(result==1){
				isUpdate=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn);
			DbManager.closePreparedStatement(prep);
		}
		return isUpdate;
	}
	@Override
	public ArrayList<MyOrder> findOrderByCustomerId(int customerId,String timeDefference,String orderState) {
		String sql="";
		Connection conn =DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime=simpleDateFormat.format(new Date());
		if(orderState.equals("")){
			sql="select * from orders where customer_id=? and createTime between ? and ? and order_state!='用户删除'";
		}else{
			sql="select * from orders where customer_id=? and createTime between ? and ? and order_state=?";
		}
		ArrayList<MyOrder> myorderList=new ArrayList<MyOrder>();
		try {
			prep=conn.prepareStatement(sql);
			if(orderState.equals("")){
				prep.setInt(1, customerId);
				prep.setString(2, timeDefference);
				prep.setString(3, currentTime);
			}else{
				prep.setInt(1, customerId);
				prep.setString(2, timeDefference);
				prep.setString(3, currentTime);
				prep.setString(4, orderState);
			}
			result=prep.executeQuery();
			while(result.next()){
				MyOrder myorder=new MyOrder();
				Product  product=new Product();
				String productId=result.getString("product_id");
				ArrayList<Product> productList=new ArrayList<Product>();
				if(productId.contains(",")){
					List<String> productIdList=Arrays.asList(productId.split(","));
					for(int i=0;i<productIdList.size();i++){
						int productID=Integer.parseInt(productIdList.get(i));
						product=findProductByID(productID);
						productList.add(product);
					}
				}else{
					int productID=Integer.parseInt(productId);
					product=findProductByID(productID);
					productList.add(product);
				}
				myorder.setOrderState(result.getString("order_state")).setReceiverName(result.getString("receiver_name")).setTotalPrice(result.getDouble("order_totalPrice")).setCreateTime(result.getString("createTime")).setOrderNumber(result.getString("order_number")).setProductList(productList);
				myorderList.add(myorder);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn);
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
		}
		return myorderList;
	}
	@Override
	public boolean confirmReceiptByOrderNumber(String orderNumber) {
		String sql="update orders set order_state=? where order_number=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		boolean isUpdate=false;
		int result=0;
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, "待评价");
			prep.setString(2, orderNumber);
			result=prep.executeUpdate();
			if(result==1){
				isUpdate=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn);
			DbManager.closePreparedStatement(prep);
		}
		return isUpdate;
	}
	@Override
	public boolean updateMyOrderStateToDeleteByOrderNumber(String orderNumber) {
		String sql="update orders set order_state=? where order_number=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		boolean isUpdate=false;
		int result=0;
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, "用户删除");
			prep.setString(2, orderNumber);
			result=prep.executeUpdate();
			if(result==1){
				isUpdate=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn);
			DbManager.closePreparedStatement(prep);
		}
		return isUpdate;
	}
	@Override
	public boolean updateOrderAliPayNumberByOrderNumber(String orderNumber,String aliOrderNumber,String orderState) {
		String sql="update orders set aliOrder_number=?,order_state=? where order_number=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		int result=0;
		boolean isUpdate=false;
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1,aliOrderNumber);
			prep.setString(2, orderState);
			prep.setString(3, orderNumber);
			result=prep.executeUpdate();
			if(result==1){
				isUpdate=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn);
			DbManager.closePreparedStatement(prep);
		}
		return isUpdate;
	}
	@Override
	public boolean judgmentWhetherComment(int productId, int customerId, String orderState) {
		String sql="select * from orders where product_id=? and customer_id=? and order_state=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		boolean isExist=false;
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, productId);
			prep.setInt(2, customerId);
			prep.setString(3, orderState);
			result=prep.executeQuery();
			if(result.next()){
				isExist=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn);
			DbManager.closePreparedStatement(prep);
		}
		return isExist;
	}
	@Override
	public ArrayList<String> judgmentAattributeItem(String attributeValue, int productId) {
		String sql="SELECT attribute_value FROM c_attribute_value WHERE attributeValue_id IN(SELECT attributeValue_id FROM sku_attribute WHERE attributeValue_id IN(SELECT attributeValue_id FROM sku_attribute WHERE attribute_id NOT IN"
					+"("
					+"SELECT DISTINCT attribute_id FROM sku_attribute WHERE attributeValue_id IN"
					+"(SELECT attributeValue_id FROM c_attribute_value WHERE attribute_id IN"
					+"(SELECT attribute_id FROM category_attribute WHERE category_id IN"
					+"(SELECT category_id FROM productcategory WHERE product_id=? AND category_level=1)"
					+") "
					+"AND attribute_value=?)"
					+") AND product_id IN("
					+"SELECT product_id FROM sku WHERE group_id= (SELECT group_id FROM sku WHERE product_id=?)"
					+")"
					+")"
					+"AND attributeValue_id NOT IN(SELECT attributeValue_id FROM sku_attribute WHERE group_sign IN(SELECT group_sign FROM sku_attribute WHERE attributeValue_id="
					+"(SELECT attributeValue_id FROM c_attribute_value WHERE attribute_id IN(SELECT attribute_id FROM category_attribute WHERE category_id IN"
					+"(SELECT category_id FROM productcategory WHERE product_id=? AND category_level=1)) AND attribute_value=?)) AND product_id IN"
					+"(SELECT product_id FROM sku WHERE group_id=(SELECT group_id FROM sku WHERE product_id=?)) AND attributeValue_id!="
					+"(SELECT attributeValue_id FROM c_attribute_value WHERE attribute_id IN(SELECT attribute_id FROM category_attribute WHERE category_id IN"
					+"(SELECT category_id FROM productcategory WHERE product_id=? AND category_level=1)) AND attribute_value=?))"
					+"AND product_id IN("
					+"SELECT product_id FROM sku WHERE group_id= (SELECT group_id FROM sku WHERE product_id=?)))";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		ArrayList<String> attributeValueList=new ArrayList<String>();
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, productId);
			prep.setString(2, attributeValue);
			prep.setInt(3, productId);
			prep.setInt(4, productId);
			prep.setString(5, attributeValue);
			prep.setInt(6, productId);
			prep.setInt(7, productId);
			prep.setString(8, attributeValue);
			prep.setInt(9, productId);
			result=prep.executeQuery();
			while(result.next()){
				attributeValueList.add(result.getString("attribute_value"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn);
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
		}
		return attributeValueList;
	}
	@Override
	public boolean addReport(Report report) {
		String sql="insert into report(brand,category,sell_totalNumber,sell_totalPrice,createTime,updateTime) values(?,?,?,?,?,?)";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		int result=0;
		boolean isAdd=false;
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, report.getBrand());
			prep.setString(2, report.getCategory());
			prep.setInt(3, report.getSellTotalNumber());
			prep.setDouble(4, report.getSellTotalPrice());
			prep.setString(5, report.getCreateTime());
			prep.setString(6, report.getUpdateTime());
			result=prep.executeUpdate();
			if(result==1){
				isAdd=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn);
			DbManager.closePreparedStatement(prep);
			
		}
		return isAdd;
	}
	@Override
	public ArrayList<Integer> findProductIdByOrderNumber(String orderNumber) {
		String sql="select * from orders where order_number=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		ArrayList<Integer> productIdList=new ArrayList<Integer>();
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, orderNumber);
			result=prep.executeQuery();
			while(result.next()){
				productIdList.add(result.getInt("product_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn);
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
		}
		return productIdList;
	}
	@Override
	public ArrayList<Report> findReportByProductId(int proudctId) {
		String sql="select ";
		return null;
	}
	@Override
	public boolean updateMyOrderState(int productId, int customerId, String orderState,String orderNumber) {
		String sql="update orders set order_state=? where product_id=? and customer_id=? and order_state=? and order_number=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		int result=0;
		boolean isUpdate=false;
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, "交易成功");
			prep.setInt(2, productId);
			prep.setInt(3, customerId);
			prep.setString(4, orderState);
			prep.setString(5, orderNumber);
			result=prep.executeUpdate();
			if(result==1){
				isUpdate=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn);
			DbManager.closePreparedStatement(prep);
		}
		return isUpdate;
	}
	@Override
	public boolean addRefundReson(String orderNumber, String refundReson,String createTime,String updateTime) {
		String sql="insert into refund_appply(order_number,refund_reson,createTime,updateTime) values(?,?,?,?)";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		int result=0;
		boolean isAdd=false;
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, orderNumber);
			prep.setString(2, refundReson);
			prep.setString(3, createTime);
			prep.setString(4, updateTime);
			result=prep.executeUpdate();
			if(result==1){
				isAdd=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn);
			DbManager.closePreparedStatement(prep);
		}
		return isAdd;
	}
	@Override
	public boolean updateMyOrderRefundState(String orderState, String orderNumber) {
		String sql="update orders set order_state=? where order_number=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		int result=0;
		boolean isUpdate=false;
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, orderState);
			prep.setString(2, orderNumber);
			result=prep.executeUpdate();
			if(result==1){
				isUpdate=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn);
			DbManager.closePreparedStatement(prep);
		}
		return isUpdate;
	}
	@Override
	public boolean cancelOrderByOrderNumber(String orderNumber) {
		String sql="delete from orders where order_number=?";
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		int result=0;
		boolean isDelete=false;
		try {
			prep=conn.prepareStatement(sql);
			prep.setString(1, orderNumber);
			result=prep.executeUpdate();
			if(result==1){
				isDelete=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closeConnection(conn);
			DbManager.closePreparedStatement(prep);
		}
		return isDelete;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*@Override
	public ArrayList<Comment> findComment(int currentPage, int productId) {
		int limit=10;
		String sql="select * from sku_comment where product_id=? order by comment_id limit "+(currentPage - 1)*limit+","+limit;
		Connection conn=DbManager.getConnection();
		PreparedStatement prep=null;
		ResultSet result=null;
		ArrayList<Comment> commentList=new ArrayList<Comment>();
		try {
			prep=conn.prepareStatement(sql);
			prep.setInt(1, productId);
			result=prep.executeQuery();
			while(result.next()){
				Comment comment=new Comment();
				comment.setCommentId(result.getInt("comment_id"));
				comment.setCommentLevel(result.getString("comment_level"));
				comment.setCommentValue(result.getString("comment_value"));
				comment.setCreateTime(result.getString("createTime"));
				comment.setCustomerId(result.getInt("customer_id"));
				comment.setCustomerName(result.getString("customer_name"));
				comment.setProductId(result.getInt("product_id"));
				comment.setUpdateTime(result.getString("updateTime"));
				commentList.add(comment);
			}
			log.info(commentList+"list");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DbManager.closePreparedStatement(prep);
			DbManager.closeResultSet(result);
			DbManager.closeConnection(conn);
		}
		return commentList;
	}
	
*/


}
