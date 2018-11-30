package com.ycnet.frms.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ycnet.core.util.KVBean;
import com.ycnet.frms.bean.Areas;
import com.ycnet.frms.bean.DeviceReg;
import com.ycnet.frms.bean.Organizition;
import com.ycnet.frms.mapper.AreasMapper;
import com.ycnet.frms.service.AreasService;
import com.ycnet.frms.service.OrganizitionService;
import com.ycnet.frms.vo.AreasBean;
import com.ycnet.frms.vo.AreasParent;
import com.ycnet.frms.vo.AreasVo;
import com.ycnet.frms.vo.Position;

@Service("areasService")
public class AreasServiceImpl implements AreasService {

	@Resource(name="areasMapper")
	private AreasMapper areasMapper;
	
	
	@Resource(name="organizitionService")
	private OrganizitionService organizitionService;

	@Override
	public Areas selectByCode(String areaCode) {
		return areasMapper.selectByPrimaryKey(areaCode);
	}

	@Override
	public List<Areas> selectByParent(String parentAreaCode) {
		return areasMapper.selectByParent(parentAreaCode);
	}

	@Override
	public List<AreasVo> selectVoByParent(String parentAreaCode) {
		List<Areas> list = areasMapper.selectByParent(parentAreaCode);
		List<AreasVo> voList = new ArrayList<AreasVo>();
		for(Areas a :list){
			AreasVo vo  =new AreasVo();
			vo.setAreaCode(a.getAreaCode());
			vo.setAreaName(a.getAreaName());
			vo.setAreaRank(a.getAreaRank());
			vo.setParentAreaCode(a.getParentAreaCode());
			List<Areas> ll =  areasMapper.selectByParent(vo.getAreaCode());
			vo.setAreas(ll);
			voList.add(vo);
		}
		return voList;
	}
	
	/**
	 * 根据组织机构id获取其下级区域列表
	 */
	@Override
	public List<Areas> selectByOrgId(Long orgId) {
		
		Organizition org=organizitionService.selectById(orgId);
		
		if(org!=null)
		{
			if(org.getCode2()!=null && !org.getCode2().equals("")) {
				return selectByParent(org.getCode1());
			}else {
				return selectByParent(org.getCode1());
			}
		}
		
		return null;
	}
	/**
	 * 
	* @Title: selectByOrgIdParent 
	* @Description: 根据组织机构查询 
	* @param @param orgId
	* @param @return    
	* @return List<Areas>
	* @author liucanghai 
	* @throws
	* @date 2018年1月17日 下午4:42:53 
	* @version V1.0
	 */
	@Override
	public List<AreasParent> selectByOrgIdParent(Long orgId) {
		Organizition org=organizitionService.selectById(orgId);
		if(org!=null)
		{
			if(org.getCode1()!=null && !org.getCode1().equals("")) {
				return selectByParentChild(org.getCode1());
			}
		}
		return null;
	}
	/**
	 * 	
	* @Title: selectByParentChild 
	* @Description: 根据父类区域编码查询子类 
	* @param @param code1
	* @param @return    
	* @return List<Areas>
	* @author liucanghai 
	* @throws
	* @date 2018年1月17日 下午4:45:41 
	* @version V1.0
	 */
	private List<AreasParent> selectByParentChild(String code1) {
		List<AreasParent> areasList=areasMapper.selectByParentChild(code1);
		
		if(areasList !=null && areasList.size()>0) {
			
			for (int i = 0; i < areasList.size(); i++) {
				areasList.get(i).setAreas(null);
				List<Areas> childList=areasMapper.selectByParent(areasList.get(i).getParentAreaCode());
				if(childList!=null && childList.size()>0) {
					for (int j = 0; j < childList.size(); j++) {
						childList.get(j).setParentAreaCode(areasList.get(i).getParentAreaCode());
						areasList.get(i).setAreas(childList);
					}
				}
			}
		}
		return areasList;
	}

	@Override
	public List<KVBean> selectByAreaRank(Areas record) {
		return codeList(areasMapper.selectByAreaRank(record));
	}
	
	
	private List<KVBean> codeList(List<Areas> list)
	{
		List<KVBean> ls = new ArrayList<KVBean>();
		for(Areas area:list)
		{
			if(area==null||area.getAreaCode()==null||"".equals(area.getAreaCode().trim()))
				continue;
			KVBean bean = new KVBean(area.getAreaCode(),area.getAreaName());
			ls.add(bean);
		}
		return ls;
	}

	@Override
	public int updateByPrimaryKeySelective(Areas record) {
		if(record != null){
			return areasMapper.updateByPrimaryKeySelective(record);
		}
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Areas record) {
		if(record != null){
			return areasMapper.updateByPrimaryKey(record);
		}
		return 0;
	}

