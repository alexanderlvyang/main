package org.yang.suibian.mapper.demo;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.yang.suibian.model.demo.ParentUser;
import org.yang.suibian.model.demo.ParentUserExample;

public interface ParentUserMapper {
    long countByExample(ParentUserExample example);

    int deleteByExample(ParentUserExample example);

    int deleteByPrimaryKey(Integer pid);

    int insert(ParentUser record);

    int insertSelective(ParentUser record);

    List<ParentUser> selectByExample(ParentUserExample example);

    ParentUser selectByPrimaryKey(Integer pid);

    int updateByExampleSelective(@Param("record") ParentUser record, @Param("example") ParentUserExample example);

    int updateByExample(@Param("record") ParentUser record, @Param("example") ParentUserExample example);

    int updateByPrimaryKeySelective(ParentUser record);

    int updateByPrimaryKey(ParentUser record);
}