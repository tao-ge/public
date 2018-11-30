package com.ycnet.frms.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Colors;
import com.ycnet.frms.bean.Piping;
import com.ycnet.frms.bean.PipingCable;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.bean.Well;
import com.ycnet.frms.vo.PipingBean;
import com.ycnet.frms.vo.PipingSectionBean;
import com.ycnet.frms.vo.WellBean;

/**
 * 
* @ClassName: WellService 
* @Description: TODO(井，管道专用接口) 
* @author (刘沧海)  
* @date 2017年12月21日 下午3:03:01 
* @version V1.0
 */
public interface WellService {

	/**
	 *  井信息（关联每个井对应的管道段）
	* 
	* @Title: WellService.java 
	* @Description: TODO 
	* @param @param wellName
	* @param @param distance
	* @param @param curLng
	* @param @param curLat
	* @param @return
	* @return List<Well>
	* @author fl
	* @date 2017年12月21日 下午3:19:22
	* @version V1.0
	 * @param wellType 
	 * @param pb 
	 * @param users 
	 */
	List<WellBean> queryWells(WellBean well,Long distance,PageBean pb, Users users);

	/**
	 * 
	* @Title: saveWellFacility 
	* @Description: 保存和修改井设施 
	* @param @param well
	* @param @param users
	* @param @return    
	* @return int
	* @author liucanghai 
	* @throws
	* @date 2017年12月22日 上午10:45:45 
	* @version V1.0
	 * @param request 
	 */
	int saveWellFacility(Well well, Users users, HttpServletRequest request);

	/**
	 * 查询井信息
	* 
	* @Title: WellService.java 
	* @Description: TODO 
	* @param @param wellId
	* @param @return
	* @return Well
	* @author fl
	* @date 2017年12月22日 上午10:46:21
	* @version V1.0
	 * @param users 
	 */
	WellBean queryWellByWellId(Long wellId, Users users);

	/**
	 * 
	* @Title: savePiping 
	* @Description:添加修改管孔信息 
	* @param @param piping
	* @param @param users
	* @param @return    
	* @return int
	* @author liucanghai 
	* @throws
	* @date 2017年12月22日 上午11:02:11 
	* @version V1.0
	 */
	int savePiping(Piping piping, Users users);

	/**
	 * 根据入参,删除光缆段
	* 
	* @Title: WellService.java 
	* @Description: TODO 
	* @param @param sectId
	* @param @param pipingId
	* @param @param subtubeId
	* @param @return
	* @return int
	* @author fl
	* @date 2017年12月22日 下午4:53:43
	* @version V1.0
	 * @param sectState 
	 * @param session 
	 */
	int deletepipingCableByThings(PipingCable pipingCable,Users users);

	/**
	 * 根据入参,恢复光缆段
	* 
	* @Title: WellService.java 
	* @Description: TODO 
	* @param @param sectId
	* @param @param pipingId
	* @param @param subtubeId
	* @param @return
	* @return int
	* @author fl
	* @date 2017年12月22日 下午5:37:04
	* @version V1.0
	 * @param users 
	 */
	int recoverpipingCable(PipingCable pipingCable, Users users);

	/**
	 * 
	* @Title: savePipingSection 
	* @Description: 添加和修改管孔信息 
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2017年12月22日 下午12:13:58 
	* @version V1.0
	 */
	int savePipingSection(PipingSectionBean bean, Users users);
	/**
	 * 
	* @Title: queryPipingSectByPipingSectId 
	* @Description: 根据管道段id查询管孔信息列表
	* @param @return    入参
	* @return Object    返回类型
	* @author zhouyu 
	* @throws
	* @date 2017年12月22日 上午11:10:30 
	* @version V1.0
	 * @param orgId 
	 * @param wellId 
	 */
	PipingSectionBean queryPipingSectByPipingSectId(Long pipingSectId, Long orgId, Long wellId);
	/**
	 * 
	* @Title: modifWell 
	* @Description: 删除井设施 
	* @param @param session
	* @param @param well
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2017年12月22日 下午4:53:23 
	* @version V1.0
	 */
	int modifWell(WellBean bean, Users users);

	/**
	 * 
	* @Title: recoverWell 
	* @Description: 恢复井设施 
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2017年12月22日 下午5:57:06 
	* @version V1.0
	 */
	int recoverWell(Long wellId, Users users);

