package com.book.poq.configurations.advert;

import com.book.poq.adapters.AdvertAdapter;
import com.book.poq.adapters.impl.AdvertAdapterImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableConfigurationProperties(PoqAdvertProperties.class)
public class PoqAdvertConfiguration {
    @Bean
    public AdvertAdapter advertAdapter(WebClient.Builder builder, PoqAdvertProperties poqAdvertProperties){
        return new AdvertAdapterImpl(builder, poqAdvertProperties);
    }
}
