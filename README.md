**This is a readme for the project evaluation task: crossover.com -> Application Process, QA Engineer -> Step 4, Project Evaluation, PArt 2**

**Pre-requisites:**
* Eclipse
* JRE 1.7+, jars from "lib" added to Project Properties -> Libraries
* Selenium 2.53: selenium-release.storage.googleapis.com/2.53, jars (also from "lib") added to Project Properties -> Libraries
* IE Driver: selenium-release.storage.googleapis.com/2.53/IEDriverServer_x64_2.53.1.zip, should be unzipped into .\utils sub-directory
* JUnit 4.11, mvnrepository.com/artifact/junit/junit/4.11, path to the jar added to CLASSPATH

**Project description**
* Configuration: .\resources\config.properties:
  - uncomment the one you need (comment the other one)
  - the rest is expected key values for the top three countries for validation 
* Main file: CountryKeyFiguresTest, should be run via TestRunner, here is the summary of what it does:
  -  Goto worldbank.org -> Data -> By Country -> High Income:
     - get for each country: GDP, Population, CO2
  - Go home, close browser
  - For top 3 countries:
     - perform validation 
     - log GDP, Population, CO2 per country to stdout
     - export GDP, Population, CO2 per country to csv files in .\out sub-dir

**How to run**
* Running on the virtual PC where it's deployed already, with pre-requisites in place:
  - Open the project in Eclipse
  - Navigate to TestRunner class, Run -> Run (Ctrl+F11)
* Running on some other PC
  - Make sure pre-requisites mentioned above are in place
  - Unzip the project locally, import into Eclipse
  - Run as described above
                      
**Known issues**
* Environment issue: does not run under IE on the virtual PC: 
"Unexpected error launching Internet Explorer. Protected Mode settings are not the same for all zones. 
Enable Protected Mode must be set to the same value (enabled or disabled) for all zones."
Tried to use a workaround (ignoring security domains) - didn't help, though works fine on my local PC with such settings being different - requires additional 
investigation. 
* Navigation back to the country list page didn't work as expected (requires additional investigation), so another approach (open a specific country page
every time) was chosen.
