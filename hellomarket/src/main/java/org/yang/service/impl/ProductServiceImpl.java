package org.yang.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.yang.mapper.BrandMapper;
import org.yang.mapper.CategoryMapper;
import org.yang.mapper.OperationLogsMapper;
import org.yang.mapper.ProductMapper;
import org.yang.mapper.ProductSpecificationMapper;
import org.yang.pojo.Admin;
import org.yang.pojo.Brand;
import org.yang.pojo.Category;
import org.yang.pojo.OperationLogs;
import org.yang.pojo.Product;
import org.yang.pojo.ProductInfo;
import org.yang.service.ProductService;
import org.yang.utils.Utils;
@Service
public class ProductServiceImpl implements ProductService {
	@Resource
	private ProductMapper productMapper;
	@Resource
	private OperationLogsMapper operationLogsMapper;
	@Resource
	private ProductSpecificationMapper productSpecificationMapper;
	@Resource
	private CategoryMapper categoryMapper;
	@Resource
	private	BrandMapper brandMapper;
	@Override
	public List<Product> showProduct(String condition, String startPage, int limit,HttpServletRequest request) {
		List<Product> productList=null;
		if(startPage==null||startPage.equals("")) {
			startPage="1";
		}
		if(condition==null||condition.equals("")) {
			request.getSession().removeAttribute("condition");
			productList=productMapper.selectAllProductByPage((Integer.parseInt(startPage)-1)*limit, limit);
		}else {
			condition = Utils.judgeChiese(condition);
			request.getSession().setAttribute("condition", condition);
			productList=productMapper.selectAllproductByPageAndCondition(condition, (Integer.parseInt(startPage)-1)*limit, limit);
		}
		for (int i=0;i<productList.size();i++){
			if(productList.get(i).getProduct_describe().length()>15){
				productList.get(i).setProduct_describe(productList.get(i).getProduct_describe().substring(0,15)+"...");
			}
			if(productList.get(i).getProduct_introduction().length()>15){
				productList.get(i).setProduct_introduction(productList.get(i).getProduct_introduction().substring(0,15)+"...");
			}
		}
		return productList;
	}

	@Override
	public int getTotalPage(String condition,int limit) {
		int count=0;
		if(condition==null||condition.equals("")) {
			count=productMapper.selectCount();
		}else {
			condition=Utils.judgeChiese(condition);
			count=productMapper.selectCountByCondition(condition);
		}
		int totalPage=(int)Math.ceil(count/(limit*1.0));
		return totalPage;
	}

