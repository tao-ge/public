package com.ycnet.frms.mapper.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ycnet.core.basic.BaseSqlSupport;
import com.ycnet.frms.bean.Colors;
import com.ycnet.frms.mapper.ColorsMapper;


@Repository("colorsMapper")
public class ColorsMapperImpl extends BaseSqlSupport implements ColorsMapper{

	@Override
	public int deleteByPrimaryKey(Long colorId) {
		return this.delete("com.ycnet.frms.mapper.ColorsMapper.delete",colorId);
	}

	@Override
	public int insert(Colors record) {
		return this.insert("com.ycnet.frms.mapper.ColorsMapper.insert",record);
	}

	@Override
	public int insertSelective(Colors record) {
		return this.insert("com.ycnet.frms.mapper.ColorsMapper.insertSelective",record);
	}

	@Override
	public Colors selectByPrimaryKey(Long colorId) {
		return this.selectOne("com.ycnet.frms.mapper.ColorsMapper.selectByPrimaryKey",colorId);
	}

	@Override
	public int updateByPrimaryKeySelective(Colors record) {
		return this.update("com.ycnet.frms.mapper.ColorsMapper.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(Colors record) {
		return this.update("com.ycnet.frms.mapper.ColorsMapper.updateByPrimaryKey", record);
	}

	/**
	 * 查询所有颜色
	 * @author fl
	 * @date 2017年11月30日 13:54:06
	 */
	@Override
	public List<Colors> queryColor() {
		return this.selectList("com.ycnet.frms.mapper.ColorsMapper.queryColor");
	}

}
