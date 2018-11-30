package com.ycnet.system.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ycnet.core.util.KVBean;
import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Pages;
import com.ycnet.frms.service.PagesService;

@Controller
public class PagesController {
	
	@Resource(name="pagesService")
	private PagesService pagesService;
	private static final Logger LOG = LoggerFactory.getLogger(PagesController.class);
	
	private static String prexImagUrl ="/uploadImage";

	@RequestMapping("/pageList.htm")
	public String pageList(HttpServletRequest request ,HttpSession session ,Pages pages,Model model,PageBean pb){
		/*List<Pages> list = pagesService.getPageList(pages);
		
		for(Pages page : list){
			if(page.getParentPageId() != null){
				Pages parent = pagesService.selectByPrimaryKey(page.getParentPageId());
				page.setParentPageName(parent.getPageName());
			}
		}*/
		/**
		 * zhouyu 12/28
		 */
		model.addAttribute("pb",pagesService.queryByConditionMap(pages,pb));
		/*model.addAttribute("pageList",list);
		*/
		return "system/pageList";
	}
	
	@RequestMapping("/pageAdd.htm")
	public String pageAdd(HttpServletRequest request ,HttpSession session ,Pages pages,Model model){
		if(pages == null){
			pages = new Pages();
		}
		pages.setPageRank(1l);
		List<KVBean> list = pagesService.selectByPagesRank(pages);
		model.addAttribute("parentList", list);
		return "system/pageAdd";
	}
	
	@RequestMapping("/pageInsert.htm")
	public String pageInsert(HttpServletRequest request ,HttpSession session ,Pages pages,Model model,PageBean pb){
		
		String result = "";
		
		// 图片上传
		if(request instanceof MultipartHttpServletRequest)
		{
			MultipartHttpServletRequest req = (MultipartHttpServletRequest)request;
			MultipartFile file = req.getFile("uploadFile");
			if (file!=null&&!file.isEmpty()) {
				String image = uploadFile(file, req);
				if(image == null || "".equals(image)){
					model.addAttribute("error", "图片上传失败！");
					Pages param = new Pages();
					param.setPageRank(1l);
					List<KVBean> list = pagesService.selectByPagesRank(param);
					model.addAttribute("parentList", list);
					return "system/pageAdd";
				 } else {
					pages.setIcon(image);
				 }
			 }
		}
		
		if("1".equals(pages.getPageRank()+"")){
			pages.setParentPageId(null);
		}
		
		int num = pagesService.insertSelective(pages);
		
		if(pages.getDirectFlg() != null && "1".equals(pages.getDirectFlg())){
			Pages param = new Pages();
			param.setPageRank(1l);
			List<KVBean> list = pagesService.selectByPagesRank(param);
			model.addAttribute("parentList", list);
			model.addAttribute("pages", new Pages());
			result = "system/pageAdd";
		} else {
//			List<Pages> list = pagesService.getPageList(new Pages());
//			for(Pages page : list){
//				if(page.getParentPageId() != null){
//					Pages parent = pagesService.selectByPrimaryKey(page.getParentPageId());
//					page.setParentPageName(parent.getPageName());
//				}
//			}
//			model.addAttribute("pageList",list);
//			result = "system/pageList";
			pages.setPageName(null);
			pages.setFlag(null);
			pages.setPageRank(null);
			pages.setParentPageName(null);
			pages.setParentPageId(null);
			model.addAttribute("pb",pagesService.queryByConditionMap(pages,pb));
			result = "system/pageList";
		}
		
		return result;
	}
	
	@RequestMapping("/pageUp.htm")
	public String pageUp(HttpServletRequest request ,HttpSession session ,Pages pages,Model model){
		
		if(pages.getPageId() != null){
			Pages result = pagesService.selectByPrimaryKey(pages.getPageId());
			model.addAttribute("pages", result);
			
			pages.setPageRank(1l);
			List<KVBean> list = pagesService.selectByPagesRank(pages);
			model.addAttribute("parentList", list);
		}
		return "system/pageUpdate";
	}
	
