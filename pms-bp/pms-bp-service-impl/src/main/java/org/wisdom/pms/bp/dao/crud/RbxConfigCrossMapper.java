package org.wisdom.pms.bp.dao.crud;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.wisdom.pms.bp.model.crud.RbxConfigCross;
import org.wisdom.pms.bp.model.crud.RbxConfigCrossExample;
import org.wisdom.pms.bp.model.crud.RbxConfigCrossKey;

public interface RbxConfigCrossMapper {
    int countByExample(RbxConfigCrossExample example);

    int deleteByExample(RbxConfigCrossExample example);

    int deleteByPrimaryKey(RbxConfigCrossKey key);

    int insert(RbxConfigCross record);

    int insertSelective(RbxConfigCross record);

    List<RbxConfigCross> selectByExample(RbxConfigCrossExample example);

    RbxConfigCross selectByPrimaryKey(RbxConfigCrossKey key);

    int updateByExampleSelective(@Param("record") RbxConfigCross record, @Param("example") RbxConfigCrossExample example);

    int updateByExample(@Param("record") RbxConfigCross record, @Param("example") RbxConfigCrossExample example);

    int updateByPrimaryKeySelective(RbxConfigCross record);

    int updateByPrimaryKey(RbxConfigCross record);
}