package test;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import apiResponse.GetExcelData;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostReq {
	
	GetExcelData dt;
	static String baseURI;
	static int expRes;
	private static Logger logger = LoggerFactory.getLogger(PostReq.class);
	
	public PostReq(String path)
	{
		 dt = new GetExcelData(path);
	}
	
	public String getBaseURI()
	{
		logger.info("Getting base URI");
		String baseURI=(String) dt.getData(2, 1);
		System.out.println("The BaseURI for Post is "+baseURI);
		
		return baseURI;
	}
	
	public int expecResCode()
	{
		logger.info("Getting Expected res code");
		int resCode=Integer.valueOf((String) dt.getData(2, 2)) ;
		System.out.println("Expected Response for Post is "+resCode);
		
		return resCode;
	}
	
	Response sendPostReq()
	{
		logger.error("Sending post Request");
		RestAssured.baseURI=getBaseURI();
		RestAssured.useRelaxedHTTPSValidation();
		
		RequestSpecification reqSpec = RestAssured.given();
		
		JSONObject obj = new JSONObject();
		
		obj.put("name", "RestTest1");
		obj.put("job", "Org2");
		
		reqSpec.headers("Content-Type","application/JSON");
		reqSpec.body(obj.toString());
		
		Response res  = reqSpec.request(Method.POST, "api/users");
		
		int statusCode=res.getStatusCode();
		
		//System.out.println("The POST response body is "+res.prettyPrint());
		System.out.println("The POST status code  is "+res.getStatusCode());
		return res;
		
	}

}
