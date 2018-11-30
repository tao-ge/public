package com.ycnet.system.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Basecode;
import com.ycnet.frms.service.BasecodeService;

@Controller
public class BaseCodeController {

	@Resource(name="basecodeService")
	private BasecodeService basecodeService;
	private static final Logger LOG = LoggerFactory.getLogger(BaseCodeController.class);
	
	/**
	 * 
	* @Title: baseCodeList 
	* @Description: TODO(给数据字典添加分页,并且修改查询回显功能) 
	* @param @param request
	* @param @param session
	* @param @param pb
	* @param @param basecode
	* @param @param model
	* @param @return    入参
	* @return String    返回类型
	* @author 刘沧海
	* @throws
	* @date 2017年9月26日 下午3:48:31 
	* @version V1.0
	 */
	@RequestMapping("/basecodeList.htm")
	public String baseCodeList(HttpServletRequest request ,HttpSession session ,PageBean pb, Basecode basecode,Model model){
//		List<Basecode> list = basecodeService.getBaseCodeList(basecode);
//		model.addAttribute("basecodeList",list);
		
		model.addAttribute("pb", basecodeService.queryBaseCode(basecode,pb));
		model.addAttribute("basecode",basecode);
		if(basecode.getClassCode()!=null)
		model.addAttribute("code", basecode.getClassCode());
		return "system/basecodeList";
	}
	
	@RequestMapping("/basecodeAdd.htm")
	public String baseCodeAdd(HttpServletRequest request ,HttpSession session , Basecode basecode,Model model){
		return "system/basecodeAdd";
	}
	
	@RequestMapping("/basecodeInsert.htm")
	public String baseCodeInsert(HttpServletRequest request ,HttpSession session , Basecode basecode,Model model){
		String result = "";

		// insert
		int num = basecodeService.insert(basecode);
		
		if(basecode.getDirectFlg() != null && "1".equals(basecode.getDirectFlg())){
			result = "system/basecodeAdd";
		} else {
			
			/*List<Basecode> list = basecodeService.getBaseCodeList(new Basecode());
			model.addAttribute("basecodeList", list);*/
			/**
			 * zhouyu 12/28
			 */
			Basecode basecode2 = new Basecode();
			model.addAttribute("pb", basecodeService.queryBaseCode(basecode2,new PageBean()));
			model.addAttribute("basecode",basecode2);
			
			result = "system/basecodeList";
			//basecode里只有新加的元素
		}
		return result;
	}
	
	@RequestMapping("/basecodeUp.htm")
	public String baseCodeUp(HttpServletRequest request ,HttpSession session , Basecode basecode,Model model){
		Basecode result = basecodeService.selectByPrimaryKey(basecode.getCodeId());
		model.addAttribute("basecode", result);
		return "system/basecodeUpdate";
	}
	
	@RequestMapping("/basecodeUpdate.htm")
	public String baseCodeUpdate(HttpServletRequest request ,HttpSession session , Basecode basecode,Model model){
		String result = "";

		// insert
		int num = basecodeService.updateByPrimaryKey(basecode);
		
		/*List<Basecode> list = basecodeService.getBaseCodeList(new Basecode());
		model.addAttribute("basecodeList", list);*/
		/**
		 * zhouyu 12/28
		 */
		Basecode basecode2 = new Basecode();
		model.addAttribute("pb", basecodeService.queryBaseCode(basecode2,new PageBean()));
		model.addAttribute("basecode",basecode2);
		result = "system/basecodeList";
		
		return result;
	}
	
	@RequestMapping("/basecodeDelete.htm")
	public String basecodeDelete(HttpServletRequest request ,HttpSession session , Basecode basecode,Model model){
		
		if(basecode != null){
			basecode.setManagerSign("0");
		}
		/**
		 * zhouyu 12/28
		 */
		int num = basecodeService.updateByPrimaryKeySelective(basecode);
		Basecode basecode2 = new Basecode();
		model.addAttribute("pb", basecodeService.queryBaseCode(basecode2,new PageBean()));
		model.addAttribute("basecode",basecode2);
		/*List<Basecode> list = basecodeService.getBaseCodeList(new Basecode());
		model.addAttribute("basecodeList", list);*/
		
		return "system/basecodeList";
	}
	/**
	 * 数据字典  当分类确定 代码值唯一校验 
	 * @param session
	 * @param basecode
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/basecodeValueCodeVerify.htm")
	public Object basecodeValueCodeVerify(HttpSession session , Basecode basecode,Model model){
			
		
		Basecode b = basecodeService.basecodeValueCodeVerify(basecode);
				
		return b==null?1:0;
		
	}
	
}
