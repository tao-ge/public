package com.ycnet.frms.service;

import com.ycnet.frms.bean.DiscinfoEntity;
import com.ycnet.frms.bean.FacilityEntity;
import com.ycnet.frms.bean.FiberdiscGroupEntity;

public interface DiscinfoEntityService {

	/**
	 * 
	 * @Title: addDiscInfo
	 * @Description: 添加熔纤盘
	 * @param @param discinfo
	 * @param @param index
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月13日 上午8:47:20
	 * @version V1.0
	 */
	int addDiscInfo(DiscinfoEntity discinfo, Integer index);

	/**
	 * 
	 * @Title: updateByPrimaryKeySelective
	 * @Description: 修改熔纤盘
	 * @param @param discinfo
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月13日 上午8:53:04
	 * @version V1.0
	 */
	int updateByPrimaryKeySelective(DiscinfoEntity discinfo);

	/**
	 * 
	 * @Title: insert
	 * @Description: 添加熔纤盘
	 * @param @param discinfo
	 * @param @param index
	 * @param @param object
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月13日 上午9:00:43
	 * @version V1.0
	 */
	int insert(DiscinfoEntity discinfo, Integer index, String model);

	/**
	 * 
	 * @Title: genDiscinfo
	 * @Description: 自动生成熔纤盘信息
	 * @param @param group
	 * @param @param facility
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月13日 上午11:38:05
	 * @version V1.0
	 */
	int genDiscinfo(FiberdiscGroupEntity group, FacilityEntity facility);

}
  
    