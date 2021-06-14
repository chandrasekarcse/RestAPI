package test

import spock.lang.Specification

class GetSpec extends Specification{
	
	def "Validate Get Response Code" ()
	{
		given:
			def resObj= new GetReq("E:\\ApiPoc\\RestApi\\data\\TestData.xlsx")
		when:
			def actResCode = resObj.sendGetReq()
			def expResCode = resObj.expecResCode()
		then:
			actResCode == expResCode
	}
}
