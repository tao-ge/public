package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.frms.bean.OcIdentifPort;
import com.ycnet.frms.vo.mobile.ocii.OcIdentifPortResult;

public interface OcIdentifPortMapper {
    int deleteByPrimaryKey(Long portId);

    int insert(OcIdentifPort record);

    int insertSelective(OcIdentifPort record);

    OcIdentifPort selectByPrimaryKey(Long portId);

    int updateByPrimaryKeySelective(OcIdentifPort record);

    int updateByPrimaryKey(OcIdentifPort record);

    /**
     * 根据ID查询端口信息
    * @Title: selectPortByIdentifId 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param identId
    * @param @return    入参
    * @return List<OcIdentifPort>    返回类型
    * @author YHT（作者） 
    * @throws
    * @date 2018年10月24日 下午2:39:11 
    * @version V1.0
     */
	List<OcIdentifPort> selectPortByIdentifId(Long identId);

	/**
	 * 接口 - 查询端口详情
	* @Title: selectPortByIdentIdAndWellId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param wellDevId
	* @param @param type
	* @param @param identId
	* @param @return    入参
	* @return List<OcIdentifPortResult>    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年10月25日 下午1:52:29 
	* @version V1.0
	 */
	List<OcIdentifPortResult> selectPortByIdentIdAndWellId(Long wellDevId, String type, Long identId);
}