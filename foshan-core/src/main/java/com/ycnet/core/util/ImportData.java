package com.ycnet.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ycnet.core.Constants;
import com.ycnet.core.FrmsException;
import com.ycnet.core.LineType;
import com.ycnet.frms.bean.CableSection;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.Fiberdisc;
import com.ycnet.frms.bean.FiberdiscGroup;
import com.ycnet.frms.bean.Lines;
import com.ycnet.frms.mapper.LinesMapper;
import com.ycnet.frms.service.CableSectionService;
import com.ycnet.frms.service.FacilityCoreService;
import com.ycnet.frms.service.FacilityService;
import com.ycnet.frms.service.FiberdiscGroupService;
import com.ycnet.frms.service.FiberdiscService;
import com.ycnet.frms.service.LinesService;
import com.ycnet.frms.vo.CableSectionConditionBean;
import com.ycnet.frms.vo.FacilityConditionBean;

@Component
@Transactional
public class ImportData {

	@Autowired
	private FacilityService facilityService;
	
	@Autowired
	private CableSectionService sectionService;
	
	@Autowired
	private FiberdiscGroupService groupService;
	
	@Autowired
	private FacilityCoreService fcService;
	
	@Autowired
	private LinesService linesService;
	
	@Autowired
	private LinesMapper linesMapper;
	
	@Autowired
	private FiberdiscService fiberdiscService;
	
