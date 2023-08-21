package be.sample.pages_and_components;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractPage<T> {

    public <T> void set(T element, String input) {
        log.info("Setting {} into element {}", input, element);
    }
}
