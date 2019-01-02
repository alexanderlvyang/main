package servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.config.AlipayConfig;
import com.mysql.jdbc.StringUtils;

import dao.ProductOperation;
import daoimpl.ProductOperationImpl;
import daoimpl.StaffOperationImpl;
import javabean.Brand;
import javabean.Category;
import javabean.CategoryAttribute;
import javabean.CategoryAttributeValue;
import javabean.Comment;
import javabean.CommentImage;
import javabean.Customer;
import javabean.Sensitive;
import javabean.Image;
import javabean.MyOrder;
import javabean.OperationLog;
import javabean.Order;
import javabean.Product;
import javabean.ProductItem;
import javabean.ReceiverAddress;
import javabean.Refund;
import javabean.SkuAttribute;
import javabean.Staff;
import javabean.buyCar;
import javabean.productCategory;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.JudgmentIsEmpty;

public class ProductManager extends HttpServlet {
	Logger log = Logger.getLogger(this.getClass().getName());
	String staff_username = null;
	String staff_password = null;
	StaffOperationImpl soi = null;
	buyCar buycar = new buyCar();

	/**
	 * Constructor of the object.
	 */
	public ProductManager() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductOperation productOperation = new ProductOperationImpl();
		Staff staff = new Staff();
		String vrifyCode = "";
		HttpSession codesession = request.getSession();
		response.setCharacterEncoding("UTF-8");// 设置编码格式
		request.setCharacterEncoding("UTF-8");
		String url = request.getRequestURI();
		String action = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf(".")); // util.SubUril
		HttpSession session = request.getSession();
		response.setContentType("text/html charset=utf-8");
		ArrayList<Product> productItemList = new ArrayList<Product>();
		Cookie customercookie = null;
		Customer customer = null;
		log.info(action);
		if (action.equals("index")) {
			request.getRequestDispatcher("/WEB-INF/page/index.jsp").forward(request, response);
			return;
		}
		if (action.equals("login")) {
			staff_username = request.getParameter("username");// 获取前端传来的username值
			staff_password = request.getParameter("password");// 获取前端传来的password值
			soi = new StaffOperationImpl();
			staff = soi.LoginVrify(staff_username, staff_password);// 验证用户是否存在
			OperationLog operationLog = new OperationLog();
			operationLog.setOperationName("登录");
			operationLog.setOperationContent("登录系统");
			operationLog.setCreateTime(new Date().getTime());
			operationLog.setUpdateTime(new Date().getTime());

			if (staff != null && staff.getStaffUsername() != null && staff.getStaffPassword() != null) {
				session.setAttribute("staff", staff);
				response.sendRedirect("menu.do");
				productOperation.addStaffOperationLog(staff, operationLog);
				return;
			} else {
				response.sendRedirect("index.do");// 重定向到登陆失败界面
				return;

			}
		}
		/*
		 * if (!(staff !=
		 * null&&staff.getStaffUsername()!=null&&staff.getStaffPassword()!=null)
		 * ) { response.sendRedirect("index.do");// 重定向到登陆失败界面 return; }
		 */

		if (action.equals("menu")) {
			if (session.getAttribute("staff") != null) {
				request.getRequestDispatcher("/WEB-INF/page/menu.jsp").forward(request, response);
				return;
			} else {
				response.sendRedirect("login.do");
			}
		}
		if (action.equals("left")) {
			request.getRequestDispatcher("/WEB-INF/page/left.jsp").forward(request, response);
			return;
		}
		if (action.equals("right")) {
			request.getRequestDispatcher("/WEB-INF/page/right.jsp").forward(request, response);
			return;
		}
		if (action.equals("productManager")) {
			request.getRequestDispatcher("/WEB-INF/page/productManager.jsp").forward(request, response);
			return;
		}
		if (action.equals("buildProduct")) {
			ArrayList<String> categorynameList = productOperation.findCategoryName();
			ArrayList<String> brandNameList = productOperation.findBrandName();
			request.setAttribute("categorynameListSize", categorynameList.size());
			request.setAttribute("categoryName", categorynameList);
			request.setAttribute("brandName", brandNameList);
			String firstCategoryName = categorynameList.get(0);
			int categoryId = productOperation.findCategoryIdByName(firstCategoryName);
			log.info(categoryId + "");
			ArrayList<String> categoryAttributeList = productOperation.findCategoryAttributeNameById(categoryId);
			log.info(categoryAttributeList + "");
			Map<String, ArrayList<String>> categoryAttributeMap = new HashMap<String, ArrayList<String>>();
			for (int i = 0; i < categoryAttributeList.size(); i++) {
				int attributeId = productOperation.findAttributeIdByName(categoryAttributeList.get(i), categoryId);
				ArrayList<String> attributeValueList = productOperation
						.findCategoryAttributeValueStringById(attributeId);
				log.info(attributeValueList + "///////////////////////");
				categoryAttributeMap.put(categoryAttributeList.get(i), attributeValueList);
			}
			JSONArray json = JSONArray.fromObject(categoryAttributeMap);
			log.info(json + "jsonjsonnsjoso");
			// request.setAttribute("categoryAttributeList",
			// categoryAttributeList);
			request.setAttribute("categoryAttributeMap", categoryAttributeMap);
			// request.setAttribute("categoryAttributeListSize",
			// categoryAttributeList.size());
			request.setAttribute("brandNameListSize", brandNameList.size());
			request.getRequestDispatcher("/WEB-INF/page/buildProduct.jsp").forward(request, response);
			return;
		}
		if (action.equals("editer")) {
			ArrayList<String> categorynameList = productOperation.findCategoryName();
			ArrayList<String> brandNameList = productOperation.findBrandName();
			int productId = Integer.parseInt(request.getParameter("productId"));
			request.setAttribute("categorynameListSize", categorynameList.size());
			request.setAttribute("categoryName", categorynameList);
			request.setAttribute("brandName", brandNameList);
			request.setAttribute("brandNameListSize", brandNameList.size());
			request.setAttribute("productId", productId);
			request.getRequestDispatcher("/WEB-INF/page/editer.jsp").forward(request, response);
			return;
		}

		if (action.equals("find")) {
			if (session.getAttribute("staff") == null) {
				request.getRequestDispatcher("WEB-INF/page/adminLoginError.jsp").forward(request, response);
			} else {
				HashMap<String, String> findconditionmap = null;
				Map<String, String> conditionmap = null;
				String currentPage = request.getParameter("page");
				int currentpage = 1;
				if (currentPage != null) {
					// request.setAttribute("find", findconditionmap);
					findconditionmap = (HashMap<String, String>) request.getSession().getAttribute("find");
					try {
						currentpage = Integer.parseInt(currentPage);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					String product_name = request.getParameter("product_name");
					String product_lowPrice = request.getParameter("product_minprice");
					String product_highPrice = request.getParameter("product_maxprice");
					String product_category = request.getParameter("product_category");
					int categoryId = productOperation.findCategoryIdByName(product_category);
					findconditionmap = new HashMap<String, String>();
					conditionmap = new HashMap<String, String>();
					if (JudgmentIsEmpty.judgmentIsEmpty(product_name)) {
						findconditionmap.put("product_name", product_name);
					}
					if (JudgmentIsEmpty.judgmentIsEmpty(product_lowPrice)) {
						findconditionmap.put("lowPrice", product_lowPrice);
					}
					if (JudgmentIsEmpty.judgmentIsEmpty(product_highPrice)) {
						findconditionmap.put("highPrice", product_highPrice);
					}
					if (JudgmentIsEmpty.judgmentIsEmpty(product_category)) {
						findconditionmap.put("categoryId", categoryId + "");
					}

					if (JudgmentIsEmpty.judgmentIsEmpty(product_name)) {
						conditionmap.put("product_name", product_name);
					}
					if (JudgmentIsEmpty.judgmentIsEmpty(product_lowPrice)) {
						conditionmap.put("lowPrice", product_lowPrice);
					}
					if (JudgmentIsEmpty.judgmentIsEmpty(product_highPrice)) {
						conditionmap.put("highPrice", product_highPrice);
					}
					if (JudgmentIsEmpty.judgmentIsEmpty(product_category)) {
						conditionmap.put("product_category", product_category);
					}
				}
				ArrayList<Product> productList = productOperation.findProductListByCondition(findconditionmap,
						currentpage);// 查询所有商品

				log.info(productList + "list");
				/*
				 * JSONArray productJson = JSONArray.fromObject(productList);
				 * response.getWriter().print(productJson);
				 */
				log.info(productList + "?????????????????????????????????????????????");
				request.getSession().setAttribute("productList", productList);
				if (conditionmap != null) {
					request.getSession().setAttribute("find", conditionmap);
				}
				int productListSize = productList.size();
				int totalpages = productOperation.gettotalpages(findconditionmap);
				request.setAttribute("productListSize", productListSize);
				request.setAttribute("totalpages", totalpages);
				request.getRequestDispatcher("/WEB-INF/page/productManager.jsp").forward(request, response);
				return;
			}
		}
		if (action.equals("insert")) {
			String attrvalues = request.getParameter("attrvalues");
			String product_name = request.getParameter("product_name");
			int productId = productOperation.findProductIdByName(product_name);
			String product_state = request.getParameter("product_state");
			double transformprice = Double.valueOf(request.getParameter("product_realprice")).doubleValue();
			double transformoriginalprice = Double.valueOf(request.getParameter("product_originalprice")).doubleValue();
			String product_describe = request.getParameter("product_describe");
			String product_introduction = request.getParameter("product_introduction");
			int product_sort = Integer.parseInt(request.getParameter("product_sort"));
			String product_thumbnail = request.getParameter("product_thumbnail");
			int product_number = Integer.parseInt(request.getParameter("product_number"));
			String brand_name = request.getParameter("brand_name");
			String firstSelect = request.getParameter("firstselect");
			int categoryId = productOperation.findCategoryIdByName(firstSelect);
			String secondSelect = request.getParameter("secondselect");
			String thirdSelect = request.getParameter("thirdselect");
			int firstSelectId = productOperation.findCategoryIdByName(firstSelect);
			int secondSelectId = productOperation.findCategoryIdByName(secondSelect);
			int thirdSelectId = productOperation.findCategoryIdByName(thirdSelect);
			int brand_id = productOperation.findBrandIdByName(brand_name);
			NumberFormat numberFormat = NumberFormat.getNumberInstance();
			numberFormat.setMaximumFractionDigits(2);
			numberFormat.setGroupingUsed(false);
			double product_realprice = Double.valueOf(numberFormat.format(transformprice)).doubleValue();
			double product_originalprice = Double.valueOf(numberFormat.format(transformoriginalprice)).doubleValue();
			int result = 0;
			int groupId = productOperation.isExistProduct(product_name);
			if (groupId == 0) {
				groupId = productOperation.findMaxGroupId() + 1;
			}
			log.info(groupId + "ididididiiddi");
			if (product_name.matches("[\\u4e00-\\u9fa5]+")) {
				String pinyin = productOperation.transformStringToPinyin(product_name);
				result = productOperation.insertProductAttribute(product_name, groupId, product_introduction,
						product_originalprice, product_realprice, product_state, product_sort, product_describe,
						product_thumbnail, product_number, brand_id, pinyin);
			} else {
				result = productOperation.insertProductAttribute(product_name, groupId, product_introduction,
						product_originalprice, product_realprice, product_state, product_sort, product_describe,
						product_thumbnail, product_number, brand_id, null);
			}

			if (result == 1) {
				ArrayList<String> attributeList = productOperation.findCategoryAttributeNameById(categoryId);
				List<String> attributeValueList = Arrays.asList(attrvalues.split(","));
				int product_id = productOperation.findProductIdByName(product_name);
				/*
				 * String groupSign=productOperation.isExistProduct(product_id);
				 * if(groupSign.equals("")){ groupSign=product_id+"-1"; }else{
				 * int mantissa=Integer.parseInt(groupSign.substring(groupSign.
				 * indexOf("-"), groupSign.length()))+1;
				 * groupSign=product_id+"-"+mantissa; }
				 */
				for (int i = 0; i < attributeList.size(); i++) {
					int groupSign = product_id;
					int attributeId = productOperation.findAttributeIdByName(attributeList.get(i), categoryId);
					int attributeValueId = productOperation.findAttributeValueIdByattributeValue(attributeId,
							attributeValueList.get(i));
					productOperation.addSkuAttribute(product_id, attributeId, attributeValueId, groupSign);
				}
				boolean firstSelectAdd = productOperation.addProductCategory(product_id, firstSelectId, 1);
				boolean secondSelectAdd = productOperation.addProductCategory(product_id, secondSelectId, 2);
				boolean thirdSelectAdd = productOperation.addProductCategory(product_id, thirdSelectId, 3);
				if (firstSelectAdd && secondSelectAdd && thirdSelectAdd) {
					boolean isFirstInsert = productOperation.addBrandAndCategory(brand_id, firstSelectId);
					boolean isSecondInsert = productOperation.addBrandAndCategory(brand_id, secondSelectId);
					boolean isThirdInsert = productOperation.addBrandAndCategory(brand_id, thirdSelectId);
					if (isFirstInsert && isSecondInsert && isThirdInsert) {
						response.getWriter().print("添加成功");
						OperationLog operationLog = new OperationLog();
						operationLog.setOperationName("添加商品");
						operationLog.setOperationContent("添加id为" + product_id + "的商品");
						operationLog.setCreateTime(new Date().getTime());
						operationLog.setUpdateTime(new Date().getTime());
						staff = (Staff) session.getAttribute("staff");
						productOperation.addStaffOperationLog(staff, operationLog);
					}
				}
			} else {
				response.getWriter().print("添加失败");
			}
		}
		if (action.equals("changeState")) {
			int product_id = Integer.parseInt(request.getParameter("product_id"));
			String product_state = productOperation.findProductState(product_id);// 查询该商品的状态
			int result = productOperation.updateProductState(product_id);// 更新产品的状态
			OperationLog operationLog = new OperationLog();
			if (result == 1) {
				if (product_state.equals("上架")) {// 判断当前产品的状态
					response.getWriter().print("下架");
					operationLog.setOperationContent("改变id为" + product_id + "商品状态为下架");
				} else if (product_state.equals("下架")) {
					response.getWriter().print("上架");
					operationLog.setOperationContent("改变id为" + product_id + "商品状态为上架");
				}

				operationLog.setOperationName("改变商品状态");
				operationLog.setCreateTime(new Date().getTime());
				operationLog.setUpdateTime(new Date().getTime());
				staff = (Staff) session.getAttribute("staff");
				productOperation.addStaffOperationLog(staff, operationLog);

			}
		}
		if (action.equals("update")) {
			String product_name = request.getParameter("product_name");
			log.info(product_name + "name");
			String product_state = request.getParameter("product_state");
			double product_realprice = Double.valueOf(request.getParameter("product_realprice")).doubleValue();
			double product_originalprice = Double.valueOf(request.getParameter("product_originalprice")).doubleValue();
			String product_describe = request.getParameter("product_describe");
			String product_introduction = request.getParameter("product_introduction");
			int product_sort = Integer.parseInt(request.getParameter("product_sort"));
			String product_thumbnail = request.getParameter("product_thumbnail");
			int product_number = Integer.parseInt(request.getParameter("product_number"));
			String brand_name = request.getParameter("brand_name");
			String firstSelect = request.getParameter("firstselect");
			String secondSelect = request.getParameter("secondselect");
			String thirdSelect = request.getParameter("thirdselect");
			int firstSelectId = productOperation.findCategoryIdByName(firstSelect);
			int secondSelectId = productOperation.findCategoryIdByName(secondSelect);
			int thirdSelectId = productOperation.findCategoryIdByName(thirdSelect);
			int brand_id = productOperation.findBrandIdByName(brand_name);
			int product_id = Integer.parseInt(request.getParameter("productId"));
			int result = 0;
			if (product_name.matches("[\\u4e00-\\u9fa5]+")) {
				String pinyin = productOperation.transformStringToPinyin(product_name);
				result = productOperation.updateProductAttribute(product_name, product_introduction,
						product_originalprice, product_realprice, product_state, product_sort, product_describe,
						product_thumbnail, product_id, product_number, brand_id, pinyin);
			} else {
				result = productOperation.updateProductAttribute(product_name, product_introduction,
						product_originalprice, product_realprice, product_state, product_sort, product_describe,
						product_thumbnail, product_id, product_number, brand_id, null);
			}

			if (result == 1) {
				boolean firstSelectAdd = productOperation.updateProductCategory(product_id, firstSelectId, 1);
				boolean secondSelectAdd = productOperation.updateProductCategory(product_id, secondSelectId, 2);
				boolean thirdSelectAdd = productOperation.updateProductCategory(product_id, thirdSelectId, 3);
				if (firstSelectAdd && secondSelectAdd && thirdSelectAdd) {
					response.getWriter().print("更新成功");
					OperationLog operationLog = new OperationLog();
					operationLog.setOperationName("更新商品");
					operationLog.setOperationContent("更新id为" + product_id + "的商品");
					operationLog.setCreateTime(new Date().getTime());
					operationLog.setUpdateTime(new Date().getTime());
					staff = (Staff) session.getAttribute("staff");
					productOperation.addStaffOperationLog(staff, operationLog);

				}
			} else {
				response.getWriter().print("更新失败");
			}

		}
		if (action.equals("findById")) {
			int product_id = Integer.parseInt(request.getParameter("product_id"));
			log.info(product_id + "4566778899");
			ArrayList<Product> productList = productOperation.getProductAttributeListById(product_id);
			log.info(productList + "465687989");
			JSONArray productJson = JSONArray.fromObject(productList);
			response.getWriter().print(productJson);
		}
		if (action.equals("logout")) {
			OperationLog operationLog = new OperationLog();
			operationLog.setOperationName("登出");
			operationLog.setOperationContent("退出系统");
			operationLog.setCreateTime(new Date().getTime());
			operationLog.setUpdateTime(new Date().getTime());
			staff = (Staff) session.getAttribute("staff");
			productOperation.addStaffOperationLog(staff, operationLog);
			// staff = null;
			session.removeAttribute("staff");
			response.sendRedirect("index.do");
			return;
		}

		if (action.equals("customerLogin")) {
			request.getRequestDispatcher("/WEB-INF/page/customerLogin.jsp").forward(request, response);
			return;
		}
		if (action.equals("customerLoginOperation")) {
			String customer_username = request.getParameter("username");// 获取前端传来的username值
			String customer_password = request.getParameter("password");// 获取前端传来的password值
			customer = productOperation.LoginVrify(customer_username, customer_password);// 验证用户是否存在
			if (customer != null && customer.getCustomerUsername() != null && customer.getCustomerPassword() != null) {
				session.setAttribute("customerID", customer.getCustomerId());
				customercookie = new Cookie("customername", customer.getCustomerName());
				customercookie.setMaxAge(60 * 60 * 24);
				response.addCookie(customercookie);
				response.getWriter().print("登陆成功");
				response.sendRedirect("homepage.do");
				return;
			} else {
				response.sendRedirect("customerLogin.do");
				response.getWriter().print("登陆失败");// 重定向到登陆失败界面
				return;

			}
		}

		if (action.equals("homepage")) {
			String customername = "";
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("customername")) {
					customername = cookie.getValue();
				}
			}
			Map<Integer, String> categorynamemap = productOperation.findProductCategory();
			HttpSession categorynamesession = request.getSession();
			categorynamesession.setAttribute("categorynamemap", categorynamemap);
			request.setAttribute("customername", customername);
			request.getRequestDispatcher("/WEB-INF/page/homepage.jsp").forward(request, response);
			return;
		}
		if (action.equals("customerLogout")) {
			customer = null;
			session.removeAttribute("customerID");
			response.getWriter().print("退出成功");
		}
		if (action.equals("car")) {

			if (session.getAttribute("customerID") != null) {
				int customerid = (int) session.getAttribute("customerID");
				buyCar buycar = new buyCar();
				buycar = productOperation.findBuyCarByCustomerId(customerid, buycar);
				Map<String, ProductItem> buycarmap = buycar.getMap();
				Iterator iterator = buycarmap.keySet().iterator();
				String key = "";
				ProductItem productitem = null;
				ArrayList<ProductItem> productitemList = new ArrayList<ProductItem>();
				while (iterator.hasNext()) {
					key = (String) iterator.next();
					productitem = (ProductItem) buycarmap.get(key);
					productitemList.add(productitem);
				}
				int poductitemListSize = productitemList.size();
				session.setAttribute("productitemList", productitemList);
				request.setAttribute("productitemList", productitemList);
				request.setAttribute("productitemListSize", poductitemListSize);
				request.getRequestDispatcher("/WEB-INF/page/buyCar.jsp").forward(request, response);
				return;
			} else {
				Cookie[] cookies = request.getCookies();
				if (cookies == null) {
					log.info(",,,,");
					// response.getWriter().write("购物车为空");
				} else {
					boolean isExist = false;
					String json = "";
					for (Cookie cookie : cookies) {
						log.info(cookie.getName());
						if (cookie.getName().equals("buycar")) {
							isExist = true;
							json = URLDecoder.decode(cookie.getValue());
						}
					}
					if (isExist) {
						Map<String, ProductItem> buycarmap = (Map<String, ProductItem>) JSON.parseObject(json,
								new TypeReference<Map<String, ProductItem>>() {
								});
						;
						Iterator<String> iterator = buycarmap.keySet().iterator();
						String key = "";
						ProductItem poductitem = null;
						ArrayList<ProductItem> poductitemList = new ArrayList<ProductItem>();
						while (iterator.hasNext()) {
							key = (String) iterator.next();
							poductitem = (ProductItem) buycarmap.get(key);
							poductitemList.add(poductitem);
						}
						int poductitemListSize = poductitemList.size();
						request.setAttribute("productitemlist", poductitemList);
						request.setAttribute("productitemlistsize", poductitemListSize);
						request.getRequestDispatcher("/WEB-INF/page/buyCar.jsp").forward(request, response);
					} else {
						request.setAttribute("productitemlist", null);
						request.setAttribute("productitemlistsize", 0);
						request.getRequestDispatcher("/WEB-INF/page/buyCar.jsp").forward(request, response);
					}
				}
			}
		}
		/*
		 * if(action.equals("find")){ ProductOperation productOperation = new
		 * ProductOperationImpl();
		 * productItemList=productOperation.findProduct(); int
		 * listsize=productItemList.size(); request.setAttribute("listsize",
		 * listsize); request.getSession().setAttribute("productItemList",
		 * productItemList);
		 * request.getRequestDispatcher("/WEB-INF/page/index.jsp").forward(
		 * request, response);
		 * 
		 * }
		 */
		if (action.equals("addBuycar")) {
			if (session.getAttribute("customerID") == null) {
				Cookie[] cookies = request.getCookies();
				boolean isexist = false;
				if (cookies == null) {
					buycar = new buyCar();
				} else {
					for (Cookie cookie : cookies) {
						if (cookie.getName().equals("buycar")) {
							log.info("true");
							isexist = true;
						}
					}
					if (!isexist) {
						log.info("false");
						buycar = new buyCar();
					}
				}
				int product_id = 0;
				int product_number = 1;
				try {
					product_id = Integer.parseInt(request.getParameter("product_id").trim());
					product_number = Integer.parseInt(request.getParameter("product_number"));
				} catch (NumberFormatException e) {
					product_number = 1;
				}
				Product product = new Product();
				product = productOperation.findProductByID(product_id);
				ProductItem productitem = new ProductItem(product, product_number);
				buycar.addProductItem(productitem, product_number);
				JSONObject buycarmapjson = JSONObject.fromObject(buycar.getMap());
				String buycarStr = URLEncoder.encode(buycarmapjson.toString());
				Cookie cookie = new Cookie("buycar", buycarStr);
				cookie.setMaxAge(60 * 2);
				response.addCookie(cookie);
				response.getWriter().print("添加成功");
			} else {
				log.info("cookienothing");
				int customerid = (int) session.getAttribute("customerID");
				int product_id = 0;
				int product_number = 1;
				try {
					product_id = Integer.parseInt(request.getParameter("product_id").trim());
					product_number = Integer.parseInt(request.getParameter("product_number"));
				} catch (NumberFormatException e) {
					product_number = 1;
				}

				Product product = new Product();
				product = productOperation.findProductByID(product_id);
				boolean isSuccess = productOperation.addCarProductToDatabase(product, customerid, product_number);
				if (isSuccess) {
					response.getWriter().print("添加成功");
				} else {
					response.getWriter().print("添加失败");
				}
			}

		}
		if (action.equals("delete")) {
			int product_id = Integer.parseInt(request.getParameter("productid"));
			if (session.getAttribute("customerID") != null) {
				boolean isDelete = productOperation.deleteProductFromDatabase(product_id);
				if (isDelete) {
					response.getWriter().print("删除成功");

				} else {
					response.getWriter().print("删除失败");
				}
			} else {
				buycar = productOperation.deleteProductByID(product_id, buycar);
				JSONObject buycarmapjson = JSONObject.fromObject(buycar.getMap());
				String buycarStr = URLEncoder.encode(buycarmapjson.toString());
				Cookie buycarcookie = new Cookie("buycar", buycarStr);
				buycarcookie.setMaxAge(60);
				response.addCookie(buycarcookie);
			}
		}
		if (action.equals("searchResult")) {
			String product_name = request.getParameter("product_name");
			ArrayList<Product> productList = new ArrayList<Product>();
			if (product_name != null) {
				if (product_name.matches("[\\u4e00-\\u9fa5]+")) {
					log.info(">>>");
					String pinyin = productOperation.transformStringToPinyin(product_name);
					boolean isExist = productOperation.judgmentWhetherExistCategoryName(product_name);
					if (isExist) {
						productList = productOperation.findProductByCategoryName(product_name);
					} else {
						productList = productOperation.findProductByName(product_name, pinyin, 0);
					}
				} else {
					productList = productOperation.findProductByName(product_name, null, 0);
				}
				if (productList.size() > 0) {
					ArrayList<Brand> brandList = productOperation.findAllBrand();
					int categoryid;
					try {
						categoryid = Integer.parseInt(request.getParameter("categoryid"));
					} catch (NumberFormatException e) {
						categoryid = 0;
					}
					log.info(categoryid + "/" + product_name + "/" + brandList + "/" + productList);
					request.setAttribute("categoryId", categoryid);
					request.setAttribute("productName", product_name);
					request.setAttribute("brandList", brandList);
					request.setAttribute("brandListSize", brandList.size());
					request.setAttribute("productList", productList);
					request.setAttribute("productListSize", productList.size());
					request.getRequestDispatcher("/WEB-INF/page/searchResult.jsp").forward(request, response);
				} else {
					response.sendRedirect("homepage.do");
				}
			} else {
				int categoryid = Integer.parseInt(request.getParameter("categoryid"));
				productList = productOperation.findProductByCategoryid(categoryid);
				if (productList.size() > 0) {
					ArrayList<Brand> brandList = productOperation.findAllBrand();
					request.setAttribute("productName", product_name);
					request.setAttribute("brandList", brandList);
					request.setAttribute("brandListSize", brandList.size());
					request.setAttribute("productList", productList);
					request.setAttribute("categoryId", categoryid);
					request.setAttribute("productListSize", productList.size());
					request.getRequestDispatcher("WEB-INF/page/searchResult.jsp").forward(request, response);
				} else {
					response.sendRedirect("homepage.do");
				}
			}

		}
		/*
		 * if(action.equals("searchcategoryresult")){ ProductOperation
		 * productOperation = new ProductOperationImpl(); int
		 * categoryid=Integer.parseInt(request.getParameter("categoryid"));
		 * ArrayList<Product>
		 * productlist=productOperation.findProductByCategoryid(categoryid);
		 * if(productlist.size()>0){ request.setAttribute("productlist",
		 * productlist); request.setAttribute("productlistsize",
		 * productlist.size()); response.sendRedirect("search.do"); }else{
		 * response.sendRedirect("homepage.do"); } }
		 */
		if (action.equals("showCategory")) {
			int categoryid = Integer.parseInt(request.getParameter("categoryid"));
			ArrayList<productCategory> categorylist = productOperation.findCategoryById(categoryid);
			JSONArray categoryjson = JSONArray.fromObject(categorylist);
			log.info(categorylist + "");
			response.getWriter().print(categoryjson);
			/*
			 * HttpSession productcategorysession=request.getSession();
			 * productcategorysession.setAttribute("productcategorymap",
			 * productcategorymap);
			 */
		}

		if (action.equals("getCaptcha")) {
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			int width = 80;
			int height = 40;
			Random random = new Random();
			BufferedImage image = new BufferedImage(width, height, 1);
			Graphics graphics = image.getGraphics();
			graphics.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
			graphics.setFont(new Font("Times New Roman", 0, 28));
			graphics.fillRect(0, 0, width, height);
			for (int i = 0; i < 60; i++) {
				graphics.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
				int x = random.nextInt(width);
				int y = random.nextInt(height);
				int x1 = random.nextInt(10);
				int y1 = random.nextInt(10);
				graphics.drawLine(x, y, x + x1, y + y1);
			}

			for (int i = 0; i < 4; i++) {
				String rand = String.valueOf(random.nextInt(10));
				vrifyCode = vrifyCode + rand;
				graphics.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
				graphics.drawString(rand, 13 * i + 6, 28);
			}
			session.setAttribute("captcha", vrifyCode);
			graphics.dispose();
			codesession.setAttribute("vrifyCode", vrifyCode);
			ServletOutputStream out = null;
			out = response.getOutputStream();
			ImageIO.write(image, "jpg", out);

			// ImageIO.write(image, "JPG", new
			// File("E:/myeclipse-workspace/market/WebRoot/image/verifycode.jpg"));

			out.flush();
			out.close();
		}
		if (action.equals("register")) {
			request.getRequestDispatcher("WEB-INF/page/register.jsp").forward(request, response);
		}
		if (action.equals("getCode")) {
			String captcha = request.getParameter("captcha");
			String phoneNumber = request.getParameter("phonenumber");
			String captchacode = (String) codesession.getAttribute("vrifyCode");
			String phonecaptcha = "";
			Random random = new Random();
			for (int i = 0; i < 5; i++) {
				phonecaptcha += random.nextInt(10);
			}
			log.info(phonecaptcha + "");
			session.setAttribute("phonecaptcha", phonecaptcha);
			session.setAttribute("phoneNumber", phoneNumber);
			/*
			 * if(!(captcha.equals(captchacode))){
			 * response.getWriter().print("验证码错误"); }else{ ProductOperation
			 * productOperation = new ProductOperationImpl(); String
			 * message=productOperation.getMessage(phonenumber, phonecaptcha);
			 * if(message.equals("提交成功!")){ response.getWriter().print("已发送"); }
			 */
		}
		if (action.equals("judgmentCaptcha")) {
			String captcha = (String) session.getAttribute("phonecaptcha");
			log.info(captcha + "yzm");
			String phoneCaptcha = request.getParameter("phoneCaptcha");
			if (captcha.equals(phoneCaptcha)) {
				response.getWriter().print("success");
			} else {
				response.getWriter().print("faild");
			}
		}
		if (action.equals("addNewCustomer")) {
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			String phoneNumber = (String) session.getAttribute("phoneNumber");
			Customer addCustomer = new Customer();
			addCustomer.setCustomerUsername(userName).setCustomerPassword(password).setCustomerPhoneNumber(phoneNumber);
			session.setAttribute("userName", userName);
			boolean isSuccess = productOperation.addNewCustomer(addCustomer);
			if (isSuccess) {
				request.getRequestDispatcher("/WEB-INF/page/registerSuccess.jsp").forward(request, response);
			}

		}
		if (action.equals("judgmentPhoneNumber")) {
			String customer_phoneNumber = request.getParameter("phoneNumber");
			boolean isExist = productOperation.findCustomerPhoneNumber(customer_phoneNumber);
			if (isExist) {
				response.getWriter().print("存在");
			}
		}

		if (action.equals("deleteCategory")) {
			int categoryId = Integer.parseInt(request.getParameter("categoryId"));
			boolean isdelete = productOperation.deleteCategoryById(categoryId);
			if (isdelete == true) {
				response.getWriter().print("删除成功");
				OperationLog operationLog = new OperationLog();
				operationLog.setOperationName("删除");
				operationLog.setOperationContent("删除id为" + categoryId + "的分类");
				operationLog.setCreateTime(new Date().getTime());
				operationLog.setUpdateTime(new Date().getTime());
				staff = (Staff) session.getAttribute("staff");
				productOperation.addStaffOperationLog(staff, operationLog);
			} else {
				response.getWriter().print("删除失败");
			}

		}

		if (action.equals("categoryManager")) {
			if (session.getAttribute("staff") == null) {
				request.getRequestDispatcher("WEB-INF/page/adminLoginError.jsp").forward(request, response);
			} else {
				ArrayList<Category> categoryList = new ArrayList<Category>();
				categoryList = productOperation.findCategory();

				request.setAttribute("categoryListSize", categoryList.size());
				request.setAttribute("categoryList", categoryList);
				request.getRequestDispatcher("/WEB-INF/page/categoryManager.jsp").forward(request, response);
				return;
			}
		}
		if (action.equals("addCategory")) {
			String category_name = request.getParameter("category_name");
			boolean issuccess = productOperation.addNewCategory(category_name);
			if (issuccess == true) {
				int categoryId = productOperation.findCategoryIdByName(category_name);
				OperationLog operationLog = new OperationLog();
				operationLog.setOperationName("添加");
				operationLog.setOperationContent("添加名称为" + category_name + "的分类");
				operationLog.setCreateTime(new Date().getTime());
				operationLog.setUpdateTime(new Date().getTime());
				staff = (Staff) session.getAttribute("staff");
				productOperation.addStaffOperationLog(staff, operationLog);
				response.sendRedirect("categoryManager.do");
			} else {
				response.sendRedirect("addNewCategory.do");
			}
		}
		if (action.equals("addNewCategory")) {
			request.getRequestDispatcher("/WEB-INF/page/addNewCategory.jsp").forward(request, response);
			return;
		}
		if (action.equals("addSecondChildCategory")) {
			int categoryFatherId = Integer.parseInt(request.getParameter("categoryFatherId"));
			request.setAttribute("categoryFatherId", categoryFatherId);
			request.getRequestDispatcher("/WEB-INF/page/addSecondChildCategory.jsp").forward(request, response);
		}
		if (action.equals("addSecondChildCategoryOperation")) {
			String childCategory_name = request.getParameter("childCategory_name");
			int categoryFatherId = Integer.parseInt(request.getParameter("categoryFatherId"));
			boolean issuccess = productOperation.addChildCategory(childCategory_name, categoryFatherId, 2);
			if (issuccess == true) {
				OperationLog operationLog = new OperationLog();
				operationLog.setOperationName("添加");
				operationLog.setOperationContent("添加名称为" + childCategory_name + "的分类");
				operationLog.setCreateTime(new Date().getTime());
				operationLog.setUpdateTime(new Date().getTime());
				staff = (Staff) session.getAttribute("staff");
				staff = (Staff) session.getAttribute("staff");
				productOperation.addStaffOperationLog(staff, operationLog);
				response.sendRedirect("secondChildCategoryManager.do?categoryid=" + categoryFatherId);
			} else {
				response.sendRedirect("addNewCategory.do");
			}
		}
		if (action.equals("addThirdCategory")) {
			int categoryFatherId = Integer.parseInt(request.getParameter("categoryFatherId"));
			request.setAttribute("categoryFatherId", categoryFatherId);
			request.getRequestDispatcher("/WEB-INF/page/addThirdChildCategory.jsp").forward(request, response);

		}
		if (action.equals("addThirdChildCategoryOperation")) {
			String childCategory_name = request.getParameter("childCategory_name");
			int categoryFatherId = Integer.parseInt(request.getParameter("categoryFatherId"));
			boolean issuccess = productOperation.addChildCategory(childCategory_name, categoryFatherId, 3);
			if (issuccess == true) {
				OperationLog operationLog = new OperationLog();
				operationLog.setOperationName("添加");
				operationLog.setOperationContent("添加名称为" + childCategory_name + "的分类");
				operationLog.setCreateTime(new Date().getTime());
				operationLog.setUpdateTime(new Date().getTime());
				staff = (Staff) session.getAttribute("staff");
				productOperation.addStaffOperationLog(staff, operationLog);
				response.sendRedirect("thirdChildCategoryManager.do?categoryid=" + categoryFatherId);
			} else {
				response.sendRedirect("addNewCategory.do");
			}
		}
		if (action.equals("secondChildCategoryManager")) {
			ArrayList<Category> categoryList = new ArrayList<Category>();
			int category_id = Integer.parseInt(request.getParameter("categoryid"));
			categoryList = productOperation.findChildCategoryById(category_id);
			request.setAttribute("categoryListSize", categoryList.size());
			request.setAttribute("categoryList", categoryList);
			request.setAttribute("fatherid", category_id);
			request.getRequestDispatcher("/WEB-INF/page/secondChildCategoryManager.jsp").forward(request, response);
			return;
		}
		if (action.equals("thirdChildCategoryManager")) {
			ArrayList<Category> categoryList = new ArrayList<Category>();
			int category_id = Integer.parseInt(request.getParameter("categoryid"));
			int grandFatherId = 0;
			try {
				grandFatherId = Integer.parseInt(request.getParameter("fatherId"));
			} catch (NumberFormatException e) {
				grandFatherId = 0;
			}
			categoryList = productOperation.findChildCategoryById(category_id);
			request.setAttribute("categoryListSize", categoryList.size());
			request.setAttribute("categoryList", categoryList);
			request.setAttribute("fatherid", category_id);
			request.setAttribute("grandFatherId", grandFatherId);
			request.getRequestDispatcher("/WEB-INF/page/thirdChildCategoryManager.jsp").forward(request, response);
			return;
		}
		if (action.equals("findChildCategory")) {
			String category_name = request.getParameter("categoryName");
			ArrayList<Category> categoryList = productOperation.findChildCategoryByName(category_name);
			JSONArray categoryJson = JSONArray.fromObject(categoryList);
			response.getWriter().print(categoryJson);
		}
		if (action.equals("findSecondSelect")) {
			String firstCategory = request.getParameter("firstCategory");
			ArrayList<Category> categoryList = productOperation.findInitialSecondChildCategory(firstCategory);
			JSONArray categoryJson = JSONArray.fromObject(categoryList);
			response.getWriter().print(categoryJson);
		}
		if (action.equals("findThirdSelect")) {
			String secondCategory = request.getParameter("secondCategory");
			log.info(secondCategory + "second");
			ArrayList<Category> categoryList = productOperation.findInitialThirdChildCategory(secondCategory);
			JSONArray categoryJson = JSONArray.fromObject(categoryList);
			response.getWriter().print(categoryJson);

		}
		if (action.equals("brandManager")) {
			if (session.getAttribute("staff") == null) {
				request.getRequestDispatcher("WEB-INF/page/adminLoginError.jsp").forward(request, response);
			} else {
				ArrayList<Brand> brandList = productOperation.findAllBrand();
				request.setAttribute("brandListSize", brandList.size());
				request.setAttribute("brandList", brandList);
				request.getRequestDispatcher("/WEB-INF/page/brandManager.jsp").forward(request, response);
			}
		}

		if (action.equals("addNewBrand")) {
			request.getRequestDispatcher("/WEB-INF/page/addNewBrand.jsp").forward(request, response);
		}
		if (action.equals("addNewBrandOperation")) {
			String brandName = request.getParameter("brandName");
			String brandEnglishName = request.getParameter("brandEnglishName");
			boolean isAdd = productOperation.addBrand(brandName, brandEnglishName);
			log.info(isAdd + "");
			if (isAdd) {
				OperationLog operationLog = new OperationLog();
				operationLog.setOperationName("添加");
				operationLog.setOperationContent("添加名称为" + brandName + "的品牌");
				operationLog.setCreateTime(new Date().getTime());
				operationLog.setUpdateTime(new Date().getTime());
				staff = (Staff) session.getAttribute("staff");
				productOperation.addStaffOperationLog(staff, operationLog);
				response.sendRedirect("brandManager.do");
			}
		}
		if (action.equals("deleteBrand")) {
			int brandId = Integer.parseInt(request.getParameter("brandId"));
			boolean isDelete = productOperation.deleteBrandById(brandId);
			if (isDelete) {
				OperationLog operationLog = new OperationLog();
				operationLog.setOperationName("删除");
				operationLog.setOperationContent("删除id为" + brandId + "的品牌");
				operationLog.setCreateTime(new Date().getTime());
				operationLog.setUpdateTime(new Date().getTime());
				staff = (Staff) session.getAttribute("staff");
				productOperation.addStaffOperationLog(staff, operationLog);
				response.getWriter().print("delete successful");
			}
		}
		if (action.equals("findProductByBrand")) {
			int categoryId = Integer.parseInt(request.getParameter("categoryId"));
			int brandId = Integer.parseInt(request.getParameter("brandId"));
			ArrayList<Product> productList = new ArrayList<Product>();

			ArrayList<Brand> brandList = productOperation.findAllBrand();
			String productName = request.getParameter("productName");
			log.info(categoryId + "/" + productName + "/" + brandList + "/" + productList + "++++");
			if (!(productName.equals("null"))) {
				String pinyin = productOperation.transformStringToPinyin(productName);
				boolean isExist = productOperation.judgmentWhetherExistCategoryName(productName);
				log.info(isExist + "");
				if (isExist) {
					productList = productOperation.findProductByCategoryNameAndBrandId(productName, brandId);
				} else {
					productList = productOperation.findProductByName(productName, pinyin, 0);
				}
			} else {
				log.info(">>>>");
				productList = productOperation.findProductByBrandIdAndCategoryId(brandId, categoryId);
			}
			request.setAttribute("brandList", brandList);
			request.setAttribute("brandListSize", brandList.size());
			request.setAttribute("productList", productList);
			request.setAttribute("categoryId", categoryId);
			request.setAttribute("productListSize", productList.size());
			request.setAttribute("productName", productName);
			request.getRequestDispatcher("WEB-INF/page/searchResult.jsp").forward(request, response);
		}
		if (action.equals("productAttributeChoose")) {
			int productId = Integer.parseInt(request.getParameter("product_id"));
			int categoryId = productOperation.findCategoryIdByProductId(productId);
			String attributeValue = request.getParameter("attributeValue");
			List<String> attrValueList = Arrays.asList(attributeValue.split(","));
			int groupId = productOperation.findGroupIdByProductId(productId);
			int oneattributeValueId = productOperation.findAttributeValueIdByCategoryIdAndAttributeValue(categoryId,
					attrValueList.get(0));
			int anotherattributeValueId = productOperation.findAttributeValueIdByCategoryIdAndAttributeValue(categoryId,
					attrValueList.get(1));
			productId = productOperation.findProductIdByAttributeId(oneattributeValueId, anotherattributeValueId,
					groupId);
			response.getWriter().print(productId);
		}
		if (action.equals("productDetail")) {
			int productId = Integer.parseInt(request.getParameter("product_id"));
			String orderNumber=request.getParameter("orderNumber");
			if(orderNumber!=null){
				session.setAttribute("orderNumber", orderNumber);
			}
			int categoryId = productOperation.findCategoryIdByProductId(productId);
			try {
				String attributeValue = request.getParameter("attributeValue");
				List<String> attrValueList = Arrays.asList(attributeValue.split(","));
				int groupId = productOperation.findGroupIdByProductId(productId);
				int oneattributeValueId = productOperation.findAttributeValueIdByCategoryIdAndAttributeValue(categoryId,
						attrValueList.get(1));
				int anotherattributeValueId = productOperation
						.findAttributeValueIdByCategoryIdAndAttributeValue(categoryId, attrValueList.get(2));
				productId = productOperation.findProductIdByAttributeId(oneattributeValueId, anotherattributeValueId,
						groupId);
			} catch (Exception e) {

			}
			ArrayList<String> productSpecificationList = new ArrayList<String>();
			productSpecificationList = productOperation.findProductSpecificationByProductId(productId);
			request.setAttribute("productSpecificationList", productSpecificationList);
			request.setAttribute("productSpecificationListSize", productSpecificationList.size());
			ArrayList<String> catalogNameList = productOperation.findCatalogNameByProductId(productId);
			ArrayList<String> categoryNameList = productOperation.findCategoryNameByProductId(productId);
			ArrayList<String> showImageUrlList = productOperation.findShowImageUrlByProductIdAndImageCategory(productId,
					"展示图片");
			ArrayList<String> introductionImageUrlList = productOperation
					.findShowImageUrlByProductIdAndImageCategory(productId, "介绍图片");
			ArrayList<String> attributeList = productOperation.findCategoryAttributeByProductId(productId);
			/*
			 * //Map<String, List<String>> productAttributeMap =
			 * productOperation.findProductAttributeListByProductIdAndCategoryId
			 * (categoryId, productId);
			 * request.setAttribute("productAttributeMap", productAttributeMap);
			 * log.info(productAttributeMap + "map");
			 */
			Map<String, ArrayList<String>> skuattributeMap = new HashMap<String, ArrayList<String>>();
			ArrayList<String> attributeValueList = new ArrayList<String>();
			for (int i = 0; i < attributeList.size(); i++) {
				log.info(productId + "ididididididid");
				attributeValueList = productOperation.findAttributeValueByProductId(productId, attributeList.get(i));
				log.info(attributeList + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
				skuattributeMap.put(attributeList.get(i), attributeValueList);
			}
			log.info(skuattributeMap + "mapmapmapmap");
			request.setAttribute("skuattributeMap", skuattributeMap);
			int currentPage = 1;
			ArrayList<Comment> commentList = productOperation.findCommentByProductId(productId, currentPage);
			/*
			 * ArrayList<ArrayList<CommentImage>> commentImageList=new
			 * ArrayList<ArrayList<CommentImage>>(); for(int
			 * i=0;i<commentList.size();i++){ int
			 * commentId=commentList.get(i).getCommentId();
			 * ArrayList<CommentImage>
			 * ImageList=productOperation.findImageSrcByComentId(commentId);
			 * log.info(ImageList+"list"); commentImageList.add(ImageList); }
			 * log.info(commentImageList+"commentImageList");
			 * request.setAttribute("commentImageList", commentImageList);
			 */
			Map<String, Integer> commentLevelMap = new HashMap<String, Integer>();
			commentLevelMap = productOperation.findCommentCountByCommentLevel("好评", commentLevelMap, productId);
			commentLevelMap = productOperation.findCommentCountByCommentLevel("中评", commentLevelMap, productId);
			commentLevelMap = productOperation.findCommentCountByCommentLevel("差评", commentLevelMap, productId);
			int totalPage = productOperation.getCommentTotalPages(productId);
			request.setAttribute("totalPage", totalPage);
			log.info(commentLevelMap + "");
			request.setAttribute("commentLevelMap", commentLevelMap);
			log.info(commentList + "////////////");
			request.setAttribute("commentList", commentList);
			request.setAttribute("commentListSize", commentList.size());
			request.setAttribute("attributeList", attributeList);
			request.setAttribute("attributeListSize", attributeList.size());
			request.setAttribute("introductionImageUrlList", introductionImageUrlList);
			request.setAttribute("introductionImageUrlListSize", introductionImageUrlList.size());
			request.setAttribute("showImageUrlList", showImageUrlList);
			request.setAttribute("showImageUrlListSize", showImageUrlList.size());
			request.setAttribute("categoryNameList", categoryNameList);
			request.setAttribute("categoryNameListSize", categoryNameList.size());
			request.setAttribute("catalogNameList", catalogNameList);
			request.setAttribute("catalogNameListSize", catalogNameList.size());
			Product product = productOperation.findProductByID(productId);
			String productDecribe = product.getProduct_describe();
			List<String> describeList = new ArrayList<String>();
			if (productDecribe.contains(";")) {
				describeList = Arrays.asList(productDecribe.split(";"));
			}
			if (productDecribe.contains("；")) {
				describeList = Arrays.asList(productDecribe.split("；"));
			}

			request.setAttribute("describeList", describeList);
			request.setAttribute("describeListSize", describeList.size());
			request.setAttribute("product", product);
			log.info(product + "clclclcl");
			request.getRequestDispatcher("/WEB-INF/page/productDetail.jsp").forward(request, response);
		}
		if (action.equals("imageManagerPage")) {
			if (session.getAttribute("staff") == null) {
				request.getRequestDispatcher("WEB-INF/page/adminLoginError.jsp").forward(request, response);
			} else {
				int totalPages = productOperation.getTotalPages();
				request.setAttribute("totalPages", totalPages);
				ArrayList<Image> imageList = new ArrayList<Image>();
				int currentPage = 0;
				int productId = 0;
				try {
					currentPage = Integer.parseInt(request.getParameter("currentPage"));
				} catch (NumberFormatException e1) {
					currentPage = 1;
				}
				try {
					productId = Integer.parseInt(request.getParameter("productId"));
				} catch (NumberFormatException e) {
					imageList = productOperation.findAllImage(currentPage);
				}
				log.info(productId + "");
				if (productId > 0) {
					imageList = productOperation.findImageByProductId(productId, currentPage);
					request.setAttribute("productId", productId);
				}
				request.setAttribute("imageList", imageList);
				request.setAttribute("imageListSize", imageList.size());

				request.getRequestDispatcher("/WEB-INF/page/imageManager.jsp").forward(request, response);
			}
		}
		if (action.equals("addImagePage")) {
			request.getRequestDispatcher("/WEB-INF/page/addImagePage.jsp").forward(request, response);
		}
		if (action.equals("updateImagePage")) {

			int imageId = Integer.parseInt(request.getParameter("imageId"));
			Image image = productOperation.getImageUrlById(imageId);

			request.setAttribute("imageId", imageId);
			request.setAttribute("imageCategory", image.getImageCategory());
			request.setAttribute("imageUrl", image.getImageUrl());
			request.getRequestDispatcher("/WEB-INF/page/updateImage.jsp").forward(request, response);
		}
		if (action.equals("addImageOperation")) {
			int productId = Integer.parseInt(request.getParameter("productId"));
			String imageUrl = request.getParameter("imageUrl");
			String imageCategory = request.getParameter("imageCategory");
			boolean isAdd = productOperation.addImage(productId, imageUrl, imageCategory);
			int imageId = productOperation.findImageIdByImageUrl(imageUrl);
			if (isAdd) {
				OperationLog operationLog = new OperationLog();
				operationLog.setOperationName("添加");
				operationLog.setOperationContent("添加id为" + imageId + "的图片");
				operationLog.setCreateTime(new Date().getTime());
				operationLog.setUpdateTime(new Date().getTime());
				staff = (Staff) session.getAttribute("staff");
				productOperation.addStaffOperationLog(staff, operationLog);
				response.getWriter().print("添加成功");
			} else {
				response.getWriter().print("添加失败");
			}
		}
		if (action.equals("updateImageOperation")) {
			String imageUrl = request.getParameter("imageUrl");
			int imageId = Integer.parseInt(request.getParameter("imageId"));
			String imageCatgeory = request.getParameter("imageCategory");
			boolean isUpdate = productOperation.updateImageUrlById(imageUrl, imageId, imageCatgeory);
			if (isUpdate) {
				OperationLog operationLog = new OperationLog();
				operationLog.setOperationName("更新");
				operationLog.setOperationContent("更新id为" + imageId + "的图片");
				operationLog.setCreateTime(new Date().getTime());
				operationLog.setUpdateTime(new Date().getTime());
				staff = (Staff) session.getAttribute("staff");
				productOperation.addStaffOperationLog(staff, operationLog);
				response.getWriter().print("更新成功");
			} else {
				response.getWriter().print("更新失败");
			}

		}
		if (action.equals("deleteImageOperation")) {
			int imageId = Integer.parseInt(request.getParameter("imageId"));
			boolean isDelete = productOperation.deleteImageOperationById(imageId);
			if (isDelete) {
				OperationLog operationLog = new OperationLog();
				operationLog.setOperationName("删除");
				operationLog.setOperationContent("删除id为" + imageId + "的图片");
				operationLog.setCreateTime(new Date().getTime());
				operationLog.setUpdateTime(new Date().getTime());
				staff = (Staff) session.getAttribute("staff");
				productOperation.addStaffOperationLog(staff, operationLog);
				response.getWriter().print("删除成功");
			} else {
				response.getWriter().print("删除失败");
			}
		}
		if (action.equals("showAllProduct")) {
			int productId = Integer.parseInt(request.getParameter("productId"));
			ArrayList<productCategory> categoryList = productOperation.findCategoryByProductId(productId);
			JSONArray categoryjson = JSONArray.fromObject(categoryList);
			response.getWriter().print(categoryjson);
		}
		if (action.equals("showProduct")) {
			int categoryId = Integer.parseInt(request.getParameter("categoryid"));
			ArrayList<Product> productList = productOperation.findProductByCategoryid(categoryId);
			JSONArray productjson = JSONArray.fromObject(productList);
			response.getWriter().print(productjson);
			log.info(productjson + "");
		}

		if (action.equals("showAttribute")) {
			int categoryId = Integer.parseInt(request.getParameter("categoryId").trim());
			Map<Integer, String> attributeMap = productOperation.findAttributeByCategoryId(categoryId);
			request.setAttribute("categoryId", categoryId);
			request.setAttribute("attributeMap", attributeMap);
			request.getRequestDispatcher("WEB-INF/page/showAttribute.jsp").forward(request, response);
			return;
		}
		if (action.equals("attributeEditer")) {
			List<String> attributeNameList = null;
			try {
				attributeNameList = Arrays.asList(request.getParameterValues("attributeName"));
				int categoryId = Integer.parseInt(request.getParameter("categoryId"));
				if (attributeNameList.size() > 0) {
					for (int i = 0; i < attributeNameList.size(); i++) {
						log.info(">>>");
						productOperation.addAttributeByCategoryId(categoryId, attributeNameList.get(i));
					}
				}
			} catch (Exception e) {

			}

			Enumeration enu = request.getParameterNames();
			while (enu.hasMoreElements()) {
				String attributeId = (String) enu.nextElement();
				if (!(attributeId.equals("categoryId")) && !(attributeId.equals("attributeName"))) {
					String attributeName = request.getParameter(attributeId);
					productOperation.updateAttributeByAttributeId(Integer.parseInt(attributeId.trim()), attributeName);
				}

			}

			response.sendRedirect("categoryManager.do");
		}
		if (action.equals("deleteAttribute")) {
			int attributeId = Integer.parseInt(request.getParameter("attributeId"));
			boolean isDelete = productOperation.deleteAttributeByAttributeId(attributeId);
			if (isDelete) {
				response.getWriter().print("删除成功");
			} else {
				response.getWriter().print("删除失败");
			}
		}
		if (action.equals("productAttributeManager")) {
			request.getRequestDispatcher("/WEB-INF/page/productAttributeManager.jsp").forward(request, response);
		}
		if (action.equals("sendComment")) {

			if (session.getAttribute("customerID") != null) {
				int customerId = (int) session.getAttribute("customerID");
				String customerName = productOperation.findCustomerNameByCustomerId(customerId);
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String createTime = simpleDateFormat.format(new Date());
				String updateTime = simpleDateFormat.format(new Date());
				String commentLevel = request.getParameter("commentLevel");
				ArrayList<String> ImageSrcList = new ArrayList<String>();
				
				String Content = request.getParameter("contenttext").replaceAll("<p>", "").replaceAll("</p>", "");
				log.info(Content + "content");
				ArrayList<Sensitive> sensitiveList = productOperation.getAllSensitive();
				String productContent = "";
				for (int i = 0; i < sensitiveList.size(); i++) {
					String Sensitive = sensitiveList.get(i).getSensitiveValue();
					if (Content.contains(Sensitive)) {
						String symbol = "";
						for (int j = 0; j < Sensitive.length(); j++) {
							symbol += "*";
						}
						productContent = Content.replaceAll(Sensitive, symbol);
					}
				}
				productContent = Content;
				log.info(productContent + "------------------------");
				int productId = Integer.parseInt(request.getParameter("productId"));

				ArrayList<String> commentList = new ArrayList<String>();
				commentList.add(customerName);
				commentList.add(createTime);
				commentList.add(productContent);
				JSONArray commentJson = JSONArray.fromObject(commentList);
				log.info(commentJson + "");
				boolean isAdd = productOperation.addProductComment(productId, customerId, customerName, productContent,
						commentLevel, createTime, updateTime);
				if (isAdd) {
					response.getWriter().print(commentJson);
					try {
						String container = request.getParameter("content");
						int tagCount = org.apache.commons.lang.StringUtils.countMatches(container, "img");
						String content = "";
						for (int i = 0; i < tagCount; i++) {
							content = container.substring(container.indexOf("<img"), container.indexOf("/>") + 2);
							container = container.replace(content, "");
							String src = content.substring(content.indexOf("=") + 2, content.indexOf("jpg") + 3);
							ImageSrcList.add(src);
						}
						int commentId = productOperation.findCommentIdByTimeAndCustomerId(customerId, createTime);
						for (int i = 0; i < ImageSrcList.size(); i++) {
							productOperation.addCommentImage(ImageSrcList.get(i), productId, customerId, commentId,
									customerName);
						}
					} catch (Exception e) {

					}
				} else {
					response.getWriter().print("发布失败");
				}
			} else {
				response.getWriter().print("未登录不能发表评论");
			}
		}
		if (action.equals("findCommentByLevel")) {
			String commentLevel = request.getParameter("commentLevel");
			log.info(commentLevel);
			int productId = Integer.parseInt(request.getParameter("productId"));
			int currentPage = 1;
			try {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			} catch (NumberFormatException e) {
				currentPage = 1;
			}
			log.info(currentPage + "");
			ArrayList<Comment> commentLevelList = productOperation.findCommentByLevel(commentLevel, currentPage,
					productId);
			log.info(commentLevelList + "lll");
			JSONArray commentLevelJson = JSONArray.fromObject(commentLevelList);
			response.getWriter().print(commentLevelJson);
		}
		if (action.equals("getTotalByCommentLevel")) {

			int productId = Integer.parseInt(request.getParameter("productId"));
			String commentLevel = request.getParameter("commentLevel");
			int totalPage = productOperation.getCommentTotalPagesByCommentLevel(commentLevel, productId);
			response.getWriter().print(totalPage);

		}
		if (action.equals("findComment")) {
			int currentPage = 0;
			try {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			} catch (NumberFormatException e) {
				currentPage = 1;
			}
			int productId = Integer.parseInt(request.getParameter("productId"));
			log.info(currentPage + "");
			ArrayList<Comment> commentLevelList = productOperation.findCommentByProductId(productId, currentPage);
			log.info(commentLevelList + "lll");
			JSONArray commentLevelJson = JSONArray.fromObject(commentLevelList);
			response.getWriter().print(commentLevelJson);
		}
		if (action.equals("addSpecification")) {
			int productId = Integer.parseInt(request.getParameter("productId"));
			int categoryId = productOperation.findCategoryIdByProductId(productId);
			ArrayList<CategoryAttribute> attributeList = productOperation.findAttributeNameByCategoryId(categoryId);
			request.setAttribute("productId", productId);
			request.setAttribute("attributeList", attributeList);
			request.setAttribute("attributeListSize", attributeList.size());
			request.getRequestDispatcher("WEB-INF/page/addSpecification.jsp").forward(request, response);
		}
		/*
		 * if (action.equals("addSpecificationOperation")) { log.info(">>>");
		 * int productId = Integer.parseInt(request.getParameter("productId"));
		 * Enumeration<String> enu = request.getParameterNames();
		 * ProductOperation productOperation = new ProductOperationImpl(); int
		 * categoryId=productOperation.findCategoryIdByProductId(productId);
		 * boolean isAdd = false; while (enu.hasMoreElements()) { String name =
		 * enu.nextElement(); log.info(name); if (!(name.contains("input")) &&
		 * !(name.equals("productId"))) { String attributeName =
		 * request.getParameter(name); log.info(attributeName); int attributeId
		 * = productOperation.findAttributeIdByName(attributeName,categoryId);
		 * String attributeValue = request.getParameter(attributeId + "input");
		 * log.info(attributeValue); isAdd =
		 * productOperation.addSkuAttribute(productId, attributeId,
		 * attributeValue); } } if (isAdd) {
		 * response.sendRedirect("showSkuAttribute.do?productId=" + productId);
		 * } }
		 */
		if (action.equals("showSkuAttribute")) {
			int productId = Integer.parseInt(request.getParameter("productId"));
			int categoryId = productOperation.findCategoryIdByProductId(productId);
			List<SkuAttribute> attributeList = productOperation.findAttributeByProductId(productId);
			log.info(attributeList + "list===");
			request.setAttribute("attributeList", attributeList);
			request.setAttribute("attributeListSize", attributeList.size());
			List<String> attributeNameList = productOperation.findAttributeNameByProductId(categoryId);
			request.setAttribute("attributeNameList", attributeNameList);
			request.setAttribute("attributeNameListSize", attributeNameList.size());
			request.setAttribute("productId", productId);
			request.getRequestDispatcher("WEB-INF/page/showSkuAttribute.jsp").forward(request, response);
		}
		if (action.equals("sensitiveManager")) {
			if (session.getAttribute("staff") == null) {
				request.getRequestDispatcher("WEB-INF/page/adminLoginError.jsp").forward(request, response);
			} else {
				ArrayList<Sensitive> sensitiveList = productOperation.getAllSensitive();
				request.setAttribute("sensitiveList", sensitiveList);
				request.setAttribute("sensitiveListSize", sensitiveList.size());
				request.getRequestDispatcher("WEB-INF/page/sensitiveManager.jsp").forward(request, response);
			}
		}
		if (action.equals("deleteSensitive")) {
			int sensitiveId = Integer.parseInt(request.getParameter("sensitiveId"));
			boolean isDelete = productOperation.deleteSensitiveById(sensitiveId);
			if (isDelete) {
				response.getWriter().print("删除成功");
			} else {
				response.getWriter().print("删除失败");
			}

		}
		if (action.equals("addSensitive")) {
			String sensitiveValue = request.getParameter("sensitive");
			log.info(sensitiveValue.contains("，") + "");
			List<String> sensitiveList = null;
			boolean isAdd = false;
			if (sensitiveValue.contains("，")) {
				sensitiveList = Arrays.asList(sensitiveValue.split("，"));
				log.info(sensitiveList + "list");
				for (int i = 0; i < sensitiveList.size(); i++) {
					isAdd = productOperation.addSensitive(sensitiveList.get(i));
				}
			}
			if (sensitiveValue.contains(",")) {
				sensitiveList = Arrays.asList(sensitiveValue.split(","));
				log.info(sensitiveList + "list");
				for (int i = 0; i < sensitiveList.size(); i++) {
					isAdd = productOperation.addSensitive(sensitiveList.get(i));
				}
			}
			if (!(sensitiveValue.contains(",")) && !(sensitiveValue.contains("，"))) {
				isAdd = productOperation.addSensitive(sensitiveValue);
			}
			if (isAdd) {
				response.getWriter().print("添加成功");
			} else {
				response.getWriter().print("添加失败");
			}
		}
		if (action.equals("findCommentImage")) {
			int commentId = Integer.parseInt(request.getParameter("commentId"));
			ArrayList<String> imageList = productOperation.findImageSrcByComentId(commentId);
			JSONArray imageJson = JSONArray.fromObject(imageList);
			response.getWriter().print(imageJson);
		}
		if (action.equals("commentManager")) {
			if (session.getAttribute("staff") == null) {
				request.getRequestDispatcher("WEB-INF/page/adminLoginError.jsp").forward(request, response);
			} else {
				HashMap<String, String> conditionMap = new HashMap<String, String>();
				String currentPage = request.getParameter("currentPage");
				int currentpage = 1;
				if (currentPage != null) {
					conditionMap = (HashMap<String, String>) request.getSession().getAttribute("findComment");
					try {
						currentpage = Integer.parseInt(currentPage);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					String productId = request.getParameter("productId");
					String commentLevel = request.getParameter("commentLevel");
					String createTime = request.getParameter("createTime");
					if (JudgmentIsEmpty.judgmentIsEmpty(productId)) {
						conditionMap.put("product_id", productId);
					}
					if (JudgmentIsEmpty.judgmentIsEmpty(commentLevel)) {
						conditionMap.put("comment_level", commentLevel);
					}
					if (JudgmentIsEmpty.judgmentIsEmpty(createTime)) {
						conditionMap.put("createTime", createTime);
					}
				}
				ArrayList<Comment> commentList = new ArrayList<Comment>();
				commentList = productOperation.findCommentByCondition(conditionMap, currentpage);
				int totalPage = productOperation.getCommentTotalPage(conditionMap);
				if (conditionMap != null) {
					request.getSession().setAttribute("findComment", conditionMap);
				}
				request.setAttribute("totalPage", totalPage);
				request.setAttribute("commentList", commentList);
				request.setAttribute("commentListSize", commentList.size());
				request.getRequestDispatcher("WEB-INF/page/commentManager.jsp").forward(request, response);
			}
		}
		
		if (action.equals("showCommentImage")) {
			int commentId = Integer.parseInt(request.getParameter("commentId"));
			ArrayList<CommentImage> commentImageList = productOperation.findCommentImageByComentId(commentId);
			request.setAttribute("commentImageList", commentImageList);
			request.setAttribute("commentImageListSize", commentImageList.size());
			request.getRequestDispatcher("WEB-INF/page/showCommentImage.jsp").forward(request, response);
		}
		if (action.equals("deleteCommentImage")) {
			int commentImageId = Integer.parseInt(request.getParameter("commentImageId"));
			boolean isDelete = productOperation.deleteCommentImageBycommentImageId(commentImageId);
			log.info(isDelete + "");
			if (isDelete) {
				response.getWriter().print("删除成功");
			}
		}
		if (action.equals("deleteComment")) {
			int commentId = Integer.parseInt(request.getParameter("commentId"));
			boolean isDelete = productOperation.deleteCommentById(commentId);
			if (isDelete) {
				response.getWriter().print("删除成功");
			} else {
				response.getWriter().print("删除失败");
			}

		}
		if (action.equals("orderSettlement")) {
			int customerId;
			try {
				Order order = new Order();
				customerId = (int) session.getAttribute("customerID");
				String customerPhone = productOperation.findCustomerPhoneByCustomerId(customerId);
				String orderNumber=String.valueOf(new Date().getTime());
				session.setAttribute("orderNumber", orderNumber);
				ArrayList<ReceiverAddress> receiverAddressList = productOperation
						.findReseiverAddressByCustomerId(customerId);
				ProductItem productItem = null;
				ArrayList<ProductItem> ProductItemList = new ArrayList<ProductItem>();
				ArrayList<Order> orderList=new ArrayList<Order>();
				if(request.getParameter("productId")!=null) {
					int productId = Integer.parseInt(request.getParameter("productId"));
					Product product = productOperation.findProductByID(productId);
					int productCount = Integer.parseInt(request.getParameter("count"));
					productItem = new ProductItem();
					productItem.setProduct(product);
					productItem.setCount(productCount);
					ProductItemList.add(productItem);
					order.setCustomerId(customerId);
					order.setCustomerPhone(customerPhone);
					order.setOrderNumber(orderNumber);
					order.setProductId(String.valueOf(productId));
					order.setTotalPrice(productItem.totalMoney());
					log.info("id");
					session.setAttribute("order", order);
				} else {
					log.info("group");
					ProductItemList=(ArrayList<ProductItem>)session.getAttribute("productitemList");
					/*String productIdStr="";
					double totalPrice=0.00;*/
					for(int i=0;i<ProductItemList.size();i++){
						ProductItem Productitem=ProductItemList.get(i);
						Product product=Productitem.getProduct();
						Order ordergroup=new Order();
						ordergroup.setCustomerId(customerId);
						ordergroup.setCustomerPhone(customerPhone);
						ordergroup.setOrderNumber(orderNumber);
						ordergroup.setProductId(String.valueOf(product.getProduct_id()));
						ordergroup.setTotalPrice(Productitem.totalMoney());
						orderList.add(ordergroup);
					}
					/*productIdStr=productIdStr.substring(0, productIdStr.length()-1);
					log.info(productIdStr);
					order.setProductId(productIdStr);
					order.setTotalPrice(totalPrice);*/
					session.setAttribute("orderList", orderList);
				}
				request.setAttribute("ProductItemList", ProductItemList);
				request.setAttribute("ProductItemListSize", ProductItemList.size());
				request.setAttribute("receiverAddressList", receiverAddressList);
				request.setAttribute("receiverAddressListSize", receiverAddressList.size());
				request.getRequestDispatcher("WEB-INF/page/orderSettlement.jsp").forward(request, response);
			} catch (Exception e) {
				request.getRequestDispatcher("WEB-INF/page/NotLoginError.jsp").forward(request, response);
			}

		}
		if(action.equals("NotLoginError")){
			request.getRequestDispatcher("WEB-INF/page/NotLoginError.jsp").forward(request, response);
			
		}
		if (action.equals("showCategoryAttributeValue")) {
			int attributeId = Integer.parseInt(request.getParameter("attributeId"));
			ArrayList<CategoryAttributeValue> categoryAttributelist = new ArrayList<CategoryAttributeValue>();
			categoryAttributelist = productOperation.findCategoryAttributeValueById(attributeId);
			request.setAttribute("attributeId", attributeId);
			request.setAttribute("categoryAttributelist", categoryAttributelist);
			request.setAttribute("categoryAttributelistSize", categoryAttributelist.size());
			request.getRequestDispatcher("WEB-INF/page/showAttributeValue.jsp").forward(request, response);

		}
		if (action.equals("addCategoryAttributeValue")) {
			int attributeId = Integer.parseInt(request.getParameter("attributeId"));
			List<String> attrvalueList = Arrays.asList(request.getParameterValues("attrvalue"));
			for (int i = 0; i < attrvalueList.size(); i++) {
				productOperation.addCategoryAttributeValue(attributeId, attrvalueList.get(i));
			}
			response.sendRedirect("showCategoryAttributeValue.do?attributeId=" + attributeId);
		}
		if (action.equals("deleteAttributeValue")) {
			int attributeValueId = Integer.parseInt(request.getParameter("attributeValueId"));
			boolean isDelete = productOperation.deleteAttributeValueById(attributeValueId);
			if (isDelete) {
				response.getWriter().print("删除成功");
			} else {
				response.getWriter().print("删除失败");
			}
		}
		if (action.equals("showCategoryAttribute")) {
			String firstCategoryName = request.getParameter("firstCategoryName");
			int categoryId = productOperation.findCategoryIdByName(firstCategoryName);
			log.info(categoryId + "");
			ArrayList<String> categoryAttributeList = productOperation.findCategoryAttributeNameById(categoryId);
			log.info(categoryAttributeList + "");
			Map<String, ArrayList<String>> categoryAttributeMap = new HashMap<String, ArrayList<String>>();
			for (int i = 0; i < categoryAttributeList.size(); i++) {
				log.info(categoryAttributeList.get(i));
				int attributeId = productOperation.findAttributeIdByName(categoryAttributeList.get(i), categoryId);
				log.info(attributeId + ",,,,,,,,,,,,,,,");
				ArrayList<String> attributeValueList = productOperation
						.findCategoryAttributeValueStringById(attributeId);
				log.info(attributeValueList + "///////////////////////");
				categoryAttributeMap.put(categoryAttributeList.get(i), attributeValueList);
			}
			JSONObject json = JSONObject.fromObject(categoryAttributeMap);
			response.getWriter().print(json);
		}
		if (action.equals("myOrder")) {
			if(session.getAttribute("customerID")==null){
				response.sendRedirect("NotLoginError.do");
			}else{
				int customerId = (int) session.getAttribute("customerID");
				ArrayList<MyOrder> myOrderList=new ArrayList<MyOrder>();
				SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar calendar=Calendar.getInstance();
				calendar.add(Calendar.DAY_OF_MONTH, -7);
				Date date=calendar.getTime();
				String timeDifference=simpleDateFormat.format(date);
				myOrderList=productOperation.findOrderByCustomerId(customerId,timeDifference,"");
				request.setAttribute("myOrderList", myOrderList);
				request.setAttribute("myOrderListSize", myOrderList.size());
				request.getRequestDispatcher("WEB-INF/page/myOrder.jsp").forward(request, response);
			}
		}
		if (action.equals("addReceiver")) {
			String receiverAddress = request.getParameter("address");
			String receiverName = request.getParameter("receiver");
			String phoneNumber = request.getParameter("phoneNumber");
			int customerId = (int) session.getAttribute("customerID");
			String email = request.getParameter("email");
			String otherAddressName = request.getParameter("otherAddressName");
			boolean isAdd = productOperation.addReceiverAddress(customerId, receiverAddress, phoneNumber, receiverName,
					email, otherAddressName);
			if (isAdd) {
				response.getWriter().print("添加成功");
			} else {
				response.getWriter().print("添加成功");

			}
		}
		if (action.equals("aliPayPage")) {
			log.info("123432t5");
			// 获得初始化的AlipayClient
			String orderNumber=request.getParameter("orderNumber");
			Order order=null;
			double totalAmount =0.00;
			String getOrderNumber="";
			if(orderNumber==null){
				String receiverInfo=request.getParameter("receiverInfo");
				List<String> receiverAddressList=Arrays.asList(receiverInfo.split(","));
				log.info(session.getAttribute("order")+"");
				if(session.getAttribute("order")==null){
					log.info("nulllllll");
					ArrayList<Order> orderList=(ArrayList<Order>)session.getAttribute("orderList");
					SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					getOrderNumber=orderList.get(0).getOrderNumber();
					String currentTime=simpledateformat.format(new Date());
					for(int i=0;i<orderList.size();i++){
						Order ordergroup=orderList.get(i);
						String receiverName=receiverAddressList.get(0);
						String receiverAddress=receiverAddressList.get(1);
						String receiverPhone=receiverAddressList.get(2);
						ordergroup.setReceiverAddress(receiverAddress);
						ordergroup.setReceiverName(receiverName);
						ordergroup.setReceiverPhone(receiverPhone);
						ordergroup.setOrderState("待付款");
						ordergroup.setCreateTime(currentTime);
						ordergroup.setUpdateTime(currentTime);
						ordergroup.setAliOrderNumber("");
						totalAmount = totalAmount+ordergroup.getTotalPrice();
						productOperation.addOrderInformation(ordergroup);
						log.info(totalAmount+"");
					}
				}else{
				log.info("wqeqe");
				order = (Order) session.getAttribute("order");
				getOrderNumber=order.getOrderNumber();
				String receiverName=receiverAddressList.get(0);
				String receiverAddress=receiverAddressList.get(1);
				String receiverPhone=receiverAddressList.get(2);
				order.setReceiverAddress(receiverAddress);
				order.setReceiverName(receiverName);
				order.setReceiverPhone(receiverPhone);
				order.setOrderState("待付款");
				SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				order.setCreateTime(simpledateformat.format(new Date()));
				order.setUpdateTime(simpledateformat.format(new Date()));
				order.setAliOrderNumber("");
				totalAmount = order.getTotalPrice();
				boolean isAdd=productOperation.addOrderInformation(order);
				session.setAttribute("order", order);
				}
			}else{
				log.info("asd");
				order=productOperation.findOrderByOrderNumber(orderNumber);
				totalAmount = order.getTotalPrice();
				getOrderNumber=order.getOrderNumber();
				session.setAttribute("order", order);
			}
			log.info(totalAmount+"");
			AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
					AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
					AlipayConfig.sign_type);

			// 设置请求参数
			AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
			alipayRequest.setReturnUrl(AlipayConfig.return_url);
			alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

			/*
			 * //商户订单号，商户网站订单系统中唯一订单号，必填 String out_trade_no = new
			 * String(request.getParameter("WIDout_trade_no").getBytes(
			 * "ISO-8859-1"),"UTF-8"); //付款金额，必填 String total_amount = new
			 * String(request.getParameter("WIDtotal_amount").getBytes(
			 * "ISO-8859-1"),"UTF-8"); //订单名称，必填 String subject = new
			 * String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"),
			 * "UTF-8"); //商品描述，可空 String body = new
			 * String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),
			 * "UTF-8");
			 */

			// 商户订单号，商户网站订单系统中唯一订单号，必填
			String out_trade_no = String.valueOf(getOrderNumber);
			// 付款金额，必填
			String total_amount = String.valueOf(totalAmount);
			// 订单名称，必填
			String subject = String.valueOf("Honor");
			// 商品描述，可空
			String body = "";
			alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\""
					+ total_amount + "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
					+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

			// 若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
			// alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no
			// +"\","
			// + "\"total_amount\":\""+ total_amount +"\","
			// + "\"subject\":\""+ subject +"\","
			// + "\"body\":\""+ body +"\","
			// + "\"timeout_express\":\"10m\","
			// + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
			// 请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节
			String result = "";
			// 请求
			try {
				result = alipayClient.pageExecute(alipayRequest).getBody();
			} catch (AlipayApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("result", result);
			request.getRequestDispatcher("WEB-INF/page/alipay.trade.page.pay.jsp").forward(request, response);
		}
		if (action.equals("return_url")) {
			Map<String, String> params = new HashMap<String, String>();
			Map<String, String[]> requestParams = request.getParameterMap();
			for (Iterator<String> iterator = requestParams.keySet().iterator(); iterator.hasNext();) {
				String name = (String) iterator.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				// 乱码解决，这段代码在出现乱码时使用
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}

			boolean signVerified = false;
			try {
				signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset,
						AlipayConfig.sign_type);
			} catch (AlipayApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 调用SDK验证签名

			// ——请在这里编写您的程序（以下代码仅作参考）——
			if (signVerified) {
				// 商户订单号
				String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

				// 支付宝交易号
				String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

				// 付款金额
				String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
				log.info(out_trade_no + "/" + total_amount + "/" + trade_no);
				if(session.getAttribute("order")==null){
					String orderNumber=(String) session.getAttribute("orderNumber");
					productOperation.updateOrderAliPayNumberByOrderNumber(orderNumber,trade_no,"未发货");
				}else{
					Order order = (Order) session.getAttribute("order");
					if(order.getAliOrderNumber().equals("")){
						productOperation.updateOrderAliPayNumberByOrderNumber(order.getOrderNumber(),trade_no,"未发货");
					}
				}
				
			} else {
				response.sendRedirect("alipay.trade.close.do");
			}
			response.sendRedirect("homepage.do");
			// request.getRequestDispatcher("WEB-INF/page/return_url.jsp").forward(request,
			// response);
		}
		if (action.equals("aliOrderManager")) {

			request.getRequestDispatcher("WEB-INF/page/aliOrderManager.jsp").forward(request, response);

		}
		if (action.equals("alipay.trade.query")) {

			request.getRequestDispatcher("WEB-INF/page/alipay.trade.query.jsp").forward(request, response);
		}
		if (action.equals("alipay.trade.refund")) {
			//获得初始化的AlipayClient
			AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
			
			//设置请求参数
			AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
			
			//商户订单号，商户网站订单系统中唯一订单号
			String orderNumber = new String(request.getParameter("orderNumber").getBytes("ISO-8859-1"),"UTF-8");
			log.info(orderNumber);
			//支付宝交易号
			String trade_no ="";
			//请二选一设置
			log.info(String.valueOf(productOperation.findTotalPriceByOrderNumber(orderNumber)));
			//需要退款的金额，该金额不能大于订单金额，必填
			String refund_amount = new String(String.valueOf(productOperation.findTotalPriceByOrderNumber(orderNumber)).getBytes("ISO-8859-1"),"UTF-8");
			//退款的原因说明
			String refund_reason ="";
			//标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
			String out_request_no = "";
			
			alipayRequest.setBizContent("{\"out_trade_no\":\""+ orderNumber +"\"," 
					+ "\"trade_no\":\""+ trade_no +"\"," 
					+ "\"refund_amount\":\""+ refund_amount +"\"," 
					+ "\"refund_reason\":\""+ refund_reason +"\"," 
					+ "\"out_request_no\":\""+ out_request_no +"\"}");
			
			//请求
			String result="";
			try {
				result = alipayClient.execute(alipayRequest).getBody();
			} catch (AlipayApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.info(result);
			com.alibaba.fastjson.JSONObject resultJson=JSON.parseObject(result);
			String responseResult=resultJson.getString("alipay_trade_refund_response");
			com.alibaba.fastjson.JSONObject responseJson=JSON.parseObject(responseResult);
			String message=responseJson.getString("msg");
			String fundChange=responseJson.getString("fund_change");
			log.info(message);
			log.info(fundChange);
			Order order=productOperation.findOrderByOrderNumber(orderNumber);
			productOperation.updateOrderRefundStateByOrderNumber(orderNumber);
			Refund refund=new Refund();
			if(message.equals("Success")&&fundChange.equals("Y")){
				response.getWriter().print("退款成功");
				refund.setOrder(order);
				SimpleDateFormat simpleDateFormat=new SimpleDateFormat();
				String createTime=simpleDateFormat.format(new Date());
				refund.setCreateTime(createTime);
				staff=(Staff)session.getAttribute("staff");
				refund.setStaffId(staff.getStaffId());
				refund.setRefundIntroduction(refund_reason);
				//TODO 退款后续处理
			}else{
				response.getWriter().print("退款失败");
			}
			//request.getRequestDispatcher("WEB-INF/page/alipay.trade.refund.jsp").forward(request, response);
		}
		if (action.equals("alipay.trade.fastpay.refund.query")) {

			request.getRequestDispatcher("WEB-INF/page/alipay.trade.fastpay.refund.query.jsp").forward(request,
					response);
		}
		if (action.equals("alipay.trade.close")) {
			request.getRequestDispatcher("WEB-INF/page/alipay.trade.close.jsp").forward(request, response);
		}
		if (action.equals("CaptchaVrify")) {
			String captcha = (String) session.getAttribute("captcha");
			String inputCaptcha = request.getParameter("captcha");
			if (inputCaptcha.equals(captcha)) {
				response.getWriter().print("正确");
			} else {
				response.getWriter().print("错误");
			}

		}
		if(action.equals("orderManager")){
			int currentPage;
			try {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			} catch (NumberFormatException e) {
				currentPage=1;
			}
			HashMap<String,String> conditionMap=new HashMap<String, String>();
			String customerPhone=request.getParameter("customerPhone");
			String orderNumber=request.getParameter("orderNumber");
			String createTime=request.getParameter("createTime");
			String deliverGoodsState=request.getParameter("orderState");
			System.out.println(customerPhone+"/"+orderNumber+"/"+createTime+"/"+deliverGoodsState);
			if(currentPage==1){
				if (JudgmentIsEmpty.judgmentIsEmpty(customerPhone)) {
					conditionMap.put("customer_phone", customerPhone);
				}
				if (JudgmentIsEmpty.judgmentIsEmpty(orderNumber)) {
					conditionMap.put("order_number", orderNumber);
				}
				if (JudgmentIsEmpty.judgmentIsEmpty(createTime)) {
					conditionMap.put("createTime", createTime);
				}
				if(JudgmentIsEmpty.judgmentIsEmpty(deliverGoodsState)){
					conditionMap.put("order_state", deliverGoodsState);
				}
				ArrayList<Order> orderList=new ArrayList<Order>();
				log.info("safdghjghdsdf");
				orderList=productOperation.findOrderListByCondition(conditionMap, currentPage);
				log.info("asfdgfhgh");
				int totalPage=productOperation.getOrderTotalPage(conditionMap);
				log.info(totalPage+"");
				if (conditionMap != null) {
					request.getSession().setAttribute("orderCondition", conditionMap);
				}
				orderList=productOperation.findOrderListByCondition(conditionMap, currentPage);
				log.info(orderList+"");
				request.setAttribute("totalPage", totalPage);
				request.setAttribute("orderList", orderList);
				request.setAttribute("orderListSize", orderList.size());
			}
			request.getRequestDispatcher("WEB-INF/page/orderManager.jsp").forward(request, response);
		}
		if(action.equals("orderCondition")){
			HashMap<String,String> conditionMap=(HashMap<String, String>) session.getAttribute("orderCondition");
			JSONObject conditionMapJson=JSONObject.fromObject(conditionMap);
			log.info(conditionMapJson+"");
			response.getWriter().print(conditionMapJson);
		}
		if(action.equals("changeOrderState")){
			String orderNumber=request.getParameter("orderNumber");
			productOperation.updateDeliverGoodsStateByOrderNumber(orderNumber);
		}
		if(action.equals("findReceiverInfo")){
			String orderNumber=request.getParameter("orderNumber");
			ArrayList<String> receiverInfoList=productOperation.findReceiverInfoByOrderNumber(orderNumber);
			JSONArray receiverInfoJson=JSONArray.fromObject(receiverInfoList);
			log.info(receiverInfoJson+"");
			response.getWriter().print(receiverInfoJson);
			
		}
		if(action.equals("updateOrder")){
			String orderNumber=request.getParameter("orderNumber");
			String receiverName=request.getParameter("receiverName");
			String receiverPhone=request.getParameter("receiverPhone");
			String receiverAddress=request.getParameter("receiverAddress");
			boolean isUpdate=productOperation.updateReveiverInfoByOrderNumber(orderNumber, receiverName, receiverPhone, receiverAddress);
			log.info(isUpdate+"");
			if(isUpdate){
				response.getWriter().print("更新成功");
			}else{
				response.getWriter().print("更新失败");
			}
		}
		if(action.equals("findMyOrder")){
			if(session.getAttribute("customerID")==null){
				response.sendRedirect("NotLoginError.do");
			}else{
				int customerId=(int)session.getAttribute("customerID");
				String orderCategory=request.getParameter("orderCategory");
				log.info(orderCategory);
				String timeDifferenceValue=request.getParameter("timeDifference");
				SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar calendar=Calendar.getInstance();
				String timeDifference="";
				if(timeDifferenceValue==null){
					calendar.add(Calendar.DAY_OF_MONTH, -7);
					Date date=calendar.getTime();
					timeDifference=simpleDateFormat.format(date);
					
				}else
				if(timeDifferenceValue.equals("近一周")){
					calendar.add(Calendar.DAY_OF_MONTH, -7);
					Date date=calendar.getTime();
					timeDifference=simpleDateFormat.format(date);
				}else
				if(timeDifferenceValue.equals("近一月")){
					calendar.add(Calendar.MONTH, -1);
					Date date=calendar.getTime();
					timeDifference=simpleDateFormat.format(date);
				}else
				if(timeDifferenceValue.equals("近一年")){
					calendar.add(Calendar.YEAR, -1);
					Date date=calendar.getTime();
					timeDifference=simpleDateFormat.format(date);
				}
				log.info(timeDifference);
				String orderState="";
				switch(orderCategory){
				case "所有订单":
						orderState="";
						break;
				case "待付款":
						orderState="待付款";
						break;
				case "待收货":
						orderState="已发货";
						break;
				case "待评价":
						orderState="待评价";
				
				}
				ArrayList<MyOrder> myOrderList=new ArrayList<MyOrder>();
				myOrderList=productOperation.findOrderByCustomerId(customerId,timeDifference,orderState);
				JSONArray myOrderJson=JSONArray.fromObject(myOrderList);
				response.getWriter().print(myOrderJson);
			}
		}
		if(action.equals("confirmReceipt")){
			String orderNumber=request.getParameter("orderNumber");
			boolean isUpdate=productOperation.confirmReceiptByOrderNumber(orderNumber);
			ArrayList<Integer> productIdList=productOperation.findProductIdByOrderNumber(orderNumber);
			if(isUpdate){
				response.getWriter().print("已收货");
			}else{
				response.getWriter().print("收货失败");
			}
		}
		if(action.equals("deleteMyOrder")){
			String orderNumber=request.getParameter("orderNumber");
			boolean isUpdate=productOperation.updateMyOrderStateToDeleteByOrderNumber(orderNumber);
			log.info(isUpdate+"");
			if(isUpdate){
				response.getWriter().print("删除成功");
			}else{
				response.getWriter().print("删除失败");
			}
		}
		if(action.equals("judgmentIsComment")){
			if (session.getAttribute("customerID") != null) {
				int productId=Integer.parseInt(request.getParameter("productId"));
				int customerId=(int)session.getAttribute("customerID");
				String orderNumber=(String)session.getAttribute("orderNumber");
				String orderState="待评价";
				log.info(customerId+"customerID"+productId);
				boolean isExist=productOperation.judgmentWhetherComment(productId, customerId, orderState);
				log.info(isExist+"");
				if(isExist){
					response.getWriter().print("存在");
					productOperation.updateMyOrderState(productId, customerId, orderState,orderNumber);
				}else{
					response.getWriter().print("不存在");
				}
			}else{
				response.getWriter().print("请登陆后评论");
			}
			
		}
		if(action.equals("judgmentAttributeItem")){
			List<String> attributeValueList=Arrays.asList(request.getParameter("attributeValue").split(","));
			int productId=Integer.parseInt(request.getParameter("productId"));
			Map<String,ArrayList<String>> attributeValueMap=new HashMap<String, ArrayList<String>>();
			for(int i=0;i<attributeValueList.size();i++){
				ArrayList<String> attributeList=productOperation.judgmentAattributeItem(attributeValueList.get(i), productId);
				attributeValueMap.put(String.valueOf(i), attributeList);
				log.info(attributeList+"");
			}
			JSONObject attributeJson=JSONObject.fromObject(attributeValueMap);
			log.info(attributeJson+"");
			response.getWriter().print(attributeJson);
		}
		if(action.equals("submitApplication")){
			String orderNumber=request.getParameter("orderNumber");
			String refundReson=request.getParameter("refundReson");
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String createTime=simpleDateFormat.format(new Date());
			String updateTime=simpleDateFormat.format(new Date());
			boolean isAdd=productOperation.addRefundReson(orderNumber, refundReson, createTime, updateTime);
			if(isAdd){
				response.getWriter().print("申请成功");
				productOperation.updateMyOrderRefundState("已提交退款申请", orderNumber);
			}else{
				response.getWriter().print("申请失败");
			}
		}
		if(action.equals("cancelOrder")){
			String orderNumber=request.getParameter("orderNumber");
			boolean isDelete=productOperation.cancelOrderByOrderNumber(orderNumber);
			if(isDelete){
				response.getWriter().print("取消成功");
			}else{
				response.getWriter().print("取消失败");
			}
		}
		
		
		
		
		
		
		
		
		
		
		/*
		 * if(action.equals("findCommentByCondition")){ String
		 * productId=request.getParameter("productId"); String
		 * commentLevel=request.getParameter("commentLevel"); String
		 * createTime=request.getParameter("createTime"); HashMap<String,String>
		 * conditionMap=new HashMap<String, String>();
		 * if(JudgmentIsEmpty.judgmentIsEmpty(productId)){
		 * conditionMap.put("product_id", productId); }
		 * if(JudgmentIsEmpty.judgmentIsEmpty(commentLevel)){
		 * conditionMap.put("comment_level", commentLevel); }
		 * if(JudgmentIsEmpty.judgmentIsEmpty(createTime)){
		 * conditionMap.put("createTime", createTime); } int currentPage=0; try
		 * { currentPage=Integer.parseInt(request.getParameter("currentPage"));
		 * } catch (NumberFormatException e) { currentPage=1; } ProductOperation
		 * productOperation=new ProductOperationImpl(); ArrayList<Comment>
		 * commentList=new ArrayList<Comment>();
		 * commentList=productOperation.findCommentByCondition(conditionMap,
		 * currentPage); JSONArray
		 * commentJson=JSONArray.fromObject(commentList);
		 * log.info(commentJson+""); response.getWriter().print(commentJson); }
		 */

	}

	public void init() throws ServletException {
		// Put your code here
	}

}
