package org.yang.service;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.yang.pojo.SensitiveWord;

public interface SensitiveWordService {
	List<SensitiveWord> showSensitiveWord(String condition,String startPage,HttpServletRequest request);
	String deleteSensitiveWord(int sensitive_id,HttpServletRequest request);
	int getTotalPage(String condition);
	String addSensitiveWord(SensitiveWord sensitiveWord,HttpServletRequest request);
	Set<String> getSensitiveWordSet();
}
