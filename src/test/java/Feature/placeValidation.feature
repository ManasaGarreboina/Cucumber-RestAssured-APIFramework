Feature: Api Validation 
@AddPlace @Regression
Scenario Outline: Verifing add place
	Given Add Place Payload <Accuracy> "<name>" "<language>" "<address>" "<Phone_number>" "<Website>"
	When user calls "AddPlaceAPI" with "POST" http request
	Then the API call success with status code 200
	And "status" in response body is "OK"   
	And "scope" in response body is "APP"   
	And verify placeId created maps to "<name>" using "getPlaceAPI"


Examples: 
	|Accuracy	|name	|language	|address	|Phone_number		|Website						|
	|10			|ANI	|English	|10th Streat|(+91) 973 844 3737	|https://rahulshettyacademy.com |
#	|50			|VANI	|Hindi		|5th Streat	|(+91) 973 855 3737	|https://rahulacademy.com 		|

@DeletePlace @Regression
Scenario: Verifing  Delete place

	Given Delete Place Payload
 	When user calls "deletePlaceAPI" with "DELETE" http request 
 	Then the API call success with status code 200
 	And "status" in response body is "OK"   