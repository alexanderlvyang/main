package org.yang.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.yang.pojo.Orders;
import org.yang.service.OrdersService;
import org.yang.service.ProductInfoService;
import org.yang.utils.Utils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;

@Controller
public class PayController {
	@Resource
	private ProductInfoService productInfoServiceImpl;
	@Resource
	private OrdersService ordersServiceImpl;
	@RequestMapping("alipayQuery")
	public String alipayQuery(HttpServletRequest request) {
		// 获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
				AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
				AlipayConfig.sign_type);

		// 设置请求参数
		AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();

		// 请求
		String result = null;
		try {
			// 商户订单号，商户网站订单系统中唯一订单号
			String out_trade_no = new String(request.getParameter("WIDTQout_trade_no").getBytes("ISO-8859-1"), "UTF-8");
			// 支付宝交易号
			String trade_no = new String(request.getParameter("WIDTQtrade_no").getBytes("ISO-8859-1"), "UTF-8");
			// 请二选一设置

			alipayRequest.setBizContent(
					"{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"trade_no\":\"" + trade_no + "\"}");

			result = alipayClient.execute(alipayRequest).getBody();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("result", result);
		return "WEB-INF/pages/alipay.trade.query.jsp";
	}

	@RequestMapping("alipayPay")
	public String alipayPay(String payMethod,String address,String specificationIdArray,HttpServletRequest request) {
		payMethod=Utils.judgeChiese(payMethod);
		if(payMethod.equals("支付宝支付")) {
			double total_amount=0.0f;
			Orders order=new Orders();
			order.setCreateTime(Utils.getCurrentTime());
			order.setAddressee_id(Integer.parseInt(address));
			order.setOrder_status("已下单");
			Date date=new Date();
			long out_trade_no=date.getTime();
			order.setOrder_id(out_trade_no);
			order.setSpecification_id(specificationIdArray);
			total_amount= productInfoServiceImpl.getTotalPriceBySpecficationId(specificationIdArray, request);
			order.setOrder_totalPrice(total_amount);
			order.setUpdateTime(Utils.getCurrentTime());
			ordersServiceImpl.addOrders(order);
			String subject="jdmarket";
			String body="";
			// 获得初始化的AlipayClient
			AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
					AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
					AlipayConfig.sign_type);
			
			// 设置请求参数
			AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
			alipayRequest.setReturnUrl(AlipayConfig.return_url);
			alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
			
			/*// 商户订单号，商户网站订单系统中唯一订单号，必填
			String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"), "UTF-8");
			// 付款金额，必填
			String total_amount = new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"), "UTF-8");
			// 订单名称，必填
			String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"), "UTF-8");
			// 商品描述，可空
			String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"), "UTF-8");
			*/
			alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\""
					+ total_amount + "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
					+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
			
			// 若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
			// alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
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
		}
		return "WEB-INF/pages/alipay.trade.page.pay.jsp";
	}
}