	@Override
	public String addProduct(Product product,HttpServletRequest request) {
		Admin admin=(Admin)request.getSession().getAttribute("admin");
		String currentTime=Utils.getCurrentTime();
		String content="添加了id为"+product.getProduct_name()+"的商品";
		product.setProduct_joinTime(currentTime);
		product.setUpdateTime(currentTime);
		product.setProduct_status("上架");
		product.setProduct_price("");
		product.setPinyin(Utils.getPinYin(product.getProduct_name()));
		int addStatus=productMapper.insertProduct(product);
		if(addStatus==1) {
			OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "添加成功", admin.getUsername());
			operationLogsMapper.insertOperation(operationLogs);
			return "添加成功";
		}else {
			OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "添加失败", admin.getUsername());
			operationLogsMapper.insertOperation(operationLogs);
			return "添加失败";
		}
	}

	@Override
	public String updateStatus(int product_id, String product_status,HttpServletRequest request) {
		Admin admin=(Admin)request.getSession().getAttribute("admin");	
		String currentTime=Utils.getCurrentTime();
		String content="更新id为"+product_id+"的商品状态为"+product_status;
		int updateStatus = productMapper.updateStatus(product_id, product_status);
		if(updateStatus>=1) {
			OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "更新成功", admin.getUsername());
			operationLogsMapper.insertOperation(operationLogs);
			return "更新成功";
		}else {
			OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "更新失败", admin.getUsername());
			operationLogsMapper.insertOperation(operationLogs);
			return "更新失败";
		}
	}

	@Override
	public Product showProductById(int product_id) {
		return productMapper.selectProductById(product_id);
	}

	@Override
	public String updateProductById(Product product,HttpServletRequest request) {
		Admin admin=(Admin)request.getSession().getAttribute("admin");
		int updateStatus = productMapper.updateProductById(product);
		String currentTime=Utils.getCurrentTime();
		String content="更新了id为"+product.getProduct_id()+"的商品";
		if(updateStatus>0) {
			OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "更新成功", admin.getUsername());
			operationLogsMapper.insertOperation(operationLogs);
			return "更新成功";
		}else {
			OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "更新失败", admin.getUsername());
			operationLogsMapper.insertOperation(operationLogs);
			return "更新失败";
		}
	}

	@Override
	public List<ProductInfo> searchProduct(String condition,String startPage,HttpServletRequest request) {
		condition=Utils.judgeChiese(condition);
		int categoryId=0;
		int brandId=0;
		int limit=40;
		List<ProductInfo> productInfoList = null;
		condition=Utils.judgeChiese(condition);
		if(Utils.judgEmpty(startPage)) {
			startPage="1";
		}
		Category categoryObj = categoryMapper.selectCategoryByName(condition);
		Brand brandObj = brandMapper.selectBrandByName(condition);
		if(brandObj!=null) {
			request.getSession().removeAttribute("condition");
			brandId=brandObj.getBrand_id();
			productInfoList=productMapper.selectProductInfo(condition, categoryId, brandId,(Integer.parseInt(startPage)-1)*limit,limit);
		}
		if(categoryObj!=null) {
			request.getSession().removeAttribute("condition");
			categoryId=categoryObj.getId();
			if(categoryObj.getGrade()==2) {
				productInfoList=productMapper.selectProductInfo(condition, categoryId, brandId,(Integer.parseInt(startPage)-1)*limit,limit);
			}
			if(categoryObj.getGrade()==1) {
				productInfoList=productMapper.selectProductInfoBySecondCategory(condition, categoryObj.getName(), brandId, (Integer.parseInt(startPage)-1)*limit, limit);
			}
			if(categoryObj.getGrade()==0) {
				productInfoList=productMapper.selectProductInfoByFirstCategory(condition, categoryObj.getName(), brandId, (Integer.parseInt(startPage)-1)*limit, limit);
			}
		}
		if(brandObj==null&&categoryObj==null) {
			request.getSession().setAttribute("condition", condition);
			productInfoList=productMapper.selectProductInfo(condition, categoryId, brandId,(Integer.parseInt(startPage)-1)*limit,limit);
		}
		if(condition.equals("")&&categoryId==0&&brandId==0) {
			productInfoList.removeAll(productInfoList);
			return productInfoList;
		}else {
			return productInfoList;
		}
	}

	@Override
	public int getProductInfoTotalPage(String condition) {
		int totalPage=0;
		int categoryId=0;
		int brandId=0;
		int limit=40;
		int count=0;
		condition=Utils.judgeChiese(condition);
		Category categoryObj = categoryMapper.selectCategoryByName(condition);
		Brand brandObj = brandMapper.selectBrandByName(condition);
		if(categoryObj!=null) {
			if(categoryObj.getGrade()==2) {
				categoryId=categoryObj.getId();
				count=productMapper.selectProductInfoCount(condition, categoryId, brandId);
			}
			if(categoryObj.getGrade()==1) {
				count=productMapper.selectProductInfoCountBySecondCategory(condition, categoryObj.getName(), brandId);
			}
			if(categoryObj.getGrade()==0) {
				count=productMapper.selectProductInfoCountByFirstCategory(condition, categoryObj.getName(), brandId);
			}
		}
		if(brandObj!=null) {
			brandId=brandObj.getBrand_id();
			count=productMapper.selectProductInfoCount(condition, categoryId, brandId);
		}
		if(brandObj==null&&categoryObj==null) {
			count=productMapper.selectProductInfoCount(condition, categoryId, brandId);
		}
		totalPage=(int)Math.ceil(count/(limit*1.0));
		return totalPage;
	}

	@Override
	public List<ProductInfo> selectProductInfoByProductId(int productId) {
		return productMapper.selectProductInfoByProductId(productId);
	}

	@Override
	public List<ProductInfo> selectProductInfoBySpecification(String specification) {
		return productMapper.selectProductInfoBySpecification(specification);
	}

	@Override
	public ProductInfo selectProductInfoByProductIdAndSpecificationAndColor(String productId, String color,
			String specification) {
		return productMapper.selectProductInfoByProductIdAndSpecificationAndColor(Integer.parseInt(productId), specification, color);
	}

	@Override
	public ProductInfo selectProductInfoByProductIdAndSpecification(String productId, String specification) {
		return  productMapper.selectProductInfoByProductIdAndSpecification(Integer.parseInt(productId), specification);
	}
}
