package be.sample;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnableAutoConfiguration
@CucumberContextConfiguration
@Slf4j
@SuppressWarnings("unused")
public class ScenarioHooks extends BaseContextConfiguration {

    /**
     * Start mobile driver
     *
     * @param scenario scenario object
     */
    @Before(order = 0)
    public void setup(Scenario scenario) {
        log.info("🔥 Scenario started - {}", scenario.getName());
    }

    /**
     * Generic teardown (runs for all tags)
     */
    @After
    @SneakyThrows
    public void teardown(Scenario scenario) {
        log.info("Scenario {} - {}({})", scenario.getName(), scenario.getStatus(), !scenario.isFailed() ? "✅" : "❌");
    }
}