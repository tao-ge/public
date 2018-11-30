package com.ycnet.frms.mapper;

import java.util.List;
import java.util.Map;

import com.ycnet.frms.bean.InspectImg;

public interface InspectImgMapper {
    int deleteByPrimaryKey(Long devImgId);

    int insert(InspectImg record);

    int insertSelective(InspectImg record);

    InspectImg selectByPrimaryKey(Long devImgId);

    int updateByPrimaryKeySelective(InspectImg record);

    int updateByPrimaryKey(InspectImg record);
    
    List<InspectImg> selectImg(InspectImg param);
    
    InspectImg selectByWorkId(Map<String, Object> map);
    
    List<InspectImg> selectByWorkIds(Long workId);
//    	
//	public List<InspectImg> queryByConditionMap(Map<String, Object> map) ;
//	
//	public int queryCountByConditionMap(Map<String, Object> map);

    //根据workId查询巡检图片
	List<InspectImg> queryListByWorkId(Long workId);
}