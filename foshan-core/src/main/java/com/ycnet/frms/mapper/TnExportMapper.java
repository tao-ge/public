package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.Fiberdisc;
import com.ycnet.frms.bean.FiberdiscGroup;
import com.ycnet.frms.bean.TnExport;
import com.ycnet.frms.vo.FacilityAll;
import com.ycnet.frms.vo.Group;

public interface TnExportMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TnExport record);

    int insertSelective(TnExport record);
    
    int insertSelective01(TnExport record);

    TnExport selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TnExport record);
    
    int updateByPrimaryKeySelective2(TnExport record);

    int updateByPrimaryKey(TnExport record);
    
    List<TnExport> selectByparam(String routeType);
    
    int deleteByParam(String routeType);
    
    List<TnExport> selectByparams(String routeType);
    
    TnExport selectByRouteName(String routeName);
    
    List<TnExport> selectByparamByType(String routeType);
    
    List<FacilityAll> labExport();
    
    List<FiberdiscGroup> FGlist(Long devId,String side);
    
    List<Fiberdisc> selectFiber(String code);
    
    /**
     * 根据数据查询重复数据
     * @author: YHT
     * @date: 2017年10月19日 下午4:59:56 
     * @Title: selectByTnExport  
     * @param @param export
     * @param @return     
     * @return List<TnExport>   
     * @throws
     */
    List<TnExport> selectByTnExport(TnExport export);
    
    /**
     * 查询TnExport01中所有数据
     * @author: YHT
     * @date: 2017年10月20日 上午11:27:29 
     * @Title: selectTnExport01  
     * @param @return     
     * @return List<TnExport>   
     * @throws
     */
    List<TnExport> selectTnExport01();
    
    /**
     * 删除TnExport01中所有数据
     * @author: YHT
     * @date: 2017年10月20日 上午11:28:12 
     * @Title: deleteTnExport01  
     * @param @return     
     * @return int   
     * @throws
     */
    int deleteTnExport01();
    
    /**
     * 查询光缆接头
     * @author: YHT
     * @date: 2017年10月20日 下午3:48:07 
     * @Title: selectJT  
     * @param @return     
     * @return List<Facility>   
     * @throws
     */
    List<TnExport> selectJT();
    /**
     * 
    * @Title: queryList 
    * @Description: TODO(查询A端数据表) 
    * @param @return    入参
    * @return List<TnExport>    返回类型
    * @author （刘沧海） 
    * @throws
    * @date 2017年11月8日 下午4:16:29 
    * @version V1.0
     */
	List<TnExport> queryList();
    
    /**
     * 查询Export03的信息
     */
    List<TnExport> selectExport03();
    
    /**
     * 对应传输断
    * @Title: selectByRouteId 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @param routeId
    * @param @return    入参
    * @return TnExport    返回类型
    * @author YHT（作者） 
    * @throws
    * @date 2017年11月23日 下午7:32:44 
    * @version V1.0
     */
    TnExport selectByRouteId(String routeId);
    
    
    List<TnExport> selectExport19();
    
    List<TnExport> selectExport20();
    
    List<TnExport> selectExport01();
    
    List<TnExport> selectExport11();
    
    List<TnExport> selectExport00();
    
    List<TnExport> selectExportgl();
    
    List<TnExport> selectExportguanglu();
    
    /**
     * 导入接头【专用】
    * @Title: selectTnExport04 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @return    入参
    * @return List<TnExport>    返回类型
    * @author YHT（作者） 
    * @throws
    * @date 2017年12月20日 上午11:16:38 
    * @version V1.0
     */
    List<TnExport> selectExport04();
    
    /**
     * 导入井信息（佛山）
    * @Title: selectExportWell 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @return    入参
    * @return List<TnExport>    返回类型
    * @author YHT（作者） 
    * @throws
    * @date 2018年1月30日 下午3:32:18 
    * @version V1.0
     */
    List<TnExport> selectExportWell();
    
    /**
     * 导入管道段信息（佛山）
    * @Title: selectExportPiping 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @return    入参
    * @return List<TnExport>    返回类型
    * @author YHT（作者） 
    * @throws
    * @date 2018年1月30日 下午3:32:36 
    * @version V1.0
     */
    List<TnExport> selectExportPiping();
    
    /**
     * 导入光缆段信息
    * @Title: selectExportSection 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @param @return    入参
    * @return List<TnExport>    返回类型
    * @author YHT（作者） 
    * @throws
    * @date 2018年1月30日 下午3:32:55 
    * @version V1.0
     */
    List<TnExport> selectExportSection();
    
    int deleteBy00(Long id);
    
    int deleteByGl(Long id);

    List<TnExport> selectExport();

	List<TnExport> queryRouteText();

	List<TnExport> pdhcopy();

	/**
	 * 查询汇聚并导入
	* @Title: selectExportAreaCode 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    入参
	* @return List<TnExport>    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2018年3月27日 上午10:28:41 
	* @version V1.0
	 */
	List<TnExport> selectExportAreaCode();

}