/**   
 * @Package: com.ycnet.frms.mapper.impl 
 * @author: FL   
 * @date: 2018年10月15日 下午1:20:59 
 */
package com.ycnet.frms.mapper.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.OcCableSectionImage;
import com.ycnet.frms.mapper.OcCableSectionImageMapper;

/** 
* @ClassName: OcCableSectionImageMapperImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月15日 下午1:20:59  
*/
@Repository("ocCableSectionImageMapper")
public class OcCableSectionImageMapperImpl extends BaseSqlSupport implements OcCableSectionImageMapper{
	
	@Override
	public int deleteByPrimaryKey(Long sectImageId) {
		return this.delete("com.ycnet.frms.mapper.OcCableSectionImageMapper.deleteByPrimaryKey",sectImageId);
	}

	@Override
	public int insert(OcCableSectionImage record) {
		return this.insert("com.ycnet.frms.mapper.OcCableSectionImageMapper.insert",record);
	}

	@Override
	public int insertSelective(OcCableSectionImage record) {
		return this.insert("com.ycnet.frms.mapper.OcCableSectionImageMapper.insertSelective",record); 
	}

	@Override
	public OcCableSectionImage selectByPrimaryKey(Long sectImageId) {
		return this.selectOne("com.ycnet.frms.mapper.OcCableSectionImageMapper.selectByPrimaryKey",sectImageId);
	}

	@Override
	public int updateByPrimaryKeySelective(OcCableSectionImage record) {
		return this.update("com.ycnet.frms.mapper.OcCableSectionImageMapper.updateByPrimaryKeySelective",record);  
	}

	@Override
	public int updateByPrimaryKey(OcCableSectionImage record) {
		return this.update("com.ycnet.frms.mapper.OcCableSectionImageMapper.updateByPrimaryKey",record);
	}

	/** 
	 * 根据sectId查询光缆图片
	* @Title: queryImagesBySectId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param sectId
	* @param @return    入参
	* @return List<OcCableSectionImage>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月15日 下午1:18:47 
	* @version V1.0   
	*/
	/* (non-Javadoc)
	 * @see com.ycnet.frms.mapper.OcCableSectionImageMapper#queryImagesBySectId(java.lang.Long)
	 */
	@Override
	public List<OcCableSectionImage> queryImagesBySectId(Long sectId) {
		return this.selectList("com.ycnet.frms.mapper.OcCableSectionImageMapper.queryImagesBySectId", sectId);
	}

	/** 
	 * 删除光物理缆图片
	* @Title: deleteBySectIdAndUrl 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param map
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月15日 下午2:50:38 
	* @version V1.0   
	*/
	/* (non-Javadoc)
	 * @see com.ycnet.frms.mapper.OcCableSectionImageMapper#deleteBySectIdAndUrl(java.util.Map)
	 */
	@Override
	public int deleteBySectIdAndUrl(Map<String, Object> map) {
		return this.delete("com.ycnet.frms.mapper.OcCableSectionImageMapper.deleteBySectIdAndUrl",map);
	}

}
