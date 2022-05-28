package com.restapi.controller;

 

 

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.restapi.service.WAPService;

@RestController
@RequestMapping("/users")
public class WorkandPensionsController  // implements ErrorController 
{
	
	HttpServletResponse response;
	
	@Autowired
	private  RestTemplate restTemplate;
	
	@Autowired
	private WAPService wAPService;
	
	@GetMapping("{city}")
	 
	public List<Object> getLondonUsers(@PathVariable ("city") String city) throws Exception {
		 
		String url = "https://dwp-techtest.herokuapp.com/city/" + city + "/users";
		
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
		String body = responseEntity.getBody();
		System.out.println(body);
		
		HttpHeaders headers = responseEntity.getHeaders();
		System.out.println(headers);
		
			int statusCode = responseEntity.getStatusCodeValue();
		 
			System.out.println(statusCode);
			/*
			 * if(statusCode == 200) {
			 * 
			 * }
			 */
		
		
	/* */	  
	   if(statusCode == 200) {
		Object[] objects = restTemplate.getForObject(url,Object[].class);
		 return Arrays.asList(objects);
	   }
	   else throw new Exception ("Exception");
	   
		  
		 	
		
	}
	
	
	
	@GetMapping("/longt")
	@ResponseBody   
	public List<Object> lontitudemore() {
		
		System.out.println("res" + wAPService.getFilter());
		return wAPService.getFilter();
	
	}
	 
	  
	 
}
