# hmh
TechnicalTest

## Installation

To run tests:

Download java jdk1.8.0_202 https://www.oracle.com/cis/java/technologies/javase/javase8-archive-downloads.html
Install maven (use guide to install maven https://maven.apache.org/install.html) go to root folder and run.
	
```bash
	mvn clean install
```
	
## Tests related

Both endpoints have been checked according to specification https://favqs.com/api

The next checks have not been implemented:

- Pivate filter for list quotes endpoint has not been checked as it requires pro user session to be applied
- Unfav error with message "Private quotes cannot be unfav'd." has not been cheked cause:
	- it is not possible to find existing private quote across first 100 pages
	- it requires pro session to update quote to private
	
Known issues are:

	- Hidden filter doesn't return any quote in response but all other requests return "No quote found". Assume it as bug
	- Endpoint PUT /api/quotes/:quote_id/fav with invalid quote_id returns an error model that doesn't meet requirments. Assume it as bug
