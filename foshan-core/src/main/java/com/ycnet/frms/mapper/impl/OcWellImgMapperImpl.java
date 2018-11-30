/**   
 * @Package: com.ycnet.frms.mapper.impl 
 * @author: FL   
 * @date: 2018年10月10日 下午2:38:31 
 */
package com.ycnet.frms.mapper.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.OcPiping;
import com.ycnet.frms.bean.OcWellImg;
import com.ycnet.frms.mapper.OcWellImgMapper;

/** 
* @ClassName: OcWellImgMapperImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月10日 下午2:38:31  
*/
@Repository("ocWellImgMapper")
public class OcWellImgMapperImpl extends BaseSqlSupport implements OcWellImgMapper{
	@Override
	public int deleteByPrimaryKey(Long wellImgId) {
		return this.delete("com.ycnet.frms.mapper.OcWellImgMapper.deleteByPrimaryKey",wellImgId);
	}

	@Override
	public int insert(OcWellImg record) {
		return this.insert("com.ycnet.frms.mapper.OcWellImgMapper.insert",record);
	}

	@Override
	public int insertSelective(OcWellImg record) {
		return this.insert("com.ycnet.frms.mapper.OcWellImgMapper.insertSelective",record); 
	}

	@Override
	public OcWellImg selectByPrimaryKey(Long wellImgId) {
		return this.selectOne("com.ycnet.frms.mapper.OcWellImgMapper.selectByPrimaryKey",wellImgId);
	}

	@Override
	public int updateByPrimaryKeySelective(OcWellImg record) {
		return this.update("com.ycnet.frms.mapper.OcWellImgMapper.updateByPrimaryKeySelective",record);  
	}

	@Override
	public int updateByPrimaryKey(OcWellImg record) {
		return this.update("com.ycnet.frms.mapper.OcWellImgMapper.updateByPrimaryKey",record);
	}

	/* (non-Javadoc)
	 * @see com.ycnet.frms.mapper.OcWellImgMapper#deleteByDevIdAndUrl(java.util.Map)
	 */
	/**
	 * 根据devId和imgUrl删除数据
	 */
	@Override
	public int deleteByDevIdAndUrl(Map<String, Object> map) {
		return  this.delete("com.ycnet.frms.mapper.OcWellImgMapper.deleteByDevIdAndUrl",map);
	}
}
