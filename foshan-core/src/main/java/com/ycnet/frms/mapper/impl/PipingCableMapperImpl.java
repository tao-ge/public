package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.Piping;
import com.ycnet.frms.bean.PipingCable;
import com.ycnet.frms.bean.PipingSection;
import com.ycnet.frms.mapper.PipingCableMapper;
import com.ycnet.frms.vo.PipingBean;
import com.ycnet.frms.vo.PipingCableBean;
import com.ycnet.frms.vo.PipingSectionBean;
@Service("pipingCableMapper")
@Transactional
public class PipingCableMapperImpl extends  BaseSqlSupport implements PipingCableMapper {

	@Override
	public int insert(PipingCable record)
	{
		return this.insert("com.ycnet.frms.mapper.PipingCableMapper.insert",record);
	}

	@Override
	public PipingCable selectByPrimaryKey(Long id)
	{
		return this.selectOne("com.ycnet.frms.mapper.PipingCableMapper.selectByPrimaryKey",id);
	}

	@Override
	public int deleteByPrimaryKey(Long id)
	{
		return this.delete("com.ycnet.frms.mapper.PipingCableMapper.deleteByPrimaryKey",id);
	}

	@Override
	public int insertSelective(PipingCable record)
	{
		return this.insert("com.ycnet.frms.mapper.PipingCableMapper.insertSelective",record);
	}
	
	@Override
	public int updateByPrimaryKeySelective(PipingCable record)
	{
		return this.update("com.ycnet.frms.mapper.PipingCableMapper.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(PipingCable record)
	{
		return this.update("com.ycnet.frms.mapper.PipingCableMapper.updateByPrimaryKey",record);
	}

	/**
	 * 查询光缆段关系表
	 */
	@Override
	public List<PipingCable> queryByPipingCableBean(PipingCable pc) {
		return this.selectList("com.ycnet.frms.mapper.PipingCableMapper.queryByPipingCableBean", pc);
	}
	
	/**
	 * 鏌ヨpipingCable
	 */
	@Override
	public List<PipingCable> selectpipingCableByThings(PipingCable pipingCable) {
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("sectId", pipingCable.getSectId());
		map.put("pipingId", pipingCable.getPipingId());
		map.put("subtubeId",pipingCable.getSubtubeId());
		return this.selectList("com.ycnet.frms.mapper.PipingCableMapper.selectpipingCableByThings", map);
	}

	//鏌ヨ绠″彛 zhouyu
	@Override
	public List<PipingCableBean> queryPipingCableListByPipingId(Long pipingId,Long orgId) {
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("pipingId",pipingId);
		map.put("orgId", orgId);
		return this.selectList("com.ycnet.frms.mapper.PipingCableMapper.queryPipingCableListByPipingId",map);
	}

	/**
	 * 查询pipingCable 已经删除的
	 * @Title: selectpipingCableByThreeIds
	 * @Description: 这里用一句话描述这个方法的作用
	 * @param @param pipingCable
	 * @param @return
	 * @return String    返回类型
	 * @author fl
	 * @throws
	 * @date 2017年12月25日 下午2:22:42
	 * @version V1.0
	 */
	@Override
	public List<PipingCable> selectpipingCableByThreeIds(PipingCable pipingCable) {
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("sectId", pipingCable.getSectId());
		map.put("pipingId", pipingCable.getPipingId());
		map.put("subtubeId",pipingCable.getSubtubeId());
		return this.selectList("com.ycnet.frms.mapper.PipingCableMapper.selectpipingCableByThreeIds", map);
	}

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
	@Override
	public List<PipingSectionBean> queryByWellId(Long wellId) {
		return this.selectList("com.ycnet.frms.mapper.PipingCableMapper.queryByWellId",wellId);
	}
	/**
	 * 
	* @Title: queryPipingByPipingSectId 
	* @Description: 根据管道段id查询管孔信息列表
	* @param @return    入参
	* @return Object    返回类型
	* @author zhouyu 
	* @throws
	* @date 2017年12月22日 上午11:10:30 
	* @version V1.0
	 */
	@Override
	public List<PipingCableBean> selectPipingSectionBeanByPipingsectid(Long pipingSectId, Long orgId,Long wellId) {
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("pipingSectId", pipingSectId);
		hashMap.put("orgId", orgId);
		hashMap.put("wellId", wellId);
		return this.selectList("com.ycnet.frms.mapper.PipingCableMapper.selectPipingSectionBeanByPipingsectid",hashMap);
	}

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
	@Override
	public PipingSectionBean queryBySectIdForWellId(Long pipingSectId, Long wellId, Long orgId) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("pipingSectId", pipingSectId);
		hashMap.put("orgId", orgId);
		hashMap.put("wellId", wellId);
		return this.selectOne("com.ycnet.frms.mapper.PipingCableMapper.queryBySectIdForWellId", hashMap);
	}

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
	 */
	@Override
	public PipingSectionBean queryPipingSectionPing(Long pipingSectId,Long wellId,Long orgId) {
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("pipingSectId", pipingSectId);
		map.put("wellId", wellId);
		map.put("orgId", orgId);
		return this.selectOne("com.ycnet.frms.mapper.PipingCableMapper.queryPipingSectionPing", map);
	}

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
	@Override
	public PipingBean queryCableSectionImage(Long pipingId, Long orgId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("pipingId", pipingId);
		map.put("orgId", orgId);
		return this.selectOne("com.ycnet.frms.mapper.PipingCableMapper.queryCableSectionImage", map);
	}

	/**
	 * 
	 * @Title: queryPipingCableBySectId
	 * @Description: 根据sectId查询光缆段绑定子管表
	 * @param @param sectId
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年2月7日 下午3:49:27
	 * @version V1.0
	 */
	@Override
	public PipingCable queryPipingCableBySectId(Long sectId, Long pipingId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("sectId", sectId);
		map.put("pipingId", pipingId);
		return this.selectOne("com.ycnet.frms.mapper.PipingCableMapper.queryPipingCableBySectId", map);
	}


}
