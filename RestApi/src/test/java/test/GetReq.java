package test;

import apiResponse.*;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetReq {

	GetExcelData dt;
	static String baseURI;
	static int expRes;
		
	public GetReq(String path)
	{
		 dt = new GetExcelData(path);
	}
	
		
	public String getBaseURI()
	{
		
		String baseURI=(String) dt.getData(1, 1);
		System.out.println("The BaseURI for Get is "+baseURI);
		
		return baseURI;
	}
	
	public int expecResCode()
	{
		
		int resCode=Integer.valueOf((String) dt.getData(1, 2)) ;
		System.out.println("Expected Response for Get is "+resCode);
		
		return resCode;
	}
	
	public int  sendGetReq()
	{
		
		RestAssured.baseURI=getBaseURI();
		RestAssured.useRelaxedHTTPSValidation();
		
		RequestSpecification httpRequest = RestAssured.given();
		
		Response res=httpRequest.request(Method.GET,"api/users/2");
		
		return res.getStatusCode();
	}
	
	
	
	
}
