package test;

import org.json.JSONObject;

import apiResponse.GetExcelData;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostReq {
	
	GetExcelData dt;
	static String baseURI;
	static int expRes;
	
	public PostReq(String path)
	{
		 dt = new GetExcelData(path);
	}
	
	public String getBaseURI()
	{
		
		String baseURI=(String) dt.getData(2, 1);
		System.out.println("The BaseURI for Post is "+baseURI);
		
		return baseURI;
	}
	
	public int expecResCode()
	{
		
		int resCode=Integer.valueOf((String) dt.getData(2, 2)) ;
		System.out.println("Expected Response for Post is "+resCode);
		
		return resCode;
	}
	
	Response sendPostReq()
	{
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
		
		System.out.println("The POST response body is "+res.prettyPrint());
		System.out.println("The POST status code  is "+res.getStatusCode());
		return res;
		
	}

}
