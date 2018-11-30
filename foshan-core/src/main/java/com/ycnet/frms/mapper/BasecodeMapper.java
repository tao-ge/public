package com.ycnet.frms.mapper;

import java.util.List;
import java.util.Map;

import com.ycnet.frms.bean.Basecode;

public interface BasecodeMapper {
    int deleteByPrimaryKey(Long codeId);

    int insert(Basecode record);

    int insertSelective(Basecode record);

    Basecode selectByPrimaryKey(Long codeId);

    int updateByPrimaryKeySelective(Basecode record);

    int updateByPrimaryKey(Basecode record);
    
    List<Basecode> selectSimilar(String classCode);
    
    List<Basecode> getBaseCodeList(Basecode basecode);
    
    List<Basecode> getClasscode();

	Basecode basecodeValueCodeVerify(Basecode basecode);

	//查询分页  2017-9-26 刘沧海
	List<Basecode> queryBaseCodeList(Map<String, Object> map);
	
	//查询分页  2017-9-26 刘沧海
	int queryBaseCodeCount(Map<String, Object> map);

	//导出数据库  刘沧海 2017/10/13
	List<Basecode> queryList();

	/**
	 * 产权性质查询
	* 
	* @Title: BasecodeMapper.java 
	* @Description: TODO 
	* @param @return
	* @return List<Basecode>
	* @author fl
	* @date 2018年1月30日 下午4:35:55
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
	/**
	 * 
	* @Title: getplatformListByValueVode 
	* @Description: 根据code查询平台类型
	* @param @return    入参
	* @return Basecode    返回类型
	* @author 周宇（作者） 
	* @throws
	* @date 2018年2月11日 下午2:19:52 
	* @version V1.0
	 * @param valueCode 
	 */
	Basecode getplatformListByValueVode(String valueCode);
}