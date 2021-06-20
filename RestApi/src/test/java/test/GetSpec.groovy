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
	
	@Unroll('ISBN value should be #b')
	def "Authorization using Bearer Token , Validate Post  Response " ()
	{
			
			def postObj= new SendRequest("C:\\Users\\Lenovo\\git\\RestAPI\\RestApi\\data\\TestData.xlsx")
			def actResCode = postObj.postResponse("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6IlJlc3RBc3N1cmVkIiwicGFzc3dvcmQiOiJUZXN0MTIzNCQiLCJpYXQiOjE2MjQxOTgwMzV9.PqDgE_gBgAG8eltYw7ogI50InA-hEeimYUHoT0xlJJ8")
			def expResCode = postObj.expPostResponseCode()
		
		expect: "Validate Status code and ISBN Value added"
			actResCode.getStatusCode() == expResCode
			actResCode.jsonPath().get("books[0].isbn").toString().contains(b) == true
			
			
		where:
			
			a << ["isbn"]
			b<< ["9781449337711"]
		
	}
}
