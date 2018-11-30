package com.ycnet.frms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.core.FrmsException;
import com.ycnet.frms.bean.Fiberdisc;
import com.ycnet.frms.bean.Twinfiber;
import com.ycnet.frms.mapper.FiberdiscMapper;
import com.ycnet.frms.mapper.TwinfiberMapper;
import com.ycnet.frms.service.TwinfiberService;
import com.ycnet.frms.vo.TwinfiberBean;
import com.ycnet.frms.vo.TwinfiberVo;

@Service("twinfiberService")
@Transactional
public class TwinfiberServiceImpl  implements TwinfiberService{

	@Resource
	private TwinfiberMapper twinfiberMapper;
	
	@Resource
	private FiberdiscMapper fiberdiscMapper;
	
	@Override
	public int add(TwinfiberVo vo) {
		if(vo.getDevId()==null||vo.getPortCode1()==null||"".equals(vo.getPortCode1())||vo.getPortCode2()==null||"".equals(vo.getPortCode2()))
			throw new FrmsException("参数不能为空");
		if(exists(vo.getPortCode1())||exists(vo.getPortCode2()))
			throw new FrmsException("端子数据已有存在的双芯配对");
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("devId", vo.getDevId());
		param.put("port01", vo.getPortCode1());
		List<Fiberdisc> list1 = fiberdiscMapper.queryFiberDisc(param);
		if(list1==null||list1.size()!=1){
			throw new FrmsException("端子数据("+vo.getPortCode1()+")有错");
		}
		
		param.put("port01", vo.getPortCode2());
		List<Fiberdisc> list2 = fiberdiscMapper.queryFiberDisc(param);
		if(list2==null||list2.size()!=1){
			throw new FrmsException("端子数据("+vo.getPortCode2()+")有错");
		}
		
		Fiberdisc f1 =list1.get(0),f2=list2.get(0);
		if(f1==null||f2==null)
			throw new FrmsException("端子数据有误");
		
		if(! f1.getDiscCode().equals(f2.getDiscCode()))
		{
			throw new FrmsException("双芯数据只能在同一盘中");
		}
		Twinfiber tf = new Twinfiber();
		tf.setDiscCode(f1.getDiscCode());
		tf.setSide(f1.getSide());
		tf.setDevId(vo.getDevId());
		tf.setPortCode1(vo.getPortCode1());
		tf.setPortCode2(vo.getPortCode2());
		
		return twinfiberMapper.insertSelective(tf);
	}
	
	@Override
	public boolean exists(String portCode) {
		List<Twinfiber> list = twinfiberMapper.selectByCode(portCode);
		if(list!=null&&list.size()>0)
			return true;
		return false;
	}
	
	@Override
	public List<TwinfiberBean> findByDiscId(Long discId) {
		return twinfiberMapper.selectByDisc(discId);
	}
	
	@Override
	public int delete(Long id) {
		return  twinfiberMapper.deleteByPrimaryKey(id);
	}

	//导出数据库  刘沧海 2017/10/13
	@Override
	public List<Twinfiber> queryList(Long orgId, String areaCode) {
		return twinfiberMapper.queryList(orgId,areaCode);
	}
}
