package com.ycnet.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ycnet.core.config.SocketDataConfig;
import com.ycnet.core.util.LatLng;
import com.ycnet.core.util.Map_Util;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.service.FacilityService;
import com.ycnet.frms.service.SiteCodeService;
import com.ycnet.frms.vo.FacilityConditionBean;

@Controller
@RequestMapping("/test")
public class TestController {

	private static Logger logger = LoggerFactory.getLogger(TestController.class) ;
	//演示修改
	@Resource(name="siteCodeService")
	private SiteCodeService siteCodeService;
	
	@Resource(name="facilityService")
	private FacilityService facilityService;
	
	@RequestMapping("/getCode.htm")
	public String getCode(HttpServletRequest request)
	{
		//siteCodeService.getNewSectCode();
		String str = request.getSession().getServletContext().getRealPath("/");
		logger.info(str);
		System.out.println(str);
		return str;
	}

	@RequestMapping("/m/test01.htm")
	public String test01() {
		return "test";
	}
	
	public void aB()
	{
		
	}
	
	//冲lysdcsdaf突测试123324
	
	
	
	//冲突测试111111111111111111333
	@RequestMapping("/test2.htm")
	public String test2(Model model)
	{
		
		return "list";
	}
	
	public static void main(String[] args) {
		String test="C:\\Users\\liucanghai\\Desktop\\js拼接列表.txt";
		File f=new File(test);
		int count = 0;
		try {
			InputStream in=new FileInputStream(f);
			byte b[] =new byte[1024];
			int s=in.read(b);
			String str[] = new String(b,0,s).split("");
			
			for (int i = 0; i < str.length; i++) {
				if("expectfinishTimeLight".equals(str[i])) count++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(count);
		
	}
	
	@RequestMapping("/baidu.htm")
	public String baidu(Model model) throws Exception
	{
		FacilityConditionBean bean=new FacilityConditionBean();
		List<Facility> facilityList=facilityService.queryByConditionBean(bean);
		
		List<DiTuJson> sz=new ArrayList<DiTuJson>();
		for(Facility f:facilityList)
		{
			DiTuJson s=new DiTuJson();
			s.title=f.getDevName();
			//LatLng latLng=Map_Util.Gps84_To_bd09(Double.parseDouble(f.getLatitude()), Double.parseDouble(f.getLongitude()));
			
			//s.point=latLng.getLongitude()+"|"+latLng.getLatitude();
			s.point=f.getLongitude()+"|"+f.getLatitude();
			s.isOpen=0;
			s.devId=f.getDevId();
			s.content=f.getDevName();
			s.icon=new Icon();
			sz.add(s);
		}
		
		ObjectMapper mapper = new ObjectMapper();  
		String s=mapper.writeValueAsString(sz);
		model.addAttribute("str", s);
		return "baidu";
	}
	@RequestMapping("/m/ceshi.htm")
	public String ceshi() {
		String route="惠州桥东东升路（微小）-综合机柜-ODF01(24芯横（左到右下到上）/01-A-016,24芯横（左到右下到上）/01-A-017){惠州桥东东升路（微小）-综合机柜-ODF01-华罗庚中学光交3(FX016,FX017)}华罗庚中学光交3(A-10-004,A-10-005)《跳纤》华罗庚中学光交3(A-01-008,A-01-009){华罗庚中学光交2-华罗庚中学光交3(FX008,FX009)}华罗庚中学光交2(B13-008,B13-009)《跳纤》华罗庚中学光交2(A-08-011,A-08-012){华罗庚中学光交2-华罗庚综合机柜02-ODF4/5/6(FX059,FX060)}华罗庚综合机柜02-ODF4/5/6(ODF5-01-011,ODF5-01-012)";
		
		String s="(dsaf(323)ld)sao,(sd)"; 
		
		String pattern="(\\([^\\)]+\\))"; //正则表达式，匹配括号内容
		ArrayList list=new ArrayList();
		Pattern p=Pattern.compile(pattern);
		Matcher m=p.matcher(s);
		while(m.find()){
		list.add(m.group());
		System.out.println();
		}
		return "";
	}
	
	//版本控制冲突演示  作者B更新	
	public class DiTuJson
	{
		//content:"我的备注",point:"113.9888|24.6349",isOpen:0,icon:{w:21,h:21,l:0,t:0,x:6,lb:5}

		private String title;
		private Long devId;
		private String content;
		private String point;
		private int isOpen;		
		private Icon icon;	
		
		
		public Long getDevId() {
			return devId;
		}

		public void setDevId(Long devId) {
			this.devId = devId;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getPoint() {
			return point;
		}

		public void setPoint(String point) {
			this.point = point;
		}

		public int getIsOpen() {
			return isOpen;
		}

		public void setIsOpen(int isOpen) {
			this.isOpen = isOpen;
		}

		public Icon getIcon() {
			return icon;
		}

		public void setIcon(Icon icon) {
			this.icon = icon;
		}

		public String getTitle() {
			return title;
		}
		
		public void setTitle(String title) {
			this.title = title;
		}
		
		
	}
	
	//dsafdsafasdfasd
	public class Icon
	{
		private int w;
		private int h;
		private int l;
		private int t;
		private int x;
		private int lb;
		
		//{w:21,h:21,l:0,t:0,x:6,lb:5}
		private Icon()
		{
			this.w=21;
			this.h=21;
			this.l=0;
			this.t=0;
			this.x=6;
			this.lb=5;
		}
		
		public int getW() {
			return w;
		}
		public void setW(int w) {
			this.w = w;
		}
		public int getH() {
			return h;
		}
		public void setH(int h) {
			this.h = h;
		}
		public int getL() {
			return l;
		}
		public void setL(int l) {
			this.l = l;
		}
		public int getT() {
			return t;
		}
		public void setT(int t) {
			this.t = t;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getLb() {
			return lb;
		}
		public void setLb(int lb) {
			this.lb = lb;
		}
		
	}
	
	@Autowired
	private SocketDataConfig socketDataConfig;
	
	@RequestMapping("/testProperties.htm")
	@ResponseBody
	public String testProperties() {
		
		return socketDataConfig.getTcpIp()+"==="+socketDataConfig.getUdpPort();
	}
}
