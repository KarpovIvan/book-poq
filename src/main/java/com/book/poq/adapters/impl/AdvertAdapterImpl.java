package com.book.poq.adapters.impl;

import com.book.poq.adapters.AdvertAdapter;
import com.book.poq.configurations.advert.PoqAdvertProperties;
import com.book.poq.model.Advert;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class AdvertAdapterImpl implements AdvertAdapter {

    private WebClient webClient;
    private PoqAdvertProperties poqAdvertProperties;

    public AdvertAdapterImpl(WebClient.Builder builder, PoqAdvertProperties properties) {
        this.poqAdvertProperties = properties;
        this.webClient = builder.baseUrl(poqAdvertProperties.getUrl()).build();
    }

    public Mono<Advert> getAdvert(int id){
        return webClient
                .get()
                .uri("/" + id)
                .retrieve()
                .bodyToMono(Advert.class);
    }

    public Flux<Advert> findAllAdverts(){
        return webClient
                .get()
                .uri("")
                .retrieve()
                .bodyToFlux(Advert.class);
    }
}
