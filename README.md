# hmh
TechnicalTest

To run tests:
	- download java jdk1.8.0_202 https://www.oracle.com/cis/java/technologies/javase/javase8-archive-downloads.html
	- install maven go to root folder and run: mvn clean install
	- https://maven.apache.org/install.html use guide to install maven
	
Tests related:
	- Both endpoints has been checked according to specification https://favqs.com/api
	- The next check has not been implemented
		- Pivate quote field inside list quote endpoint has not been checked as it requires pro user session for verification
		- Unfav error related to private quote has not been cheked as well as it is not possible to find exiting private quote across first 100 pages
	- Known issues are:
		- Hidden filter dosn't return any quote in json but all other requests returns "No quote found" assune it as bug
		- Endpoint PUT /api/quotes/:quote_id/fav returns error model that doesn't meet requirmets assume it as bug
