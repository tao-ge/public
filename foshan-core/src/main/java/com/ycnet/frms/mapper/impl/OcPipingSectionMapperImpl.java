/**   
 * @Package: com.ycnet.frms.mapper.impl 
 * @author: FL   
 * @date: 2018年10月10日 下午2:03:08 
 */
package com.ycnet.frms.mapper.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.OcPipingSection;
import com.ycnet.frms.mapper.OcPipingSectionMapper;
import com.ycnet.frms.vo.mobile.ocii.IndexInBean;
import com.ycnet.frms.vo.mobile.ocii.IndexLine;
import com.ycnet.frms.vo.mobile.ocii.OcPipingSectionUtil;

/** 
* @ClassName: OcPipingSectionMapperImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月10日 下午2:03:08  
*/
@Repository("ocPipingSectionMapper")
public class OcPipingSectionMapperImpl extends BaseSqlSupport implements OcPipingSectionMapper{
	@Override
	public int deleteByPrimaryKey(Long pipingSectId) {
		return this.delete("com.ycnet.frms.mapper.OcPipingSectionMapper.deleteByPrimaryKey",pipingSectId);
	}

	@Override
	public int insert(OcPipingSection record) {
		return this.insert("com.ycnet.frms.mapper.OcPipingSectionMapper.insert",record);
	}

	@Override
	public int insertSelective(OcPipingSection record) {
		return this.insert("com.ycnet.frms.mapper.OcPipingSectionMapper.insertSelective",record); 
	}

	@Override
	public OcPipingSection selectByPrimaryKey(Long pipingSectId) {
		return this.selectOne("com.ycnet.frms.mapper.OcPipingSectionMapper.selectByPrimaryKey",pipingSectId);
	}

	@Override
	public int updateByPrimaryKeySelective(OcPipingSection record) {
		return this.update("com.ycnet.frms.mapper.OcPipingSectionMapper.updateByPrimaryKeySelective",record);  
	}

	@Override
	public int updateByPrimaryKey(OcPipingSection record) {
		return this.update("com.ycnet.frms.mapper.OcPipingSectionMapper.updateByPrimaryKey",record);
	}


	/**
     * 根据条件查询管道段两端
    * @Title: OcIndexPipByType 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param iib
    * @param @return    入参
    * @return List<IndexLine>    返回类型
    * @author YHT（作者） 
    * @throws
    * @date 2018年10月17日 上午9:06:44 
    * @version V1.0
     */
	@Override
	public List<IndexLine> OcIndexPipByType(IndexInBean iib) {
		// TODO Auto-generated method stub
		return this.selectList("com.ycnet.frms.mapper.OcPipingSectionMapper.OcIndexPipByType",iib);
	}

	/**
	 * 根据管道段ID查询管道段
	* @Title: selectPipSectById 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param pipingSectId
	* @param @return    入参
	* @return OcPipingSectionUtil    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年10月22日 上午9:50:16 
	* @version V1.0
	 */
	@Override
	public OcPipingSectionUtil selectPipSectById(Long pipingSectId) {
		// TODO Auto-generated method stub
		return this.selectOne("com.ycnet.frms.mapper.OcPipingSectionMapper.selectPipSectById",pipingSectId);
	}
}
