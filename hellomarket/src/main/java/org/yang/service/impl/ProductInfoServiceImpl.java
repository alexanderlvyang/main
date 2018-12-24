package org.yang.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.yang.mapper.ProductMapper;
import org.yang.pojo.ProductInfo;
import org.yang.service.ProductInfoService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
@Service
public class ProductInfoServiceImpl implements ProductInfoService{
	@Resource
	private ProductMapper productMapper;
	@Override
	public void getProductInfoBySpecificationId(String specificationId,String Identification,String count,Model model,HttpServletRequest request) {
		List<ProductInfo> productInfoList=new ArrayList<ProductInfo>();
		Map<String,ProductInfo> shoppingCartMap=new HashMap<String,ProductInfo>();
		if(Identification.equals("detail")) {
			try {
				specificationId=URLDecoder.decode(specificationId, "UTF-8").substring(0, URLDecoder.decode(specificationId, "UTF-8").length()-1);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ProductInfo productInfo = productMapper.selectProductInfoByProductSpecificationId(Integer.parseInt(specificationId));
			productInfo.setTotal_price(productInfo.getPrice()*Integer.parseInt(count));
			productInfo.setCount(Integer.parseInt(count));
			productInfoList.add(productInfo);
			model.addAttribute("productInfoLsit", productInfoList);
			model.addAttribute("payTotalPrice", productInfo.getPrice()*Integer.parseInt(count));
			request.getSession().setAttribute("totalPrice", productInfo.getPrice()*Integer.parseInt(count));
		}else {
			try {
				String specificationArray=URLDecoder.decode(specificationId, "UTF-8");
				List<String> specificationIdList= Arrays.asList(specificationArray.substring(0, specificationArray.length()-1).split(","));
				for(int i=0;i<specificationIdList.size();i++) {
					ProductInfo productInfo = productMapper.selectProductInfoBySpecificationId(Integer.parseInt(specificationIdList.get(i)));
					if(productInfo==null) {
						Cookie[] cookies = request.getCookies();
						for (Cookie cookie : cookies) {
								if(cookie.getName().equals("productInfo")){
									try {
											String mapString=URLDecoder.decode(cookie.getValue(), "UTF-8");
											shoppingCartMap= (Map<String, ProductInfo>) JSON.parseObject(mapString,
													new TypeReference<Map<String, ProductInfo>>() {});
											Iterator<String> iterator = shoppingCartMap.keySet().iterator();
											String key = "";
											while (iterator.hasNext()) {
												key = (String) iterator.next();
												if(key.equals(specificationIdList.get(i))) {
													productInfo = (ProductInfo) shoppingCartMap.get(key);
												}
											}
									} catch (UnsupportedEncodingException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
							}
						}
					}else {
						Cookie[] cookies = request.getCookies();
						for (Cookie cookie : cookies) {
							if(cookie.getName().equals("productInfo")){
								try {
									String mapString=URLDecoder.decode(cookie.getValue(), "UTF-8");
									shoppingCartMap= (Map<String, ProductInfo>) JSON.parseObject(mapString,
											new TypeReference<Map<String, ProductInfo>>() {});
									Iterator<String> iterator = shoppingCartMap.keySet().iterator();
									String key = "";
									while (iterator.hasNext()) {
										key = (String) iterator.next();
										if(key.equals(specificationIdList.get(i))) {
											ProductInfo cookieProductInfo = (ProductInfo) shoppingCartMap.get(key);
											productInfo.setCount(cookieProductInfo.getCount()+productInfo.getCount());
											productInfo.setTotal_price(cookieProductInfo.getTotal_price()+productInfo.getTotal_price());
										}
									}
								} catch (UnsupportedEncodingException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
						
					}
					productInfoList.add(productInfo);
				}
				model.addAttribute("productInfoLsit", productInfoList);
				double payTotalPrice=0.0f;
				for(int i=0;i<productInfoList.size();i++) {
					payTotalPrice+=productInfoList.get(i).getTotal_price();
				}
				model.addAttribute("payTotalPrice", String.valueOf(payTotalPrice));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public double getTotalPriceBySpecficationId(String  specificationId,HttpServletRequest request) {
		List<ProductInfo> productInfoList=new ArrayList<ProductInfo>();
		Map<String,ProductInfo> shoppingCartMap=new HashMap<String,ProductInfo>();
		double payTotalPrice=0.0f;
		if(specificationId.length()<3) {
			return (double) request.getSession().getAttribute("totalPrice");
		}else {
			try {
				String specificationArray=URLDecoder.decode(specificationId, "UTF-8");
				List<String> specificationIdList= Arrays.asList(specificationArray.substring(0, specificationArray.length()-1).split(","));
				for(int i=0;i<specificationIdList.size();i++) {
					ProductInfo productInfo = productMapper.selectProductInfoBySpecificationId(Integer.parseInt(specificationIdList.get(i)));
					if(productInfo==null) {
						Cookie[] cookies = request.getCookies();
						for (Cookie cookie : cookies) {
								if(cookie.getName().equals("productInfo")){
									try {
											String mapString=URLDecoder.decode(cookie.getValue(), "UTF-8");
											shoppingCartMap= (Map<String, ProductInfo>) JSON.parseObject(mapString,
													new TypeReference<Map<String, ProductInfo>>() {});
											Iterator<String> iterator = shoppingCartMap.keySet().iterator();
											String key = "";
											while (iterator.hasNext()) {
												key = (String) iterator.next();
												if(key.equals(specificationIdList.get(i))) {
													productInfo = (ProductInfo) shoppingCartMap.get(key);
												}
											}
									} catch (UnsupportedEncodingException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
							}
						}
					}else {
						Cookie[] cookies = request.getCookies();
						for (Cookie cookie : cookies) {
							if(cookie.getName().equals("productInfo")){
								try {
									String mapString=URLDecoder.decode(cookie.getValue(), "UTF-8");
									shoppingCartMap= (Map<String, ProductInfo>) JSON.parseObject(mapString,
											new TypeReference<Map<String, ProductInfo>>() {});
									Iterator<String> iterator = shoppingCartMap.keySet().iterator();
									String key = "";
									while (iterator.hasNext()) {
										key = (String) iterator.next();
										if(key.equals(specificationIdList.get(i))) {
											ProductInfo cookieProductInfo = (ProductInfo) shoppingCartMap.get(key);
											productInfo.setCount(cookieProductInfo.getCount()+productInfo.getCount());
											productInfo.setTotal_price(cookieProductInfo.getTotal_price()+productInfo.getTotal_price());
										}
									}
								} catch (UnsupportedEncodingException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
						
					}
					productInfoList.add(productInfo);
				}
				
				for(int i=0;i<productInfoList.size();i++) {
					payTotalPrice+=productInfoList.get(i).getTotal_price();
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return payTotalPrice;
	}

}
