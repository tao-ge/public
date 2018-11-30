package com.ycnet.frms.service.impl.pipeline;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ycnet.frms.bean.PipelineSection;
import com.ycnet.frms.bean.pipeline.Pipeline;
import com.ycnet.frms.bean.point.Point;
import com.ycnet.frms.mapper.pipeline.PipelineMapper;
import com.ycnet.frms.mapper.point.PointMapper;
import com.ycnet.frms.service.pipeline.IPipeLineService;
@Service("IPipeLineService")
public class PipeLineServiceImpl implements IPipeLineService {
	@Resource(name="PipelineMapper")
	private PipelineMapper pipelineMapper;
	@Resource()
	private PointMapper pointMapper;
	
	/**
	 * 接收前台传入的Excel文件,执行导入数据
	 */
		@Override
		public String importPipeLineByExcel(HttpServletRequest request,HttpServletResponse response,MultipartHttpServletRequest multiRequest) {
			try {
	            MultipartFile file = multiRequest.getFile("importExcel");
	            if (file.isEmpty()) {
	                return "文件为空";
	            }else if(!".xls".equals(file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."),file.getOriginalFilename().length()))){
	                return "扩展名不正确";
	            }
	            return execImport(getPipeLines(file.getInputStream()), request);
	        } catch (IOException e) {
	            System.err.println("导入管道失败:"+e.getLocalizedMessage());
	            return "失败";
	        } 
		}
		
		/*
		 * 将Excel中的点位数据存入List<Pipeline>,方便之后插入数据库
		 */
		private Map<String, Object> getPipeLines(InputStream is) {
			 Map<String, Object> map = new HashMap<String,Object>();
		        
	        List<Pipeline> pipelineList = new ArrayList<Pipeline>();
	        Pipeline pipeline = null;
	        POIFSFileSystem fs = null;
	        HSSFWorkbook wb = null;
	        HSSFSheet sheet = null;
	        HSSFRow row = null;
	        int rows = 0;
	        try {
	            try {
	                fs = new POIFSFileSystem(is);
	                wb = new HSSFWorkbook(fs);
	            } catch (IOException e) {
	                return null;
	            }
	            // 获取excel有几页
	           int page= wb.getNumberOfSheets();
	            for(int b=0;b<page;b++){
	            	sheet = wb.getSheetAt(b);
		            rows = sheet.getPhysicalNumberOfRows();
		            for (int i = 1; i < rows; i++) {
		            	pipeline = new Pipeline();
		                row = sheet.getRow(i);
		                // 排除空cell问题
		                if(null == row || null == row.getCell(0) || "".equals(row.getCell(0).getStringCellValue().trim()) ){
		                	continue;
		                }
		                // 1.设置管道名称
		                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
		                String pipelineName;
						try {
							pipelineName = row.getCell(0).getStringCellValue();
							
						} catch (Exception e) {
							continue;
						}
						// 判断管道名称是否唯一
						int count = pipelineMapper.findPipelineName(pipelineName);
						if (count!=0) {
							continue;
						}
						pipeline.setPipelineName(pipelineName.trim());
		                // 2.设置前点位
		                if(row.getCell(0)!=null){
			                try {
								row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
								int length=row.getCell(0).getStringCellValue().indexOf("-");
								if (length==-1) {
									continue;
								}
								String frontPointName =row.getCell(0).getStringCellValue().substring(0,length);
								Point point=pointMapper.findPointByName(frontPointName);
								// 前点位是否存在
								if (null!=point) {
									pipeline.setFrontPointId(point.getId());
								}else {
									pipeline.setFrontPointId(null);
								}
							} catch (Exception e) {
								continue;
							}
		                }
		                //3.设置后点位
		                if(row.getCell(0)!=null){
			                try {
								row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
								String backPointName =row.getCell(0).getStringCellValue().substring(row.getCell(0).getStringCellValue().indexOf("-")+1,row.getCell(0).getStringCellValue().length());
								Point point=pointMapper.findPointByName(backPointName);
								// 后点位是否存在
								if (null!=point) {
									pipeline.setBackPointId(point.getId());
								}else {
									pipeline.setBackPointId(null);
								}
							} catch (Exception e) {
								continue;
							}
		                }
		                //4.管道类型
		                if(row.getCell(2)!=null){
			                try {
								row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
								String type = row.getCell(2).getStringCellValue();
								if (!type.isEmpty()) {
									pipeline.setPipelineType(type);
								}else {
									pipeline.setPipelineType("无");
								}
								
							} catch (Exception e) {
								continue;
							}
		                }
		                //5.管孔 只有管道段有
		                if(row.getCell(5)!=null && b==0){
			                try {
								row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
								String pore = row.getCell(5).getStringCellValue();
								if (!pore.isEmpty()) {
									pipeline.setPore(Integer.parseInt(pore));
								}else {
									pipeline.setPore(null);
								}
								
							} catch (Exception e) {
								continue;
							}
		                }
		               
		                //6.子管 只有管道段有
		                if(row.getCell(6)!=null && b==0){
			                try {
								row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
								String pipe = row.getCell(6).getStringCellValue();
								if (!pipe.isEmpty()) {
									pipeline.setPipe(pipe);
								}else {
									pipeline.setPipe("无");
								}
								
							} catch (Exception e) {
								continue;
							}
		                }
		                
		                //7.中继段 管道段在7 其他在5列
		                if(row.getCell(7)!=null){
			                try {
			                	String trunkName =null;
			                	if (b==0) {
			                		row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
									 trunkName = row.getCell(7).getStringCellValue();
								}else {
									row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
									trunkName = row.getCell(5).getStringCellValue();
								}
								
								if (!trunkName.isEmpty()) {
									pipeline.setTrunkName(trunkName);
								}else {
									pipeline.setTrunkName("无");
								}
								
							} catch (Exception e) {
								continue;
							}
		                }
		                //8.分类： 管道段 杆路段 挂墙段
		                if(b==0){
			                pipeline.setPepelineClassify("管道段");
		                }else if (b==1) {
		                	 pipeline.setPepelineClassify("杆路段");
						}else {
							pipeline.setPepelineClassify("挂墙段");
						}
		                pipelineList.add(pipeline);
		            }
	            }
	            map.put("pipelineList", pipelineList);
	            return map;
	           
	            
	        } catch (Exception e) {
	        	e.printStackTrace();
	            map.put("result", e.getMessage());
	            return map;
	        } 
	}
	
		
		
		/*
		 * 将List<Pipeline>中数据写入数据库
		 */
			@SuppressWarnings("unchecked")
			private String execImport(Map<String, Object> map, HttpServletRequest request) {
				List<Pipeline> list = (List<Pipeline>) map.get("pipelineList");
		        if(list==null){
		            return map.get("result").toString();
		        }
		        
		        try {
		            if(null != list && list.size()>0){ 
		            	int insertCount=0;
		            // List<Point> temp = null;
		                for(Pipeline pipeline:list) {
		                //	temp = pointMapper.queryByPointName(point);
		                	int count =pipelineMapper.findPipelineName(pipeline.getPipelineName());
		                    if(count==0){
		                    	String temp2 = pipeline.getPipelineName();
		                    	if(null != temp2 && temp2 !=""){
		                    		int i = pipelineMapper.insertSelective(pipeline);
		                    		if(i==1){
		                    			insertCount+=1;
		                    		}
		                    	}
		                    }
		                }
		                return "导入完成,共"+insertCount+"条";
		            }else{
		                return "失败";
		            }
		        } catch (Exception e) {
		            System.err.println(e.getMessage());
		        }
		        return "导入完成";
		    }

			@Override
			public List<Pipeline> findPipeline() {
				List<Pipeline> list =pipelineMapper.findAllNotNull();
				return list;
			}
			/**
			 * 根据井id查询管道段信息
			 */
			@Override
			public List<PipelineSection> queryPipelineSectionByDevId(Long devId) {
				List<PipelineSection> pipelineSections = pipelineMapper.queryPipelineSectionByDevId(devId);
				return pipelineSections;
			}
}
