package org.yang.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yang.pojo.Addressee;
import org.yang.pojo.Comment;
import org.yang.pojo.CommentImage;
import org.yang.pojo.ProductInfo;
import org.yang.pojo.Users;
import org.yang.service.AddressService;
import org.yang.service.AdvertisementService;
import org.yang.service.BrandService;
import org.yang.service.CategoryService;
import org.yang.service.CommentImageService;
import org.yang.service.CommentService;
import org.yang.service.OrdersService;
import org.yang.service.ProductImageService;
import org.yang.service.ProductInfoService;
import org.yang.service.ProductService;
import org.yang.service.ProductSpecificationService;
import org.yang.service.RotationChartService;
import org.yang.service.ShoppingCartService;
import org.yang.service.UsersService;
import org.yang.utils.Utils;

import net.sf.json.JSONArray;

@Controller
public class FrontController {
	@Resource
	private CategoryService categoryServiceImpl;
	@Resource
	private RotationChartService rotationChartServiceImpl;
	@Resource
	private AdvertisementService advertisementServiceImpl;
	@Resource
	private ProductService productServiceImpl;
	@Resource
	private BrandService brandServiceImpl;
	@Resource
	private ProductImageService productImageServiceImpl;
	@Resource
	private ProductSpecificationService productSpecificationServiceImpl;
	@Resource
	private CommentService commentServiceImpl;
	@Resource
	private CommentImageService commentImageServiceImpl;
	@Resource
	private ShoppingCartService shoppingCartServiceImpl;
	@Resource
	private ProductInfoService productInfoServiceImpl;
	@Resource
	private AddressService addressServiceImpl;
	@Resource
	private UsersService usersServiceImpl;
	@Resource
	private OrdersService ordersServiceImpl;
	
