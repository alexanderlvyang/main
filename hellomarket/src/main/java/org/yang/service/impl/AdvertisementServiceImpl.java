package org.yang.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.yang.mapper.AdvertisementMapper;
import org.yang.mapper.OperationLogsMapper;
import org.yang.pojo.Admin;
import org.yang.pojo.Advertisement;
import org.yang.pojo.OperationLogs;
import org.yang.service.AdvertisementService;
import org.yang.utils.Utils;
@Service
public class AdvertisementServiceImpl implements AdvertisementService {
	@Resource
	private AdvertisementMapper advertisementMapper;
	@Resource
	private OperationLogsMapper operationLogsMapper;
	@Override
	public List<Advertisement> showAdvertisementByType(String advertise_type,HttpServletRequest request) {
		if(Utils.judgEmpty(advertise_type)) {
			advertise_type="图片";
		}
		advertise_type = Utils.judgeChiese(advertise_type);
		request.getSession().setAttribute("advertise_type", advertise_type);
		return advertisementMapper.selectAdvertisementBytype(advertise_type);
	}

	@Override
	public String addAdvertisement(Advertisement advertisement, HttpServletRequest request) {
		String result="";
		if(advertisement.getAdvertise_range().equals("所有栏目")&&advertisement.getAdvertise_type().equals("图片")) {
			int count = advertisementMapper.selectCountByRangeAndType(advertisement.getAdvertise_range(), advertisement.getAdvertise_type());
			int shouCount =count+ advertisementMapper.selectCountByRangeAndType("首页", advertisement.getAdvertise_type());
			int sousuoCount =count+ advertisementMapper.selectCountByRangeAndType("搜索页", advertisement.getAdvertise_type());
			if(shouCount>=3||sousuoCount>=5) {
				result="此范围此分类的广告已满";
			}else {
				Admin admin=(Admin)request.getSession().getAttribute("admin");
				String currentTime=Utils.getCurrentTime();
				String content="添加了id为"+advertisement.getAdvertise_id()+"的广告";
				advertisement.setCreateTime(currentTime);
				advertisement.setUpdateTime(currentTime);
				int insertStatus = advertisementMapper.insertAdvertisement(advertisement);
				if(insertStatus==1) {
					OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "添加成功", admin.getUsername());
					operationLogsMapper.insertOperation(operationLogs);
					result="添加成功";
				}else {
					OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "添加失败", admin.getUsername());
					operationLogsMapper.insertOperation(operationLogs);
					result="添加失败";
				}
			}
		}
		if(advertisement.getAdvertise_range().equals("所有栏目")&&advertisement.getAdvertise_type().equals("文本")) {
			int count = advertisementMapper.selectCountByRangeAndType(advertisement.getAdvertise_range(), advertisement.getAdvertise_type());
			int shouCount =count+ advertisementMapper.selectCountByRangeAndType("首页", advertisement.getAdvertise_type());
			int sousuoCount =count+ advertisementMapper.selectCountByRangeAndType("搜索页", advertisement.getAdvertise_type());
			if(shouCount>=5||sousuoCount>=5) {
				result="此范围此分类的广告已满";
			}else {
				Admin admin=(Admin)request.getSession().getAttribute("admin");
				String currentTime=Utils.getCurrentTime();
				String content="添加了id为"+advertisement.getAdvertise_id()+"的广告";
				advertisement.setCreateTime(currentTime);
				advertisement.setUpdateTime(currentTime);
				int insertStatus = advertisementMapper.insertAdvertisement(advertisement);
				if(insertStatus==1) {
					OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "添加成功", admin.getUsername());
					operationLogsMapper.insertOperation(operationLogs);
					result="添加成功";
				}else {
					OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "添加失败", admin.getUsername());
					operationLogsMapper.insertOperation(operationLogs);
					result="添加失败";
				}
			}
		}
		if(advertisement.getAdvertise_range().equals("首页")&&advertisement.getAdvertise_type().equals("图片")) {
			int count = advertisementMapper.selectCountByRangeAndType(advertisement.getAdvertise_range(), advertisement.getAdvertise_type());
			count += advertisementMapper.selectCountByRangeAndType("所有栏目", advertisement.getAdvertise_type());
			count += advertisementMapper.selectCountByRangeAndType("搜索页", advertisement.getAdvertise_type());
			if(count>=3) {
				result="此范围此分类的广告已满";
			}else {
				Admin admin=(Admin)request.getSession().getAttribute("admin");
				String currentTime=Utils.getCurrentTime();
				String content="添加了id为"+advertisement.getAdvertise_id()+"的广告";
				advertisement.setCreateTime(currentTime);
				advertisement.setUpdateTime(currentTime);
				int insertStatus = advertisementMapper.insertAdvertisement(advertisement);
				if(insertStatus==1) {
					OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "添加成功", admin.getUsername());
					operationLogsMapper.insertOperation(operationLogs);
					result="添加成功";
				}else {
					OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "添加失败", admin.getUsername());
					operationLogsMapper.insertOperation(operationLogs);
					result="添加失败";
				}
			}
		}
		if(advertisement.getAdvertise_range().equals("首页")&&advertisement.getAdvertise_type().equals("文本")) {
			int count = advertisementMapper.selectCountByRangeAndType(advertisement.getAdvertise_range(), advertisement.getAdvertise_type());
			count += advertisementMapper.selectCountByRangeAndType("所有栏目", advertisement.getAdvertise_type());
			count += advertisementMapper.selectCountByRangeAndType("搜索页", advertisement.getAdvertise_type());
			if(count>=5) {
				result="此范围此分类的广告已满";
			}else {
				Admin admin=(Admin)request.getSession().getAttribute("admin");
				String currentTime=Utils.getCurrentTime();
				String content="添加了id为"+advertisement.getAdvertise_id()+"的广告";
				advertisement.setCreateTime(currentTime);
				advertisement.setUpdateTime(currentTime);
				int insertStatus = advertisementMapper.insertAdvertisement(advertisement);
				if(insertStatus==1) {
					OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "添加成功", admin.getUsername());
					operationLogsMapper.insertOperation(operationLogs);
					result="添加成功";
				}else {
					OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "添加失败", admin.getUsername());
					operationLogsMapper.insertOperation(operationLogs);
					result="添加失败";
				}
			}
		}
		if(advertisement.getAdvertise_range().equals("搜索页")&&advertisement.getAdvertise_type().equals("图片")) {
			int count = advertisementMapper.selectCountByRangeAndType(advertisement.getAdvertise_range(), advertisement.getAdvertise_type());
			count += advertisementMapper.selectCountByRangeAndType("所有栏目", advertisement.getAdvertise_type());
			count += advertisementMapper.selectCountByRangeAndType("首页", advertisement.getAdvertise_type());
			if(count>=5) {
				result="此范围此分类的广告已满";
			}else {
				Admin admin=(Admin)request.getSession().getAttribute("admin");
				String currentTime=Utils.getCurrentTime();
				String content="添加了id为"+advertisement.getAdvertise_id()+"的广告";
				advertisement.setCreateTime(currentTime);
				advertisement.setUpdateTime(currentTime);
				int insertStatus = advertisementMapper.insertAdvertisement(advertisement);
				if(insertStatus==1) {
					OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "添加成功", admin.getUsername());
					operationLogsMapper.insertOperation(operationLogs);
					result="添加成功";
				}else {
					OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "添加失败", admin.getUsername());
					operationLogsMapper.insertOperation(operationLogs);
					result="添加失败";
				}
			}
		}
		if(advertisement.getAdvertise_range().equals("搜索页")&&advertisement.getAdvertise_type().equals("文本")) {
			int count = advertisementMapper.selectCountByRangeAndType(advertisement.getAdvertise_range(), advertisement.getAdvertise_type());
			count += advertisementMapper.selectCountByRangeAndType("所有栏目", advertisement.getAdvertise_type());
			count += advertisementMapper.selectCountByRangeAndType("首页", advertisement.getAdvertise_type());
			if(count>=5) {
				result="此范围此分类的广告已满";
			}else{
				Admin admin=(Admin)request.getSession().getAttribute("admin");
				String currentTime=Utils.getCurrentTime();
				String content="添加了id为"+advertisement.getAdvertise_id()+"的广告";
				advertisement.setCreateTime(currentTime);
				advertisement.setUpdateTime(currentTime);
				int insertStatus = advertisementMapper.insertAdvertisement(advertisement);
				if(insertStatus==1) {
					OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "添加成功", admin.getUsername());
					operationLogsMapper.insertOperation(operationLogs);
					result="添加成功";
				}else {
					OperationLogs operationLogs = Utils.operationLogs(currentTime, content, "添加失败", admin.getUsername());
					operationLogsMapper.insertOperation(operationLogs);
					result="添加失败";
				}
			}
		}
		
		return result;
	}

	@Override
	public String updateAadvertisementById(Advertisement advertisement, HttpServletRequest request) {
		Admin admin=(Admin)request.getSession().getAttribute("admin");
		String currentTime=Utils.getCurrentTime();
		String content="修改了id为"+advertisement.getAdvertise_id()+"的广告";
		advertisement.setUpdateTime(currentTime);
		int updateStatus = advertisementMapper.updateAdvertisementById(advertisement);
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
	public String deleteAdvertisementById(int advertise_id, HttpServletRequest request) {
		Admin admin=(Admin)request.getSession().getAttribute("admin");
		String currentTime=Utils.getCurrentTime();
		String content="删除了id为"+advertise_id+"的广告";
		int deleteStatus = advertisementMapper.deleteAdvertisementById(advertise_id);
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
	public Advertisement showAdvertisementById(int advertise_id) {
		return advertisementMapper.selectAdvertisementById(advertise_id);
	}

	@Override
	public List<Advertisement> showAdvertiseByRangeAndTime(String advertise_range, String currentTime,String type) {
		return advertisementMapper.selectAdvertisementByRangeAndTime(advertise_range, currentTime,type);
	}

}
