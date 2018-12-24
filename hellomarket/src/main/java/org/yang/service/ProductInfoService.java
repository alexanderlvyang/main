package org.yang.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface ProductInfoService {
	void getProductInfoBySpecificationId(String specificationId,String Identification,String count,Model model,HttpServletRequest request);
	double getTotalPriceBySpecficationId(String  specificationId,HttpServletRequest request);
}