	/**
	 * 1.生成纤芯
	 */
	public void addFiber(Long orgId)
	{
		CableSectionConditionBean bean = new CableSectionConditionBean();
		List<CableSection> list = sectionService.queryByConditionBean(bean);
		for(CableSection sect: list)
		{
			if(sect.getFiberNum()!=null&&sect.getOrgId().longValue()==orgId.longValue())
			{
				List<Lines> lines = linesService.selectBySect(sect.getSectId(),null,null);
				for(Lines l :lines)
				{
					if(l.getAcode()!=null||l.getZcode()!=null)
					{
						throw new RuntimeException("尾纤已成端，不能修改。");
					}
					linesService.deleteById(l.getLineId());
				}
				//获取光缆段A、Z两端设施
				//Facility devA = facilityService.selectById(sect.getDevIdA());
				//Facility devZ = facilityService.selectById(sect.getDevIdZ());
				
				for(long index = 1L ;index<= sect.getFiberNum();index++)
				{
					Lines l = new Lines();
					l.setLineType(LineType.FIBER.toString());
					l.setSectId(sect.getSectId());
					l.setAdevId(sect.getDevIdA());
					l.setZdevId(sect.getDevIdZ());
					l.setFiberNo(index);
					l.setOrgId(orgId);
					linesMapper.insertSelective(l);
				}
				
			}
		}
		
		//throw new FrmsException("故意抛出错误，检查过程是否正确。");
	}
	/**
	 * 2. 生成分组和法兰盘
	 */
	public void genDisc(Long orgId)
	{
		File file = new File("d:\\sg");
		File[] tempFiles = file.listFiles();
		for(int i = 0;i<tempFiles.length;i++)
		{
			if(tempFiles[i].isFile())
			{
				System.out.println("----------------"+tempFiles[i]+"----------------------------");
				InputStream is =null;
				Workbook wb = null;
				try {
					is =  new FileInputStream(tempFiles[i]);
					try {
						wb = WorkbookFactory.create(is);
					} catch (Exception e) {
						e.printStackTrace();
						throw new FrmsException(e.getMessage());
					}
			        
			        Sheet sheet = null;
			        
			        Row row = null;
			        //光交箱
			        sheet = wb.getSheetAt(0);
			        row = sheet.getRow(0);
			        String devCode = row.getCell(1).getStringCellValue().trim();
			        System.out.println("-----------------["+devCode+"]------------------");
			        Facility facility = facilityService.selectByCode(devCode);
			        //成端信息
			        Set<String> sides = new HashSet<String>();
			        Map<String,Integer> discNums = new HashMap<String,Integer>();
			        Integer discNum = 0;
			        sheet = wb.getSheetAt(1);
			        for (int j =2;j<sheet.getPhysicalNumberOfRows();j++ )
			        {
			        	row = sheet.getRow(j);
			        	Cell cell = null;
			        	try {
							cell = row.getCell(2);
						} catch (Exception e) {
						}
			        	String s_disc =getCell(cell).toString().trim();
			        	if("".equals(s_disc))
			        	{
			        		break;
			        	}
			        	String s_side = getCell(row.getCell(1)).toString().trim();
			        	sides.add(s_side);
			        	discNum = Integer.parseInt(s_disc);
			        	if(discNums.get(s_side)==null||discNums.get(s_side).intValue()<discNum)
			        		discNums.put(s_side, discNum);
			        }
			        //生成熔纤盘
			        for(String side :sides )
			        {
				        FiberdiscGroup fdGroup = new FiberdiscGroup();
				        fdGroup.setDevId(facility.getDevId());
				        fdGroup.setDiscNum(discNums.get(side));
				        fdGroup.setPortNum(12);
				        fdGroup.setSide(side);
				        fcService.addGroup(fdGroup);
			        }
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					throw new FrmsException(e.getMessage());
				}catch(Exception e){
					e.printStackTrace();
					throw new FrmsException(e.getMessage());
				}
				finally{
					if(wb!=null)
					{
						try {
							wb.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					if(is!=null)
					{
						try {
							is.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					System.out.println("-end--------------"+tempFiles[i]+"-------------------------end--");
				}
			}
		}
		//生成分光器分组和熔纤盘
		FacilityConditionBean bean =new  FacilityConditionBean();
		bean.setDevType(Constants.FACILITTYPE.OBD.toString());
		List<Facility> list = facilityService.queryByConditionBean(bean);
		for(Facility f:list)
		{
			Constants.OBDTYPE type = Constants.OBDTYPE.getType(f.getDevModel());
			if (type==Constants.OBDTYPE.NONE)
				continue;
			if (f.getOrgId().longValue()!=orgId.longValue())
				continue;
			//生成分光器分组
			FiberdiscGroup in = new FiberdiscGroup();
			in.setDevId(f.getDevId());
			in.setDiscNum(1);
			in.setSide("A");
			in.setPortNum(type.getLeftNum());
			fcService.addGroup(in);
			
			
			FiberdiscGroup out = new FiberdiscGroup();
			out.setDevId(f.getDevId());
			out.setSide("B");
			out.setDiscNum(1);
			out.setPortNum(type.getRightNum());
			fcService.addGroup(out);
			linesService.addVirtual(f.getDevId(),Constants.FACILITTYPE.OBD);
		}
		
		//throw new FrmsException("故意抛出错误，检查过程是否正确。");
	}
	
	
	/**
	 * 3.成端
	 */
	public void genLocation(Long orgId)
	{
		File file = new File("d:\\sg");
		File[] tempFiles = file.listFiles();
		for(int i = 0;i<tempFiles.length;i++)
		{
			if(tempFiles[i].isFile())
			{
				System.out.println("----------------"+tempFiles[i]+"----------------------------");
				InputStream is =null;
				Workbook wb = null;
				try {
					is =  new FileInputStream(tempFiles[i]);
					try {
						wb = WorkbookFactory.create(is);
					} catch (Exception e) {
						e.printStackTrace();
						throw new FrmsException(e.getMessage());
					}
					Sheet sheet = null;
					Row row = null;
			        //光交箱
			        sheet = wb.getSheetAt(0);
			        row = sheet.getRow(0);
			        String devCode = row.getCell(1).getStringCellValue().trim();
			        Facility facility = facilityService.selectByCode(devCode);
			        //成端信息
			        sheet = wb.getSheetAt(1);
			        for (int j =2;j<sheet.getPhysicalNumberOfRows();j++ )
			        {
			        	row  = sheet.getRow(j);
			        	Cell cell = null;
			        	try {
							cell = row.getCell(2);
						} catch (Exception e) {
						}
			        	String s_disc =getCell(cell).toString().trim();
			        	if("".equals(s_disc))
			        	{
			        		break;
			        	}
			        	String s_side = getCell(row.getCell(1)).toString().trim();
			        	String s_glcode = getCell(row.getCell(0)).toString().trim();
			        	if("".equals(s_glcode))
			        		continue;
			        	CableSection section = sectionService.selectByCode(s_glcode);
			        	for(int index = 3;index < 3+12;index ++)
			        	{
			        		String s_fiberNo = getCell(row.getCell(index)).toString().trim();
			        		if("".equals(s_fiberNo))
			        			continue;
			        		String discCode = devCode + "-" +s_side +"-"+StringUtils.leftZeroFill(Integer.parseInt(s_disc), 2);
			        		facilityService.fiberLocate(facility.getDevId(), section.getSectId(), discCode, Integer.parseInt(s_fiberNo),Integer.parseInt(s_fiberNo) , index-2);
			        	}
			        	
			        }
			        
			     
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					throw new FrmsException(e.getMessage());
				}catch(Exception e)				{
					e.printStackTrace();
					throw new FrmsException(e.getMessage());
				}
				finally{
					if(wb!=null)
					{
						try {
							wb.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					if(is!=null)
					{
						try {
							is.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					System.out.println("--end--------------"+tempFiles[i]+"--------------------------end--");
				}
			}
		}
		
		//throw new FrmsException("故意抛出错误，检查过程是否正确。");
	}
	
	/**
	 * 4.生成跳纤
	 */
	public void genJumper(Long orgId,Long userId)
	{

		File file = new File("d:\\sg");
		File[] tempFiles = file.listFiles();
		for(int i = 0;i<tempFiles.length;i++)
		{
			if(tempFiles[i].isFile())
			{
				System.out.println("----------------"+tempFiles[i]+"----------------------------");
				InputStream is =null;
				Workbook wb = null;
				try {
					is =  new FileInputStream(tempFiles[i]);
					try {
						wb = WorkbookFactory.create(is);
					} catch (Exception e) {
						e.printStackTrace();
						throw new FrmsException(e.getMessage());
					}
					Sheet sheet = null;
					Row row = null;
			        //光交箱
			        sheet = wb.getSheetAt(0);
			        row = sheet.getRow(0);
			        //跳纤
			        sheet = wb.getSheetAt(3);
			        for (int j =2;j<sheet.getPhysicalNumberOfRows();j++ )
			        {
			        	row = sheet.getRow(j);
			        	Cell cell0 = null ,cell1 = null,cell2=null,cell3=null;
			        	try {
							cell0 = row.getCell(0);
						} catch (Exception e) {
							e.printStackTrace();
						}
			        	try {
							cell1 = row.getCell(1);
						} catch (Exception e) {
							e.printStackTrace();
						}
			        	
			        	try {
							cell2 = row.getCell(2);
						} catch (Exception e) {
							e.printStackTrace();
						}
			        	try {
							cell3 = row.getCell(3);
						} catch (Exception e) {
							e.printStackTrace();
						}
			        	String acode = getCell(cell0).toString().trim();
			        	String zcode = getCell(cell1).toString().trim();
			        	String unknownPointName= getCell(cell2).toString().trim();
			        	String srvName = getCell(cell3).toString().trim();
			        	
			        	if("".equals(acode)&&"".equals(zcode))
			        		continue;
			        	
			        	Fiberdisc a = fiberdiscService.selectByPort(acode);
			        	Fiberdisc z = fiberdiscService.selectByPort(zcode);
			        	if(a==null&&z==null)
			        		continue;
			        	linesService.addJumper(a==null?null:a.getDevId(), acode==null||"".equals(acode)?null:acode, z==null?null:z.getDevId(), zcode==null||"".equals(zcode)?null:zcode, srvName,orgId,unknownPointName,userId);
			        	
			        }
			        
			     
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					throw new FrmsException(e.getMessage());
				}catch(Exception e)				{
					e.printStackTrace();
					throw new FrmsException(e.getMessage());
				}
				finally{
					if(wb!=null)
					{
						try {
							wb.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					if(is!=null)
					{
						try {
							is.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					System.out.println("--end--------------"+tempFiles[i]+"-------------------------end---");
				}
			}
		}
		
		//throw new FrmsException("故意抛出错误，检查过程是否正确。");
	}
	
	
	private static Object getCell(Cell cell)
	{
		if(cell==null) return "";
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		case Cell.CELL_TYPE_BLANK:
			return "";
		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue();
		case Cell.CELL_TYPE_ERROR:
			return "";
		case Cell.CELL_TYPE_FORMULA:
			return cell.getCellFormula();
		case Cell.CELL_TYPE_NUMERIC:
			return Double.valueOf(cell.getNumericCellValue()).intValue();
		default:
			return "";
		}
	}
}