	@RequestMapping("homePage")
	public String homePage(Model model,HttpServletRequest request) {
		model.addAttribute("categoryList", categoryServiceImpl.showCategory(""));
		model.addAttribute("rotationList", rotationChartServiceImpl.showRotationChart());
		model.addAttribute("imageAdvertiseList", advertisementServiceImpl.showAdvertiseByRangeAndTime("首页", Utils.getCurrentDate(),"图片"));
		model.addAttribute("textAdvertiseList", advertisementServiceImpl.showAdvertiseByRangeAndTime("首页", Utils.getCurrentDate(),"文本"));
		return "WEB-INF/frontpages/home.jsp";
	}
	@RequestMapping("outLogin")
	public String outLogin(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
		return "homePage";
	}
	@RequestMapping("search")
	public String search(Model model,String condition,String startPage,HttpServletRequest request) {
		model.addAttribute("categoryList", categoryServiceImpl.showCategory(""));
		List<ProductInfo> productInfoList=productServiceImpl.searchProduct(condition,startPage,request);
		if(productInfoList.isEmpty()||productInfoList==null){
			model.addAttribute("warning", "很抱歉，商品不存在");
		}else {
			List<ProductInfo> productInfoListed=new ArrayList<ProductInfo>();
			for(int i=0;i<productInfoList.size();i++) {
				ProductInfo productInfo=new ProductInfo();
				productInfo=productInfoList.get(i);
				if(productInfo.getProduct_introduction().length()>15) {
					productInfo.setProduct_introduction(productInfo.getProduct_introduction().substring(0, 15)+"...");
				}
				productInfoListed.add(productInfo);
			}
			model.addAttribute("productInfoList", productInfoListed);
		}
		model.addAttribute("totalPage", productServiceImpl.getProductInfoTotalPage(condition));
		model.addAttribute("imageAdvertiseList", advertisementServiceImpl.showAdvertiseByRangeAndTime("搜索页", Utils.getCurrentDate(),"图片"));
		model.addAttribute("textAdvertiseList", advertisementServiceImpl.showAdvertiseByRangeAndTime("搜索页", Utils.getCurrentDate(),"文本"));
		model.addAttribute("brandList", brandServiceImpl.showBrand());
		return "WEB-INF/frontpages/searchresult.jsp";
	}
	@RequestMapping("productDetail")
	public String productDetail(Model model,String productId,String specification,String color,String startPage) {
		model.addAttribute("productImage", productImageServiceImpl.showProductImage(Integer.parseInt(productId)));
		/*model.addAttribute("thumbnailList",productSpecificationServiceImpl.showSpecificationByProductId(Integer.parseInt(productId)));*/
		model.addAttribute("thumbnailList", productSpecificationServiceImpl.showSpecificationByProductId(Integer.parseInt(productId),"color"));
		model.addAttribute("productInfoList", productServiceImpl.selectProductInfoByProductId(Integer.parseInt(productId)));
		if(Utils.judgEmpty(specification)&&Utils.judgEmpty(color)) {
			color=productServiceImpl.selectProductInfoByProductId(Integer.parseInt(productId)).get(0).getProduct_color();
			specification=productServiceImpl.selectProductInfoByProductId(Integer.parseInt(productId)).get(0).getProduct_specification();
		}else {
			try {
				color = URLDecoder.decode(color, "UTF-8");
				specification=URLDecoder.decode(specification, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(Utils.judgEmpty(color)) {
			model.addAttribute("specificationObj", productSpecificationServiceImpl.selectSpecificationBySpecification(specification, productId));
			model.addAttribute("notInSpecificaionList", productSpecificationServiceImpl.selectNotInColorBySpecification(specification,productId));
			model.addAttribute("specificationId", productSpecificationServiceImpl.selectSpecificationBySpecification(specification, productId).getSpecification_id());
		}else
		if(!(Utils.judgEmpty(specification))){
			model.addAttribute("specificationObj",productSpecificationServiceImpl.selectSpecificationByColorAndSpecification(color,specification,productId));
			model.addAttribute("notInSpecificationList", productSpecificationServiceImpl.selectNotInColorBySpecification(specification,productId));
			model.addAttribute("notInColorList", productSpecificationServiceImpl.selectNotInSpecificationByColor(color,productId));
			model.addAttribute("specificationId", productSpecificationServiceImpl.selectSpecificationByColorAndSpecification(color,specification,productId).getSpecification_id());
		}
		List<ProductInfo> productInfos = productServiceImpl.selectProductInfoByProductId(Integer.parseInt(productId));
		List<String> describeList = Arrays.asList(productInfos.get(0).getProduct_describe().split(";"));
		if(Utils.judgEmpty(startPage)){
			startPage="1";
		}
		model.addAttribute("colorList", productSpecificationServiceImpl.selectColorByProductId(productId));
		model.addAttribute("specificationList", productSpecificationServiceImpl.selectSpecificationByProductId(productId));
		model.addAttribute("describeList",describeList);
		model.addAttribute("commentList",commentServiceImpl.showCommentByProductId(Integer.parseInt(productId),startPage,""));
		model.addAttribute("commentImageList",commentImageServiceImpl.selectCommentImageByProductId(Integer.parseInt(productId)));
		model.addAttribute("totalPage",commentServiceImpl.getTotalPageByProductId(Integer.parseInt(productId),""));
		return "WEB-INF/frontpages/productdetail.jsp";
	}
	@RequestMapping("sendComment")
    @ResponseBody
    public String sendComment(String commentContent,String commentTxt,String grade,String username,String productId,HttpServletRequest request){
		Users user=(Users)request.getSession().getAttribute("user");
		String result="";
		boolean buyStatus=false;
		List<String> specificationIdByProductId = productSpecificationServiceImpl.selectSpecificationIdByProductId(productId);
		List<String> specificationIdByPhone = ordersServiceImpl.selectSpecificationIdByPhone(user.getPhone());
		String specificationId="";
		for(int i=0;i<specificationIdByPhone.size();i++) {
			for(int j=0;j<specificationIdByProductId.size();j++) {
				System.out.println(specificationIdByPhone.get(i));
				System.out.println(specificationIdByProductId.get(j));
				if(specificationIdByPhone.get(i).length()>3) {
					String singleSpecification=specificationIdByPhone.get(i).substring(0, specificationIdByPhone.get(i).length()-1);
					List<String> specificationList=Arrays.asList(singleSpecification.split(","));
					for(int k=0;k<specificationList.size();k++) {
						if(specificationList.get(k).equals(specificationIdByProductId.get(j))) {
							buyStatus=true;
							specificationId=specificationList.get(k);
							ordersServiceImpl.updateOrderStatus(specificationId);
						}
					}
				}else {
					if(specificationIdByProductId.get(j).equals(specificationIdByPhone.get(i))) {
						specificationId=specificationIdByProductId.get(j);
						ordersServiceImpl.updateOrderStatus(specificationId);
						buyStatus=true;
					}
				}
			}
		}
		if(buyStatus) {
		    Pattern pattern= Pattern.compile("(<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>)");
	        Matcher matcher = pattern.matcher(commentContent);
	        List<String> fileNameList=new ArrayList<String>();
			int comment_id=commentServiceImpl.addComment(grade,username,productId,commentTxt);
	        while (matcher.find()) {
	            String img = matcher.group(2); //m_img.group(1) 为获得整个img标签  m_img.group(2) 为获得src的值
				String fileName=img.substring(img.lastIndexOf("/")+1,img.length());
				fileNameList.add(fileName);
	        }
	        int count=0;
			for (int i=0;i<fileNameList.size();i++){
					CommentImage commentImage=new CommentImage();
					commentImage.setComment_id(comment_id);
					commentImage.setComment_thumbnail(fileNameList.get(i));
					commentImage.setProduct_id(Integer.parseInt(productId));
					commentImageServiceImpl.addCommentImage(commentImage);
					count++;
			}
			if(count==fileNameList.size()){
				result="发布成功";
			}
		}else {
			result="您还未购买该商品或者还未确认收货或者超过评论期限";
		}
       return result;
    }
	@RequestMapping("getComment")
	@ResponseBody
	public Object getComment(String currentPage,String productId,String grade) {
		List<Comment> commentList = commentServiceImpl.showCommentByProductId(Integer.parseInt(productId), currentPage,grade);
		JSONArray json=JSONArray.fromObject(commentList);
		return json;
	}
	@RequestMapping("getCommentImage")
	@ResponseBody
	public Object getCommentImage(String productId) {
		List<CommentImage> commentImageList = commentImageServiceImpl.selectCommentImageByProductId(Integer.parseInt(productId));
		JSONArray json=JSONArray.fromObject(commentImageList);
		return json;
	}
	@RequestMapping("getPage")
	@ResponseBody
	public int getPage(String productId,String grade) {
		return commentServiceImpl.getTotalPageByProductId(Integer.parseInt(productId), grade);
	}
	@RequestMapping("addShoppingCart")
	@ResponseBody
	public String addShoppingCart(String productId,String color,String specification,String count,HttpServletResponse response,HttpServletRequest request) {
		shoppingCartServiceImpl.addShoppingCartNotUser(productId, color, specification, count, request, response);
		return "添加成功";
	}
	@RequestMapping("shoppingCartPage")
	public String shoppingCartPage(Model model,HttpServletRequest request) {
		shoppingCartServiceImpl.showCart(request,model);
		return "WEB-INF/frontpages/shoppingcart.jsp";
	}
	@RequestMapping("deleteCart")
	@ResponseBody
	public String deleteCart(String specificationId,HttpServletRequest request,HttpServletResponse response) {
		return shoppingCartServiceImpl.deleteShoppingCart(specificationId, request,response);
	}
	@RequestMapping("personInfo")
	public String personInfo(Model model,HttpServletRequest request) {
		Users user=(Users) request.getSession().getAttribute("user");
		List<Addressee> addressList=addressServiceImpl.showAddressById(request);
		model.addAttribute("addressList",addressList);
		model.addAttribute("user", user);
		return "WEB-INF/frontpages/personinfo.jsp";
	}
	@RequestMapping("confirmPay")
	public String confirmPay(Model model,String indentification,String count,String specificationId,HttpServletRequest request) {
		model.addAttribute("addressList", addressServiceImpl.showAddressById(request));
		productInfoServiceImpl.getProductInfoBySpecificationId(specificationId,indentification,count,model,request);
		
		return "WEB-INF/frontpages/orderinfo.jsp";
	}
/*	@RequestMapping("confirmPay")
	public String confirmPay(Model model,String specificationId,HttpServletRequest request) {
		model.addAttribute("addressList", addressServiceImpl.showAddressById(request));
		productInfoServiceImpl.getProductInfoBySpecificationId(specificationId,model,request);
		return "WEB-INF/frontpages/orderinfo.jsp";
	}*/
	@RequestMapping("updateUsername")
	@ResponseBody
	public String updateUsername(String username,HttpServletRequest request) {
		Users user=(Users)request.getSession().getAttribute("user");
		String updateStatus=usersServiceImpl.updateUsernameById(user.getId(), username);
		if(updateStatus=="修改成功") {
			user.setUsername(username);
			request.getSession().setAttribute("user", user);
		}
		return updateStatus;
	}
	@RequestMapping("updatePhone")
	@ResponseBody
	public String updatePhone(String phone,HttpServletRequest request) {
		Users user=(Users)request.getSession().getAttribute("user");
		String updateStatus=usersServiceImpl.updatePhoneById(user.getId(), phone);
		if(updateStatus=="修改成功") {
			user.setPhone(phone);
			request.getSession().setAttribute("user", user);
		}
		return updateStatus;
	}
	@RequestMapping("updateEmail")
	@ResponseBody
	public String updateEmail(String email,HttpServletRequest request) {
		if(email.contains("@")) {
			Users user=(Users)request.getSession().getAttribute("user");
			String updateStatus=usersServiceImpl.updateEmailById(user.getId(), email);
			if(updateStatus=="修改成功") {
				user.setEmail(email);
				request.getSession().setAttribute("user", user);
			}
			return updateStatus;
		}else {
			return " 邮箱格式错误";
		}
	}
	@RequestMapping("deleteAddress")
	@ResponseBody
	public String deleteAddress(int addressId,HttpServletRequest request) {
		return usersServiceImpl.deleteAddress(addressId);
	}
	@RequestMapping("addAddressee")
	@ResponseBody
	public String addAddressee(Addressee address,HttpServletRequest request) {
		return addressServiceImpl.addAddress(address, request);
	}
	
}
