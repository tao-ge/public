package com.ycnet.frms.mapper;

import java.util.List;
import java.util.Map;

import com.ycnet.core.util.KVBean;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.PipingSection;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.PipingSectionBean;

public interface PipingSectionMapper {
    int deleteByPrimaryKey(Long pipingSectId);

    int insert(PipingSection record);

    int insertSelective(PipingSection record);

    PipingSection selectByPrimaryKey(Long pipingSectId);
    PipingSectionBean queryByPrimaryKey(Long pipingSectId);

    int updateByPrimaryKeySelective(PipingSection record);

    int updateByPrimaryKey(PipingSection record);

    /**
     * 查询管道段,根据wellId
    * 
    * @Title: PipingSectionMapper.java 
    * @Description: TODO 
    * @param @param wellId
    * @param @return
    * @return List<PipingSection>
    * @author fl
    * @date 2017年12月21日 下午4:40:09
    * @version V1.0
     */
	List<PipingSection> queryByWellId(Long wellId);

	/**
	 * 查询管道段,分面
	* 
	* @Title: PipingSectionMapper.java 
	* @Description: TODO 
	* @param @param wellId
	* @param @return
	* @return List<PipingSection>
	* @author fl
	* @date 2017年12月22日 下午1:43:39
	* @version V1.0
	 * @param users 
	 */
	List<PipingSectionBean> queryAllByWellId(Long wellId);
	
	/**
	 * 查询管道段总数
	* 
	* @Title: PipingSectionMapper.java 
	* @Description: TODO 
	* @param @param map
	* @param @return
	* @return Integer
	* @author fl
	* @date 2018年1月2日 下午4:04:25
	* @version V1.0
	 */
	Integer queryCountPipingSections(Map<String, Object> map);

	/**
	 * 查询管道段
	* 
	* @Title: PipingSectionMapper.java 
	* @Description: TODO 
	* @param @param map
	* @param @return
	* @return List<?>
	* @author fl
	* @date 2018年1月2日 下午4:05:29
	* @version V1.0
	 */
	List<PipingSection> queryPipingSections(Map<String, Object> map);


}