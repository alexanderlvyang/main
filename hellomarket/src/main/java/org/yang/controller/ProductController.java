package org.yang.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.yang.pojo.Brand;
import org.yang.pojo.Category;
import org.yang.pojo.Product;
import org.yang.pojo.ProductImage;
import org.yang.pojo.ProductSpecification;
import org.yang.service.BrandService;
import org.yang.service.CategoryService;
import org.yang.service.ProductImageService;
import org.yang.service.ProductService;
import org.yang.service.ProductSpecificationService;

import net.coobird.thumbnailator.Thumbnails;

@Controller
public class ProductController {
	@Resource
	private ProductService productServiceImpl;
	@Resource
	private CategoryService categoryServiceImpl;
	@Resource
	private BrandService brandServiceImpl;
	@Resource
	private ProductSpecificationService productSpecificationServiceImpl;
	@Resource
	private ProductImageService productImageServiceImpl;

	@RequestMapping("productManage.do")
	public String productManage(Model model, String condition, String startPage, HttpServletRequest request) {
		int limit = 15;
		List<Product> productList = productServiceImpl.showProduct(condition, startPage, limit, request);
		int totalPage = productServiceImpl.getTotalPage(condition, limit);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("productList", productList);
		return "WEB-INF/pages/productmanage.jsp";
	}

	@RequestMapping("addProduct.do")
	public String addProduct(Model model, HttpServletRequest request) {
		List<Category> categoryList = categoryServiceImpl.showCategory("2");
		List<Brand> brandList = brandServiceImpl.showBrandByPageAndCondition(null, null, 0, request);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("brandList", brandList);
		return "WEB-INF/pages/addproduct.jsp";
	}

	@RequestMapping("updateProductStatus.do")
	@ResponseBody
	public String updateProductStatus(int productId, String productStatus, HttpServletRequest request) {
		return productServiceImpl.updateStatus(productId, productStatus, request);
	}

	@RequestMapping("fileUpload.do")
	@ResponseBody
	public String fileUpload(MultipartFile file, HttpServletRequest request,String identification) {
		String path = request.getSession().getServletContext().getRealPath("/");
		path += "files\\";
		String filename = UUID.randomUUID() + file.getOriginalFilename();
		if (file.getSize() / 1024 > 1024) {
			return "文件过大";
		} else {
			try {
				if(identification.equals("lunbo")) {
					Thumbnails.of(file.getInputStream()).size(800,400).keepAspectRatio(false).toFile(new File(path + filename));
				}/*else
				if(identification.equals("sku")||identification.equals("specification")) {
					Thumbnails.of(file.getInputStream()).size(800,1200).toFile(new File(path + filename));*/
				else{
					Thumbnails.of(file.getInputStream()).size(800,1200).toFile(new File(path + filename));
				}
				/*Image image = ImageIO.read(file.getInputStream());*/
			/*	BufferedImage bufferImage = new BufferedImage(750, 1097, BufferedImage.TYPE_INT_RGB);
				bufferImage.getGraphics().drawImage(image, 0, 0, 750, 1097, null);*/
			/*	BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(path + filename));
				ImageIO.write(bufferImage, "PNG", outputStream);*/
			/*	outputStream.close();*/
				return filename;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "上传失败";
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "上传失败";
			}
			// 创建要保存文件的路径
			/*
			 * File dirFile = new File(path,filename); if (!dirFile.exists()){
			 * dirFile.mkdirs(); } try { //将文件写入创建的路径 file.transferTo(dirFile); return
			 * filename; } catch (IOException e) { e.printStackTrace(); return "上传失败"; }
			 */
		}
	}

	@RequestMapping("submitProduct.do")
	@ResponseBody
	public String submitProduct(Product product, String productCategory, String productBrand,
			HttpServletRequest request) {
		Category category = categoryServiceImpl.selectCategoryByName(productCategory);
		Brand brand = brandServiceImpl.selectBrandByName(productBrand);
		product.setProduct_brand(brand.getBrand_id());
		String newDescribe = "";
		product.setProduct_introduction(product.getProduct_introduction().trim());
		if (product.getProduct_describe().contains("；") || product.getProduct_describe().contains("：")) {
			newDescribe = product.getProduct_describe().replaceAll("；", ";");
			newDescribe = newDescribe.replaceAll("：", ":");
			product.setProduct_describe(newDescribe);
		}
		product.setProduct_category(category.getId());
		return productServiceImpl.addProduct(product, request);
	}

	@RequestMapping("showSpecification.do")
	public String showSpecification(Model model, String product_id,String productColor, HttpServletRequest request) {
		List<ProductSpecification> productSpecificationList = productSpecificationServiceImpl
				.showSpecificationByProductId(Integer.parseInt(product_id),productColor);
		request.getSession().setAttribute("productId", product_id);
		model.addAttribute("productSpecificationList", productSpecificationList);
		return "WEB-INF/pages/specification.jsp";
	}

	@RequestMapping("addSpecification.do")
	@ResponseBody
	public String addSpecification(ProductSpecification productSpecification, HttpServletRequest request) {
		return productSpecificationServiceImpl.addSpecification(productSpecification, request);
	}

	@RequestMapping("updateSpecificationStatus.do")
	@ResponseBody
	public String updateSpecificationStatus(String status, int specification_id, HttpServletRequest request) {
		return productSpecificationServiceImpl.updateSpecificationStatus(status, specification_id, request);
	}

	@RequestMapping("showProductById.do")
	public String showProductById(Model model, int product_id) {
		model.addAttribute("product", productServiceImpl.showProductById(product_id));
		return "WEB-INF/pages/updateproduct.jsp";
	}

	@RequestMapping("updateProduct.do")
	@ResponseBody
	public String updateProduct(Product product, HttpServletRequest request) {
		product.setProduct_introduction(product.getProduct_introduction().trim());
		String newDescribe = "";
		if (product.getProduct_describe().contains("；") || product.getProduct_describe().contains("：")) {
			newDescribe = product.getProduct_describe().replaceAll("；", ";");
			newDescribe = newDescribe.replaceAll("：", ":");
			product.setProduct_describe(newDescribe);
		}
		return productServiceImpl.updateProductById(product, request);
	}

	@RequestMapping("updateSpecification.do")
	public String updateSpecification(Model model, int specification_id) {
		model.addAttribute("specification",
				productSpecificationServiceImpl.selectSpecificationBySpecificationId(specification_id));
		return "WEB-INF/pages/updatespecification.jsp";
	}

	@RequestMapping("updateProductionSpecification.do")
	@ResponseBody
	public String updateSpecification(ProductSpecification productSpecification, HttpServletRequest request) {
		return productSpecificationServiceImpl.updateSpecification(productSpecification, request);
	}

	@RequestMapping("showProductImage.do")
	public String showProductImage(Model model, int product_id) {
		model.addAttribute("productImageList", productImageServiceImpl.showProductImage(product_id));
		model.addAttribute("product_id", product_id);
		return "WEB-INF/pages/productimage.jsp";
	}

	@RequestMapping("addProductImage.do")
	@ResponseBody
	public String addProductImage(ProductImage productImage, HttpServletRequest request) {
		return productImageServiceImpl.addProductImage(productImage, request);
	}

	@RequestMapping("deleteProductImage.do")
	@ResponseBody
	public String deleteProductImage(int productImage_id, HttpServletRequest request) {
		return productImageServiceImpl.deleteProductImageById(productImage_id, request);
	}
}
