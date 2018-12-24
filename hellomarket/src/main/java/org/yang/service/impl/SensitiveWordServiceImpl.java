package org.yang.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.yang.mapper.OperationLogsMapper;
import org.yang.mapper.SensitiveWordMapper;
import org.yang.pojo.Admin;
import org.yang.pojo.OperationLogs;
import org.yang.pojo.SensitiveWord;
import org.yang.service.SensitiveWordService;
import org.yang.utils.Utils;
@Service
public class SensitiveWordServiceImpl implements SensitiveWordService {
	@Resource
	private SensitiveWordMapper sensitiveWordMapper;
	@Resource
	private OperationLogsMapper operationLogsMapper;
	private int limit =20;
	@Override
	public List<SensitiveWord> showSensitiveWord(String condition, String startPage,HttpServletRequest request) {
		List<SensitiveWord> sensitiveWordList;
		if(Utils.judgEmpty(startPage)) {
			startPage="1";
		}
		if(Utils.judgEmpty(condition)) {
			request.getSession().removeAttribute("condition");
			sensitiveWordList=sensitiveWordMapper.selectSensitive((Integer.parseInt(startPage)-1)*limit, limit);
		}else {
			condition=Utils.judgeChiese(condition);
			request.getSession().setAttribute("condition", condition);
			condition="%"+condition+"%";
			sensitiveWordList=sensitiveWordMapper.selectSensitiveByCondition(condition,(Integer.parseInt(startPage)-1)*limit, limit);
		}
		return sensitiveWordList;
	}

	@Override
	public String deleteSensitiveWord(int sensitive_id, HttpServletRequest request) {
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		String currentTime=Utils.getCurrentTime();
		String content="删除了id为"+sensitive_id+"敏感词";
		int deleteStatus = sensitiveWordMapper.deleteSensitive(sensitive_id);
		if(deleteStatus==1) {
			OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "删除成功", admin.getUsername());
			operationLogsMapper.insertOperation(operationLogs);
			return "删除成功";
		}else {
			OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "删除失败", admin.getUsername());
			operationLogsMapper.insertOperation(operationLogs);
			return "删除失败";
		}
	}

	@Override
	public int getTotalPage(String condition) {
		int count=0;
		if(Utils.judgEmpty(condition)) {
			count=sensitiveWordMapper.selectCount();
		}else {
			condition=Utils.judgeChiese(condition);
			count=sensitiveWordMapper.selectCountByCondition(condition);
		}
		int totalPage=(int) Math.ceil(count/(limit*1.0));
		return totalPage;
	}

	@Override
	public String addSensitiveWord(SensitiveWord sensitiveWord, HttpServletRequest request) {
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		String currentTime=Utils.getCurrentTime();
		sensitiveWord.setCreateTime(currentTime);
		List<String> contentList;
		int insertStatus=0;
		if(sensitiveWord.getSensitive_content().indexOf(";")!=-1){
			contentList=Arrays.asList(sensitiveWord.getSensitive_content().split(";"));
			for (int i = 0; i < contentList.size(); i++) {
				String content="添加了"+contentList.get(i)+"敏感词";
				sensitiveWord.setSensitive_content(contentList.get(i));
				insertStatus = sensitiveWordMapper.insertSensitive(sensitiveWord);
				if(insertStatus==1) {
					OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "添加成功", admin.getUsername());
					operationLogsMapper.insertOperation(operationLogs);
				}else {
					OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "添加失败", admin.getUsername());
					operationLogsMapper.insertOperation(operationLogs);
				}
			}
		}else {
		if(sensitiveWord.getSensitive_content().indexOf("；")!=-1) {
			contentList=Arrays.asList(sensitiveWord.getSensitive_content().split("；"));
			for (int i = 0; i < contentList.size(); i++) {
				String content="添加了"+contentList.get(i)+"敏感词";
				sensitiveWord.setSensitive_content(contentList.get(i));
				insertStatus = sensitiveWordMapper.insertSensitive(sensitiveWord);
				if(insertStatus==1) {
					OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "添加成功", admin.getUsername());
					operationLogsMapper.insertOperation(operationLogs);
				}else {
					OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "添加失败", admin.getUsername());
					operationLogsMapper.insertOperation(operationLogs);
				}
			}
		}else {
			if(sensitiveWord.getSensitive_content().indexOf("；")==-1&&sensitiveWord.getSensitive_content().indexOf(";")==-1) {
				insertStatus = sensitiveWordMapper.insertSensitive(sensitiveWord);
				String content="添加了"+sensitiveWord.getSensitive_content()+"敏感词";
				if(insertStatus==1) {
					OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "添加成功", admin.getUsername());
					operationLogsMapper.insertOperation(operationLogs);
				}else {
					OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "添加失败", admin.getUsername());
					operationLogsMapper.insertOperation(operationLogs);
				}
			}
		}
		
		}
		if(insertStatus==1) {
			return "添加成功";
		}else {
			return "添加失败";
		}
	}
	public Set<String> getSensitiveWordSet() {
		Set<String> sensitiveWordSet = sensitiveWordMapper.selectSensitiveWord();
		return sensitiveWordSet;
	}
	
}
