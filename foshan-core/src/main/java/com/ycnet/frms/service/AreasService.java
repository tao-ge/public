package com.ycnet.frms.service;

import java.util.List;

import com.ycnet.core.util.KVBean;
import com.ycnet.frms.bean.Areas;
import com.ycnet.frms.vo.AreasBean;
import com.ycnet.frms.vo.AreasParent;
import com.ycnet.frms.vo.AreasVo;
import com.ycnet.frms.vo.Position;

public interface AreasService {

	Areas selectByCode(String areaCode);
	
	List<Areas> selectByParent(String parentAreaCode);
	

	List<Areas> selectByOrgId(Long orgId);

	List<KVBean> selectByAreaRank(Areas record);
	
    int updateByPrimaryKeySelective(Areas record);

    int updateByPrimaryKey(Areas record);
    
    
    List<AreasVo> selectVoByParent(String parentAreaCode);

    //导出数据库用  刘沧海 2017/10/12
	List<Areas> queryList();

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
	List<Areas> selectByOrgIdJQ(Long orgId);

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
	List<AreasParent> selectByOrgIdParent(Long orgId);

	/**
	 * fl 根据ParentCode 查询所属子汇聚去编码
	 * @param areaCode1
	 * @return
	 */
	List<AreasParent> selectByParentCode(String areaCode1);

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
	List<Areas> selectParentCode(String parentAreaCode);

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
	List<AreasBean> queryThreeAreas();

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
	List<Position> queryPosition();
}
