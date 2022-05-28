package com.restapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.restapi.dto.WAPDto;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;

@Service
public class WAPService {
	
	@Autowired
	private  RestTemplate restTemplate;

	public List<Object> getFilter() {

		List<WAPDto> wAPDto = RestAssured.get("https://dwp-techtest.herokuapp.com/users")
				.as(new TypeRef<List<WAPDto>>() {
				});

		List<Object> reuduceObjects = wAPDto.stream().filter(s -> distance(s.getLatitude(), s.getLongitude()) <= 50)
				.collect(Collectors.toList());

		return reuduceObjects;
	}

	private double distance(double lat1, double lon1) {
		 
		final int R = 3838;
		double lat2 = 51.509865;
		double lon2 = -0.118092;
		double latDistance = Math.toRadians(lat2 - lat1);
		double lonDistance = Math.toRadians(lon2 - lon1);
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return R * c;
	}
	
	
	 
	public int checkStaus () 
	{
	String city = "London";	
	String url = "https://dwp-techtest.herokuapp.com/city/" + city + "/users";
	
	ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
	int statusCode = responseEntity.getStatusCodeValue();
	System.out.println("status code" + statusCode);
	return statusCode;

	}
	
	
	public boolean checkSum () {
		boolean res = false;
		 
		List<WAPDto> wAPDto1 = RestAssured.get("https://dwp-techtest.herokuapp.com/users")
				.as(new TypeRef<List<WAPDto>>() {
				});
		
		int totalRecords = wAPDto1.size();
		int lessfiftyOrEqual = wAPDto1.stream().filter(s -> distance(s.getLatitude(), s.getLongitude()) <= 50)
				.collect(Collectors.toList()).size();
		int moreFifty = wAPDto1.stream().filter(s -> distance(s.getLatitude(), s.getLongitude()) > 50)
				.collect(Collectors.toList()).size();
		if(totalRecords == lessfiftyOrEqual + moreFifty) 
		  res = true;
		
		return res;
	}

}
