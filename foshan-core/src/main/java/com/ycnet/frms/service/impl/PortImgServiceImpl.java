package com.ycnet.frms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ycnet.frms.bean.Fiberdisc;
import com.ycnet.frms.bean.PortImg;
import com.ycnet.frms.mapper.FiberdiscMapper;
import com.ycnet.frms.mapper.PortImgMapper;
import com.ycnet.frms.service.PortImgService;

@Service("portImgService")
public class PortImgServiceImpl implements PortImgService {

	@Resource(name = "portImgMapper")
	private PortImgMapper portImgMapper;

	@Resource(name = "fiberdiscMapper")
	private FiberdiscMapper fiberdiscMapper;

	/**
	 * 保存端子图片
	* 
	* @Title: PortImgService.java 
	* @Description: TODO 
	* @param @param 
	*            fiberdisc
	* @param @param 
	*            images
	* @param @return
	* @return int
	* @author fl
	* @date 2017年12月3日 下午3:23:57
	* @version V1.0
	 */
	@Override
	public int insertPortImg(Fiberdisc fiberdisc, List<PortImg> list) {
		int num2 = 0;
//		if (fiberdisc.getDevId() != null) {
			for (int i = 0; i < list.size(); i++) {
				PortImg portImg = list.get(i);
				portImg.setPort01(fiberdisc.getPort01());
				Fiberdisc fiberDisc = fiberdiscMapper.queryId(fiberdisc.getPort01());
				portImg.setDevId(fiberDisc.getDevId());
				portImg.setFlag("1");
				num2 = portImgMapper.insertSelective(portImg);
			}
//		} else {
//			num2 = 0;
//		}
		return num2;
	}

	/**
	 * 删除库中的路径
	 * 
	 * @Title: PortImgService.java
	 * @Description: TODO
	 * @param @param
	 *            path
	 * @return void
	 * @author fl
	 * @date 2017年12月15日 下午5:01:49
	 * @version V1.0
	 */
	@Override
	public int deleteByUrl(String path) {
		return portImgMapper.deleteByUrl(path);
	}

}
