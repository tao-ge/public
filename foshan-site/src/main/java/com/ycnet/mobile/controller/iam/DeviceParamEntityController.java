package com.ycnet.mobile.controller.iam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ycnet.core.config.SessionName;
import com.ycnet.core.util.AppRequestEntity;
import com.ycnet.core.util.AppResponseEntity;
import com.ycnet.frms.bean.DeviceParamEntity;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.service.DeviceParamService;
import com.ycnet.frms.service.DeviceService;
import com.ycnet.frms.vo.mobile.DeviceParamMobile;


/**
 * 设备参数控制器
 * @author FL
 *
 */
@RestController
@RequestMapping("/iam")
public class DeviceParamEntityController {
	
	@Resource
	private DeviceParamService deviceParamService;
	
	@Resource
	private DeviceService deviceService;
	
	/**
	 * 根据orgId,查询设备参数信息
	* @Title: querydeviceParamByOrgId 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgId
	* @param @param request
	* @param @return    入参
	* @return Object    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年4月13日 上午10:17:02 
	* @version V1.0
	 */
	@RequestMapping("/querydeviceParamByOrgId.htm")
	public Object querydeviceParamByOrgId(HttpServletRequest request,@RequestBody AppRequestEntity entity) {
		Long orgId = Long.parseLong(entity.getDto().getOrDefault("orgId", -999L).toString());
		Long devId = Long.parseLong(entity.getDto().getOrDefault("devId", "0").toString());
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("querydeviceParamByOrgId");
		try {
			Users user=(Users) request.getSession().getAttribute(SessionName.mobile);
			if (orgId ==null || orgId==-999L) {
				r.setCode("0");
				r.setMessage("机构ID不能为空!");
				return r;
			}
			if (user !=null) {
				DeviceParamMobile deviceParamEntity=deviceParamService.querydeviceParamByOrgId(orgId);
				if (deviceParamEntity !=null) {
					if(devId > 0) {
						deviceParamEntity.setImDeviceNum(deviceService.queryImDeviceCountByDevId(devId));
					}
					r.setCode("1");
					r.setMessage("查询成功!");
					r.setDto(deviceParamEntity);
				}else {
					r.setCode("0");
					r.setMessage("未查询到数据!");
				}
			}else {
				r.setCode("0");
				r.setMessage("用户未登录!");
			}
		} catch (Exception e) {
			r.setCode("0");
			r.setMessage("操作失败!");
			e.printStackTrace();
		}
		return r;
	}
	
}
