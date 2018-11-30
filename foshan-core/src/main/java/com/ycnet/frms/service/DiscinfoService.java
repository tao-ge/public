package com.ycnet.frms.service;

import java.util.List;

import com.ycnet.frms.bean.Discinfo;
import com.ycnet.frms.bean.DiscinfoZF;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.FiberdiscGroup;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.vo.Disc;
import com.ycnet.frms.vo.DiscWX;
import com.ycnet.frms.vo.mobile.DiscinfoMobile;

public interface DiscinfoService {

	Discinfo selectById(Long discId);
	
	List<Discinfo> selectByGroup(Long groupId);
	
	int insert(Discinfo discinfo,int index,String model);
	
	/**
	 * 修改名称和业务
	 * @param discinfo
	 * @return
	 */
	int chgDiscinfo(Discinfo discinfo);
	/**
	 * 自动生成熔纤盘信息
	 * @param group
	 * @param facility
	 * @return
	 */
	int genDiscinfo(FiberdiscGroup group ,Facility facility );
	
	/**
	 * 盘占用情况  -- 添加尾纤悬空状态
	 * @author: YHT
	 * @date: 2017年9月26日 上午11:12:44 
	 * @Title: selectDiscWX  
	 * @param @param devId
	 * @param @param side
	 * @param @param discId
	 * @param @return     
	 * @return DiscWX   
	 * @throws
	 */
	DiscWX selectDiscWX(Long devId, String side, Long discId) ;
	
	Disc selectDisc(Long devId, String side, Long discId) ;
	
	Disc selectDiscBySectId(Long devId, String side, Long discId,Long sectId) ;

	//导出数据库 刘沧海 2017/10/13
	List<DiscinfoZF> queryList(Long orgId, String areaCode);
	
	/**
	 * 资管专用
	 * @author: YHT
	 * @date: 2017年10月24日 上午10:33:17 
	 * @Title: genDiscinfo  
	 * @param @param group
	 * @param @param facility
	 * @param @return     
	 * @return int   
	 * @throws
	 */
	int genDiscinfoZG(FiberdiscGroup group ,Facility facility );

	/**
	 * 
	 * @Title: discinfoDelete
	 * @Description: 删除熔纤盘
	 * @param @param discId
	 * @param @param user
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月5日 上午9:09:19
	 * @version V1.0
	 * @param discCode 
	 */
	int discinfoDelete(Long discId, String discCode, Users user);

	/**
	 * 
	 * @Title: saveDiscinfo
	 * @Description: 生成直熔盘信息
	 * @param @param group
	 * @param @param facility
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月17日 下午3:29:21
	 * @version V1.0
	 * @param user 
	 */
	int saveDiscinfo(FiberdiscGroup group, Facility facility, Users user);

	/**
	 * 
	 * @Title: deleteLnvestments
	 * @Description: 删除直熔盘
	 * @param @param discId
	 * @param @param discCode
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2017年12月17日 下午7:37:41
	 * @version V1.0
	 * @param user 
	 */
	int deleteLnvestments(Long discId, String discCode, Users user);

	/**
	 * 修改单个熔纤盘
	* 
	* @Title: DiscinfoService.java 
	* @Description: TODO 
	* @param @param discinfo
	* @return void
	* @author fl
	* @date 2017年12月19日 下午4:09:34
	* @version V1.0
	 */
	int update(Discinfo discinfo);

	/**
	 * 查询机柜中该设施可选点
	* @Title: queryDiscinfoByConditions 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param devId
	* @param @param otherDevId
	* @param @return    入参
	* @return List<DiscinfoMobile>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年8月27日 下午4:13:53 
	* @version V1.0
	 */
	List<DiscinfoMobile> queryDiscinfoByConditions(Long devId, Long otherDevId);

}
