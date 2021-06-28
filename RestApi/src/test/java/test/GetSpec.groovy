package test

import groovy.json.JsonSlurper
import groovy.util.logging.Log4j
import groovy.util.logging.Log4j2
import groovy.util.logging.Slf4j

import static org.junit.Assert.assertNotNull
import static org.junit.Assert.assertTrue

import java.util.logging.Logger
import spock.lang.Specification
import spock.lang.Unroll
@Slf4j(value = 'LOGGER')

class GetSpec extends Specification{
	
	
	
	@Unroll
	def "Validate Get Response Code" ()
	{
		
		
		given: "Intializing Objects"
			def resObj= new SendRequest("C:\\Users\\Lenovo\\git\\RestAPI\\RestApi\\data\\TestData.xlsx")
			
		when: "Sending Get Request "
			def actResCode = resObj.getResponse()
			def expResCode = resObj.expGetResponseCode()
		then: "Validating Response code for Get Request"
			actResCode.getStatusCode() == expResCode
	}
	
		def "Authorization using Bearer Token , Validate Post  Response " ()
	{
			given: "Intializing Objects"
			def postObj= new SendRequest("C:\\Users\\Lenovo\\git\\RestAPI\\RestApi\\data\\TestData.xlsx")
			def expResCode = postObj.expPostResponseCode()
			
			when: "Sending Post Response"
			def actResCode = postObj.postResponse("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6IlJlc3RBc3N1cmVkIiwicGFzc3dvcmQiOiJUZXN0MTIzNCQiLCJpYXQiOjE2MjQ5MDEyNjh9.j7wgt8Qn45oDY5PUtsRWUZqkB_Mo1-l8enFzumGB-Mo")
						
		
			then: "Validate Status code and book added to user - POST"
			
				actResCode.jsonPath().get("books[0].isbn").toString().contains(a) == true
			
			
		where:
			
				a << ["9781593275846"]
		
	}
	
	def "Authorization using Bearer Token,Validate Put Response" ()
	{
		def putObj = new SendRequest("C:\\Users\\Lenovo\\git\\RestAPI\\RestApi\\data\\TestData.xlsx")
		def actRes = putObj.putResponse("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6IlJlc3RBc3N1cmVkIiwicGFzc3dvcmQiOiJUZXN0MTIzNCQiLCJpYXQiOjE2MjQ5MDEyNjh9.j7wgt8Qn45oDY5PUtsRWUZqkB_Mo1-l8enFzumGB-Mo")
		def expResCode  = putObj.expPutResponseCode();
		
		expect: "Validate response code and response data for PUT"
		actRes.getStatusCode() == expResCode
		actRes.jsonPath().get("books[0].isbn").toString().contains("9781449325862") == true
	}
	
	def "Authorization using Bearer Token,Validate Delete Response" ()
	{
		def deleteObj = new SendRequest("C:\\Users\\Lenovo\\git\\RestAPI\\RestApi\\data\\TestData.xlsx")
		def expResCode = deleteObj.expDeleteResponseCode();
		
		def actRes = deleteObj.deleteResponse("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6IlJlc3RBc3N1cmVkIiwicGFzc3dvcmQiOiJUZXN0MTIzNCQiLCJpYXQiOjE2MjQ5MDEyNjh9.j7wgt8Qn45oDY5PUtsRWUZqkB_Mo1-l8enFzumGB-Mo")
		
		
		
		expect: "Validate response code for Delete is 204"
			actRes.getStatusCode() == expResCode
			
			
		
	}
}