	//导出数据库用 刘沧海 2017/10/12
	@Override
	public List<Areas> queryList() {
		return areasMapper.queryList();
	}

	/**
	 * 
	* @Title: selectByParentJQ 
	* @Description: TODO(根据组织机构id获取其下级区域列表) 
	* @param @param orgId
	* @param @return    入参
	* @return List<Areas>    返回类型
	* @author （刘沧海） 
	* @throws
	* @date 2017年10月26日 上午11:18:26 
	* @version V1.0
	 */
	@Override
	public List<Areas> selectByOrgIdJQ(Long orgId) {
		Organizition org=organizitionService.selectById(orgId);
		
		if(org!=null)
		{
			if(org.getCode2()!=null && !org.getCode2().equals("")) {
				List<Areas> list =selectByParentJQ(org.getCode2());
				if(list!=null && list.size()>0) {
					return selectByParentJQ(org.getCode2());//查询子类
				}else {
					return selectByChildrenJQ(org.getCode2());//查询
				}
			}else {
				List<Areas> list1 =selectByParentJQ(org.getCode1());
				if(list1!=null && list1.size()>0) {
					return selectByParentJQ(org.getCode1());
				}else {
					return selectByChildrenJQ(org.getCode1());
				}
			}
		}
		
		return null;
	}

	/**
	 * 
	* @Title: selectByParentJQ 
	* @Description: TODO(根据局域代码查询) 
	* @param @param code
	* @param @return    入参
	* @return List<Areas>    返回类型
	* @author （刘沧海） 
	* @throws
	* @date 2017年10月26日 上午11:22:24 
	* @version V1.0
	 */
	private List<Areas> selectByChildrenJQ(String code) {
		return areasMapper.selectByChildrenJQ(code);
	}

	/**
	 * 
	* @Title: selectByParentJQ 
	* @Description: TODO(根据区域编码查询子类) 
	* @param @param code
	* @param @return    入参
	* @return List<Areas>    返回类型
	* @author （刘沧海） 
	* @throws
	* @date 2017年10月26日 上午11:22:24 
	* @version V1.0
	 */
	private List<Areas> selectByParentJQ(String code) {
		return areasMapper.selectByParentJQ(code);
	}

	/**
	 * fl 根据ParentCode 查询所属子汇聚去编码
	 * @param areaCode1
	 * @date 2018年2月212日 上午11:22:24 
	 * @return
	 */
	@Override
	public List<AreasParent> selectByParentCode(String areaCode1) {
		List<AreasParent> list = areasMapper.selectByParentChild(areaCode1);//子类汇聚区
		AreasParent areasParent=areasMapper.selectByCode(areaCode1);//本汇聚区
		if (list.size()>0 && list !=null) {
			list.add(areasParent);
			int a=0;
			for (int i = 0; i < list.size(); i++) {//去重复
				if (list.get(i).getParentAreaCode().equals(areasParent.getParentAreaCode())) {//排除重复的情况
					a++;
				}
				if (a==2) {
					list.remove(list.size()-1);
				}
			}
			for (int i = 0; i < list.size(); i++) {//拼接名称，编码
				String codeAndName = list.get(i).getParentAreaCode() +","+ list.get(i).getParentAreaName();
				list.get(i).setCodeAndName(codeAndName);
			}
			String parentAreaName = list.get(list.size()-1).getParentAreaName();
			String codeAndName = list.get(list.size()-1).getCodeAndName();
			for (int i = list.size()-1; i >0; i--) {//排序
				list.get(i).setParentAreaName(list.get(i-1).getParentAreaName());
				list.get(i).setCodeAndName(list.get(i-1).getCodeAndName());
			}
			list.get(0).setCodeAndName(codeAndName);
			list.get(0).setParentAreaName(parentAreaName);
		}
		return list;
	}

	/**根据ParentCode 查询所属子汇聚去编码
	 * 
	* @Title: selectParentCode 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param substring
	* @param @return    入参
	* @return List<Areas>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月8日 下午4:21:57 
	* @version V1.0
	 */
	@Override
	public List<Areas> selectParentCode(String parentAreaCode) {
		return areasMapper.selectByParent(parentAreaCode);
	}

	
	/**
	 * 查询省市区
	* @Title: queryThreeAreas 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    入参
	* @return List<AreasBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月14日 下午3:09:56 
	* @version V1.0
	 */
	@Override
	public List<AreasBean> queryThreeAreas() {
		return areasMapper.queryThreeAreas();
	}

	/**
	 * 查询省市区镇
	* @Title: queryPosition 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    入参
	* @return List<Position>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月15日 上午10:39:23 
	* @version V1.0
	 */
	@Override
	public List<Position> queryPosition() {
		return areasMapper.queryPosition();
	}
}
