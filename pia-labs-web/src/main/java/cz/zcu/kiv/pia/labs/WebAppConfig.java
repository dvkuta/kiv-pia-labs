package cz.zcu.kiv.pia.labs;

import cz.zcu.pia.labs.number.ConstantNumberService;
import cz.zcu.pia.labs.number.NumberService;
import cz.zcu.pia.labs.number.RandomNumberService;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import java.util.random.RandomGenerator;

import static org.slf4j.LoggerFactory.getLogger;

@Configuration
@EnableWebFlux
@ComponentScan(basePackages = "cz.zcu.kiv.pia.labs")
public class WebAppConfig implements WebFluxConfigurer {
    private static final Logger LOG = getLogger(WebAppConfig.class);

    @EventListener
    public void onContextStarted(ContextStartedEvent cse) {
        LOG.info("*************** APPLICATION CONTEXT STARTED ***************");

        for (String beanName : cse.getApplicationContext().getBeanDefinitionNames()) {
            LOG.info(beanName);
        }
    }

    @Bean
    public RandomGenerator randomGenerator() {
        return RandomGenerator.getDefault();
    }

    @Bean
    public Number getNumber() {
        return 123;
    }

    @Bean
    public NumberService randomNumberService(RandomGenerator randomGenerator) {
        return new RandomNumberService(randomGenerator);
    }

    @Bean
    @RequestScope
    public NumberService constantNumberService(RandomGenerator randomGenerator) {
        return new ConstantNumberService(randomGenerator.nextInt());
    }
}
