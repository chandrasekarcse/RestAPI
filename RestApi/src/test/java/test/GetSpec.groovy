package test

import groovy.json.JsonSlurper
import spock.lang.Specification

class GetSpec extends Specification{
	
	def "Validate Get Response Code" ()
	{
		
		given:
			def resObj= new GetReq("C:\\Users\\Lenovo\\git\\RestAPI\\RestApi\\data\\TestData.xlsx")
		when:
			def actResCode = resObj.sendGetReq()
			def expResCode = resObj.expecResCode()
		then:
			actResCode == expResCode
	}
	
	def "Validate Post  Response Code" ()
	{
		given:
			def postObj= new PostReq("C:\\Users\\Lenovo\\git\\RestAPI\\RestApi\\data\\TestData.xlsx")
		when:
			def actResCode = postObj.sendPostReq()
			def expResCode = postObj.expecResCode()
			
			
			
		then:
			actResCode.getStatusCode() == expResCode
			actResCode.jsonPath().get("name") == "RestTest1"
			actResCode.jsonPath().get("job") == "Org2"
			
	
	}
}
