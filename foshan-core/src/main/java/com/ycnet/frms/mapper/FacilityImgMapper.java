package com.ycnet.frms.mapper;

import java.util.List;
import java.util.Map;

import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.FacilityImg;

public interface FacilityImgMapper {
    int deleteByPrimaryKey(Long devImgId);

    int insert(FacilityImg record);

    int insertSelective(FacilityImg record);

    FacilityImg selectByPrimaryKey(Long devImgId);

    int updateByPrimaryKeySelective(FacilityImg record);

    int updateByPrimaryKey(FacilityImg record);
    
    List<FacilityImg> selectImg(FacilityImg param);
    
    /**
     * 查询巡检所对应的设施图片信息
     * @author YHT
     * @time   2016年7月8日 上午10:04:04
     * @param param
     * @return
     */
    List<FacilityImg> selectByInspectedId(Long inspectedId);
    	
	public List<FacilityImg> queryByConditionMap(Map<String, Object> map) ;
	
	public int queryCountByConditionMap(Map<String, Object> map);

	List<FacilityImg> selectByDevId(Long devId);

	FacilityImg selectByDevUrl(String paths);

	int saveUrl(FacilityImg facilityImg);

	int deleteUrl(String paths);
	
	int deleteByDevId(Long devId);

	//导出数据库  刘沧海 2017/10/13
	List<FacilityImg> queryList();

	/**
	 * 
	 * @Title: selectByInspectedIdAndDevId
	 * @Description: 根据巡检ID和设施ID查询巡检图片
	 * @param @param inspectId
	 * @param @param devId
	 * @param @return 
	 * @return FacilityImg 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年3月13日 下午2:35:11
	 * @version V1.0
	 */
	FacilityImg selectByInspectedIdAndDevId(Map<String,Object> map);

	/**
	 * 
	 * @Title: deleteByDevIdAndUrl
	 * @Description: 根据devId和imgUrl删除
	 * @param @param map
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月16日 下午2:50:41
	 * @version V1.0
	 */
	int deleteByDevIdAndUrl(Map<String, Object> map);
}