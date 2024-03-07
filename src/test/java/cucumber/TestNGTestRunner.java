package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//Cucumber -> Depend on TestNG, JUnit runners
@CucumberOptions(features = "src/test/java/cucumber", glue = "christianacademy.cucumberStepDefinitions", 
monochrome = true, tags = "@Regression", plugin = {"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests{
	
}
