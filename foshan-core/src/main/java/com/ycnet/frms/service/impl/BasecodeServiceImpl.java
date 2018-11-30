package com.ycnet.frms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.core.CodeTable;
import com.ycnet.core.util.KVBean;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Basecode;
import com.ycnet.frms.mapper.BasecodeMapper;
import com.ycnet.frms.service.BasecodeService;

@Service("basecodeService")
@Transactional
public class BasecodeServiceImpl implements BasecodeService {

	@Resource(name= "basecodeMapper")
	private BasecodeMapper basecodeMapper;

	@Override
	public Map<String, String> getOBDTypeMap() {
		return genMap(basecodeMapper.selectSimilar(CodeTable.OBDTYPES.getName()));
	}
	
	@Override
	public  List<Basecode> getCommTypeMap(CodeTable t) {
		return basecodeMapper.selectSimilar(t.getName());
	}
	@Override
	public Map<String, String> getDEVTypeMap() {
		return genMap(basecodeMapper.selectSimilar(CodeTable.DEVTYPE.getName()));
	}

	@Override
	public Map<String, String> getIMGTypeMap() {
		
		return genMap(basecodeMapper.selectSimilar(CodeTable.IMGTYPE.getName()));
	}


	private Map<String,String> genMap(List<Basecode> list)
	{
		Map<String,String> map = new HashMap<String,String>();
		for(Basecode code : list)
		{
			if(code==null||code.getValueCode()==null||"".equals(code.getValueCode().trim()))
				continue;
			map.put(code.getValueCode(), code.getValueName());
		}
		return map;
	}

	private List<KVBean> genList(List<Basecode> list)
	{
		List<KVBean> ls = new ArrayList<KVBean>();
		for(Basecode code:list)
		{
			if(code==null||code.getValueCode()==null||"".equals(code.getValueCode().trim()))
				continue;
			KVBean bean = new KVBean(code.getValueCode(),code.getValueName());
			ls.add(bean);
		}
		return ls;
	}

	@Override
	public List<KVBean> getOBDTYPEList() {
		return genList(basecodeMapper.selectSimilar(CodeTable.OBDTYPES.getName()));
	}


	@Override
	public List<KVBean> getDEVTypeList() {
		return genList(basecodeMapper.selectSimilar(CodeTable.DEVTYPE.getName()));
	}


	@Override
	public List<KVBean> getIMGTypeList() {
		return genList(basecodeMapper.selectSimilar(CodeTable.IMGTYPE.getName()));
	}


	@Override
	public List<Basecode> getBaseCodeList(Basecode basecode) {
		
		return basecodeMapper.getBaseCodeList(basecode);
	}
	
	@Override
	public List<KVBean> getClasscode() {
		return codeList(basecodeMapper.getClasscode());
	}
	
	private List<KVBean> codeList(List<Basecode> list)
	{
		List<KVBean> ls = new ArrayList<KVBean>();
		for(Basecode code:list)
		{
			if(code==null||code.getClassCode()==null||"".equals(code.getClassCode().trim()))
				continue;
			KVBean bean = new KVBean(code.getClassCode(),code.getClassName());
			ls.add(bean);
		}
		return ls;
	}


	@Override
	public int insert(Basecode basecode) {
		if(basecode != null){
			return basecodeMapper.insert(basecode);
		}
		return 0;
	}


	@Override
	public Basecode selectByPrimaryKey(Long codeId) {
		if(codeId != null){
			return basecodeMapper.selectByPrimaryKey(codeId);
		}
		return null;
	}


	@Override
	public int updateByPrimaryKey(Basecode basecode) {
		if(basecode != null){
			return basecodeMapper.updateByPrimaryKey(basecode);
		}
		return 0;
	}


	@Override
	public int deleteByPrimaryKey(Long codeId) {
		if(codeId != null){
			return basecodeMapper.deleteByPrimaryKey(codeId);
		}
		return 0;
	}


	@Override
	public int updateByPrimaryKeySelective(Basecode record) {

		if(record != null){
			return basecodeMapper.updateByPrimaryKeySelective(record);
		}
		return 0;
	}
	
	/** 数据字典 代码值唯一校验  **/
	@Override
	public Basecode basecodeValueCodeVerify(Basecode basecode) {
		
		Basecode b=null;
						
		if(basecode.getClassCode() != null && basecode.getValueCode() != null && 
					basecode.getClassCode() != "" && basecode.getValueCode() != ""){
				
			if(basecode.getCodeId() == null) {
				b= basecodeMapper.basecodeValueCodeVerify(basecode);
				
				}else {
					 b = basecodeMapper.basecodeValueCodeVerify(basecode);				
					//判断 查出来的 是不是他本身
					if(b != null && basecode.getCodeId().equals( b.getCodeId() ) ) {														
						b = null;										
					}			
			}
		}
		
		return b;
	}
	/**
	 * 数据字典添加分页,并且修改查询回显功能
	 * 2017-9-26
	 * 刘沧海
	 */
	@Override
	public PageBean queryBaseCode(Basecode basecode, PageBean pb) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("pb", pb);
		map.put("basecode", basecode);
		pb.setList(basecodeMapper.queryBaseCodeList(map));
		pb.setRows(basecodeMapper.queryBaseCodeCount(map));
		return pb;
	}

	//导出数据库 刘沧海 2017/10/13
	@Override
	public List<Basecode> queryList() {
		return basecodeMapper.queryList();
	}
	
	@Override
	public List<KVBean> getPipeTypes() {
		return genList(basecodeMapper.selectSimilar(CodeTable.PIPETYPE.getName()));
	}

	/**
	 * 
	* 产权性质查询
	* @Title: BasecodeService.java 
	* @Description: TODO 
	* @param @return
	* @return List<Basecode>
	* @author fl
	* @date 2018年1月30日 下午4:34:53
	* @version V1.0
	 */
	@Override
	public List<Basecode> querynopTypes() {
		return basecodeMapper.querynopTypes();
	}
	/**
	 * 
	* @Title: getplatformList 
	* @Description: 查询所有平台类型
	* @param @return    入参
	* @return List<String>    返回类型
	* @author zhouyu 
	* @throws
	* @date 2018年2月7日 下午4:01:40 
	* @version V1.0
	 */
	@Override
	public List<Basecode> getplatformList() {
		return basecodeMapper.getplatformList();
	}
}
