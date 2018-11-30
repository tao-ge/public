package com.ycnet.frms.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ycnet.frms.bean.Tdisc;
import com.ycnet.frms.bean.Tjumper;
import com.ycnet.frms.mapper.CableSectionMapper;
import com.ycnet.frms.mapper.FacilityMapper;
import com.ycnet.frms.mapper.TdiscMapper;
import com.ycnet.frms.mapper.TjumperMapper;
import com.ycnet.frms.service.CensusService;
import com.ycnet.frms.service.TtableService;

@Service("censusService")
public class CensusServiceImpl implements CensusService{

	@Resource
	private TtableService tmpService;
	
	@Resource
	private TdiscMapper tdiscMapper;
	
	@Resource 
	private TjumperMapper tjumperMapper;
	
	@Resource(name="facilityMapper")
	private FacilityMapper facilityMapper;
	
	@Resource(name="cableSectionMapper")
	private CableSectionMapper cableSectionMapper;
	
	private  static Date now = new Date();
	
	/*
	@Override
	public PageBean queryByConditionBean(FlangePlate flangePlate,Users user, PageBean pb) {
		// TODO Auto-generated method stub
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("flangePlateCon", flangePlate);
		conditionMap.put("user", user);
		conditionMap.put("pb", pb);
		pb.setRows(censusMapper.queryCountByConditionMaps(conditionMap));//;
		pb.setList(censusMapper.queryByConditionMaps(conditionMap));	
		return pb;
	}
	*/
	
	@Transactional
	public List<String> saveFile(MultipartFile file,Long userId,boolean isDel) {
			List<String> retList = new ArrayList<String>();
	        List<Tdisc> tdiscList = new ArrayList<Tdisc>();
			List<Tjumper> tjumperList = new ArrayList<Tjumper>();
			
			String fileName = file.getOriginalFilename();
					
			//导入位置信息
			String  details1 = "" ,details2="";
			
			Workbook wb = null;
			
			Sheet sheet0 = null;
			Sheet sheet1 = null;
			//Sheet sheet2 = null;
			Sheet sheet3 = null;
		    try {
				wb = WorkbookFactory.create(file.getInputStream());
			} catch (Exception e) {
				retList.add(fileName +" 不是有效的excel文件！");
				return retList;
				//e.printStackTrace();
			}
		    
		    sheet0 = wb.getSheetAt(0);
		    sheet1 = wb.getSheetAt(1);
		    //sheet2 = wb.getSheetAt(2);
		    sheet3 = wb.getSheetAt(3);
		    
		    String devCode = String.valueOf(getCell(sheet0.getRow(0)==null?null:sheet0.getRow(0).getCell(1)));
		    devCode = isNull(devCode);
		    if(isDel)
		    {
		    	tmpService.deleteByDev(devCode);
		    }
		    
		    //成端sheet
		    for (int index = 2; index < sheet1.getPhysicalNumberOfRows(); index++){
		    	details1="("+devCode+")【"+fileName +"】熔纤盘信息：第"+(index+1)+"行-- "  ;
		    	Row row = sheet1.getRow(index);
		    	String sectCode = String.valueOf(getCell(row==null?null:row.getCell(0)));
		    	sectCode = isNull(sectCode);
		    	String side = String.valueOf(getCell(row==null?null:row.getCell(1)));
		    	side = isNull(side);
		    	Integer discRowNo = parseInt(getCell(row==null?null:row.getCell(2)), details1 +"法兰盘编号：", retList);
		    	String remark = String.valueOf(getCell(row==null?null:row.getCell(15)));
		    	remark = isNull(remark);
		    	if(sectCode==null&&side==null&&discRowNo==null){
		    		break;
		    	}
		    	
		    	for(int col = 3;col<3+12;col++)
		    	{
		    		String details_tmp = details1;
		    		details_tmp += "第" + (col+1) + "列";
		    		Tdisc tdisc = new Tdisc();
		    		tdisc.setSectCode(sectCode);
		    		tdisc.setSide(side);
		    		tdisc.setDiscRowNo(discRowNo);
		    		Object o = getCell(row==null?null:row.getCell(col));
		    		Integer fiberNum =parseInt(o, details_tmp, retList);
		    		
		    		tdisc.setFiberNum(fiberNum);
		    		tdisc.setDiscColNo(col - 2);
		    		tdisc.setRemark(remark);
		    		tdisc.setDevCode(devCode);
		    		tdisc.setDetails(details_tmp);
		    		tdisc.setUserId(userId);
		    		tdiscList.add(tdisc);
		    	}
		    	
		    }
		    //跳线sheet
		    for(int index = 2; index < sheet3.getPhysicalNumberOfRows(); index++){
		    	details2 = "("+devCode+")【"+fileName +"】跳线信息：第"+(index+1) +"行";
		    	Row row = sheet3.getRow(index);
		    	Tjumper tjumper = new Tjumper();
		    	String acode = String.valueOf(getCell(row.getCell(0)));
		    	acode = isNull(acode);
		    	String zcode =String.valueOf(getCell(row.getCell(1)));
		    	zcode = isNull(zcode); 
		    	String unknownPointName = String.valueOf(getCell(row.getCell(2)));
		    	unknownPointName = isNull(unknownPointName);
		    	String srvName = String.valueOf(getCell(row.getCell(3)));
		    	srvName = isNull(srvName);
		    	if(acode==null&&zcode==null)
		    	{
		    		break;
		    	}
		    	tjumper.setAcode(acode);
		    	tjumper.setZcode(zcode);
		    	tjumper.setSrvName(srvName);
		    	tjumper.setUnknownPointName(unknownPointName);
		    	tjumper.setDevCode(devCode);
		    	tjumper.setDetails(details2);
		    	tjumper.setUserId(userId);
		    	tjumperList.add(tjumper);
		    }
		    
		    tmpService.saveDisc(tdiscList);
		    tmpService.saveJumper(tjumperList);
		    return retList;
	}


