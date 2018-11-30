package com.ycnet.frms.service.impl.point;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ycnet.core.util.PageBean;
import com.ycnet.frms.bean.Facility;
import com.ycnet.frms.bean.Project;
import com.ycnet.frms.bean.TnExport;
import com.ycnet.frms.bean.Users;
import com.ycnet.frms.bean.pipeline.Pipeline;
import com.ycnet.frms.bean.point.Point;
import com.ycnet.frms.bean.point.PointExample;
import com.ycnet.frms.mapper.TnExportMapper;
import com.ycnet.frms.mapper.point.PointMapper;
import com.ycnet.frms.service.point.IPointService;
import com.ycnet.frms.vo.FacilityConditionBean;
import com.ycnet.frms.vo.FacilityConditionBean;
import com.ycnet.frms.mapper.pipeline.PipelineMapper;
@Service("IPointService")
public class PointServiceImpl implements IPointService {
	
	@Resource(name="PointMapper")
	private PointMapper pointMapper;
	@Resource(name="exportMapper")
	private TnExportMapper exportMapper;
	@Resource(name="PipelineMapper")
	private PipelineMapper PipelineMapper;
	
	/**
	 * 接收前台传入的Excel文件,执行导入数据
	 */
		@Override
		public String importPointByExcel(HttpServletRequest request,HttpServletResponse response,MultipartHttpServletRequest multiRequest) {
			try {
	            MultipartFile file = multiRequest.getFile("importExcel");
	            if (file.isEmpty()) {
	                return "文件为空";
	            }else if(!".xls".equals(file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."),file.getOriginalFilename().length()))){
	                return "扩展名不正确";
	            }
	            return execImport(getPoints(file.getInputStream()), request);
	        } catch (IOException e) {
	            System.err.println("导入点位失败:"+e.getLocalizedMessage());
	            return "失败";
	        } 
		}
		
		/*
		 * 将Excel中的点位数据存入List<Point>,方便之后插入数据库
		 */
		private Map<String, Object> getPoints(InputStream is) {
			 Map<String, Object> map = new HashMap<String,Object>();
		        
	        List<Point> pointList = new ArrayList<Point>();
	        Point point = null;
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
		            	point = new Point();
		                row = sheet.getRow(i);
		                // 排除空cell问题
		                if(null == row || null == row.getCell(0) || "".equals(row.getCell(0).getStringCellValue().trim()) ){
		                	continue;
		                }
		                // 1.设置点位名称
		                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
		                // -前面的是点位名称
		                String pointName;
						try {
							int length=row.getCell(0).getStringCellValue().indexOf("-");
							if (length==-1) {
								continue;
							}
							pointName = row.getCell(0).getStringCellValue().substring(0,length);
						} catch (Exception e) {
							continue;
						}
						// 判断点位名称是否唯一
						int count = pointMapper.findPointName(pointName);
						if (count!=0) {
							continue;
						}
						point.setPointName(pointName.trim());
		                // 2.设置点位经度
		                if(row.getCell(3)!=null){
			                try {
								row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
								String longitude =row.getCell(3).getStringCellValue();
								BigDecimal bd = new BigDecimal(longitude);
								point.setLongitude(bd);
							} catch (Exception e) {
								continue;
							}
		                }
		                //3.设置点位维度
		                if(row.getCell(4)!=null){
			                try {
								row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
								String latitude = row.getCell(4).getStringCellValue();
								BigDecimal bd = new BigDecimal(latitude);
								point.setLatitude(bd);;
							} catch (Exception e) {
								continue;
							}
		                }
		                //4.汇聚区
		                if(row.getCell(1)!=null){
			                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
			                String area = row.getCell(1).getStringCellValue();
			                point.setArea(area);
		                }
		                // 0 未转化成百度坐标 1已经转化成百度坐标
		                point.setIsTranslated("0");
		                pointList.add(point);
		            }
		           
	            }
	            map.put("pointList", pointList);
	            return map;
	            
	        } catch (Exception e) {
	        	e.printStackTrace();
	            map.put("result", e.getMessage());
	            return map;
	        } finally {
	        	pointList = null;
	        	point = null;
	            fs = null;
	            wb = null;
	            sheet = null;
	            row = null;
	        }
	}
		
		
		/*
		 * 将List<Point>中数据写入数据库
		 */
			@SuppressWarnings("unchecked")
			private String execImport(Map<String, Object> map, HttpServletRequest request) {
				List<Point> list = (List<Point>) map.get("pointList");
		        if(list==null){
		            return map.get("result").toString();
		        }
		        
		        try {
		            if(null != list && list.size()>0){ 
		            	int insertCount=0;
		            // List<Point> temp = null;
		                for(Point point:list) {
		                //	temp = pointMapper.queryByPointName(point);
		                	int count =pointMapper.findPointName(point.getPointName());
		                    if(count==0){
		                    	String temp2 = point.getPointName();
		                    	if(null != temp2 && temp2 !=""){
		                    		int i = pointMapper.insertSelective(point);
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
			public String changePoints() {
				PointExample example = new PointExample();
				example.createCriteria().andIsTranslatedEqualTo("0");
				// s1  先查询总记录条数
				// s2 定义pageSize   从 第一页 for循环 进行查询
				int total = pointMapper.countByExample(example );
				int pageSize = 50;
				Map<String,Integer> map=new HashMap<String,Integer>();
				int totalPages = total % 50 == 0 ? total / 50 : total / 50 + 1;
				for (int i = 1; i <= totalPages; i++) {
					int startNum = i * pageSize - pageSize;
					//map.put("startNum", startNum);
					map.put("pageSize", pageSize);
					List<Point> list = pointMapper.findNoChangePointList(map);
					boolean isSuccess=excHttpTranslate(list);
					if (!isSuccess) {
						break;
					}
				}
				
				//  执行完之后，  查询一下， 剩余数量
				total = pointMapper.countByExample(example);
				return total == 0 ? "true" :"false";
				//return "true";
			}
			/**		from 取值为如下：
			1：GPS设备获取的角度坐标，wgs84坐标;
			2：GPS获取的米制坐标、sogou地图所用坐标;
			3：google地图、soso地图、aliyun地图、mapabc地图和amap地图所用坐标，国测局坐标;
			4：3中列表地图坐标对应的米制坐标;
			5：百度地图采用的经纬度坐标;
			6：百度地图采用的米制坐标;
			7：mapbar地图坐标;
			8：51地图坐标。  
			to 有两种可供选择：5、6。
			5：bd09ll(百度经纬度坐标),
			6：bd09mc(百度米制经纬度坐标); 
			?from=1&to=5&ak=0a3ma3VXzWL0k8a0TgZ3AjllNDmtgFX5
			*/ 
			private boolean excHttpTranslate(List<Point> pList) {
				CloseableHttpClient httpClient = HttpClients.createDefault();
				String result = "";
				String post_url="http://api.map.baidu.com/geoconv/v1/";
				try {
					HttpPost post = new HttpPost(post_url);
					List<NameValuePair> list = new ArrayList<NameValuePair>();
					String coords="";
					for(int j=0;j<pList.size();j++){
						String x=pList.get(j).getLongitude().toString();
						String y=pList.get(j).getLatitude().toString();
						String coord=x+","+y+";";
						coords+=coord;
					}
					
					coords=coords.substring(0,coords.length()-1);
					list.add(new BasicNameValuePair("coords", coords));
					list.add(new BasicNameValuePair("from", "1"));
					list.add(new BasicNameValuePair("to", "5"));
					list.add(new BasicNameValuePair("ak", "0a3ma3VXzWL0k8a0TgZ3AjllNDmtgFX5"));
					
					
					UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(list, "UTF-8");
					post.setEntity(uefEntity);
					CloseableHttpResponse httpResponse = httpClient.execute(post);
					HttpEntity entity = httpResponse.getEntity();
					if (null != entity) {
						result= EntityUtils.toString(entity,"utf-8");
						System.out.println(result);
					}
					JSONObject json=JSONObject.fromObject(result);
					JSONArray jsonArray = json.getJSONArray("result");
					Object jsonStatus = json.get("status");
					System.err.println(jsonStatus);
					if (Integer.parseInt(jsonStatus.toString())==0) {
						Point updateBean = null;
						JSONObject item = null;
						for (int i = 0; i < pList.size(); i++) {
							item = jsonArray.getJSONObject(i);
							updateBean = new Point();
							updateBean.setId(pList.get(i).getId());
							updateBean.setBaiduX(BigDecimal.valueOf(item.optDouble("x")));
							updateBean.setBaiduY(BigDecimal.valueOf(item.optDouble("y")));
							updateBean.setIsTranslated("1");
							pointMapper.updateByPrimaryKeySelective(updateBean);
						}
					}else {
						return false;
					}
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
				return true;
			}

	/**
	 * 
	* @Title: queryPoint 
	* @Description: TODO(导入管道站点) 
	* @param @return    入参
	* @return String    返回类型
	* @author （刘沧海） 
	* @throws
	* @date 2017年11月8日 下午4:09:48 
	* @version V1.0
	 */
	@Override
	public int queryPoint() {
		Point point=null;//点位
		Pipeline pipe=null;//管道段
		List<TnExport> list=exportMapper.queryList();
		int reg=0;
		Integer aId = null;//a端
		Integer zId = null;//z端
		String str="";
		for (int j = 0; j < list.size(); j++) 
		{
			point=new Point();
			// 判断点位名称是否唯一
			int aCount = pointMapper.findPointName(list.get(j).getD());
			if (aCount==0) {
				point.setArea(list.get(j).getC());
				point.setPointName(list.get(j).getD());
				if(list.get(j).getE()==null) {
					point.setLongitude(null);
				}else {
					BigDecimal ajingdu = new BigDecimal(list.get(j).getE());
					point.setLongitude(ajingdu);//A经度
				}
				if(list.get(j).getF()==null) {
					point.setLatitude(null);
				}else {
					BigDecimal aweidu = new BigDecimal(list.get(j).getF());
					point.setLatitude(aweidu);
				}
				point.setIsTranslated("0");
				
				reg=pointMapper.insertSelective(point);
				aId = point.getId();
				point = null;
			}
			point=new Point();
			// 判断点位名称是否唯一
			int zCount = pointMapper.findPointName(list.get(j).getG());
			if (zCount==0) {
				point.setArea(list.get(j).getC());
				point.setPointName(list.get(j).getG());
				if(list.get(j).getH()==null) {
					point.setLongitude(null);
				}else {
					BigDecimal zjingdu = new BigDecimal(list.get(j).getH());
					point.setLongitude(zjingdu);//Z经度
				}
				if(list.get(j).getI()==null) {
					point.setLatitude(null);
				}else {
					BigDecimal zweidu = new BigDecimal(list.get(j).getI());
					point.setLatitude(zweidu);
				}
				point.setIsTranslated("0");
				reg=pointMapper.insertSelective(point);
				zId = point.getId();
				point = null;
			}
			int countPname =PipelineMapper.findPipelineName(list.get(j).getA());
			if(countPname==0) {
				pipe=new Pipeline();
				pipe.setPipelineName(list.get(j).getA());
				pipe.setFrontPointId(aId);
				pipe.setBackPointId(zId);
				pipe.setPipelineType("直通");
				if(list.get(j).getK().equals("0")) {
					pipe.setPore(0);
				}else {
					pipe.setPore(Integer.valueOf(list.get(j).getK()));//子管孔数
				}
				str+=list.get(j).getJ()+"-"+list.get(j).getK()+"-"+list.get(j).getL();//子管,拼接
				pipe.setPipe(str);
				pipe.setTrunkName(null);
				pipe.setPepelineClassify("管道段");
				reg=PipelineMapper.insertSelective(pipe);
				pipe=null;
				str="";
			}
		}

		return reg;
	}
}
