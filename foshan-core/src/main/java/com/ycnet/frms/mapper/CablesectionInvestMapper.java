package com.ycnet.frms.mapper;

import java.util.List;

import com.ycnet.frms.bean.CablesectionInvest;

public interface CablesectionInvestMapper {
    int deleteByPrimaryKey(Long sectInvestId);

    int insert(CablesectionInvest record);

    int insertSelective(CablesectionInvest record);

    CablesectionInvest selectByPrimaryKey(Long sectInvestId);

    int updateByPrimaryKeySelective(CablesectionInvest record);

    int updateByPrimaryKey(CablesectionInvest record);
    /**
     * 查询所属直熔盘列表
    * 
    * @Title: CablesectionInvestMapper.java 
    * @Description: TODO 
    * @param @param devId
    * @param @return
    * @return List<CablesectionInvest>
    * @author fl
    * @date 2017年12月16日 上午11:21:08
    * @version V1.0
     */
	List<CablesectionInvest> queryInvestByInversId(Long investId);
	 /**
		 * 
		* @Title: deleteInvestByDevIdAndSectId 
		* @Description: 删除直熔盘光缆段 
		* @param @param sectId
		* @param @param devId
		* @param @return    入参
		* @return String    返回类型
		* @author 周宇（作者） 
		* @throws
		* @date 2017年12月16日 上午11:36:55 
		* @version V1.0
		 */
	int deleteByInvestId(Long investId);

	/**
	 * 根据上行光缆ID查询
	* @Title: selectByUpSectId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param sectId
	* @param @return    入参
	* @return List<CablesectionInvest>    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2017年12月16日 下午10:27:18 
	* @version V1.0
	 */
	List<CablesectionInvest> selectByUpSectId(Long sectId);


}