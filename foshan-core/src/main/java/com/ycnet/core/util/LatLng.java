package com.ycnet.core.util;

public class LatLng
{
	private double latitude;
	private double longitude;
	
	LatLng(double mgLat, double mgLon)
	{
		this.latitude=mgLat;
		this.longitude=mgLon;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	
}
