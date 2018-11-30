package com.ycnet.mobile.controller.iam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ycnet.core.FrmsException;
import com.ycnet.core.config.SessionName;
import com.ycnet.core.util.AppRequestEntity;
import com.ycnet.core.util.AppResponseEntity;
import com.ycnet.core.util.HexUtil;
import com.ycnet.frms.bean.DeviceDiscinfoEntity;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.service.DeviceDiscinfoService;
import com.ycnet.frms.service.DiscinfoService;
import com.ycnet.frms.vo.FiberdiscGroupEntityVo;
import com.ycnet.frms.vo.mobile.DeviceDiscinfoEntityMobile;
import com.ycnet.frms.vo.mobile.DiscinfoMobile;

/**
 * 端子控制器
 * @author FL
 *
 */
@RestController
@RequestMapping("/iam")
public class DeviceDiscinfoController {
	
	@Autowired
	private DeviceDiscinfoService deviceDiscinfoService;
	
	@Autowired
	private DiscinfoService discinfoService;
	
	
	/**
	 * 
	 * @Title: bandDiscAndDiscControl
	 * @Description: 端子控制器绑定熔纤盘
	 * @param @return 
	 * @return Object 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年4月13日 下午1:26:41
	 * @version V1.0
	 */
	@RequestMapping("/bandDiscAndDiscControl.htm")
	public Object bandDiscAndDiscControl(HttpServletRequest request,@RequestBody AppRequestEntity entity) {
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("bandDiscAndDiscControl");
		try {
			Long bandType = Long.parseLong(entity.getDto().getOrDefault("bandType", -999L).toString());
			Long discId = Long.parseLong(entity.getDto().getOrDefault("discId", -999L).toString());
			Long codId = Long.parseLong(entity.getDto().getOrDefault("codId", -999L).toString());
			String discContrCode = entity.getDto().getOrDefault("discContrCode", "").toString();
			String discContrId = entity.getDto().getOrDefault("discContrId", "").toString();
			Users user=(Users) request.getSession().getAttribute(SessionName.mobile);
			if (null==user) {
				r.setCode("0");
				r.setMessage("服务器用户未登录!");
				return r;
			}
			if(discId==null || discId.longValue()==-999L){
				r.setCode("0");
				r.setMessage("服务器熔纤盘ID不能为空");
				return r;
			}
			if(codId==null || codId.longValue()==-999L) {
				r.setCode("0");
				r.setMessage("服务器中控器ID不能为空");
				return r;
			}
			if(discContrCode==null || "".equals(discContrCode.trim())){
				r.setCode("0");
				r.setMessage("服务器端子控制器编码不能为空");
				return r;
			}
			if(discContrId==null || "".equals(discContrId.trim())){
				r.setCode("0");
				r.setMessage("服务器检测板编码不能为空");
				return r;
			}
			if(discContrCode != null && !"".equals(discContrCode.trim()) && discContrCode.length()>200) {
				r.setCode("0");
				r.setMessage("服务器端子控制器编码过长");
				return r;
			}
			DeviceDiscinfoEntity dd=new DeviceDiscinfoEntity();
			dd.setDiscId(discId);
			dd.setCodId(codId);
			//String contrCode = HexUtil.encode(discContrCode);
			dd.setDiscContrCode(discContrCode);
			dd.setDiscContrId(discContrId);
			int req = deviceDiscinfoService.band(dd,user,bandType);
			if(req>0) {
				r.setCode("1");
				r.setMessage("服务器绑定成功");
			}else {
				r.setCode("0");
				r.setMessage("服务器绑定失败");
			}
		} catch (FrmsException e1) {
			r.setCode("0");
			r.setMessage("服务器" + e1.getMessage());
			e1.printStackTrace();
		} catch (Exception e) {
			r.setCode("0");
			r.setMessage("服务器绑定失败");
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 
	 * @Title: deleteDeviceDiscinfo
	 * @Description: 删除端子控制器
	 * @param @param request
	 * @param @param entity
	 * @param @return 
	 * @return Object 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年7月9日 下午4:03:45
	 * @version V1.0
	 */
	@RequestMapping("/deleteDeviceDiscinfo.htm")
	public Object deleteDeviceDiscinfo(HttpServletRequest request,@RequestBody AppRequestEntity entity) {
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("deleteDeviceDiscinfo");
		try {
			Long discId = Long.parseLong(entity.getDto().getOrDefault("discId", -999L).toString());
			Users user=(Users) request.getSession().getAttribute(SessionName.mobile);
			if (null==user) {
				r.setCode("0");
				r.setMessage("服务器用户未登录!");
				return r;
			}
			if(discId==null || discId.longValue()==-999L){
				r.setCode("0");
				r.setMessage("服务器端子控制器ID不能为空");
				return r;
			}
			int req = deviceDiscinfoService.deleteDeviceDiscinfo(discId,user);
			if(req>0) {
				r.setCode("1");
				r.setMessage("服务器删除成功");
			}else {
				r.setCode("0");
				r.setMessage("服务器删除失败");
			}
		} catch (Exception e) {
			r.setCode("0");
			r.setMessage("服务器删除失败");
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 根据盘ID,刷新熔纤盘
	* @Title: pushDeviceDiscinfoByDiscId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    入参
	* @return Object    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年5月31日 上午11:01:25 
	* @version V1.0
	 */
	@RequestMapping("/pushDeviceDiscinfoByDiscId.htm")
	public Object pushDeviceDiscinfoByDiscId(HttpServletRequest request,@RequestBody AppRequestEntity entity) {
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("pushDeviceDiscinfoByDiscId");
		try {
			Users user=(Users) request.getSession().getAttribute(SessionName.mobile);
			if (null==user) {
				r.setCode("0");
				r.setMessage("用户未登录!");
				return r;
			}
			Long discId= Long.valueOf(entity.getDto().getOrDefault("discId", -999l).toString());
			if (null==discId || discId==-999l) {
				r.setCode("0");
				r.setMessage("盘ID不能为空!");
				return r;
			}
			List<DeviceDiscinfoEntityMobile> deviceDiscinfoEntityMobileList=deviceDiscinfoService.queryDeviceDisinfoEntityByDisId(discId);
			if (deviceDiscinfoEntityMobileList !=null && deviceDiscinfoEntityMobileList.size()>0) {
				r.setCode("1");
				r.setMessage("查询成功");
				r.setDto(deviceDiscinfoEntityMobileList);
			}else {
				r.setCode("0");
				r.setMessage("未查询导数据");
			}
		} catch (Exception e) {
			r.setCode("0");
			r.setMessage("查询失败");
			e.printStackTrace();
		}
		return r;
	}
	
	@RequestMapping("/bandDiscControl.htm")
	public Object bandDiscControl(HttpServletRequest request,@RequestBody AppRequestEntity entity) {
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("bandDiscControl");
		try {
			Long bandType = Long.parseLong(entity.getDto().getOrDefault("bandType", -999L).toString());
			Long codId = Long.parseLong(entity.getDto().getOrDefault("codId", -999L).toString());
			Long devId = Long.parseLong(entity.getDto().getOrDefault("devId", -999L).toString());
			String discContrId = entity.getDto().getOrDefault("discContrId", "").toString();
			Users user=(Users) request.getSession().getAttribute(SessionName.mobile);
			if (null==user) {
				r.setCode("0");
				r.setMessage("服务器用户未登录!");
				return r;
			}
			if(devId==null || devId.longValue()==-999L){
				r.setCode("0");
				r.setMessage("服务器设施ID不能为空");
				return r;
			}
			if(codId==null || codId.longValue()==-999L) {
				r.setCode("0");
				r.setMessage("服务器中控器ID不能为空");
				return r;
			}
			if(discContrId==null || "".equals(discContrId.trim())){
				r.setCode("0");
				r.setMessage("服务器检测板编码不能为空");
				return r;
			}
			
			DeviceDiscinfoEntity dd=new DeviceDiscinfoEntity();
			dd.setCodId(codId);
			dd.setDevId(devId);
			dd.setDiscContrId(discContrId);
			int req = deviceDiscinfoService.bandContr(dd,user,bandType);
			if(req==1) {
				r.setCode("1");
				r.setMessage("服务器绑定成功");
			}else if(req==2){
				r.setCode("1");
				r.setMessage("服务器拆除成功");
			}else {
				r.setCode("0");
				r.setMessage("操作失败");
			}
		} catch (FrmsException e1) {
			r.setCode("0");
			r.setMessage("服务器" + e1.getMessage());
			e1.printStackTrace();
		} catch (Exception e) {
			r.setCode("0");
			r.setMessage("服务器绑定失败");
			e.printStackTrace();
		}
		return r;
	}
	
}
