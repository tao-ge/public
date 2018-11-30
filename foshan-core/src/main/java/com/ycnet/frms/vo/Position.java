package com.ycnet.frms.vo;

import java.util.List;

	public class Position {
		
	private int id;
		
	private Long provinceId;
	
	private String provinceName;
	
	private List<City> cityList;

	public Long getProvinceId() {
		return provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public List<City> getCityList() {
		return cityList;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	}
	class City{
		private Long cityId;
		
		private String cityName;
		
		private List<County> countList;

		public Long getCityId() {
			return cityId;
		}

		public String getCityName() {
			return cityName;
		}

		public void setCityId(Long cityId) {
			this.cityId = cityId;
		}

		public void setCityName(String cityName) {
			this.cityName = cityName;
		}

		public List<County> getCountList() {
			return countList;
		}

		public void setCountList(List<County> countList) {
			this.countList = countList;
		}

	}
	
	class County{
		private Long townId;
		
		private String townName;

		public Long getTownId() {
			return townId;
		}

		public String getTownName() {
			return townName;
		}

		public void setTownId(Long townId) {
			this.townId = townId;
		}

		public void setTownName(String townName) {
			this.townName = townName;
		}
		
	}