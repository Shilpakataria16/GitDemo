package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features",plugin="json:target/jsonReports/cucumber-report.json",glue= {"stepDefinations"})
//@CucumberOptions(features="src/test/java/features",glue= {"stepDefinations"},tags= "@AddPlace or @DeletePlace")
public class TestRunner {
//,tags="@AddPlace And @DeletePlace"
	//,tags="@DeletePlace"
	//testrunner will generate json report file
}
