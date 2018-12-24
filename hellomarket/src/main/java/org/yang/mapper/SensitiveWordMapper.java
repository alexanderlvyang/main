package org.yang.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.yang.pojo.SensitiveWord;

public interface SensitiveWordMapper {
	List<SensitiveWord> selectSensitiveByCondition(@Param("condition") String condition, @Param("startPage") int startPage, @Param("limit") int limit);
	List<SensitiveWord> selectSensitive(@Param("startPage") int startPage, @Param("limit") int limit);
	int deleteSensitive(int sensitive_id);
	int selectCount();
	int selectCountByCondition(String condition);
	int insertSensitive(SensitiveWord sensitiveWord);
	Set<String> selectSensitiveWord();
}
