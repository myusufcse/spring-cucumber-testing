package be.sample;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"be.sample"},
        monochrome = true,
        features = "src/test/resources/features"
)
@SuppressWarnings("all")
public class Runner {
}
