package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/Feature",plugin="json:target/jsonReports/Cucumer-report.json",glue={"StepDef"})
public class TestRunner {
//,tags={"@DeletePlace"} add after glue for running this particular testcase
//,tags={"@AddPlace"}
	//,tags={"@UpdatePlace"}
}
