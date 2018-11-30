package com.ycnet.frms.mapper.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.LineType;
import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.Discinfo;
import com.ycnet.frms.bean.Lines;
import com.ycnet.frms.bean.LinesZF;
import com.ycnet.frms.mapper.LinesMapper;
import com.ycnet.frms.vo.LinesBean;
import com.ycnet.frms.vo.LinesConditionBean;

@Repository("linesMapper")
public class LinesMapperImpl extends BaseSqlSupport implements LinesMapper{

	@Override
	public int deleteByPrimaryKey(Long lineId) {
		return super.delete("com.ycnet.frms.mapper.LinesMapper.deleteByPrimaryKey",lineId);
	}

	@Override
	public int delete1(String lineType, Long sectId) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("lineType", lineType);
		param.put("sectId", sectId);
		return this.delete("com.ycnet.frms.mapper.LinesMapper.deleteByTypeAndSect",param);
	}
	@Override
	public int insert(Lines record) {
		return this.insert("com.ycnet.frms.mapper.LinesMapper.insert",record);
	}

	@Override
	public int insertSelective(Lines record) {
		return this.insert("com.ycnet.frms.mapper.LinesMapper.insertSelective",record);
	}
	
	@Override
	public int insertSelectiveZG(Lines record) {
		return this.insert("com.ycnet.frms.mapper.LinesMapper.insertSelectiveZG",record);
	}

	@Override
	public Lines selectByPrimaryKey(Long lineId) {
		return this.selectOne("com.ycnet.frms.mapper.LinesMapper.selectByPrimaryKey",lineId);
	}

	@Override
	public int updateByPrimaryKeySelective(Lines record) {
		return this.update("com.ycnet.frms.mapper.LinesMapper.updateByPrimaryKeySelective",record);
	}
	
	@Override
	public int updateByPrimaryKeySelectiveZG(Lines record) {
		return this.update("com.ycnet.frms.mapper.LinesMapper.updateByPrimaryKeySelectiveZG",record);
	}

	@Override
	public int updateByPrimaryKey(Lines record) {
		return this.update("com.ycnet.frms.mapper.LinesMapper.updateByPrimaryKey",record);
	}

	@Override
	public List<Lines> queryFiber(LinesConditionBean param) {
		return this.selectList("com.ycnet.frms.mapper.LinesMapper.queryFiber",param);
	}
	@Override
	public List<Lines> queryFiberByFiberNo(Map<String,Object> param)
	{
		return this.selectList("com.ycnet.frms.mapper.LinesMapper.queryFiberByFiberNo",param);
	}
	@Override
	public List<Lines> queryJumper(LinesConditionBean param) {
		return this.selectList("com.ycnet.frms.mapper.LinesMapper.queryJumper",param);
	}

	@Override
	public List<Lines> queryVirtual(LinesConditionBean param) {
		return this.selectList("com.ycnet.frms.mapper.LinesMapper.queryVirtual",param);
	}

	@Override
	public int unFiberlocate(String lineType, Long devId, Long sectId,
			Integer fromFiberNo, Integer endFiberNo) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("lineType",lineType);
		param.put("devId",devId);
		param.put("sectId", sectId);
		param.put("fromFiberNo",fromFiberNo);
		param.put("endFiberNo",endFiberNo);
		return this.update("com.ycnet.frms.mapper.LinesMapper.unFiberlocate",param);
	}
	/**
	 * 
	* @Title: unLocateDisc 
	* @Description: 修改:解成端时添加最后修改人和时间
	* @param @param devId
	* @param @param sectId
	* @param @param session
	* @param @return    入参
	* @return Object    返回类型
	* @author （刘沧海） 
	* @throws
	* @date 2017年11月2日 下午4:07:06 
	* @version V1.0
	 */
	@Override
	public int unFiberlocate(String lineType, Long devId, Long sectId,Long userId) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("lineType",lineType);
		param.put("devId",devId);
		param.put("sectId", sectId);
		param.put("userId", userId);
		param.put("lastModifyUser", userId);
		param.put("lastModifyTime", new Date());
		return this.update("com.ycnet.frms.mapper.LinesMapper.unAllFiberlocate",param);
	}

	@Override
	public Lines selectJumper(LinesConditionBean param) {
		return this.selectOne("com.ycnet.frms.mapper.LinesMapper.selectJumper",param);
	}

	
	@Override
	public Lines selectJumperWithNullPoint(LinesConditionBean param) {
		if(param.getAdevId()==null&&param.getZdevId()==null)
			throw new RuntimeException("参数不能都为空");
		return this.selectOne("com.ycnet.frms.mapper.LinesMapper.selectJumper",param);
	}

	@Override
	public List<Lines> queryByPoint(String code) {
		return this.selectList("com.ycnet.frms.mapper.LinesMapper.queryByPoint",code);
	}

	@Override
	public Lines selectLinesByTwoPoint(String acode, String zcode) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("acode", acode);
		param.put("zcode", zcode);
		return this.selectOne("com.ycnet.frms.mapper.LinesMapper.selectLinesByTwoPoint",param);
	}
	
	@Override
	public List<String> selectAlonePort() {
		return this.selectList("com.ycnet.frms.mapper.LinesMapper.selectAlonePort");
	}
	
	@Override
	public List<String> selectAlonePort(Long orgId,String areaCode,String lineIds,Long devId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orgId",orgId);
		map.put("areaCode1", areaCode);
		map.put("lineIds", lineIds);
		map.put("devId", devId);
		return this.selectList("com.ycnet.frms.mapper.LinesMapper.selectAlonePort",map);
	}
	
	@Override
	public List<Lines> selectRelationLines(String code) {
		String lineIds = this.selectOne("com.ycnet.frms.mapper.LinesMapper.findPoint",code);
		return this.selectList("com.ycnet.frms.mapper.LinesMapper.selectRelationLines",lineIds);
	}

	@Override
	public List<Lines> selectJumperByGroup(Long devId,String side) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("lineType", LineType.JUMPER.toString());
		param.put("devId",devId);
		param.put("side",side);
		return this.selectList("com.ycnet.frms.mapper.LinesMapper.selectJumperByGroup",param);
	}

	@Override
	public Lines selectAllLineWithOneNull(String code) {
		return this.selectOne("com.ycnet.frms.mapper.LinesMapper.selectAllLineWithOneNull",code);
	}

	@Override
	public int deleteLines(String str) {
		return this.delete("com.ycnet.frms.mapper.LinesMapper.deleteLines",str);
	}

	@Override
	public List<Lines> queryLinesSideAcode(String str) {
		return this.selectList("com.ycnet.frms.mapper.LinesMapper.queryLinesSideAcode", str);
	}

	@Override
	public List<Lines> queryLinesSideZcode(String str) {
		return this.selectList("com.ycnet.frms.mapper.LinesMapper.queryLinesSideZcode",str);
	}
	
	@Override
	public List<Lines> selectIsRBySectId(Long sectId) {
		return this.selectList("com.ycnet.frms.mapper.LinesMapper.selectIsRBySectId",sectId);
	}

	@Override
	public int selectTerminatByPort(String acode,String zcode){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("acode",acode);
		map.put("zcode",zcode);
		return this.selectOne("com.ycnet.frms.mapper.LinesMapper.selectTerminatByPort",map);
	}

	@Override
	public List<LinesBean> selectLineBySect(Long sectId) {
		return this.selectList("com.ycnet.frms.mapper.LinesMapper.selectLineBySect",sectId);
	}

	/**
	 * 用于删除设备
	 */
	@Override
	public List<Lines> selectByDevId(Long devId) {
		return this.selectList("com.ycnet.frms.mapper.LinesMapper.selectByDevId", devId);
	}
	/**
	 * 用于删除设备
	 */
	@Override
	public int deleteByDevId(Long devId) {
		return this.delete("com.ycnet.frms.mapper.LinesMapper.deleteByDevId", devId);
	}

	@Override
	public int isExistJumperByCode(String code) {
		return this.selectOne("com.ycnet.frms.mapper.LinesMapper.isExistJumperByCode",code);
	}

	//导出数据库  刘沧海 2017/10/13
	@Override
	public List<LinesZF> queryList(Long orgId,String areaCode) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orgId",orgId);
		map.put("areaCode",areaCode);
		return this.selectList("com.ycnet.frms.mapper.LinesMapper.queryList", map);
	}

	@Override
	public Lines selectBySectOrFib(Long sectId, Long fiberNo) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sectId",sectId);
		map.put("fiberNo",fiberNo);
		return this.selectOne("com.ycnet.frms.mapper.LinesMapper.selectBySectOrFib", map);
	}

	@Override
	public List<Lines> selectBySectId(Long sectId) {
		return this.selectList("com.ycnet.frms.mapper.LinesMapper.selectBySectId", sectId);
	}

	@Override
	public Lines queryBySectIdAndFiberNo(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.LinesMapper.queryBySectIdAndFiberNo", map);
	}

	@Override
	public List<Lines> queryLinsFor01() {
		return this.selectList("com.ycnet.frms.mapper.LinesMapper.queryLinsFor01");
	}

	@Override
	public Lines queryLinsForAcode(String code) {
		return this.selectOne("com.ycnet.frms.mapper.LinesMapper.queryLinsForAcode", code);
	}

	/**
	 * 根据code查询是否成端
	 */
	@Override
	public List<LinesBean> queryLineType(Map<String, Object> map) {
		return this.selectList("com.ycnet.frms.mapper.LinesMapper.queryLineType", map);
	}

	/**
	 * 
	* @Title: queryByCode01 
	* @Description: 根据端子编码查询成端信息 
	* @param @param acode
	* @param @return    
	* @return List<Lines>
	* @author liucanghai 
	* @throws
	* @date 2017年12月3日 下午3:37:49 
	* @version V1.0
	 */
	@Override
	public Lines queryByCode01(String code) {
		return this.selectOne("com.ycnet.frms.mapper.LinesMapper.queryByCode01", code);
	}

	/**
	 * 根据sectId查询数据
	 */
	@Override
	public List<LinesBean> queryBySectId(Long sectId) {
		return this.selectList("com.ycnet.frms.mapper.LinesMapper.queryBySectId",sectId);
	}

	@Override
	public List<Lines> queryFiberNumFor() {
		return this.selectList("com.ycnet.frms.mapper.LinesMapper.queryFiberNumFor");
	}

	/**
	 * 删除多余成端信息
	 */
	@Override
	public int deleteBySectIdAndFiberNum(Long sectId, Long fiberNum) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sectId",sectId);
		map.put("fiberNum",fiberNum);
		return super.delete("com.ycnet.frms.mapper.LinesMapper.deleteBySectIdAndFiberNum",map);
	}

	/**
	 * 
	* @Title: queryDevCodeByCode 
	* @Description: 根据设施编号模糊查询有没有成端 
	* @param @param devCode
	* @param @return    
	* @return List<Lines>
	* @author liucanghai 
	* @throws
	* @date 2017年12月16日 下午4:11:44 
	* @version V1.0
	 */
	@Override
	public List<Lines> queryDevCodeByCode(String devCode) {
		return this.selectList("com.ycnet.frms.mapper.LinesMapper.queryDevCodeByCode",devCode);
	}

	/**
	 * 
	* @Title: queryForSectId 
	* @Description: 根据光缆段ID查询成端 
	* @param @param sectId
	* @param @param lineType
	* @param @return    
	* @return List<Lines>
	* @author liucanghai 
	* @throws
	* @date 2017年12月16日 下午5:36:12 
	* @version V1.0
	 */
	@Override
	public List<Lines> queryForSectId(Long sectId, String lineType) {
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("sectId", sectId);
		map.put("lineType", lineType);
		return this.selectList("com.ycnet.frms.mapper.LinesMapper.queryForSectId",map);
	}

	/**
	 * 
	* @Title: queryFutureTwoCableSection 
	* @Description: 根据光缆段ID和纤芯序号查询 
	* @param @param sectIdA
	* @param @param i
	* @param @return    
	* @return Lines
	* @author liucanghai 
	* @throws
	* @date 2017年12月17日 下午3:03:34 
	* @version V1.0
	 */
	@Override
	public Lines queryFutureTwoCableSection(Long sectIdA, long i) {
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("sectIdA", sectIdA);
		map.put("fiberNo", i);
		return this.selectOne("com.ycnet.frms.mapper.LinesMapper.queryFutureTwoCableSection", map);
	}

	/**
	 * 根据discCode删除跳纤
	 */
	@Override
	public int deleteLinesByLineTypeAndCode(String discCode) {
		return this.delete("com.ycnet.frms.mapper.LinesMapper.deleteLinesByLineTypeAndCode",discCode);
	}

	/**
	 * 根据discCode查成端
	 */
	@Override
	public List<Lines> queryByDiscCode(String discCode) {
		return this.selectList("com.ycnet.frms.mapper.LinesMapper.queryByDiscCode",discCode);
	}

	@Override
	public Lines selectLinesByTwoPointZY(String code, String zcode) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("acode", code);
		param.put("zcode", zcode);
		return this.selectOne("com.ycnet.frms.mapper.LinesMapper.selectLinesByTwoPointZY",param);
	}

	/**
	 * 
	* @Title: queryBySectIdLineType 
	* @Description: 根据line类型和sectId查询 
	* @param @param sectIdA
	* @param @param string
	* @param @return    
	* @return List<Lines>
	* @author liucanghai 
	* @throws
	* @date 2017年12月26日 下午12:05:08 
	* @version V1.0
	 */
	@Override
	public List<Lines> queryBySectIdLineType(Long sectId, String lineType) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("lineType", lineType);
		param.put("sectId", sectId);
		return this.selectList("com.ycnet.frms.mapper.LinesMapper.queryBySectIdLineType",param);
	}

	/**
	 * 
	 * @Title: selectByDiscCode
	 * @Description: 根据discCode查看当前盘是否存在成端或跳纤,如果存在,则不可以删除 
	 * @param @param discCode
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月5日 上午9:54:38
	 * @version V1.0
	 */
	@Override
	public List<Lines> selectByDiscCode(String discCode) {
		return this.selectList("com.ycnet.frms.mapper.LinesMapper.selectByDiscCode",discCode);
	}

	/**
	 * 
	* @Title: queryForDiscCodeByOPS 
	* @Description: 根据盘编码模糊查询 
	* @param @param discCode
	* @param @return    
	* @return List<Discinfo>
	* @author liucanghai 
	* @throws
	* @date 2018年1月4日 下午3:38:43 	2018年1月8日14:02:54(DZY修改)
	* @version V1.0
	 */
	@Override
	public List<Lines> queryForDiscCodeByOPS(String lineType, String devCode) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("lineType", lineType);
		map.put("devCode", devCode);
		return this.selectList("com.ycnet.frms.mapper.LinesMapper.queryForDiscCodeByOPS",map);
	}

	/**
	 * 
	* @Title: unbindAsaside 
	* @Description: 解成端处理lines表 
	* @param @param devId
	* @param @param sectId
	* @param @param aorz
	* @param @param userId
	* @param @return    
	* @return int
	* @author liucanghai 
	* @throws
	* @date 2018年1月12日 上午10:58:12 
	* @version V1.0
	 */
	@Override
	public int unbindAsaside(String lineType, Long devId, Long sectId,Integer aorz, Long userId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("lineType", lineType);
		map.put("devId", devId);
		map.put("sectId", sectId);
		map.put("aorz", aorz);
		map.put("userId", userId);
		return this.update("com.ycnet.frms.mapper.LinesMapper.unbindAsaside",map);
	}

	
	/**
	 * 根据devId 查设施
	* 
	* @Title: LinesMapper.java 
	* @Description: TODO 
	* @param @param code
	* @param @return
	* @return List<Lines>
	* @author fl
	* @date 2018年1月16日 上午8:55:00
	* @version V1.0
	 */
	@Override
	public List<Lines> selectJumperByCode(String code) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("lineType", LineType.JUMPER.toString());
		param.put("code", code);
		return this.selectList("com.ycnet.frms.mapper.LinesMapper.selectJumperByCode",param);
	}

	/**
	 * 
	* @Title: queryNotoEnd 
	* @Description: 查询A端未成端的 
	* @param @param devId
	* @param @param sectId
	* @param @return    
	* @return List<Lines>
	* @author liucanghai 
	* @throws
	* @date 2018年4月19日 下午1:51:28 
	* @version V1.0
	 */
	@Override
	public List<Lines> queryNotoEndA(Long devId, Long sectId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sectId",sectId);
		map.put("devId", devId);
		return this.selectList("com.ycnet.frms.mapper.LinesMapper.queryNotoEndA", map);
	}

	/**
	 * 
	* @Title: queryNotoEnd 
	* @Description: 查询Z端未成端的 
	* @param @param devId
	* @param @param sectId
	* @param @return    
	* @return List<Lines>
	* @author liucanghai 
	* @throws
	* @date 2018年4月19日 下午1:51:28 
	* @version V1.0
	 * @param state 
	 */
	@Override
	public List<Lines> queryNotoEndZ(Long devId, Long sectId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sectId",sectId);
		map.put("devId", devId);
		return this.selectList("com.ycnet.frms.mapper.LinesMapper.queryNotoEndZ", map);
	}

	/**
	 * 
	* @Title: queryLineType02 
	* @Description: 查询跳纤 
	* @param @param port01
	* @param @return    
	* @return Lines
	* @author liucanghai 
	* @throws
	* @date 2018年4月10日 上午10:35:35 
	* @version V1.0
	 */
	@Override
	public Lines queryLineType02(String port01) {
		return this.selectOne("com.ycnet.frms.mapper.LinesMapper.queryLineType02", port01);
	}

	/**
	 * 
	 * @Title: queryByAcodeAndZcode
	 * @Description: 根据acode和zcode查询跳纤
	 * @param @param map
	 * @param @return
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月6日 上午11:48:24
	 * @version V1.0
	 */
	@Override
	public LinesBean queryByAcodeAndZcode(Map<String, Object> map) {
		return this.selectOne("com.ycnet.frms.mapper.LinesMapper.queryByAcodeAndZcode", map);
	}

	/**
	 * 查询对端跳纤的端子
	* @Title: queryOppositeByPort01 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param port01
	* @param @return    入参
	* @return Lines    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月22日 下午3:31:26 
	* @version V1.0
	 */
	@Override
	public Lines queryOppositeByPort01(String port01) {
		return this.selectOne("com.ycnet.frms.mapper.LinesMapper.queryOppositeByPort01", port01);
	}

}
