package Runner;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.DataProvider;

@CucumberOptions(
		features="src/test/resources/Features",
		glue= {"Steps"},
		monochrome = true,
		plugin= {
				"pretty",
				"html:target/report.html",
				"json:target/report.json",
				"junit:target/report.xml",
				},
		tags="@smoketest"
		)
public class TestRunner extends AbstractTestNGCucumberTests {
	@Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
