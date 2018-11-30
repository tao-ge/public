package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.Twinfiber;
import com.ycnet.frms.mapper.TwinfiberMapper;
import com.ycnet.frms.vo.TwinfiberBean;

@Repository("twinfiberMapper")
public class TwinfiberMapperImpl extends  BaseSqlSupport 
				implements TwinfiberMapper{

	@Override
	public int deleteByPrimaryKey(Long id) {
		return this.delete("com.ycnet.frms.mapper.TwinfiberMapper.deleteByPrimaryKey",id);
	}

	@Override
	public int insert(Twinfiber record) {
		return this.insert("com.ycnet.frms.mapper.TwinfiberMapper.insert",record);
	}

	@Override
	public int insertSelective(Twinfiber record) {
		return this.insert("com.ycnet.frms.mapper.TwinfiberMapper.insertSelective",record);
	}

	@Override
	public Twinfiber selectByPrimaryKey(Long id) {
		return this.selectOne("com.ycnet.frms.mapper.TwinfiberMapper.selectByPrimaryKey",id);
	}

	@Override
	public int updateByPrimaryKeySelective(Twinfiber record) {
		return this.update("com.ycnet.frms.mapper.TwinfiberMapper.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(Twinfiber record) {
		return this.update("com.ycnet.frms.mapper.TwinfiberMapper.updateByPrimaryKey",record);
	}

	@Override
	public List<Twinfiber> selectByCode(String portCode) {
		return this.selectList("com.ycnet.frms.mapper.TwinfiberMapper.selectByCode",portCode);
	}
	
	@Override
	public List<TwinfiberBean> selectByDisc(Long discId) {
		return this.selectList("com.ycnet.frms.mapper.TwinfiberMapper.selectByDisc",discId);
	}

	@Override
	public List<Twinfiber> selectByDevId(Long devId) {
		return this.selectList("com.ycnet.frms.mapper.TwinfiberMapper.selectByDevId",devId);
	}

	@Override
	public int deleteByDevId(Long devId) {
		return this.delete("com.ycnet.frms.mapper.TwinfiberMapper.deleteByDevId", devId);
	}

	//导出数据库  刘沧海 2017/10/13
	@Override
	public List<Twinfiber> queryList(Long orgId, String areaCode) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orgId", orgId);
		map.put("areaCode", areaCode);
		return this.selectList("com.ycnet.frms.mapper.TwinfiberMapper.queryList",map);
	}

	/**
	 * 
	 * @Title: selectByDevIdAndSide
	 * @Description: 根据devId和side查询数据
	 * @param @param devId
	 * @param @param side
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月24日 上午9:57:01
	 * @version V1.0
	 */
	@Override
	public List<Twinfiber> selectByDevIdAndSide(Long devId, String side) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("devId", devId);
		map.put("side", side);
		return this.selectList("com.ycnet.frms.mapper.TwinfiberMapper.selectByDevIdAndSide",map);
	}
}
