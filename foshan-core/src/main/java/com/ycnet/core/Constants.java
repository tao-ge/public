package com.ycnet.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ycnet.core.util.KVBean;

/**
 * 系统使用固定常量类型，与basecode表保持一致。
 *
 */
public class Constants {
	
	//设备类型
	public static enum FACILITTYPE{
		GJX("01"), //光交箱
		ZDH("02"),//光终端盒
		FXX("03"),//分纤箱
		GLT("04"),//光缆接头
		ODF("05"),//ODF
		OBD("06"),  //分光器
		PTN("07"),//PTN
		POLE("10"),//杆
		WELL("11"),//井
		WALL("13"),//挂墙
		STONE("14"),//标石
		BURY("15"),//直埋
		ROOM("20"),//机房
		NONE(""),//无
		;
		private String value;
		private FACILITTYPE(String value)
		{
			this.value = value;
		}
		
		@Override
		public String toString()
		{
			return this.value;
		}
		
		public static FACILITTYPE getType(String val)
		{
			for(FACILITTYPE type :FACILITTYPE.values())
			{
				if (type.value.equals(val))
					return type;
			}
			return NONE;
		}
	}
	
	//分光器型号
	public static enum OBDTYPE{
		_1_2("11",1,2),
		_1_4("06",1,4),
		_1_8("01",1,8),  //分光器类型 01 1：8
		_1_16("02",1,16),
		_1_32("03",1,32),
		_1_64("10",1,64),   
		_2_4("07",2,4),
		_2_8("08",2,8),
		_2_16("09",2,16),
		_2_32("04",2,32),
		_2_64("05",2,64), 
		NONE("",0,0),
		;  
		private String value;
		
		private int leftNum;
		
		private int rightNum;
		
		
		private OBDTYPE(String value,int leftNum,int rightNum)
		{
			this.value = value;
			this.leftNum = leftNum;
			this.rightNum=rightNum;
		}
		@Override
		public String toString()
		{
			return this.value;
		}
		
		public int getLeftNum()
		{
			return this.leftNum;
		}
		
		public int getRightNum()
		{
			
			return this.rightNum;
		}
		
		public static OBDTYPE getType(String value)
		{
			for(OBDTYPE o : OBDTYPE.values())
			{
				if(o.toString().equals(value))
					return o;
			}
			return OBDTYPE.NONE;
		}
		
		
	}
	
	//普查标志列表
	public  static List<KVBean> SurveyResultList=new ArrayList<KVBean>()
			{
				private static final long serialVersionUID = 1L;
				{
					add(new KVBean("0","未普查"));
				    add(new KVBean("1","已普查"));
				    add(new KVBean("2","无设施"));
				}
			};
	//普查标志MAP
	public  static Map<String,String> SurveyResultMap=new LinkedHashMap<String,String>()
					   {
						   private static final long serialVersionUID = 1L;
							{
								this.put("1","已普查");
								this.put("0","未普查");
								this.put("2","无设施");
							}
						};	
	
			
	//报警处理标志列表
	public  static List<KVBean> DealSignList=new ArrayList<KVBean>()
			{
				private static final long serialVersionUID = 1L;
				{
					add(new KVBean("0","未处理"));
					add(new KVBean("1","已处理"));
				}
			};
			//报警处理标志MAP
	public  static Map<String,String> DealSignMap=new HashMap<String,String>()
			{
						
				private static final long serialVersionUID = 1L;

				{
					this.put("0","未处理");
					this.put("1","已处理");
				}
			};
			
			/**
			 * 监控数据类型：1蓝牙开关锁 2其他开锁 3.激活数据 4定时上报数据 5报警数据 6 其他
			 */
			public  static Map<String,String> oprStyleMap=new LinkedHashMap<String,String>()
					{
								
						private static final long serialVersionUID = 1L;

						{
							this.put("1","蓝牙开关锁");
							this.put("2","机械开锁");
							this.put("3","激活数据");
							this.put("4","定时上报数据");
							this.put("5","报警数据");
//							this.put("6","其他");
							this.put("7","关锁");
							this.put("8","超时报警");
//							this.put("11","远程开锁");
//							this.put("12","扫码开锁");
//							this.put("13","小程序开锁");
//							this.put("14","机械开锁");
						}
					};
					
