package test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import apiResponse.GetExcelData;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SendRequest {

	GetExcelData dt;
	static String baseURI;
	private static Logger logger = LoggerFactory.getLogger(SendRequest.class);
	
	public SendRequest(String path)
	{
		dt = new GetExcelData(path);
		baseURI = String.valueOf(dt.getData(1, 1));
		logger.info("BaseURI is "+baseURI);
		
	}
	
	int expGetResponseCode()
	{
		return Integer.valueOf((String)dt.getData(1, 2));
	}
	
	int expPostResponseCode()
	{
		return Integer.valueOf((String)dt.getData(2, 2));
	}
	
	int expPutResponseCode()
	{
		return Integer.valueOf((String)dt.getData(3, 2));
	}
	
	Response getResponse()
	{
		RestAssured.baseURI=baseURI;
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification httpRequest = RestAssured.given();
		
		logger.info("Sending Get Request");
		
		Response res=httpRequest.request(Method.GET,"/BookStore/v1/Books");
		
		res.prettyPrint();
		
		return res;
	}
	
	Response postResponse(String Token)
	{
		RestAssured.baseURI=baseURI;
		RestAssured.useRelaxedHTTPSValidation();
		RequestSpecification httpRequest = RestAssured.given();
		
		String addBookToUser = "\r\n" + 
				"{\r\n" + 
				"  \"userId\": \"50ec03f5-8fac-4bee-976a-1490c667082f\",\r\n" + 
				"  \"collectionOfIsbns\": [\r\n" + 
				"    {\r\n" + 
				"      \"isbn\": \"9781593275846\"\r\n" + 
				"    }\r\n" + 
				"  ]\r\n" + 
				"}";
		
		logger.info("Sending Post Request");
		
		httpRequest.header("Authorization","Bearer "+Token).header("Content-Type","application/json");
		
		Response res = httpRequest.body(addBookToUser).post("/BookStore/v1/Books");
					
		res.prettyPrint();
		
		return res;
		
	}
	
	Response putResponse(String token)
	{
		RestAssured.baseURI=baseURI;
		
		RequestSpecification httpRequest= RestAssured.given();
		
		String payload = "{\r\n" + 
				"  \"userId\": \"50ec03f5-8fac-4bee-976a-1490c667082f\",\r\n" + 
				"  \"isbn\": \"9781449325862\"\r\n" + 
				"}";
		
		httpRequest.header("Authorization","Bearer "+token).header("Content-Type","application/json");
		
		logger.info("Sending Put Request");
		
		Response res=httpRequest.body(payload).put("/BookStore/v1/Books/9781593275846");
		
		res.prettyPrint();
		
		return res;
		
	}
}
