package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.frms.bean.Piping;
import com.ycnet.frms.bean.PipingCable;
import com.ycnet.frms.bean.PipingSection;
import com.ycnet.frms.vo.PipingBean;
import com.ycnet.frms.vo.PipingCableBean;
import com.ycnet.frms.vo.PipingSectionBean;

public interface PipingCableMapper {
    int deleteByPrimaryKey(Long subtubeRefSectId);

    int insert(PipingCable record);

    int insertSelective(PipingCable record);

    PipingCable selectByPrimaryKey(Long subtubeRefSectId);

    int updateByPrimaryKeySelective(PipingCable record);

    int updateByPrimaryKey(PipingCable record);

    /**
     * 
    * @Title: queryByPipingCableBean 
    * @Description: 查询井，光缆段关系表 
    * @param @param pc
    * @param @return    
    * @return List<PipingCable>
    * @author liucanghai 
    * @throws
    * @date 2017年12月22日 下午5:14:52 
    * @version V1.0
     */
	List<PipingCable> queryByPipingCableBean(PipingCable pc);

    /**
     * 查询pipingCable
    * 
    * @Title: PipingCableMapper.java 
    * @Description: TODO 
    * @param @param sectId
    * @param @param pipingId
    * @param @param subtubeId
    * @param @return
    * @return List<PipingCable>
    * @author fl
    * @date 2017年12月22日 下午4:56:43
    * @version V1.0
     */
	List<PipingCable> selectpipingCableByThings(PipingCable pipingCable);
    //根据pipingid查询管口光缆度列表
	List<PipingCableBean> queryPipingCableListByPipingId(Long pipingId, Long orgId);
	/**
	 * 查询pipingCable已经删除的光缆段
	 * @Title: selectpipingCableByThreeIds
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param pipingCable
	 * @param @return 
	 * @return List<PipingCable> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月25日 下午2:21:56
	 * @version V1.0
	 */
	List<PipingCable> selectpipingCableByThreeIds(PipingCable pipingCable);

	/**
	 * 
	* @Title: pipingSectionList 
	* @Description: 查询井设施详细信息 
	* @param @param wellId
	* @param @return    
	* @return String
	* @author liucanghai 
	* @throws
	* @date 2018年1月3日 下午3:30:32 
	* @version V1.0
	 */
	List<PipingSectionBean> queryByWellId(Long wellId);
	/**
	 * 查询管口
	 * @param wellId 
	 */
	List<PipingCableBean> selectPipingSectionBeanByPipingsectid(Long pipingSectId, Long orgId, Long wellId);

	/**
	 * 
	* @Title: queryBySectIdForWellId 
	* @Description: 根据井ID和管道段ID查询光缆段信息 
	* @param @param pipingSectId
	* @param @param wellId
	* @param @param orgId
	* @param @return    
	* @return PipingSectionBean
	* @author liucanghai 
	* @throws
	* @date 2018年1月18日 上午10:06:50 
	* @version V1.0
	 */
	PipingSectionBean queryBySectIdForWellId(Long pipingSectId, Long wellId, Long orgId);

	/**
	 * 
	* @Title: queryPipingSectionPing 
	* @Description: 根据管道段ID查询 管孔信息
	* @param @param pipingSectId
	* @param @return    
	* @return PipingSectionBean
	* @author liucanghai 
	* @throws
	* @date 2018年1月19日 下午3:06:08 
	* @version V1.0
	 * @param wellId 
	 * @param orgId 
	 */
	PipingSectionBean queryPipingSectionPing(Long pipingSectId, Long wellId, Long orgId);

	/**
	 * 
	* @Title: queryCableSectionImage 
	* @Description: 根据管孔ID查询光缆段信心和图片 
	* @param @param piping
	* @param @param session
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年1月22日 上午10:40:13 
	* @version V1.0
	 */
	PipingBean queryCableSectionImage(Long pipingId, Long orgId);

	/**
	 * 
	 * @Title: queryPipingCableBySectId
	 * @Description: 根据sectId查询光缆段绑定子管表
	 * @param @param sectId
	 * @param @return 
	 * @return PipingCable 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年2月7日 下午3:49:11
	 * @version V1.0
	 * @param pipingId 
	 */
	PipingCable queryPipingCableBySectId(Long sectId, Long pipingId);


}