package Runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/Features",
publish = true,
monochrome=true,
glue= {"StepDefinition"},
plugin = {"pretty", "json:target/jsonReports/cucumber.json"})
public class TestRunner {

}