	@Override
	public List<String> saveExcel(MultipartFile[] files, Long userId,String flag) {
		List<String> retList = new ArrayList<String>();
		String filename =null;
		if("0".equals(flag))
		{
			tmpService.deleteByUser(userId);
		}
		boolean isDel = "1".equals(flag)?true:false;
		for(int index = 0;index<files.length;index++)
		{
			try
			{
				filename = files[index].getOriginalFilename();
				List<String> tmp = saveFile(files[index],userId,isDel);
				retList.addAll(tmp);
			}catch(Exception e)
			{
				e.printStackTrace();
				retList.add("导入文件【"+filename+"】发生错误："+e.getMessage());
			}
		}
		
		return retList;
	}
	
	@Override
	public List<String> check(Long userId, Long orgId) {
		savePort(userId,orgId);
		List<String>  list= new ArrayList<String>();
		List<Tdisc> dList = null;
		List<Tjumper> jList = null;
		Set<String> set = new HashSet<String>();
		String sql="";
		
		sql = "t.dev_code is null or not exists (select 1 from tn_facility t1 where  t1.dev_code = t.dev_code and t1.org_id =" +orgId + ")";
		dList =  tdiscMapper.findRecords(userId, sql);
		set.clear();
		for(Tdisc t:dList){
			set.add(t.getDevCode());
		}
		if(set.size()>0)
		{
			String tmp ="";
			for(String str:set){
				tmp +="【"+str+"】 ";
			}
			list.add("设施编码不正确或者该设施不属于用户所属区域：" +tmp);
		}
		
		sql = " t.sect_code is not null and not exists (select 1 from tn_cable_section t1 where  t1.sect_code = t.sect_code and t1.org_id =" +orgId + ")";
		dList =  tdiscMapper.findRecords(userId, sql);
		set.clear();
		for(Tdisc t:dList){
			Pattern p = Pattern.compile("【(.*)】");
			Matcher m = p.matcher(t.getDetails());
			if(m.find())
			{
				set.add(m.group(1)+"("+t.getSectCode()+")");
			}
		}
		if(set.size()>0)
		{
			String tmp ="";
			for(String str:set){
				tmp +="【"+str+"】 ";
			}
			list.add("光缆段编码不正确或者该光缆段不属于用户所属区域：" +tmp);
		}
		
		sql = " exists (select 1 from tn_cable_section t1 where t1.sect_code = t.sect_code and t.fiber_num > t1.fiber_num)";
		dList =  tdiscMapper.findRecords(userId, sql);
		set.clear();
		for(Tdisc t:dList){
			list.add("不存在的光缆段线芯号：" + t.getDetails());
		}
		
		sql =" exists (select 1 from tt_disc t1 where t1.dev_code = t.dev_code and t1.sect_code = t.sect_code and t1.fiber_num = t.fiber_num and t1.id <> t.id and t.user_id = t1.user_id)";
		dList =  tdiscMapper.findRecords(userId, sql);
		set.clear();
		for(Tdisc t:dList){
			list.add("重复成端：" + t.getDetails());
		}
		
		sql =" exists( select 1 from tt_disc t1 where t1.sect_code = t.sect_code and t.fiber_num = t1.fiber_num and t1.user_id  = t.user_id group by t1.sect_code,t1.fiber_num having count(1) >1)";
		dList =  tdiscMapper.findRecords(userId, sql);
		set.clear();
		for(Tdisc t:dList){
			list.add("光缆段线芯成端错误：" + t.getDetails());
		}
		
		sql = "t.acode is not null and not exists (select 1 from tt_disc t1 where t1.user_id =t.user_id and t.acode is not null and  t.acode = t1.port01 )";
		jList = tjumperMapper.findRecords(userId, sql);
		set.clear();
		for(Tjumper t:jList){
			list.add("跳纤编码不正确：" +t.getDetails());
		}
		sql = "t.zcode is not null and not exists (select 1 from tt_disc t1 where t1.user_id =t.user_id  and   t.zcode = t1.port01 )";
		jList = tjumperMapper.findRecords(userId, sql);
		set.clear();
		for(Tjumper t:jList){
			list.add("跳纤编码不正确：" +t.getDetails());
		}
		return list;
	}
	
