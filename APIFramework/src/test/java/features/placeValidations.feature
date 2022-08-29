Feature: Validating Place API's
@AddPlace@Regression
Scenario Outline: Verify that Place is succefully added using AddPlaceAPI
Given Add place payload with "<name>" "<language>" "<address>"
When User calls "AddPlaceAPI" using "POST" http request
Then the API call is successfull with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_Id cretaed matches to "<name>" using "GetPlaceAPI" 

Examples: 
	|name 				|language |address 					|
	|Frontline house	|English  |29, side layout, cohen 10|
#	|ABHouse			|Spanish  |14 Marketstreet			|

@DeletePlace@Regression
Scenario: Verify if Delete Place functionality is working
	Given DeletePlace payload
	When User calls "DeletePlaceAPI" using "POST" http request 
	Then the API call is successfull with status code 200
	And "status" in response body is "OK"