Feature: Validating place apis 

@bartek
Scenario Outline: verify if add place API is working
			Given Add place body payload "<name>" "<language>" "<address>"
 			When user calls "post" api with "RESOURCE_ADD" request
			Then the response code is 200
			And "status" in response body is "OK"
			And "scope" in response body is "APP"
			And verify placeId created maps to "<name>" using "get"

			
			
Examples:
|name 				 | language					| address					|
|name bart one| language bart one | address bart one|
|name bart two| language bart two | address bart two|

@delete
Scenario: Verify if delete place api is working
		Given DeletePlace payload 
		When user calls "post" api with "RESOURCE_DELETE" request
		Then the response code is 200
		And "status" in response body is "OK"
		
		
		