package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.PortImg;
import com.ycnet.frms.mapper.PortImgMapper;

@Repository("portImgMapper")
public class PortImgMapperImpl extends  BaseSqlSupport implements PortImgMapper{

	@Override
	public int deleteByPrimaryKey(Long portImgId) {
		return this.delete("com.ycnet.frms.mapper.PortImgMapper.deleteByPrimaryKey", portImgId);
	}

	@Override
	public int insert(PortImg record) {
		return this.insert("com.ycnet.frms.mapper.PortImgMapper.insert",record);
	}

	@Override
	public int insertSelective(PortImg record) {
		return this.insert("com.ycnet.frms.mapper.PortImgMapper.insertSelective",record);
	}

	@Override
	public PortImg selectByPrimaryKey(Long portImgId) {
		return this.selectOne("com.ycnet.frms.mapper.PortImgMapper.selectByPrimaryKey", portImgId);
	}

	@Override
	public int updateByPrimaryKeySelective(PortImg record) {
		return this.update("com.ycnet.frms.mapper.PortImgMapper.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(PortImg record) {
		return this.update("com.ycnet.frms.mapper.PortImgMapper.updateByPrimaryKey", record);
	}

	/**
     * 
    * @Title: queryImgPaths 
    * @Description: 根据端子查询图片路径 
    * @param @param devId
    * @param @param port01
    * @param @return    
    * @return List<PortImg>
    * @author liucanghai 
    * @throws
    * @date 2017年12月4日 下午4:44:42 
    * @version V1.0
     */
	@Override
	public List<PortImg> queryImgPaths(Long devId, String port01) {
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("devId", devId);
		map.put("port01", port01);
		return this.selectList("com.ycnet.frms.mapper.PortImgMapper.queryImgPaths", map);
	}

	@Override
	public int deleteByUrl(String path) {
		return this.delete("com.ycnet.frms.mapper.PortImgMapper.deleteByUrl", path);
	}

	
}
