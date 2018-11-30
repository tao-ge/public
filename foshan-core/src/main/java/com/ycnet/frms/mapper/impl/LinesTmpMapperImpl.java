package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.Lines;
import com.ycnet.frms.mapper.LinesTmpMapper;
import com.ycnet.frms.vo.LinesTmp;

@Repository("linesTmpMapper")
public class LinesTmpMapperImpl extends BaseSqlSupport implements LinesTmpMapper{

	@Override
	public int deleteByPrimaryKey(Long lineId) {
		return super.delete("com.ycnet.frms.mapper.LinesTmpMapper.deleteByPrimaryKey",lineId);
	}

	@Override
	public int delete1(String lineType, Long sectId) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("lineType", lineType);
		param.put("sectId", sectId);
		return this.delete("com.ycnet.frms.mapper.LinesTmpMapper.deleteByTypeAndSect",param);
	}
	@Override
	public int insert(Lines record) {
		return this.insert("com.ycnet.frms.mapper.LinesTmpMapper.insert",record);
	}

	@Override
	public int insertSelective(Lines record) {
		return this.insert("com.ycnet.frms.mapper.LinesTmpMapper.insertSelective",record);
	}

	@Override
	public Lines selectByPrimaryKey(Long lineId) {
		return this.selectOne("com.ycnet.frms.mapper.LinesTmpMapper.selectByPrimaryKey",lineId);
	}

	@Override
	public int updateByPrimaryKeySelective(Lines record) {
		return this.update("com.ycnet.frms.mapper.LinesTmpMapper.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(Lines record) {
		return this.update("com.ycnet.frms.mapper.LinesTmpMapper.updateByPrimaryKey",record);
	}

	@Override
	public List<LinesTmp> selectFGBy01(Long orgId) {
		// TODO Auto-generated method stub
		return this.selectList("com.ycnet.frms.mapper.LinesTmpMapper.selectFGBy01",orgId);
	}

	@Override
	public LinesTmp selectTXByCode(String code,Long orgId,String areaCode) {
		// TODO Auto-generated method stub
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("orgId", orgId);
		param.put("code", code);
		param.put("areaCode", areaCode);
		return this.selectOne("com.ycnet.frms.mapper.LinesTmpMapper.selectTXByCode",param);
	}

	@Override
	public LinesTmp selectGXByCode(String code,Long orgId,String areaCode) {
		// TODO Auto-generated method stub
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("orgId", orgId);
		param.put("code", code);
		param.put("areaCode", areaCode);
		return this.selectOne("com.ycnet.frms.mapper.LinesTmpMapper.selectGXByCode",param);
	}

	@Override
	public List<LinesTmp> selectFGListByCode(String code,Long orgId) {
		// TODO Auto-generated method stub
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("orgId", orgId);
		param.put("code", code);
		return this.selectList("com.ycnet.frms.mapper.LinesTmpMapper.selectFGListByCode",param);
	}

	@Override
	public LinesTmp selectZCLines(Long orgId) {
		// TODO Auto-generated method stub
		return this.selectOne("com.ycnet.frms.mapper.LinesTmpMapper.selectZCLines",orgId);
	}
	
	@Override
	public List<LinesTmp> selectZCLinesList(Long orgId) {
		// TODO Auto-generated method stub
		return this.selectList("com.ycnet.frms.mapper.LinesTmpMapper.selectZCLinesList",orgId);
	}

	@Override
	public int deleteByCode(LinesTmp line) {
		// TODO Auto-generated method stub
		return super.delete("com.ycnet.frms.mapper.LinesTmpMapper.deleteByCode",line);
	}

	@Override
	public int insertByOrgId01(Long orgId, String areaCode) {
		// TODO Auto-generated method stub
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("orgId", orgId);
		param.put("areaCode", areaCode);
		return this.insert("com.ycnet.frms.mapper.LinesTmpMapper.insertByOrgId01",param);
	}
	
	@Override
	public int insertByOrgId(Long orgId, String areaCode) {
		// TODO Auto-generated method stub
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("orgId", orgId);
		param.put("areaCode", areaCode);
		return this.insert("com.ycnet.frms.mapper.LinesTmpMapper.insertByOrgId",param);
	}

	@Override
	public int selectByOrgId(Long orgId) {
		// TODO Auto-generated method stub
		return this.selectOne("com.ycnet.frms.mapper.LinesTmpMapper.selectByOrgId",orgId);
	}
	
	@Override
	public int deleteByLine() {
		this.delete("com.ycnet.frms.mapper.LinesTmpMapper.deleteByLine1");
		return this.delete("com.ycnet.frms.mapper.LinesTmpMapper.deleteByLine2");
	}

	@Override
	public int selectByCodeA(String code) {
		// TODO Auto-generated method stub
		return this.selectOne("com.ycnet.frms.mapper.LinesTmpMapper.selectByCodeA",code);
	}

	@Override
	public int selectByCodeZ(String code) {
		// TODO Auto-generated method stub
		return this.selectOne("com.ycnet.frms.mapper.LinesTmpMapper.selectByCodeZ",code);
	}

	@Override
	public int selectExitTXbyCode(String code, Long orgId) {
		// TODO Auto-generated method stub
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("orgId", orgId);
		param.put("code", code);
		return this.selectOne("com.ycnet.frms.mapper.LinesTmpMapper.selectExitTXbyCode",param);
	}

	@Override
	public LinesTmp selectCodeByTX(String code, Long orgId) {
		// TODO Auto-generated method stub
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("orgId", orgId);
		param.put("code", code);
		return this.selectOne("com.ycnet.frms.mapper.LinesTmpMapper.selectCodeByTX",param);
	}

	@Override
	public LinesTmp selectCodeByGX(String code, Long orgId) {
		// TODO Auto-generated method stub
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("orgId", orgId);
		param.put("code", code);
		return this.selectOne("com.ycnet.frms.mapper.LinesTmpMapper.selectCodeByGX",param);
	}

	@Override
	public String selectCodeByFGB(String code) {
		// TODO Auto-generated method stub
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("code", code);
		return this.selectOne("com.ycnet.frms.mapper.LinesTmpMapper.selectCodeByFGB",param);
	}

	@Override
	public String selectCodeByFG00(String code) {
		// TODO Auto-generated method stub
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("code", code);
		return this.selectOne("com.ycnet.frms.mapper.LinesTmpMapper.selectCodeByFG00",param);
	}

	@Override
	public LinesTmp selectTXByCode01(String code, Long orgId) {
		// TODO Auto-generated method stub
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("orgId", orgId);
		param.put("code", code);
		LinesTmp lt = this.selectOne("com.ycnet.frms.mapper.LinesTmpMapper.selectTXByCode01",param);
		if(lt != null) {
			Lines line = this.selectOne("com.ycnet.frms.mapper.LinesTmpMapper.selectLineByCode",lt.getRouteCode());
			if(line != null) {
				if(lt.getOtherRouteNoteName() != null) {
					lt.setOtherRouteNoteName(lt.getOtherRouteNoteName() + "(No:" + line.getFiberNo() + ")");
				}
				if(lt.getRouteNoteName() != null) {
					lt.setRouteNoteName(lt.getRouteNoteName() + "(No:" + line.getFiberNo() + ")");
				}
			}
		}
		return lt;
	}

	@Override
	public LinesTmp selectGXByCode01(String code, Long orgId) {
		// TODO Auto-generated method stub
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("orgId", orgId);
		param.put("code", code);
		return this.selectOne("com.ycnet.frms.mapper.LinesTmpMapper.selectGXByCode01",param);
	}

	@Override
	public List<LinesTmp> selectFGByCode(String code, Long orgId) {
		// TODO Auto-generated method stub
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("orgId", orgId);
		param.put("code", code);
		return this.selectList("com.ycnet.frms.mapper.LinesTmpMapper.selectFGByCode",param);
	}
}
