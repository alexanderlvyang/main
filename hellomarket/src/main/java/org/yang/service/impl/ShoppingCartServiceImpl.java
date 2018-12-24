package org.yang.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.yang.mapper.ProductMapper;
import org.yang.mapper.ProductSpecificationMapper;
import org.yang.mapper.ShoppingCartMapper;
import org.yang.pojo.ProductInfo;
import org.yang.pojo.ShoppingCart;
import org.yang.pojo.Users;
import org.yang.service.ShoppingCartService;
import org.yang.utils.Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import net.sf.json.JSONObject;
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
	@Resource
	private ShoppingCartMapper shoppingCartMapper;
	@Resource
	private ProductMapper productMapper;
	@Resource
	private ProductSpecificationMapper productSpecificationMapper;
	private ShoppingCart shoppingcart=new ShoppingCart();
	@Override
	public int addShoppingCart(ShoppingCart shoppingCart) {
		return shoppingCartMapper.insertShoppingCart(shoppingCart);
	}

	@Override
	public List<ShoppingCart> showShoppingCart(int userId) {
		return shoppingCartMapper.selectShoppingCartById(userId);
	}

	@Override
	public void addShoppingCartNotUser(String productId, String color, String specification, String count,
			HttpServletRequest request, HttpServletResponse response) {
		ProductInfo productInfo=null;
		int specificationId=0;
		if(Utils.judgEmpty(color)) {
			productInfo=productMapper.selectProductInfoByProductIdAndSpecification(Integer.parseInt(productId), specification);
			specificationId=productSpecificationMapper.selectSpecificationByColorAndSpecification(color, specification, Integer.parseInt(productId)).getSpecification_id();
		}else {
			productInfo=productMapper.selectProductInfoByProductIdAndSpecificationAndColor(Integer.parseInt(productId), specification, color);
			specificationId=productSpecificationMapper.selectSpecificationByColorAndSpecification(color, specification, Integer.parseInt(productId)).getSpecification_id();
		}
		productInfo.setCount(Integer.parseInt(count));
		double totalPrice=Integer.parseInt(count)*productInfo.getPrice();
		productInfo.setTotal_price(totalPrice);
		Users user=(Users) request.getSession().getAttribute("user");
		if(user!=null) {
			ShoppingCart shoppingCart=new ShoppingCart();
			shoppingCart.setId(user.getId());
			shoppingCart.setProduct_id(Integer.parseInt(productId));
			shoppingCart.setSpecification_id(specificationId);
			shoppingCart.setTotal_price(totalPrice);
			shoppingCart.setCount(Integer.parseInt(count));
			ShoppingCart shoppingCartBySpecificationId = shoppingCartMapper.selectShoppingCartBySpecificationId(specificationId);
			if(shoppingCartBySpecificationId!=null) {
				int newcount=shoppingCartBySpecificationId.getCount()+Integer.parseInt(count);
				shoppingCartMapper.updateShoppingCartBySpecificationId(newcount, specificationId);
			}else {
				shoppingCartMapper.insertShoppingCart(shoppingCart);
			}
		}else if(user==null) {
			Cookie[] cookies = request.getCookies();
			boolean isexist = false;
			if (cookies == null) {
				shoppingcart = new ShoppingCart();
			} else {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("productInfo")) {
						isexist = true;
					}
				}
				if (!isexist) {
					shoppingcart = new ShoppingCart();
				}
			}
			
			shoppingcart.addCartMap(productId, productInfo, Integer.parseInt(count));
			try {
				Cookie cookie = new Cookie("productInfo",URLEncoder.encode(JSONObject.fromObject(shoppingcart.getCartMap()).toString(),"UTF-8"));
				response.addCookie(cookie);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}


	@Override
	public void showCart(HttpServletRequest request,Model model) {
		Users user=(Users)request.getSession().getAttribute("user");
		Map<String,ProductInfo> shoppingCartMap=new HashMap<String,ProductInfo>();
		List<ProductInfo> productInfoList=new ArrayList<ProductInfo>();
		List<ProductInfo> cookieProductInfoList=new ArrayList<ProductInfo>();
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
					if(cookie.getName().equals("productInfo")){
						try {
								String mapString=URLDecoder.decode(cookie.getValue(), "UTF-8");
								shoppingCartMap= (Map<String, ProductInfo>) JSON.parseObject(mapString,
										new TypeReference<Map<String, ProductInfo>>() {});
								Iterator<String> iterator = shoppingCartMap.keySet().iterator();
								String key = "";
								ProductInfo productInfo = null;
								while (iterator.hasNext()) {
									key = (String) iterator.next();
									productInfo = (ProductInfo) shoppingCartMap.get(key);
									cookieProductInfoList.add(productInfo);
								}
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			if(user!=null) {
				productInfoList=shoppingCartMapper.selectProductInfoById(user.getId());
				for(int i=0;i<cookieProductInfoList.size();i++) {
					for(int j=0;j<productInfoList.size();j++) {
						if(cookieProductInfoList.get(i).getSpecification_id()==productInfoList.get(j).getSpecification_id()) {
							ProductInfo productInfo=cookieProductInfoList.get(i);
							productInfo.setCount(cookieProductInfoList.get(i).getCount()+productInfoList.get(j).getCount());
							productInfo.setTotal_price(cookieProductInfoList.get(i).getTotal_price()+productInfoList.get(j).getTotal_price());
							productInfoList.remove(productInfoList.get(j));
							cookieProductInfoList.remove(cookieProductInfoList.get(i));
							cookieProductInfoList.add(productInfo);
						}
					}
				}
				model.addAttribute("productInfoList", productInfoList);
			}
			model.addAttribute("cookieProductInfoList", cookieProductInfoList);
	}

	@Override
	public String deleteShoppingCart(String specificationId,HttpServletRequest request,HttpServletResponse response) {
		String status="";
		Users user=(Users)request.getSession().getAttribute("user");
		Map<String,ProductInfo> shoppingCartMap=new HashMap<String,ProductInfo>();
		if(user==null) {
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
					if(cookie.getName().equals("productInfo")){
						try {
								String mapString=URLDecoder.decode(cookie.getValue(), "UTF-8");
								shoppingCartMap= (Map<String, ProductInfo>) JSON.parseObject(mapString,
										new TypeReference<Map<String, ProductInfo>>() {});
						/*		Iterator<String> iterator = shoppingCartMap.keySet().iterator();
								String key = "";
								ProductInfo productInfo = null;
								while (iterator.hasNext()) {
									key = (String) iterator.next();
									productInfo = (ProductInfo) shoppingCartMap.get(key);
									productInfoList.add(productInfo);
								}*/
								shoppingCartMap.remove(specificationId);
								Cookie newcookie = new Cookie("productInfo",URLEncoder.encode(JSONObject.fromObject(shoppingCartMap).toString(),"UTF-8"));
								response.addCookie(newcookie);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			status="删除成功";
		}else {
			int deleteStatus = shoppingCartMapper.deleteShoppingCartBySpecificationId(Integer.parseInt(specificationId));
			if(deleteStatus==1) {
				status="删除成功";
			}else {
				status="删除失败";
			}
		}
		return status;
	}

}
