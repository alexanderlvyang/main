package org.yang.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yang.pojo.SensitiveWord;
import org.yang.service.SensitiveWordService;

@Controller
public class SensitiveWordController {
	@Resource
	private SensitiveWordService sensitiveWordServiceImpl;
	@RequestMapping("sensitiveManage.do")
	public String sensitiveManage(Model model,String condition,String startPage,HttpServletRequest request) {
		model.addAttribute("sensitiveList", sensitiveWordServiceImpl.showSensitiveWord(condition, startPage, request));
		model.addAttribute("totalPage",sensitiveWordServiceImpl.getTotalPage(condition));
		return "WEB-INF/pages/sensitivemanage.jsp";
	}
	@RequestMapping("addSensitiveWord.do")
	@ResponseBody
	public String addSensitiveWord(SensitiveWord sensitiveWord,HttpServletRequest request) {
		return sensitiveWordServiceImpl.addSensitiveWord(sensitiveWord, request);
	}
	@RequestMapping("deleteSensitiveWord.do")
	@ResponseBody
	public String deleteSensitiveWord(int sensitive_id,HttpServletRequest request) {
		return sensitiveWordServiceImpl.deleteSensitiveWord(sensitive_id, request);
	}
	
}
