package org.yang.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.yang.mapper.OperationLogsMapper;
import org.yang.pojo.OperationLogs;
import org.yang.service.OperationLogsService;
@Service
public class OperationLogsServiceImpl implements OperationLogsService {
	@Resource
	private OperationLogsMapper operationLogsMapper;
	@Override
	public int addOperationLogs(OperationLogs operationLogs) {
		return operationLogsMapper.insertOperation(operationLogs);
	}

}
