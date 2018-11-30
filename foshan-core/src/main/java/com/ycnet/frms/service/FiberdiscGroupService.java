package com.ycnet.frms.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.ycnet.frms.bean.FiberdiscGroup;
import com.ycnet.frms.bean.FiberdiscGroupZF;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.Disc;
import com.ycnet.frms.vo.FactilityFiberDiscGroupBean;
import com.ycnet.frms.vo.FiberdiscGroupBean;
import com.ycnet.frms.vo.WorkorderFiberdiscabindBean;

public interface FiberdiscGroupService {

	/**
	 * 保存分组，接口不可以直接调用，需要在业务逻辑处理后再调用
	 * @param fiberdiscGroup
	 * @return
	 */
	//FiberdiscGroup save(FiberdiscGroup fiberdiscGroup);
	
	int insert(FiberdiscGroup fiberdiscGroup);
	
	FiberdiscGroup selectById(Long groupId);
	
	List<FiberdiscGroupBean> selectDiscinfoByGroup(Long devId,Long groupId);
	
	FiberdiscGroupBean selectDiscinfoByGroup(Long groupId);

	int delete(Long groupId);

	int update(Long groupId, String groupName);
	
	int updateGroup(FiberdiscGroup discGroup);

	//设施分组信息 -- 刘沧海 --2017-9-26
	List<FiberdiscGroup> queryByDevId(Long devId);

	//导出数据库 刘沧海 2017/10/13
	List<FiberdiscGroupZF> queryList(Long orgId, String areaCode);
	
	/**
	 * 资管导入专用
	 * @author: YHT
	 * @date: 2017年10月24日 上午10:24:47 
	 * @Title: insert  
	 * @param @param fiberdiscGroup
	 * @param @return     
	 * @return int   
	 * @throws
	 */
	int insertZG(FiberdiscGroup fiberdiscGroup);

	int saveGroupDescByGroupId(Long groupId, String groupDesc, String groupName, Users user);

	/**
	 * 新建的熔纤盘列表展示
	* 
	* @Title: FiberdiscGroupService.java 
	* @Description: TODO 
	* @param @param fiberdiscGroup
	* @param @return
	* @return List<FiberdiscGroup>
	* @author fl
	* @date 2017年12月17日 下午2:50:29
	* @version V1.0
	 */
	List<FiberdiscGroup> queryisInvest(Long devId);

	/**
	 * 
	 * @Title: saveLnvestments
	 * @Description: 添加直熔盘
	 * @param @param group
	 * @param @param session
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月17日 下午2:32:22
	 * @version V1.0
	 */
	int saveLnvestments(FiberdiscGroup group, Users user);

	/**
	 * 
	 * @Title: saveLnvestmentsGroup
	 * @Description: 保存直熔盘分组
	 * @param @param group
	 * @param @param user
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月17日 下午2:49:32
	 * @version V1.0
	 */
	int saveLnvestmentsGroupIn(FiberdiscGroup group, Users user);

	/**
	 * 
	 * @Title: saveLnvestmentsGroupOUT
	 * @Description: 保存直熔盘分组
	 * @param @param group
	 * @param @param user
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月17日 下午4:23:33
	 * @version V1.0
	 */
	int saveLnvestmentsGroupOUT(FiberdiscGroup group, Users user);

	/**
	 * 
	* @Title: deleteLnvestmentsGroup 
	* @Description: 删除直熔盘分组 
	* @param @param groupId
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2017年12月17日 下午7:54:09 
	* @version V1.0
	 * @param user 
	 */
	int deleteLnvestmentsGroup(Long groupId, Users user);

	/**
	 * 
	* @Title: deleteFiberdiscGroup 
	* @Description: 删除熔纤盘分组 
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年1月4日 下午2:20:41 
	* @version V1.0
	 */
	int deleteFiberdiscGroup(Long groupId, Users user);

	/**
	 * 
	 * @Title: facilityGroupsSave
	 * @Description: 保存设施分组
	 * @param @param group
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月15日 下午5:08:17
	 * @version V1.0
	 * @param users 
	 */
	int facilityGroupsSave(FiberdiscGroup group, Users users);

	/**
	 * 
	 * @Title: modifyGroupSide
	 * @Description: 修改熔纤盘分组编码
	 * @param @param fiberdiscGroup
	 * @param @param user
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月23日 下午1:53:22
	 * @version V1.0
	 */
	int modifyGroupSide(FiberdiscGroup fiberdiscGroup, Users user);

	/**
	 * 
	 * @Title: queryByDevIdAndSide
	 * @Description: 根据devId和side查询分组数据
	 * @param @param devId
	 * @param @param side
	 * @param @return 
	 * @return List<FiberdiscGroup> 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月23日 下午2:23:58
	 * @version V1.0
	 */
	List<FiberdiscGroup> queryByDevIdAndSide(Long devId, String side);

	/**
	 * 根据工单查询
	* @Title: queryWorkorderFiberdiscabindAllByDesignroutesId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param designroutesId
	* @param @return    入参
	* @return FactilityFiberDiscGroupBean    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月6日 上午9:40:41 
	* @version V1.0
	 * @param devId 
	 * @param state 
	 */
	FactilityFiberDiscGroupBean queryWorkorderFiberdiscabindAllByDesignroutesId(Long designroutesId, Long devId, String state,String startOrEnd);
}
