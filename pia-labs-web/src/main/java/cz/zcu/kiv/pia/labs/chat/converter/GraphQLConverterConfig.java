package cz.zcu.kiv.pia.labs.chat.converter;

import cz.zcu.kiv.pia.labs.chat.converter.graphql.RoomToRoomVOConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class GraphQLConverterConfig implements WebFluxConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new RoomToRoomVOConverter());
    }
}