					/**
					 * 门 锁状态标识
					 */
					public  static Map<String,String> doorAndLockStatusMap=new LinkedHashMap<String,String>()
							{
										
								private static final long serialVersionUID = 1L;

								{
									this.put("1","关");
									this.put("0","开");
								}
							};
							
	
	public  static Map<String,String> isAlarmMap=new LinkedHashMap<String,String>()
				{
												
					private static final long serialVersionUID = 1L;

						{
								this.put("1","否");
								this.put("0","是");
						}
				};
				
     public  static Map<Integer,String> alarmTypeMap=new LinkedHashMap<Integer,String>()
				   {
					   private static final long serialVersionUID = 1L;
						{
							this.put(9,"没有报警");
							this.put(1,"温度报警");
							this.put(2,"湿度报警");
							this.put(3,"电压报警");
							this.put(4,"倾斜报警");
							this.put(5,"强行进入");
						}
					};
    /**
     * 有效标志
     */
	public  static Map<String,String> FlagMap=new LinkedHashMap<String,String>()
			{
					private static final long serialVersionUID = 1L;
					{
						this.put("1","有效");
						this.put("0","无效");
						
					}
			};	
	
	
	//核查状态
	public  static Map<String,String> CheckTypeMap=new LinkedHashMap<String,String>()
	{
		private static final long serialVersionUID = 1L;
			{
				this.put("0","未巡检");
				this.put("1","已锁定");
				this.put("2","已巡检");
			}
	};	
	//设施核查状态
	public  static Map<Integer,String> DevTypeall=new LinkedHashMap<Integer,String>()
	{
		private static final long serialVersionUID = 1L;
			{
				this.put(1,"已核对");
				this.put(0,"未核对");
			}
	};	
	
	//光缆状态
	public  static Map<String,String> CableTypeMap=new LinkedHashMap<String,String>()
	{
		private static final long serialVersionUID = 1L;
			{
				this.put("0","末端光缆");
				this.put("1","一端未成端");
				this.put("4","两端均未成端");
				this.put("2","已成端");
				this.put("3", "重复光缆");
			}
	};
	//是否为局前井
	public  static Map<String,String> WellIsformerbureau=new LinkedHashMap<String,String>()
	{
		private static final long serialVersionUID = 1L;
			{
				this.put("0","否");
				this.put("1","是");
			}
	};
	//井种类
		public  static Map<String,String> WellKind=new LinkedHashMap<String,String>()
		{
			private static final long serialVersionUID = 1L;
				{
					this.put("1","孤立");
					this.put("2","直通 ");
					this.put("3","三通 ");
					this.put("4","四通 ");
					this.put("5","五通 ");
					this.put("6","六通 ");
					this.put("7","七通 ");
					this.put("8","七通以上 ");
				}
		};
		//井类型
		public  static Map<String,String> WellType=new LinkedHashMap<String,String>()
		{
			private static final long serialVersionUID = 1L;
				{
					this.put("01","井");
					this.put("02","挂墙");
					this.put("03","杆");
					this.put("04","标石");
				}
		};
		//现场核查状态 0 未核查 1 与现场一致 2 新增 3修改 4未找到 5新增删除 6存疑
		public  static Map<String,String> WellState=new LinkedHashMap<String,String>()
		{
			private static final long serialVersionUID = 1L;
				{	
					this.put("0","未核查");
					this.put("1","与现场一致");
					this.put("2","新增 ");
					this.put("3","修改 ");
					this.put("4","未找到 ");
					this.put("5","新增删除 ");
					this.put("6","存疑 ");
				}
		};
		//管道段状态 0 未核查 1 与现场一致 2 新增 3修改 4未找到 5新增删除 6存疑
				public  static Map<String,String> PipingSectionState=new LinkedHashMap<String,String>()
				{
					private static final long serialVersionUID = 1L;
						{	
							this.put("0","未核查");
							this.put("1","与现场一致");
							this.put("2","新增 ");
							this.put("3","修改 ");
							this.put("4","未找到 ");
							this.put("5","新增删除 ");
							this.put("6","存疑 ");
						}
				};
		//设施 状态 0 未核查 1 与现场一致 2 新增 3修改 4未找到 5新增删除 
				public  static Map<String,String> FacilityState=new LinkedHashMap<String,String>()
				{
					private static final long serialVersionUID = 1L;
						{	
							this.put("0","未核查");
							this.put("1","与现场一致");
							this.put("2","新增 ");
							this.put("3","修改 ");
							this.put("4","未找到 ");
							this.put("5","新增删除 ");
						}
				};
				//光缆 状态 1 与现场一致 2 新增 3修改 4未找到 5新增删除 
				public  static Map<String,String>SectSectionState=new LinkedHashMap<String,String>()
				{
					private static final long serialVersionUID = 1L;
						{	
							//this.put("0","未核查");
							this.put("1","与现场一致");
							this.put("2","新增 ");
							this.put("3","修改 ");
							this.put("4","未找到 ");
							this.put("5","新增删除 ");
						}
				};
				
