package be.sample;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ScenarioHooks {
    /**
     * Start driver
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
