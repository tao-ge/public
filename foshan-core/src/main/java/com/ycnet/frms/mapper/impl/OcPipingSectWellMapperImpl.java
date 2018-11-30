/**   
 * @Package: com.ycnet.frms.mapper.impl 
 * @author: FL   
 * @date: 2018年10月23日 上午10:02:47 
 */
package com.ycnet.frms.mapper.impl;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.OcPipingSectWell;
import com.ycnet.frms.bean.OcSpace;
import com.ycnet.frms.mapper.OcPipingSectWellMapper;

/** 
* @ClassName: OcPipingSectWellMapperImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author FL（作者）
* @date 2018年10月23日 上午10:02:47  
*/
@Repository("ocPipingSectWellMapper")
public class OcPipingSectWellMapperImpl extends BaseSqlSupport implements OcPipingSectWellMapper{

	@Override
	public int deleteByPrimaryKey(Long pipingWellId) {
		return this.delete("com.ycnet.frms.mapper.OcPipingSectWellMapper.deleteByPrimaryKey",pipingWellId);
	}

	@Override
	public int insert(OcPipingSectWell record) {
		return this.insert("com.ycnet.frms.mapper.OcPipingSectWellMapper.insert",record);
	}

	@Override
	public int insertSelective(OcPipingSectWell record) {
		return this.insert("com.ycnet.frms.mapper.OcPipingSectWellMapper.insertSelective",record); 
	}

	@Override
	public OcPipingSectWell selectByPrimaryKey(Long pipingWellId) {
		return this.selectOne("com.ycnet.frms.mapper.OcPipingSectWellMapper.selectByPrimaryKey",pipingWellId);
	}

	@Override
	public int updateByPrimaryKeySelective(OcPipingSectWell record) {
		return this.update("com.ycnet.frms.mapper.OcPipingSectWellMapper.updateByPrimaryKeySelective",record);  
	}

	@Override
	public int updateByPrimaryKey(OcPipingSectWell record) {
		return this.update("com.ycnet.frms.mapper.OcPipingSectWellMapper.updateByPrimaryKey",record);
	}

}
