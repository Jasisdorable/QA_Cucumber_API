package TestRunners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/featurefile",
        		 glue = {"StepDefinitions"},
        	        monochrome = true,
        strict = true
)

public class TestRunner {

}

