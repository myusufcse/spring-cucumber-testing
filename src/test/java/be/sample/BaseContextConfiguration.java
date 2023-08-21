package be.sample;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@Configuration
@ContextConfiguration(classes = BaseContextConfiguration.class, loader = AnnotationConfigContextLoader.class)
@ComponentScan({"be.sample"})
@PropertySource("classpath:framework.properties")
@EnableAutoConfiguration
public class BaseContextConfiguration {
}
