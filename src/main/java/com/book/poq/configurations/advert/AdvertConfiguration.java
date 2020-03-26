package com.book.poq.configurations.advert;

import com.book.poq.adapters.AdvertAdapter;
import com.book.poq.adapters.impl.AdvertAdapterImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableConfigurationProperties(AdvertProperties.class)
public class AdvertConfiguration {
    @Bean
    public AdvertAdapter advertAdapter(WebClient.Builder builder, PoqAdvertProperties advertProperties){
        return new AdvertAdapterImpl(builder, advertProperties);
    }
}
