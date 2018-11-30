package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.frms.bean.Colors;

public interface ColorsMapper {
    int deleteByPrimaryKey(Long colorId);

    int insert(Colors record);

    int insertSelective(Colors record);

    Colors selectByPrimaryKey(Long colorId);

    int updateByPrimaryKeySelective(Colors record);

    int updateByPrimaryKey(Colors record);
    
    /**
	 * 手机接口,查询颜色的所有数据
	 * @author fl
	 * @deprecated
	 * @date 2017年11月20日   13:11:53
	 * @return
	 */
	List<Colors> queryColor();
}