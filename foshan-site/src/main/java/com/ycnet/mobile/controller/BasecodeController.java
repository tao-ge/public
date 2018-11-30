package com.ycnet.mobile.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ycnet.core.CodeTable;
import com.ycnet.core.util.KVBean;
import com.ycnet.frms.bean.Basecode;
import com.ycnet.frms.service.BasecodeService;
import com.ycnet.frms.vo.GroupTemplate;
import com.ycnet.mobile.util.Result;

@RestController
public class BasecodeController {

	@Resource(name="basecodeService")
	private BasecodeService basecodeService;
	
	@RequestMapping("/m/getObdTypes.htm")
	public Object getObdTypes()
	{
		Result r = Result.get();
			r.putDtList(basecodeService.getOBDTYPEList()).initQueryMessage();
		return r;
	}
	
	@RequestMapping("/m/getFacilityTypes.htm")
	public Object getFacilityTypes()
	{
		Result r = Result.get();
		r.putDtList(basecodeService.getDEVTypeList()).initQueryMessage();
		return r;
	}
	
	@RequestMapping("/m/getTransDevTypes.htm")
	public Object getTransDevTypes(String devType){
		Result r = Result.get();
		if(devType==null||"".equals(devType)){
			r.setR_content("参数不正确。");
			return r;
		}
		devType = devType+"_TYPES";
		CodeTable ct = CodeTable.toType(devType);
		if(ct==null){
			r.setR_content("参数不正确。");
			return r;
		}
		List<Basecode> list = basecodeService.getCommTypeMap(ct);
		List<GroupTemplate> l = new ArrayList<GroupTemplate>();
		for(Basecode b:list){
			String model =null,side = null ,row=null,col=null;
			List<String> discList = null;
			GroupTemplate t = new GroupTemplate();
			model = b.getValueCode();
			
			String values = b.getValueName();
			String[] value = (values==null)?null:values.split("\\|");
			side = (value==null||value.length<1)?"":value[0];
			
			String groups=(value==null||value.length<2)?"":value[1];
			String[] group = groups.split("\\*");
			row = (group==null||group.length<1)?"":group[0];
			col = (group==null||group.length<2)?"":group[1];
			
			String discTypes = (value==null||value.length<3)?null:value[2];
			String[] discType=(discTypes==null?null:discTypes.split("\\,"));
			discList = discType==null?null:Arrays.asList(discType);
			
			String startIndex = (value==null||value.length<4)?"1":value[3];
			
			t.setModel(model);
			t.setSide(side);
			t.setRow(row);
			t.setCol(col);
			t.setDiscType(discList);
			t.setStartIndex(startIndex);
			l.add(t);
			
		}
		int size = l.size();
		r.putR(size);
		r.putDtList(size>0?l:null).initQueryMessage();
		if(size==0){
			r.putRContent("未获取到数据。");
		}
		return r;
	}
	
	@RequestMapping("/m/v1/getFacilityTypes.htm")
	public Object getFacilityTypes(Long pdevId){
		Result r = Result.get();
		List<KVBean> list = basecodeService.getDEVTypeList();
		List<KVBean> tmp = new ArrayList<KVBean>();
		for(KVBean bean :list)
		{
			String name = bean.getName();
			if("15".equals(name)||"14".equals(name)||"13".equals(name)||"11".equals(name)||"10".equals(name)||"05".equals(name)||"06".equals(name)||"07".equals(name)) //除去ODF ，分光器,和PTN
			{
				continue;
			}
			tmp.add(bean);
		}
		r.putDtList(tmp).initQueryMessage();
		return r;
	}
	
	/**
	 * 查询管道段类型
	* @Title: getPipeTypes 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    入参
	* @return Object    返回类型
	* @author YHT（作者） 
	* @throws
	* @date 2017年11月22日 下午2:20:08 
	* @version V1.0
	 */
	@RequestMapping("/m/getPipeTypes.htm")
	public Object getPipeTypes()
	{
		Result r = Result.get();
		r.putDtList(basecodeService.getPipeTypes()).initQueryMessage();
		return r;
	}
	
	public static void main(String[] args) {
		String[] a="10*13".split("\\*");
		System.out.println( a);
	}
	/**
	 * 产权性质查询
	* 
	* @Title: BasecodeController.java 
	* @Description: TODO 
	* @param @param request
	* @param @return
	* @return Object
	* @author fl
	* @date 2018年1月30日 下午4:47:27
	* @version V1.0
	 */
	@RequestMapping("/m/querynopTypes.htm")
	public Object querynopTypes(HttpServletRequest request) {
		Result r = Result.get();
		try {
			List<Basecode> list=basecodeService.querynopTypes();
			if(list!=null && list.size()>0) {
				r.putDtList(list);
				r.setR(1);
				r.setR_content("查询成功!");
			}else {
				r.setR(0);
				r.setR_content("没有查询到设施数据!");
				return r;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
}
