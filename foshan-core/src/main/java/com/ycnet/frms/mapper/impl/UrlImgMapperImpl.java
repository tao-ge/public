package com.ycnet.frms.mapper.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.UrlImg;
import com.ycnet.frms.mapper.UrlImgMapper;

@Repository("urlImgMapper")
public class UrlImgMapperImpl extends  BaseSqlSupport implements UrlImgMapper {

	@Override
	public int insert(UrlImg record)
	{
		return this.insert("com.ycnet.frms.mapper.UrlImgMapper.insert",record);
	}

	@Override
	public UrlImg selectByPrimaryKey(Long id)
	{
		return this.selectOne("com.ycnet.frms.mapper.UrlImgMapper.selectByPrimaryKey",id);
	}

	@Override
	public int deleteByPrimaryKey(Long id)
	{
		return this.delete("com.ycnet.frms.mapper.UrlImgMapper.deleteByPrimaryKey",id);
	}

	@Override
	public int insertSelective(UrlImg record)
	{
		return this.insert("com.ycnet.frms.mapper.UrlImgMapper.insertSelective",record);
	}

	@Override
	public int updateByPrimaryKeySelective(UrlImg record)
	{
		return this.update("com.ycnet.frms.mapper.UrlImgMapper.updateByPrimaryKeySelective",record);
	}

	@Override
	public int updateByPrimaryKey(UrlImg record)
	{
		return this.update("com.ycnet.frms.mapper.UrlImgMapper.updateByPrimaryKey",record);
	}

	/**
	 * 
	* @Title: queryDownApkUrl 
	* @Description: 根据项目名称和IP查询 tn_url_img
	* @param @param ip
	* @param @param proName
	* @param @return    
	* @return List<UrlImg>
	* @author liucanghai 
	* @throws
	* @date 2018年3月22日 上午11:24:43 
	* @version V1.0
	 */
	@Override
	public List<UrlImg> queryDownApkUrl(String ip, String proName) {
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("ip", ip);
		map.put("proName", proName);
		return this.selectList("com.ycnet.frms.mapper.UrlImgMapper.queryDownApkUrl", map);
	}

}
