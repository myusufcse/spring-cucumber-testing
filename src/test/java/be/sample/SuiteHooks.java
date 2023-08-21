package be.sample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Slf4j
@SuppressWarnings("all")
public class SuiteHooks {

    @PostConstruct
    public void setup() {
        log.info("One time setup before execution");
    }

    @PreDestroy
    public void teardown() {
        log.info("One time teardown after execution");
    }
}