				//光缆 状态 1 与现场一致 2 新增 3修改 4未找到 5新增删除 
				public  static Map<String,String> existLngLat =new LinkedHashMap<String,String>()
				{
					private static final long serialVersionUID = 1L;
						{	
							this.put("0","无经纬度");
							this.put("1","有经纬度 ");
						}
				};
				//所属平台 01 移创转发平台 02 华为转发平台 03 杭州转发平台 04 电信转发平台
				public  static Map<String,String> devPlatform =new LinkedHashMap<String,String>()
				{
					private static final long serialVersionUID = 1L;
						{	
							this.put("01","移创转发平台");
							this.put("02","深圳实验室转发平台");
							this.put("03","杭州转发平台");
							this.put("04","电信转发平台");
							this.put("05","干城电信转发平台");
						}
				};
				
		//产权性质类型 01 自建 02 共建 03 租用 04 其他
		public  static Map<String,String> nopTypes =new LinkedHashMap<String,String>()
		{
			private static final long serialVersionUID = 1L;
				{	
					this.put("01","自建");
					this.put("02","共建");
					this.put("03","租用");
					this.put("04","其他");
				}
		};
		
		//告警类型  01 阈值告警 02 水浸告警 03 震动告警 04 门锁异常告警
				public  static Map<String,String> alarmTypes =new LinkedHashMap<String,String>()
				{
					private static final long serialVersionUID = 1L;
						{	
							this.put("01","阈值告警");
							this.put("02","水浸告警");
							this.put("03","震动告警 ");
							this.put("04","门锁异常告警");
						}
				};
				
		public  static Map<String,String> oprStyleMap1=new LinkedHashMap<String,String>()
		{
			private static final long serialVersionUID = 1L;
			{ 
				this.put("0","关锁");
				this.put("1","蓝牙开关锁");
				this.put("2","远程开锁");
				this.put("3","其他开锁");
				this.put("4","定时上报");
				this.put("5","报警数据");
			}
		};
		//中控器状态
		public  static Map<String,String> codState=new LinkedHashMap<String,String>()
		{
			private static final long serialVersionUID = 1L;
			{ 
				this.put("0","已绑定");
				this.put("1","已激活");
				this.put("2","已删除");
			}
		};
		//开锁类型
		public  static Map<String,String> switchType=new LinkedHashMap<String,String>()
		{
			private static final long serialVersionUID = 1L;
			{ 
				this.put("1","蓝牙开锁");
				this.put("2","远程开锁");
				this.put("3","扫码开锁");
				this.put("4","机械开锁");
				this.put("5","其他");
			}
		};
}
	
