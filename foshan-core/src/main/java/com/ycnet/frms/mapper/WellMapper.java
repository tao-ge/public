package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Well;
import com.ycnet.frms.vo.WellBean;

public interface WellMapper {
    int deleteByPrimaryKey(Long wellId);

    int insert(Well record);

    /**
     * 
    * @Title: insertSelective 
    * @Description: 保存井设施 
    * @param @param record
    * @param @return    
    * @return int
    * @author liucanghai 
    * @throws
    * @date 2017年12月21日 下午3:35:03 
    * @version V1.0
     */
    int insertSelective(Well record);

    Well selectByPrimaryKey(Long wellId);

    int updateByPrimaryKeySelective(Well record);

    int updateByPrimaryKey(Well record);

    /**
     * 井信息（关联每个井对应的管道段）
    * 
    * @Title: WellMapper.java 
    * @Description: TODO 
    * @param @param wellName
    * @param @param distance
    * @param @param curLng
    * @param @param curLat
    * @param @param upper
    * @param @param wellType
    * @param @return
    * @return List<Well>
    * @author fl
    * @date 2017年12月21日 下午3:40:43
    * @version V1.0
     * @param pb 
     * @param orgId 
     */
	List<WellBean> queryWells(WellBean well,String Upper,PageBean pb, Long distance, Long orgId);

	/**
	 * 查询井信息,关联光缆段
	* 
	* @Title: WellMapper.java 
	* @Description: TODO 
	* @param @param wellId
	* @param @return
	* @return WellBean
	* @author fl
	* @date 2017年12月22日 上午11:18:32
	* @version V1.0
	 * @param orgId 
	 */
	WellBean queryWellByWellId(Long wellId, Long orgId);
	/**
	 * 
	* @Title: selectByPrimaryKeyAndOrgId 
	* @Description: 根据id和orgid查询 
	* @param @param wellId
	* @param @param orgId
	* @param @return    入参
	* @return Well    返回类型
	* @author zhouyu 
	* @throws
	* @date 2017年12月25日 下午1:23:18 
	* @version V1.0
	 */
	Well selectByPrimaryKeyAndOrgId(Long wellId, Long orgId);

	
	/**
	 * 
	* @Title: queryBySectId 
	* @Description: 根据光缆段ID查询井信息 
	* @param @param sectId
	* @param @return    
	* @return List<WellBean>
	* @author liucanghai 
	* @throws
	* @date 2018年1月3日 下午2:25:07 
	* @version V1.0
	 * @param orgId 
	 */
	List<WellBean> queryBySectId(Long sectId, Long orgId);
	/**
	 * 
	* @Title: queryWells 
	* @Description: 查询井列表
	* @param @param well
	* @param @param orgId
	* @param @param pb
	* @param @return    入参
	* @return List<WellBean>    返回类型
	* @author zhouyu 
	* @throws
	* @date 2018年1月2日 下午4:29:59 
	* @version V1.0
	 */
	List<WellBean> queryWells(WellBean well, PageBean pb);
	/**
	 * 
	* @Title: queryWellCount 
	* @Description: 查询井列表个数
	* @param @return    入参
	* @return List<WellBean>    返回类型
	* @author zhouyu 
	* @throws
	* @date 2018年1月2日 下午4:29:59 
	* @version V1.0
	 * @param pb 
	 * @param well 
	 */
	Integer queryWellCount(WellBean well, PageBean pb);

	/**
	 * 查询所有井
	* 
	* @Title: WellMapper.java 
	* @Description: TODO 
	* @param @return
	* @return List<Well>
	* @author fl
	* @date 2018年1月3日 下午2:17:32
	* @version V1.0
	 */
	List<Well> queryWell();

	/**
	 * 
	* @Title: updateWellBean 
	* @Description: 修改井设施 
	* @param @param well
	* @param @return    
	* @return long
	* @author liucanghai 
	* @throws
	* @date 2018年2月7日 上午11:27:02 
	* @version V1.0
	 */
	int updateWellBean(Well well);

	/**
	 * 
	* @Title: queryByWellId 
	* @Description: 根据井ID查询井 
	* @param @param wellId
	* @param @return    
	* @return WellBean
	* @author liucanghai 
	* @throws
	* @date 2018年3月1日 下午1:22:56 
	* @version V1.0
	 */
	WellBean queryByWellId(Long wellId);

	/**
	 * 
	* @Title: queryWellExport 
	* @Description: 条件查询井设施 
	* @param @param well
	* @param @return    
	* @return List<WellBean>
	* @author liucanghai 
	* @throws
	* @date 2018年3月28日 下午3:39:28 
	* @version V1.0
	 */
	List<WellBean> queryWellExport(WellBean well);

	/**
	 * 
	* @Title: queryWellAll 
	* @Description: 查询组织机构下的井设施 
	* @param @param orgId
	* @param @return    
	* @return List<Well>
	* @author liucanghai 
	* @throws
	* @date 2018年3月28日 下午5:36:30 
	* @version V1.0
	 */
	List<Well> queryWellAll(Long orgId);
}