Feature: Validating Place APIs 

@AddPlace @Regression
Scenario Outline:
Validate if place is being successfully added using AddPlaceAPI 

	Given Add place payload with "<name>" "<language>" "<address>" 
	When user calls "ADDPlaceAPI" with "Post" http request 
	Then the API call is success with status code 200 
	And "status" in response body is "OK" 
	And "scope" in response body is "APP" 
	And verify place_id created maps to "<name>" using "GetPlaceAPI" 
	
	Examples: 
		| name           | language |  address      |	
		|Home Address    | English  | J P Nagar     |
		|Office Address  | English  | Marathahalli  |
		
	@DeletePlace	
Scenario: Verify delete place functionality 

	Given DeletePlace payload 
	When user calls "DeletePlaceAPI" with "Post" http request 
	Then the API call is success with status code 200 
	And "status" in response body is "OK" 
 