	/**
	 * 
	* @Title: deletePipingSectionByPipingId 
	* @Description: 删除管孔信息 
	* @param @param pipingId
	* @param @return    入参
	* @return Object    返回类型
	* @author 周宇
	* @throws
	* @date 2017年12月22日 下午5:40:41 
	* @version V1.0
	 * @param users 
	 */
	int deletePipingSectionByPipingId(Long pipingId,String pipingState, Users users);
	/**
	 * 
	* @Title: deletePipingSectionByPipingId 
	* @Description: 恢复管孔信息 
	* @param @param pipingId
	* @param @return    入参
	* @return Object    返回类型
	* @author 周宇
	* @throws
	* @date 2017年12月22日 下午5:40:41 
	* @version V1.0
	 */
	int recorePipingSectionByPipingId(Long pipingId, Users users);

	/**
	 * 
	* @Title: deletePipingSect 
	* @Description: 删除管道段 
	* @param @param pipings
	* @param @param users
	* @param @return    
	* @return int
	* @author liucanghai 
	* @throws
	* @date 2017年12月27日 上午10:21:40 
	* @version V1.0
	 */
	int deletePipingSect(PipingSectionBean bean, Users users);

	/**
	 * 
	* @Title: recorePipingSect 
	* @Description: 恢复管道段 
	* @param @param session
	* @param @param bean
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2017年12月27日 上午10:43:26 
	* @version V1.0
	 */
	int recorePipingSect(PipingSectionBean bean, Users users);
	/**
	 * 
	* @Title: change 
	* @Description: 数字转中文 
	* @param @param wells
	* @param @return    入参
	* @return List<WellBean>    返回类型
	* @author zhouyu
	* @throws
	* @date 2018年1月3日 下午4:22:30 
	* @version V1.0
	 */
	List<WellBean> change(List<WellBean> wells);

	/**
	 * 
	* @Title: pipingSectionList 
	* @Description: 根据井ID查询管道段信息 
	* @param @param wellId
	* @param @return    
	* @return String
	* @author liucanghai 
	* @throws
	* @date 2018年1月3日 下午3:30:32 
	* @version V1.0
	 */
	List<PipingSectionBean> querypipingSectionList(Long wellId);
	/**
	 * 
	 * @Title: bindCablePipingAndSubtube
	 * @Description: 绑定光缆段与子管(或者管孔)
	 * @param @param sectId
	 * @param @param pipingId
	 * @param @param subtubeId
	 * @param @param pipingSectId
	 * @param @param user
	 * @param @return 
	 * @return int 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年1月2日 下午4:16:27
	 * @version V1.0
	 * @param colorId 
	 */
	int bindCablePipingAndSubtube(PipingCable pipingCable, Long colorId, Users user,HttpServletRequest request);

	Well selectByPrimaryKey(Long wellId);

	/**
	 * 
	* @Title: queryCableSectionImage 
	* @Description: 根据管孔ID查询光缆段信心和图片 
	* @param @param piping
	* @param @param session
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年1月22日 上午10:40:13 
	* @version V1.0
	 */
	PipingBean queryCableSectionImage(Long pipingId, Users user);
	/**
	 * 
	* @Title: updateWellById 
	* @Description: 根据井id更改井信息
	* @param @return    入参
	* @return int    返回类型
	* @author zhouyu
	* @throws
	* @date 2018年1月23日 上午11:12:31 
	* @version V1.0
	 */
	int updateWellById(Long wellId);

	/**
	 * 
	* @Title: queryColor 
	* @Description: 查询颜色 
	* @param @return    
	* @return List<Color>
	* @author liucanghai 
	* @throws
	* @date 2018年1月24日 上午10:49:43 
	* @version V1.0
	 */
	List<Colors> queryColor();

	/**
	 * 
	 * @Title: queryPipingCableBySectId
	 * @Description: 根据sectId查询光缆段绑定子管表
	 * @param @param sectId
	 * @param @return 
	 * @return PipingCable 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年2月7日 下午3:47:18
	 * @version V1.0
	 * @param pipingId 
	 */
	PipingCable queryPipingCableBySectId(Long sectId, Long pipingId);

	/**
	 * 
	* @Title: queryAllPipelineByWellId 
	* @Description: 根据井ID查询整个管道 
	* @param @return    
	* @return Object
	* @author liucanghai 
	* @throws
	* @date 2018年3月1日 上午9:54:53 
	* @version V1.0
	 */
	List<WellBean> queryAllPipelineByWellId(Long wellId, Users user);

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
}
