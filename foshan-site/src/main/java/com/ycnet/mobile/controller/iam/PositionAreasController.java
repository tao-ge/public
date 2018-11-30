package com.ycnet.mobile.controller.iam;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ycnet.core.config.SessionName;
import com.ycnet.core.util.AppRequestEntity;
import com.ycnet.core.util.AppResponseEntity;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.service.PositionAreasService;
import com.ycnet.frms.vo.CityBean;
import com.ycnet.frms.vo.CountyBean;
import com.ycnet.frms.vo.ProvinceBean;
import com.ycnet.frms.vo.TownBean;

@RestController
@RequestMapping("/iam")
public class PositionAreasController {

	@Resource(name="positionAreasService")
	private PositionAreasService positionAreasService;
	
	/**
	 * 
	 * @Title: queryProvinceById
	 * @Description: 查询省
	 * @param @param session
	 * @param @param entity
	 * @param @return 
	 * @return Object 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月15日 下午3:02:30
	 * @version V1.0
	 */
	@RequestMapping("/queryAllProvince.htm")
	public Object queryAllProvince(HttpSession session ,@RequestBody AppRequestEntity entity) {
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("queryAllProvince");
		try {
			Users user = (Users) session.getAttribute("users");
			if(null == user) {
				r.setCode("0");
				r.setMessage("用户未登录或不存在！");
				return r;
			}
			List<ProvinceBean> pList = positionAreasService.queryAllProvince();
			r.setCode("1");
			r.setMessage("查询成功");
			r.setDto(pList);
		} catch (NumberFormatException e) {
			r.setCode("0");
			r.setMessage("查询失败");
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 
	 * @Title: queryCityById
	 * @Description: 根据ID查询市
	 * @param @param session
	 * @param @param entity
	 * @param @return 
	 * @return Object 入参
	 * @return String    返回类型
	 * @author DZY 
	 * @throws
	 * @date 2018年6月15日 下午3:32:50
	 * @version V1.0
	 */
	@RequestMapping("/queryCityById.htm")
	public Object queryCityById(HttpSession session ,@RequestBody AppRequestEntity entity) {
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("queryCityById");
		try {
			String provinceId = entity.getDto().getOrDefault("provinceId", "").toString();
			Users user = (Users) session.getAttribute("users");
			if(null == user) {
				r.setCode("0");
				r.setMessage("用户未登录或不存在！");
				return r;
			}
			if(provinceId == null || "".equals(provinceId.trim())) {
				r.setCode("0");
				r.setMessage("省ID不可为空");
				return r;
			}
			List<CityBean> cityList = positionAreasService.queryCityById(provinceId);
			r.setCode("1");
			r.setMessage("查询成功");
			r.setDto(cityList);
		} catch (NumberFormatException e) {
			r.setCode("0");
			r.setMessage("查询失败");
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 根据市查区
	* @Title: queryCountyById 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    入参
	* @return Object    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月15日 下午3:00:43 
	* @version V1.0
	 */
	@RequestMapping("/queryCountyById.htm")
	public Object queryCountyById(@RequestBody AppRequestEntity entity,HttpSession session) {
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("queryCountyById");
		try {
			String cityId = entity.getDto().getOrDefault("cityId", "").toString();
			Users user=(Users)session.getAttribute(SessionName.mobile);
			if (null==user) {
				r.setCode("0");
				r.setMessage("用户未登录!");
				return r;
			}
			if (null==cityId || "".equals(cityId.trim())) {
				r.setCode("0");
				r.setMessage("市ID不能为空!");
				return r;
			}
			List<CountyBean> countyList=positionAreasService.queryByCityId(cityId);
			if (countyList!=null && countyList.size()>0) {
				r.setCode("1");
				r.setMessage("查询成功!");
				r.setDto(countyList);
			}else {
				r.setCode("0");
				r.setMessage("未查询到数据!");
			}
		} catch (Exception e) {
			r.setCode("0");
			r.setMessage("查询失败!");
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 根据区查镇
	* @Title: queryTownById 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param entity
	* @param @param session
	* @param @return    入参
	* @return Object    返回类型
	* @author FL（作者） 
	* @throws
	* @date 2018年6月15日 下午3:27:08 
	* @version V1.0
	 */
	@RequestMapping("/queryTownById.htm")
	public Object queryTownById(@RequestBody AppRequestEntity entity,HttpSession session) {
		AppResponseEntity r = new AppResponseEntity();
		r.setTranCode("queryTownById");
		try {
			String countyId = entity.getDto().getOrDefault("countyId", "").toString();
			Users user=(Users)session.getAttribute(SessionName.mobile);
			if (null==user) {
				r.setCode("0");
				r.setMessage("用户未登录!");
				return r;
			}
			if (null==countyId || "".equals(countyId.trim()) ) {
				r.setCode("0");
				r.setMessage("市ID不能为空!");
				return r;
			}
			List<TownBean> townList=positionAreasService.queryTownById(countyId);
			if (townList!=null && townList.size()>0) {
				r.setCode("1");
				r.setMessage("查询成功!");
				r.setDto(townList);
			}else {
				r.setCode("0");
				r.setMessage("未查询到数据!");
			}
		} catch (Exception e) {
			r.setCode("0");
			r.setMessage("查询失败!");
			e.printStackTrace();
		}
		return r;
	}
}
  
    