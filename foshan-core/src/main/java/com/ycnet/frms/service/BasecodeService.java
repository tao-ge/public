package com.ycnet.frms.service;

import java.util.List;
import java.util.Map;

import com.ycnet.core.CodeTable;
import com.ycnet.core.util.KVBean;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Basecode;

public interface BasecodeService {

	/**
	 * 分光器类型
	 * @return
	 */
	Map<String,String> getOBDTypeMap();
	
	
	
	List<KVBean> getOBDTYPEList();
	
	List<Basecode> getCommTypeMap(CodeTable t);
	/**
	 * 设施类型
	 * */
	
	Map<String,String> getDEVTypeMap();
	List<KVBean> getDEVTypeList();
	
	/**
	 * 图片类型
	 */
	Map<String,String> getIMGTypeMap();
	List<KVBean> getIMGTypeList();
	
	List<Basecode> getBaseCodeList(Basecode basecode);
	/**
	 * 所有分类
	 */
	List<KVBean> getClasscode();
	
	int insert(Basecode basecode);
	
	Basecode selectByPrimaryKey(Long codeId);
	
	int updateByPrimaryKey(Basecode basecode);
	
	int updateByPrimaryKeySelective(Basecode record);
	
	int deleteByPrimaryKey(Long codeId);



	Basecode basecodeValueCodeVerify(Basecode basecode);


	//数据字典添加分页,刘沧海,2017-9-26
	PageBean queryBaseCode(Basecode basecode, PageBean pb);


	//导出数据库 刘沧海 2017/10/13
	List<Basecode> queryList();
	
	/**
	 * 查询管道段
	 * @return
	 */
	List<KVBean> getPipeTypes();


	/**
	 * 
	* 产权性质查询
	* @Title: BasecodeService.java 
	* @Description: TODO 
	* @param @return
	* @return List<Basecode>
	* @author fl
	* @date 2018年1月30日 下午4:34:53
	* @version V1.0
	 */
	List<Basecode> querynopTypes();


	/**
	 * 
	* @Title: getplatformList 
	* @Description: 查询所有平台类型
	* @param @return    入参
	* @return List<String>    返回类型
	* @author zhouyu 
	* @throws
	* @date 2018年2月7日 下午4:01:40 
	* @version V1.0
	 */
	List<Basecode> getplatformList();

}
