package com.ycnet.frms.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ycnet.core.util.KVBean;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.PipingSection;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.bean.Well;
import com.ycnet.frms.mapper.PipingSectionMapper;
import com.ycnet.frms.mapper.WellMapper;
import com.ycnet.frms.service.PipingSectionService;
import com.ycnet.frms.vo.PipingSectionBean;

@Service("pipingSectionService")
public class PipingSectionServiceImpl implements PipingSectionService{
	
	@Resource(name="pipingSectionMapper")
	private PipingSectionMapper pipingSectionMapper;
	
	@Resource(name="wellMapper")
	private WellMapper wellMapper;
	
	/**
	 * 管道段查询
	 */
	@Override
	public PageBean queryPipingSections(PipingSectionBean pipingSectionBean,PageBean pb,Users user) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("pb", pb);
		map.put("pipingSectionBean", pipingSectionBean);
		map.put("wellName", pipingSectionBean.getWellName());
		map.put("user", user);
		pb.setRows(pipingSectionMapper.queryCountPipingSections(map));
		pb.setList(pipingSectionMapper.queryPipingSections(map));
		return pb;
	}

	/**
	 * 添加保存
	 */
	@Override
	public void insertpipingSection(PipingSectionBean pipingSectionBean, Users user) {
		pipingSectionBean.setLastModifyTime(new Date());
		pipingSectionBean.setLastModifyUser(user.getUserId());
		pipingSectionBean.setOrgId(user.getOrgId());
		pipingSectionBean.setCreateTime(new Date());
		pipingSectionBean.setCreateUser(user.getUserId());
		pipingSectionMapper.insertSelective(pipingSectionBean);
	}

	/**
	 * 删除
	 */
	@Override
	public void deletePipingSection(Long pipingSectId) {
		pipingSectionMapper.deleteByPrimaryKey(pipingSectId);		
	}

	/**
	 * 修改回显查询
	 */
	@Override
	public PipingSection selectById(Long pipingSectId) {
		return pipingSectionMapper.selectByPrimaryKey(pipingSectId);
	}

	/**
	 * 修改保存
	 */
	@Override
	public void update(PipingSection pipingSection) {
		pipingSectionMapper.insertSelective(pipingSection);
	}

	/**
	 * 查询井
	 */
	@Override
	public List<Well> queryWell() {
		return wellMapper.queryWell();
	}

}
