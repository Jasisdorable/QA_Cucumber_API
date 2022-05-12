Feature: Login QA_challenge_API 


Scenario: Login test for QA_challenge_API Page
Given Navigate to given URL Link
When  User logged in using user email and password
Then User Successfully Logged in

Scenario: Get a list of email addresses of practice id 2 
Given Navigate to email URL Link
When  Header is "User-Agent" and Authorization is "token"
Then User should be prevented from getting the list

Scenario: Get a list of "veterinary" clinics
Given Navigate to clinic URL Link
When  params key is "veterinary" 
Then Getting the Veterinary clinic lists

