package com.ycnet.frms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Areas;
import com.ycnet.frms.bean.Basecode;
import com.ycnet.frms.bean.Organizition;
import com.ycnet.frms.mapper.AreasMapper;
import com.ycnet.frms.mapper.BasecodeMapper;
import com.ycnet.frms.mapper.OrganizitionMapper;
import com.ycnet.frms.service.OrganizitionService;

import net.sf.json.JSONObject;

@Service("organizitionService")
public class OrganizitionServiceImpl implements OrganizitionService{

	@Resource(name="organizitionMapper")
	private OrganizitionMapper organizitionMapper;
	
	@Resource(name="areasMapper")
	private AreasMapper areasMapper;
	
	@Resource(name="basecodeMapper")
	private BasecodeMapper basecodeMapper;
	

	

	@Override
	public Organizition selectById(Long orgId) {
		
		return organizitionMapper.selectByPrimaryKey(orgId);
	}
	//管理员的全部重新生成隐藏
	@Override
	public Organizition selectroleid(Long userId) {
		
		return organizitionMapper.selectroleid(userId);
	}
	@Override
	public List<Organizition> getOrganizitionList(Organizition record) {
		
		return organizitionMapper.getOrganizitionList(record);
	}
	
	@Override
	public PageBean queryByConditionMap(Organizition record,PageBean pb) {
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("organizition", record);
		conditionMap.put("pb", pb);
		pb.setRows(organizitionMapper.queryCountByConditionMap(conditionMap));
		pb.setList(organizitionMapper.queryByConditionMap(conditionMap));
		if(pb.getList() != null && pb.getList().size() > 0){
			for(int i = 0 ; i < pb.getList().size(); i++){
				Organizition org = (Organizition) pb.getList().get(i);
				Areas areas = areasMapper.selectByPrimaryKey(org.getCode1());
				if(areas != null){
					org.setCodeName1(areas.getAreaName());
				}
			}
		}
		
		return pb;
	}
	/**
	 * 
	* @Title: queryByConditionMapAndOrgId 
	* @Description: 组织机构根据orgid和orgName查询
	* @param @param organizition
	* @param @param pb
	* @param @param orgId
	* @param @return    入参
	* @return Object    返回类型
	* @author zhouyu
	* @throws
	* @date 2018年2月6日 上午10:20:49 
	* @version V1.0
	 */
	@Override
	public PageBean queryByConditionMapAndOrgId(Organizition record,PageBean pb,Long orgId) {
	
		return null;
	}

	@Override
	@Transactional
	public int insert(Organizition record) {
		if(record != null){
			return organizitionMapper.insertSelective(record);
		}
		return 0;
	}

	@Override
	public Organizition selectByPrimaryKey(Long orgId) {
		if(orgId != null){
			return organizitionMapper.selectByPrimaryKey(orgId);
		}
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(Organizition record) {
		if(record != null){
			return organizitionMapper.updateByPrimaryKeySelective(record);
		}
		return 1;
	}

	@Override
	public int updateByPrimaryKey(Organizition record) {
		if(record != null){
			return organizitionMapper.updateByPrimaryKey(record);
		}
		return 0;
	}
	
	@Override
	public int deleteByPrimaryKey(Long orgId) {
		if(orgId != null){
			return organizitionMapper.deleteByPrimaryKey(orgId);
		}
		return 0;
	}
	/**
	 * 
	* @Title: getorgPlatformList 
	* @Description: 得到当前组织机构下的所有平台类型 
	* @param @param orgId
	* @param @return    入参
	* @return List<String>    返回类型
	* @author 周宇
	* @throws
	* @date 2018年2月11日 下午1:35:36 
	* @version V1.0
	 */
	@Override
	public List<Basecode> getorgPlatformList(Long orgId) {
		try {
			String orgDevplatform=organizitionMapper.getorgPlatformList(orgId).getDevPlatform();
			String[] orgDevplatformLsit = orgDevplatform.split(",");
			List<Basecode> list = new ArrayList<Basecode>();
			for (int i = 0; i < orgDevplatformLsit.length; i++) {
				list.add(basecodeMapper.getplatformListByValueVode(orgDevplatformLsit[i]));
			}
			return list;
		} catch (Exception e) {
			return null;
		}
		
		
	}
	
	
	/**
	 * 查询机构名称
	* @Title: queryByOrgId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @return    入参
	* @return Organizition    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月29日 上午11:00:33 
	* @version V1.0
	 */
	@Override
	public Organizition queryByOrgId(Long orgId) {
		return organizitionMapper.selectByPrimaryKey(orgId);
	}

	/**
	 * 提交参数到NB平台
	* @Title: httpsConection 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param organizition
	* @param @return    入参
	* @return int    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年7月26日 下午2:54:39 
	* @version V1.0 
	 * @throws Exception 
	 */
	@Override
	public int httpsConection(Organizition organizition) throws Exception {
		
		return 1;
	}
	
	
	public static void main(String[] args) {
		String a = "{\"status\":\"0\"}";
		JSONObject jsonObj = JSONObject.fromObject(a.toString());
		System.err.println(jsonObj.getString("status"));
	}
}