	@Transactional
	public void savePort(Long userId,Long orgId)
	{
		tdiscMapper.genPortCode(userId,orgId);
	}

	private static Object getCell(Cell cell)
	{
		if(cell==null) return null;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			String ret = cell.getStringCellValue();
			return ret==null||"".equals(ret.trim())?null:ret;
		case Cell.CELL_TYPE_BLANK:
			return null;
		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue();
		case Cell.CELL_TYPE_ERROR:
			return null;
		case Cell.CELL_TYPE_FORMULA:
			return cell.getCellFormula();
		case Cell.CELL_TYPE_NUMERIC:
			return Double.valueOf(cell.getNumericCellValue()).intValue();
		default:
			return null;
		}
	}
	
	private Integer parseInt(Object o,String info,List<String> retList){
		if(o==null)
			return null;
		try{
			return Integer.parseInt(o.toString(),10);
		}catch(Exception e)
		{
			retList.add(info + " 不是有效的数字！");
			return null;
		}
		
	}
	
	private String isNull(String str)
	{
		return str==null||"null".equals(str.toLowerCase())?null:str;
	}
	public static void main(String[] args) {
		String a ="(GJ3196)【GJ3196 千手缘机房ODF01.xls】熔纤盘信息：第3行-- 第10列";
		Pattern p = Pattern.compile("【(.*)】");
		Matcher m = p.matcher(a);
		System.out.println(m.find());
		System.out.println(String.valueOf(a));
	}
}
