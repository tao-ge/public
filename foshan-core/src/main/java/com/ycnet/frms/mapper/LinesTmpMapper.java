package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.frms.bean.Lines;
import com.ycnet.frms.vo.LinesTmp;

public interface LinesTmpMapper {
	
    int deleteByPrimaryKey(Long lineId);
    
    int delete1(String lineType,Long sectId);

    int insert(Lines record);

    int insertSelective(Lines record);

    Lines selectByPrimaryKey(Long lineId);

    int updateByPrimaryKeySelective(Lines record);

    int updateByPrimaryKey(Lines record);
	
	List<LinesTmp> selectFGBy01(Long orgId);
	
	LinesTmp selectTXByCode(String code,Long orgId,String areaCode);
	
	LinesTmp selectGXByCode(String code,Long orgId,String areaCode);
	
	List<LinesTmp> selectFGListByCode(String code,Long orgId);
	
	LinesTmp selectZCLines(Long orgId);
	
	/**
	 * 手动生成光路（专用）
	* @Title: selectZCLinesList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @return    入参
	* @return List<LinesTmp>    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年1月3日 上午9:00:16 
	* @version V1.0
	 */
	List<LinesTmp> selectZCLinesList(Long orgId);
	
	int deleteByCode(LinesTmp line);
	
	/**
	 * 光纤信息添加临时表
	 * @author: YHT
	 * @date: 2017年9月7日 上午11:48:43 
	 * @Title: insertByOrgId01  
	 * @param @param orgId
	 * @param @param areaCode
	 * @param @return     
	 * @return int   
	 * @throws
	 */
	int insertByOrgId01(Long orgId,String areaCode);
	
	/**
	 * 跳纤分光器信息添加临时表
	 * @author: YHT
	 * @date: 2017年9月7日 上午11:49:28 
	 * @Title: insertByOrgId  
	 * @param @param orgId
	 * @param @param areaCode
	 * @param @return     
	 * @return int   
	 * @throws
	 */
	int insertByOrgId(Long orgId,String areaCode);
	
	int selectByOrgId(Long orgId);
	
	int deleteByLine();
	
	int selectByCodeA(String code);
	
	int selectByCodeZ(String code);
	
	/**
	 * 查询能否生成光路
	 * @author: YHT
	 * @date: 2017年9月26日 上午11:57:14 
	 * @Title: selectExitTXbyCode  
	 * @param @param code
	 * @param @param orgId
	 * @param @return     
	 * @return int   
	 * @throws
	 */
	int selectExitTXbyCode(String code,Long orgId);
	
	/**
	 * 查询跳纤另一端子编号
	 * @author: YHT
	 * @date: 2017年10月10日 下午4:27:00 
	 * @Title: selectCodeByTX  
	 * @param @param code
	 * @param @param orgId
	 * @param @return     
	 * @return LinesTmp   
	 * @throws
	 */
	LinesTmp selectCodeByTX(String code,Long orgId);
	
	/**
	 * 查询光纤另一端子编号
	 * @author: YHT
	 * @date: 2017年10月10日 下午4:27:57 
	 * @Title: selectCodeByGX  
	 * @param @param code
	 * @param @param orgId
	 * @param @return     
	 * @return LinesTmp   
	 * @throws
	 */
	LinesTmp selectCodeByGX(String code,Long orgId);
	
	/**
	 * 根据分光器末端查询起始端子
	 * @author: YHT
	 * @date: 2017年10月10日 下午4:28:16 
	 * @Title: selectCodeByFGB  
	 * @param @param code
	 * @param @return     
	 * @return String   
	 * @throws
	 */
	String selectCodeByFGB(String code);
	
	/**
	 * 根据分光器中端查询起始端子
	 * @author: YHT
	 * @date: 2017年10月10日 下午4:29:02 
	 * @Title: selectCodeByFG00  
	 * @param @param code
	 * @param @return     
	 * @return String   
	 * @throws
	 */
	String selectCodeByFG00(String code);
	
	/**
	 * 单点生成 - 查询跳纤一端信息
	 * @author: YHT
	 * @date: 2017年10月10日 下午4:30:05 
	 * @Title: selectTXByCode01  
	 * @param @param code
	 * @param @param orgId
	 * @param @return     
	 * @return LinesTmp   
	 * @throws
	 */
	LinesTmp selectTXByCode01(String code,Long orgId);
	
	/**
	 * 单点生成 - 查询成端一端信息
	 * @author: YHT
	 * @date: 2017年10月10日 下午4:31:32 
	 * @Title: selectGXByCode01  
	 * @param @param code
	 * @param @param orgId
	 * @param @return     
	 * @return LinesTmp   
	 * @throws
	 */
	LinesTmp selectGXByCode01(String code,Long orgId);
	/**
	 * 根据分光器A点查询B点
	 * @author: YHT
	 * @date: 2017年10月11日 上午10:48:45 
	 * @Title: selectFGByCode  
	 * @param @param code
	 * @param @param orgId
	 * @param @return     
	 * @return LinesTmp   
	 * @throws
	 */
	List<LinesTmp> selectFGByCode(String code,Long orgId);
}