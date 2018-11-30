package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.frms.bean.Twinfiber;
import com.ycnet.frms.vo.TwinfiberBean;

public interface TwinfiberMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Twinfiber record);

    int insertSelective(Twinfiber record);

    Twinfiber selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Twinfiber record);

    int updateByPrimaryKey(Twinfiber record);
    
    List<Twinfiber> selectByCode(String portCode);
    
    List<TwinfiberBean> selectByDisc(Long discId);

	List<Twinfiber> selectByDevId(Long devId);

	int deleteByDevId(Long devId);

	//导出数据库  刘沧海 2017/10/13
	List<Twinfiber> queryList(Long orgId, String areaCode);

	/**
	 * 
	 * @Title: selectByDevIdAndSide
	 * @Description: 根据devId和side查询数据
	 * @param @param devId
	 * @param @param side
	 * @param @return 
	 * @return List<Twinfiber> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月24日 上午9:56:29
	 * @version V1.0
	 */
	List<Twinfiber> selectByDevIdAndSide(Long devId, String side);
	
    
}