	@RequestMapping("/pageUpdate.htm")
	public String pageUpdate(HttpServletRequest request ,HttpSession session ,Pages pages,Model model,PageBean pb){
		
		// 图片上传
		if(request instanceof MultipartHttpServletRequest)
		{
			MultipartHttpServletRequest req = (MultipartHttpServletRequest)request;
			MultipartFile file = req.getFile("uploadFile");
			if (file!=null && !file.isEmpty()) {
				String image = uploadFile(file, req);
				if(image == null || "".equals(image)){
					model.addAttribute("error", "图片上传失败！");
					Pages param = new Pages();
					param.setPageRank(1l);
					List<KVBean> list = pagesService.selectByPagesRank(param);
					model.addAttribute("parentList", list);
					return "system/pageUpdate";
				} else {
					pages.setIcon(image);
				}
			}
		}
		
		if("1".equals(pages.getPageRank()+"")){
			pages.setParentPageId(null);
		}
		
		int num = pagesService.updateByPrimaryKey(pages);
		
//		List<Pages> list = pagesService.getPageList(new Pages());
//		for(Pages page : list){
//			if(page.getParentPageId() != null){
//				Pages parent = pagesService.selectByPrimaryKey(page.getParentPageId());
//				page.setParentPageName(parent.getPageName());
//			}
//		}
//		model.addAttribute("pageList",list);
		pages.setPageName(null);
		pages.setFlag(null);
		pages.setPageRank(null);
		pages.setParentPageName(null);
		pages.setParentPageId(null);
		model.addAttribute("pb",pagesService.queryByConditionMap(pages,pb));
		return "system/pageList";
	}
	
	@RequestMapping("/pageDelete.htm")
	public String pageDelete(HttpServletRequest request ,HttpSession session ,Pages pages,Model model,PageBean pb){
		
		if(pages != null){
			pages.setFlag("0");
			//int num = pagesService.updateByPrimaryKeySelective(pages);
			int num = pagesService.deleteByPrimaryKeySelective(pages);
		}
		
//		List<Pages> list = pagesService.getPageList(new Pages());
//		for(Pages page : list){
//			if(page.getParentPageId() != null){
//				Pages parent = pagesService.selectByPrimaryKey(page.getParentPageId());
//				page.setParentPageName(parent.getPageName());
//			}
//		}
//		model.addAttribute("pageList",list);
		pages.setPageName(null);
		pages.setFlag(null);
		pages.setPageRank(null);
		pages.setParentPageName(null);
		pages.setParentPageId(null);
		model.addAttribute("pb",pagesService.queryByConditionMap(pages,pb));
		return "system/pageList";
	}
	
	/**
	 * 图片上传处理
	 * @param file
	 * @param request
	 * @return
	 */
	public static String uploadFile(MultipartFile file,MultipartHttpServletRequest request)
	{
		String path = request.getSession().getServletContext().getRealPath("/") + prexImagUrl;
		Date date = new Date();
		String fileName = date.getTime() +file.getOriginalFilename();
		String FileURL = prexImagUrl +"/ico/" +fileName;
		File parent = new File(path);
		parent.mkdirs();
		File imageFile = new File(parent , fileName );
		
		OutputStream out = null;
		
		try {
			out= new FileOutputStream(imageFile);
			out.write(file.getBytes());
			out.flush();
			return FileURL;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if (out!=null)
			{
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return "";
	}
	
	@ResponseBody
	@RequestMapping("/queryByPageName.htm")
	public Object checkPageName(String pageName) {
		List<Pages> list=pagesService.queryByPageName(pageName);
		if(list.size()>0) {
			return "no";
		}else {
			return "yes";
		}
	}
	/**
	 * 查看下级是否使用上级功能管理
	 * @author tsw
	 * @since 2017年10月27日8:45:52
	 *
	 */
	@ResponseBody
	@RequestMapping("/queryByxjname.htm")
	public Object queryByxjname(long pageId) {
		List<Pages> list=pagesService.queryByxjname(pageId);
		if(list.size()>0) {
			return "no";
		}else {
			return "yes";
		}
	}
	/**
	 * 添加页面功能管理
	 * @author tsw
	 * @since 2017年10月30日8:45:52
	 *
	 */
	@ResponseBody
	@RequestMapping("/pageordder.htm")
	public  String pageordder(HttpServletRequest request ,HttpSession session ,Pages pages,Model model){
//		if(pages == null){
//			pages = new WorkorderPages();
//		}
		//pages.setPageRank(1l);
		List<Pages> orders = pagesService.selectByPagesorder(pages);
		Pages order =(Pages)orders.get(0);
	String orderby=String.valueOf(Long.parseLong((order.getPageOrders()).toString())+1);
		return orderby;
	}
}
