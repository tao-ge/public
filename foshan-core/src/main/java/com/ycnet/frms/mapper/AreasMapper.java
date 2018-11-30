package com.ycnet.frms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ycnet.frms.bean.Areas;
import com.ycnet.frms.vo.AreasBean;
import com.ycnet.frms.vo.AreasParent;
import com.ycnet.frms.vo.Position;

public interface AreasMapper {
    int deleteByPrimaryKey(String areaCode);

    int insert(Areas record);

    int insertSelective(Areas record);

    Areas selectByPrimaryKey(String areaCode);

    int updateByPrimaryKeySelective(Areas record);

    int updateByPrimaryKey(Areas record);
    

    List<Areas> selectByParent(@Param("parentAreaCode") String parentAreaCode);

    List<Areas> selectByAreaRank(Areas record);

    //导出数据库用 刘沧海 2017/10/12
	List<Areas> queryList();

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
	List<Areas> selectByParentJQ(String code);

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
	List<Areas> selectByChildrenJQ(String code);

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
	List<AreasParent> selectByParentChild(String code1);

	/**
	 * fl 根据Code 查询本汇聚去编码
	 * @param areaCode1
	 * @date 2018年2月212日 上午11:22:24 
	 * @return
	 */
	AreasParent selectByCode(String areaCode1);

	/**
	 * 查询省市区
	* @Title: queryThreeAreas 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    入参
	* @return List<AreasBean>    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月14日 下午3:11:03 
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
	* @date 2018年6月15日 上午10:40:26 
	* @version V1.0
	 */
	List<Position> queryPosition();


}
