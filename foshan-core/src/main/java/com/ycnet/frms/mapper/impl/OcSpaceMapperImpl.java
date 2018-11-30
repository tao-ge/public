/**   
 * @Package: com.ycnet.frms.mapper.impl 
 * @author: FL   
 * @date: 2018年10月11日 上午10:19:24 
 */
package com.ycnet.frms.mapper.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.OcSpace;
import com.ycnet.frms.mapper.OcSpaceMapper;
import com.ycnet.frms.vo.mobile.OcSpaceBean;
import com.ycnet.frms.vo.mobile.ocii.OcSpaceResult;

/** 
* @ClassName: OcSpaceMapperImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月11日 上午10:19:24  
*/
@Repository("ocSpaceMapper")
public class OcSpaceMapperImpl extends BaseSqlSupport implements OcSpaceMapper{

	@Override
	public int deleteByPrimaryKey(Integer spaceId) {
		return this.delete("com.ycnet.frms.mapper.OcSpaceMapper.deleteByPrimaryKey",spaceId);
	}

	@Override
	public int insert(OcSpace record) {
		return this.insert("com.ycnet.frms.mapper.OcSpaceMapper.insert",record);
	}

	@Override
	public int insertSelective(OcSpace record) {
		return this.insert("com.ycnet.frms.mapper.OcSpaceMapper.insertSelective",record); 
	}

	@Override
	public OcSpace selectByPrimaryKey(Integer spaceId) {
		return this.selectOne("com.ycnet.frms.mapper.OcSpaceMapper.selectByPrimaryKey",spaceId);
	}

	@Override
	public int updateByPrimaryKeySelective(OcSpace record) {
		return this.update("com.ycnet.frms.mapper.OcSpaceMapper.updateByPrimaryKeySelective",record);  
	}

	@Override
	public int updateByPrimaryKey(OcSpace record) {
		return this.update("com.ycnet.frms.mapper.OcSpaceMapper.updateByPrimaryKey",record);
	}

	/* (non-Javadoc)
	 * @see com.ycnet.frms.mapper.OcSpaceMapper#querySpaceAndPips(java.lang.Long)
	 */
	/** 
	 * 查询分面及下属大孔信息
	* @Title: querySpaceAndPips 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param wellId
	* @param @return    入参
	* @return List<OcSpaceBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月11日 上午10:16:14 
	* @version V1.0   
	*/
	@Override
	public List<OcSpaceBean> querySpaceAndPips(Long wellId) {
		return this.selectList("com.ycnet.frms.mapper.OcSpaceMapper.querySpaceAndPips",wellId);
	}

	/** 
	 * 根据井ID,删除分面
	* @Title: deleteByWellId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param wellId
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年10月15日 下午4:11:02 
	* @version V1.0   
	*/
	/* (non-Javadoc)
	 * @see com.ycnet.frms.mapper.OcSpaceMapper#deleteByWellId(java.lang.Long)
	 */
	@Override
	public int deleteByWellId(Long wellId) {
		return this.delete("com.ycnet.frms.mapper.OcSpaceMapper.deleteByWellId",wellId);
	}

	@Override
	public List<OcSpaceResult> selectSpaceByWellId(Long wellId) {
		// TODO Auto-generated method stub
		return this.selectList("com.ycnet.frms.mapper.OcSpaceMapper.selectSpaceByWellId",wellId);
	}
}
