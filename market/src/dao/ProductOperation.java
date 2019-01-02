package dao;

import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import javabean.ReceiverAddress;
import javabean.Report;
import javabean.SkuAttribute;
import javabean.Staff;
import javabean.buyCar;
import javabean.productCategory;

public interface ProductOperation {
	public abstract ArrayList<Product> findProductListByCondition(HashMap<String,String> findconditionmap,int currentPage);
	public abstract int insertProductAttribute(String product_name,int groupId,String product_introduction, double product_originalprice,double product_price,String product_state,int product_sort,String product_describe,String product_thumbnail,int product_number,int brand_id,String pinyin);
	public abstract int updateProductState(int product_id);
	public abstract String findProductState(int product_id);
	public abstract int updateProductAttribute(String product_name,String product_introduction, double product_originalprice,double product_price,String product_state,int product_sort,String product_describe,String product_thumbnail,int product_id,int product_number,int brand_id,String pinyin);
	public abstract ArrayList<Product> getProductAttributeListById(int product_id);
	public abstract int gettotalpages(HashMap<String, String> findconditionmap);
	public abstract ArrayList<Product> findProduct();
	public abstract Product findProductByID(int product_id);
	public abstract buyCar deleteProductByID(int product_id,buyCar buycar);
	public abstract Customer LoginVrify(String staff_username,String staff_password);
	public abstract boolean addCarProductToDatabase(Product product,int customerid,int productcount);
	public abstract buyCar findBuyCarByCustomerId(int customerid,buyCar buycar);
	public abstract boolean deleteProductFromDatabase(int product_id);
	public abstract ArrayList<Product> findProductByName(String product_name,String pinyin,int brandId);
	public abstract String transformStringToPinyin(String product_name);
	public abstract Map<Integer,String> findProductCategory();
	public abstract ArrayList<productCategory> findCategoryById(int category_id);
	public abstract ArrayList<Product> findProductByCategoryid(int categoryid);
	public abstract String getMessage(String phonenumber,String captcha);
	public abstract ArrayList<String> findCategoryName();
	public abstract ArrayList<Category> findCategory();
	public abstract boolean addNewCategory(String category_name);
	public abstract boolean addChildCategory(String childCategory_name,int categoryFatherId,int categoryLevel);
	public abstract ArrayList<Category> findChildCategoryById(int category_id);
	public abstract boolean deleteCategoryById(int category_id);
	public abstract boolean findCustomerPhoneNumber(String customer_phoneNumber);
	public abstract boolean addNewCustomer(Customer customer);
	public abstract ArrayList<Category> findChildCategoryByName(String categoryName);
	public abstract ArrayList<Category
	> findInitialSecondChildCategory(String category_name);
	public abstract ArrayList<Category> findInitialThirdChildCategory(String category_name);
	public abstract ArrayList<String> findBrandName();
	public abstract int findBrandIdByName(String brand_name);
	public abstract int findCategoryIdByName(String category_name);
	public abstract int findProductIdByName(String product_name);
	public abstract boolean addProductCategory(int product_id,int category_id,int category_level);
	public abstract String findProductCategoryByProductId(int product_id);
	public abstract int findProductIdByCategoryName(String category_name);
	public abstract ArrayList<Brand> findAllBrand();
	public abstract boolean addBrand(String brandName,String brandEnglishName);
	public abstract boolean deleteBrandById(int brandId);
	public abstract ArrayList<Product> findProductByBrandIdAndCategoryId(int brandId,int categoryId);
	public abstract boolean judgmentWhetherExistCategoryName(String categoryName);//public abstract ArrayList<Product> findProductByBrandIdAndProductName(int brandId,String productName);
	public abstract ArrayList<Product> findProductByCategoryNameAndBrandId(String categoryName,int brandId);
	public abstract boolean updateProductCategory(int productId,int caategoryId,int caategoryLevel);
	public abstract boolean addImage(int productId,String imageUrl,String imageCategory);
	public abstract ArrayList<Image> findAllImage(int currentPage);
	public abstract ArrayList<Image> findImageByProductId(int productId,int currentPage);
	public abstract int getTotalPages();
	public abstract Image getImageUrlById(int imageId);
	public abstract boolean updateImageUrlById(String imageUrl,int imageId,String imageCategory);
	public abstract boolean deleteImageOperationById(int imageId);
	public abstract boolean addBrandAndCategory(int brandId,int categoryId);
	//public abstract int findBrandIdByProductId(int productId);
	public abstract ArrayList<String> findCatalogNameByProductId(int productId);
	public abstract ArrayList<String> findCategoryNameByProductId(int productId);
	public abstract ArrayList<productCategory> findCategoryByProductId(int productId);
	public abstract ArrayList<String> findProductNameByCategoryId(int categoryId);
	public abstract ArrayList<Product> findProductByCategoryName(String categoryName);
	public abstract ArrayList<String> findShowImageUrlByProductIdAndImageCategory(int productId,String imageCategory);
	public abstract void addStaffOperationLog(Staff staff,OperationLog operationLog);
	public abstract int findImageIdByImageUrl(String imageUrl);
	public abstract boolean addAttributeByCategoryId(int categoryId,String attributeName);
	public abstract Map<Integer,String> findAttributeByCategoryId(int categoryId);
	public abstract void updateAttributeByAttributeId(int attributeId,String attributeName);
	public abstract boolean deleteAttributeByAttributeId(int attributeId);
	public abstract ArrayList<String> findCategoryAttributeByProductId(int productId);
	public abstract boolean addProductComment(int productId,int customerId,String customerName,String productContent,String commentLevel,String createTime,String updateTime);
	public abstract ArrayList<Comment> findCommentByProductId(int productId,int currentPage);
	public abstract String findCustomerNameByCustomerId(int customerId);
	public abstract Map<String,Integer> findCommentCountByCommentLevel(String commentLevel,Map<String,Integer> commentLevelMap,int productId);
	public abstract ArrayList<Comment> findCommentByLevel(String commentLevel,int currentPage,int productId);
	public abstract int getCommentTotalPages(int productId);
	public abstract int getCommentTotalPagesByCommentLevel(String commentLevel,int productId);
	//public abstract ArrayList<Comment> findComment(int currentPage,int productId);
	public abstract ArrayList<CategoryAttribute> findAttributeNameByCategoryId(int categoryId);
	public abstract int findCategoryIdByProductId(int productId);
	public abstract int findAttributeIdByName(String attributeName,int categoryId);
	//public abstract boolean addSkuAttribute(int productId,int attributeId,String attributeValue);
	public abstract List<SkuAttribute> findAttributeByProductId(int productId);
	public abstract List<String> findAttributeNameByProductId(int productId);
	public abstract Map<String, List<String>> findProductAttributeListByProductIdAndCategoryId(int categoryId,int product_id);
	public abstract ArrayList<Sensitive> getAllSensitive();
	public abstract boolean deleteSensitiveById(int sensitiveId);
	public abstract boolean addSensitive(String sensitiveValue);
	public abstract boolean addCommentImage(String imageSrc,int productId,int customerId,int commentId,String customerName);
	public abstract int findCommentIdByTimeAndCustomerId(int customerId,String createTime);
	public abstract ArrayList<String> findImageSrcByComentId(int commentId);
	public abstract ArrayList<Comment> findComment(int currentPage);
	public abstract int getCommentTotalPage(HashMap<String,String> conditionMap);
	public abstract ArrayList<CommentImage> findCommentImageByComentId(int commentId);
	public abstract boolean deleteCommentImageBycommentImageId(int commentId);
	public abstract ArrayList<Comment> findCommentByCondition(HashMap<String,String> conditionMap,int currentPage);
	public abstract boolean deleteCommentById(int commentId);
	public abstract ArrayList<String> findCategoryAttributeNameById(int categoryId);
	public abstract ArrayList<CategoryAttributeValue> findCategoryAttributeValueById(int attributeId);
	public abstract boolean addCategoryAttributeValue(int attributeId,String c_attr_value);
	public abstract ArrayList<String> findCategoryAttributeValueStringById(int attributeId);
	public abstract boolean deleteAttributeValueById(int attributeValueId);
	public abstract boolean addSkuAttribute(int skuId, int attributeId, int attributeValueId,int groupSign);
	public abstract int isExistProduct(String prouductName);
	public abstract boolean addReceiverAddress(int customerId,String receiverAddress,String phoneNumber,String receiverName,String email,String otherAddressName);
	public abstract ArrayList<ReceiverAddress> findReseiverAddressByCustomerId(int customerId);
	public abstract int findAttributeValueIdByattributeValue(int attributeId,String attributeValue);
	public abstract ArrayList<String> findAttributeValueByProductId(int productId,String attributeName);
	public abstract ArrayList<String> findProductSpecificationByProductId(int productId);
	public abstract int findMaxGroupId();
	public abstract int findProductIdByAttributeId(int oneattributeValueId, int anotherattributeValueId,int groupId);
	public abstract int findGroupIdByProductId(int productId);
	public abstract int findAttributeValueIdByCategoryIdAndAttributeValue(int categoryId,String attributeValue);
	public abstract String findCustomerPhoneByCustomerId(int customerId);
	public abstract boolean addOrderInformation(Order order);
	public abstract ArrayList<Order> findOrderListByCondition(HashMap<String,String> conditionMap,int currentPage);
	public abstract int getOrderTotalPage(HashMap<String,String> conditionMap);
	public abstract void updateDeliverGoodsStateByOrderNumber(String orderNumber);
	public abstract double findTotalPriceByOrderNumber(String orderNumber);
	public abstract Order findOrderByOrderNumber(String orderNumber);
	public abstract boolean updateOrderRefundStateByOrderNumber(String orderNumber);
	public abstract ArrayList<String> findReceiverInfoByOrderNumber(String orderNumber);
	public abstract boolean updateReveiverInfoByOrderNumber(String orderNumber,String receiverName,String receiverPhone,String receiverAddress);
	public abstract ArrayList<MyOrder> findOrderByCustomerId(int customerId,String timeDefference,String orderState);
	public abstract boolean confirmReceiptByOrderNumber(String orderNumber);
	public abstract boolean updateMyOrderStateToDeleteByOrderNumber(String orderNumber);
	public abstract boolean updateOrderAliPayNumberByOrderNumber(String orderNumber,String aliOrderNumber,String orderState);
	public abstract boolean judgmentWhetherComment(int productId,int customerId,String orderState);
	public abstract ArrayList<String> judgmentAattributeItem(String attributeValue,int productId);
	public abstract boolean addReport(Report report);
	public abstract ArrayList<Integer> findProductIdByOrderNumber(String orderNumber);
	public abstract ArrayList<Report> findReportByProductId(int proudctId);
	public abstract boolean updateMyOrderState(int productId,int customerId,String orderState,String orderNumber);
	public abstract boolean addRefundReson(String orderNumber,String refundReson,String createTime,String updateTime);
	public abstract boolean updateMyOrderRefundState(String orderState,String orderNumber);
	public abstract boolean cancelOrderByOrderNumber(String orderNumber);

}
